package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScoredTokenManager extends DomainConceptManager<ScoredToken> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final ScoredTokenManager instance = new ScoredTokenManager();

  public ScoredTokenManager() {
    super();
    managedClass = ScoredToken.class;
    this.NULLVALUE = new ScoredToken(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static ScoredTokenManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static ScoredToken getScoredToken(int id) {
    return instance.get(id);
  }
}
