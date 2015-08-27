package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GraphTrainingContextManager extends DomainConceptManager<GraphTrainingContext> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final GraphTrainingContextManager instance = new GraphTrainingContextManager();

  public GraphTrainingContextManager() {
    super();
    managedClass = GraphTrainingContext.class;
    this.NULLVALUE = new GraphTrainingContext(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static GraphTrainingContextManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static GraphTrainingContext getGraphTrainingContext(int id) {
    return instance.get(id);
  }
}
