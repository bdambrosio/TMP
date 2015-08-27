package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class InstanceManager extends DomainConceptManager<Instance> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final InstanceManager instance = new InstanceManager();

  public InstanceManager() {
    super();
    managedClass = Instance.class;
    this.NULLVALUE = new Instance(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
  }

  public static InstanceManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static Instance getInstance(int id) {
    return instance.get(id);
  }
}
