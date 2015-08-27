package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

//import
import java.io.BufferedWriter;
//import
import java.io.IOException;
//import
import java.util.HashMap;

//import
import org.json.JSONArray;
//import
import org.json.JSONException;
//import
import org.json.JSONObject;

public class NodeAddlMethods extends Node {

  static int nextNodeNum = 0;

  public static int nextOrder() {
    return nextNodeNum++;
  }
  
  public static Node newNode(String name) {
    Node n = new Node();
    n.setHasName(name);
    n.setOrder(nextOrder());
    n.setEvidence(-1);
    return n;
    
  }

  public static Node newNode(String a_name, String [] a_domain) {
    Node n = new Node();
    n.setHasName(a_name);
    n.setOrder(nextOrder());
    n.setDomain(a_domain);
    n.setEvidence(-1);
    return n;
    
  }
  
  public Distribution getCachedLambda(Node parent) {
    for (Distribution d_li: getCachedLambdas()) {
      if (d_li.getHasDomainNodes().size() == 1 && d_li.getHasDomainNodes().get(0) == parent) {
        return d_li;
      }
    }
    return null;
  }

  public String toString() {
    return "N"+hasName;
  }
  
  public long export(BufferedWriter writer, JSONObject json, HashMap <Object, Long> objectIndex) 
      throws IOException, JSONException {
    /* Node 1 {"hasName":"Q5","id":"51","order":50,"hasDistributions":"1389043350133325000288,","evidence":-1,"domain":["S0","S1"],"class":"Node"} */
    if (objectIndex.containsKey(this)) {
      return objectIndex.get(this);
    } else {
      long nodeId = GraphTMP.nextJSONId();
      objectIndex.put(this, nodeId);
      JSONObject nodeJSON = asJSON();
      JSONArray jsonDistributions = new JSONArray();
      if (getHasDistributions() != null) {
        for (Distribution dist: getHasDistributions()) {
          jsonDistributions.put(dist.export(writer, json, objectIndex));
        }
        nodeJSON.put("hasDistributions", jsonDistributions);
      }
      JSONArray jsonModel = new JSONArray();
      if (getModel() != null) {
        for (Distribution dist: getModel()) {
          jsonModel.put(dist.export(writer, json, objectIndex));
        }
        nodeJSON.put("model", jsonModel);
        json.put(""+nodeId, nodeJSON);
      }
      return nodeId;
    }
  }

}
