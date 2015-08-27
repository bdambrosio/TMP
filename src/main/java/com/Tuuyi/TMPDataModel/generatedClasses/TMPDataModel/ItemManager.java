package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ItemManager extends DomainConceptManager<Item> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final ItemManager instance = new ItemManager();

  public ItemManager() {
    super();
    managedClass = Item.class;
    this.NULLVALUE = new Item(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static ItemManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static Item getItem(int id) {
    return instance.get(id);
  }
}
