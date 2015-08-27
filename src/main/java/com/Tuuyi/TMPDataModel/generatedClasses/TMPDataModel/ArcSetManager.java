package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ArcSetManager extends DomainConceptManager<ArcSet> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final ArcSetManager instance = new ArcSetManager();

  public ArcSetManager() {
    super();
    managedClass = ArcSet.class;
    this.NULLVALUE = new ArcSet(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
  }

  public static ArcSetManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static ArcSet getArcSet(int id) {
    return instance.get(id);
  }
}
