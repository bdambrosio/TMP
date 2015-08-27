package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import java.util.ArrayList;

public class WeightedSubjectArcAddlMethods extends WeightedSubjectArc {

  public String getTailAsString() {
    StringBuffer asString = new StringBuffer(64);
    if (getHasTail() != null) {
      for (int i = 0; i < getHasTail().length; i++) {
        asString.append(getHasTail()[i]);
        if (i < getHasTail().length-1) {
          asString.append(",");
        }
      }
    }
    return asString.toString();
  }
  
  public boolean hasTailElement(String tailElementQuery) {
    if (getHasTail() != null) {
      for (String tailElement: getHasTail()) {
        if (tailElementQuery.equals(tailElement)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public String jointTailString(WeightedSubjectArc arc2) {
    StringBuffer asString = new StringBuffer(getTailAsString());
    for (String tail: arc2.getHasTail()) {
        if(!hasTailElement(tail)) {
          asString.append(",");
          asString.append(tail);
        }
      }
    return asString.toString();
  }

  public String [] jointTail(WeightedSubjectArc arc2) {
    ArrayList<String> jointTailArrayList = new ArrayList<String> ();
    for (String tail: getHasTail()) {
      jointTailArrayList.add(tail);
    }
    
    for (String tail: arc2.getHasTail()) {
      if(!hasTailElement(tail)) {
        jointTailArrayList.add(tail);
      }
    }
    String [] jointTail = new String[jointTailArrayList.size()];
    for (int i = 0; i < jointTailArrayList.size(); i++) {
      jointTail[i] = jointTailArrayList.get(i);
    }
    return jointTail;
  }
}
