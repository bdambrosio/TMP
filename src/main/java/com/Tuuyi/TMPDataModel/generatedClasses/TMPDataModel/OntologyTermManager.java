package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class OntologyTermManager extends DomainConceptManager<OntologyTerm> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final OntologyTermManager instance = new OntologyTermManager();

  public OntologyTermManager() {
    super();
    managedClass = OntologyTerm.class;
    this.NULLVALUE = new OntologyTerm(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static OntologyTermManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static OntologyTerm getOntologyTerm(int id) {
    return instance.get(id);
  }
}
