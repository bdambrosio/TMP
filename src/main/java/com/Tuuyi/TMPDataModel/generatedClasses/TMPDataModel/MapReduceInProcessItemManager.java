package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class MapReduceInProcessItemManager extends DomainConceptManager<MapReduceInProcessItem> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final MapReduceInProcessItemManager instance = new MapReduceInProcessItemManager();

  public MapReduceInProcessItemManager() {
    super();
    managedClass = MapReduceInProcessItem.class;
    this.NULLVALUE = new MapReduceInProcessItem(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static MapReduceInProcessItemManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static MapReduceInProcessItem getMapReduceInProcessItem(int id) {
    return instance.get(id);
  }
}
