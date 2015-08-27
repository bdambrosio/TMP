package com.Tuuyi.TMPDataModel;

public interface PropagationFilter {

  boolean propagate(final String item);

  boolean collect(final String item);

  }
