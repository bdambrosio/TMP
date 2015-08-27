package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

public class OntologyTermAddlMethods extends OntologyTerm {

//DL    public OntologyTerm(String name) {
//DL      this.setHasValue(name);
//DL    }
  
  public String toString() {
    return getHasValue();
  }
  
  public void initializeRecursiveItemCount() {
    setRecursiveItemCount(-1);
  }
  
  public long computeRecursiveItemCount() {
    long count = 0;
    count = getHasItems().size();
    for (OntologyRelation relation: getAsArg2In()) {
      if (relation.getRelation() == OntologyRelation.GENERALIZATION_RELATION) {
        if (relation.getHasArg1().getRecursiveItemCount() < 0) {
          relation.getHasArg1().computeRecursiveItemCount();
        }
        count += relation.getHasArg1().getRecursiveItemCount();
        
      }
    }
    setRecursiveItemCount(count);
    return count;
  }

}
