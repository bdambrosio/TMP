/* generated by writeJava methods in Workspace */
package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import com.Tuuyi.TDM.*;
import org.apache.log4j.Logger;
import java.util.*;
import java.lang.reflect.Method;
import org.json.*;
import java.util.concurrent.ConcurrentHashMap;

public class AbstractItem extends  Item {

  protected static final Logger logWriter = Logger.getLogger(AbstractItem.class.getName());
  private static final boolean persistant = false;

  public void inMemoryOnly(boolean local) {inMemoryOnly = local;}
  public boolean getInMemoryOnly() {return inMemoryOnly;}
  public boolean isPersistant() {return persistant;}

  public static boolean isPersistantClass() {return persistant;}

  protected ArrayList<MapReduceItem> hasAbstractionMapping = new ArrayList<MapReduceItem>(0);

  public void setHasAbstractionMappingRawValueInternal(String rawVal) {
  }


  /** if no arg, assume from db **/
  public AbstractItem() {
    this(true);
  }
  /** if from DB, set nonFunctional slot wrappers to stub **/
  /**    so subsequent slot get will do retrieval         **/
  public AbstractItem(boolean fromDB) {
    super(Workspace.getCurrentWorkspace(), fromDB);
  }
  public AbstractItem(Workspace workspace, boolean fromDB) {
    super(workspace, fromDB);
  }
  public boolean hasAbstractionMappingAllResident() {
      return true;
  }

  /** returns true if this object contains the given object without accessing the database **/
  public boolean containsHasAbstractionMapping(MapReduceItem value) {
      return hasAbstractionMapping.contains(value);
  }

  public ArrayList<MapReduceItem> getHasAbstractionMapping() {
      return hasAbstractionMapping;
  }

  /** note that this method does not check for duplicates, which may cause a DB error */
  public void setHasAbstractionMapping(Collection<MapReduceItem> newHasAbstractionMapping) {
    hasAbstractionMapping=new ArrayList<MapReduceItem>(newHasAbstractionMapping);
  }

  public void add1HasAbstractionMapping(MapReduceItem newHasAbstractionMapping) {
    hasAbstractionMapping.add(newHasAbstractionMapping);
  }

  /** note that this method does not check for duplicates, which may cause a DB error */
  public void addAllHasAbstractionMapping(Collection<MapReduceItem> newHasAbstractionMapping) {
    hasAbstractionMapping.addAll(newHasAbstractionMapping);
  }

  public boolean remove1HasAbstractionMapping(MapReduceItem newHasAbstractionMapping) {
    return hasAbstractionMapping.remove(newHasAbstractionMapping);
  }

  /* to support remove operation on collections, java objs are equal if id match */
  public boolean equals(Object o) {
    if (!(o instanceof AbstractItem)) //covers o == null case
      return false;
    AbstractItem other = (AbstractItem)o;
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
      jsonObj.put("class", "AbstractItem");
      jsonObj.put("id", id);
       if (getHasAbstractionMapping() != null) {
         String HasAbstractionMappingAsString = "";
         for (MapReduceItem HasAbstractionMappingitem: getHasAbstractionMapping()) {
           HasAbstractionMappingAsString += HasAbstractionMappingitem.getId() + ",";
           HasAbstractionMappingAsString.replaceFirst(",$", "");
         }
         jsonObj.put("hasAbstractionMapping", HasAbstractionMappingAsString);
       }
      if (getHasAbstraction() != null) {
        jsonObj.put("hasAbstraction", getHasAbstraction().getId());
      }
      jsonObj.put("id", getId());
      jsonObj.put("score", getScore());
      jsonObj.put("itemId", getItemId());
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
      jsonObj.put("class", "AbstractItem");
      jsonObj.put("id", id);
      if (written.contains(this)) {
        return jsonObj;
      }
      written.put(this, this);
       if (getHasAbstractionMapping() != null) {
         String HasAbstractionMappingAsString = "";
         for (MapReduceItem HasAbstractionMappingitem: getHasAbstractionMapping()) {
           HasAbstractionMappingAsString += HasAbstractionMappingitem.getId() + ",";
           HasAbstractionMappingAsString.replaceFirst(",$", "");
         }
         jsonObj.put("hasAbstractionMapping", HasAbstractionMappingAsString);
       }
      if (getHasAbstraction() != null) {
        jsonObj.put("hasAbstraction", getHasAbstraction().asJSONTreeAux(written));
      }
      jsonObj.put("id", getId());
      jsonObj.put("score", getScore());
      jsonObj.put("itemId", getItemId());
      written.remove(this);
    } catch (Exception e1) {
      logWriter.error("Error in marshalling to JSON ", e1);
    }
    return jsonObj;
  }


/** method to update data in caching layer object from JSON **/
  public boolean updateFromJSON (JSONObject jsonObj) {
    try {
      if (!jsonObj.isNull("hasAbstractionMapping")) {
      String [] hasAbstractionMappingAsStrings = jsonObj.optString("hasAbstractionMapping").split(",");
      ArrayList<MapReduceItem> hasAbstractionMappingValues = new ArrayList<MapReduceItem>(hasAbstractionMappingAsStrings.length);
      for (String hasAbstractionMappingItemId: hasAbstractionMappingAsStrings) {
      if (hasAbstractionMappingItemId != null && MapReduceItemManager.getInstance().get(Integer.parseInt(hasAbstractionMappingItemId)) != null) {
          hasAbstractionMappingValues.add(MapReduceItemManager.getInstance().get(Integer.parseInt(hasAbstractionMappingItemId)));
        }
      }
      hasAbstractionMapping = hasAbstractionMappingValues;
      }
      if (!jsonObj.isNull("hasAbstraction")) {
        int hasAbstractionId = jsonObj.optInt("hasAbstraction");
        Abstraction value = AbstractionManager.getInstance().get(hasAbstractionId);
        if(value != null) {
            setHasAbstraction(value);
        }
      }
      if (!jsonObj.isNull("score")) {
        setScore(jsonObj.optDouble("score"));
      }
      if (!jsonObj.isNull("itemId")) {
        setItemId(jsonObj.optString("itemId"));
      }
    } catch (Exception e) {
      logWriter.error("Failure updating from JSON", e);
      return false;
    }
    return true;
  }

}
