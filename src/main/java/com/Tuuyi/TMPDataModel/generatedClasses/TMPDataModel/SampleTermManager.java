package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class SampleTermManager extends DomainConceptManager<SampleTerm> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final SampleTermManager instance = new SampleTermManager();

  public SampleTermManager() {
    super();
    managedClass = SampleTerm.class;
    this.NULLVALUE = new SampleTerm(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static SampleTermManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static SampleTerm getSampleTerm(int id) {
    return instance.get(id);
  }
}
