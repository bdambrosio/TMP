package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TMPClassManager extends DomainConceptManager<TMPClass> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final TMPClassManager instance = new TMPClassManager();

  public TMPClassManager() {
    super();
    managedClass = TMPClass.class;
    this.NULLVALUE = new TMPClass(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
  }

  public static TMPClassManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static TMPClass getTMPClass(int id) {
    return instance.get(id);
  }
}
