package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import java.util.HashMap;
//import
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.Namespace.DynamicCount;

public class MapperAddlMethods extends Mapper {

  protected HeapPriorityModel hpModel;
  //private HashMap<String, MapReduceItem> propagationIWorkspace = new HashMap<String, MapReduceItem> ();
  //private HashMap<String, MapReduceItem> propagationOWorkspace = new HashMap<String, MapReduceItem> ();
  public long propagationMark = 0;
  
  public static Mapper newMapper(GraphTMP model, Namespace inputNamespace, Collector source, Namespace outputNamespace, Collector sink) {
    Mapper mapper = new Mapper();
    mapper.setHasSource(source);
    mapper.setHasSink(sink);
    mapper.setHasModel(model);
    return mapper;
  }
  
  public MapReduceItem getAsOutput(String node) {
    MapReduceItem mri = hasSink.beliefspace.getMRI(node);
    if (mri == null) {
        logWriter.error("beliefspace returned null mri:"+node);
        mri = MapReduceItem.newMapReduceItem(node, 0.000000000001);
    }
    return mri;
  }

  public MapReduceItem [] getAsArrayInput(String node) {
    MapReduceItem mri = hasSource.beliefspace.getMRI(node);
    if (mri == null) {
      logWriter.error("beliefspace returned null mri:"+node);
      mri = MapReduceItem.newMapReduceItem(node, 0.000000000001);
    }
    MapReduceItem [] input = new MapReduceItem[1];
    input[0] = mri;
    return input;
  }

  public MapReduceItem getAsInput(String node) {
    MapReduceItem mri = hasSource.beliefspace.getMRI(node);
    if (mri == null) {
      DynamicCount count = ((InstanceSparseGraph)getHasModel()).inputNamespace.getMarginalCnt(node);
      double prior = .001;
      if (count != null) {
        prior = count.getMarginal();
      }
      mri = MapReduceItem.newMapReduceItem(node, prior);
      hasSource.beliefspace.setMRI(node, mri);
    }
    return mri;
  }

  public MapReduceItem [] getAsArrayInput(String [] nodes) {
    MapReduceItem [] input = new MapReduceItem[nodes.length];
    for (int i = 0; i < nodes.length; i++) {
      MapReduceItem mri = hasSource.beliefspace.getMRI(nodes[i]);
      if (mri != null) {
        input[i] = mri;
      }
    }
    return input;
  }

  public void clear() {
    for (MapReduceItem mri: hasSource.beliefspace.getKnownMRIs()) {
      DynamicCount count =inputNamespace.getMarginalCnt(mri.getItem());
      double prior = .001;
      if (count != null) {
        prior = count.getMarginal();
      }
      mri.setScore(prior);
      //mri.setMark(propagationWorkspaceMark);
    }
    for (MapReduceItem mri: hasSink.beliefspace.getKnownMRIs()) {
      DynamicCount count = outputNamespace.getMarginalCnt(mri.getItem());
      double prior = .001;
      if (count != null) {
        prior = count.getMarginal();
      }
      mri.setScore(prior);
      //mri.setMark(propagationWorkspaceMark);
    }
  }

  /**
   * scatters computation over a set of threads
   * should be generic, no need to override
   * 
   * Default is to scatter entire input. An alternative is to stream through based on collector pull.
   */

  public void scatter () { 
    scatter(true);
  }
  
  public void scatter (boolean fillin) {  // not used here, used in extension classes
    context.propagationMark++;
    while (getHasSource().hasMore()) {
      MapReduceInProcessItem item = getHasSource().nextItem();
      //logWriter.info("new scatter item:"+item.getHasMapReduceItem().getItem());
      map(item.getHasMapReduceItem(), item.getHasDelta(), item.getPropagationMark());
      item.hasMapReduceItem.getHasMapReduceIPIs().remove(item);
    }
  }
  
  public void scatter (boolean fillin, int targetCnt, long timeLimitMs) {  // not used here, used in extension classes
    context.propagationMark++;
    while (getHasSource().hasMore()) {
      MapReduceInProcessItem item = getHasSource().nextItem();
      //logWriter.info("new scatter item:"+item.getHasMapReduceItem().getItem());
      map(item.getHasMapReduceItem(), item.getHasDelta(), item.getPropagationMark());
      item.hasMapReduceItem.getHasMapReduceIPIs().remove(item);
    }
  }
  
  public void scatter (double collectThreshold) { 
    scatter (collectThreshold, true);
  }

  public void scatter (double collectThreshold, boolean fillin) { // not used here, used in extension classes
    context.propagationMark++;
    while (getHasSource().hasMore()) {
      MapReduceInProcessItem item = getHasSource().nextItem();
      MapReduceItem mri = item.getHasMapReduceItem();
      //logWriter.info("new scatter item:"+item.getHasMapReduceItem().getItem());
      map(item.getHasMapReduceItem(), item.getHasDelta(), item.getPropagationMark(), collectThreshold);
      mri.setScore(mri.getScore()+item.hasDelta);
      item.hasMapReduceItem.getHasMapReduceIPIs().remove(item);
    }
  }
  
  public void scatter (double collectThreshold, boolean fillin, int targetCnt, long timeLimitMs) { // not used here, used in extension classes
    context.propagationMark++;
    while (getHasSource().hasMore()) {
      MapReduceInProcessItem item = getHasSource().nextItem();
      MapReduceItem mri = item.getHasMapReduceItem();
      //logWriter.info("new scatter item:"+item.getHasMapReduceItem().getItem());
      map(item.getHasMapReduceItem(), item.getHasDelta(), item.getPropagationMark(), collectThreshold);
      mri.setScore(mri.getScore()+item.hasDelta);
      item.hasMapReduceItem.getHasMapReduceIPIs().remove(item);
    }
  }
  
  /**
   * maps a single { item, inputValue} pair through model
   * must be overridden by model-specific mappings
   */
  public void map (MapReduceItem item, double value, long propagationMark) {
    //logWriter.info("new map item:"+item.getItem());
    map (item, value, propagationMark, 0.0);
  }
  /**
   * maps a single { item, inputValue} pair through model
   * must be overridden by model-specific mappings
   */
  public void map (MapReduceItem item, double value, long propagationMark, double collectThreshold) {
    //logWriter.info("new map item:"+item.getItem());
  }
}
