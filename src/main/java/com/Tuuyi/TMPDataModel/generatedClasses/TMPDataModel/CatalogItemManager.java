package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CatalogItemManager extends DomainConceptManager<CatalogItem> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final CatalogItemManager instance = new CatalogItemManager();

  public CatalogItemManager() {
    super();
    managedClass = CatalogItem.class;
    this.NULLVALUE = new CatalogItem(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static CatalogItemManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static CatalogItem getCatalogItem(int id) {
    return instance.get(id);
  }
}
