package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

//import
import com.Tuuyi.TMPDataModel.DefaultProjector;

public class ProjectionAddlMethods extends Projection {
  static {
    initSampleOrder();
  }

  private Projector projector;
  //DL    public Projection(Projector p, Abstraction a) {
  //DL      projector = p;
  //DL      setHasAbstraction(a);
  //DL    }

  //DL    public Projection(Abstraction a) {
  //DL      projector = new DefaultProjector();
  //DL      setHasAbstraction(a);
  //DL    }

  public Projector getProjector() {
    return projector;
  }

  public void setProjector(Projector projector) {
    this.projector = projector;
  }

  public void useDefaultProjector() {
    this.projector = new DefaultProjector(getUsesOntology());
  }

  public long inititalize(Abstraction a, Iterator<CatalogItem> catalog) {
    setHasAbstraction(a);
    long itemCount = 0;
    while (catalog.hasNext()) {
      indexItem(a.abstractItem(catalog.next()));
      itemCount++;
    }
    return itemCount;
  }

  public Collection<ScoredTerm> indexItem(AbstractItem ai) {
    // an ai contains merely an unstructured collection of scoredTokens
    // ontology parses into terms, extending ontology as needed

    ItemProjection item = getUsesOntology().index(ai);
    Collection<ScoredTerm> terms = item.getHasOntologyTerms();
    // now index the item under each term
    for (ScoredTerm term: terms) {
      term.getForTerm().add1HasItems(item);
    }
    return terms;
  }

  static HashMap <Integer, int []> samplingOrder;

  static void initSampleOrder() {
    samplingOrder = new HashMap <Integer, int []> ();
    for (int i = 1; i < 2400000; i=i*2) {
      ArrayList <SampleOrder> sampling = new ArrayList<SampleOrder> (i);
      for (int j = 0; j < i; j++) {
        sampling.add(new SampleOrder(j, Math.random()));
      }
      Collections.sort(sampling);
      int [] ordering = new int[sampling.size()];
      for (int j = 0; j < sampling.size(); j++) {
        ordering[j] = sampling.get(j).sampleIndex;
      }
      samplingOrder.put((int)(Math.log(i)/Math.log(2.0)), ordering);
    }
  }
  
  static int [] getSampleOrder(int size) {
    int index = (int)Math.ceil((Math.log(size)/Math.log(2.0)));
    return samplingOrder.get(index);
  }
  
  public InstanceSparseGraphMapper newMapper(Collector source, Collector sink) {
    InstanceSparseGraphMapper mapper = new InstanceSparseGraphMapper();
    mapper.setInputNamespace(inputNamespace);
    mapper.setOutputNamespace(outputNamespace);
    mapper.setHasSource(source);
    mapper.setHasSink(sink);
    mapper.setHasModel(this);
    mapper.clear();
    return mapper;
  }



  static class SampleOrder implements Comparable {
    int sampleIndex;
    double order;

    SampleOrder (int s, double p) {
      sampleIndex = s;
      order = p;
    }

    public int compareTo(Object arg0) {
      if (SampleOrder.class.isInstance(arg0)) {
        if (order > ((SampleOrder)arg0).order) {
          return -1;
        } else if (order < ((SampleOrder)arg0).order) {
          return 1;
        }
      }
      return 0;
    }

  }
}

