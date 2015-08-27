package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class HierarchicalNodeManager extends DomainConceptManager<HierarchicalNode> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final HierarchicalNodeManager instance = new HierarchicalNodeManager();

  public HierarchicalNodeManager() {
    super();
    managedClass = HierarchicalNode.class;
    this.NULLVALUE = new HierarchicalNode(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
  }

  public static HierarchicalNodeManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static HierarchicalNode getHierarchicalNode(int id) {
    return instance.get(id);
  }
}
