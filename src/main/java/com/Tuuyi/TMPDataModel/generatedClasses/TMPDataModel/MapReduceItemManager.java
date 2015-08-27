package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class MapReduceItemManager extends DomainConceptManager<MapReduceItem> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final MapReduceItemManager instance = new MapReduceItemManager();

  public MapReduceItemManager() {
    super();
    managedClass = MapReduceItem.class;
    this.NULLVALUE = new MapReduceItem(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static MapReduceItemManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static MapReduceItem getMapReduceItem(int id) {
    return instance.get(id);
  }
}