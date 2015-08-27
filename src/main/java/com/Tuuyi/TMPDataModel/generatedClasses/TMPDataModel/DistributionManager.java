package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class DistributionManager extends DomainConceptManager<Distribution> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final DistributionManager instance = new DistributionManager();

  public DistributionManager() {
    super();
    managedClass = Distribution.class;
    this.NULLVALUE = new Distribution(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
  }

  public static DistributionManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static Distribution getDistribution(int id) {
    return instance.get(id);
  }
}
