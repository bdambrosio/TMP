/* generated by writeJava methods in Workspace */
package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import com.Tuuyi.TDM.*;
import org.apache.log4j.Logger;
import java.util.*;
import java.lang.reflect.Method;
import org.json.*;
import java.util.concurrent.ConcurrentHashMap;

public class Query extends DomainConcept {

  protected static final Logger logWriter = Logger.getLogger(Query.class.getName());
  private static final boolean persistant = false;

  public void inMemoryOnly(boolean local) {inMemoryOnly = local;}
  public boolean getInMemoryOnly() {return inMemoryOnly;}
  public boolean isPersistant() {return persistant;}

  public static boolean isPersistantClass() {return persistant;}

  protected Distribution hasResult = null;

  protected ArrayList<Distribution> hasDistributions = new ArrayList<Distribution>(0);

  public void setHasDistributionsRawValueInternal(String rawVal) {
  }

  protected NodeList hasRemainingEliminationNodes = null;

  private ArrayList<Node> hasNodeDistMap = new ArrayList<Node>();
  protected NodeList hasTarget = null;

  protected Plan hasPlan = null;

  protected GraphTMP hasModel = null;


  /** if no arg, assume from db **/
  public Query() {
    this(true);
  }
  /** if from DB, set nonFunctional slot wrappers to stub **/
  /**    so subsequent slot get will do retrieval         **/
  public Query(boolean fromDB) {
    super(Workspace.getCurrentWorkspace(), fromDB);
    hasNodeDistMap= new ArrayList<Node>(0);
  }
  public Query(Workspace workspace, boolean fromDB) {
    super(workspace, fromDB);
    hasNodeDistMap= new ArrayList<Node>(0);
  }
  public boolean HasResultIsResident() {
      return true;
  }
  public Distribution getHasResult() {

    return hasResult;
  }
  public int getHasResultInternalId() {
    if (hasResult == null) {
      return -1;
    } else { 
      return hasResult.getId();
    }
  }
  public boolean hasDistributionsAllResident() {
      return true;
  }

  /** returns true if this object contains the given object without accessing the database **/
  public boolean containsHasDistributions(Distribution value) {
      return hasDistributions.contains(value);
  }

  public ArrayList<Distribution> getHasDistributions() {
      return hasDistributions;
  }
  public boolean HasRemainingEliminationNodesIsResident() {
      return true;
  }
  public NodeList getHasRemainingEliminationNodes() {

    return hasRemainingEliminationNodes;
  }
  public int getHasRemainingEliminationNodesInternalId() {
    if (hasRemainingEliminationNodes == null) {
      return -1;
    } else { 
      return hasRemainingEliminationNodes.getId();
    }
  }
  public List<Node> getHasNodeDistMap() {
      return hasNodeDistMap;
  }
  public boolean HasTargetIsResident() {
      return true;
  }
  public NodeList getHasTarget() {

    return hasTarget;
  }
  public int getHasTargetInternalId() {
    if (hasTarget == null) {
      return -1;
    } else { 
      return hasTarget.getId();
    }
  }
  public boolean HasPlanIsResident() {
      return true;
  }
  public Plan getHasPlan() {

    return hasPlan;
  }
  public int getHasPlanInternalId() {
    if (hasPlan == null) {
      return -1;
    } else { 
      return hasPlan.getId();
    }
  }
  public boolean HasModelIsResident() {
      return true;
  }
  public GraphTMP getHasModel() {

    return hasModel;
  }
  public int getHasModelInternalId() {
    if (hasModel == null) {
      return -1;
    } else { 
      return hasModel.getId();
    }
  }
  public int getId() {
    return id;
  }

  public void setHasResult(Distribution newHasResult) {
    hasResult = newHasResult;
  }

  /** note that this method does not check for duplicates, which may cause a DB error */
  public void setHasDistributions(Collection<Distribution> newHasDistributions) {
    hasDistributions=new ArrayList<Distribution>(newHasDistributions);
  }

  public void add1HasDistributions(Distribution newHasDistributions) {
    hasDistributions.add(newHasDistributions);
  }

  /** note that this method does not check for duplicates, which may cause a DB error */
  public void addAllHasDistributions(Collection<Distribution> newHasDistributions) {
    hasDistributions.addAll(newHasDistributions);
  }

  public boolean remove1HasDistributions(Distribution newHasDistributions) {
    return hasDistributions.remove(newHasDistributions);
  }

  public void setHasRemainingEliminationNodes(NodeList newHasRemainingEliminationNodes) {
    hasRemainingEliminationNodes = newHasRemainingEliminationNodes;
  }

  /** note that this method does not check for duplicates, which may cause a DB error */
  public void setHasNodeDistMap(Collection<Node> newHasNodeDistMap) {
    hasNodeDistMap=new ArrayList<Node>(newHasNodeDistMap);
  }

  public void add1HasNodeDistMap(Node newHasNodeDistMap) {
    hasNodeDistMap.add(newHasNodeDistMap);
  }

  /** note that this method does not check for duplicates, which may cause a DB error */
  public void addAllHasNodeDistMap(Collection<Node> newHasNodeDistMap) {
    hasNodeDistMap.addAll(newHasNodeDistMap);
  }

  public boolean remove1HasNodeDistMap(Node newHasNodeDistMap) {
    return hasNodeDistMap.remove(newHasNodeDistMap);
  }

  public void setHasTarget(NodeList newHasTarget) {
    hasTarget = newHasTarget;
  }

  public void setHasPlan(Plan newHasPlan) {
    hasPlan = newHasPlan;
  }

  public void setHasModel(GraphTMP newHasModel) {
    hasModel = newHasModel;
  }
  public void setId (int a_id) {
      if (a_id> -1) {
        id = a_id;
        QueryManager.getInstance().putInCache(this);
      }
  }

  /* to support remove operation on collections, java objs are equal if id match */
  public boolean equals(Object o) {
    if (!(o instanceof Query)) //covers o == null case
      return false;
    Query other = (Query)o;
    if (this == other)
      return true;
    else
      return other.id == id;
  }

  /* to maintain hashCode contract */
  public int hashCode() {
    if (id == -1) {
      if (isPersistant()) {
        id = Workspace.makeGuid();
      } else {
        id = Workspace.nextId();
      }
    }
    return id;
  }

/** method to marshall data from caching layer object to JSON **/
  public JSONObject asJSON () {
    JSONObject jsonObj = new JSONObject();
    try {
      jsonObj.put("class", "Query");
      jsonObj.put("id", id);
      if (getHasResult() != null) {
        jsonObj.put("hasResult", getHasResult().getId());
      }
       if (getHasDistributions() != null) {
         String HasDistributionsAsString = "";
         for (Distribution HasDistributionsitem: getHasDistributions()) {
           HasDistributionsAsString += HasDistributionsitem.getId() + ",";
           HasDistributionsAsString.replaceFirst(",$", "");
         }
         jsonObj.put("hasDistributions", HasDistributionsAsString);
       }
      if (getHasRemainingEliminationNodes() != null) {
        jsonObj.put("hasRemainingEliminationNodes", getHasRemainingEliminationNodes().getId());
      }
      if (getHasNodeDistMap() != null) {
        JSONArray jsonHasNodeDistMap = new JSONArray();
        for (Node row: getHasNodeDistMap()) {
          jsonHasNodeDistMap.put(row.getId());
        }
        jsonObj.put("hasNodeDistMap", jsonHasNodeDistMap);
      }
      if (getHasTarget() != null) {
        jsonObj.put("hasTarget", getHasTarget().getId());
      }
      if (getHasPlan() != null) {
        jsonObj.put("hasPlan", getHasPlan().getId());
      }
      if (getHasModel() != null) {
        jsonObj.put("hasModel", getHasModel().getId());
      }
      jsonObj.put("id", getId());
    } catch (Exception e1) {
      logWriter.error("Error in marshalling to JSON ", e1);
    }
    return jsonObj;
  }


/** method to marshall data from caching layer object to JSON **/
  public JSONObject asJSONTree () {
    ConcurrentHashMap <DomainConcept, DomainConcept> written = new ConcurrentHashMap<DomainConcept, DomainConcept> ();
    return asJSONTreeAux(written);
  }
  public JSONObject asJSONTreeAux (ConcurrentHashMap<DomainConcept, DomainConcept> written) {
    JSONObject jsonObj = new JSONObject();
    try {
      jsonObj.put("class", "Query");
      jsonObj.put("id", id);
      if (written.contains(this)) {
        return jsonObj;
      }
      written.put(this, this);
      if (getHasResult() != null) {
        jsonObj.put("hasResult", getHasResult().asJSONTreeAux(written));
      }
       if (getHasDistributions() != null) {
         String HasDistributionsAsString = "";
         for (Distribution HasDistributionsitem: getHasDistributions()) {
           HasDistributionsAsString += HasDistributionsitem.getId() + ",";
           HasDistributionsAsString.replaceFirst(",$", "");
         }
         jsonObj.put("hasDistributions", HasDistributionsAsString);
       }
      if (getHasRemainingEliminationNodes() != null) {
        jsonObj.put("hasRemainingEliminationNodes", getHasRemainingEliminationNodes().asJSONTreeAux(written));
      }
      if (getHasNodeDistMap() != null) {
        JSONArray jsonHasNodeDistMap = new JSONArray();
        for (Node row: getHasNodeDistMap()) {
          jsonHasNodeDistMap.put(row.asJSONTreeAux(written));
        }
        jsonObj.put("hasNodeDistMap", jsonHasNodeDistMap);
      }
      if (getHasTarget() != null) {
        jsonObj.put("hasTarget", getHasTarget().asJSONTreeAux(written));
      }
      if (getHasPlan() != null) {
        jsonObj.put("hasPlan", getHasPlan().asJSONTreeAux(written));
      }
      if (getHasModel() != null) {
        jsonObj.put("hasModel", getHasModel().asJSONTreeAux(written));
      }
      jsonObj.put("id", getId());
      written.remove(this);
    } catch (Exception e1) {
      logWriter.error("Error in marshalling to JSON ", e1);
    }
    return jsonObj;
  }


/** method to update data in caching layer object from JSON **/
  public boolean updateFromJSON (JSONObject jsonObj) {
    try {
      if (!jsonObj.isNull("hasResult")) {
        int hasResultId = jsonObj.optInt("hasResult");
        Distribution value = DistributionManager.getInstance().get(hasResultId);
        if(value != null) {
            setHasResult(value);
        }
      }
      if (!jsonObj.isNull("hasDistributions")) {
      String [] hasDistributionsAsStrings = jsonObj.optString("hasDistributions").split(",");
      ArrayList<Distribution> hasDistributionsValues = new ArrayList<Distribution>(hasDistributionsAsStrings.length);
      for (String hasDistributionsItemId: hasDistributionsAsStrings) {
      if (hasDistributionsItemId != null && DistributionManager.getInstance().get(Integer.parseInt(hasDistributionsItemId)) != null) {
          hasDistributionsValues.add(DistributionManager.getInstance().get(Integer.parseInt(hasDistributionsItemId)));
        }
      }
      hasDistributions = hasDistributionsValues;
      }
      if (!jsonObj.isNull("hasRemainingEliminationNodes")) {
        int hasRemainingEliminationNodesId = jsonObj.optInt("hasRemainingEliminationNodes");
        NodeList value = NodeListManager.getInstance().get(hasRemainingEliminationNodesId);
        if(value != null) {
            setHasRemainingEliminationNodes(value);
        }
      }
      JSONArray hasNodeDistMapArray = jsonObj.optJSONArray("hasNodeDistMap");
      if(hasNodeDistMapArray != null) {
        ArrayList<Node> aListOfHasNodeDistMap = new ArrayList<Node>(hasNodeDistMapArray.length());
        for (int i = 0; i < hasNodeDistMapArray.length(); i++) {
          int id = hasNodeDistMapArray.optInt(i);
          if (NodeManager.getInstance().get(id) != null) {
            aListOfHasNodeDistMap.add(NodeManager.getInstance().get(id));
          }
        }
        setHasNodeDistMap(aListOfHasNodeDistMap);
      }
      if (!jsonObj.isNull("hasTarget")) {
        int hasTargetId = jsonObj.optInt("hasTarget");
        NodeList value = NodeListManager.getInstance().get(hasTargetId);
        if(value != null) {
            setHasTarget(value);
        }
      }
      if (!jsonObj.isNull("hasPlan")) {
        int hasPlanId = jsonObj.optInt("hasPlan");
        Plan value = PlanManager.getInstance().get(hasPlanId);
        if(value != null) {
            setHasPlan(value);
        }
      }
      if (!jsonObj.isNull("hasModel")) {
        int hasModelId = jsonObj.optInt("hasModel");
        GraphTMP value = GraphTMPManager.getInstance().get(hasModelId);
        if(value != null) {
            setHasModel(value);
        }
      }
    } catch (Exception e) {
      logWriter.error("Failure updating from JSON", e);
      return false;
    }
    return true;
  }

  
  HashMap<Node, ArrayList<Distribution>> nodeDistsMap = new HashMap<Node, ArrayList<Distribution>> ();
 // a query index for detecting outdated TwoOp computations
 protected static int COUNT = 0;
  
  public ArrayList<Distribution> getDists(Node node) {
    return nodeDistsMap.get(node);
  }

  public void removeDist(Node node, Distribution dist) {
    ArrayList<Distribution> dists = nodeDistsMap.get(node);
    if (dists != null) {
      dists.remove(dist);
    }
  }

  public void removeDist(Distribution dist) {
    for (int n = 0; n < dist.getAllNodes().size(); n++) {
      removeDist(dist.getAllNodes().get(n), dist);
    }
  }

  public void addDist(Node node, Distribution dist) {
    ArrayList<Distribution> dists = nodeDistsMap.get(node);
    if (dists == null) {
      dists = new ArrayList<Distribution>();
      nodeDistsMap.put(node, dists);
    }
    dists.add(dist);
  }
  
  public void addDist(Distribution dist) {
    for (int n = 0; n < dist.getAllNodes().size(); n++) {
      addDist(dist.getAllNodes().get(n), dist);
    }
  }


  /**
   * legacy, simplest, no heuristics or caching, plan construction
   */
  public Plan buildDefaultPlan(){
    ArrayList<Distribution> remDists= new ArrayList<Distribution>(getHasDistributions());
    NodeList elimNodes = new NodeList(false);
    for (Distribution dist: getHasDistributions()) {
      elimNodes = elimNodes.union(dist.getHasDomainNodes()).union(dist.getHasRangeNodes());
    }
    elimNodes = elimNodes.setDifference(getHasTarget());
    /* obviously we need a better elim order than this... */
    ArrayList<ArrayList <Distribution> > nodeDistList = new ArrayList<ArrayList<Distribution> > ();
    
    NodeList remNodes = new NodeList(elimNodes);
    
    /* now build plan, assuming elim order is linear ordering. Note this is overly strict, 
     * should be a tree of nodes, and a tree of dists within node
     * but note tree of nodes depends on reduction complexity of dist set within a node
     * so this is a non-trivial computation
     * also note can sometimes eliminate a set of nodes in a single step, so it is 
     * actually a tree of node sets that can be eliminated
     * which, of course, only makes the subtree for the dists combined at that stage yet more complex
     * 
     *  recursive?
     *  
     *  */

    Plan childPlan = null;
    for (int n = 0; n < elimNodes.size(); n++) {
      hasPlan = new Plan();
      if (childPlan != null) {
        hasPlan.add1Children(childPlan);
      }
      Node node = elimNodes.get(n);
      ArrayList <Distribution> nodeDists = new ArrayList<Distribution> ();
      nodeDistList.add(nodeDists);
      for (Distribution dist: remDists) {
        if (dist.getHasDomainNodes().member(node) || dist.getHasRangeNodes().member(node)) {
          nodeDists.add(dist);
        }
      }

      //TODO there MUST be a better elim order than this!
      //TODO note elim order is only a partial order! below is linear, dumb, but may not matter much */
      while (nodeDists.size() > 1) {
         
        Distribution d1 = nodeDists.get(0);
        Distribution d2 = nodeDists.get(1);
        NodeList localNodes = d1.getHasDomainNodes().union(d1.getHasRangeNodes().union(d2.getHasDomainNodes().union(d2.getHasRangeNodes())));
        remDists.remove(d1);
        remDists.remove(d2);
        NodeList localTarget = getHasTarget().intersection(localNodes);
        for (Distribution remDist: remDists) {
          localTarget = localTarget.union(remDist.getHasDomainNodes().union(remDist.getHasRangeNodes()).intersection(localNodes));
        }
        //localTarget = localTarget.intersection(localNodes);
        //TODO don't need to keep so much, should intersect local target w (query nodes union outside dists nodes) 
        TwoOpIndexStructure s1 = d1.makeMultiplyIndexStructure(d2, localTarget);
        hasPlan.getOperations().add(s1);
        //d1.doMultiply(s1);
        Distribution r = s1.getHasResult();
        nodeDists.remove(d1);
        nodeDists.remove(d2);
        nodeDists.add(r);
        remDists.add(r);
        setHasResult(r);
      }
      
      /* now just need to marginalize */
      if (nodeDists.size() > 0 && nodeDists.get(0).getHasDomainNodes().member(node)) {
        Distribution d1 = nodeDists.get(0);
        NodeList localTarget = d1.getHasDomainNodes().union(d1.getHasRangeNodes());
        localTarget = localTarget.removeNode(node);
        TwoOpIndexStructure s1 = d1.makeMultiplyIndexStructure(GraphTMP.ONE, localTarget);
        //d1.doMultiply(s1);
        Distribution r = s1.getHasResult();
        nodeDists.remove(d1);
        nodeDists.add(r);
        remDists.remove(d1);
        remDists.add(r);
        hasPlan.getOperations().add(s1);
        setHasResult(r); // last dist computed is result
      }
      childPlan = hasPlan;
    }
    /* now reduce product expression */
    while (remDists.size() > 1) { 
      Distribution d1 = remDists.get(0);
      Distribution d2 = remDists.get(1);
      NodeList localNodes = d1.getHasDomainNodes().union(d1.getHasRangeNodes().union(d2.getHasDomainNodes().union(d2.getHasRangeNodes())));
      remDists.remove(d1);
      remDists.remove(d2);
      NodeList localTarget = getHasTarget().intersection(localNodes);
      for (Distribution remDist: remDists) {
        localTarget = localTarget.union(remDist.getHasDomainNodes().union(remDist.getHasRangeNodes()).intersection(localNodes));
      }
      TwoOpIndexStructure s1 = d1.makeMultiplyIndexStructure(d2, localTarget);
      hasPlan.getOperations().add(s1);
      //d1.doMultiply(s1);
      Distribution r = s1.getHasResult();
      remDists.add(r);
      setHasResult(r);
    }

    return hasPlan;
  }
  
    
  public Plan buildPlan(){
    /* 
     * Greedy heuristic - select lowest complexity eval first
     * note this means we need to update all remaining node dist lists.
     * 
     * heuristic - # nodes eliminated - log(productSpace)
     *   note that product space can be non-obvious - we may not be in full exponential, may be able to return a factored result
     *   note 2 - complaint was always that elim ordering is sub-optimal for noisy or local expressions.
     *   note 3 - yes but, what IS local expression for value trees?
     *   
     *   ElimPlan for graph (target, {dist})
     *     - buildPlan
     *       elimNodes = {dist}.nodes - targetSet
     *       - {distSet} = for each elimNode, set of distributions naming it in domain or range
     *       - for each in {distSet}, 
     *         - compute elimPlan(distSet)
     *       - while {distSet} not empty:
     *         - choose cheapest distSet ElimPlan
     *         - add to overall ElimPlan
     *         - simulate
     *         - for distSet: {distSets} changed
     *           - compute new elimPlan
     *         - remove distSet from {distSets}
     *           - last distSet removed contains result
     *    - runPlan
     *      - bottom up runPlan for distSet: {distSet}
     *     
     *   
     *   ElimPlan for distSet 
     *     - buildPlan - a plan for a distSet is a sequence of operations
     *        - basic SPI - choose lowest cost pair. This one is simple, cost is f1(productSpace) + f2(resultSpace)
     *          where f1, f2 are chosen from n*log, n*mthRoot, or some other function.
     *     - runPlan
     *        - for each step
     *          - execute step
     *          - remove inputs, add output
     *          - remove inputs from all other distSets add output if needed, mark changed
     *        - for distSet:{distSets} changed
     *          - compute ElimPlan
     * 
     */
    
    /* first some quick simplifications */
    nodePlanCache.clear();
   
    for (Distribution dist: getHasDistributions()) {
        addDist(dist);
    }
    Plan absorbPlan = absorb();
    if (absorbPlan != null && absorbPlan.operations != null && absorbPlan.operations.size() > 0) {
      hasPlan = absorbPlan;
    }

    HashSet<Node> elimNodes = new HashSet<Node>();
    for (Distribution dist: getHasDistributions()) {
      for (int n = 0; n < dist.getAllNodes().size(); n++) {
        Node node = dist.getAllNodes().get(n);
        elimNodes.add(dist.getAllNodes().get(n));
      }
    }
    for (int r = 0; r < getHasTarget().size(); r++) {
      elimNodes.remove(getHasTarget().get(r));
    }

    while (elimNodes.size() > 0) {
      Plan planStep = selectNextNodePlan(elimNodes, getHasDistributions());
      if (hasPlan != null && planStep != null) {
        planStep.add1Children(hasPlan);
      }
      // now build plan bottom up
      if (planStep != null) {
        hasPlan = planStep;
        // now symbolically execute step, so we can determine next step
        sRunPlanStep(planStep, elimNodes, getHasDistributions());
      } else {
        break;
      }
    }

    if (hasPlan == null) {
      hasPlan = new Plan(); 
      ArrayList<Distribution> targetDists = new ArrayList<Distribution> ();
      for (Distribution d: getHasDistributions()) {
        if (!targetDists.contains(d)) {
          targetDists.add(d);
        }
      }
      hasPlan.setHasResults(targetDists);// nothing to do!
    }
    return hasPlan;
  }
  
  protected Plan absorb() {
    // first test for simple absorptions
    Plan absorbPlan = new Plan();
    absorbPlan.setCost(0.0);
    absorbPlan.setOperations(new ArrayList<TwoOpIndexStructure> ());

    ArrayList<Node> nodes = new ArrayList<Node>(nodeDistsMap.keySet());
    for (Node n: nodes) {
      ArrayList<Distribution> nodeDists = nodeDistsMap.get(n);
      if (nodeDists.size() < 2) {
        continue;
      }
      boolean absorptionFound = true;
      while (absorptionFound == true) {
        nodeDists = nodeDistsMap.get(n);
        absorptionFound = false;
        TwoOpIndexStructure operation = null;
        outer: for (Distribution d1: nodeDists) {
          if (d1.getAllNodes().size() == 1 && d1.getAllNodes().get(0) == n) {
            Node absorbNode = n;
            for (Distribution d2: nodeDists) {
              /* rule one - if there are two single-node dists over 1 var, combine */
              if (d2 != d1 && d2.getAllNodes().size() == 1 && d2.getAllNodes().get(0) == absorbNode) {
                NodeList localNodes = d2.getAllNodes();
                NodeList localTarget = localNodes;
                if (!getHasTarget().member(absorbNode) && nodeDists.size() == 2) {
                  // we are combining last two dists for this node, and it isn't in target
                  localTarget = new NodeList();
                }
                //TODO don't need to keep so much, should intersect local target w (query nodes union outside dists nodes) 
                operation = getHasModel().findOperation(d1, d2, localTarget);
                if (operation == null) {
                  operation = d1.makeMultiplyIndexStructure(d2, localTarget);
                }
                absorptionFound = true;
                break outer;
              }
            }
            for (Distribution d2: nodeDists) {
              /* rule two - if there is a single-node dist and a two node dist that overlap, combine */
              if (d2 != d1 && d2.getAllNodes().size() == 2 && d2.getAllNodes().member(absorbNode)) {
                NodeList localNodes = new NodeList(d2.getAllNodes());
                NodeList localTarget = localNodes;
                if (!getHasTarget().member(absorbNode) && nodeDists.size() == 2) {
                  // we are combining last two dists for this node, and it isn't in target
                  localTarget = localTarget.removeNode(absorbNode);
                }
                //TODO don't need to keep so much, should intersect local target w (query nodes union outside dists nodes) 
                operation = getHasModel().findOperation(d1, d2, localTarget);
                if (operation == null) {
                  operation = d1.makeMultiplyIndexStructure(d2, localTarget);
                }
                absorptionFound = true;
                break outer;
              }
            }
          }
        }

        if (absorptionFound == true) {
          absorbPlan.add1Operations(operation);
          removeDist(operation.getDist1());
          hasDistributions.remove(operation.getDist1());
          absorbPlan.remove1HasResults(operation.getDist1());
          removeDist(operation.getDist2());
          hasDistributions.remove(operation.getDist2());
          absorbPlan.remove1HasResults(operation.getDist2());
          addDist(operation.getHasResult());
          hasDistributions.add(operation.getHasResult());
          absorbPlan.add1HasResults(operation.getHasResult());
        }
      }
    }
    absorbPlan.setHasResults(hasDistributions);
    return absorbPlan;
  }
  
 public static int OPERATION_REUSE_COUNT = 0;
  
  public Plan buildReducePlan(NodeList target) {
    Plan reducePlan = new Plan();
    reducePlan.add1Children(hasPlan);
    Distribution prevResult = hasPlan.getHasResults().get(0);
    for (Distribution d2: hasPlan.getHasResults()) {
      if (prevResult != d2) {
        TwoOpIndexStructure tois = getHasModel().findOperation(prevResult, d2, target);
        if (tois == null) {
          tois = prevResult.makeMultiplyIndexStructure(d2, target);
        } else {
          OPERATION_REUSE_COUNT++;
        }
        reducePlan.add1Operations(tois);
        prevResult = tois.getHasResult();
      }
    }
    setHasPlan(reducePlan);
    setHasResult(prevResult);
    return reducePlan;
  }

  /**
   * @param elimNodes - the set of nodes remaining to be eliminated 
   * @param remDists - the set of distributions remaining to be reduced
   * @return - the local plan (sequence of operations) for eliminating the lowest cost node
   *   that is, this selects both the next node to eliminate AND the plan for doing so.
   *   It involves constructing plans for all possible elim node choices, so we can evaluate their cost. 
   *   This is probably silly, we can probably find an adequate but simpler heuristic.
   *   
   *   note this is very expensive, since it is inside a loop at the next layer up. 
   *   We should  cache node plans and only invalidate those affected
   *   by a particular reduction
   *   
   *   simple heuristic - if we have a single-node dist and a dist with at most 1 more var that can absorb it, just do it.
   *   
   *   Note - what if there is nothing to reduce, ie, only elim node is a marginal?
   */
  Plan selectNextNodePlan(HashSet<Node> elimNodes, ArrayList<Distribution> remDists) {
   ArrayList<Plan> nodePlans = new ArrayList<Plan>();
    Iterator<Node> elimItr = elimNodes.iterator();
    while (elimItr.hasNext()) {
      Node node = elimItr.next();
      ArrayList <Distribution> nodeDists = new ArrayList<Distribution> ();
      ArrayList <Distribution> outsideDists = new ArrayList<Distribution> ();
      for (Distribution dist: remDists) {
        if (dist.getAllNodes().member(node)) {
          nodeDists.add(dist);
        } else {
          outsideDists.add(dist);
        }
      }
       Plan plan = buildNodePlan(node, nodeDists, outsideDists);
      if (plan != null) {
        nodePlans.add(plan);
      }
    }
    
    Plan nextNodePlan = null;
    double lowestStepCost = Double.MAX_VALUE;
    for (Plan candidateStep: nodePlans) {
      if (candidateStep.getCost() < lowestStepCost) {
        lowestStepCost = candidateStep.getCost();
        nextNodePlan = candidateStep;
      }
    }
    return nextNodePlan;
  }
  
  void sRunPlanStep(Plan planStep, HashSet<Node> elimNodes, ArrayList<Distribution> remDists) {
    invalidateNodePlanCache(planStep);
    for (TwoOpIndexStructure operation: planStep.getOperations()) {
      NodeList removedNodes = operation.getNodes().setDifference(operation.getHasResult().getAllNodes());
      for (int r = 0; r < removedNodes.size(); r++) {
        elimNodes.remove(removedNodes.get(r));
      }
      remDists.remove(operation.getDist1());
      remDists.remove(operation.getDist2());
      remDists.add(operation.getHasResult());
      planStep.setHasResults(remDists);
    }
  }
  
  protected void invalidateNodePlanCache(Plan planStep) {
    for (TwoOpIndexStructure operation: planStep.getOperations()) {
      NodeList nodes = operation.getDist1().getAllNodes();
      for (int n = 0; n < nodes.size(); n++) {
        nodePlanCache.remove(nodes.get(n));
      }
      nodes = operation.getDist2().getAllNodes();
      for (int n = 0; n < nodes.size(); n++) {
        nodePlanCache.remove(nodes.get(n));
      }
    }
  }

  /**
   * builds a plan to eliminate the chosen node
  */
 
  HashMap <Node, Plan> nodePlanCache = new HashMap<Node, Plan>();
  Plan buildNodePlan (Node node, ArrayList<Distribution> nodeDists, ArrayList<Distribution> outsideDists) {
    if (nodePlanCache.containsKey(node)) {
      Plan cachedPlan = new Plan(nodePlanCache.get(node));
      return cachedPlan;
    }
    if (nodeDists.size() == 1 && nodeDists.get(0).getHasRangeNodes().member(node)) {
      // just marginalize out node
      Plan nodePlan = new Plan();
      nodePlan.setCost(0.0);
      nodePlan.setOperations(new ArrayList<TwoOpIndexStructure> ());
      TwoOpIndexStructure marginalizeStep = Distribution.ONE().makeMultiplyIndexStructure(nodeDists.get(0), nodeDists.get(0).getAllNodes().setDifference(new NodeList(node)));
      nodePlan.getOperations().add(marginalizeStep);
      Distribution r = marginalizeStep.getHasResult();
      ArrayList<Distribution> remDists = new ArrayList<Distribution> (nodeDists);
      remDists.remove(nodeDists.get(0));
      remDists.add(r);
      nodePlan.setCost(marginalizeStep.getCost());
      nodePlan.setHasResults(remDists);
      return nodePlan;
    }
   NodeList outsideNodes = new NodeList();
    for (Distribution dist: outsideDists) {
      outsideNodes = outsideNodes.union(dist.getAllNodes());
    }
    Plan nodePlan = new Plan();
    nodePlan.setCost(0.0);
    nodePlan.setOperations(new ArrayList<TwoOpIndexStructure> ());
    ArrayList <Distribution> remDists = new ArrayList<Distribution> (nodeDists);
    //iterate till set is reduced or nothing left to reduce
    boolean reductionFound = true;
    while (reductionFound == true) {
      reductionFound = false;
      double bestCost = Double.MAX_VALUE;
      TwoOpIndexStructure bestNextStep = null;
      for (int i = 0; i < remDists.size()-1; i++) {
        Distribution d1 = remDists.get(i);
        NodeList d1Nodes = d1.getAllNodes();
        for (int j = i+1; j < remDists.size(); j++) {
          Distribution d2 = remDists.get(j);
          NodeList d2Nodes = d2.getAllNodes();
          // test if makes sense to combine this pair.
          if ( !d1.getAllNodes().intersects(d2.getAllNodes())) {
            continue;
          }
          //ok, have a viable pair, estimate cost
          NodeList localNodes = d1.getAllNodes().union(d2.getAllNodes());
          NodeList localTarget = getHasTarget().intersection(localNodes).union(outsideNodes);
          for (Distribution remDist: remDists) {
            if (remDist != d1 && remDist != d2) {
              localTarget = localTarget.union(remDist.getAllNodes().intersection(localNodes));
            }
          }
          //TODO don't need to keep so much, should intersect local target w (query nodes union outside dists nodes) 
          TwoOpIndexStructure candidateStep = getHasModel().findOperation(d1, d2, localTarget);
          if (candidateStep == null) {
            candidateStep = d1.makeMultiplyIndexStructure(d2, localTarget);
          } else {
            OPERATION_REUSE_COUNT++;
          }
          if ( candidateStep.getCost() < bestCost) {
            reductionFound = true;
            bestCost = candidateStep.getCost();
            bestNextStep = candidateStep;
          }
        }
      }
      
      // did we find a step?
      if (bestNextStep != null) {
        reductionFound = true;
        nodePlan.getOperations().add(bestNextStep);
        Distribution r = bestNextStep.getHasResult();
        remDists.remove(bestNextStep.getDist1());
        remDists.remove(bestNextStep.getDist2());
        remDists.add(r);
        nodePlan.setCost(nodePlan.getCost()+bestNextStep.getCost());
        nodePlan.setHasResults(remDists);
      }
    }
    nodePlanCache.put(node, nodePlan);
    if (nodePlan.getOperations().size() == 0) {
      return null;
    } else {

      return nodePlan;
    }
  }

  public static void invalidate() {
    COUNT++;
  }
   
  public void invalidate(Node node) {
    ArrayList<Distribution> dists = node.getHasDistributions();
    for (Distribution dist: dists) {
      invalidate(dist);
    }
  }

  public void invalidate(Distribution dist) {
    ArrayList<TwoOpIndexStructure> operations = getHasModel().getD1Operations(dist);
    if (operations != null) {
      for (TwoOpIndexStructure tois: operations) {
        invalidate(tois);
      }
    }
  }

  public void invalidate(TwoOpIndexStructure tois) {
    if (tois.getCount() != COUNT) {
      // already invalidated
      return;
    }
    invalidate(tois.getHasResult());
    tois.setCount(-1);
  }

  public Distribution evalPlan() {
    return evalThisPlan(hasPlan);
  }

  public Distribution evalThisPlan(Plan a_plan) {
    Distribution result = null;
    for (Plan child: a_plan.getChildren()) {
      evalThisPlan(child);
    }
    for (TwoOpIndexStructure step: a_plan.getOperations()) {
      step.getHasResult().doMultiply(step);
      result = step.getHasResult();
    }
    return result;
  }
  
}
