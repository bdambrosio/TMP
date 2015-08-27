package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class OntologyRelationManager extends DomainConceptManager<OntologyRelation> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final OntologyRelationManager instance = new OntologyRelationManager();

  public OntologyRelationManager() {
    super();
    managedClass = OntologyRelation.class;
    this.NULLVALUE = new OntologyRelation(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static OntologyRelationManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static OntologyRelation getOntologyRelation(int id) {
    return instance.get(id);
  }
}
