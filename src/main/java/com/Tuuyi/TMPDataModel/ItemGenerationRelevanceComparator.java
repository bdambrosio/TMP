package com.Tuuyi.TMPDataModel;

import java.util.Collection;
import java.util.Comparator;

import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.SampleTerm;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.ScoredTerm;

public class ItemGenerationRelevanceComparator implements Comparator<SampleTerm> {
  Collection<ScoredTerm> target = null;
  double targetContextScore = 0.0;
  
  protected ItemGenerationRelevanceComparator(Collection<ScoredTerm> target) {
    this.target = target;
    targetContextScore = score(target);
  }
  
  double score(Collection <ScoredTerm> target) {
    double score = 0.0;
    for (ScoredTerm term: target) {
      score += term.getScore();
    }
    return score;
  }
  
  @Override
  public int compare(SampleTerm arg0, SampleTerm arg1) {
    if (arg0 == null && arg1 == null) {
      return 0;
    } else if (arg0 == null) {
      return -1;
    } else if (arg1 == null) {
      return 1;
    } else {
      double r0 = arg0.getRelevance();
      double r1 = arg1.getRelevance();
      return  r0 == r1 ? 0 : (r0 < r1 ? -1 : 1);
      
    }
  }
  
}
