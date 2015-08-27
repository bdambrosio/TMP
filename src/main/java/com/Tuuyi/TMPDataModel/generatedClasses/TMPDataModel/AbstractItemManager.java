package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class AbstractItemManager extends DomainConceptManager<AbstractItem> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final AbstractItemManager instance = new AbstractItemManager();

  public AbstractItemManager() {
    super();
    managedClass = AbstractItem.class;
    this.NULLVALUE = new AbstractItem(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static AbstractItemManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static AbstractItem getAbstractItem(int id) {
    return instance.get(id);
  }
}
