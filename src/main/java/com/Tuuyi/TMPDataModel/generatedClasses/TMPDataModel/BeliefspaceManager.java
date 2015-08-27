package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class BeliefspaceManager extends DomainConceptManager<Beliefspace> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final BeliefspaceManager instance = new BeliefspaceManager();

  public BeliefspaceManager() {
    super();
    managedClass = Beliefspace.class;
    this.NULLVALUE = new Beliefspace(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static BeliefspaceManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static Beliefspace getBeliefspace(int id) {
    return instance.get(id);
  }
}
