package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class NodeModelManager extends DomainConceptManager<NodeModel> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final NodeModelManager instance = new NodeModelManager();

  public NodeModelManager() {
    super();
    managedClass = NodeModel.class;
    this.NULLVALUE = new NodeModel(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
  }

  public static NodeModelManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static NodeModel getNodeModel(int id) {
    return instance.get(id);
  }
}
