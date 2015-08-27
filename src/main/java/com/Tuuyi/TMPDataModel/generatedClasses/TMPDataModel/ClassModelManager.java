package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClassModelManager extends DomainConceptManager<ClassModel> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final ClassModelManager instance = new ClassModelManager();

  public ClassModelManager() {
    super();
    managedClass = ClassModel.class;
    this.NULLVALUE = new ClassModel(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
  }

  public static ClassModelManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static ClassModel getClassModel(int id) {
    return instance.get(id);
  }
}
