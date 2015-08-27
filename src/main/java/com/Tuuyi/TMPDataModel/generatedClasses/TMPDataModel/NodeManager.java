package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class NodeManager extends DomainConceptManager<Node> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final NodeManager instance = new NodeManager();

  public NodeManager() {
    super();
    managedClass = Node.class;
    this.NULLVALUE = new Node(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
  }

  public static NodeManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static Node getNode(int id) {
    return instance.get(id);
  }
}
