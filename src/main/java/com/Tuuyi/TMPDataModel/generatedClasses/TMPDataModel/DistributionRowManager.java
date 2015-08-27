package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class DistributionRowManager extends DomainConceptManager<DistributionRow> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final DistributionRowManager instance = new DistributionRowManager();

  public DistributionRowManager() {
    super();
    managedClass = DistributionRow.class;
    this.NULLVALUE = new DistributionRow(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
  }

  public static DistributionRowManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static DistributionRow getDistributionRow(int id) {
    return instance.get(id);
  }
}
