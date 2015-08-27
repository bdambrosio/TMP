package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class HyperArcManager extends DomainConceptManager<HyperArc> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final HyperArcManager instance = new HyperArcManager();

  public HyperArcManager() {
    super();
    managedClass = HyperArc.class;
    this.NULLVALUE = new HyperArc(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static HyperArcManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static HyperArc getHyperArc(int id) {
    return instance.get(id);
  }
}
