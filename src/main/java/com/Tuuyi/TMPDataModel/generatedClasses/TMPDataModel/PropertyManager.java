package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class PropertyManager extends DomainConceptManager<Property> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final PropertyManager instance = new PropertyManager();

  public PropertyManager() {
    super();
    managedClass = Property.class;
    this.NULLVALUE = new Property(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
  }

  public static PropertyManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static Property getProperty(int id) {
    return instance.get(id);
  }
}
