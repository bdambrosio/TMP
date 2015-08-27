package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import java.util.Comparator;
import java.util.Date;

public class MapReduceItemAddlMethods extends MapReduceItem {

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
  
//DL  public MapReduceItem(String i, double v) {
//DL    this.setItem(i);
//DL    this.setId(Integer.parseInt(i));
//DL    this.setScore(v);
//DL  }

}
