package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class HeapPriorityModelManager extends DomainConceptManager<HeapPriorityModel> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final HeapPriorityModelManager instance = new HeapPriorityModelManager();

  public HeapPriorityModelManager() {
    super();
    managedClass = HeapPriorityModel.class;
    this.NULLVALUE = new HeapPriorityModel(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static HeapPriorityModelManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static HeapPriorityModel getHeapPriorityModel(int id) {
    return instance.get(id);
  }
}
