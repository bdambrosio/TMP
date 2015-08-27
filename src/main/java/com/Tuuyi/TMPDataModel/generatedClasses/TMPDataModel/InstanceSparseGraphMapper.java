package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


/**
 * 
 * @author bruce
 *
 */
public class InstanceSparseGraphMapper extends Mapper {

  private long propagationWorkspaceMark = 0;
  private HashSet<MapReduceItem> startSet = new HashSet<MapReduceItem> ();
  private HashSet<MapReduceItem> inputSet = new HashSet<MapReduceItem> ();
  private HashSet<MapReduceItem> outputSet = new HashSet<MapReduceItem> ();
  private HashSet<MapReduceItem> touchedSet = new HashSet<MapReduceItem> ();
  public static int FORWARD_MAP = 0;
  public static int REVERSE_MAP = 1;
  protected int direction = FORWARD_MAP;
  HashSet<MapReduceInProcessItem> nextSweepQueue = new HashSet<MapReduceInProcessItem>();

  public int getDirection() {
    return direction;
  }

  public void scatter () { 
    scatter(1.0, true);
  }

  public void scatter (double collectThreshold) { 
    scatter(collectThreshold, true, 100, 500);
  }

  public void scatter (double collectThreshold, boolean fillin) { 
    scatter(collectThreshold, fillin, 100, 500);
  }

  public void scatter (double collectThreshold, boolean fillIn, int targetItemCount, long timeLimitMs) { 
    context.propagationMark++;
    Collector localSource;
    Collector localSink;
    if (direction == FORWARD_MAP) {
      localSource = getHasSource();
      localSink = getHasSink();
    } else {
      localSource = getHasSink();
      localSink = getHasSource();
      }
    long start = System.nanoTime();
   while (localSource.hasMore() && localSink.size() < targetItemCount && (System.nanoTime()-start)/1000000 < timeLimitMs) {
      MapReduceInProcessItem item = localSource.nextItem();
      MapReduceItem mri = item.getHasMapReduceItem();
      //logWriter.info("new scatter item:"+item.getHasMapReduceItem().getItem());
      map(item.getHasMapReduceItem(), item.getHasDelta(), item.getPropagationMark(), collectThreshold);
      // mri.setScore(mri.getScore()+item.hasDelta-mri.getScore()*item.hasDelta); // might hve already inferred increases through other channels
      item.hasMapReduceItem.getHasMapReduceIPIs().remove(item);
    }
   if (!fillIn) {
     return;
   }

   InstanceSparseGraph model = (InstanceSparseGraph)getHasModel();
   double priorCollectThreshold = collectThreshold*model.getMaxHeadPrior()*0.1;
   if (localSink.hasMore() ) {
     priorCollectThreshold = localSink.inventory.max().data.getHasMapReduceItem().getScore();
   }
   double lastScore = 1.0;
   int priorIndx = 0;

   priorIndx = 0;
   if (direction == FORWARD_MAP) {
     while (priorIndx < ((InstanceSparseGraph)hasModel).getHeadPriorsSize()  && localSink.size() < 10) {
       MapReduceItem mri = this.getContext().outputBeliefspace.getMRI( ((InstanceSparseGraph)hasModel).getHeadPrior(priorIndx).getItem());
       if (mri.getScore() > priorCollectThreshold) {
         if (!mri.ipiInCollector(localSink) 
             && (context.getPropagationFilter() == null || context.getPropagationFilter().collect(mri.getItem())) ) {
           localSink.collect(mri, mri.getScore());
         }
       } 
       if (((InstanceSparseGraph)hasModel).getHeadPrior(priorIndx).getScore() < priorCollectThreshold) {
         break;
       }
       priorIndx++;
     }

     // final attempt to fill in anything we can find
     while (priorIndx < ((InstanceSparseGraph)hasModel).getHeadPriorsSize() && localSink.size() < 10) {
       MapReduceItem mri = this.getContext().outputBeliefspace.getMRI( ((InstanceSparseGraph)hasModel).getHeadPrior(priorIndx).getItem());
       if (!mri.ipiInCollector(localSink) 
           && (context.getPropagationFilter() == null || context.getPropagationFilter().collect(mri.getItem())) ) {
         localSink.collect(mri, mri.getScore());
       }
       priorIndx++;
     } 
   }  else {
     
     //tail 
     while (priorIndx < ((InstanceSparseGraph)hasModel).getTailPriorsSize()) {
       MapReduceItem mri = this.getContext().outputBeliefspace.getMRI( ((InstanceSparseGraph)hasModel).getTailPrior(priorIndx).getItem());
       if (mri.getScore() > priorCollectThreshold) {
         if (!mri.ipiInCollector(localSink) 
             && (context.getPropagationFilter() == null || context.getPropagationFilter().collect(mri.getItem())) ) {
           localSink.collect(mri, mri.getScore());
         }
       } 
       if (((InstanceSparseGraph)hasModel).getTailPrior(priorIndx).getScore() < priorCollectThreshold) {
         break;
       }
       priorIndx++;
     }

     // final attempt to fill in anything we can find
     while (priorIndx < ((InstanceSparseGraph)hasModel).getTailPriorsSize() && localSink.size() < 5) {
       MapReduceItem mri = this.getContext().outputBeliefspace.getMRI( ((InstanceSparseGraph)hasModel).getTailPrior(priorIndx).getItem());
       if (!mri.ipiInCollector(localSink)  
           && (context.getPropagationFilter() == null || context.getPropagationFilter().collect(mri.getItem())) ) {
         localSink.collect(mri, mri.getScore());
       }
       priorIndx++;
     }
     while (priorIndx < ((InstanceSparseGraph)hasModel).getTailPriorsSize()) {
       MapReduceItem mri = this.getContext().outputBeliefspace.getMRI( ((InstanceSparseGraph)hasModel).getTailPrior(priorIndx).getItem());
       if (mri.getScore() > priorCollectThreshold) {
         if (!mri.ipiInCollector(localSink) 
             && (context.getPropagationFilter() == null || context.getPropagationFilter().collect(mri.getItem())) ) {
           localSink.collect(mri, mri.getScore());
         }
       } 
       if (((InstanceSparseGraph)hasModel).getTailPrior(priorIndx).getScore() < priorCollectThreshold) {
         break;
       }
       priorIndx++;
     }
   }
  }
  
  public void setDirection(int newDirection) {
    if (direction == newDirection) {
      return; // nothing to do
    }
    if (direction == FORWARD_MAP) {
      direction = REVERSE_MAP;
      for (MapReduceInProcessItem queued: nextSweepQueue) {
        getHasSink().add(queued);
      }
    } else {
      direction = FORWARD_MAP;
      for (MapReduceInProcessItem queued: nextSweepQueue) {
        getHasSource().add(queued);
      }
    }
    nextSweepQueue.clear();
  }
  
  public void switchDirection() {
    if (direction == FORWARD_MAP) {
      direction = REVERSE_MAP;
      for (MapReduceInProcessItem queued: nextSweepQueue) {
        getHasSink().add(queued);
      }
    } else {
      direction = FORWARD_MAP;
      for (MapReduceInProcessItem queued: nextSweepQueue) {
        getHasSource().add(queued);
      }
    }
    nextSweepQueue.clear();
  }
  
  public void clear() {
    super.clear();
    inputSet.clear();
    outputSet.clear();
    touchedSet.clear();
  }

  private double propagationThreshold = 0.05;
  public static long used = 0;
  public static long skipped = 0;

  public void map (MapReduceItem item, double value, long propagationMark, double collectThreshold) {
    // propagate one step
    //logWriter.info("new map item:"+item.getItem());
    String seed = item.getItem();
    InstanceSparseGraph model = (InstanceSparseGraph)getHasModel();
    // base is 10x avg prior, can be overridden by factor
    double prior = 0.0;
    if (direction == FORWARD_MAP ) {
      prior = inputNamespace.getWeightedPrior(item.item);
    } else {
      prior = outputNamespace.getWeightedPrior(item.item);
    }
    double localPropagationThreshold = propagationThreshold*(1+collectThreshold/20);
    collectThreshold = collectThreshold*prior;
    HashMap<String,WeightedSubjectArc> arcMap = model.getOutgoingArcs(seed, direction);
    // System.out.println("CoViews for "+((Entity)sdcIn.getWrappedDC()).getHasTitle());
    if (arcMap != null) {
      Collection<WeightedSubjectArc> outgoingArcs = arcMap.values();
      collectThreshold = collectThreshold/outgoingArcs.size();
      double deltaIn = value;
      double sourceProb = item.getScore();
      for (WeightedSubjectArc arc: outgoingArcs) {
        if (//context.propagationMark <= arc.getPropagationMark() || 
            context.getPropagationFilter() != null && !context.getPropagationFilter().propagate(arc.getHasHead())) {
          continue;
        }
        String head = arc.getHasHead();
        String [] tail = arc.getHasTail();
        double likelihoodRatio = arc.getScore();
        double confidence = Math.sqrt(Math.min(10.0, arc.getRawCount())/10.0);
        MapReduceItem targetMRI = null;
        MapReduceItem [] sourceMRIs = null;
        if (direction == FORWARD_MAP ) {
          sourceMRIs = getAsArrayInput(tail);
          targetMRI = getAsOutput(head);
        } else {
          sourceMRIs = getAsArrayInput(head);
          targetMRI = getAsOutput(tail[0]);
        }
        double targetPrior = 0.0;
        if (direction == FORWARD_MAP ) {
          targetPrior = outputNamespace.getWeightedPrior(targetMRI.item);
        } else {
          targetPrior = inputNamespace.getWeightedPrior(targetMRI.item);
        }

        double deltaOut = update(targetMRI, targetPrior, sourceMRIs, item, deltaIn, likelihoodRatio*confidence+1.0*(1-confidence));
        if (deltaOut > localPropagationThreshold) {
          MapReduceItem propagateItem = null;
          if (((InstanceSparseGraphMapper)context.getHasMapMethod()).direction == InstanceSparseGraphMapper.FORWARD_MAP) {
            propagateItem = getAsInput(targetMRI.item);
            context.hasSource.collect(propagateItem, deltaOut);
          } else {
            context.hasSink.collect(propagateItem, deltaOut);
          }
        } else {
          skipped++;
        }
        arc.setPropagationMark(context.propagationMark);// this arc has updated wrt all info produced up to now.
        context.propagationMark++;
        double ct = collectThreshold; //*Math.min(1, (2+getHasSink().inventory.size())/30.0);
        if (deltaOut > ct && targetMRI.getScore() > ct 
            && (context.getPropagationFilter() == null || context.getPropagationFilter().collect(arc.getHasHead())) ) { // don't filter too much initially
          if (((InstanceSparseGraphMapper)context.getHasMapMethod()).direction == InstanceSparseGraphMapper.FORWARD_MAP) {
            context.hasSink.collect(targetMRI, deltaOut);
          } else {
            context.hasSource.collect(targetMRI, deltaOut);
          }
          //store for opposite sweep
          MapReduceInProcessItem mriIp = new MapReduceInProcessItem();
          if (!nextSweepQueue.contains(mriIp)) {
            mriIp.setHasMapReduceItem(targetMRI);
            mriIp.setHasDelta(deltaOut);
            nextSweepQueue.add(mriIp);
          } else {
            skipped --;
          }
          used++;
        } else {
          skipped++;
        }
        // touchedSet.add(coEntitySDC);
      }
    }
  }
  
  public double update(MapReduceItem targetMRI,double targetPrior, MapReduceItem [] sourceMRIs, MapReduceItem triggerItem, double delta, double likelihoodRatio) {
    String item = targetMRI.getItem();
    double oldScore = targetMRI.getScore();
    
    double otherInputPrior = otherInputJointPrior(triggerItem, sourceMRIs);
    double opinion = Math.max(-1.0, Math.min(1.0, delta*targetPrior*otherInputPrior*(likelihoodRatio)));
    double newScore = opinion+oldScore-opinion*oldScore;
    double deltaOut = Math.max(-opinion, Math.min(opinion,  newScore-oldScore)); // can be less than 0 due to rounding error in above formula
    if (deltaOut > 1.0+1.0E-14 || deltaOut < 0.0-1.0E-14 || newScore > 1.0 || newScore < 0.0) {
      logWriter.warn(" delta or newScore out of bounds in update!");
    }
    // logWriter.info("Update: "+headMRI.getItem()+" tail: "+tailProb+"deltaIn:"+delta+"lr: "+likelihoodRatio+"pnorm: "+preNorm+" pnormf: "+preNormF +" deltaO: "+deltaOut+" newScore: "+newScore);
    targetMRI.setScore(newScore);
    return Math.max(0.0,  Math.min(1.0, deltaOut));
  }

  private double otherInputJointPrior(MapReduceItem triggerItem, MapReduceItem [] source) {
    double joint = 1.0;
    for (MapReduceItem item: source) {
      if (item != triggerItem) {
        joint *= item.getScore();
      }
    }
    return joint;
  }
  

}
