package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProjectionManager extends DomainConceptManager<Projection> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final ProjectionManager instance = new ProjectionManager();

  public ProjectionManager() {
    super();
    managedClass = Projection.class;
    this.NULLVALUE = new Projection(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static ProjectionManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static Projection getProjection(int id) {
    return instance.get(id);
  }
}
