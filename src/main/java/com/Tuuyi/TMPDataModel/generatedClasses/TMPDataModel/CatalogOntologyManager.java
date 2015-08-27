package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CatalogOntologyManager extends DomainConceptManager<CatalogOntology> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final CatalogOntologyManager instance = new CatalogOntologyManager();

  public CatalogOntologyManager() {
    super();
    managedClass = CatalogOntology.class;
    this.NULLVALUE = new CatalogOntology(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static CatalogOntologyManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static CatalogOntology getCatalogOntology(int id) {
    return instance.get(id);
  }
}
