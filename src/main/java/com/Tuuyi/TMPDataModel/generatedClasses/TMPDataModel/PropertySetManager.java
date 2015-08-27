package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class PropertySetManager extends DomainConceptManager<PropertySet> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final PropertySetManager instance = new PropertySetManager();

  public PropertySetManager() {
    super();
    managedClass = PropertySet.class;
    this.NULLVALUE = new PropertySet(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
  }

  public static PropertySetManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static PropertySet getPropertySet(int id) {
    return instance.get(id);
  }
}
