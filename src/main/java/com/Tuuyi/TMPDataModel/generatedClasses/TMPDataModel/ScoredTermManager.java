package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScoredTermManager extends DomainConceptManager<ScoredTerm> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final ScoredTermManager instance = new ScoredTermManager();

  public ScoredTermManager() {
    super();
    managedClass = ScoredTerm.class;
    this.NULLVALUE = new ScoredTerm(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static ScoredTermManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static ScoredTerm getScoredTerm(int id) {
    return instance.get(id);
  }
}
