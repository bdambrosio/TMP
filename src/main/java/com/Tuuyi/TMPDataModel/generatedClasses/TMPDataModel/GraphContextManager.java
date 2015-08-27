package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GraphContextManager extends DomainConceptManager<GraphContext> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final GraphContextManager instance = new GraphContextManager();

  public GraphContextManager() {
    super();
    managedClass = GraphContext.class;
    this.NULLVALUE = new GraphContext(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static GraphContextManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static GraphContext getGraphContext(int id) {
    return instance.get(id);
  }
}
