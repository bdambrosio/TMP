package com.Tuuyi.TMPDataModel;

import java.util.Comparator;

import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.MapReduceItem;

public class MRIComparator implements Comparator<MapReduceItem> {

  @Override
  public int compare(MapReduceItem arg0, MapReduceItem arg1) {
    // TODO Auto-generated method stub
    if (arg0.getScore() > arg1.getScore()) {
      return -1;
    } else if (arg0.getScore() < arg1.getScore()) {
      return 1;
    }
    return 0;
  }

}
