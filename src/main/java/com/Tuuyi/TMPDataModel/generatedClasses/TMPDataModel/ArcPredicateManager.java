package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ArcPredicateManager extends DomainConceptManager<ArcPredicate> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final ArcPredicateManager instance = new ArcPredicateManager();

  public ArcPredicateManager() {
    super();
    managedClass = ArcPredicate.class;
    this.NULLVALUE = new ArcPredicate(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static ArcPredicateManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static ArcPredicate getArcPredicate(int id) {
    return instance.get(id);
  }
}
