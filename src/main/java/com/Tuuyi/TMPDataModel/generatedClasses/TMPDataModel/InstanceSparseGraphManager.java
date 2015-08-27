package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class InstanceSparseGraphManager extends DomainConceptManager<InstanceSparseGraph> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final InstanceSparseGraphManager instance = new InstanceSparseGraphManager();

  public InstanceSparseGraphManager() {
    super();
    managedClass = InstanceSparseGraph.class;
    this.NULLVALUE = new InstanceSparseGraph(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static InstanceSparseGraphManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static InstanceSparseGraph getInstanceSparseGraph(int id) {
    return instance.get(id);
  }
}
