package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class WeightedSubjectArcManager extends DomainConceptManager<WeightedSubjectArc> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final WeightedSubjectArcManager instance = new WeightedSubjectArcManager();

  public WeightedSubjectArcManager() {
    super();
    managedClass = WeightedSubjectArc.class;
    this.NULLVALUE = new WeightedSubjectArc(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static WeightedSubjectArcManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static WeightedSubjectArc getWeightedSubjectArc(int id) {
    return instance.get(id);
  }
}
