package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

/** written by Workspace writeJavaManagerClass method **/

import com.Tuuyi.TDM.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TokenManager extends DomainConceptManager<Token> {

  protected static final AtomicBoolean check = new AtomicBoolean(false);
  protected static final TokenManager instance = new TokenManager();

  public TokenManager() {
    super();
    managedClass = Token.class;
    this.NULLVALUE = new Token(false);
    // the datalayer model breaks down if we have multiple managers for a single type
    if (check.getAndSet(true)) {
      super.logWriter.fatal("MULTIPLE MANAGER CONSTRUCTION!");
      throw new IllegalStateException("multiple construction failure");
    }
    this.localCache = null;
  }

  public static TokenManager getInstance() {
    return instance;
  }

  /** A shortcut for Manager.getInstance().get(int id) */
  public static Token getToken(int id) {
    return instance.get(id);
  }
}
