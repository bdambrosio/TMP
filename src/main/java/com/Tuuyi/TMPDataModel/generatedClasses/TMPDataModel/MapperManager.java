package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class MapperManager extends DomainConceptManager<Mapper> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final MapperManager instance = new MapperManager();

  public MapperManager() {
    super();
    managedClass = Mapper.class;
    this.NULLVALUE = new Mapper(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static MapperManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static Mapper getMapper(int id) {
    return instance.get(id);
  }
}
