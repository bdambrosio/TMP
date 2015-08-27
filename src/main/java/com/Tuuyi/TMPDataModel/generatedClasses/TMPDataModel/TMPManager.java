package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TMPManager extends DomainConceptManager<TMP> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final TMPManager instance = new TMPManager();

  public TMPManager() {
    super();
    managedClass = TMP.class;
    this.NULLVALUE = new TMP(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
  }

  public static TMPManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static TMP getTMP(int id) {
    return instance.get(id);
  }
}
