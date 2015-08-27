/* generated by writeJava methods in Workspace */
package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import com.Tuuyi.TDM.*;
import org.apache.log4j.Logger;
import java.util.*;
import java.lang.reflect.Method;
import org.json.*;
import java.util.concurrent.ConcurrentHashMap;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.FibonacciHeap;

public class MapReduceInProcessItem extends DomainConcept {

  protected static final Logger logWriter = Logger.getLogger(MapReduceInProcessItem.class.getName());
  private static final boolean persistant = false;

  public void inMemoryOnly(boolean local) {inMemoryOnly = local;}
  public boolean getInMemoryOnly() {return inMemoryOnly;}
  public boolean isPersistant() {return persistant;}

  public static boolean isPersistantClass() {return persistant;}

  long propagationMark;
  protected Collector hasSink = null;

  double hasDelta;
  protected MapReduceItem hasMapReduceItem = null;


  /** if no arg, assume from db **/
  public MapReduceInProcessItem() {
    this(true);
  }
  /** if from DB, set nonFunctional slot wrappers to stub **/
  /**    so subsequent slot get will do retrieval         **/
  public MapReduceInProcessItem(boolean fromDB) {
    super(Workspace.getCurrentWorkspace(), fromDB);
  }
  public MapReduceInProcessItem(Workspace workspace, boolean fromDB) {
    super(workspace, fromDB);
  }
  public long getPropagationMark() {
    return propagationMark;
  }
  public int getId() {
    return id;
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
  public double getHasDelta() {
    return hasDelta;
  }
  public boolean HasMapReduceItemIsResident() {
      return true;
  }
  public MapReduceItem getHasMapReduceItem() {

    return hasMapReduceItem;
  }
  public int getHasMapReduceItemInternalId() {
    if (hasMapReduceItem == null) {
      return -1;
    } else { 
      return hasMapReduceItem.getId();
    }
  }
  public void setPropagationMark (long a_propagationMark) {
    propagationMark = a_propagationMark;
  }
  public void setId (int a_id) {
      if (a_id> -1) {
        id = a_id;
      }
  }

  public void setHasSink(Collector newHasSink) {
    hasSink = newHasSink;
  }
  public void setHasDelta (double a_hasDelta) {
    hasDelta = a_hasDelta;
  }

  public void setHasMapReduceItem(MapReduceItem newHasMapReduceItem) {
    hasMapReduceItem = newHasMapReduceItem;
  }

  /* to support remove operation on collections, java objs are equal if id match */
  public boolean equals(Object o) {
    if (!(o instanceof MapReduceInProcessItem)) //covers o == null case
      return false;
    MapReduceInProcessItem other = (MapReduceInProcessItem)o;
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
      jsonObj.put("class", "MapReduceInProcessItem");
      jsonObj.put("id", id);
      jsonObj.put("propagationMark", getPropagationMark());
      jsonObj.put("id", getId());
      if (getHasSink() != null) {
        jsonObj.put("hasSink", getHasSink().getId());
      }
      jsonObj.put("hasDelta", getHasDelta());
      if (getHasMapReduceItem() != null) {
        jsonObj.put("hasMapReduceItem", getHasMapReduceItem().getId());
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
      jsonObj.put("class", "MapReduceInProcessItem");
      jsonObj.put("id", id);
      if (written.contains(this)) {
        return jsonObj;
      }
      written.put(this, this);
      jsonObj.put("propagationMark", getPropagationMark());
      jsonObj.put("id", getId());
      if (getHasSink() != null) {
        jsonObj.put("hasSink", getHasSink().asJSONTreeAux(written));
      }
      jsonObj.put("hasDelta", getHasDelta());
      if (getHasMapReduceItem() != null) {
        jsonObj.put("hasMapReduceItem", getHasMapReduceItem().asJSONTreeAux(written));
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
      if (!jsonObj.isNull("propagationMark")) {
        setPropagationMark(jsonObj.optLong("propagationMark"));
      }
      if (!jsonObj.isNull("hasSink")) {
        int hasSinkId = jsonObj.optInt("hasSink");
        Collector value = CollectorManager.getInstance().get(hasSinkId);
        if(value != null) {
            setHasSink(value);
        }
      }
      if (!jsonObj.isNull("hasDelta")) {
        setHasDelta(jsonObj.optDouble("hasDelta"));
      }
      if (!jsonObj.isNull("hasMapReduceItem")) {
        int hasMapReduceItemId = jsonObj.optInt("hasMapReduceItem");
        MapReduceItem value = MapReduceItemManager.getInstance().get(hasMapReduceItemId);
        if(value != null) {
            setHasMapReduceItem(value);
        }
      }
    } catch (Exception e) {
      logWriter.error("Failure updating from JSON", e);
      return false;
    }
    return true;
  }


 FibonacciHeapNode<MapReduceInProcessItem> heapNode = null;
   
  public MapReduceInProcessItem(MapReduceItem mri, long mapperPropagationMark) {
    hasMapReduceItem = mri;
    propagationMark = mapperPropagationMark;
    if (mri != null) {
      if (mri.getHasMapReduceIPIs() == null) {
        mri.setHasMapReduceIPIs(new ArrayList<MapReduceInProcessItem>(4));
      }
      mri.getHasMapReduceIPIs().add(this);
    }  
  }
  
  public FibonacciHeapNode<MapReduceInProcessItem> getHasHeapNode() {
    return heapNode;
  }

  public FibonacciHeapNode<MapReduceInProcessItem> setHasHeapNode(FibonacciHeapNode<MapReduceInProcessItem> fibNode) {
    heapNode = fibNode;
    return heapNode;
  }
  
 
}
