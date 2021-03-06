/* generated by writeJava methods in Workspace */
package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import com.Tuuyi.TDM.*;
import org.apache.log4j.Logger;
import java.util.*;
import java.lang.reflect.Method;
import org.json.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapReduceItem extends DomainConcept {

  protected static final Logger logWriter = Logger.getLogger(MapReduceItem.class.getName());
  private static final boolean persistant = false;

  public void inMemoryOnly(boolean local) {inMemoryOnly = local;}
  public boolean getInMemoryOnly() {return inMemoryOnly;}
  public boolean isPersistant() {return persistant;}

  public static boolean isPersistantClass() {return persistant;}

  double score;
  String item;
  private ArrayList<MapReduceInProcessItem> hasMapReduceIPIs = new ArrayList<MapReduceInProcessItem>();

  /** if no arg, assume from db **/
  public MapReduceItem() {
    this(true);
  }
  /** if from DB, set nonFunctional slot wrappers to stub **/
  /**    so subsequent slot get will do retrieval         **/
  public MapReduceItem(boolean fromDB) {
    super(Workspace.getCurrentWorkspace(), fromDB);
    hasMapReduceIPIs= new ArrayList<MapReduceInProcessItem>(0);
  }
  public MapReduceItem(Workspace workspace, boolean fromDB) {
    super(workspace, fromDB);
    hasMapReduceIPIs= new ArrayList<MapReduceInProcessItem>(0);
  }
  public double getScore() {
    return score;
  }
  public String getItem() {
    return item;
  }
  public int getId() {
    return id;
  }
  public List<MapReduceInProcessItem> getHasMapReduceIPIs() {
      return hasMapReduceIPIs;
  }
  public void setScore (double a_score) {
    score = a_score;
  }
  public void setItem (String a_item) {
    item = a_item;
  }
  public void setId (int a_id) {
      if (a_id> -1) {
        id = a_id;
      }
  }

  /** note that this method does not check for duplicates, which may cause a DB error */
  public void setHasMapReduceIPIs(Collection<MapReduceInProcessItem> newHasMapReduceIPIs) {
    hasMapReduceIPIs=new ArrayList<MapReduceInProcessItem>(newHasMapReduceIPIs);
  }

  public void add1HasMapReduceIPIs(MapReduceInProcessItem newHasMapReduceIPIs) {
    hasMapReduceIPIs.add(newHasMapReduceIPIs);
  }

  /** note that this method does not check for duplicates, which may cause a DB error */
  public void addAllHasMapReduceIPIs(Collection<MapReduceInProcessItem> newHasMapReduceIPIs) {
    hasMapReduceIPIs.addAll(newHasMapReduceIPIs);
  }

  public boolean remove1HasMapReduceIPIs(MapReduceInProcessItem newHasMapReduceIPIs) {
    return hasMapReduceIPIs.remove(newHasMapReduceIPIs);
  }

  /* to support remove operation on collections, java objs are equal if id match */
  public boolean equals(Object o) {
    if (!(o instanceof MapReduceItem)) //covers o == null case
      return false;
    MapReduceItem other = (MapReduceItem)o;
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
      jsonObj.put("class", "MapReduceItem");
      jsonObj.put("id", id);
      jsonObj.put("score", getScore());
      jsonObj.put("item", getItem());
      jsonObj.put("id", getId());
      if (getHasMapReduceIPIs() != null) {
        JSONArray jsonHasMapReduceIPIs = new JSONArray();
        for (MapReduceInProcessItem row: getHasMapReduceIPIs()) {
          jsonHasMapReduceIPIs.put(row.getId());
        }
        jsonObj.put("hasMapReduceIPIs", jsonHasMapReduceIPIs);
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
      jsonObj.put("class", "MapReduceItem");
      jsonObj.put("id", id);
      if (written.contains(this)) {
        return jsonObj;
      }
      written.put(this, this);
      jsonObj.put("score", getScore());
      jsonObj.put("item", getItem());
      jsonObj.put("id", getId());
      if (getHasMapReduceIPIs() != null) {
        JSONArray jsonHasMapReduceIPIs = new JSONArray();
        for (MapReduceInProcessItem row: getHasMapReduceIPIs()) {
          jsonHasMapReduceIPIs.put(row.asJSONTreeAux(written));
        }
        jsonObj.put("hasMapReduceIPIs", jsonHasMapReduceIPIs);
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
      if (!jsonObj.isNull("score")) {
        setScore(jsonObj.optDouble("score"));
      }
      if (!jsonObj.isNull("item")) {
        setItem(jsonObj.optString("item"));
      }
      JSONArray hasMapReduceIPIsArray = jsonObj.optJSONArray("hasMapReduceIPIs");
      if(hasMapReduceIPIsArray != null) {
        ArrayList<MapReduceInProcessItem> aListOfHasMapReduceIPIs = new ArrayList<MapReduceInProcessItem>(hasMapReduceIPIsArray.length());
        for (int i = 0; i < hasMapReduceIPIsArray.length(); i++) {
          int id = hasMapReduceIPIsArray.optInt(i);
          if (MapReduceInProcessItemManager.getInstance().get(id) != null) {
            aListOfHasMapReduceIPIs.add(MapReduceInProcessItemManager.getInstance().get(id));
          }
        }
        setHasMapReduceIPIs(aListOfHasMapReduceIPIs);
      }
    } catch (Exception e) {
      logWriter.error("Failure updating from JSON", e);
      return false;
    }
    return true;
  }


  public static MapReduceItem newMapReduceItem(String i, double v) {
    MapReduceItem item = new MapReduceItem();
    item.setItem(i);
    item.setId(Integer.parseInt(i));
    item.setScore(v);
    return item;
  }
  
  public boolean ipiInCollector(Collector collector) {
    for (MapReduceInProcessItem mriIp: getHasMapReduceIPIs()) {
      if (mriIp.getHasSink() == collector) {
        return true;
      } 
    }
    return false;
  }

  /**
   * MRI comparator
   */
public static class MRIComparator implements Comparator<MapReduceItem> {
  
    public int compare(MapReduceItem mri1, MapReduceItem mri2) {
      if (mri1 != null && mri2 != null) {
        return mri1.score>mri2.score ? -1: (mri1.score==mri2.score) ? 0 : 1;
      } else if (mri1 != null) {
        return 1;
      } else if (mri2 != null) {
        return -1;
      } else {
        return 0;
      }
    }
  };
  


  
  @Override
  public String toString() {
    return this.getItem()+":"+this.getScore();
  }
  
  public MapReduceItem(String i, double v) {
    this.setItem(i);
    this.setId(Integer.parseInt(i));
    this.setScore(v);
  }

}
