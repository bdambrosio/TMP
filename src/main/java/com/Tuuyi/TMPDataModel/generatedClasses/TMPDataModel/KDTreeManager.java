package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class KDTreeManager extends DomainConceptManager<KDTree> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final KDTreeManager instance = new KDTreeManager();

  public KDTreeManager() {
    super();
    managedClass = KDTree.class;
    this.NULLVALUE = new KDTree(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static KDTreeManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static KDTree getKDTree(int id) {
    return instance.get(id);
  }
}
