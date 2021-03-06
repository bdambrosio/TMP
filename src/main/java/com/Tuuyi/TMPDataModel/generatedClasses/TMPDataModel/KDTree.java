/* generated by writeJava methods in Workspace */
package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import com.Tuuyi.TDM.*;
import org.apache.log4j.Logger;
import java.util.*;
import java.lang.reflect.Method;
import org.json.*;
import java.util.concurrent.ConcurrentHashMap;

public class KDTree extends  GraphTMP {

  protected static final Logger logWriter = Logger.getLogger(KDTree.class.getName());
  private static final boolean persistant = false;

  public void inMemoryOnly(boolean local) {inMemoryOnly = local;}
  public boolean getInMemoryOnly() {return inMemoryOnly;}
  public boolean isPersistant() {return persistant;}

  public static boolean isPersistantClass() {return persistant;}


  /** if no arg, assume from db **/
  public KDTree() {
    this(true);
  }
  /** if from DB, set nonFunctional slot wrappers to stub **/
  /**    so subsequent slot get will do retrieval         **/
  public KDTree(boolean fromDB) {
    super(Workspace.getCurrentWorkspace(), fromDB);
  }
  public KDTree(Workspace workspace, boolean fromDB) {
    super(workspace, fromDB);
  }

  /* to support remove operation on collections, java objs are equal if id match */
  public boolean equals(Object o) {
    if (!(o instanceof KDTree)) //covers o == null case
      return false;
    KDTree other = (KDTree)o;
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
      jsonObj.put("class", "KDTree");
      jsonObj.put("id", id);
      if (getContext() != null) {
        jsonObj.put("context", getContext().getId());
      }
      if (getOutputNamespace() != null) {
        jsonObj.put("outputNamespace", getOutputNamespace().getId());
      }
      if (getInputNamespace() != null) {
        jsonObj.put("inputNamespace", getInputNamespace().getId());
      }
      if (getHasDistributions() != null) {
        JSONArray jsonHasDistributions = new JSONArray();
        for (Distribution row: getHasDistributions()) {
          jsonHasDistributions.put(row.getId());
        }
        jsonObj.put("hasDistributions", jsonHasDistributions);
      }
      jsonObj.put("id", getId());
      jsonObj.put("id", getId());
       if (getHasInstances() != null) {
         String HasInstancesAsString = "";
         for (Instance HasInstancesitem: getHasInstances()) {
           HasInstancesAsString += HasInstancesitem.getId() + ",";
           HasInstancesAsString.replaceFirst(",$", "");
         }
         jsonObj.put("hasInstances", HasInstancesAsString);
       }
      if (getHasPropertySet() != null) {
        jsonObj.put("hasPropertySet", getHasPropertySet().getId());
      }
      if (getHasDomainNodes() != null) {
        jsonObj.put("hasDomainNodes", getHasDomainNodes().getId());
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
      jsonObj.put("class", "KDTree");
      jsonObj.put("id", id);
      if (written.contains(this)) {
        return jsonObj;
      }
      written.put(this, this);
      if (getContext() != null) {
        jsonObj.put("context", getContext().asJSONTreeAux(written));
      }
      if (getOutputNamespace() != null) {
        jsonObj.put("outputNamespace", getOutputNamespace().asJSONTreeAux(written));
      }
      if (getInputNamespace() != null) {
        jsonObj.put("inputNamespace", getInputNamespace().asJSONTreeAux(written));
      }
      if (getHasDistributions() != null) {
        JSONArray jsonHasDistributions = new JSONArray();
        for (Distribution row: getHasDistributions()) {
          jsonHasDistributions.put(row.asJSONTreeAux(written));
        }
        jsonObj.put("hasDistributions", jsonHasDistributions);
      }
      jsonObj.put("id", getId());
      jsonObj.put("id", getId());
       if (getHasInstances() != null) {
         String HasInstancesAsString = "";
         for (Instance HasInstancesitem: getHasInstances()) {
           HasInstancesAsString += HasInstancesitem.getId() + ",";
           HasInstancesAsString.replaceFirst(",$", "");
         }
         jsonObj.put("hasInstances", HasInstancesAsString);
       }
      if (getHasPropertySet() != null) {
        jsonObj.put("hasPropertySet", getHasPropertySet().asJSONTreeAux(written));
      }
      if (getHasDomainNodes() != null) {
        jsonObj.put("hasDomainNodes", getHasDomainNodes().asJSONTreeAux(written));
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
      if (!jsonObj.isNull("context")) {
        int contextId = jsonObj.optInt("context");
        GraphContext value = GraphContextManager.getInstance().get(contextId);
        if(value != null) {
            setContext(value);
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
      JSONArray hasDistributionsArray = jsonObj.optJSONArray("hasDistributions");
      if(hasDistributionsArray != null) {
        ArrayList<Distribution> aListOfHasDistributions = new ArrayList<Distribution>(hasDistributionsArray.length());
        for (int i = 0; i < hasDistributionsArray.length(); i++) {
          int id = hasDistributionsArray.optInt(i);
          if (DistributionManager.getInstance().get(id) != null) {
            aListOfHasDistributions.add(DistributionManager.getInstance().get(id));
          }
        }
        setHasDistributions(aListOfHasDistributions);
      }
      if (!jsonObj.isNull("hasInstances")) {
      String [] hasInstancesAsStrings = jsonObj.optString("hasInstances").split(",");
      ArrayList<Instance> hasInstancesValues = new ArrayList<Instance>(hasInstancesAsStrings.length);
      for (String hasInstancesItemId: hasInstancesAsStrings) {
      if (hasInstancesItemId != null && InstanceManager.getInstance().get(Integer.parseInt(hasInstancesItemId)) != null) {
          hasInstancesValues.add(InstanceManager.getInstance().get(Integer.parseInt(hasInstancesItemId)));
        }
      }
      hasInstances = hasInstancesValues;
      }
      if (!jsonObj.isNull("hasPropertySet")) {
        int hasPropertySetId = jsonObj.optInt("hasPropertySet");
        PropertySet value = PropertySetManager.getInstance().get(hasPropertySetId);
        if(value != null) {
            setHasPropertySet(value);
        }
      }
      if (!jsonObj.isNull("hasDomainNodes")) {
        int hasDomainNodesId = jsonObj.optInt("hasDomainNodes");
        NodeList value = NodeListManager.getInstance().get(hasDomainNodesId);
        if(value != null) {
            setHasDomainNodes(value);
        }
      }
    } catch (Exception e) {
      logWriter.error("Failure updating from JSON", e);
      return false;
    }
    return true;
  }

}
