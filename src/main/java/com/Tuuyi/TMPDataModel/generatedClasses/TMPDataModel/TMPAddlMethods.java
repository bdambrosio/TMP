package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TMPAddlMethods extends GraphTMP {
  
  public Query makeQuery(NodeList targets) {
    Query q = new Query();
    q.setHasModel((GraphTMP)this);
    return q;
  }
  
  public void evalQuery(Query q){};
  
  public Node getNode(String name) {
    for (int n = 0; n < getHasDomainNodes().size(); n++) {
      Node node =  getHasDomainNodes().get(n);
      if (node.getHasName().equalsIgnoreCase(name)) {
        return node;
      }
    }
    return null;
  }

}
