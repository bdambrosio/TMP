package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class NamespaceManager extends DomainConceptManager<Namespace> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final NamespaceManager instance = new NamespaceManager();

  public NamespaceManager() {
    super();
    managedClass = Namespace.class;
    this.NULLVALUE = new Namespace(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static NamespaceManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static Namespace getNamespace(int id) {
    return instance.get(id);
  }
}
