package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlanManager extends DomainConceptManager<Plan> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final PlanManager instance = new PlanManager();

  public PlanManager() {
    super();
    managedClass = Plan.class;
    this.NULLVALUE = new Plan(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static PlanManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static Plan getPlan(int id) {
    return instance.get(id);
  }
}
