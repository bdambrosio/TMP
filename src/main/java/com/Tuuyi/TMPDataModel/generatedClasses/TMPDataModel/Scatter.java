/* generated by writeJava methods in Workspace */
package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import com.Tuuyi.TDM.*;
import org.apache.log4j.Logger;
import java.util.*;
import java.lang.reflect.Method;
import org.json.*;
import java.util.concurrent.ConcurrentHashMap;

public class Scatter extends DomainConcept {

  protected static final Logger logWriter = Logger.getLogger(Scatter.class.getName());
  private static final boolean persistant = false;

  public void inMemoryOnly(boolean local) {inMemoryOnly = local;}
  public boolean getInMemoryOnly() {return inMemoryOnly;}
  public boolean isPersistant() {return persistant;}

  public static boolean isPersistantClass() {return persistant;}

  protected Mapper hasMapMethod = null;

  protected Collector hasSink = null;

  protected MappingContext hasMappingContext = null;

  protected Collector hasSource = null;


  /** if no arg, assume from db **/
  public Scatter() {
    this(true);
  }
  /** if from DB, set nonFunctional slot wrappers to stub **/
  /**    so subsequent slot get will do retrieval         **/
  public Scatter(boolean fromDB) {
    super(Workspace.getCurrentWorkspace(), fromDB);
  }
  public Scatter(Workspace workspace, boolean fromDB) {
    super(workspace, fromDB);
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
  public boolean HasMappingContextIsResident() {
      return true;
  }
  public MappingContext getHasMappingContext() {

    return hasMappingContext;
  }
  public int getHasMappingContextInternalId() {
    if (hasMappingContext == null) {
      return -1;
    } else { 
      return hasMappingContext.getId();
    }
  }
  public int getId() {
    return id;
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

  public void setHasMapMethod(Mapper newHasMapMethod) {
    hasMapMethod = newHasMapMethod;
  }

  public void setHasSink(Collector newHasSink) {
    hasSink = newHasSink;
  }

  public void setHasMappingContext(MappingContext newHasMappingContext) {
    hasMappingContext = newHasMappingContext;
  }
  public void setId (int a_id) {
      if (a_id> -1) {
        id = a_id;
      }
  }

  public void setHasSource(Collector newHasSource) {
    hasSource = newHasSource;
  }

  /* to support remove operation on collections, java objs are equal if id match */
  public boolean equals(Object o) {
    if (!(o instanceof Scatter)) //covers o == null case
      return false;
    Scatter other = (Scatter)o;
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
      jsonObj.put("class", "Scatter");
      jsonObj.put("id", id);
      if (getHasMapMethod() != null) {
        jsonObj.put("hasMapMethod", getHasMapMethod().getId());
      }
      if (getHasSink() != null) {
        jsonObj.put("hasSink", getHasSink().getId());
      }
      if (getHasMappingContext() != null) {
        jsonObj.put("hasMappingContext", getHasMappingContext().getId());
      }
      jsonObj.put("id", getId());
      if (getHasSource() != null) {
        jsonObj.put("hasSource", getHasSource().getId());
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
      jsonObj.put("class", "Scatter");
      jsonObj.put("id", id);
      if (written.contains(this)) {
        return jsonObj;
      }
      written.put(this, this);
      if (getHasMapMethod() != null) {
        jsonObj.put("hasMapMethod", getHasMapMethod().asJSONTreeAux(written));
      }
      if (getHasSink() != null) {
        jsonObj.put("hasSink", getHasSink().asJSONTreeAux(written));
      }
      if (getHasMappingContext() != null) {
        jsonObj.put("hasMappingContext", getHasMappingContext().asJSONTreeAux(written));
      }
      jsonObj.put("id", getId());
      if (getHasSource() != null) {
        jsonObj.put("hasSource", getHasSource().asJSONTreeAux(written));
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
      if (!jsonObj.isNull("hasMapMethod")) {
        int hasMapMethodId = jsonObj.optInt("hasMapMethod");
        Mapper value = MapperManager.getInstance().get(hasMapMethodId);
        if(value != null) {
            setHasMapMethod(value);
        }
      }
      if (!jsonObj.isNull("hasSink")) {
        int hasSinkId = jsonObj.optInt("hasSink");
        Collector value = CollectorManager.getInstance().get(hasSinkId);
        if(value != null) {
            setHasSink(value);
        }
      }
      if (!jsonObj.isNull("hasMappingContext")) {
        int hasMappingContextId = jsonObj.optInt("hasMappingContext");
        MappingContext value = MappingContextManager.getInstance().get(hasMappingContextId);
        if(value != null) {
            setHasMappingContext(value);
        }
      }
      if (!jsonObj.isNull("hasSource")) {
        int hasSourceId = jsonObj.optInt("hasSource");
        Collector value = CollectorManager.getInstance().get(hasSourceId);
        if(value != null) {
            setHasSource(value);
        }
      }
    } catch (Exception e) {
      logWriter.error("Failure updating from JSON", e);
      return false;
    }
    return true;
  }



}
