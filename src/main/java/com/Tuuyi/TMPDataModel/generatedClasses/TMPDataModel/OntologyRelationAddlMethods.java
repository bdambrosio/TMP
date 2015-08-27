package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

public class OntologyRelationAddlMethods extends OntologyRelation {

//DL  public static final String TYPE_RELATION = "TYPE_RELATION";
//DL  public static final String GENERALIZATION_RELATION = "GENERALIZATION_RELATION";
  
//DL  public OntologyRelation (String relationType, OntologyTerm arg1, OntologyTerm arg2) {
//DL    this.setHasArg2(arg2);
//DL    this.setRelation(relationType);
//DL    this.setHasArg1(arg1);
//DL    this.setHasArg2(arg2);
//DL    arg1.add1AsArg1In(this);
//DL    arg2.add1AsArg2In(this);
//DL  }
  
  public String toString() {
    return relation+"("+getHasArg1().getHasValue()+","+getHasArg2().getHasValue()+")";
  }
}
