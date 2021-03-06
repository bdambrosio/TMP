/* generated by writeJava methods in Workspace */
package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import com.Tuuyi.TDM.*;
import org.apache.log4j.Logger;
import java.util.*;
import java.lang.reflect.Method;
import org.json.*;
import java.util.concurrent.ConcurrentHashMap;

import java.util.Collection;
import java.util.Iterator;

public class Collector extends DomainConcept {

  protected static final Logger logWriter = Logger.getLogger(Collector.class.getName());
  private static final boolean persistant = false;

  public void inMemoryOnly(boolean local) {inMemoryOnly = local;}
  public boolean getInMemoryOnly() {return inMemoryOnly;}
  public boolean isPersistant() {return persistant;}

  public static boolean isPersistantClass() {return persistant;}

  protected Namespace namespace = null;

  int lastConsolidated;
  protected GraphContext context = null;

  protected Beliefspace beliefspace = null;


  /** if no arg, assume from db **/
  public Collector() {
    this(true);
  }
  /** if from DB, set nonFunctional slot wrappers to stub **/
  /**    so subsequent slot get will do retrieval         **/
  public Collector(boolean fromDB) {
    super(Workspace.getCurrentWorkspace(), fromDB);
  }
  public Collector(Workspace workspace, boolean fromDB) {
    super(workspace, fromDB);
  }
  public boolean NamespaceIsResident() {
      return true;
  }
  public Namespace getNamespace() {

    return namespace;
  }
  public int getNamespaceInternalId() {
    if (namespace == null) {
      return -1;
    } else { 
      return namespace.getId();
    }
  }
  public int getLastConsolidated() {
    return lastConsolidated;
  }
  public boolean ContextIsResident() {
      return true;
  }
  public GraphContext getContext() {

    return context;
  }
  public int getContextInternalId() {
    if (context == null) {
      return -1;
    } else { 
      return context.getId();
    }
  }
  public int getId() {
    return id;
  }
  public boolean BeliefspaceIsResident() {
      return true;
  }
  public Beliefspace getBeliefspace() {

    return beliefspace;
  }
  public int getBeliefspaceInternalId() {
    if (beliefspace == null) {
      return -1;
    } else { 
      return beliefspace.getId();
    }
  }

  public void setNamespace(Namespace newNamespace) {
    namespace = newNamespace;
  }
  public void setLastConsolidated (int a_lastConsolidated) {
    lastConsolidated = a_lastConsolidated;
  }

  public void setContext(GraphContext newContext) {
    context = newContext;
  }
  public void setId (int a_id) {
      if (a_id> -1) {
        id = a_id;
      }
  }

  public void setBeliefspace(Beliefspace newBeliefspace) {
    beliefspace = newBeliefspace;
  }

  /* to support remove operation on collections, java objs are equal if id match */
  public boolean equals(Object o) {
    if (!(o instanceof Collector)) //covers o == null case
      return false;
    Collector other = (Collector)o;
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
      jsonObj.put("class", "Collector");
      jsonObj.put("id", id);
      if (getNamespace() != null) {
        jsonObj.put("namespace", getNamespace().getId());
      }
      jsonObj.put("lastConsolidated", getLastConsolidated());
      if (getContext() != null) {
        jsonObj.put("context", getContext().getId());
      }
      jsonObj.put("id", getId());
      if (getBeliefspace() != null) {
        jsonObj.put("beliefspace", getBeliefspace().getId());
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
      jsonObj.put("class", "Collector");
      jsonObj.put("id", id);
      if (written.contains(this)) {
        return jsonObj;
      }
      written.put(this, this);
      if (getNamespace() != null) {
        jsonObj.put("namespace", getNamespace().asJSONTreeAux(written));
      }
      jsonObj.put("lastConsolidated", getLastConsolidated());
      if (getContext() != null) {
        jsonObj.put("context", getContext().asJSONTreeAux(written));
      }
      jsonObj.put("id", getId());
      if (getBeliefspace() != null) {
        jsonObj.put("beliefspace", getBeliefspace().asJSONTreeAux(written));
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
      if (!jsonObj.isNull("namespace")) {
        int namespaceId = jsonObj.optInt("namespace");
        Namespace value = NamespaceManager.getInstance().get(namespaceId);
        if(value != null) {
            setNamespace(value);
        }
      }
      if (!jsonObj.isNull("lastConsolidated")) {
        setLastConsolidated(jsonObj.optInt("lastConsolidated"));
      }
      if (!jsonObj.isNull("context")) {
        int contextId = jsonObj.optInt("context");
        GraphContext value = GraphContextManager.getInstance().get(contextId);
        if(value != null) {
            setContext(value);
        }
      }
      if (!jsonObj.isNull("beliefspace")) {
        int beliefspaceId = jsonObj.optInt("beliefspace");
        Beliefspace value = BeliefspaceManager.getInstance().get(beliefspaceId);
        if(value != null) {
            setBeliefspace(value);
        }
      }
    } catch (Exception e) {
      logWriter.error("Failure updating from JSON", e);
      return false;
    }
    return true;
  }


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
