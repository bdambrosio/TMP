package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class DistributionListManager extends DomainConceptManager<DistributionList> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final DistributionListManager instance = new DistributionListManager();

  public DistributionListManager() {
    super();
    managedClass = DistributionList.class;
    this.NULLVALUE = new DistributionList(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
  }

  public static DistributionListManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static DistributionList getDistributionList(int id) {
    return instance.get(id);
  }
}
