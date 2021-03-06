package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ItemProjectionManager extends DomainConceptManager<ItemProjection> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final ItemProjectionManager instance = new ItemProjectionManager();

  public ItemProjectionManager() {
    super();
    managedClass = ItemProjection.class;
    this.NULLVALUE = new ItemProjection(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static ItemProjectionManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static ItemProjection getItemProjection(int id) {
    return instance.get(id);
  }
}
