package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

public class SampleTermAddlMethods extends SampleTerm {
//DL public SampleTerm(ScoredTerm term, double score) {
//DL   this.setRelevance(score);
//DL   this.setHasScoredTerm(term);
//DL   this.setHasItems(term.getForTerm().getHasItems());
//DL   this.initIndex();
//DL  }
  
//DL boolean more = true;
  
  int initIndex() {
    setSampleSequence(Projection.getSampleOrder(getHasItems().size()));
    startIndex = (int)Math.floor((Math.random()*(sampleSequence.length-1)));
    while (sampleSequence[startIndex] >= getHasItems().size()) {
      startIndex = (startIndex+1) % getSampleSequence().length;
    }
    nextIndex = startIndex;
    if (getHasItems().size() > 0) {
      more = true;
    } else {
      more = false;
    }
    return startIndex;
  }
  
  /** returns -1 when exhausted **/
  
  protected int incrIndex() {
    if (!more) {
      return -1;
    }
    nextIndex = (nextIndex+1) % getSampleSequence().length;
    while (nextIndex != startIndex && sampleSequence[nextIndex] >= getHasItems().size() ) {
      nextIndex = (nextIndex+1) % getSampleSequence().length;
      if (nextIndex == startIndex ) {
        more = false;
        return -1;
      }
    }
    if (nextIndex == startIndex ) {
      more = false;
      return -1;
    }
    return nextIndex;
  }
  
  public boolean hasMore() {
    return more;
  }
  
  public ItemProjection sample () {
    ItemProjection next = null;
    int sampleIndex = sampleSequence[nextIndex];
    next = getHasItems().get(sampleIndex);
    incrIndex();
    if (next == null) {
      logWriter.error("shouldn't happen");
    }
    return next;
  }


}
