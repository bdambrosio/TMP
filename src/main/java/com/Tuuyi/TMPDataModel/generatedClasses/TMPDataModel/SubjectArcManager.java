package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class SubjectArcManager extends DomainConceptManager<SubjectArc> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final SubjectArcManager instance = new SubjectArcManager();

  public SubjectArcManager() {
    super();
    managedClass = SubjectArc.class;
    this.NULLVALUE = new SubjectArc(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static SubjectArcManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static SubjectArc getSubjectArc(int id) {
    return instance.get(id);
  }
}
