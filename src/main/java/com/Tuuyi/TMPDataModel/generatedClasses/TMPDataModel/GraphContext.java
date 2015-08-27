/* generated by writeJava methods in Workspace */
package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import com.Tuuyi.TDM.*;
import org.apache.log4j.Logger;
import java.util.*;
import java.lang.reflect.Method;
import org.json.*;
import java.util.concurrent.ConcurrentHashMap;
import com.Tuuyi.TMPDataModel.PropagationFilter;

public class GraphContext extends DomainConcept {

  protected static final Logger logWriter = Logger.getLogger(GraphContext.class.getName());
  private static final boolean persistant = false;

  public void inMemoryOnly(boolean local) {inMemoryOnly = local;}
  public boolean getInMemoryOnly() {return inMemoryOnly;}
  public boolean isPersistant() {return persistant;}

  public static boolean isPersistantClass() {return persistant;}

  protected Collector hasSink = null;

  protected Beliefspace inputBeliefspace = null;

  long propagationMark;
  protected Channel hasDefaultInputChannel = null;

  double scatterThreshold;
  protected Beliefspace outputBeliefspace = null;

  protected GraphTMP hasModel = null;

  protected Namespace outputNamespace = null;

  protected Namespace inputNamespace = null;

  protected Collector hasSource = null;

  protected Mapper hasMapMethod = null;


  /** if no arg, assume from db **/
  public GraphContext() {
    this(true);
  }
  /** if from DB, set nonFunctional slot wrappers to stub **/
  /**    so subsequent slot get will do retrieval         **/
  public GraphContext(boolean fromDB) {
    super(Workspace.getCurrentWorkspace(), fromDB);
  }
  public GraphContext(Workspace workspace, boolean fromDB) {
    super(workspace, fromDB);
  }
  public boolean HasSinkIsResident() {
      return true;
  }
  public Collector getHasSink() {

    return hasSink;
  }
  public int getHasSinkInternalId() {
    if (hasSink == null) {
      return -1;
    } else { 
      return hasSink.getId();
    }
  }
  public int getId() {
    return id;
  }
  public boolean InputBeliefspaceIsResident() {
      return true;
  }
  public Beliefspace getInputBeliefspace() {

    return inputBeliefspace;
  }
  public int getInputBeliefspaceInternalId() {
    if (inputBeliefspace == null) {
      return -1;
    } else { 
      return inputBeliefspace.getId();
    }
  }
  public long getPropagationMark() {
    return propagationMark;
  }
  public boolean HasDefaultInputChannelIsResident() {
      return true;
  }
  public Channel getHasDefaultInputChannel() {

    return hasDefaultInputChannel;
  }
  public int getHasDefaultInputChannelInternalId() {
    if (hasDefaultInputChannel == null) {
      return -1;
    } else { 
      return hasDefaultInputChannel.getId();
    }
  }
  public double getScatterThreshold() {
    return scatterThreshold;
  }
  public boolean OutputBeliefspaceIsResident() {
      return true;
  }
  public Beliefspace getOutputBeliefspace() {

    return outputBeliefspace;
  }
  public int getOutputBeliefspaceInternalId() {
    if (outputBeliefspace == null) {
      return -1;
    } else { 
      return outputBeliefspace.getId();
    }
  }
  public boolean HasModelIsResident() {
      return true;
  }
  public GraphTMP getHasModel() {

    return hasModel;
  }
  public int getHasModelInternalId() {
    if (hasModel == null) {
      return -1;
    } else { 
      return hasModel.getId();
    }
  }
  public boolean OutputNamespaceIsResident() {
      return true;
  }
  public Namespace getOutputNamespace() {

    return outputNamespace;
  }
  public int getOutputNamespaceInternalId() {
    if (outputNamespace == null) {
      return -1;
    } else { 
      return outputNamespace.getId();
    }
  }
  public boolean InputNamespaceIsResident() {
      return true;
  }
  public Namespace getInputNamespace() {

    return inputNamespace;
  }
  public int getInputNamespaceInternalId() {
    if (inputNamespace == null) {
      return -1;
    } else { 
      return inputNamespace.getId();
    }
  }
  public boolean HasSourceIsResident() {
      return true;
  }
  public Collector getHasSource() {

    return hasSource;
  }
  public int getHasSourceInternalId() {
    if (hasSource == null) {
      return -1;
    } else { 
      return hasSource.getId();
    }
  }
  public boolean HasMapMethodIsResident() {
      return true;
  }
  public Mapper getHasMapMethod() {

    return hasMapMethod;
  }
  public int getHasMapMethodInternalId() {
    if (hasMapMethod == null) {
      return -1;
    } else { 
      return hasMapMethod.getId();
    }
  }

  public void setHasSink(Collector newHasSink) {
    hasSink = newHasSink;
  }
  public void setId (int a_id) {
      if (a_id> -1) {
        id = a_id;
      }
  }

  public void setInputBeliefspace(Beliefspace newInputBeliefspace) {
    inputBeliefspace = newInputBeliefspace;
  }
  public void setPropagationMark (long a_propagationMark) {
    propagationMark = a_propagationMark;
  }

  public void setHasDefaultInputChannel(Channel newHasDefaultInputChannel) {
    hasDefaultInputChannel = newHasDefaultInputChannel;
  }
  public void setScatterThreshold (double a_scatterThreshold) {
    scatterThreshold = a_scatterThreshold;
  }

  public void setOutputBeliefspace(Beliefspace newOutputBeliefspace) {
    outputBeliefspace = newOutputBeliefspace;
  }

  public void setHasModel(GraphTMP newHasModel) {
    hasModel = newHasModel;
  }

  public void setOutputNamespace(Namespace newOutputNamespace) {
    outputNamespace = newOutputNamespace;
  }

  public void setInputNamespace(Namespace newInputNamespace) {
    inputNamespace = newInputNamespace;
  }

  public void setHasSource(Collector newHasSource) {
    hasSource = newHasSource;
  }

  public void setHasMapMethod(Mapper newHasMapMethod) {
    hasMapMethod = newHasMapMethod;
  }

  /* to support remove operation on collections, java objs are equal if id match */
  public boolean equals(Object o) {
    if (!(o instanceof GraphContext)) //covers o == null case
      return false;
    GraphContext other = (GraphContext)o;
    if (this == other)
      return true;
    else
      return other.id == id;
  }

  /* to maintain hashCode contract */
  public int hashCode() {
    if (id == -1) {
      if (isPersistant()) {
        id = Workspace.makeGuid();
      } else {
        id = Workspace.nextId();
      }
    }
    return id;
  }

/** method to marshall data from caching layer object to JSON **/
  public JSONObject asJSON () {
    JSONObject jsonObj = new JSONObject();
    try {
      jsonObj.put("class", "GraphContext");
      jsonObj.put("id", id);
      if (getHasSink() != null) {
        jsonObj.put("hasSink", getHasSink().getId());
      }
      jsonObj.put("id", getId());
      if (getInputBeliefspace() != null) {
        jsonObj.put("inputBeliefspace", getInputBeliefspace().getId());
      }
      jsonObj.put("propagationMark", getPropagationMark());
      if (getHasDefaultInputChannel() != null) {
        jsonObj.put("hasDefaultInputChannel", getHasDefaultInputChannel().getId());
      }
      jsonObj.put("scatterThreshold", getScatterThreshold());
      if (getOutputBeliefspace() != null) {
        jsonObj.put("outputBeliefspace", getOutputBeliefspace().getId());
      }
      if (getHasModel() != null) {
        jsonObj.put("hasModel", getHasModel().getId());
      }
      if (getOutputNamespace() != null) {
        jsonObj.put("outputNamespace", getOutputNamespace().getId());
      }
      if (getInputNamespace() != null) {
        jsonObj.put("inputNamespace", getInputNamespace().getId());
      }
      if (getHasSource() != null) {
        jsonObj.put("hasSource", getHasSource().getId());
      }
      if (getHasMapMethod() != null) {
        jsonObj.put("hasMapMethod", getHasMapMethod().getId());
      }
    } catch (Exception e1) {
      logWriter.error("Error in marshalling to JSON ", e1);
    }
    return jsonObj;
  }


/** method to marshall data from caching layer object to JSON **/
  public JSONObject asJSONTree () {
    ConcurrentHashMap <DomainConcept, DomainConcept> written = new ConcurrentHashMap<DomainConcept, DomainConcept> ();
    return asJSONTreeAux(written);
  }
  public JSONObject asJSONTreeAux (ConcurrentHashMap<DomainConcept, DomainConcept> written) {
    JSONObject jsonObj = new JSONObject();
    try {
      jsonObj.put("class", "GraphContext");
      jsonObj.put("id", id);
      if (written.contains(this)) {
        return jsonObj;
      }
      written.put(this, this);
      if (getHasSink() != null) {
        jsonObj.put("hasSink", getHasSink().asJSONTreeAux(written));
      }
      jsonObj.put("id", getId());
      if (getInputBeliefspace() != null) {
        jsonObj.put("inputBeliefspace", getInputBeliefspace().asJSONTreeAux(written));
      }
      jsonObj.put("propagationMark", getPropagationMark());
      if (getHasDefaultInputChannel() != null) {
        jsonObj.put("hasDefaultInputChannel", getHasDefaultInputChannel().asJSONTreeAux(written));
      }
      jsonObj.put("scatterThreshold", getScatterThreshold());
      if (getOutputBeliefspace() != null) {
        jsonObj.put("outputBeliefspace", getOutputBeliefspace().asJSONTreeAux(written));
      }
      if (getHasModel() != null) {
        jsonObj.put("hasModel", getHasModel().asJSONTreeAux(written));
      }
      if (getOutputNamespace() != null) {
        jsonObj.put("outputNamespace", getOutputNamespace().asJSONTreeAux(written));
      }
      if (getInputNamespace() != null) {
        jsonObj.put("inputNamespace", getInputNamespace().asJSONTreeAux(written));
      }
      if (getHasSource() != null) {
        jsonObj.put("hasSource", getHasSource().asJSONTreeAux(written));
      }
      if (getHasMapMethod() != null) {
        jsonObj.put("hasMapMethod", getHasMapMethod().asJSONTreeAux(written));
      }
      written.remove(this);
    } catch (Exception e1) {
      logWriter.error("Error in marshalling to JSON ", e1);
    }
    return jsonObj;
  }


/** method to update data in caching layer object from JSON **/
  public boolean updateFromJSON (JSONObject jsonObj) {
    try {
      if (!jsonObj.isNull("hasSink")) {
        int hasSinkId = jsonObj.optInt("hasSink");
        Collector value = CollectorManager.getInstance().get(hasSinkId);
        if(value != null) {
            setHasSink(value);
        }
      }
      if (!jsonObj.isNull("inputBeliefspace")) {
        int inputBeliefspaceId = jsonObj.optInt("inputBeliefspace");
        Beliefspace value = BeliefspaceManager.getInstance().get(inputBeliefspaceId);
        if(value != null) {
            setInputBeliefspace(value);
        }
      }
      if (!jsonObj.isNull("propagationMark")) {
        setPropagationMark(jsonObj.optLong("propagationMark"));
      }
      if (!jsonObj.isNull("hasDefaultInputChannel")) {
        int hasDefaultInputChannelId = jsonObj.optInt("hasDefaultInputChannel");
        Channel value = ChannelManager.getInstance().get(hasDefaultInputChannelId);
        if(value != null) {
            setHasDefaultInputChannel(value);
        }
      }
      if (!jsonObj.isNull("scatterThreshold")) {
        setScatterThreshold(jsonObj.optDouble("scatterThreshold"));
      }
      if (!jsonObj.isNull("outputBeliefspace")) {
        int outputBeliefspaceId = jsonObj.optInt("outputBeliefspace");
        Beliefspace value = BeliefspaceManager.getInstance().get(outputBeliefspaceId);
        if(value != null) {
            setOutputBeliefspace(value);
        }
      }
      if (!jsonObj.isNull("hasModel")) {
        int hasModelId = jsonObj.optInt("hasModel");
        GraphTMP value = GraphTMPManager.getInstance().get(hasModelId);
        if(value != null) {
            setHasModel(value);
        }
      }
      if (!jsonObj.isNull("outputNamespace")) {
        int outputNamespaceId = jsonObj.optInt("outputNamespace");
        Namespace value = NamespaceManager.getInstance().get(outputNamespaceId);
        if(value != null) {
            setOutputNamespace(value);
        }
      }
      if (!jsonObj.isNull("inputNamespace")) {
        int inputNamespaceId = jsonObj.optInt("inputNamespace");
        Namespace value = NamespaceManager.getInstance().get(inputNamespaceId);
        if(value != null) {
            setInputNamespace(value);
        }
      }
      if (!jsonObj.isNull("hasSource")) {
        int hasSourceId = jsonObj.optInt("hasSource");
        Collector value = CollectorManager.getInstance().get(hasSourceId);
        if(value != null) {
            setHasSource(value);
        }
      }
      if (!jsonObj.isNull("hasMapMethod")) {
        int hasMapMethodId = jsonObj.optInt("hasMapMethod");
        Mapper value = MapperManager.getInstance().get(hasMapMethodId);
        if(value != null) {
            setHasMapMethod(value);
        }
      }
    } catch (Exception e) {
      logWriter.error("Failure updating from JSON", e);
      return false;
    }
    return true;
  }


 public GraphContext(InstanceSparseGraph graph, double aScatterThreshold) {
  setHasModel(graph);
  clear();
  setScatterThreshold(aScatterThreshold);
 }
  
 public GraphContext(Projection projection, double aScatterThreshold) {
  setHasModel(projection);
  clear();
  setScatterThreshold(aScatterThreshold);
 }
  
 public GraphContext(InstanceSparseGraph graph) {
  setHasModel(graph);
  clear();
  setScatterThreshold(1.0);
 }
  
 public GraphContext(Projection projection) {
  setHasModel(projection);
  clear();
  setScatterThreshold(1.0);
 }
  
  PropagationFilter propagationFilter = null;
  
  /** todo - note this is NOT threadsafe! **/
  private HashMap<WeightedSubjectArc, Integer> propagated = new HashMap<WeightedSubjectArc,Integer> ();
  
  /** assumes graph has been set **/
  public void clear() {
    setInputNamespace(hasModel.getInputNamespace());
    Beliefspace inputBeliefspace = new Beliefspace();
    inputBeliefspace.setNamespace(hasModel.getInputNamespace());
    setInputBeliefspace(inputBeliefspace);
    Beliefspace outputBeliefspace = new Beliefspace();
    outputBeliefspace.setNamespace(hasModel.getOutputNamespace());
    setOutputBeliefspace(outputBeliefspace);
    setOutputNamespace(hasModel.getOutputNamespace());
    setHasSource(Collector.newCollector());
    hasSource.setContext(this);
    hasSource.setNamespace(inputNamespace);
    hasSource.setBeliefspace(inputBeliefspace);
    setHasSink(Collector.newCollector());
    hasSink.setContext(this);
    hasSink.setNamespace(outputNamespace);
    hasSink.setBeliefspace(outputBeliefspace);
    setHasMapMethod(hasModel.newMapper(getHasSource(), getHasSink()));
    hasMapMethod.setContext(this);
    setHasDefaultInputChannel(new Channel(this, 1.0));
    propagated.clear();
  }
  
  public void setPropagationFilter(PropagationFilter p) {
    propagationFilter = p;
  }

  public PropagationFilter getPropagationFilter() {
    return propagationFilter;
  }
  
  public void send(MapReduceItem item, double delta) {
    if (((InstanceSparseGraphMapper)this.getHasMapMethod()).direction == InstanceSparseGraphMapper.FORWARD_MAP) {
      getHasDefaultInputChannel().send(item, delta);
    } else {
      this.hasSink.collect(((InstanceSparseGraphMapper)getHasMapMethod()).getAsOutput(item.item), delta);
    }
  }
  
  
  public MapReduceItem getAsInput(String item) {
    return this.hasMapMethod.getAsInput(item);
  }


  public MapReduceItem getAsOutput(String item) {
    return this.hasMapMethod.getAsOutput(item);
  }


  public void send(ArrayList<MapReduceItem> items) {
    for (MapReduceItem item: items) {
      send(item.getItem(), item.getScore());
    }
  }

  public void send(String item, double delta) {
    if (((InstanceSparseGraphMapper)this.getHasMapMethod()).direction == InstanceSparseGraphMapper.FORWARD_MAP) {
      getHasDefaultInputChannel().send(item, delta);
    } else {
      this.hasSink.collect(((InstanceSparseGraphMapper)getHasMapMethod()).getAsOutput(item), delta);
    }
  }

  public void send(String item) {
    send(item, 1.0);
  }

  public void send(String [] items) {
    for (String item: items) {
      send(item, 1.0);
    }
  }

  public void scatter() {
    getHasMapMethod().scatter(scatterThreshold);
  }
  
  public void scatter(boolean fillin) {
    getHasMapMethod().scatter(scatterThreshold, fillin);
  }
  
  public void scatter(boolean fillin, int targetItemCount, long timeLimitMs) {
    getHasMapMethod().scatter(scatterThreshold, fillin, targetItemCount, timeLimitMs);
  }
  
  public void biScatter(ArrayList<MapReduceItem> seed, double scatterThreshold, int targetCnt, boolean fillin) {
    getHasMapMethod().scatter(scatterThreshold, fillin);
    ArrayList<MapReduceItem> backscatter = gather();
    if (backscatter.size() > targetCnt) {
      hasSink.addAll(backscatter);
      return;
    }
    backscatter.addAll(seed);
    for (MapReduceInProcessItem mripi: ((InstanceSparseGraphMapper)getHasMapMethod()).nextSweepQueue) {
      backscatter.add(mripi.getHasMapReduceItem());
    }
    ArrayList<MapReduceItem>normalizedBackscatter = normalize(backscatter);
    ((InstanceSparseGraphMapper)getHasMapMethod()).switchDirection();
    send(normalizedBackscatter);
    getHasMapMethod().scatter(1.0, fillin); // low threshold on backscatter to ensure we have items to propagate
    backscatter = gather();
    normalizedBackscatter = normalize(backscatter);
    ((InstanceSparseGraphMapper)getHasMapMethod()).switchDirection();
    send(normalizedBackscatter);
    getHasMapMethod().scatter(scatterThreshold, fillin);
  }
  
  /** assumes seed has been set on appropriate collector and initial direction has been set **/
  /** usual use case is initial direction is reverse and seed is in outputCollector         **/
  public void backScatter(double forwardThreshold, boolean fillin) {
    getHasMapMethod().scatter(1, true);
    ArrayList<MapReduceItem> backscatter = gather();
    ArrayList<MapReduceItem>normalizedBackscatter = normalize(backscatter);
    ((InstanceSparseGraphMapper)getHasMapMethod()).switchDirection();
    send(normalizedBackscatter);
    getHasMapMethod().scatter(forwardThreshold, fillin);  }
  
  public void backScatter(boolean fill) {
    getHasMapMethod().scatter(1, fill);
    ArrayList<MapReduceItem> results = gather();
    ((InstanceSparseGraphMapper)getHasMapMethod()).switchDirection();
    send(results);
    getHasMapMethod().scatter(scatterThreshold, fill); 
  }
  
  public ArrayList<MapReduceItem> gather () {
    boolean forward = ((InstanceSparseGraphMapper)this.getHasMapMethod()).direction == InstanceSparseGraphMapper.FORWARD_MAP;
    Collector from = getHasSink();
    if (!forward) {
      from = getHasSource();
    }
   ArrayList<MapReduceItem> result = new ArrayList<MapReduceItem> ();
    MapReduceInProcessItem next = null;
    while (from.hasMore() && (next = from.nextItem()) != null) {
      MapReduceItem item = next.getHasMapReduceItem();
      MapReduceItem mri = (forward ? getHasMapMethod().getAsOutput(item.getItem()) : getHasMapMethod().getAsInput(item.getItem()));
      result.add(mri);
    }
    return result;
  }

  public ArrayList<MapReduceItem> gather (boolean merge) {
    ArrayList<MapReduceItem> result = new ArrayList<MapReduceItem> ();
    boolean forward = ((InstanceSparseGraphMapper)this.getHasMapMethod()).direction == InstanceSparseGraphMapper.FORWARD_MAP;
    Collector from = getHasSink();
    if (!forward) {
      from = getHasSource();
    }
    MapReduceInProcessItem next = null;
    while (from.hasMore() && (next = from.nextItem()) != null) {
      MapReduceItem item = next.getHasMapReduceItem();
      MapReduceItem mri = (forward ? getHasMapMethod().getAsOutput(item.getItem()) : getHasMapMethod().getAsInput(item.getItem()));
      /** hack - new scatter doesn't post delta till after scatter, so we need to do it here. **/
      if (merge) {
        item.setScore(next.getHasDelta()+mri.getScore()-next.getHasDelta()*mri.getScore());
      }
      result.add(item);
    }
    return result;
  }

  ArrayList<MapReduceItem> normalize(ArrayList<MapReduceItem> mris) {
    ArrayList<MapReduceItem> normalized = new ArrayList<MapReduceItem>(mris.size());
    double max = 0.0;
    for (MapReduceItem mri: mris) {
      if (mri.score > max) {
        max = mri.score;
      }
    }
    if (max <= 0.0) {
      return mris;
    }
    for (MapReduceItem mri: mris) {
      normalized.add(new MapReduceItem(mri.item, mri.score/max));
    }
    return normalized;
  }
}
