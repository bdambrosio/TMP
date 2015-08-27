package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class RestrictedBoltzmannMachineManager extends DomainConceptManager<RestrictedBoltzmannMachine> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final RestrictedBoltzmannMachineManager instance = new RestrictedBoltzmannMachineManager();

  public RestrictedBoltzmannMachineManager() {
    super();
    managedClass = RestrictedBoltzmannMachine.class;
    this.NULLVALUE = new RestrictedBoltzmannMachine(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static RestrictedBoltzmannMachineManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static RestrictedBoltzmannMachine getRestrictedBoltzmannMachine(int id) {
    return instance.get(id);
  }
}
