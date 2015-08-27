package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

//import

import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.FibonacciHeap;

//import
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
//import
import java.util.Iterator;

public class ChannelAddlMethods extends Channel {

  protected double weight = 1.0;
  protected HashMap<String, Double> filter = new HashMap<String, Double> ();
  protected Double defaultScore = 1.0;
  

//DL @Deprecated
//DL // Should not be used, a Channel needs an outputContext!
//DL  public Channel(Collector aCollector, Namespace aNamespace, Beliefspace aBeliefspace, double aWeight) {
//DL    hasDestinationCollector = aCollector;
//DL    namespace = aNamespace;
//DL    beliefspace = aBeliefspace;
//DL    weight = aWeight;
//DL  }

//DL  public Channel(GraphContext anOutputContext, Collector aCollector, Namespace aNamespace, Beliefspace aBeliefspace, double aWeight) {
//DL    hasDestinationCollector = aCollector;
//DL    hasOutputContext = anOutputContext;
//DL    namespace = aNamespace;
//DL    beliefspace = aBeliefspace;
//DL    weight = aWeight;
//DL  }

//DL  public Channel(GraphContext anOutputContext, Collector aCollector, double aWeight) {
//DL    hasDestinationCollector = aCollector;
//DL    hasOutputContext = anOutputContext;
//DL    namespace = aCollector.getNamespace();
//DL    beliefspace = aCollector.getBeliefspace();
//DL    weight = aWeight;
//DL  }

//DL  public Channel(GraphContext anOutputContext, double aWeight) {
//DL    hasDestinationCollector = anOutputContext.getHasSource();
//DL    hasOutputContext = anOutputContext;
//DL    namespace = anOutputContext.getInputNamespace();
//DL    beliefspace = anOutputContext.getInputBeliefspace();
//DL    weight = aWeight;
//DL  }

  public void addFilterTerm(MapReduceItem term) {
    filter.put(term.getItem(), term.getScore());
  }

  /** set filter map to the given set of terms.  **/
  /** empty string is the default score for missing terms, permitting construction of leaky filters **/
  public void setFilterTerms(ArrayList<MapReduceItem> terms) {
    filter = new HashMap<String, Double>(terms.size());
    for (MapReduceItem term: terms) {
      if (term.getItem() == "") {
        defaultScore = term.getScore();
      }
      filter.put(term.getItem(), term.getScore());
    }
  }
  
  public void setDefaultFilterScore(double score) {
    defaultScore = score;
  }
    
  public double getDefaultFilterScore() {
    return defaultScore;
  }
  
  public double filter (MapReduceItem item) {
    if (filter == null || filter.size() == 0) {
      return 1.0;
    }
    ArrayList<MapReduceItem> mapping = abstractionMapping(item);
    double count = 0;
    double score = 1.0;
    for (MapReduceItem mappedToken: mapping) {
      count++;
      Double match = filter.get(mappedToken.getItem());
      if (match == null && filterNamespace != null) {// if we don't have a posterior for this term, use prior
        match = filterNamespace.getWeightedPrior(mappedToken.getItem());
      } 
      if (match != null) {
      score += Math.log(match);
      } else {
        score += Math.log(defaultScore);
      }
    }
    if (count == 0) {
      return 1.0;
    } else {
      return Math.exp(score/count);
    }
  }
  
  /** default abstraction is just the item itself **/
  public ArrayList<MapReduceItem> abstractionMapping(MapReduceItem item) {
    ArrayList<MapReduceItem> result = new ArrayList<MapReduceItem> (1);
    result.add(item);
    return result;
  }
  
  public void send(MapReduceItem item, double delta) {
    MapReduceItem targetNamespaceMRI = beliefspace.getMRI(item.getItem());
    double oldScore = targetNamespaceMRI.getScore();
    double mappedDelta = weight*filter(targetNamespaceMRI)*Math.min(delta, 1.0-oldScore); // can't propagate a change that raises belief over 1
    if (mappedDelta > 0) { 
      targetNamespaceMRI.setScore(oldScore+mappedDelta);
      hasDestinationCollector.collect(targetNamespaceMRI, mappedDelta);
    }
  }
  
  public void send(String item, double delta) {
    MapReduceItem targetNamespaceMRI = beliefspace.getMRI(item);
    double oldScore = targetNamespaceMRI.getScore();
    double mappedDelta = weight*filter(new MapReduceItem(item, delta))*Math.min(delta, 1.0-oldScore); // can't propagate a change that raises belief over 1
    if (mappedDelta > 0) { 
      targetNamespaceMRI.setScore(oldScore+mappedDelta);
      hasDestinationCollector.collect(targetNamespaceMRI, mappedDelta);
    }
  }
    
  public void send(ArrayList<MapReduceItem> items) {
    for (MapReduceItem item: items) {
      send(item.getItem(), item.getScore());
    }
  }


  
  
}
