package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class BayesNetManager extends DomainConceptManager<BayesNet> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final BayesNetManager instance = new BayesNetManager();

  public BayesNetManager() {
    super();
    managedClass = BayesNet.class;
    this.NULLVALUE = new BayesNet(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static BayesNetManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static BayesNet getBayesNet(int id) {
    return instance.get(id);
  }
}
