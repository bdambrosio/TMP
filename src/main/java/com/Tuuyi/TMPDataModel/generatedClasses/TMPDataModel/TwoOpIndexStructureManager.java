package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TwoOpIndexStructureManager extends DomainConceptManager<TwoOpIndexStructure> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final TwoOpIndexStructureManager instance = new TwoOpIndexStructureManager();

  public TwoOpIndexStructureManager() {
    super();
    managedClass = TwoOpIndexStructure.class;
    this.NULLVALUE = new TwoOpIndexStructure(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static TwoOpIndexStructureManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static TwoOpIndexStructure getTwoOpIndexStructure(int id) {
    return instance.get(id);
  }
}
