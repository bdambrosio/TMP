package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

//import

import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.FibonacciHeap;

//import
import java.util.Collection;
//import
import java.util.Iterator;

public class CollectorAddlMethods extends Collector {

  protected FibonacciHeap<MapReduceInProcessItem> inventory= new FibonacciHeap<MapReduceInProcessItem>();
  
  public MapReduceInProcessItem nextItem() {
    MapReduceInProcessItem next = inventory.max().getData();
    inventory.delete(inventory.max());
    next.getHasMapReduceItem().remove1HasMapReduceIPIs(next);
    if (!inventory.isEmpty()) {
      inventory.consolidate();
    }
    return next;
  }
  
  public int size() {
    return inventory.size();
  }
  
  public boolean hasMore() {
    return !inventory.isEmpty();
  }
  
  public static Collector newCollector (Collection<MapReduceItem> c , GraphContext aContext) {
    Collector collector = new Collector();
    collector.context = aContext;
    collector.addAll(c);
    return collector;
  }
  
  public void addAll (Collection<MapReduceItem> c) {
    Iterator<MapReduceItem> itr = c.iterator();
    while (itr.hasNext()) {
      MapReduceItem mri = itr.next();
      collect(mri, mri.getScore());
    }
  }
  
  public static Collector newCollector () {
    Collector collector = new Collector();
    return collector;
  }
  
  public void add(MapReduceInProcessItem item) {
    collect(item.getHasMapReduceItem(), item.getHasDelta()); // redundant and suboptimal w dup checking
  }

  public void collect (MapReduceItem item, double mapValue) {
    for (MapReduceInProcessItem mriIp: item.getHasMapReduceIPIs()) {
      if (mriIp.getHasSink() == this) {
        // already an MRIIP in queue, update
        double newScore = mriIp.getHasHeapNode().key+mapValue - mriIp.getHasHeapNode().key*mapValue;
        mriIp.setHasDelta(newScore);
        try {
          inventory.increaseKey(mriIp.getHasHeapNode(), newScore);
        } catch (Exception e){}
        return;
      } 
    }
    MapReduceInProcessItem mriIp = new MapReduceInProcessItem(item, context.propagationMark);
    mriIp.setHasDelta(mapValue);
    mriIp.setHasSink(this);
    FibonacciHeapNode<MapReduceInProcessItem> fibNode = new FibonacciHeapNode<MapReduceInProcessItem>(mriIp, mapValue);
    mriIp.setHasHeapNode(fibNode);
    inventory.insert (fibNode, mapValue);
    lastConsolidated++;
    if (lastConsolidated % 10 == 0) {
      inventory.consolidate();
    } 
  }
  
  public void clear() {
    inventory.clear();
  }
}
