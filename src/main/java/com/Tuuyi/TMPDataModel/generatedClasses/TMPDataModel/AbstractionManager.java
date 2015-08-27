package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class AbstractionManager extends DomainConceptManager<Abstraction> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final AbstractionManager instance = new AbstractionManager();

  public AbstractionManager() {
    super();
    managedClass = Abstraction.class;
    this.NULLVALUE = new Abstraction(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static AbstractionManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static Abstraction getAbstraction(int id) {
    return instance.get(id);
  }
}
