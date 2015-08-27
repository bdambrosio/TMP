
package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

public class GraphTrainingContextAddlMethods extends  GraphContext {

  protected Beliefspace inputSumSpace;
  protected Beliefspace outputSumSpace;
  protected Beliefspace arcSums;
  protected boolean inputKnown = true;
  protected boolean outputKnown = true; 

  /**
   * Basic training
   * Initialize: 
   *  (optional) create <unknownSetSize> unknown beliefspace, set each to 1/n? or random around 1/n?
   *  train. But, use unknown belief space posteriors as other half of training instances.
   *    (note initially this will just be random priors if we haven't initialized arc space)
   *    apply each training instance:
   *      scatter
   *      use inferred values as training instance for arcs.
   */
  
//DL   public GraphTrainingContext (InstanceSparseGraph initGraph, boolean inputKnown, boolean outputKnown) {
//DL     super(initGraph);
//DL     this.inputKnown = inputKnown;
//DL     this.outputKnown = outputKnown;
//DL     this.inputSumSpace = new Beliefspace();
//DL     this.outputSumSpace = new Beliefspace();
//DL     this.arcSums = new Beliefspace();
//DL     this.outputKnown = outputKnown;
//DL   }
//DL   public GraphTrainingContext  (boolean inputKnown, boolean outputKnown) {
//DL     super(new InstanceSparseGraph());
//DL     this.inputKnown = inputKnown;
//DL     this.outputKnown = outputKnown;
//DL     this.inputSumSpace = new Beliefspace();
//DL     this.outputSumSpace = new Beliefspace();
//DL     this.arcSums = new Beliefspace();
//DL     this.outputKnown = outputKnown;
//DL   }
  
  protected boolean train(InstanceSparseGraph.InstanceGraphInstance [] data) {
    return true;
  }
}
