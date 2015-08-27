package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import java.util.ArrayList;
import java.util.List;
//import
import java.util.HashSet;

public class NodeListAddlMethods extends NodeList {

//DL  public NodeList(NodeList old) {
//DL    hasNodes = new ArrayList<Node>(old.hasNodes);
//DL  }
  
//DL  public NodeList(Node [] nodes) {
//DL    for (Node n: nodes) {
//DL      addNode(n);
//DL    }
//DL  }
  
//DL  public NodeList(ArrayList<Node>nodes) {
//DL    hasNodes = nodes;
//DL  }
  
//DL  public NodeList(Node node) {
//DL    hasNodes.add(node);
//DL  }
  
  public Node get(int index) {
    return getHasNodes().get(index);
  }
  
  public boolean member(Node target) {
    return getHasNodes().contains(target);
  }
  
  public NodeList addNode(Node n) {
    ArrayList <Node> nodes = new ArrayList<Node>(getHasNodes());
    // search for insertion location
    int index = 0;
    int size = nodes.size();
    while (index < size && nodes.get(index).getOrder() < n.getOrder()) {
      index++;
    }
    // test if already in list
    if (index < size && nodes.get(index).getOrder() == n.getOrder()) {
      return this;
    } else if (index == size) {
      // add to end if last element
      nodes.add(n);
      this.setHasNodes(nodes);
      return this;
    } else { // copy tail to make room
      int lastIndex = nodes.size()-1;
      nodes.add(nodes.get(lastIndex));
      for (int copyIndex = lastIndex; copyIndex > index; copyIndex--) {
        nodes.set(copyIndex, nodes.get(copyIndex-1));
      }
      nodes.set(index, n);
      }
    this.setHasNodes(nodes);
    return this;
  }

  /**
   * note - destructive, modifies in place
   */
  public NodeList removeNode(Node n) {
    getHasNodes().remove(n);
    return this;
  }

//DL protected static HashSet<Node> unionSet = new HashSet<Node>();  
//DL  public synchronized NodeList union(NodeList op2) {
//DL    unionSet.clear();
//DL    for (int i = 0; i < this.size(); i++) {
//DL      unionSet.add(this.get(i));
//DL    }
//DL    NodeList result = new NodeList(this);
//DL    for (Node n: op2.getHasNodes()) {
//DL      if (!unionSet.contains(n)) {
//DL        result.addNode(n);
//DL      }
//DL    }
//DL    return result;
//DL  }

//DL  public NodeList intersection(NodeList op2) {
//DL    return this.union(op2).setDifference(this.setDifference(op2).union(op2.setDifference(this)));
//DL  }

  
//DL  public NodeList setDifference(NodeList op2) {
//DL    NodeList result =  new NodeList(this);
//DL    for (Node n: op2.getHasNodes()) {
//DL      result.remove1HasNodes(n);
//DL    }
//DL    return result;
//DL  }

//DL  public boolean intersects (NodeList op2) {
//DL    for (Node n: hasNodes) {
//DL      if (op2.hasNodes.contains(n)) {
//DL        return true;  
//DL      }
//DL    }
//DL    return false;
//DL  }
  
  public int size() {
    return getHasNodes().size();
  }
  
  public int distributionSize() {
    int dSize = 1;
    for (Node n: getHasNodes()) {
      dSize *= n.getDomain().length;
    }
    return dSize;
  }
  
  public int [] makeSteps (NodeList targets) {
    int [] steps = new int [targets.size()];
    int step = 1;
    for (int nIndx = targets.size()-1; nIndx >= 0; nIndx--) {
      Node target = targets.get(nIndx);
      if (target.getEvidence() == -1 && member(target)) {
        steps[nIndx] = step;
      } else {
        steps[nIndx] = 0;
      }
      if (member(target)) {
        step *= target.getDomain().length;
      }
    }
    return steps;
  }
    
  public int  computeOffset (NodeList targets) {
    int  offset = 0;
    int step = 1;
    int index = 1;
    for (int nIndx = targets.size()-1; nIndx >= 0; nIndx--) {
      Node target = targets.get(nIndx);
      if (member(target)) {
        if (target.getEvidence() == -1) {
          offset += 0;
        } else {
          offset+= step * target.getEvidence();
        }
        step *= target.getDomain().length;
      }
    }
    return offset;
  }
  
//DL static HashMap <NodeList, int [][]> rowCaseCache = new HashMap <NodeList, int [][]>();
  // nice try, but slower than just making new every time.
  protected int [][] getRowCases () {
    int [][] rowCases = rowCaseCache.get(this);
    if (rowCases == null) {
      rowCases = makeRowCases();
      rowCaseCache.put(this, rowCases);
    }
    return rowCases;
  }
  
   public int [][] makeRowCases() {
    int [][] cases = new int [distributionSize()][size()];
    for (int i = 0; i < distributionSize(); i++) {
      cases [i] = new int [size()];
    }
    int span = distributionSize();
    for (int i = 0; i < size(); i++) {
      span /= getHasNodes().get(i).getDomain().length; //first var is most slowly varying
      initializeRowCases(cases, i, span);
    }
    return cases;
  }

  protected void initializeRowCases(int [] [] cases, int nodeIndex, int span) {
    String [] domain = getHasNodes().get(nodeIndex).getDomain();
    int domainSize = domain.length;
    int subscript = 0;
    for (int caseIndex = 0; caseIndex < cases.length; caseIndex++) {
      if (caseIndex != 0 && caseIndex % span == 0) {
        subscript = (subscript+1) % domainSize;
      }
      cases [caseIndex] [nodeIndex] = subscript;
    }
  }
  
  /** 
   * @param n2 - the list to compare
   * @return a boolean - true if the two lists contain identical nodes
   * takes advantage of the fact that NodeList maintains an ordered set of nodes
   */
//DL  public boolean matches (NodeList n2) {
//DL    if (this == null || n2 == null) {
//DL      return false;
//DL    }
//DL    if (this.size() != n2.size()) {
//DL      return false;
//DL    }
//DL    for (int i = 0; i < this.hasNodes.size(); i++) {
//DL      if (this.hasNodes.get(i) != n2.hasNodes.get(i)) {
//DL        return false;
//DL      }
//DL    }
//DL    return true;
//DL  }
  
//DL  public String toString() {
//DL    StringBuffer buf = new StringBuffer();
//DL    buf.append("[");
//DL    for (Node node: hasNodes) {
//DL      buf.append(node.toString());
//DL      buf.append(",");
//DL    }
//DL    return buf.toString();
//DL  }

//DL  public boolean equals(Object o) {
//DL    if (!(o instanceof NodeList ) ) {
//DL    return false;
//DL    }
//DL    NodeList other = (NodeList) o;
//DL    if (this == other) {
//DL      return true;
//DL    } else {
//DL      if (size() == other.size()) {
//DL        for (int i = 0; i < size(); i++) {
//DL          if (hasNodes.get(i) != other.hasNodes.get(i)) {
//DL            return false;
//DL          }
//DL        }
//DL        return true;
//DL      }
//DL    return false;
//DL    }
//DL  }

//DL  public int hashCode() {
//DL    int code = 0;
//DL    int index = 0;
//DL    for (Node n: hasNodes) {
//DL      code += index*n.getOrder();
//DL      index *= 4;
//DL    }
//DL    return code;
//DL  }
}
