/* generated by writeJava methods in Workspace */
package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import com.Tuuyi.TDM.*;
import org.apache.log4j.Logger;
import java.util.*;
import java.lang.reflect.Method;
import org.json.*;
import java.util.concurrent.ConcurrentHashMap;

public class SampleTerm extends DomainConcept {

  protected static final Logger logWriter = Logger.getLogger(SampleTerm.class.getName());
  private static final boolean persistant = false;

  public void inMemoryOnly(boolean local) {inMemoryOnly = local;}
  public boolean getInMemoryOnly() {return inMemoryOnly;}
  public boolean isPersistant() {return persistant;}

  public static boolean isPersistantClass() {return persistant;}

  protected ScoredTerm hasScoredTerm = null;

  int [] sampleSequence;
  int nextIndex;
  private ArrayList<ItemProjection> hasItems = new ArrayList<ItemProjection>();
  double relevance;
  int startIndex;

  /** if no arg, assume from db **/
  public SampleTerm() {
    this(true);
  }
  /** if from DB, set nonFunctional slot wrappers to stub **/
  /**    so subsequent slot get will do retrieval         **/
  public SampleTerm(boolean fromDB) {
    super(Workspace.getCurrentWorkspace(), fromDB);
    hasItems= new ArrayList<ItemProjection>(0);
  }
  public SampleTerm(Workspace workspace, boolean fromDB) {
    super(workspace, fromDB);
    hasItems= new ArrayList<ItemProjection>(0);
  }
  public boolean HasScoredTermIsResident() {
      return true;
  }
  public ScoredTerm getHasScoredTerm() {

    return hasScoredTerm;
  }
  public int getHasScoredTermInternalId() {
    if (hasScoredTerm == null) {
      return -1;
    } else { 
      return hasScoredTerm.getId();
    }
  }
  public int [] getSampleSequence() {
    return sampleSequence;
  }
  public int getNextIndex() {
    return nextIndex;
  }
  public List<ItemProjection> getHasItems() {
      return hasItems;
  }
  public double getRelevance() {
    return relevance;
  }
  public int getStartIndex() {
    return startIndex;
  }

  public void setHasScoredTerm(ScoredTerm newHasScoredTerm) {
    hasScoredTerm = newHasScoredTerm;
  }
  public void setSampleSequence (int [] a_sampleSequence) {
    sampleSequence = a_sampleSequence;
  }
  public void setNextIndex (int a_nextIndex) {
    nextIndex = a_nextIndex;
  }

  /** note that this method does not check for duplicates, which may cause a DB error */
  public void setHasItems(Collection<ItemProjection> newHasItems) {
    hasItems=new ArrayList<ItemProjection>(newHasItems);
  }

  public void add1HasItems(ItemProjection newHasItems) {
    hasItems.add(newHasItems);
  }

  /** note that this method does not check for duplicates, which may cause a DB error */
  public void addAllHasItems(Collection<ItemProjection> newHasItems) {
    hasItems.addAll(newHasItems);
  }

  public boolean remove1HasItems(ItemProjection newHasItems) {
    return hasItems.remove(newHasItems);
  }
  public void setRelevance (double a_relevance) {
    relevance = a_relevance;
  }
  public void setStartIndex (int a_startIndex) {
    startIndex = a_startIndex;
  }

  /* to support remove operation on collections, java objs are equal if id match */
  public boolean equals(Object o) {
    if (!(o instanceof SampleTerm)) //covers o == null case
      return false;
    SampleTerm other = (SampleTerm)o;
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
      jsonObj.put("class", "SampleTerm");
      jsonObj.put("id", id);
      if (getHasScoredTerm() != null) {
        jsonObj.put("hasScoredTerm", getHasScoredTerm().getId());
      }
      if (getSampleSequence() != null) {
        JSONArray sampleSequenceArray = new JSONArray();
        for (int  item : getSampleSequence()) {
          sampleSequenceArray.put(item);
        }
        jsonObj.put("sampleSequence", sampleSequenceArray);
      }
      jsonObj.put("nextIndex", getNextIndex());
      if (getHasItems() != null) {
        JSONArray jsonHasItems = new JSONArray();
        for (ItemProjection row: getHasItems()) {
          jsonHasItems.put(row.getId());
        }
        jsonObj.put("hasItems", jsonHasItems);
      }
      jsonObj.put("relevance", getRelevance());
      jsonObj.put("startIndex", getStartIndex());
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
      jsonObj.put("class", "SampleTerm");
      jsonObj.put("id", id);
      if (written.contains(this)) {
        return jsonObj;
      }
      written.put(this, this);
      if (getHasScoredTerm() != null) {
        jsonObj.put("hasScoredTerm", getHasScoredTerm().asJSONTreeAux(written));
      }
      if (getSampleSequence() != null) {
        JSONArray sampleSequenceArray = new JSONArray();
        for (int  item : getSampleSequence()) {
          sampleSequenceArray.put(item);
        }
        jsonObj.put("sampleSequence", sampleSequenceArray);
      }
      jsonObj.put("nextIndex", getNextIndex());
      if (getHasItems() != null) {
        JSONArray jsonHasItems = new JSONArray();
        for (ItemProjection row: getHasItems()) {
          jsonHasItems.put(row.asJSONTreeAux(written));
        }
        jsonObj.put("hasItems", jsonHasItems);
      }
      jsonObj.put("relevance", getRelevance());
      jsonObj.put("startIndex", getStartIndex());
      written.remove(this);
    } catch (Exception e1) {
      logWriter.error("Error in marshalling to JSON ", e1);
    }
    return jsonObj;
  }


/** method to update data in caching layer object from JSON **/
  public boolean updateFromJSON (JSONObject jsonObj) {
    try {
      if (!jsonObj.isNull("hasScoredTerm")) {
        int hasScoredTermId = jsonObj.optInt("hasScoredTerm");
        ScoredTerm value = ScoredTermManager.getInstance().get(hasScoredTermId);
        if(value != null) {
            setHasScoredTerm(value);
        }
      }
      JSONArray sampleSequenceArray = jsonObj.optJSONArray("sampleSequence");
      if (sampleSequenceArray != null) {
        int[] aSampleSequence = new int[sampleSequenceArray.length()];
        for (int i = 0; i < sampleSequenceArray.length(); i++) {
          aSampleSequence[i] = sampleSequenceArray.getInt(i);
        }
        setSampleSequence(aSampleSequence);
      }
      if (!jsonObj.isNull("nextIndex")) {
        setNextIndex(jsonObj.optInt("nextIndex"));
      }
      JSONArray hasItemsArray = jsonObj.optJSONArray("hasItems");
      if(hasItemsArray != null) {
        ArrayList<ItemProjection> aListOfHasItems = new ArrayList<ItemProjection>(hasItemsArray.length());
        for (int i = 0; i < hasItemsArray.length(); i++) {
          int id = hasItemsArray.optInt(i);
          if (ItemProjectionManager.getInstance().get(id) != null) {
            aListOfHasItems.add(ItemProjectionManager.getInstance().get(id));
          }
        }
        setHasItems(aListOfHasItems);
      }
      if (!jsonObj.isNull("relevance")) {
        setRelevance(jsonObj.optDouble("relevance"));
      }
      if (!jsonObj.isNull("startIndex")) {
        setStartIndex(jsonObj.optInt("startIndex"));
      }
    } catch (Exception e) {
      logWriter.error("Failure updating from JSON", e);
      return false;
    }
    return true;
  }

 public SampleTerm(ScoredTerm term, double score) {
   this.setRelevance(score);
   this.setHasScoredTerm(term);
   this.setHasItems(term.getForTerm().getHasItems());
   this.initIndex();
  }
  
 boolean more = true;
  
  int initIndex() {
    setSampleSequence(Projection.getSampleOrder(getHasItems().size()));
    startIndex = (int)Math.floor((Math.random()*(sampleSequence.length-1)));
    while (sampleSequence[startIndex] >= getHasItems().size()) {
      startIndex = (startIndex+1) % getSampleSequence().length;
    }
    nextIndex = startIndex;
    if (getHasItems().size() > 0) {
      more = true;
    } else {
      more = false;
    }
    return startIndex;
  }
  
  /** returns -1 when exhausted **/
  
  protected int incrIndex() {
    if (!more) {
      return -1;
    }
    nextIndex = (nextIndex+1) % getSampleSequence().length;
    while (nextIndex != startIndex && sampleSequence[nextIndex] >= getHasItems().size() ) {
      nextIndex = (nextIndex+1) % getSampleSequence().length;
      if (nextIndex == startIndex ) {
        more = false;
        return -1;
      }
    }
    if (nextIndex == startIndex ) {
      more = false;
      return -1;
    }
    return nextIndex;
  }
  
  public boolean hasMore() {
    return more;
  }
  
  public ItemProjection sample () {
    ItemProjection next = null;
    int sampleIndex = sampleSequence[nextIndex];
    next = getHasItems().get(sampleIndex);
    incrIndex();
    if (next == null) {
      logWriter.error("shouldn't happen");
    }
    return next;
  }


}
