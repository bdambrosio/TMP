package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class NodeListManager extends DomainConceptManager<NodeList> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final NodeListManager instance = new NodeListManager();

  public NodeListManager() {
    super();
    managedClass = NodeList.class;
    this.NULLVALUE = new NodeList(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
  }

  public static NodeListManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static NodeList getNodeList(int id) {
    return instance.get(id);
  }
}
