package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class MappingContextManager extends DomainConceptManager<MappingContext> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final MappingContextManager instance = new MappingContextManager();

  public MappingContextManager() {
    super();
    managedClass = MappingContext.class;
    this.NULLVALUE = new MappingContext(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static MappingContextManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static MappingContext getMappingContext(int id) {
    return instance.get(id);
  }
}
