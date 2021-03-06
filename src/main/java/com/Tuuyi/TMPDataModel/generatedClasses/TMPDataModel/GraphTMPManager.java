package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GraphTMPManager extends DomainConceptManager<GraphTMP> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final GraphTMPManager instance = new GraphTMPManager();

  public GraphTMPManager() {
    super();
    managedClass = GraphTMP.class;
    this.NULLVALUE = new GraphTMP(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static GraphTMPManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static GraphTMP getGraphTMP(int id) {
    return instance.get(id);
  }
}
