package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

//import - causes next line to be included in base class
import java.io.BufferedReader;
//import - causes next line to be included in base class
import java.io.Writer;
//import - causes next line to be included in base class
import java.io.IOException;
//import - causes next line to be included in base class
import java.io.PrintStream;
//import - causes next line to be included in base class
import java.io.File;
//import - causes next line to be included in base class
import java.io.FileInputStream;
//import - causes next line to be included in base class
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
//import
import java.util.Set;
//import - causes next line to be included in base class
import java.util.Map;
//import - causes next line to be included in base class
import java.util.Map.Entry;
//import - causes next line to be included in base class



//import - causes next line to be included in base class
import org.json.JSONArray;
//import - causes next line to be included in base class
import org.json.JSONException;
//import - causes next line to be included in base class
import org.json.JSONObject;
//import - causes next line to be included in base class
import sun.misc.IOUtils;

//import - causes next line to be included in base class
import com.Tuuyi.TDM.Workspace;
//import - causes next line to be included in base class
import com.Tuuyi.TMPDataModel.MRIComparator;
//import - causes next line to be included in base class
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.*;
//import - causes next line to be included in base class
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.InstanceSparseGraph.InstanceGraphInstance;
//import - causes next line to be included in base class
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.Namespace.DynamicCount;
//import - causes next line to be included in base class
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * An InstanceSparseGraph is a sparseGraph approximation of the joint over a set of instances.
 * It represents instances as strings, presumably the ids of entities in another DataLayer Ontology
 * The basic rep is a set of nodes and arcs. arcs have 
 *   - scores: the likelihood ratio of the head given the tail
 *   - count: the number of samples the likelihood ratio is computed from
 * One heuristic is to use a disjoint (arcs) set of maximal spanning trees as an approximation of the joint
 * Too expensive to compute, so here we're simply storing the most significant likelihoods > 1
 * 
 * Inference is generally the task of finding the nodes with highest posteriors given a set of observations
 * We assuming priors are low enough that nodes not reached thorugh propagation can be ignored.
 * Core data for propagationis the MapReduceItem and the MapReduceInProcessItem
 * A MapReduceItem is a node and it's current posterior (this makes the computation thread-safe)
 * A MapReduceInProcessItem is a MapReduceItem + a delta being propagated.
 * 
 * @author bruce
 *
 */



import java.io.PrintStream;
//import - causes next line to be included in base class
import java.lang.reflect.InvocationTargetException;
//import - causes next line to be included in base class
import java.lang.reflect.Method;


public class InstanceSparseGraphAddlMethods extends InstanceSparseGraph {

  protected HashMap <String, HashMap<String, WeightedSubjectArc>> tailArcs = new HashMap <String, HashMap<String, WeightedSubjectArc>> ();
  protected HashMap <String, HashMap<String, WeightedSubjectArc>> headArcs = new HashMap <String, HashMap<String, WeightedSubjectArc>> ();
  protected ArrayList<MapReduceItem> headPriors = new ArrayList<MapReduceItem>();
  protected ArrayList<MapReduceItem> tailPriors = new ArrayList<MapReduceItem>();
  //  protected HashMap <String, WeightedSubjectArc> arcs = new HashMap <String, WeightedSubjectArc> ();
  protected long corefsProcessed = 0;
  protected long setsProcessed = 0;
  long lastMajorPrune = 0;
  long lastMinorPrune = 0;
  long lastDate = 0;
  long firstDate = Long.MAX_VALUE;  
  /** holder for scatter filter
   *    ItemFilter(filter);
   *    filter must implement two methods:
   */
   
//DL public void initNamespaces() {
//DL   inputNamespace = new Namespace();
//DL   outputNamespace = new Namespace();
//DL }
   
  long getNumHeadMarginals() {
    return outputNamespace.getMarginalCnts().size();
  }

  long getNumTailMarginals() {
    return inputNamespace.getMarginalCnts().size();
  }

  long getSetsProcessed() {
    return setsProcessed;
  }

  long getCorefsProcessed() {
    return corefsProcessed;
  }
  
  
  public long getHeadPriorsSize() {
    if (headPriors != null) {
      return headPriors.size();
    } else {
      return 0;
    }
  }
  
  public MapReduceItem getHeadPrior(int indx) {
    if (headPriors != null && indx < headPriors.size()) {
      return headPriors.get(indx);
    } else {
      return null;
    }
  }
  
  public long getTailPriorsSize() {
    if (tailPriors != null) {
      return tailPriors.size();
    } else {
      return 0;
    }
  }
  
  public MapReduceItem getTailPrior(int indx) {
    if (tailPriors != null && indx < tailPriors.size()) {
      return tailPriors.get(indx);
    } else {
      return null;
    }
  }
  
  public void loadJSON(BufferedReader reader) {
    inputNamespace = new Namespace();
    outputNamespace = new Namespace();
    tailArcs = new HashMap <String, HashMap<String, WeightedSubjectArc>> ();
    headArcs = new HashMap <String, HashMap<String, WeightedSubjectArc>> ();
    
    long lastPrune = 0;
    try {
      String line = null;
      while ((line=reader.readLine()) != null)  {
        try {
        JSONObject json = new JSONObject(line);
        if ("corefsProcessed".equals(json.optString("type"))) {
          corefsProcessed = json.optLong("corefsProcessed");
        } else if ("setsProcessed".equals(json.optString("type"))) {
          setsProcessed = json.optLong("setsProcessed");
        } else if ("headWeightedItemCountSum".equals(json.optString("type"))) {
          outputNamespace.setWeightedItemCountSum(json.optLong("headWeightedItemCountSum"));
        } else if ("headRawItemCountSum".equals(json.optString("type"))) {
          outputNamespace.setRawItemCountSum(json.optLong("headRawItemCountSum"));
        } else if ("tailWeightedItemCountSum".equals(json.optString("type"))) {
          inputNamespace.setWeightedItemCountSum(json.optLong("tailWeightedItemCountSum"));
        } else if ("tailRawItemCountSum".equals(json.optString("type"))) {
          inputNamespace.setRawItemCountSum(json.optLong("tailRawItemCountSum"));
        } else if ("lastDate".equals(json.optString("type"))) {
          inputNamespace.lastDate = json.optLong("inputLastDate");
          outputNamespace.lastDate = json.optLong("outputLastDate");
        } else if ("firstDate".equals(json.optString("type"))) {
          inputNamespace.firstDate = json.optLong("inputFirstDate");
          outputNamespace.firstDate = json.optLong("outputFirstDate");
        } else if ("id".equals(json.optString("type"))) {
          setId(json.optInt("id"));
        } else if ("headMarginal".equals(json.get("type"))) {
          String node = json.optString("node");
          Namespace.DynamicCount count = outputNamespace.new DynamicCount(json.optJSONObject("count"));
          outputNamespace.setMarginalCnt(node, count);
        } else if ("tailMarginal".equals(json.get("type"))) {
          String node = json.optString("node");
          DynamicCount count = inputNamespace.new DynamicCount(json.optJSONObject("count"));
          inputNamespace.setMarginalCnt(node, count);
        } else if ("marginal".equals(json.get("type"))) { // back compatibility w persist bug
          String node = json.optString("node");
          DynamicCount count = inputNamespace.new DynamicCount(json.optJSONObject("count"));
          inputNamespace.setMarginalCnt(node, count);
        } else {
          WeightedSubjectArc arc = new WeightedSubjectArc();
          arc.updateFromJSON(json);
          addArc(arc);
        }
        } catch (Exception e) {
          logWriter.error("error loading arc: "+line, e);
        }
      }
      reader.close();
      initializeTailPriors();
      initializeHeadPriors();
    } catch (Exception e) {
      logWriter.error("error loading current sparseGraph", e);
    } finally {
      if(reader != null) try {
              reader.close();
      } catch (IOException e) {
              //do something clever with the exception
      }
    }
  }

  public void initializeHeadPriors() {
    headPriors = new ArrayList<MapReduceItem>();
    for (String headToken: outputNamespace.namespace.keySet()) {
      double prior = outputNamespace.getWeightedPrior(headToken);
      MapReduceItem mri = new MapReduceItem(headToken, prior);
      headPriors.add(mri);
    }
    Collections.sort(headPriors, new MRIComparator());
  }
  
  public void initializeTailPriors() {
    tailPriors = new ArrayList<MapReduceItem>();
    for (String tailToken: inputNamespace.namespace.keySet()) {
      double prior = inputNamespace.getWeightedPrior(tailToken);
      MapReduceItem mri = new MapReduceItem(tailToken, prior);
      tailPriors.add(mri);
    }
    Collections.sort(tailPriors, new MRIComparator());
  }
  
  public HashMap<String, WeightedSubjectArc> getOutgoingArcs(String eid, int dir) {
    if (dir == InstanceSparseGraphMapper.FORWARD_MAP) {
      return tailArcs.get(eid);
    } else {
      return headArcs.get(eid);
    }
  }

  public double updateNoisyOr(MapReduceItem headMRI, double opinion) {
    double oldScore = headMRI.getScore();
    double newScore = opinion+oldScore-opinion*oldScore;
    double deltaOut = Math.max(0.0,newScore-oldScore); // can be less than 0 due to rounding error in above formula
    if (deltaOut > 1.0 || deltaOut < 0.0 || newScore > 1.0 || newScore < 0.0) {
      if (newScore > 1.2 || newScore < -0.2) {
        logWriter.warn(" delta or newScore out of bounds in update!");
      }
      if (newScore > 1.0) {
        newScore = 1.0;
      } else if (newScore < 0.0) {
        newScore = 0.0;
      }
    }
    // logWriter.info("Update: "+headMRI.getItem()+" tail: "+tailProb+"deltaIn:"+delta+"lr: "+likelihoodRatio+"pnorm: "+preNorm+" pnormf: "+preNormF +" deltaO: "+deltaOut+" newScore: "+newScore);
    headMRI.setScore(newScore);
    return deltaOut;

  }

  public void  updateGraphFromCoViewLog(String fileName){
    //override and implement for specific Domain classes
  }

  public void pruneCorefsLikelihoodHoeffding (double threshold, double countThreshold) {
    if (threshold <= 0.0) {
      return;
    }
    long pruned = 0;
    double maxHeadPrior = this.getMaxHeadPrior();
    for (HashMap<String, WeightedSubjectArc> arcSet: headArcs.values()) {
      for (Iterator<Entry<String, WeightedSubjectArc>> it = arcSet.entrySet().iterator(); it.hasNext();) {
        Entry<String, WeightedSubjectArc> entry = it.next();
        String key = entry.getKey();
        WeightedSubjectArc arc = entry.getValue();
        WeightedSubjectArc revArc = null;
        if (arc.getHasTail().length == 1) {
          revArc = getArc(arc.getHasHead(), arc.getHasTail()[0], 0, false);
        }
        boolean keyArc = topN(arc, 2);
        keyArc = keyArc && arc.getScore() >= 1.0;
        double pruneThreshold = 2;//(1.0+2.0*sqrtN)/sqrtN;

        double esf = eventSpaceFraction(arc);
        double logRawCount = Math.log(arc.rawCount);
        double earlyBias = (2.0+logRawCount)/(1.0+logRawCount);
        double prior = this.getHeadPrior(arc.getHasHead());
        if (arc.getHasTail().length == 1 && 
            ( // Math.sqrt(arc.getScore())*earlyBias < threshold
                arc.getScore()*earlyBias*prior < maxHeadPrior*threshold) ) {
          it.remove();
          removeArcFromTail(arc);
          pruned++;//  removeArcFromHead(arc); iterator removes from head!
        } else if (arc.getHasTail().length > 1 && !evalInteractionArc(arc)) {
          it.remove();
          removeArcFromTail(arc);
          pruned++;//  removeArcFromHead(arc); iterator removes from head!     
        }
      }
      hypothesizeInteractions(arcSet, threshold);
    }
    logWriter.info("corefs processed: "+ corefsProcessed +" pruned: "+pruned+" post-prune size: " + getNumArcs() );
  }

  public void hypothesizeInteractions(HashMap<String, WeightedSubjectArc> arcSet, double threshold) {
    // find top impact arc: tail prob * conditional
    
    ArrayList<WeightedSubjectArc> arcs = new ArrayList<WeightedSubjectArc> (arcSet.values());
    Collections.sort(arcs, arcComparator);
    findNewArc: for (int i = 0; i < arcs.size()-1; i++) {
      for (int j = i+1; j < arcs.size(); j++) {
        if (!tried(arcs, i, j)) {
          tryInteraction(arcs, i, j);
          getArc(arcs.get(i), arcs.get(j), true, Math.max(arcs.get(i).firstSeen, arcs.get(j).firstSeen));
          break findNewArc;
        }
      }
    }
  }
  
  boolean evalInteractionArc(WeightedSubjectArc arc) {
    //TODO: keep only if interaction likelihood <> product of marginal likelihoods
    if (arc.getRawCount() <= 1) return true; // don't try to evaluate single instance arcs
    else {
      // compute product of marginals\
      // compare to score - if different by a factor of 2, keep
      double marginalScore = 1.0;
      String headElement = arc.getHasHead();
      for (String tailElement: arc.getHasTail()) {
        WeightedSubjectArc arcElement = getArc (tailElement, headElement, 0, false);
        if (arcElement != null) {
          marginalScore *= arcElement.getScore();
        }
      }
      if (arc.getScore() > 2.0*marginalScore || arc.getScore() < 0.5*marginalScore) {
      return true;
      } else {
        return false;
      }
    }
  }

  HashSet<Integer> triedCache = new HashSet<Integer>();
  boolean tried (ArrayList<WeightedSubjectArc> arcs, int i, int j) {
    int key = arcs.hashCode()+i+j;
    return triedCache.contains(key);
  }
  
  void tryInteraction (ArrayList<WeightedSubjectArc> arcs, int i, int j) {
    int key = arcs.hashCode()+i+j;
    triedCache.add(key);
  }

  boolean topN(WeightedSubjectArc arc, int n) {
    // this could be more sophisticated and return true for most "important" top n regardless of actual number of arcs on head or tail
    // but at least this much minimizes graph fragmentation, although it doesn't ensure total connectivity
    String head = arc.getHasHead();
    String tail = arc.getTailAsString();
    HashMap<String, WeightedSubjectArc> headArcs = this.headArcs.get(head);
    HashMap<String, WeightedSubjectArc> tailArcs = this.tailArcs.get(tail);
    if (arc.score > 1.0 && (headArcs == null || tailArcs == null || headArcs.size() < n || tailArcs.size() < n) ){
      return true;
    }
    return false;
  }

  protected String e1FromCorefCnts(String key) {
    int semi = key.indexOf(';');
    String e1Id = key.substring(0,semi);
    return e1Id;
  }

  protected double getHeadMarginalCount(String e) {
    DynamicCount cnt = outputNamespace.getMarginalCnt(e);
    if (cnt == null) {
      return 0;
    } else {
      return cnt.weightedCount;
    }
  }
  
  protected double getTailMarginalCount(String e) {
    DynamicCount cnt = inputNamespace.getMarginalCnt(e);
    if (cnt == null) {
      return 0;
    } else {
      return cnt.weightedCount;
    }
  }
  
  public double getHeadWeightedMovingPrior(String e) {
    DynamicCount cnt = outputNamespace.getMarginalCnt(e);
    if (cnt == null) {
      return 0.000001;
    } else {
      return cnt.getMarginal();
    }
  }

  public double getTailWeightedMovingPrior(String e) {
    DynamicCount cnt = inputNamespace.getMarginalCnt(e);
    if (cnt == null) {
      return 0.000001;
    } else {
      return cnt.getMarginal();
    }
  }

  public double getHeadPrior(String id) {
    DynamicCount cnt = outputNamespace.getMarginalCnt(id);
    if (cnt == null) {
      return 0.5/setsProcessed;
    } else {
      return cnt.getMarginal();
    }
  }

  public double getMaxHeadPrior() {
     double maxPrior = 1.0/setsProcessed;
     for (Entry<String, DynamicCount> marginal: outputNamespace.getMarginalCntsEntrySet()) {
       DynamicCount cnt = marginal.getValue();
       if (cnt != null) {
         double prior = cnt.getMarginal();
         if (prior > maxPrior) {
           maxPrior = prior;
         }
       }
     }
     return maxPrior;
  }
    
  public double getTailPrior(String id) {
    DynamicCount cnt = inputNamespace.getMarginalCnt(id);
    if (cnt == null) {
      return 0.5/setsProcessed;
    } else {
      return cnt.getMarginal();
    }
  }

  public double getHeadPrior(String [] ids) {
    double prior = 1.0;
    for (String id: ids) {
      prior *= getHeadPrior(id);
    }
    return prior;
  }
  
  public double getTailPrior(String [] ids) {
    double prior = 1.0;
    for (String id: ids) {
      prior *= getTailPrior(id);
    }
    return prior;
  }
  
  public double getLowerHeadBoundedPrior(String id) {
    DynamicCount cnt = outputNamespace.getMarginalCnt(id);
    if (cnt == null || cnt.rawCount < 0.2*outputNamespace.getMarginalCnts().size()) { // lower bound marginals
      return ((0.2*outputNamespace.getMarginalCnts().size())/setsProcessed);
    } else {
      return (cnt.weightedCount+0.5)/outputNamespace.getWeightedItemCountSum();
    }
  }

  public double getTailLowerBoundedPrior(String id) {
    DynamicCount cnt = inputNamespace.getMarginalCnt(id);
    if (cnt == null || cnt.rawCount < 0.2*inputNamespace.getMarginalCnts().size()) { // lower bound marginals
      return ((0.2*inputNamespace.getMarginalCnts().size())/setsProcessed);
    } else {
      return (cnt.weightedCount+0.5)/inputNamespace.getWeightedItemCountSum();
    }
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

  /** persist corefCounts **/
  public JSONObject persistGraph(Writer out, JSONObject result) {
    return persistGraph(out, result, true);
  }

  public JSONObject persistGraph(Writer out, JSONObject result, boolean pruneSingletons) {
  
    try {

      JSONObject stats = new JSONObject();
      stats.put("type", "id");
      stats.put("id", getId());
      if (out != null) {
        out.write(stats.toString()+"\n");
      }
      if (result != null) {
        result.put("stats", stats);
      }
      stats = new JSONObject();
      stats.put("type", "corefsProcessed");
      stats.put("corefsProcessed", corefsProcessed);
      if (out != null) {
        out.write(stats.toString()+"\n");
      }
      if (result != null) {
        result.put("stats", stats);
      }

      stats = new JSONObject();
      stats.put("type", "setsProcessed");
      stats.put("setsProcessed", setsProcessed);
      if (out != null) {
        out.write(stats.toString()+"\n");
      }
      if (result != null) {
        result.put("stats", stats);
      }

      stats = new JSONObject();
      stats.put("type", "firstDate");
      stats.put("inputFirstDate", inputNamespace.firstDate);
      stats.put("outputFirstDate", outputNamespace.firstDate);
      if (out != null) {
        out.write(stats.toString()+"\n");
      }
      if (result != null) {
        result.put("stats", stats);
      }

      stats = new JSONObject();
      stats.put("type", "lastDate");
      stats.put("inputLastDate", inputNamespace.lastDate);
      stats.put("outputLastDate", outputNamespace.lastDate);
      if (out != null) {
        out.write(stats.toString()+"\n");
      }
      if (result != null) {
        result.put("stats", stats);
      }

      stats.put("type", "headWeightedItemCountSum");
      stats.put("headWeightedItemCountSum", outputNamespace.getWeightedItemCountSum());
      if (out != null) {
        out.write(stats.toString()+"\n");
      }
      if (result != null) {
        result.put("stats", stats);
      }

      stats = new JSONObject();
      stats.put("type", "headRawItemCountSum");
      stats.put("headRawItemCountSum", outputNamespace.getRawItemCountSum());
      if (out != null) {
        out.write(stats.toString()+"\n");
      }
      if (result != null) {
        result.put("stats", stats);
      }
      
      stats = new JSONObject();
      stats.put("type", "tailWeightedItemCountSum");
      stats.put("tailWeightedItemCountSum", inputNamespace.getWeightedItemCountSum());
      if (out != null) {
        out.write(stats.toString()+"\n");
      }
      if (result != null) {
        result.put("stats", stats);
      }

      stats = new JSONObject();
      stats.put("type", "tailRawItemCountSum");
      stats.put("tailRawItemCountSum", inputNamespace.getRawItemCountSum());
      if (out != null) {
        out.write(stats.toString()+"\n");
      }
      if (result != null) {
        result.put("stats", stats);
      }
      

      JSONArray headMarginals = new JSONArray();
      for (Entry<String, DynamicCount> marginal: outputNamespace.getMarginalCntsEntrySet()) {
        JSONObject marginalCnt = new JSONObject();
        marginalCnt.put("type", "headMarginal");
        marginalCnt.put("node", marginal.getKey());
        marginalCnt.put("count", marginal.getValue().toJSON());
        if (out != null) {
          out.write(marginalCnt.toString()+"\n");
        }
        if (result != null) {
          headMarginals.put(marginalCnt);
        }
      }
      if (result != null) {
        result.put("headMarginals", headMarginals);
      }

      JSONArray tailMarginals = new JSONArray();
      for (Entry<String, DynamicCount> marginal: inputNamespace.getMarginalCntsEntrySet()) {
        JSONObject marginalCnt = new JSONObject();
        marginalCnt.put("type", "tailMarginal");
        marginalCnt.put("node", marginal.getKey());
        marginalCnt.put("count", marginal.getValue().toJSON());
        if (out != null) {
          out.write(marginalCnt.toString()+"\n");
        }
        if (result != null) {
          tailMarginals.put(marginalCnt);
        }
      }
      if (result != null) {
        result.put("tailMarginals", tailMarginals);
      }

      long arcCnt = 0;
      double sqrtProcessed = Math.sqrt(setsProcessed);
      JSONArray arcs = new JSONArray();
      for (HashMap<String, WeightedSubjectArc> arcSet: headArcs.values()) {
        for (WeightedSubjectArc arc: arcSet.values()) {
          if (pruneSingletons && arc.getRawCount() <= 1) {
            continue;
          }
          //buildGraph(arc);
          JSONObject jsonArc = arc.asJSON();
          jsonArc.put("type", "arc");
          if (out != null) {
            out.write(jsonArc.toString()+"\n");
          }
          if (result != null) {
            arcs.put(arc);
          }
          arcCnt++;
        }
      }
      if (out != null) {
        out.close();
      }
      logWriter.info("headTokens: "+outputNamespace.getMarginalCnts().size() +"tailTokens: "+inputNamespace.getMarginalCnts().size() +", arcs: "+arcCnt);
      logWriter.info("Statistics: "+graphStatistics(1.0, 1.0, 1).toString());
    }catch (Exception e) {
      logWriter.error("Error writing to stream: ", e);
    } 
    return result;
  }

  private static String printArray(ArrayList<String> array) {
    StringBuffer mapBuffer = new StringBuffer();
    mapBuffer.append("[");
    for (String item: array) {
      mapBuffer.append(item+", ");
    }
    mapBuffer.append("]");
    return mapBuffer.toString();
  }



  HashMap <String, ArrayList<String>> variableGraph = new  HashMap <String, ArrayList<String>>();

  ArrayList<InstanceGraphInstance []> coOccurSets = new ArrayList<InstanceGraphInstance[]>();
  int nextCoOccurSet = 0;

  public long processCoOccurBag(InstanceGraphInstance [] itemSet, ArcPredicate predicate, double threshold) {
    return processCoOccurBag(itemSet, 0, 0, 0, 0, threshold, predicate);
  }
  
  public long processCoOccurBag(InstanceGraphInstance [] itemSet, int tailEndIndex, int headStartIndex, long lastPrune, double decay, double threshold) {
    return processCoOccurBag(itemSet, tailEndIndex, headStartIndex, lastPrune, decay, threshold, null);
  }
  
  static int maxSpan = 10;
  public long processCoOccurBag(InstanceGraphInstance [] itemSet, int tailEndIndex, int headStartIndex, long lastPrune, double decay, double threshold, ArcPredicate predicate) {
    /** for namespaces all that matters is this is an event, even if there are no obvservables within it. **/
    inputNamespace.incrEventCount(1);
    outputNamespace.incrEventCount(1);
    if (itemSet != null && itemSet.length > 1) {
      setsProcessed++;
      //coOccurSets.add(itemSet);
      nextCoOccurSet++;
      HashSet<String> headTokensSeen = new HashSet<String>();
      HashSet<String> tailTokensSeen = new HashSet<String>();
      
      for (int eid1Indx = 0; eid1Indx < itemSet.length; eid1Indx++) {
        InstanceGraphInstance e1IGI = itemSet[eid1Indx];
        String eid1 = itemSet[eid1Indx].getItem();
        // note - EVENT is a pair occurs in a set, doesn't matter how often! clarity.
        if (e1IGI.useAsHead && !headTokensSeen.contains(e1IGI.getItem())) {
          outputNamespace.incrMarginalCount(e1IGI); // this will be a recency-weighted count!
          headTokensSeen.add(e1IGI.getItem());
        }
        if (e1IGI.useAsTail && !tailTokensSeen.contains(e1IGI.getItem())) {
          inputNamespace.incrMarginalCount(e1IGI); // this will be a recency-weighted count!
          tailTokensSeen.add(e1IGI.getItem());
        }
      }
      for (int eid1Indx = 0; eid1Indx <= ((tailEndIndex > 0) ? tailEndIndex : itemSet.length - 1); eid1Indx++) {
        InstanceGraphInstance e1IGI = itemSet[eid1Indx];
        if (!e1IGI.useAsTail()) {
          continue;
        }
        String eid1 = e1IGI.getItem();
        for (int eid2Indx = ((headStartIndex > 0) ? headStartIndex : eid1Indx+1);
            eid2Indx < itemSet.length;
            eid2Indx++) {
          double predicateValue = 1.0;
          if (predicate != null) {
            predicateValue = predicate.value(eid1Indx, eid2Indx);
          }
          if (predicateValue <= 0.0) {
            continue;
          }
          InstanceGraphInstance e2IGI = itemSet[eid2Indx];
          String eid2 = e2IGI.getItem();
          if (eid1 != null && eid2 != null && e2IGI.useAsHead() // && !eid1.equals(eid2)
              // && !eid2s.contains(eid2)  // why not count again if visitor returns again and again
              ) {
            // eid2s.add(eid2);
            corefsProcessed++;
            updateInteractionArcs(itemSet, eid1Indx, tailEndIndex, eid2Indx, predicateValue); // update tail interaction arcs

            WeightedSubjectArc arc = getArc(eid1, eid2, e2IGI.date, true);
            double score = updateScore(arc, e1IGI, e2IGI, predicateValue, setsProcessed);
            arc.setScore(score);
          }
        }
      }
    }
    long majorCycle = 10000000;
    long minorCycle = 3000000;
    long major = corefsProcessed % majorCycle;
    if ((corefsProcessed-lastMajorPrune) > majorCycle) {
      pruneCorefsLikelihoodHoeffding(0.3*threshold, 0.0);
      lastMajorPrune = corefsProcessed;
    } else if ((corefsProcessed -lastMinorPrune > minorCycle)) {
      pruneCorefsLikelihoodHoeffding(0.1*threshold, 0.0);
      lastPrune = corefsProcessed;
      lastMinorPrune = corefsProcessed;
    }
    return lastPrune;
  }


  private void updateInteractionArcs(InstanceGraphInstance [] itemSet, int tail1Index, int tailEndIndex, int headIndex, double predicateValue) {
    String tailElement1 = itemSet[tail1Index].item;
    String headElement = itemSet[headIndex].item;
    HashMap<String, WeightedSubjectArc> headArcSet = headArcs.get(headElement);
    if (headArcSet == null) {
      return;
    }
    for (WeightedSubjectArc arc: headArcSet.values()) {
      if (arc.hasTailElement(tailElement1) && arc.getHasTail().length > 1) {
        updateInteractionArc(arc, itemSet, tailEndIndex, itemSet[headIndex].weight, predicateValue);
      }
    }
  }
 
  private void updateInteractionArc(WeightedSubjectArc arc, InstanceGraphInstance [] itemSet, int tailEndIndex, double headWeight, double predicateValue) {
    double tailWeight = 1.0;
    long date = Long.MAX_VALUE;
    for (String tailElement: arc.getHasTail()) {
      InstanceGraphInstance igi = findIGI(tailElement, itemSet, tailEndIndex);
      if (igi == null) {
        return;
      } else {
        tailWeight = tailWeight * igi.weight;
        date = Math.min(igi.date, date); // interactions get date of oldest tail.
      }
    }
    updateInteractionArcScore(arc, tailWeight, headWeight, date, predicateValue);
  }
  
  private InstanceGraphInstance findIGI(String tailElement, InstanceGraphInstance [] itemSet, int tailEndIndex) {
    for (int i = 0; i < (tailEndIndex > 0 ? Math.min(tailEndIndex,  itemSet.length) : itemSet.length); i++) {
      if (tailElement.equals(itemSet[i].item)){
        return itemSet[i];
      } 
    }
    return null;
  }

  public void getArcSetStats(ArrayList<WeightedSubjectArc> arcSet) {
    Collections.sort(arcSet, arcComparator);
    ArrayList<ArrayList<WeightedSubjectArc>> arcPairCandidateSets = new ArrayList<ArrayList<WeightedSubjectArc>> (arcSet.size());
    ArrayList<double [][]> arcStats = new ArrayList<double [][]> (arcSet.size());
    ArrayList<ArrayList <double [][][]>> jointStatSets = new ArrayList<ArrayList<double [][][]>> (arcSet.size());

    WeightedSubjectArc bestA1 = null;      
    WeightedSubjectArc bestA2 = null;
    // to make this somewhat efficient, only consider combining arcs of comparable impact.
    double impactThreshold = Double.MAX_VALUE;
    for (int i = 0; i < arcSet.size()-1; i++) {
      ArrayList<WeightedSubjectArc> arcPairCandidates = new ArrayList<WeightedSubjectArc> (10);
      arcPairCandidateSets.add(arcPairCandidates);
      double arcStat [][] = new double [2][2];
      arcStats.add(arcStat);
      ArrayList<double [][][]> jointStats = new ArrayList<double [][][]> (10);
      jointStatSets.add(jointStats);
      impactThreshold = 0.1*arcSet.get(i).getScore()*arcSet.get(i).getCount();
      for (int j = i+1; j < arcSet.size() && arcSet.get(j).getScore()*arcSet.get(j).getCount() > impactThreshold; j++) {
        arcPairCandidates.add(arcSet.get(j));
        double joint [][][] = new double [2][2][2];
        jointStats.add(joint);
      }
    }

  }


  public WeightedSubjectArc getArc(String tail, String head, long date, boolean create) {
    HashMap<String, WeightedSubjectArc> arcs = headArcs.get(head);
    if (arcs == null) {
      if (create) {
        arcs = new HashMap<String, WeightedSubjectArc> (7);
      } else {
        return null;
      }
    }
    if (arcs.get(tail) != null) {
      return arcs.get(tail);
    }
    //for (WeightedSubjectArc arc: arcs) {
    //  if (arc.hasTail.equals(tail)) {
    //    return arc;
    //  }
    //}
    if (create) {
      WeightedSubjectArc arc = new WeightedSubjectArc();
      arc.setHasHead(head);
      arc.setHasTail(new String[1]);
      arc.getHasTail()[0]=tail;
      arc.setCount(0);
      arc.setRawCount(0);
      arc.setFirstSeen(date);
      arc.setScore(0.0);
      addArc(arc);
      return arc;
    }
    else {
      return null;
    }
  }

  public WeightedSubjectArc getArc(String [] tail, String head, boolean create, long date) {
    HashMap<String, WeightedSubjectArc> arcs = headArcs.get(head);
    if (arcs == null) {
      if (create) {
        arcs = new HashMap<String, WeightedSubjectArc> (7);
      } else {
        return null;
      }
    }
    for (WeightedSubjectArc arc: arcs.values()) {
      if (arc.hasTail.length == tail.length) {
        boolean match = true;
        for (int i = 0; i < tail.length; i++) {
          if (!arc.hasTail[i].equals(tail[i])) {
          match = false;
          }
          if (match == true) {
            return arc;
          }
        }
      }
    }
    if (create) {
      WeightedSubjectArc arc = new WeightedSubjectArc();
      arc.setHasHead(head);
      String [] tailClone = tail.clone();
      arc.setHasTail(tailClone);
      arc.setCount(0);
      arc.setRawCount(0);
      arc.setFirstSeen(date);
      arc.setScore(0.0);
      addArc(arc);
      return arc;
    }
    else {
      return null;
    }
  }


  protected void addArc(WeightedSubjectArc arc) {
    HashMap<String, WeightedSubjectArc> headArcSet = headArcs.get(arc.hasHead);
    if (headArcSet == null) {
      headArcSet = new HashMap<String, WeightedSubjectArc> (7);
      headArcs.put(arc.hasHead, headArcSet);
    }
    headArcSet.put(arc.getTailAsString(), arc); // key for entry in headArcSet is tail!

    HashMap<String, WeightedSubjectArc> tailArcSet = tailArcs.get(arc.getHasTail()[0]);
    if (tailArcSet == null) {
      tailArcSet = new HashMap<String, WeightedSubjectArc> (7);
      tailArcs.put(arc.getHasTail()[0], tailArcSet);
    }
    tailArcSet.put(arc.hasHead, arc);
  }

  protected WeightedSubjectArc getArc(WeightedSubjectArc arc1, WeightedSubjectArc arc2, boolean create, long date) {
    String tailString = arc1.jointTailString(arc2);
    String [] jointTail = arc1.jointTail(arc2);
    HashMap<String, WeightedSubjectArc> headArcSet = headArcs.get(arc1.hasHead);
      if (headArcSet == null) {
        headArcSet = new HashMap<String, WeightedSubjectArc> (7);
        headArcs.put(arc1.hasHead, headArcSet);
      }
      WeightedSubjectArc jointArc = headArcSet.get(tailString);
      if (jointArc != null) {
        return jointArc;
      }
      if (create) {
        jointArc = new WeightedSubjectArc();
        jointArc.setHasHead(arc1.getHasHead());
        String [] tailClone = jointTail.clone();
        jointArc.setHasTail(tailClone);
        jointArc.setCount(arc1.getCount()*arc2.getCount()/(inputNamespace.getWeightedItemCountSum()*inputNamespace.getWeightedItemCountSum()));
        jointArc.setRawCount(1);
        jointArc.setFirstSeen(date);
        jointArc.setScore(arc1.getScore()*arc2.getScore());
        
        inputNamespace.setMarginalCnt(tailString,  inputNamespace.new DynamicCount(date, jointArc.getCount()));

        headArcSet.put(tailString, jointArc); // key for entry in headArcSet is tail!

        HashMap<String, WeightedSubjectArc> tailArcSet = tailArcs.get(tailString);
        if (tailArcSet == null) {
          tailArcSet = new HashMap<String, WeightedSubjectArc> (7);
          tailArcs.put(tailString, tailArcSet);
        }
        tailArcSet.put(arc1.hasHead, jointArc);

        /* index arc under every tail to make it easy to find */
        for (String tailElement: jointTail) {
          tailArcSet = tailArcs.get(tailElement);
          if (tailArcSet == null) {
            tailArcSet = new HashMap<String, WeightedSubjectArc> (7);
            tailArcs.put(tailElement, tailArcSet);
          }
          tailArcSet.put(arc1.hasHead, jointArc);
        }
        return jointArc;
      } else {
        return null;
      }
    }

    protected void removeArc(WeightedSubjectArc arc) {
    // don't do arcs, this is called from inside an iterator that does it.
    HashMap<String, WeightedSubjectArc> headArcSet = headArcs.get(arc.hasHead);
    if (headArcSet != null) {
      headArcSet.remove(arc.hasTail);
    }
    HashMap<String, WeightedSubjectArc> tailArcSet = tailArcs.get(arc.hasTail);
    if (tailArcSet != null) {
      tailArcSet.remove(arc.hasHead);
    }
  }

  protected void removeArcFromHead(WeightedSubjectArc arc) {
    // don't do arcs, this is called from inside an iterator that does it.
    HashMap<String, WeightedSubjectArc> headArcSet = headArcs.get(arc.hasHead);
    if (headArcSet != null) {
      headArcSet.remove(arc.hasTail);
    }
  }

    protected void removeArcFromTail(WeightedSubjectArc arc) {
      // don't do arcs, this is called from inside an iterator that does it.
      HashMap<String, WeightedSubjectArc> tailArcSet = tailArcs.get(arc.getTailAsString());
      if (tailArcSet != null) {
        tailArcSet.remove(arc.hasHead);
      }
      if (arc.getHasTail().length > 1) {
        for (String tailElement: arc.getHasTail()) {
          tailArcSet = tailArcs.get(tailElement);
          if (tailArcSet != null) {
            tailArcSet.remove(arc.hasHead);
          }
        }
      }
    }

  protected double updateScore(WeightedSubjectArc arc, InstanceGraphInstance tailIGI, InstanceGraphInstance headIGI, double predicateValue, long setsProcessed) {
    DynamicCount tailDC = inputNamespace.getMarginalCnt(tailIGI.item);
    DynamicCount headDC = outputNamespace.getMarginalCnt(headIGI.item);
    double tailCount = getTailMarginalCount(tailIGI.item);
    double headCount = getHeadMarginalCount(headIGI.item);
    double pairCount = arc.getCount();
    if (setsProcessed != arc.propagationMark){
      pairCount+= (Math.min(tailIGI.getWeight(), headIGI.getWeight()))*predicateValue;
      arc.setRawCount(arc.getRawCount()+1);
      arc.setCount(pairCount);
      arc.propagationMark = setsProcessed;
    } 
    double rawEventSpaceFraction = rawEventSpaceFraction(arc);
    //double dataDowngrade = Math.min(3.0, arc.getRawCount())/3.0;
    double dataDowngrade = 1.0+4.0/arc.getRawCount();
    double headEventSpace = headDC.eventSpaceFraction();
    double tailEventSpace = tailDC.eventSpaceFraction();
    double headEventFraction = 1.0;
    double tailEventFraction = 1.0;
    if (headDC.birthday > tailDC.birthday) {
      tailEventFraction = headEventSpace/tailEventSpace; // tail didn't exist over entire head event space
    } else {
      headEventFraction = tailEventSpace/headEventSpace; // head didn't exist over entire tail event space
    }
    double score = (pairCount*(outputNamespace.getWeightedItemCountSum()*rawEventSpaceFraction)) 
        / (headEventFraction*(headCount+0.1)*tailEventFraction*(tailCount+0.1) );
    score = Math.pow(score, 1.0/dataDowngrade);
    int intScore= (int)(score * 100);
    score = (intScore*1.0)/100;
    return score;
  }

  protected double updateInteractionArcScore(WeightedSubjectArc arc,  double tailWeight, double headWeight, long tailFirstSeen, double predicateValue) {
    String tailString = arc.getTailAsString();
    String headItem  = arc.getHasHead();
    DynamicCount tailDC = inputNamespace.getMarginalCnt(tailString);
    DynamicCount headDC = outputNamespace.getMarginalCnt(headItem);
    double tailCount = getTailMarginalCount(tailString);
    double headCount = getHeadMarginalCount(headItem);
    double pairCount = arc.getCount();
    pairCount+= ((0.5*tailWeight+0.5*headWeight))*predicateValue;
    arc.setCount(pairCount);
    arc.setRawCount(arc.getRawCount()+1);
 
    double rawEventSpaceFraction = rawEventSpaceFraction(arc);
    //double dataDowngrade = Math.min(3.0, arc.getRawCount())/3.0;
    double dataDowngrade = 1.0;
    double headEventSpace = headDC.eventSpaceFraction();
    double tailEventSpace = tailDC.eventSpaceFraction();
    double headEventFraction = 1.0;
    double tailEventFraction = 1.0;
    if (headDC.birthday > tailDC.birthday) {
      tailEventFraction = headEventSpace/tailEventSpace; // tail didn't exist over entire head event space
    } else {
      headEventFraction = tailEventSpace/headEventSpace; // head didn't exist over entire tail event space
    }
    // divide score by # tail elements because it will fire multiple times!
    double score = (pairCount*dataDowngrade*(outputNamespace.getWeightedItemCountSum()*rawEventSpaceFraction)) 
        / (headEventFraction*(headCount+0.1)*tailEventFraction*(tailCount+0.1)/arc.getHasTail().length );
    int intScore= (int)(score * 100);
    score = (intScore*1.0)/100;
    return score;
  }

  
  protected long getNumArcs() {
    long arcCnt = 0;
    for (HashMap<String, WeightedSubjectArc> arcSet: headArcs.values()) {
      arcCnt+=arcSet.size();
    }
    return arcCnt;
  }

  /**
   * WeightedSubjectArc comparator score in desc order by score*count
   */
  private static final Comparator<WeightedSubjectArc> arcComparator = new Comparator<WeightedSubjectArc>() {
    public int compare(WeightedSubjectArc arc1, WeightedSubjectArc arc2) {
      if (arc1 != null && arc2 != null) {
        double d = arc1.getScore()*arc1.getCount()-arc2.getScore()*arc2.getCount();
        return d > 0 ? -1 : ( d == 0 ? 0 : 1 );
      } else if (arc1 == null) {
        return 1;
      } else {
        return -1;
      }
    }
  };

  /**
   * arcScoreThreshold - the minimum score at least one arc for an input or output token in order to NOT be counted in the ...WNoArcScoreGT1 statistics.
   * scatterThreshold - the scatterThreshold used to scatter tests
   * scatterOutputThreshold - the minimum # of output items that must be obtained to be excluded from inputTokensWNoOutput
   *
   * example output: {"inputInteractionTokens":799,"outputTokens":896,"inputTokensWNoOutput":7140,
   *                  "outputTokensWNoArcScoreGT1":9,"outputTokensWArcs":896,"inputSingletonTokensWArcs":18203,
   *                  "inputSingletonTokens":18210,"inputInteractionTokensWArcs":799,"inputTokensWNoArcScoreGT1":9}
   */
  public JSONObject graphStatistics (double arcScoreThreshold, double scatterThreshold, long scatterOutputThreshold) {
    JSONObject stats = new JSONObject();
    try {
      stats.put("inputSingletonTokens", inputNamespace.getSingletonTokenCount());
      stats.put("outputTokens", outputNamespace.getSingletonTokenCount());
      stats.put("arcs", getSingletonArcCount());
      stats.put("inputInteractionTokens", inputNamespace.getInteractionTokenCount());
      stats.put("inputSingletonTokensWArcs", getInputSingletonTokenWArcCount());
      stats.put("inputInteractionTokensWArcs", getInputInteractionTokenWArcCount());
      stats.put("outputTokensWArcs", getOutputTokenWArcCount());
      stats.put("inputTokensWNoArcScoreGT1", getInputSingletonTokensWNoArcScoreGT(arcScoreThreshold));
      stats.put("outputTokensWNoArcScoreGT1", getOutputTokensWNoArcScoreGT(arcScoreThreshold));
      stats.put("inputTokensWNoOutput", getInputTokensWNoOutput(scatterThreshold, scatterOutputThreshold, false));
      stats.put("inputTokensWNoOutputAfterFill", getInputTokensWNoOutput(scatterThreshold, scatterOutputThreshold, true));
    } catch (JSONException je) {
      logWriter.error("failure creating json statistics" ,je);
    }
    return stats;
  }

  public long getInputSingletonTokenWArcCount() {
    long count = 0;
    for (Map.Entry<String, HashMap<String, WeightedSubjectArc>> arcSet: tailArcs.entrySet()) {
      if (!arcSet.getKey().contains(",") && arcSet.getValue().size() > 0) {
        count++;
      }
    }
    return count;
  }
  
  public long getInputInteractionTokenWArcCount() {
    long count = 0;
    for (Map.Entry<String, HashMap<String, WeightedSubjectArc>> arcSet: tailArcs.entrySet()) {
      if (arcSet.getKey().contains(",") && arcSet.getValue().size() > 0) {
        count++;
      }
    }
    return count;
  }
  
  public long getOutputTokenWArcCount() {
    long count = 0;
    for (Map.Entry<String, HashMap<String, WeightedSubjectArc>> arcSet: headArcs.entrySet()) {
      if (!arcSet.getKey().contains(",") && arcSet.getValue().size() > 0) {
        count++;
      }
    }
    return count;
  }
  
  public long getSingletonArcCount() {
    long count = 0;
    for (Map.Entry<String, HashMap<String, WeightedSubjectArc>> tailSet: tailArcs.entrySet()) {
      if (!tailSet.getKey().contains(",")) {
        count += tailSet.getValue().size();
      }
    }
    return count;
  }

  public long getInputSingletonTokensWNoArcScoreGT(double targetScore) {
    long count = 0;
    for (String tailToken: inputNamespace.getTokenSet()) {
      if (tailToken.contains(",")) {
        continue;
      }
      HashMap<String, WeightedSubjectArc> headSet = tailArcs.get(tailToken);
      if (headSet == null) {
        count++;
      } else {
        boolean foundOne = false;
        for (Map.Entry<String, WeightedSubjectArc> arcEntry: headSet.entrySet()) {
          if (arcEntry.getValue().score > targetScore) {
            foundOne = true;
          }
        }
        if (!foundOne) {
          count++;
        }
      }
    }
    return count;
  } 

  public long getOutputTokensWNoArcScoreGT(double targetScore) {
    long count = 0;
    for (String headToken: outputNamespace.getTokenSet()) {
      HashMap<String, WeightedSubjectArc> headSet = headArcs.get(headToken);
      if (headSet == null) {
        count++;
      } else {
        boolean foundOne = false;
        for (Map.Entry<String, WeightedSubjectArc> arcEntry: headSet.entrySet()) {
          if (arcEntry.getValue().score > targetScore) {
            foundOne = true;
          }
        }
        if (!foundOne) {
          count++;
        }
      }
    }
    return count;
  } 

  public long getInputTokensWNoOutput(double scatterThreshold, long outputCountTarget, boolean fill) {
    GraphContext context = new GraphContext(this);
    long count = 0;
    for (String tail: inputNamespace.getTokenSet()) {
      if (!tail.contains(",")) {
        context.clear();
        context.send(tail, 1.0);
        context.setScatterThreshold(scatterThreshold);
        context.scatter(fill);
        ArrayList<MapReduceItem> result = context.gather();
        if (result.size() < outputCountTarget) {
          count++;
        }
      }
    }
    return count;
  } 

  public long getInputTokensWNoOutputBackScatter(double scatterThreshold, long outputCountTarget, boolean fill) {
    GraphContext context = new GraphContext(this);
    long count = 0;
    for (String tail: inputNamespace.getTokenSet()) {
      if (!tail.contains(",")) {
        context.clear();
        ((InstanceSparseGraphMapper)context.getHasMapMethod()).switchDirection();
        context.send(tail, 1.0);
        context.setScatterThreshold(scatterThreshold);
        context.backScatter(fill);
        ArrayList<MapReduceItem> result = context.gather();
        if (result.size() < outputCountTarget) {
          count++;
        }
      }
    }
    return count;
  } 
  
  public ArrayList<WeightedSubjectArc> amuseEsprit () {
    ArrayList<WeightedSubjectArc> arcs = new ArrayList<WeightedSubjectArc> ();
    for (Map.Entry<String, HashMap<String, WeightedSubjectArc>> arcSet: headArcs.entrySet()) {
      String headToken = arcSet.getKey();
      for (Map.Entry<String, WeightedSubjectArc> arcEntry: arcSet.getValue().entrySet()) {
        String tailToken = arcEntry.getKey();
        WeightedSubjectArc arc = arcEntry.getValue();
        arcs.add(arc);
      }
    }
    Collections.sort(arcs, new ArcAmusementComparator());

    return arcs;
  }


//DL  public static class InstanceGraphInstance {
//DL    String item;
//DL    double weight;
//DL    long date = 0;
//DL    boolean useAsHead = true;
//DL    boolean useAsTail = true;

//DL    public InstanceGraphInstance (String anItem, double aWeight, long aDate) {
//DL      item = anItem;
//DL      weight = aWeight;
//DL      date = aDate;
//DL    }

//DL    public InstanceGraphInstance (String anItem, double aWeight, long aDate, boolean headp, boolean tailp) {
//DL      item = anItem;
//DL      weight = aWeight;
//DL      date = aDate;
//DL      useAsHead = headp;
//DL      useAsTail = tailp;
//DL    }

//DL    public String getItem() {
//DL      return item;
//DL    }

//DL    public double getWeight() {
//DL      return weight;
//DL    }

//DL    public boolean useAsHead() {
//DL      return useAsHead;
//DL    }

//DL    public boolean useAsTail() {
//DL      return useAsTail;
//DL    }

//DL    public long getDate() {
//DL      return date;
//DL    }
//DL  }
    
  public double eventSpaceFraction(WeightedSubjectArc arc) {
    return Math.min(outputNamespace.getMarginalCnt(arc.getHasHead()).eventSpaceFraction(), inputNamespace.getMarginalCnt(arc.getHasTail()[0]).eventSpaceFraction());
    }
  
  public double rawEventSpaceFraction(WeightedSubjectArc arc) {
    return Math.min(outputNamespace.getMarginalCnt(arc.getHasHead()).rawEventSpaceFraction(), inputNamespace.getMarginalCnt(arc.getHasTail()[0]).rawEventSpaceFraction());
    }

  public class ArcAmusementComparator implements Comparator<WeightedSubjectArc> {

    @Override
    public int compare(WeightedSubjectArc arg0, WeightedSubjectArc arg1) {
      if (arg0 == null || arg0.getScore() == 0 ) {
        return 1;
      }
      if (arg1 == null || arg1.getScore() == 0 ) {
        return -1;
      }
      double arg0Score = Math.log(arg0.getScore())+Math.log(Math.max(1, (arg0.rawCount - 7)));
      double arg1Score = Math.log(arg1.getScore())+Math.log(Math.max(1, (arg1.rawCount - 7)));
      
      if (arg0Score > arg1Score) {
        return -1;
      } else if (arg0Score < arg1Score) {
        return 1;
      }
      return 0;
    }
  }



}



