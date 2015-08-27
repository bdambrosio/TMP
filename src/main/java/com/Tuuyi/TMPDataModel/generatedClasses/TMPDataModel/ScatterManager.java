package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScatterManager extends DomainConceptManager<Scatter> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final ScatterManager instance = new ScatterManager();

  public ScatterManager() {
    super();
    managedClass = Scatter.class;
    this.NULLVALUE = new Scatter(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static ScatterManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static Scatter getScatter(int id) {
    return instance.get(id);
  }
}
