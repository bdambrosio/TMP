package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TMPModelManager extends DomainConceptManager<TMPModel> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final TMPModelManager instance = new TMPModelManager();

  public TMPModelManager() {
    super();
    managedClass = TMPModel.class;
    this.NULLVALUE = new TMPModel(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static TMPModelManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static TMPModel getTMPModel(int id) {
    return instance.get(id);
  }
}
