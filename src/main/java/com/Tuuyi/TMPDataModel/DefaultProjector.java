package com.Tuuyi.TMPDataModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.Tuuyi.TMPDataModel.PropagationFilter;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.AbstractItem;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.CatalogOntology;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.GraphContext;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.InstanceSparseGraphMapper;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.Item;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.ItemProjection;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.MapReduceItem;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.OntologyRelation;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.Projector;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.SampleTerm;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.ScoredTerm;

public class DefaultProjector implements Projector {

  private ConcurrentHashMap <String, Item> allEntities = new ConcurrentHashMap<String, Item> (50000);
  private CatalogOntology ontology;
  
  public DefaultProjector(CatalogOntology ontology) {
    this.ontology = ontology;
  }

  public Collection<Item> allEntities() {
    return allEntities.values();
  }

  public Item getItem(String id) {
    return allEntities.get(id);
  }


  public int project(GraphContext context, AbstractItem ai, int targetItemCount, long timeLimit) {
    HashSet<ScoredTerm> target = ontology.parse(ai,  false);
    // sort target by combo of weight and recursive item count (lower first, prefer terms w local items?)
    // sample randomly from: term, then path, then depth
    
    long start = System.nanoTime();
    ArrayList<SampleTerm> targetList = new ArrayList<SampleTerm> ();
    Iterator <ScoredTerm> targetTermIterator = target.iterator();
    
    while (targetTermIterator.hasNext()) {
      ScoredTerm term = targetTermIterator.next();
      targetList.add(new SampleTerm(term, term.relevance()));
    }
    Collections.sort(targetList, new ItemGenerationRelevanceComparator(target));
    while ((System.nanoTime()-start) < timeLimit && context.getHasSink().size() < targetItemCount) {
      ItemProjection sample = sample(context, targetList);
      if (sample != null) {
        double targetPrior = context.getOutputNamespace().getWeightedPrior(sample.getItemId());
        MapReduceItem outputItem = context.getAsOutput(sample.getItemId());
        double delta = update(outputItem, score(sample, target));
        context.getHasSink().collect(outputItem, delta);
      }
    }
    return context.getHasSink().size();
  }
  
  double score(ItemProjection item, HashSet<ScoredTerm> target) {
    double relevanceNorm = 0.0;
    for (ScoredTerm term: target) {
      relevanceNorm += term.relevance();
    }
    if (relevanceNorm < 1.0) {
      relevanceNorm = 1.0;
    }
    double score = 1.0;
    for (ScoredTerm term: target) {
      double termScore = match(item, term);
      score = noisyAnd(score, termScore, term.relevance()/relevanceNorm, target.size());
    }
    return score;
  }
  
  double noisyAnd(double oldScore, double newScore, double strength, int scale) {
    double old1Minus = 1.0-oldScore;
    double new1Minus = 1.0-Math.pow(newScore,1.0/(1+scale));
    //double new1Minus = (1.0-newScore);
    double combo = old1Minus*(1.0-strength)+strength*(old1Minus+new1Minus - (old1Minus*new1Minus));
    if (Double.isNaN(combo) || combo > 0.99) {
      System.out.println("NaN");
    }
    return 1.0-combo;
  }
  
  double update(MapReduceItem item, double newScore) {
    double combo = item.getScore()+newScore - (item.getScore()*newScore);
    double delta = combo-item.getScore();
    item.setScore(combo);
    return delta;
  }
  
  double match (ItemProjection item, ScoredTerm targetTerm) {
    double maxScore =0.0;
    ArrayList<ScoredTerm> map= item.getHasOntologyTerms();
    for (ScoredTerm st: map) {
      double score = subsumes(st, targetTerm);
      if ( score > maxScore) {
        maxScore = score;
      }
    }
    return maxScore;
  }
  
  double subsumes(ScoredTerm st, ScoredTerm targetTerm) {
    if (st.getForTerm() == targetTerm.getForTerm()) { // exact match
      return 1.0;
    }
    for (OntologyRelation relation: st.getForTerm().getAsArg1In()) { // candidate term is child of target
      if (relation.getRelation() == OntologyRelation.GENERALIZATION_RELATION) {
        if (relation.getHasArg2() == targetTerm.getForTerm()) {
          return 1.0;
        } 
      }
    }
    for (OntologyRelation relation: st.getForTerm().getAsArg2In()) { // candidate term is parent of target
      if (relation.getRelation() == OntologyRelation.GENERALIZATION_RELATION) {
        if (relation.getHasArg1() == targetTerm.getForTerm()) {
          return targetTerm.getForTerm().getRecursiveItemCount() / (st.getForTerm().getRecursiveItemCount()+1.0);
        }
      }
    }
    return 0.0; // no match
  }

  protected ItemProjection sample(GraphContext context, ArrayList<SampleTerm> targetList) {
    ItemProjection sample = null;
    double totalSourceRelevance = 0.0;
    for (SampleTerm term: targetList) {
      if (term.hasMore()) {
        totalSourceRelevance += term.getRelevance();
      }
    }
    double threshold = Math.random()*totalSourceRelevance;
    double relevanceSum = 0;
    for (SampleTerm term: targetList) {
      if (! term.hasMore()) {
        continue;
      }
      relevanceSum += term.getRelevance();
      if (relevanceSum >= threshold) {
        sample = term.sample();
        if (context.getPropagationFilter() == null || context.getPropagationFilter().collect(sample.getItemId())) {
          return sample;
        }
      }
    }
    return sample;
  }
}

