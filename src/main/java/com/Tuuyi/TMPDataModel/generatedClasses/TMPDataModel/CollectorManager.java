package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CollectorManager extends DomainConceptManager<Collector> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final CollectorManager instance = new CollectorManager();

  public CollectorManager() {
    super();
    managedClass = Collector.class;
    this.NULLVALUE = new Collector(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static CollectorManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static Collector getCollector(int id) {
    return instance.get(id);
  }
}
