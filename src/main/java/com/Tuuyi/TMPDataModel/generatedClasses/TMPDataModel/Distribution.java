/* generated by writeJava methods in Workspace */
package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import com.Tuuyi.TDM.*;
import org.apache.log4j.Logger;
import java.util.*;
import java.lang.reflect.Method;
import org.json.*;
import java.util.concurrent.ConcurrentHashMap;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Distribution extends DomainConcept {

  protected static final Logger logWriter = Logger.getLogger(Distribution.class.getName());
  private static final boolean persistant = false;

  public void inMemoryOnly(boolean local) {inMemoryOnly = local;}
  public boolean getInMemoryOnly() {return inMemoryOnly;}
  public boolean isPersistant() {return persistant;}

  public static boolean isPersistantClass() {return persistant;}

  double [] hasDependenceModel;
  protected PropertySet hasPropertySet = null;

  protected NodeList hasRangeNodes = null;

  private ArrayList<DistributionRow> hasDistributionRows = new ArrayList<DistributionRow>();
  protected NodeList hasDomainNodes = null;


  /** if no arg, assume from db **/
  public Distribution() {
    this(true);
  }
  /** if from DB, set nonFunctional slot wrappers to stub **/
  /**    so subsequent slot get will do retrieval         **/
  public Distribution(boolean fromDB) {
    super(Workspace.getCurrentWorkspace(), fromDB);
    hasDistributionRows= new ArrayList<DistributionRow>(0);
  }
  public Distribution(Workspace workspace, boolean fromDB) {
    super(workspace, fromDB);
    hasDistributionRows= new ArrayList<DistributionRow>(0);
  }
  public int getId() {
    return id;
  }
  public double [] getHasDependenceModel() {
    return hasDependenceModel;
  }
  public boolean HasPropertySetIsResident() {
      return true;
  }
  public PropertySet getHasPropertySet() {

    return hasPropertySet;
  }
  public int getHasPropertySetInternalId() {
    if (hasPropertySet == null) {
      return -1;
    } else { 
      return hasPropertySet.getId();
    }
  }
  public boolean HasRangeNodesIsResident() {
      return true;
  }
  public NodeList getHasRangeNodes() {

    return hasRangeNodes;
  }
  public int getHasRangeNodesInternalId() {
    if (hasRangeNodes == null) {
      return -1;
    } else { 
      return hasRangeNodes.getId();
    }
  }
  public List<DistributionRow> getHasDistributionRows() {
      return hasDistributionRows;
  }
  public boolean HasDomainNodesIsResident() {
      return true;
  }
  public NodeList getHasDomainNodes() {

    return hasDomainNodes;
  }
  public int getHasDomainNodesInternalId() {
    if (hasDomainNodes == null) {
      return -1;
    } else { 
      return hasDomainNodes.getId();
    }
  }
  public void setId (int a_id) {
      if (a_id> -1) {
        id = a_id;
        DistributionManager.getInstance().putInCache(this);
      }
  }
  public void setHasDependenceModel (double [] a_hasDependenceModel) {
    hasDependenceModel = a_hasDependenceModel;
  }

  public void setHasPropertySet(PropertySet newHasPropertySet) {
    hasPropertySet = newHasPropertySet;
  }

  public void setHasRangeNodes(NodeList newHasRangeNodes) {
    hasRangeNodes = newHasRangeNodes;
  }

  /** note that this method does not check for duplicates, which may cause a DB error */
  public void setHasDistributionRows(Collection<DistributionRow> newHasDistributionRows) {
    hasDistributionRows=new ArrayList<DistributionRow>(newHasDistributionRows);
  }

  public void add1HasDistributionRows(DistributionRow newHasDistributionRows) {
    hasDistributionRows.add(newHasDistributionRows);
  }

  /** note that this method does not check for duplicates, which may cause a DB error */
  public void addAllHasDistributionRows(Collection<DistributionRow> newHasDistributionRows) {
    hasDistributionRows.addAll(newHasDistributionRows);
  }

  public boolean remove1HasDistributionRows(DistributionRow newHasDistributionRows) {
    return hasDistributionRows.remove(newHasDistributionRows);
  }

  public void setHasDomainNodes(NodeList newHasDomainNodes) {
    hasDomainNodes = newHasDomainNodes;
  }

  /* to support remove operation on collections, java objs are equal if id match */
  public boolean equals(Object o) {
    if (!(o instanceof Distribution)) //covers o == null case
      return false;
    Distribution other = (Distribution)o;
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
      jsonObj.put("class", "Distribution");
      jsonObj.put("id", id);
      jsonObj.put("id", getId());
      if (getHasDependenceModel() != null) {
        JSONArray hasDependenceModelArray = new JSONArray();
        for (double  item : getHasDependenceModel()) {
          hasDependenceModelArray.put(item);
        }
        jsonObj.put("hasDependenceModel", hasDependenceModelArray);
      }
      if (getHasPropertySet() != null) {
        jsonObj.put("hasPropertySet", getHasPropertySet().getId());
      }
      if (getHasRangeNodes() != null) {
        jsonObj.put("hasRangeNodes", getHasRangeNodes().getId());
      }
      if (getHasDistributionRows() != null) {
        JSONArray jsonHasDistributionRows = new JSONArray();
        for (DistributionRow row: getHasDistributionRows()) {
          jsonHasDistributionRows.put(row.getId());
        }
        jsonObj.put("hasDistributionRows", jsonHasDistributionRows);
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
      jsonObj.put("class", "Distribution");
      jsonObj.put("id", id);
      if (written.contains(this)) {
        return jsonObj;
      }
      written.put(this, this);
      jsonObj.put("id", getId());
      if (getHasDependenceModel() != null) {
        JSONArray hasDependenceModelArray = new JSONArray();
        for (double  item : getHasDependenceModel()) {
          hasDependenceModelArray.put(item);
        }
        jsonObj.put("hasDependenceModel", hasDependenceModelArray);
      }
      if (getHasPropertySet() != null) {
        jsonObj.put("hasPropertySet", getHasPropertySet().asJSONTreeAux(written));
      }
      if (getHasRangeNodes() != null) {
        jsonObj.put("hasRangeNodes", getHasRangeNodes().asJSONTreeAux(written));
      }
      if (getHasDistributionRows() != null) {
        JSONArray jsonHasDistributionRows = new JSONArray();
        for (DistributionRow row: getHasDistributionRows()) {
          jsonHasDistributionRows.put(row.asJSONTreeAux(written));
        }
        jsonObj.put("hasDistributionRows", jsonHasDistributionRows);
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
      JSONArray hasDependenceModelArray = jsonObj.optJSONArray("hasDependenceModel");
      if (hasDependenceModelArray != null) {
        double[] aHasDependenceModel = new double[hasDependenceModelArray.length()];
        for (int i = 0; i < hasDependenceModelArray.length(); i++) {
          aHasDependenceModel[i] = hasDependenceModelArray.getDouble(i);
        }
        setHasDependenceModel(aHasDependenceModel);
      }
      if (!jsonObj.isNull("hasPropertySet")) {
        int hasPropertySetId = jsonObj.optInt("hasPropertySet");
        PropertySet value = PropertySetManager.getInstance().get(hasPropertySetId);
        if(value != null) {
            setHasPropertySet(value);
        }
      }
      if (!jsonObj.isNull("hasRangeNodes")) {
        int hasRangeNodesId = jsonObj.optInt("hasRangeNodes");
        NodeList value = NodeListManager.getInstance().get(hasRangeNodesId);
        if(value != null) {
            setHasRangeNodes(value);
        }
      }
      JSONArray hasDistributionRowsArray = jsonObj.optJSONArray("hasDistributionRows");
      if(hasDistributionRowsArray != null) {
        ArrayList<DistributionRow> aListOfHasDistributionRows = new ArrayList<DistributionRow>(hasDistributionRowsArray.length());
        for (int i = 0; i < hasDistributionRowsArray.length(); i++) {
          int id = hasDistributionRowsArray.optInt(i);
          if (DistributionRowManager.getInstance().get(id) != null) {
            aListOfHasDistributionRows.add(DistributionRowManager.getInstance().get(id));
          }
        }
        setHasDistributionRows(aListOfHasDistributionRows);
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

  
 protected NodeList allNodes = null;
  
  public static Distribution newDistribution (NodeList aDomainNodeList, NodeList aRangeNodeList) {
    Distribution result = new Distribution();
    result.setHasDomainNodes(aDomainNodeList);
    result.setHasRangeNodes(aRangeNodeList);
    result.setHasDistributionRows(result.makeRows());
    return result;
  }
  
  public NodeList getAllNodes () {
    if (allNodes != null) {
      return allNodes;
    } else {
      allNodes = getHasDomainNodes().union(getHasRangeNodes());
      return allNodes;
    }
  }
  
  public boolean allNodesMatch(NodeList targetNodes) {
    if (targetNodes == null || targetNodes.size() != getAllNodes().size()) {
      return false;
    }
    for (int n = 0; n < targetNodes.size(); n++) {
      // note we called getAllNodes above, so allNodes must be filled
      if (allNodes.get(n) != targetNodes.get(n)) {
        return false;
      }
    }
    return true;
  }
  public void addDomainNode (Node n) {
    getHasDomainNodes().addNode(n);
  }
  
  public void addRangeNode (Node n) {
    getHasRangeNodes().addNode(n);
  }
  
  protected ArrayList<DistributionRow> makeRows() {
    NodeList range = getHasRangeNodes();
    NodeList domain = getHasDomainNodes();
    int resultDistSize = range.distributionSize();
    ArrayList <DistributionRow> rows = new ArrayList <DistributionRow> (resultDistSize);
    for (int [] rowIndex: domain.makeRowCases()) {
      DistributionRow newRow = new DistributionRow();
      newRow.setIndexVector(rowIndex);
      newRow.setRow(new double [resultDistSize]);
      rows.add(newRow);
    }
    return rows;
  }
  
  public List<DistributionRow> copyRows(Distribution source) {
    List<DistributionRow> myRows = getHasDistributionRows();
    List<DistributionRow> sourceRows = source.getHasDistributionRows();
    if (myRows == null || sourceRows == null || myRows.size() != sourceRows.size() || myRows.get(0).row.length != sourceRows.get(0).row.length) {
      logWriter.error(" row copy mismatch, ignoring");
    }
    for (int rowIndex = 0; rowIndex <  this.getHasDistributionRows().size(); rowIndex++) {
      double [] myRow = myRows.get(rowIndex).row;
      double [] sourceRow = sourceRows.get(rowIndex).row;
      for (int i = 0; i < sourceRow.length; i++) {
        myRow[i] = sourceRow[i];
      }
    }
    return myRows;
  }
  
  public Distribution multiply(Distribution op2, NodeList targetNodes) {
    TwoOpIndexStructure multiplyStructure = makeMultiplyIndexStructure(op2, targetNodes);
    doMultiply(multiplyStructure);
    return multiplyStructure.getHasResult();
  }
  
  public TwoOpIndexStructure makeMultiplyIndexStructure(Distribution op2, NodeList targetNodes) {
    NodeList allNodes = getHasDomainNodes().union(getHasRangeNodes()).union(op2.getHasDomainNodes()).union(op2.getHasRangeNodes());
    NodeList cNodes = getHasDomainNodes().setDifference(op2.getHasRangeNodes()).union(op2.getHasDomainNodes().setDifference(getHasRangeNodes()));
    Distribution result = newDistribution(cNodes, allNodes.setDifference(cNodes).intersection(targetNodes));
    // make iteration steps for all three sets of rows

    TwoOpIndexStructure multStruct = new TwoOpIndexStructure();
    multStruct.setDist1(this);
    multStruct.setDist2(op2);
    multStruct.setHasResult(result);
    multStruct.setNodeIndex(0);
    multStruct.setNodes(allNodes);
    multStruct.setOp1ROffset(getHasDomainNodes().computeOffset(allNodes));
    multStruct.setOp1RSteps(getHasDomainNodes().makeSteps(allNodes));
    multStruct.setOp1COffset(getHasRangeNodes().computeOffset(allNodes));
    multStruct.setOp1CSteps(getHasRangeNodes().makeSteps(allNodes));
    multStruct.setOp2ROffset(op2.getHasDomainNodes().computeOffset(allNodes));
    multStruct.setOp2RSteps(op2.getHasDomainNodes().makeSteps(allNodes));
    multStruct.setOp2COffset(op2.getHasRangeNodes().computeOffset(allNodes));
    multStruct.setOp2CSteps(op2.getHasRangeNodes().makeSteps(allNodes));
    multStruct.setResultROffset(result.getHasDomainNodes().computeOffset(allNodes));
    multStruct.setResultRSteps(result.getHasDomainNodes().makeSteps(allNodes));
    multStruct.setResultCOffset(result.getHasRangeNodes().computeOffset(allNodes));
    multStruct.setResultCSteps(result.getHasRangeNodes().makeSteps(allNodes));
    multStruct.setOp1Rows(this.getHasDistributionRows());
    multStruct.setOp2Rows(op2.getHasDistributionRows());
    multStruct.setResultRows(result.getHasDistributionRows());
    multStruct.setCost(allNodes.size() +result.getHasDomainNodes().size()+result.getHasRangeNodes().size());
    multStruct.setCount(Query.COUNT-1);

    return multStruct;
  }
      
  public Distribution doMultiply(TwoOpIndexStructure multStruct) {
 
    if (multStruct.getCount() == Query.COUNT){
      return multStruct.getHasResult();
    }
    for (DistributionRow row: multStruct.getHasResult().getHasDistributionRows()) {
      for (int i = 0; i < row.getRow().length; i++) {
        row.getRow()[i] = 0;
      }
    }

    rowMult(0, multStruct.getNodes().getHasNodes(), multStruct.getOp1Rows(), multStruct.getOp2Rows(), multStruct.getResultRows(), 
        multStruct.getOp1ROffset(), multStruct.getOp1RSteps(), multStruct.getOp1COffset(), multStruct.getOp1CSteps(), 
        multStruct.getOp2ROffset(), multStruct.getOp2RSteps(), multStruct.getOp2COffset(), multStruct.getOp2CSteps(), 
        multStruct.getResultROffset(), multStruct.getResultRSteps(), multStruct.getResultCOffset(), multStruct.getResultCSteps() 
    );
    multStruct.setCount(Query.COUNT);
    return multStruct.getHasResult();
  }
  protected void rowMult(int nodeIndex, List<Node> nodes, List<DistributionRow> op1, List<DistributionRow> op2, List<DistributionRow> res, 
      int op1Rindx, int [] op1Rsteps, int op1Cindx, int [] op1Csteps,
      int op2Rindx, int [] op2Rsteps, int op2Cindx, int [] op2Csteps,
      int resRindx, int [] resRsteps, int resCindx, int [] resCsteps
      ) {
    Node n = nodes.get(nodeIndex);
    int start = 0;
    int limit = n.getDomain().length;
    if (n.getEvidence() != -1) {
      start = n.getEvidence();
      limit = start+1;
      op1Rindx += op1Rsteps[nodeIndex]*start; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      op1Cindx += op1Csteps[nodeIndex]*start; // % op1Cspan[nodeIndex];
      op2Rindx += op2Rsteps[nodeIndex]*start; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      op2Cindx += op2Csteps[nodeIndex]*start; // % op1Cspan[nodeIndex];
      resRindx += resRsteps[nodeIndex]*start; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      resCindx += resCsteps[nodeIndex]*start; // % op1Cspan[nodeIndex];
    }
    //int limit = (n.getEvidence() == -1) ? n.getDomain().length : 1;
    for (int i = start; i < limit; i++) {
      if (nodeIndex == nodes.size()-1) {
        res.get(resRindx).getRow()[resCindx] += op1.get(op1Rindx).getRow()[op1Cindx] * op2.get(op2Rindx).getRow()[op2Cindx] ;// do actual mult
      } else {
        rowMult(nodeIndex+1, nodes, op1, op2, res, op1Rindx, op1Rsteps, op1Cindx, op1Csteps,
            op2Rindx, op2Rsteps, op2Cindx, op2Csteps, 
            resRindx, resRsteps, resCindx, resCsteps);
      }
      op1Rindx += op1Rsteps[nodeIndex]; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      op1Cindx += op1Csteps[nodeIndex]; // % op1Cspan[nodeIndex];
      op2Rindx += op2Rsteps[nodeIndex]; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      op2Cindx += op2Csteps[nodeIndex]; // % op1Cspan[nodeIndex];
      resRindx += resRsteps[nodeIndex]; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      resCindx += resCsteps[nodeIndex]; // % op1Cspan[nodeIndex];
    }
  }
  
  /**
   * Note that add, unlike mult, does NOT zero out result first!
   */
  public Distribution doAdd(TwoOpIndexStructure multStruct) {
    
    if (multStruct.getCount() == Query.COUNT){
      return multStruct.getHasResult();
    }
    //for (DistributionRow row: multStruct.getHasResult().getHasDistributionRows()) {
    //  for (int i = 0; i < row.getRow().length; i++) {
    //    row.getRow()[i] = 0;
    //  }
    //}

    rowAdd(0, multStruct.getNodes().getHasNodes(), multStruct.getOp1Rows(), multStruct.getOp2Rows(), multStruct.getResultRows(), 
        multStruct.getOp1ROffset(), multStruct.getOp1RSteps(), multStruct.getOp1COffset(), multStruct.getOp1CSteps(), 
        multStruct.getOp2ROffset(), multStruct.getOp2RSteps(), multStruct.getOp2COffset(), multStruct.getOp2CSteps(), 
        multStruct.getResultROffset(), multStruct.getResultRSteps(), multStruct.getResultCOffset(), multStruct.getResultCSteps() 
    );
    multStruct.setCount(Query.COUNT);
    return multStruct.getHasResult();
  }
  protected void rowAdd(int nodeIndex, List<Node> nodes, List<DistributionRow> op1, List<DistributionRow> op2, List<DistributionRow> res, 
      int op1Rindx, int [] op1Rsteps, int op1Cindx, int [] op1Csteps,
      int op2Rindx, int [] op2Rsteps, int op2Cindx, int [] op2Csteps,
      int resRindx, int [] resRsteps, int resCindx, int [] resCsteps
      ) {
    Node n = nodes.get(nodeIndex);
    int start = 0;
    int limit = n.getDomain().length;
    if (n.getEvidence() != -1) {
      start = n.getEvidence();
      limit = start+1;
      op1Rindx += op1Rsteps[nodeIndex]*start; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      op1Cindx += op1Csteps[nodeIndex]*start; // % op1Cspan[nodeIndex];
      op2Rindx += op2Rsteps[nodeIndex]*start; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      op2Cindx += op2Csteps[nodeIndex]*start; // % op1Cspan[nodeIndex];
      resRindx += resRsteps[nodeIndex]*start; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      resCindx += resCsteps[nodeIndex]*start; // % op1Cspan[nodeIndex];
    }
//    int limit = (n.getEvidence() == -1) ? n.getDomain().length : 1;
    for (int i = start; i < limit; i++) {
      if (nodeIndex == nodes.size()-1) {
        res.get(resRindx).getRow()[resCindx] += op1.get(op1Rindx).getRow()[op1Cindx] + op2.get(op2Rindx).getRow()[op2Cindx] ;// do actual add
      } else {
        rowAdd(nodeIndex+1, nodes, op1, op2, res, op1Rindx, op1Rsteps, op1Cindx, op1Csteps,
            op2Rindx, op2Rsteps, op2Cindx, op2Csteps, 
            resRindx, resRsteps, resCindx, resCsteps);
      }
      op1Rindx += op1Rsteps[nodeIndex]; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      op1Cindx += op1Csteps[nodeIndex]; // % op1Cspan[nodeIndex];
      op2Rindx += op2Rsteps[nodeIndex]; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      op2Cindx += op2Csteps[nodeIndex]; // % op1Cspan[nodeIndex];
      resRindx += resRsteps[nodeIndex]; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      resCindx += resCsteps[nodeIndex]; // % op1Cspan[nodeIndex];
    }
  }
  

  public TwoOpIndexStructure makeDivideIndexStructure(Distribution op2, NodeList targetNodes) {
    NodeList allNodes = getAllNodes().union(op2.getAllNodes());
    NodeList cNodes = getHasDomainNodes().union(op2.getHasRangeNodes());
    Distribution result = newDistribution(cNodes, getHasRangeNodes().setDifference(cNodes).intersection(targetNodes));
    // make iteration steps for all three sets of rows
    /*
    int thisRowIndex = 0, thisColIndex=0, op2RowIndex = 0, op2ColIndex=0, resultRowIndex = 0, resultColIndex=0;
    int [] thisRowSteps, thisColSteps, op2RowSteps, op2ColSteps, resultRowSteps, resultColSteps;
    thisRowSteps = getHasDomainNodes().makeSteps(allNodes);
    int thisRowOffset = getHasDomainNodes().computeOffset(allNodes);
    thisColSteps = getHasRangeNodes().makeSteps(allNodes);
    int thisColOffset = getHasRangeNodes().computeOffset(allNodes);
    op2RowSteps = op2.getHasDomainNodes().makeSteps(allNodes);
    int op2RowOffset = op2.getHasDomainNodes().computeOffset(allNodes);
    op2ColSteps = op2.getHasRangeNodes().makeSteps(allNodes);
    int op2ColOffset = op2.getHasRangeNodes().computeOffset(allNodes);
    resultRowSteps = result.getHasDomainNodes().makeSteps(allNodes);
    int resultRowOffset = result.getHasDomainNodes().computeOffset(allNodes);
    resultColSteps = result.getHasRangeNodes().makeSteps(allNodes);
    int resultColOffset = result.getHasRangeNodes().computeOffset(allNodes);
     */

    TwoOpIndexStructure multStruct = new TwoOpIndexStructure();
    multStruct.setDist1(this);
    multStruct.setDist2(op2);
    multStruct.setHasResult(result);
    multStruct.setNodeIndex(0);
    multStruct.setNodes(allNodes);
    multStruct.setOp1ROffset(getHasDomainNodes().computeOffset(allNodes));
    multStruct.setOp1RSteps(getHasDomainNodes().makeSteps(allNodes));
    multStruct.setOp1COffset(getHasRangeNodes().computeOffset(allNodes));
    multStruct.setOp1CSteps(getHasRangeNodes().makeSteps(allNodes));
    multStruct.setOp2ROffset(op2.getHasDomainNodes().computeOffset(allNodes));
    multStruct.setOp2RSteps(op2.getHasDomainNodes().makeSteps(allNodes));
    multStruct.setOp2COffset(op2.getHasRangeNodes().computeOffset(allNodes));
    multStruct.setOp2CSteps(op2.getHasRangeNodes().makeSteps(allNodes));
    multStruct.setResultROffset(result.getHasDomainNodes().computeOffset(allNodes));
    multStruct.setResultRSteps(result.getHasDomainNodes().makeSteps(allNodes));
    multStruct.setResultCOffset(result.getHasRangeNodes().computeOffset(allNodes));
    multStruct.setResultCSteps(result.getHasRangeNodes().makeSteps(allNodes));
    multStruct.setOp1Rows(this.getHasDistributionRows());
    multStruct.setOp2Rows(op2.getHasDistributionRows());
    multStruct.setResultRows(result.getHasDistributionRows());
    multStruct.setCost(allNodes.size() +result.getHasDomainNodes().size()+result.getHasRangeNodes().size());
    multStruct.setCount(Query.COUNT-1);
    return multStruct;
  }
      

  public Distribution divide(Distribution op2, NodeList targetNodes) {
    TwoOpIndexStructure divideStructure = makeDivideIndexStructure(op2, targetNodes);
    doDivide(divideStructure);
    return divideStructure.getHasResult();
  }
  
  protected void rowDiv(int nodeIndex, List<Node> nodes, List<DistributionRow> op1, List<DistributionRow> op2, List<DistributionRow> res, 
      int op1Rindx, int [] op1Rsteps, int op1Cindx, int [] op1Csteps,
      int op2Rindx, int [] op2Rsteps, int op2Cindx, int [] op2Csteps,
      int resRindx, int [] resRsteps, int resCindx, int [] resCsteps
      ) {
    Node n = nodes.get(nodeIndex);
    int start = 0;
    int limit = n.getDomain().length;
    if (n.getEvidence() != -1) {
      start = n.getEvidence();
      limit = start+1;
      op1Rindx += op1Rsteps[nodeIndex]*start; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      op1Cindx += op1Csteps[nodeIndex]*start; // % op1Cspan[nodeIndex];
      op2Rindx += op2Rsteps[nodeIndex]*start; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      op2Cindx += op2Csteps[nodeIndex]*start; // % op1Cspan[nodeIndex];
      resRindx += resRsteps[nodeIndex]*start; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      resCindx += resCsteps[nodeIndex]*start; // % op1Cspan[nodeIndex];
    }
   for (int i = start; i < limit; i++) {
      if (nodeIndex == nodes.size()-1) {
        res.get(resRindx).getRow()[resCindx] += op1.get(op1Rindx).getRow()[op1Cindx] / op2.get(op2Rindx).getRow()[op2Cindx] ;// do actual mult
      } else {
        rowDiv(nodeIndex+1, nodes, op1, op2, res, op1Rindx, op1Rsteps, op1Cindx, op1Csteps,
            op2Rindx, op2Rsteps, op2Cindx, op2Csteps, 
            resRindx, resRsteps, resCindx, resCsteps);
      }
      op1Rindx += op1Rsteps[nodeIndex]; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      op1Cindx += op1Csteps[nodeIndex]; // % op1Cspan[nodeIndex];
      op2Rindx += op2Rsteps[nodeIndex]; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      op2Cindx += op2Csteps[nodeIndex]; // % op1Cspan[nodeIndex];
      resRindx += resRsteps[nodeIndex]; // % op1Rspan[nodeIndex]; (shouldn't actually be needed, right?)
      resCindx += resCsteps[nodeIndex]; // % op1Cspan[nodeIndex];
    }
  }
  
  public Distribution doDivide(TwoOpIndexStructure multStruct) {

    if (multStruct.getCount() == Query.COUNT){
      return multStruct.getHasResult();
    }

    for (DistributionRow row: multStruct.getHasResult().getHasDistributionRows()) {
      for (int i = 0; i < row.getRow().length; i++) {
        row.getRow()[i] = 0;
      }
    }

    rowDiv(0, multStruct.getNodes().getHasNodes(), multStruct.getOp1Rows(), multStruct.getOp2Rows(), multStruct.getResultRows(), 
        multStruct.getOp1ROffset(), multStruct.getOp1RSteps(), multStruct.getOp1COffset(), multStruct.getOp1CSteps(), 
        multStruct.getOp2ROffset(), multStruct.getOp2RSteps(), multStruct.getOp2COffset(), multStruct.getOp2CSteps(), 
        multStruct.getResultROffset(), multStruct.getResultRSteps(), multStruct.getResultCOffset(), multStruct.getResultCSteps() 
    );
    multStruct.setCount(Query.COUNT);
    return multStruct.getHasResult();
  }

  /** 
   * 
   * @param srm - relevant the graph 
   * @param range - a list of target nodes
   * @param domain - a list of conditioning nodes
   * @return - a new distribution, with no rows
   * 
   * does not invalidate queries
   */
  public static Distribution makeConditionalDistribution(GraphTMP srm, NodeList domain, NodeList range) {
    // build node marginal distribution
    Distribution d = new Distribution();
    d.setHasDomainNodes(domain);
    d.setHasRangeNodes(range);
    d.setHasDistributionRows(new ArrayList<DistributionRow>());
    srm.addDistribution(d);
    for (int n = 0; n < range.size(); n++) {
      Node node = range.get(n);
      node.add1HasDistributions(d);
    }
    return d;
  }
  
  /**
   * @param conditioningCase - an indexVector to search for
   * @return - the distribution row matching the case, or null
   * 
   */

  public DistributionRow getRow(int [] conditioningCase) {
    for (DistributionRow row: getHasDistributionRows()) {
      if (row.caseMatch(conditioningCase)) {
        return row;
      }
    }
    return null;
  }
  
  public void setTradeLikelihood(Node tradeVar, int tradeVarIndex, double [] trade, int [] conditioningCase) {
    if (getHasRangeNodes().size() != 0) {
      logWriter.error ("Distribution is not a likelihood! "+this.toString());
    } else if (getHasDomainNodes().size() != conditioningCase.length) {
      logWriter.error ("Invalid size conditioning case! "+this.toString());
    } else if (!getHasDomainNodes().member(tradeVar)) {
      logWriter.error ("Likelihood missing tradeVar "+tradeVar+" "+this.toString());
    } else if (trade.length != tradeVar.getDomain().length){
      logWriter.error ("trade size != var domain "+tradeVar+" "+trade.length);
    }
    for (int i = 0; i < trade.length; i++) {
      conditioningCase[tradeVarIndex] = i;
      DistributionRow row = getRow(conditioningCase);
      row.row[0] *= trade[i];
    }
  }
  
  public boolean normalize() {
    for (DistributionRow row: getHasDistributionRows()) {
      row.normalize();
    }
    return true;
  }
  
  public boolean zero() {
    for (DistributionRow row: getHasDistributionRows()) {
      row.zero();
    }
    return true;
  }
  

  /**
   * 
   * @param indecies - the set of instance values for each conditioning node
   * @param inputRow - the conditional distribution over the range (joint if appropriate) for the above instantiation
   * @return the input row, quasi-randoomized
   
  public double [] transformRow(int [] indecies, double [] inputRow) {

    double [] row = new double[inputRow.length];
    if (indecies.length < 1) {
      return inputRow;
    }
    for (int i = 0; i < row.length; i++) {
      row[i] = 1;
    }
    double sum = 0.0;
    for (int i = 0; i < indecies.length; i++) {
      for (int j = 0; j < row.length; j++) {
        row[j] *= 1.0/((indecies[i]-j)*(indecies[i]-j)+1.1);
      }
    }
    sum = 0.0;
    for (int i = 0; i < row.length; i++) {
      sum += row[i];
    }
    for (int i = 0; i < row.length; i++) {
      row[i] /= sum;
    }
    return row;
  }
  */
  
  static protected Distribution one = null;
  public static Distribution ONE() {
    if (one != null) {
      return one;
    }
    one = new Distribution();
    one.setHasDomainNodes(new NodeList());
    one.setHasRangeNodes(new NodeList());
    one.setHasDistributionRows(new ArrayList<DistributionRow>());
    DistributionRow dr = new DistributionRow();
    dr.setIndexVector(new int [0]);
    double [] prior = new double[1];
    prior[0] = 1.0;
    dr.setRow(prior);
    one.add1HasDistributionRows(dr);
    return one;
  }
  
  
  
  public Distribution convertConditionalToLikelihood () {
    // now build an empty ONE distribution so we can convert above into conditional to make it easy to find case.
    // this works because divide doesnt normalize, but notice we will get 1/posterior for case!
    Distribution denom = multiply(Distribution.ONE(), getHasRangeNodes());
    for (DistributionRow row: denom.getHasDistributionRows()) {
      for (int i = 0; i < row.row.length; i++ ) {
        row.row[i] = 1.0;
      }
    }
    Distribution result = divide(denom, getAllNodes());
    return result;
  }
  
  public Distribution computeConditional (NodeList newDomain, NodeList newRange) {
    // now build an empty ONE distribution so we can convert above into conditional to make it easy to find case.
    // this works because divide doesnt normalize, but notice we will get 1/posterior for case!
    Distribution denom = multiply(Distribution.ONE(), newDomain);
    denom.normalize();
    Distribution result = divide(denom, newDomain.union(newRange));
    return result;
  }
  
  public long export(BufferedWriter writer, JSONObject json, HashMap <Object, Long> objectIndex) 
      throws IOException, JSONException {
    if (objectIndex.containsKey(this)) {
      return objectIndex.get(this);
    } else {
      long distId = GraphTMP.nextJSONId();
      objectIndex.put(this, distId);
      JSONObject distJSON = asJSON();
      JSONArray jsonDistributionRows = new JSONArray();
      if (getHasDistributionRows() != null) {
        for (DistributionRow row: getHasDistributionRows()) {
          jsonDistributionRows.put(row.export(writer, json, objectIndex));
        }
        distJSON.put("hasDistributionRows", jsonDistributionRows);
      }
      JSONArray jsonDomainNodes = new JSONArray();
      if (getHasDomainNodes() != null) {
        for (int n = 0; n < getHasDomainNodes().size(); n++) {
          Node node =  getHasDomainNodes().get(n);
          jsonDomainNodes.put(node.export(writer, json, objectIndex));
        }
        distJSON.put("hasDomainNodes", jsonDomainNodes);
      }
      JSONArray jsonRangeNodes = new JSONArray();
      if (getHasRangeNodes() != null) {
        for (int n = 0; n < getHasRangeNodes().size(); n++) {
          Node node =  getHasRangeNodes().get(n);
          jsonRangeNodes.put(node.export(writer, json, objectIndex));
        }
        distJSON.put("hasRangeNodes", jsonRangeNodes);
      }
      json.put(""+distId, distJSON);
      return distId;
    }
  }

  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("D(");
    sb.append(getHasRangeNodes().toString());
    sb.append("|");
    sb.append(getHasDomainNodes().toString());
    sb.append(")");
    return sb.toString();
  }
  
}
