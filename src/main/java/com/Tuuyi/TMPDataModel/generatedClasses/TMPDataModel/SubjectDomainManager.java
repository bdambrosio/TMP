package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class SubjectDomainManager extends DomainConceptManager<SubjectDomain> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final SubjectDomainManager instance = new SubjectDomainManager();

  public SubjectDomainManager() {
    super();
    managedClass = SubjectDomain.class;
    this.NULLVALUE = new SubjectDomain(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static SubjectDomainManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static SubjectDomain getSubjectDomain(int id) {
    return instance.get(id);
  }
}
