package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

public class ScoredTermAddlMethods extends ScoredTerm {

//DL public ScoredTerm(OntologyTerm term, double score) {
//DL   this.setForTerm(term);
//DL   this.setScore(score);
//DL  }
  
  /**
   * relevance is a combination of term weight, number of local items, and recursive number of items
   * intuition - the higher the weight, the more relevant, the lower the number of items, the more relevant, 
   *    this latter under the assumption we are going to AND results from terms.
   */
  public double relevance() {
    return this.getForTerm().recursiveItemCount > 0 ? this.score / (this.getForTerm().getRecursiveItemCount()+1) : 0;
  }
  
  public String toString() {
    return this.getForTerm().getHasValue();
  }
  
}
