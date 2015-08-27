package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class QueryManager extends DomainConceptManager<Query> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final QueryManager instance = new QueryManager();

  public QueryManager() {
    super();
    managedClass = Query.class;
    this.NULLVALUE = new Query(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
  }

  public static QueryManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static Query getQuery(int id) {
    return instance.get(id);
  }
}
