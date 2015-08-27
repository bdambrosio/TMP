package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

//import
import java.io.BufferedWriter;
//import
import java.io.IOException;
//import
import java.util.ArrayList;
//import
import java.util.HashMap;
//import
import java.util.List;



//import
import org.json.JSONArray;
//import
import org.json.JSONException;
//import
import org.json.JSONObject;

public class DistributionAddlMethods extends Distribution {
  
//DL protected NodeList allNodes = null;
  
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
  
//DL  static protected Distribution one = null;
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
