package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

//import - causes next line to be included in base class
import java.io.BufferedReader;
//import
import java.io.IOException;
//import
import java.io.BufferedWriter;
//import
import java.io.File;
//import
import java.io.FileInputStream;
//import
import java.io.FileOutputStream;
//import
import java.io.InputStreamReader;
//import
import java.io.OutputStreamWriter;
//import - causes next line to be included in base class
import java.io.Writer;
//import
import java.util.ArrayList;
import java.util.HashSet;
//import
import java.util.Iterator;
//import
import java.util.Map.Entry;
//import
import java.util.Date;
//import
import java.util.HashMap;
//import
import java.util.List;
//import
import java.util.Map;



//import
import javax.xml.parsers.SAXParser;
//import
import javax.xml.parsers.SAXParserFactory;



//import
import org.json.JSONArray;
import org.json.JSONException;
//import
import org.json.JSONObject;
//import
import org.xml.sax.Attributes;
//import
import org.xml.sax.ErrorHandler;
//import
import org.xml.sax.InputSource;
//import
import org.xml.sax.SAXException;
//import
import org.xml.sax.SAXParseException;
//import
import org.xml.sax.helpers.DefaultHandler;



//import
import com.Tuuyi.TDM.DomainConcept;
//import
import com.Tuuyi.TMP.TMPProcessor;

public class GraphTMPAddlMethods extends GraphTMP {
  
//DL public static Distribution ONE = null;
//DL protected HashMap<String, Node> modelVars = new HashMap<String, Node>();
//DL HashMap<String, TwoOpIndexStructure> operations = new HashMap<String, TwoOpIndexStructure> ();
//DL HashMap<Distribution, ArrayList<TwoOpIndexStructure>> d1Operations = new HashMap<Distribution, ArrayList<TwoOpIndexStructure>> ();
//DL HashMap<Distribution, ArrayList<TwoOpIndexStructure>> d2Operations = new HashMap<Distribution, ArrayList<TwoOpIndexStructure>> ();
//DL HashMap<Distribution, ArrayList<TwoOpIndexStructure>> rOperations = new HashMap<Distribution, ArrayList<TwoOpIndexStructure>> ();
//DL protected boolean cutArcs = true;

//DL  public static GraphTMP newGraph() {
//DL    if (ONE == null) {
//DL      graphInit();
//DL    }
//DL    GraphTMP tmp = new GraphTMP();
//DL    tmp.setHasInstances(new ArrayList<Instance> ());
//DL    tmp.setHasDomainNodes(new NodeList ());
//DL    return tmp;
//DL  }
  
//DL  public static void graphInit() {
//DL   
//DL    ONE = Distribution.newDistribution(new NodeList(), new NodeList());
//DL    ArrayList<DistributionRow> d1Rows = new ArrayList<DistributionRow> (1);
//DL    DistributionRow d1Row = DistributionRow.newRow(new int [0], new double [] {1.0} );
//DL    d1Rows.add(d1Row);
//DL    ONE.setHasDistributionRows(d1Rows);
//DL  }

  /**
   * @param d1 - a distribution
   * @result - all indexed operations using that distribution as left arg
   */
  public ArrayList<TwoOpIndexStructure> getD1Operations(Distribution d1) {
    return d1Operations.get(d1);
  }
  
  /**
   * @param d2 - a distribution
   * @result - all indexed operations using that distribution as right arg
   */
  public ArrayList<TwoOpIndexStructure> getD2Operations(Distribution d2) {
    return d2Operations.get(d2);
  }
  
  /**
   * @param r - a distribution
   * @result - all indexed operations using that distribution as result
   */
  
  public ArrayList<TwoOpIndexStructure> getROperations(Distribution r) {
    return rOperations.get(r);
  }
  
  /**
   * @param tois - an operation to remove from all operation indexes
   * @result - void
   */
  public void removeOperation(TwoOpIndexStructure tois) {
    ArrayList<TwoOpIndexStructure> d1Array = d1Operations.get(tois.getDist1());
    if (d1Array != null) {
      d1Array.remove(tois);
    }
    ArrayList<TwoOpIndexStructure> d2Array = d1Operations.get(tois.getDist2());
    if (d2Array != null) {
      d2Array.remove(tois);
    }
    ArrayList<TwoOpIndexStructure> rArray = rOperations.get(tois.getHasResult());
    if (rArray != null) {
      rArray.remove(tois);
    }
  }
  
  /**
   * @param tois - an operation to index
   * @return - void
   * indexes an operation under left, right args and result
   * //TODO - we might want to convert first-level arrays into hashSets? 
   */
  public void indexOperation(TwoOpIndexStructure tois) {
    ArrayList<TwoOpIndexStructure> array = d1Operations.get(tois.getDist1());
    if (array == null) {
      array = new ArrayList<TwoOpIndexStructure> ();
      d1Operations.put(tois.getDist1(), array);
    }
    if (!array.contains(tois)) {
      array.add(tois);
    }

    array = d2Operations.get(tois.getDist2());
    if (array == null) {
      array = new ArrayList<TwoOpIndexStructure> ();
      d2Operations.put(tois.getDist2(), array);
    }
    if (!array.contains(tois)) {
      array.add(tois);
    }
    array = rOperations.get(tois.getHasResult());
    if (array == null) {
      array = new ArrayList<TwoOpIndexStructure> ();
      rOperations.put(tois.getHasResult(), array);
    }
    if (!array.contains(tois)) {
      array.add(tois);
    }
  }
  
  /**
   * 
   * @param d1 - the required first arg of the cached operation we seek
   * @param d2 - the required second arg of the cached operation we seek
   * @param target - the required result nodes of the cached operation we seek
   * @return - a cached operation performing the required computation
   */
  public TwoOpIndexStructure findOperation(Distribution d1, Distribution d2, NodeList target) {
    TwoOpIndexStructure result = null;
    ArrayList<TwoOpIndexStructure> d1Ops = getD1Operations(d1);
    if (d1Ops == null) {
      return null;
    }
    for (TwoOpIndexStructure d1Operation: d1Ops) {
      if (d1Operation.getDist2() == d2) {
        if (target.matches(d1Operation.getHasResult().getAllNodes())) {
          return d1Operation;
        }
      }
    }
    return null;
  }
  
//DL public static boolean BUILD_DISTRIBUTION = true;
//DL public static boolean NO_DISTRIBUTION = false;
//DL public static boolean BUILD_QUERY = true;
//DL public static boolean NO_QUERY = false;
  
  private VTDistribution makeHierarchicalDistribution(Node node, NodeList parents) {
    // build node marginal distribution
    VTDistribution d = new VTDistribution();
    addDistribution(d);
    d.setHasDomainNodes(parents);
    d.setHasRangeNodes(new NodeList());
    d.addRangeNode(node);
    d.setHasDistributionRows(new ArrayList<DistributionRow>());
    DistributionRow dr = new DistributionRow();
    dr.setIndexVector(new int [0]);
    
    // set prior
    // dr.setRow(priors);
    d.add1HasDistributionRows(dr);
    return d;
  }

  public Node makeNode(String name, String [] states, double [] priors, boolean buildDistribution, boolean buildQuery) throws IllegalArgumentException {
    // build node
    if (getModelVar(name) != null) {
      throw new IllegalArgumentException("Node already exists "+name);
    }
    Node node = Node.newNode(name);
    node.setHasName(name);
    node.setId(node.getOrder());
    
    // set node domain
    node.setDomain(states);

    // add this question to query set on graph
    // note - when we trade on a node, plan will be reconstructed.
    // we should reuse and invalidate same query object. But what about all the indexed plan objects? delete from indices?
    addModelVar(node);    
    
    if (!buildDistribution) {
      return node;
    }
    // build node marginal distribution
    Distribution d = new Distribution();
    addDistribution(d);
    d.setHasDomainNodes(new NodeList());
    d.setHasRangeNodes(new NodeList());
    d.addRangeNode(node);
    d.setHasDistributionRows(new ArrayList<DistributionRow>());
    DistributionRow dr = new DistributionRow();
    dr.setIndexVector(new int [0]);
    
    // set prior
    dr.setRow(priors);
    d.add1HasDistributionRows(dr);
    
    // add distribution to node
    node.add1HasDistributions(d);
    node.add1Model(d);
    
    if (buildQuery) {
      Query q = makeQuery(new NodeList(node));
      q.buildReducePlan(new NodeList(node));
      indexQuery(q);
    } 
    
    return node;
  }

  protected Node makeEPrimeNode(String questionId, String suffix, double prior, ArrayList<HierarchicalNode> parents) {
    String varName = "Q"+questionId+suffix+".prime";
    if (getModelVar(varName) != null) {
      throw new IllegalArgumentException("node already exists");
    }
    // set node domain
    String [] domain = new  String [2];
    domain[0] = "I";
    domain[1] = "V";

    Node node = Node.newNode(varName);
    node.setHasName(varName);
    node.setId(node.getOrder());
    // set node domain
    node.setDomain(domain);
    addModelVar(node);    
    
    for (int n=0; n < parents.size(); n++) {      
      NodeList range = new NodeList(node);
      NodeList conditional = new NodeList(parents.get(n));
      Distribution d = Distribution.newDistribution(conditional, range);
      ArrayList<DistributionRow> dRows = new ArrayList<DistributionRow> (2);
      DistributionRow dRow1 = DistributionRow.newRow(new int []{0}, new double [] {1.0, 1.0} );
      DistributionRow dRow2 = DistributionRow.newRow(new int []{1}, new double [] {1.0, 0.0} );
      dRows.add(dRow1);
      dRows.add(dRow2);
      d.setHasDistributionRows(dRows);

      node.add1HasDistributions(d);
      node.add1Model(d);
      parents.get(n).add1HasDistributions(d);
      getHasDistributions().add(d);
    }
    return node;
  }
  
  public HierarchicalNode makeHierarchicalNode(String name, String [] states, double [] priors, boolean buildDistribution, boolean buildQuery) throws IllegalArgumentException {
    // build node
    if (getModelVar(name) != null) {
      throw new IllegalArgumentException("Node already exists "+name);
    }
    HierarchicalNode node = new HierarchicalNode();
    node.setHasName(name);
    node.setOrder(Node.nextOrder());
    node.setEvidence(-1);
    node.setHasName(name);
    node.setId(node.getOrder());
    
    // set node domain
    node.setDomain(states);

    // add this question to query set on graph
    // note - when we trade on a node, plan will be reconstructed.
    // we should reuse and invalidate same query object. But what about all the indexed plan objects? delete from indices?
    addModelVar(node);    
    
    if (!buildDistribution) {
      return node;
    }
    // build node marginal distribution
    Distribution d = new Distribution();
    addDistribution(d);
    d.setHasDomainNodes(new NodeList());
    d.setHasRangeNodes(new NodeList());
    d.addRangeNode(node);
    d.setHasDistributionRows(new ArrayList<DistributionRow>());
    DistributionRow dr = new DistributionRow();
    dr.setIndexVector(new int [0]);
    
    // set prior
    dr.setRow(priors);
    d.add1HasDistributionRows(dr);
    
    // add distribution to node
    node.add1HasDistributions(d);
    node.add1Model(d);
    
    if (buildQuery) {
      Query q = makeQuery(new NodeList(node));
      q.buildReducePlan(new NodeList(node));
      indexQuery(q);
    } 
    
    return node;
  }

  
  protected Distribution makeE_EPrimeDistribution(Node node, Node ePrime) {
    NodeList range = new NodeList(node);
    NodeList conditional = new NodeList(ePrime);
    Distribution d = Distribution.newDistribution(conditional, range);
    ArrayList<DistributionRow> dRows = new ArrayList<DistributionRow> (2);
    // E' = I, E = {F, T}
    DistributionRow dRow1 = DistributionRow.newRow(new int []{0}, new double [] {0.0, 1.0} );
    // E' = V, E = {F, T}
    DistributionRow dRow2 = DistributionRow.newRow(new int []{1}, new double [] {1.0, -1.0} );
    dRows.add(dRow1);
    dRows.add(dRow2);
    d.setHasDistributionRows(dRows);

    node.add1HasDistributions(d);
    node.add1Model(d);
    ePrime.add1HasDistributions(d);
    getHasDistributions().add(d);
    return d;
  }
  
  /**
   * Build basic linear up/down computations and cached posteriors at each node in tree
   */
  protected Distribution buildHierarchicalPosteriors(Node sink, HierarchicalDomain domainStructure) {
    buildTreePiRecursive(sink);
    buildTreeLambdasRecursive(sink, null, 0);
    buildTreePosteriorRecursive(sink, null);
    return sink.getCachedPosterior();
  }

  /**
   * @param node - the current node to build the Lambda messages for.
   *   starting node is furthest from roots, so we can build lambda msgs immediately on the way back to roots
   *   (note "higher" is closer to roots in this terminology, since Bayes net drawing convention puts roots at top)
   *   the lambdas for each child are stored on the "cachedLambdas" field of the node
   *   Each lambda to parent (lambda_i) is 
   *   * lambda from child (only one - hierarchical decomposition, remember?)
   *   * ownDists
   *   * pi from parent_h, h != i
   *   Note this last is NOT same as cachedPi, because h == i is excluded, so different for each parent.
   * @return - void.
   */

  protected void buildTreeLambdasRecursive (Node node, Node child, int pathDepth) {
    Distribution lambda = null;
    if (child != null) {
      lambda = child.getCachedLambda(node);
      if (lambda == null) {
        logWriter.error("missing lambda message from child "+child.getHasName());
      }
    }
    // build lambda for each parent
    for (Distribution d_l: node.getModel()) {
      // start with lambda from child
      // verify this is a std tree parent, not a conditioning node or ...
      if (d_l.getHasDomainNodes().size() != 1) {
        continue;
      } 
      Node parent = d_l.getHasDomainNodes().get(0);
      ArrayList<Distribution> input = new ArrayList<Distribution>();
      if (lambda != null) {
        input.add(lambda);
      }
      // now include this conditionals and pi msgs for each parent != parent_l
      for (Distribution d_li: node.getModel()) {
        if (d_li.getHasDomainNodes().size() != 1) {
          continue;
        } else {
          Node oParent = d_li.getHasDomainNodes().get(0);
          input.add(d_li);
          if (d_li != d_l) {
            Distribution oParentPi = oParent.getCachedPi();
            if (oParentPi == null) {
              logWriter.error("parent missing pi msg "+node.getHasName());
            } else {
              input.add(oParentPi);
            }
          }
        }
      }
      if (input.size() == 1) {
        input.add(Distribution.ONE());
      }
      Query q = new Query();
      q.setHasModel(this);
      NodeList targets = new NodeList(parent);
      q.setHasDistributions(input);
      q.setHasTarget(targets);
      q.buildPlan();
      q.buildReducePlan(targets);
      q.evalPlan();
      Distribution rawLambda_l= q.getHasResult();
      node.add1CachedLambdas(rawLambda_l);
      buildTreeLambdasRecursive(parent, node, pathDepth+1);
    }
  }

  /**
   * @param node - the current node to build a Pi message for.
   *   code will recurse down to roots then building up pi recursively from lower pi msgs
   *   (note "higher" is closer to roots in this terminology, since Bayes net drawing convention puts roots at top)
   *   the pi from a node is stored on the "cachedPi" field of the node
   * @return - void.
   */
  protected Distribution buildTreePiRecursive(Node node) {
    if (node.getCachedPi() != null) {
      return node.getCachedPi();
    }
    boolean root = true;
    for (Distribution d: node.getModel()) {
      if (d.getHasDomainNodes().size() > 0) {
        root = false;
      }
    }
    if (root) { // at a root, pi is prior
      if (node.getModel().size() != 1) {
        logWriter.debug("Unexpected distribution while building pi "+node.toString());
      }      
      Distribution pi = node.getModel().get(0);
      node.setCachedPi(pi);
      return pi;
    } else {
      ArrayList<Distribution> input = new ArrayList<Distribution>();
      for (Distribution d: node.getModel()) {
        if (d.getHasDomainNodes().size() != 1) {
          logWriter.debug("Unexpected distribution while building pi "+d.toString());
        } else {
          Node parent = d.getHasDomainNodes().get(0);
          input.add(d);
          Distribution parentPi = buildTreePiRecursive(parent);
          if (parentPi == null) {
            logWriter.error("parent missing pi msg "+node.getHasName());
          } else {
            input.add(parentPi);
          }
        }
      }
      Query q = new Query();
      q.setHasModel(this);
      NodeList targets = new NodeList(node);
      q.setHasDistributions(input);
      q.setHasTarget(targets);
      // todo - need a buildPlan feature that does NOT expand beyond provided dists.
      q.buildPlan();
      q.buildReducePlan(targets);
      q.evalPlan();
      Distribution rawPi = q.getHasResult();
      Distribution norm = rawPi.multiply(Distribution.ONE(), new NodeList());
      Distribution pi = rawPi.divide(norm, targets);
      node.setCachedPi(pi);
      return pi;
    }
  }
  
  /**
   * @param node - the current node to build a Pi message for.
   *   this assumes pi and lambda caches have been filled for the tree
   *   code will recurse down thru tree building final tree posterior distribution for each node
   *   (note "higher" is closer to roots in this terminology, since Bayes net drawing convention puts roots at top)
   *   the distribution from a node is stored on the "cachedPosterior" field of the node
   *   posterior is just * of pi*lambda_p.
   * @return - void.
   */
  protected Distribution buildTreePosteriorRecursive(Node node, Node child) {

    if (child == null) {
      node.setCachedPosterior(node.getCachedPi());
      return node.getCachedPi();
    }
    ArrayList<Distribution> input = new ArrayList<Distribution>();
    input.add(node.getCachedPi());
    Distribution childLambda = child.getCachedLambda(node);
    if (childLambda == null) {
      logWriter.error("child missing lambda msg "+node.getHasName());
    } else {
      input.add(childLambda);
    }
    Query q = new Query();
    q.setHasModel(this);
    NodeList targets = new NodeList(node);
    q.setHasDistributions(input);
    q.setHasTarget(targets);
    q.buildReducePlan(targets);
    q.evalPlan();
    Distribution rawPosterior = q.getHasResult();
    Distribution norm = rawPosterior.multiply(Distribution.one, new NodeList());
    Distribution posterior = rawPosterior.divide(norm, targets);
    node.setCachedPosterior(posterior);
    return posterior;
  }
  
  /*Query q = new Query();
    q.setHasModel(this);
    NodeList targets = new NodeList(sink);
    q.setHasDistributions(getConnectedDistributions(targets));
    q.setHasTarget(targets);
    q.buildReducePlan(targets);
    q.evalPlan();
    Distribution posterior = q.getHasResult();
    posterior.normalize();
    return posterior;
  */
 
  /**
   * @param targets - a list of nodes across which we want a posterior marginal distribution
   */
  public Query makeQuery(NodeList targets) {
    Query q = new Query();
    q.setHasModel(this);
    q.setHasDistributions(getConnectedDistributions(targets));
    q.setHasTarget(targets);
    q.buildPlan();
    return q;
  }
  
  protected ArrayList<Distribution> getConnectedDistributions(NodeList targets) {
    if (targets.size() == 1) {
      return getConnectedDistributions(targets.get(0));
    }
    NodeList connectedNodes = new NodeList(targets);
    HashSet<Distribution> connectedDistributions = new HashSet<Distribution> ();
    for (int ni = 0; ni < targets.size(); ni++) {
      Node n = targets.get(ni);
      ArrayList<Distribution> nodeDists = getConnectedDistributions(n);
      for (Distribution d: nodeDists) {
        connectedDistributions.add(d);
      }
    }
    ArrayList<Distribution> cdList = new ArrayList<Distribution>(connectedDistributions);
    return cdList;
  }
  
//DL HashSet<Node> touchedNodes = new HashSet<Node>();
//DL HashMap <Node, ArrayList<Distribution>> nodeConnectedDistributions = new HashMap<Node, ArrayList<Distribution>>();
  protected ArrayList<Distribution> getConnectedDistributions(Node target) {
    if (nodeConnectedDistributions.get(target) != null) {
      return nodeConnectedDistributions.get(target);
    }
    NodeList connectedNodes = new NodeList(target);
    HashSet<Distribution> connectedDistributions = new HashSet<Distribution> (target.getHasDistributions());
    touchedNodes.clear();
    touchedNodes.add(target);
    ArrayList<Distribution> newDists = new ArrayList<Distribution> (connectedDistributions);
    boolean added = true;
    while (newDists.size() > 0) {
      Distribution d = newDists.remove(0);
      if (d.getAllNodes().size() != (d.getHasDomainNodes().union(d.getHasRangeNodes()).size()) ){
        logWriter.error("bad AllNodes");
      }
      NodeList dNodes = d.getAllNodes();
      for (int ni = 0; ni < dNodes.size(); ni++) {
        Node n = dNodes.get(ni);
        if (!touchedNodes.contains(n)) {
          touchedNodes.add(n);
          for (Distribution d1: n.getHasDistributions()) {
            if (!connectedDistributions.contains(d1)) {
              connectedDistributions.add(d1);
              newDists.add(d1);
            }
          }
        }
      }  
    }
    ArrayList<Distribution> cdList = new ArrayList<Distribution>(connectedDistributions);
    nodeConnectedDistributions.put(target, cdList);
    return cdList;
  }
  
  /**
   * 
   * @param q - a query to index
   * @return void
   * This is not default in makeQuery since some queries may be throw-away
   */
  public void indexQuery(Query q) {
    indexPlan(q.getHasPlan());
  }
  
  /**
   * @param p - the specific Plan object to be indexed
   */
  public void indexPlan(Plan p) {
    for (TwoOpIndexStructure tois: p.getOperations()) {
      indexOperation(tois);
    }
    for (Plan child: p.getChildren()) {
      indexPlan(child);
    }
  }
  
  /**
   * @param modelVar - the node to add to this graphTMP
   */
  public void addModelVar(Node modelVar) {
    modelVars.put(modelVar.getHasName(), modelVar);
    getHasDomainNodes().addNode(modelVar);
  }
  
  public Node getModelVar(String name) {
    return modelVars.get(name);
  }
  
  public void addDistribution(Distribution dist) {
    ArrayList<Distribution> dists = new ArrayList<Distribution>(getHasDistributions());
    dists.add(dist);
    setHasDistributions(dists);
  }
  
  /**
   * @obsolete use evalPlan on the Query object directly, this is obsolete
   */
  public void evalQuery(Query q) {
    ArrayList<Distribution> remDists= new ArrayList<Distribution>(getHasDistributions());
    NodeList elimNodes = new NodeList(false);
    for (Distribution dist: getHasDistributions()) {
      elimNodes = elimNodes.union(dist.getHasDomainNodes()).union(dist.getHasRangeNodes());
    }
    elimNodes = elimNodes.setDifference(q.getHasTarget());
    /* obviously we need a better elim order than below */
    for (Node node: elimNodes.getHasNodes()) {
      ArrayList <Distribution> nodeDists = new ArrayList<Distribution> ();
      for (Distribution dist: remDists) {
        if (dist.getHasDomainNodes().member(node) || dist.getHasRangeNodes().member(node)) {
          nodeDists.add(dist);
        }
      }

      /* again dumb, but may not matter much */
      while (nodeDists.size() > 1) {
        Distribution d1 = nodeDists.get(0);
        Distribution d2 = nodeDists.get(1);
        NodeList localTarget = d1.getHasDomainNodes().union(d1.getHasRangeNodes().union(d2.getHasDomainNodes().union(d2.getHasRangeNodes())));
        localTarget = localTarget.removeNode(node);
        TwoOpIndexStructure s1 = d1.makeMultiplyIndexStructure(d2, localTarget);
        Distribution r = d1.doMultiply(s1);
        nodeDists.remove(d1);
        nodeDists.remove(d2);
        nodeDists.add(r);
        remDists.remove(d1);
        remDists.remove(d2);
        remDists.add(r);
      }
      
      /* now cover case where only one dist, so we just need to marginalize */
      if (nodeDists.get(0).getHasDomainNodes().member(node)) {
        Distribution d1 = nodeDists.get(0);
        NodeList localTarget = d1.getHasDomainNodes().union(d1.getHasRangeNodes());
        localTarget = localTarget.removeNode(node);
        TwoOpIndexStructure s1 = d1.makeMultiplyIndexStructure(ONE, localTarget);
        Distribution r = d1.doMultiply(s1);
        nodeDists.remove(d1);
        nodeDists.add(r);
        remDists.remove(d1);
        remDists.add(r);
      }
    }
    q.setHasResult(remDists.get(0));
  }
  
  public Mapper newMapper(Collector source, Collector sink) {
    logWriter.error("should have been overridden");
    return null;
  }
  
  public void loadJSON(BufferedReader r) {
    logWriter.error("should have been overridden");
  };
  
  public void runTest(String testFileName) {};
  public void updateGraphFromCoViewLog(String logFileName, String graphFileName, double threshold) {
    logWriter.error("should have been overridden");
  };
  public void pruneCorefsLikelihoodHoeffding(double threshold, double countThreshold) {
    logWriter.error("should have been overridden");
  };
  public JSONObject persistGraph(Writer out, JSONObject result) {
    logWriter.error("should have been overridden");
    return null;
  };
  

  /**
   * 
   * @param rootId - external questionId the root of a possible hierarchical question
   * @param path - a path if hierarchical, or null or empty list if not
   * @return
   */
  public Node resolvePath(Long rootId, List<Integer> path) {
    // find question and assumptions
    StringBuffer nameBuf = new StringBuffer();
    nameBuf.append("Q"+rootId);
    if (path != null) {
      for (Integer step: path) {
        nameBuf.append("."+step);
      }
    }
    String nodeName = nameBuf.toString();
    Node node = getModelVar(nodeName);
    if (node == null) {
      throw new IllegalArgumentException("unknown question "+nodeName);
    }
    return node;
  }
  
  public ArrayList<Node> resolvePaths(Long questionId, List<Integer> start, List<Integer> end) {
    ArrayList<Node> nodes = new ArrayList<Node>();
    return nodes;
  }
  
  
  
  /**
   * 
   * @param d - a distribution, of assumed form ({R1}|{C1,...});
   * @param conditioningStates - a set of state values, one for each of C1, ...
   *          note these are assumed to be in Node order
   * @return
   */
  public DistributionRow setConditionalDistributionRows(Distribution d, int [] allConditioningStates, double [] newValues) {
    if (allConditioningStates.length != d.getHasDomainNodes().size()) {
      throw new IllegalArgumentException("domain / assumed-states length mismatch");
    }
    DistributionRow row = setConditionalDistributionRowsAux(d, allConditioningStates, newValues, 0, new int[allConditioningStates.length]);
    return row;
  }

  protected DistributionRow setConditionalDistributionRowsAux(Distribution d, int [] allConditioningStates, double [] newValues, int nextIndex, int [] indexVector) {
    DistributionRow conditionedRow = null;
      if (nextIndex >= allConditioningStates.length) {
        // create and add row
        DistributionRow row = 
            DistributionRow.newRow(indexVector, makeDefaultConditionedCaseProbs(newValues));
        if (conditionedCaseP(allConditioningStates, indexVector)) {
          conditionedRow = row;
          row.setRow(newValues);
        }
        d.add1HasDistributionRows(row);
        return conditionedRow;
      } else {
        Node cNode = d.getHasDomainNodes().get(nextIndex);
        for (int stateIndex  = 0; stateIndex < cNode.getDomain().length; stateIndex++) {
          indexVector[nextIndex] = stateIndex;
          DistributionRow dr = setConditionalDistributionRowsAux(d, allConditioningStates, newValues, nextIndex+1, indexVector);
          if (dr != null) {
            conditionedRow = dr;
          }
        }
      }
      return conditionedRow;
    }
  
  protected double [] makeDefaultConditionedCaseProbs(double [] newValues) {
    double [] row = new double[newValues.length];
    for (int i = 0; i < row.length; i++) {
      row[i] = 1.0/row.length;
    }
    return row;
  }
  
  protected boolean conditionedCaseP(int [] allConditioningStates, int [] indexVector) {
    for (int index = 0; index < indexVector.length; index++) {
      if (indexVector[index] != allConditioningStates[index]) {
        return false;
      }
    }
    return true;
  }
  
  protected double [] makeConditionedCaseProbs(double [] currentPosterior, List<Float> newValues, boolean conditionedCaseP) {
    double [] row = new double[currentPosterior.length];
    if (conditionedCaseP) {
      for (int i = 0; i < row.length; i++) {
        row[i] = newValues.get(i);
      }
      
    } else {
      for (int i = 0; i < row.length; i++) {
        row[i] = 1.0/row.length;
      }
    }
    return row;
  }
  
  
  protected double [] computeTradeLikelihood(double [] conditionalPrior, double [] assertion) throws IllegalArgumentException {
    double [] trade = new double[conditionalPrior.length];
    for (int i = 0; i < conditionalPrior.length; i++) {
      if (conditionalPrior[i] <= 0.0) {
        throw new IllegalArgumentException ("can't raise 0 prior");
      }
      trade[i] = assertion[i]/conditionalPrior[i];
    }
    return trade;
  }
  
  protected Distribution addAssertion(Node question, NodeList domain) {
    
    //Node cq = makeNode("cq"+Node.nextOrder(), question.getDomain(), null, GraphTMP.NO_DISTRIBUTION, GraphTMP.NO_QUERY);
    NodeList range = new NodeList();
    range.addNode(question);

    // compute current conditional marginal based on assumed states
    // note we don't index this plan, since it is a one-time query
    NodeList all = range.union(domain);
    Query q = makeQuery(all);
    q.buildReducePlan(all);
    // Query.COUNT++;
    // q.invalidate(); // force re-evaluation
    q.evalPlan();
    // now convert joint to conditional
    
    Distribution conditionedJoint = q.getHasResult();
    conditionedJoint.normalize();
    Distribution conditional = conditionedJoint.computeConditional(domain, range);
 
    // add tradeNode(cp) to graph
    // invalidate question and assumption(s) queries
    // rebuild question and assumption queries
    // return null
    
    return conditional;
  }
  

//DL static GraphTMP currentGraph = null;
  /**
   * stub initialize method for SciCast API
   * @return true
   * in a subsequent release this may return the GraphTMP, allowing for multiple simultaneous reasoning contexts
   */
  public static GraphTMP initialize() {
    currentGraph = new GraphTMP();
    currentGraph.setHasInstances(new ArrayList<Instance> ());
    currentGraph.setHasDomainNodes(new NodeList ());
    return currentGraph;
  }

  /**
   * 
   * @return - a long, a transaction id. This is always -1 for now, ignored for initial delivery
   * we will build a queue of operations to be executed on the commit below...
   */
  public long startNetworkActions() {
    return -1;
  }
  
  /**
   * 
   * @param transactionKey
   * @return
   * @throws IllegalArgumentException
   * Ignored for initial delivery
   */
  public synchronized boolean commitNetworkActions(long transactionKey) throws IllegalArgumentException {
    return true;
  }
  
  /**
   * 
   * @param transactionKey
   * @param occurredWhen
   * @param questionId
   * @param numberStates
   * @param initProbs
   * @param structure
   * @return
   * @throws IllegalArgumentException
   * This function creates a new question in the network. It is not required for the MarkovEngine to know the specific strings assigned to the states or question descriptions. 
   */
  public synchronized boolean addQuestion(Long transactionKey, Date occurredWhen, long questionId, int numberStates, List<Float> initProbs, String structure ) 
      throws IllegalArgumentException {
      
    if (initProbs == null) {initProbs = new ArrayList<Float>();}
    if (numberStates != initProbs.size()) {
      throw new IllegalArgumentException("marginal doesn't match number of states");
    }
    
    // build node
    String varName = "Q"+questionId;
    if (getModelVar(varName) != null) {
      throw new IllegalArgumentException("question already exists");
    }
    // set node domain
    String [] domain = new  String [numberStates];
    for (int i = 0; i< numberStates; i++) {
      domain[i] = "S"+i;
    }
    
    // set prior
    double [] prior = new double [initProbs.size()];
    for (int i = 0; i < initProbs.size(); i++) {
      prior[i] = initProbs.get(i);
    }

    Node node = makeNode(varName, domain, prior, GraphTMP.BUILD_DISTRIBUTION, GraphTMP.BUILD_QUERY);
    // Query.COUNT++;

    return true;
  }
  
  
  /**
   * 
   * @param transactionKey
   * @param occurredWhen
   * @param questionId
   * @param initProbs
   * @param structure - a GraphTMP.HierarchicalDomain object describing domain tree structure
   * @return
   * @throws IllegalArgumentException
   * This function creates a new Hierarchically structured question in the network. 
   * It is not required for the MarkovEngine to know the specific strings assigned to the states or question descriptions. 
   */
  public synchronized boolean addHierarchicalQuestion(Long transactionKey, Date occurredWhen, long questionId, List<Float> prior, HierarchicalDomain domainStructure) 
      throws IllegalArgumentException {
      
    if (domainStructure == null) {
      throw new IllegalArgumentException("domain missing");
    }
    double [] priorAsArray = new double[2];
    priorAsArray[0] = prior.get(0);
    priorAsArray[1] = prior.get(1);
    Node node = addHierarchicalNode(questionId, domainStructure, priorAsArray, "");
    buildHierarchicalPosteriors(node, domainStructure);

    return true;
  }
  
  /**
   * addHierarchicalNode - add a node to the domain model for a hierarchical question
   * @param questionId
   * @param domainStructure - the structure under this node
   * @param prior - for propagation down to roots
   * @param suffix - path from root, for name creation.
   * @return
   * @throws IllegalArgumentException
   * This function creates a new Hierarchically structured question node in the network. 
   * It is not required for the MarkovEngine to know the specific strings assigned to the states or question descriptions. 
   */
  protected HierarchicalNode addHierarchicalNode(Long questionId, GraphTMP.HierarchicalDomain domainStructure, double [] prior, String suffix) 
      throws IllegalArgumentException {
      
    if (domainStructure.numberOfChildren() == 0) {
      /* at bottom, create leaves */
      // build node
      String varName = "Q"+questionId+suffix;
      if (getModelVar(varName) != null) {
        throw new IllegalArgumentException("question already exists");
      }
      // set node domain
      String [] domain = new  String [2];
      domain[0] = "F";
      domain[1] = "T";

      HierarchicalNode node = makeHierarchicalNode(varName, domain, prior, GraphTMP.BUILD_DISTRIBUTION, GraphTMP.BUILD_QUERY);
      node.setCachedPosterior(node.getHasDistributions().get(0));
      return node;
      
    } else {
      /* recurse down to reach bottom so we can start building up */
      ArrayList<HierarchicalNode> parents = new ArrayList<HierarchicalNode>();
      double [] childPrior = new double [2];
      childPrior[1] = prior[1]/domainStructure.numberOfChildren();
      childPrior[0] = 1.0-childPrior[1];

      for (int p = 0; p < domainStructure.numberOfChildren(); p++) {
        parents.add(addHierarchicalNode(questionId, domainStructure.child(p), childPrior, suffix+'.'+p));
      }
      
      /* build noisy or structure 
       * As per Takikawa and D'Ambrosio, consists of an E' which has a multiplicative factoring
       * as the sole parent of the node at this level */
      Node ePrime = makeEPrimeNode(""+questionId, suffix, prior[1], parents);
      String varName = "Q"+questionId+suffix;
      if (getModelVar(varName) != null) {
        throw new IllegalArgumentException("question already exists");
      }
      // set node domain
      String [] domain = new  String [2];
      domain[0] = "F";
      domain[1] = "T";

      HierarchicalNode n = new HierarchicalNode();
      n.setHasName(varName);
      n.setOrder(Node.nextOrder());
      n.setId(n.getOrder());
      n.setEvidence(-1);
      n.setDomain(domain);
      n.setDown(parents);

      // add this subNode to query set on graph
      // note - when we trade on a node, plan will be reconstructed.
      // we should reuse and invalidate same query object. But what about all the indexed plan objects? delete from indices?
      addModelVar(n);    
      // Query.COUNT++;
      Distribution dist = makeE_EPrimeDistribution(n, ePrime);

      return n;
    }

  }
  
  /**
   * createUnionNode - creates a noisy-or node for a set of HierarchicalNodes, so we can compute or trade on the disjunction
   * @param questionId
   * @param domainStructure - the structure under this node
   * @param prior - for propagation down to roots
   * @param suffix - path from root, for name creation.
   * @return
   * @throws IllegalArgumentException
   * This function creates a new Hierarchically structured question node in the network. 
   * It is not required for the MarkovEngine to know the specific strings assigned to the states or question descriptions. 
   */
  protected HierarchicalNode createUnionNode(ArrayList<HierarchicalNode> parents) 
      throws IllegalArgumentException {

    if (parents == null || parents.size() == 0) {
      throw new IllegalArgumentException("No children!");
    }
    /* at bottom, create leaves */
    // build node
    String varName = "U"+Node.nextOrder();
    if (getModelVar(varName) != null) {
      throw new IllegalArgumentException("dummy node name already exists");
    }
    // set node domain
    String [] domain = new  String [2];
    domain[0] = "F";
    domain[1] = "T";

    /* build noisy or structure 
     * As per Takikawa and D'Ambrosio, consists of an E' which has a multiplicative factoring
     * as the sole parent of the node at this level */
    Node ePrime = makeEPrimeNode(varName, "", 1.0, parents);
    HierarchicalNode n = new HierarchicalNode();
    n.setHasName(varName);
    n.setOrder(Node.nextOrder());
    n.setId(n.getOrder());
    n.setEvidence(-1);
    n.setDomain(domain);
    n.setDown(parents);

    // add this subNode to query set on graph
    // note - when we trade on a node, plan will be reconstructed.
    // we should reuse and invalidate same query object. But what about all the indexed plan objects? delete from indices?
    addModelVar(n);    
    // Query.COUNT++;
    Distribution dist = makeE_EPrimeDistribution(n, ePrime);

    return n;
  }
  
  /**
   * 
   * @param transactionKey
   * @param occurredWhen
   * @param childQuestionId
   * @param parentQuestionIds
   * @param cpd
   * @return
   * @throws IllegalArgumentException
   * The semantics of the arguments and the returned value are the same, except for the argument cpd, 
   * which will be simply ignored (considered as if passing null) by implementation of the Y2 interface 
   * if it is configured to change the shared Bayes net structure without re-running the trades all again.
   * 
   * This is just information for static factor-structuring, not used in current implementation
   */
  public boolean addQuestionAssumption(Long transactionKey, Date occurredWhen, long childQuestionId, List<Long> parentQuestionIds,  List<Float> cpd) 
      throws IllegalArgumentException {
    return true;
  }

  /**
   * 
   * @param transactionKey
   * @param occurredWhen
   * @param questionId
   * @param targetPath - path if questionId is hierarchical
   * @param referencePaths - paths for hierarchical assumptionIds
   * @param newValues
   * @param assumptionIds
   * @param assumedStates
   * @return
   * @throws IllegalArgumentException
   * If trade is not successful will return null. 
   * If trade was successful, but a preview could not be estimated 
   * (e.g. when adding new nodes and performing trades in such node within the same transaction, 
   * or for some implementation issues that may prevent the preview), then it will return an empty list.
   * If transactionKey is null, then the action will be committed immediately.
   * 
   * 
   */
  public double [] addTrade(Long transactionKey, Date occurredWhen, long questionId, List<Integer> targetPath, List<List<Integer>> referencePaths, 
      List<Float> newValues, List<Long> assumptionIds, List<Integer> assumedStates) throws IllegalArgumentException {
    // find question and assumptions
    // build conditioned question node
    // compute current conditional marginal based on assumed states
    // newValues/currentCM -> conditionals for new node
    // build new "trade" node
    // build distribution for trade node
    // add distribution for trade node
    // add tradeNode to graph
    // invalidate question and assumption(s) queries
    // rebuild question and assumption queries
    // return tradeNode prior
    
    if (targetPath == null) {targetPath = new ArrayList<Integer>();}
    if (referencePaths != null) {
      if (referencePaths.size() != assumptionIds.size()) {
        throw new IllegalArgumentException ("referencePaths must be null or same size as assumptionIds");
      }
    }
    if (newValues == null) {newValues = new ArrayList<Float>();}
    if (assumptionIds == null) {assumptionIds = new ArrayList<Long>();}
    if (assumedStates == null) {assumedStates = new ArrayList<Integer>();}

    // find question and assumptions
    StringBuffer nameBuf = new StringBuffer();
    nameBuf.append("Q"+questionId);
    for (Integer step: targetPath) {
      nameBuf.append("."+step);
    }
    String varName = nameBuf.toString();
    Node question = resolvePath(questionId, targetPath);
    if (question == null) {
      throw new IllegalArgumentException("unknown question "+varName);
    }
    nodeConnectedDistributions.remove(question);
    NodeList assumptions = new NodeList();
    HashMap <Node, Integer> assumptionMap = new HashMap<Node, Integer> ();
    int [] conditionStateIndecies = new int[assumptionIds.size()];
    int index = 0;
    for (int i = 0; i < assumptionIds.size(); i++) {
      Long assumptionId = assumptionIds.get(i);
      List<Integer> referencePath = null;
      if (referencePaths != null) {
        referencePath = referencePaths.get(i);
      }
      Node n = resolvePath(assumptionId, referencePath);
      nodeConnectedDistributions.remove(n);
      assumptions.addNode(n);
      assumptionMap.put(n, index);
      index++;
    }
    for (int s = 0; s < assumptions.size(); s++) {
      conditionStateIndecies[s] = assumedStates.get(assumptionMap.get(assumptions.get(s)));
    }
    if (assumedStates != null) {
      for (int s = 0; s < assumptions.size(); s++) {
      if (conditionStateIndecies[s] >= assumptions.get(s).getDomain().length) {
        throw new IllegalArgumentException("Invalid assumedState index on "+s+" "+listToString(assumptionIds, conditionStateIndecies));
        }      
      }
    }

    if (assumedStates == null) {
      assumedStates = new ArrayList<Integer>();
    }
    
    double [] assertion = new double[newValues.size()];

    // newValues/currentConditionalRow -> conditionals for conditionedRow in cp's dist
    for (int i = 0; i < newValues.size(); i++) {
      assertion[i] = newValues.get(i);
    }
    
    double [] nullAssertion = new double[newValues.size()];
    for (int i = 0; i < newValues.size(); i++) {
      nullAssertion[i] = 1.0/nullAssertion.length;
    }
    
    // look for an existing likelihood of the right conformation
    NodeList allNodes = new NodeList(assumptions);
    allNodes.addNode(question);
    Distribution existingLikelihood = null;
    for (Distribution d: question.getHasDistributions()) {
      if (d.allNodesMatch(allNodes) && d.getHasRangeNodes().size() == 0) {
        existingLikelihood = d;
      }
    }
    Distribution conditional = addAssertion(question, assumptions);
     // pick out posterior row
    DistributionRow conditionalRow = conditional.getRow(conditionStateIndecies);
    double [] trade = computeTradeLikelihood(conditionalRow.getRow(), assertion);

    Distribution likelihood = null;
    if (existingLikelihood != null) {
      // update elements of existing likelihood corresponding to assertion
      likelihood = existingLikelihood;
      NodeList domain = likelihood.getHasDomainNodes();
      boolean tradeNodeSeen = false;
      conditionStateIndecies = new int[domain.size()];
      int tradeNodeIndex = -1;
      for (int s = 0; s < domain.size(); s++) {
        if (domain.get(s) == question) {
          conditionStateIndecies[s] = -1;
          tradeNodeSeen = true;
          tradeNodeIndex = s;
        } else {
          conditionStateIndecies[s] = assumedStates.get(assumptionMap.get(assumptions.get(tradeNodeSeen ? s-1 : s)));
        }
      }
     likelihood.setTradeLikelihood(question, tradeNodeIndex, trade, conditionStateIndecies);
      d1Operations.remove(likelihood);
      d2Operations.remove(likelihood);
    } else {
      // make conditional part of dependency structure as a likelihood, but first set to uninformative
      // note this will actually a likelihood, hence default values of 1.0
      for (DistributionRow row: conditional.getHasDistributionRows()) {
        for (int i = 0; i < row.row.length; i++) {
          row.row[i] = 1.0;
        }
      }
      conditionalRow.setRow(trade);
      //  now convert form to likelihood
      likelihood = conditional.convertConditionalToLikelihood();
      if (likelihood.getAllNodes().size() != (likelihood.getHasDomainNodes().union(likelihood.getHasRangeNodes()).size()) ){
        logWriter.error("bad AllNodes");
      }

      getHasDistributions().add(likelihood);
      question.add1HasDistributions(likelihood);
    }
    if (likelihood != existingLikelihood) {
      nodeConnectedDistributions.remove(question);
      ArrayList<Distribution> dl = getConnectedDistributions(likelihood.getAllNodes());
      for (Distribution d: dl) {
        for (int n = 0; n <  d.getAllNodes().size(); n++) {
          Node c = d.getAllNodes().get(n);
          if (c != question && likelihood.getAllNodes().member(c) && !c.getHasDistributions().contains(likelihood)) {
            c.add1HasDistributions(likelihood);
          }
          nodeConnectedDistributions.remove(c);
        }
      }
    }

    return trade;
  }

  /**
   * 
   * addHierarchicalQuestionTrade
   * 
   * @param transactionKey
   * @param occurredWhen
   * @param questionId
   * @param startTargetPath - a path to the first of a set of nodes that are the target
   * @param endTargetPath - a path to the last of a contiguous set of nodes that are the target
   * @param referencePath - deprecated, used to be the root for conditioning within a tree
   * @param newValues
   * @param assumptionIds - a list of questions we are conditioning on
   * @param assumptionPaths - the paths for each conditioning question (0 length path for non-hierarchical conditioning questions)
   * @param assumedStates
   * @return
   * @throws IllegalArgumentException
   * If trade is not successful will return null. 
   * If trade was successful, but a preview could not be estimated 
   * (e.g. when adding new nodes and performing trades in such node within the same transaction, 
   * or for some implementation issues that may prevent the preview), then it will return an empty list.
   * If transactionKey is null, then the action will be committed immediately.
   * 
   * note - assumptionPaths not supported in this release.
   */
  public double [] addHierarchicalQuestionTrade(Long transactionKey, Date occurredWhen, long questionId, List<Integer> startTargetPath, List<Integer> endTargetPath, 
      List<Integer> referencePath, 
      List<Float> newValues, List<Long> assumptionIds, List<List<Integer>> assumptionPaths, List<Integer> assumedStates) 
          throws IllegalArgumentException {
    
    if (startTargetPath == null) {
      throw new IllegalArgumentException("start Target Path cannot be null "+questionId);
    }
    if (endTargetPath == null) {
      throw new IllegalArgumentException("end Target Path cannot be null "+questionId);
    }
    if (newValues == null) {newValues = new ArrayList<Float>();}
    if (assumptionIds == null) {assumptionIds = new ArrayList<Long>();}
    if (assumedStates == null) {assumedStates = new ArrayList<Integer>();}

    // find question and assumptions
    Node root = resolvePath(questionId, new ArrayList<Integer>());
    if (!root.getClass().equals(HierarchicalNode.class)) {
      throw new IllegalArgumentException("Question is not hierarchical "+questionId);
    } 
    NodeList ql = new NodeList();
    ArrayList<HierarchicalNode> cover = Path.nodeCover((HierarchicalNode)root, startTargetPath, endTargetPath);
    for (HierarchicalNode coverNode: cover) {
      ql.add1HasNodes(coverNode);
      nodeConnectedDistributions.remove(coverNode);
    }

    HierarchicalNode question = createUnionNode(cover);
    NodeList assumptions = new NodeList();
    HashMap <Node, Integer> assumptionMap = new HashMap<Node, Integer> ();
    int [] conditionStateIndecies = new int[assumptionIds.size()];
    int index = 0;
    for (int i = 0; i < assumptionIds.size(); i++) {
      Long assumptionId = assumptionIds.get(i);
      List<Integer> assumptionPath = null;
      if (assumptionPaths != null) {
        assumptionPath = assumptionPaths.get(i);
      }
      Node n = resolvePath(assumptionId, referencePath);
      nodeConnectedDistributions.remove(n);
      assumptions.addNode(n);
      assumptionMap.put(n, index);
      index++;
    }
    for (int s = 0; s < assumptions.size(); s++) {
      conditionStateIndecies[s] = assumedStates.get(assumptionMap.get(assumptions.get(s)));
    }
    if (assumedStates != null) {
      for (int s = 0; s < assumptions.size(); s++) {
      if (conditionStateIndecies[s] >= assumptions.get(s).getDomain().length) {
        throw new IllegalArgumentException("Invalid assumedState index on "+s+" "+listToString(assumptionIds, conditionStateIndecies));
        }      
      }
    }

    if (assumedStates == null) {
      assumedStates = new ArrayList<Integer>();
    }
    
    double [] assertion = new double[newValues.size()];

    // newValues/currentConditionalRow -> conditionals for conditionedRow in cp's dist
    for (int i = 0; i < newValues.size(); i++) {
      assertion[i] = newValues.get(i);
    }
    
    double [] nullAssertion = new double[newValues.size()];
    for (int i = 0; i < newValues.size(); i++) {
      nullAssertion[i] = 1.0/nullAssertion.length;
    }
    
    // look for an existing likelihood of the right conformation
    NodeList allNodes = new NodeList(assumptions);
    allNodes.addNode(question);
    Distribution existingLikelihood = null;
    for (Distribution d: question.getHasDistributions()) {
      if (d.allNodesMatch(allNodes) && d.getHasRangeNodes().size() == 0) {
        existingLikelihood = d;
      }
    }
    Distribution conditional = addAssertion(question, assumptions);
     // pick out posterior row
    DistributionRow conditionalRow = conditional.getRow(conditionStateIndecies);
    double [] trade = computeTradeLikelihood(conditionalRow.getRow(), assertion);

    Distribution likelihood = null;
    if (existingLikelihood != null) {
      // update elements of existing likelihood corresponding to assertion
      likelihood = existingLikelihood;
      NodeList domain = likelihood.getHasDomainNodes();
      boolean tradeNodeSeen = false;
      conditionStateIndecies = new int[domain.size()];
      int tradeNodeIndex = -1;
      for (int s = 0; s < domain.size(); s++) {
        if (domain.get(s) == question) {
          conditionStateIndecies[s] = -1;
          tradeNodeSeen = true;
          tradeNodeIndex = s;
        } else {
          conditionStateIndecies[s] = assumedStates.get(assumptionMap.get(assumptions.get(tradeNodeSeen ? s-1 : s)));
        }
      }
     likelihood.setTradeLikelihood(question, tradeNodeIndex, trade, conditionStateIndecies);
      d1Operations.remove(likelihood);
      d2Operations.remove(likelihood);
    } else {
      // make conditional part of dependency structure as a likelihood, but first set to uninformative
      // note this will actually a likelihood, hence default values of 1.0
      for (DistributionRow row: conditional.getHasDistributionRows()) {
        for (int i = 0; i < row.row.length; i++) {
          row.row[i] = 1.0;
        }
      }
      conditionalRow.setRow(trade);
      //  now convert form to likelihood
      likelihood = conditional.convertConditionalToLikelihood();
      if (likelihood.getAllNodes().size() != (likelihood.getHasDomainNodes().union(likelihood.getHasRangeNodes()).size()) ){
        logWriter.error("bad AllNodes");
      }

      getHasDistributions().add(likelihood);
      question.add1HasDistributions(likelihood);
    }
    if (likelihood != existingLikelihood) {
      nodeConnectedDistributions.remove(question);
      NodeList nl = likelihood.getHasDomainNodes();
      for (int ci = 0; ci < nl.size(); ci++) {
        Node c = nl.get(ci);
        if (c != question && likelihood.getAllNodes().member(c) && !c.getHasDistributions().contains(likelihood)) {
          c.add1HasDistributions(likelihood);
        }
        nodeConnectedDistributions.remove(c);
      }
    }
    return trade;
  }

  /**
   * 
   * @param transactionKey
   * @param occurredWhen
   * @param questionID
   * @param settledState
   * @return
   * @throws IllegalArgumentException
   * This function will settle a specific question (and its choices - states) to the probability specified in settlement, which specifies the value (probability) to settle. 
   */
  public boolean resolveQuestion(Long transactionKey, Date occurredWhen, long questionId, List<Integer> targetPath, int settledState) throws IllegalArgumentException {

    // find question and assumptions
    Node question = resolvePath(questionId, targetPath);
    Node [] assumptions = new Node[0];    
    int [] conditioningCase = new int[0];    
    double [] assertion = new double[question.getDomain().length];

    // newValues/currentConditionalRow -> conditionals for conditionedRow in cp's dist
    for (int i = 0; i < assertion.length; i++) {
      if (i == settledState) {
        assertion[i] = 1.0;
      } else {
        assertion[i] = 0.0;
      }
    }    
    // Query.COUNT++;
    // pick out posterior row
    Distribution conditional = addAssertion(question, new NodeList());
    DistributionRow resolvedRow = conditional.getRow(conditioningCase);
    resolvedRow.setRow(assertion);
    Distribution likelihood = conditional.convertConditionalToLikelihood();
    getHasDistributions().add(likelihood);
    question.add1HasDistributions(likelihood);
    nodeConnectedDistributions.remove(question);
    ArrayList<Distribution> dl = getConnectedDistributions(likelihood.getAllNodes());
    for (Distribution d: dl) {
      for (int n = 0; n <  d.getAllNodes().size(); n++) {
        Node c = d.getAllNodes().get(n);
        if (c != question && likelihood.getAllNodes().member(c) && !c.getHasDistributions().contains(likelihood)) {
          c.add1HasDistributions(likelihood);
        }
        nodeConnectedDistributions.remove(c);
      }
    }
    // Query.COUNT++;

    return true;
  }
  
  /**
   * 
   * @param transactionKey
   * @param occurredWhen
   * @param questionID
   * @param settlement - a list of floats, the distribution of the settlement across the choices
   * @return
   * @throws IllegalArgumentException
   */
  public synchronized boolean resolveQuestion(Long transactionKey, Date occurredWhen, long questionId, List<Float> settlement) 
      throws IllegalArgumentException {

    Node question = getModelVar("Q"+questionId);
    if (question == null) {
      throw new IllegalArgumentException("unknown question "+questionId);
    }

    if (question.getDomain().length != settlement.size()) {
      throw new IllegalArgumentException("settlement element count doesn't match question domain size");
    }
    double [] assertion = new double[question.getDomain().length];

    // newValues/currentConditionalRow -> conditionals for conditionedRow in cp's dist
    for (int i = 0; i < assertion.length; i++) {
        assertion[i] = settlement.get(i);
    }    
    // Query.COUNT++; // global cache invalidation for now
    Distribution conditional = addAssertion(question, new NodeList());
    DistributionRow resolvedRow = conditional.getHasDistributionRows().get(0);
    resolvedRow.setRow(assertion);
    Distribution likelihood = conditional.convertConditionalToLikelihood();
    getHasDistributions().add(likelihood);
    for (int n=0; n < likelihood.getAllNodes().size(); n++) {
      Node node = likelihood.getAllNodes().get(n);
      if (node != question && likelihood.getAllNodes().member(node) && !node.getHasDistributions().contains(likelihood)) {
      node.add1HasDistributions(likelihood);
      }
      nodeConnectedDistributions.remove(node);
    }
    // Query.COUNT++;

    return true;
  }
  
  
  /**
   * resolveHierarchicalQuestion resolve a hierarchical question to a specific set
   * @param transactionKey
   * @param occurredWhen
   * @param questionID
   * @param targetPaths - the set of nodes within the domain resolved to
   * @param referencePaths - null for now
   * @param settlements - the posterior for each node in settlement
   * @return
   * @throws IllegalArgumentException
   * This method operates identical to resolveQuestion but allows a list of targetPaths and referencePaths, 
   * each specifying a list of settlement values for use in a value tree.
   */
  public synchronized boolean resolveHierarchicalQuestion(Long transactionKey, Date occurredWhen, long questionId, List<List<Integer>> targetPaths,
      List<Integer> referencePaths, List<List<Float>> settlements) throws IllegalArgumentException {
    
    Node question = getModelVar("Q"+questionId);
    if (question == null) {
      throw new IllegalArgumentException("unknown question "+questionId);
    }

    if (targetPaths.size() != settlements.size()) {
      throw new IllegalArgumentException("settlements size doesn't match settlement paths size");
    }
    
    for (int n = 0; n < targetPaths.size(); n++) {
      List<Integer> path = targetPaths.get(n);
      List<Float> settlement = settlements.get(n);
      Node node = resolvePath(questionId, path);
      if (node == null) {
        throw new IllegalArgumentException("node not found for path");
      }
      if (node.getDomain().length != settlement.size()) {
        throw new IllegalArgumentException("settlement size / node domain mismatch");
      }
      
      double [] assertion = new double[question.getDomain().length];

        // newValues/currentConditionalRow -> conditionals for conditionedRow in cp's dist
        for (int i = 0; i < assertion.length; i++) {
          assertion[i] = settlement.get(i);
        }    
        // Query.COUNT++; // global cache invalidation for now
        Distribution conditional = addAssertion(question, new NodeList());
        DistributionRow resolvedRow = conditional.getHasDistributionRows().get(0);
        resolvedRow.setRow(assertion);
        Distribution likelihood = conditional.convertConditionalToLikelihood();
        getHasDistributions().add(likelihood);
        for (int l=0; l < likelihood.getAllNodes().size(); l++) {
          Node lNode = likelihood.getAllNodes().get(l);
          if (lNode != question && likelihood.getAllNodes().member(lNode) && !lNode.getHasDistributions().contains(likelihood)) {
            lNode.add1HasDistributions(likelihood);
          }
          nodeConnectedDistributions.remove(lNode);
        }
    }
    return true;
  }
  
  /**
   * public List<Float> getProbList(long questionId, List<Integer> startTargetPath,  List<Integer> endTargetPath, List<Integer> referencePath, 
   * List<Long>assumptionIds, List<Integer> assumedStates) 
   * throws IllegalArgumentException
   */
    
    /**
     * 
     * @param questionId
     * @param targetPath - null for initial delivery
     * @param referencePath - null for initial delivery
     * @param assumptionIds
     * @param assumedStates
     * @return
     * @throws Exception 
     */
    public List<Float> getProbList(long questionId, List<Integer> startPath,  List<Integer> endPath,  List<Integer> referencePath, List<Long>assumptionIds, List<Integer> assumedStates) 
        throws IllegalArgumentException {

      ArrayList<Float> posterior = new ArrayList<Float>();
      if (startPath == null) {startPath = new ArrayList<Integer>();}
      if (endPath == null) {endPath = new ArrayList<Integer>();}
      if (referencePath == null) {referencePath = new ArrayList<Integer>();}
      if (assumptionIds == null) {assumptionIds = new ArrayList<Long>();}
      if (assumedStates == null) {assumedStates = new ArrayList<Integer>();}

      Node n = resolvePath(questionId, new ArrayList<Integer>());
      if (!n.getClass().equals(HierarchicalNode.class)) {
        throw new IllegalArgumentException("Question is not hierarchical "+questionId);
      } 
      ArrayList<HierarchicalNode> cover = Path.nodeCover((HierarchicalNode)n, startPath, endPath);

      NodeList ql = new NodeList();
      for (HierarchicalNode coverNode: cover) {
        ql.add1HasNodes(coverNode);
        nodeConnectedDistributions.remove(coverNode);
      }

      NodeList assumptions = new NodeList();
      HashMap <Node, Integer> assumptionMap = new HashMap<Node, Integer> ();
      int [] conditionStateIndecies = new int[assumptionIds.size()];
      int index = 0;
      for (Long id: assumptionIds) {
        n = getModelVar("Q"+id);
        if (n == null) {
          throw new IllegalArgumentException("unknown question Q"+id);
        }
        assumptions.addNode(n);
        assumptionMap.put(n, index);
        index++;
      }
      for (int s = 0; s < assumptions.size(); s++) {
        conditionStateIndecies[s] = assumedStates.get(assumptionMap.get(assumptions.get(s)));
      }
      if (assumedStates != null) {
        for (int s = 0; s < assumptions.size(); s++) {
        if (conditionStateIndecies[s] >= assumptions.get(s).getDomain().length) {
          throw new IllegalArgumentException("Invalid assumedState index on "+s+" "+listToString(assumptionIds, conditionStateIndecies));
          }      
        }
      }

      for (Node queryNode: cover) {
        NodeList target = new NodeList(queryNode);
        target = target.union(assumptions);
        // Query.COUNT++;
        Query q = makeQuery(target);
        q.buildReducePlan(target);
        q.evalPlan();
      
        Distribution joint = q.getHasResult();
        joint.normalize();
        Distribution conditional = joint.computeConditional(assumptions, ql);
        conditional.normalize();
        DistributionRow queryRow = conditional.getRow(conditionStateIndecies);
        if (queryRow == null) {
          throw new IllegalArgumentException("Arithmetic error encountered ");
        } 
         for (int i = 0; i < queryRow.getRow().length; i++) {
          posterior.add(new Float(queryRow.getRow()[i]));
        }
      }
      return posterior;
    }
    
    /**
     * public List<Float> getProbList(long questionId, List<Integer> startTargetPath,  List<Integer> endTargetPath, List<Integer> referencePath, 
     * List<Long>assumptionIds, List<Integer> assumedStates) 
     * throws IllegalArgumentException
     */
      
      /**
       * 
       * @param questionId
       * @param targetPath - null for initial delivery
       * @param referencePath - null for initial delivery
       * @param assumptionIds
       * @param assumedStates
       * @return
       * @throws Exception 
       */
      public List<Float> getProbList(long questionId, List<Integer> targetPath,  List<Integer> referencePath, List<Long>assumptionIds, List<Integer> assumedStates) 
          throws IllegalArgumentException {

        if (targetPath == null) {targetPath = new ArrayList<Integer>();}
        if (referencePath == null) {referencePath = new ArrayList<Integer>();}
        if (assumptionIds == null) {assumptionIds = new ArrayList<Long>();}
        if (assumedStates == null) {assumedStates = new ArrayList<Integer>();}

        NodeList ql = new NodeList();
        Node n = resolvePath(questionId, targetPath);
        ql.add1HasNodes(n);

        NodeList assumptions = new NodeList();
        HashMap <Node, Integer> assumptionMap = new HashMap<Node, Integer> ();
        int [] conditionStateIndecies = new int[assumptionIds.size()];
        int index = 0;
        for (Long id: assumptionIds) {
          n = getModelVar("Q"+id);
          if (n == null) {
            throw new IllegalArgumentException("unknown question Q"+id);
          }
          assumptions.addNode(n);
          assumptionMap.put(n, index);
          index++;
        }
        for (int s = 0; s < assumptions.size(); s++) {
          conditionStateIndecies[s] = assumedStates.get(assumptionMap.get(assumptions.get(s)));
        }
        if (assumedStates != null) {
          for (int s = 0; s < assumptions.size(); s++) {
          if (conditionStateIndecies[s] >= assumptions.get(s).getDomain().length) {
            throw new IllegalArgumentException("Invalid assumedState index on "+s+" "+listToString(assumptionIds, conditionStateIndecies));
            }      
          }
        }

        NodeList target = ql.union(assumptions);
        // Query.COUNT++;
        Query q = makeQuery(target);
        q.buildReducePlan(target);
        q.evalPlan();
        
        Distribution joint = q.getHasResult();
        joint.normalize();
        Distribution conditional = joint.computeConditional(assumptions, ql);
        conditional.normalize();
        DistributionRow queryRow = conditional.getRow(conditionStateIndecies);
        if (queryRow == null) {
          throw new IllegalArgumentException("Arithmetic error encountered ");
        } 
        ArrayList<Float> posterior = new ArrayList<Float>();
        for (int i = 0; i < queryRow.getRow().length; i++) {
          posterior.add(new Float(queryRow.getRow()[i]));
        }
        return posterior;
      }
      
  /**
   * 
   * @param questionIds
   * @param assumptionIds
   * @param assumedStates
   * @return
   * @throws IllegalArgumentException
   * Returns probability across a list of states for all questions given an assumption (set?). 
   * This is equivalent to calling getProbList for all possible questionId, but computation is supposed to be faster than calling getProbList multiple times. 
   * (bda - because of caching/cache-invalidation it may not be)
   * The argument questionIds can be used to filter the results (by passing null, all questions will be returned).

   */
  public Map<Long, ArrayList<Float>> getProbLists(List<Long> questionIds, List<Long>assumptionIds, List<Integer> assumedStates) throws IllegalArgumentException {
    if (assumptionIds == null) {assumptionIds = new ArrayList<Long>();}
    if (assumedStates == null) {assumedStates = new ArrayList<Integer>();}

    return new HashMap<Long, ArrayList<Float>> ();
  }
  
  /**
   * 
   * @param assumptionIds -a set of questionIds
   * @param assumedStates - a set of values for those questions
   * @param targetPaths - for ValueTrees, null for initial delivery
   * @param referencePaths - hmm, redundant, there is only one set of questions, so we need only one set of paths, right?
   * @return
   * @throws IllegalArgumentException
   * Obtains the joint probability of a set of assumptions. 
   * If a ValueTree is used for a given question, then the relevant element in targetPaths and referencePaths will contain a list referencing that object, 
   * otherwise it will contain null. If no questions are ValueTrees then the targetPaths and referencePaths themselves may be null
   */
  public float getJointProbability(List<Long>assumptionIds, List<Integer> assumedStates, List<List<Integer>> targetPaths, List<List<Integer>> referencePaths) throws IllegalArgumentException {

    
    if (targetPaths == null) {targetPaths = new ArrayList<List<Integer>>();}
    if (referencePaths == null) {referencePaths = new ArrayList<List<Integer>>();}
    if (assumptionIds == null) {assumptionIds = new ArrayList<Long>();}
    if (assumedStates == null) {assumedStates = new ArrayList<Integer>();}

    NodeList assumptions = new NodeList();
    HashMap <Node, Integer> assumptionMap = new HashMap<Node, Integer> ();
    int [] conditionStateIndecies = new int[assumptionIds.size()];
    int index = 0;
    for (Long id: assumptionIds) {
      Node n = getModelVar("Q"+id);
      if (n == null) {
        throw new IllegalArgumentException("unknown question Q"+id);
      }
      assumptions.addNode(n);

      assumptionMap.put(n, index);
      index++;
    }
    for (int s = 0; s < assumptions.size(); s++) {
      conditionStateIndecies[s] = assumedStates.get(assumptionMap.get(assumptions.get(s)));
    }
    if (assumedStates != null) {
      for (int s = 0; s < assumptions.size(); s++) {
      if (conditionStateIndecies[s] >= assumptions.get(s).getDomain().length) {
        throw new IllegalArgumentException("Invalid assumedState index on "+s+" "+listToString(assumptionIds, conditionStateIndecies));
        }      
      }
    }

    // Query.COUNT++;
    Query q = makeQuery(assumptions);
    q.buildReducePlan(assumptions);
    q.evalPlan();
    
    Distribution joint = q.getHasResult();
    joint.normalize();
    Distribution inverse = Distribution.ONE().divide(joint, joint.getAllNodes());
    DistributionRow queryRow = inverse.getRow(conditionStateIndecies);
    double inverseProb = queryRow.getRow()[0];
    return new Float(1.0/inverseProb);
  }

  /**
   * 
   * @param questionId - a question we'd like to make conditional trades on
   * @param assumptionIds - a set of base assumptions to be included - not supported in base implementation
   * @return - a list of questionIds that can be used to condition trades on this question
   *   note an issue here - the returned list may itself contain mutex items, and / or may be compatible w only some input assumptionIds
   * @throws IllegalArgumentException
   * Returns a list of any possible assumptions that can be made on the question.
   */
  public List<Long> getPossibleQuestionAssumptions(long questionId, List<Long>assumptionIds) throws IllegalArgumentException {
    return new ArrayList<Long>();
  }

  /**
   * @return
   * Returns a list of any possible assumptions groups (likely cliques in some implementations). 
   * This method is there because of implementation constraints and unfortunately does violate encapsulation.
   */
  public List<List<Long>> getQuestionAssumptionGroups()  {
    return null;
  }
  
  public GraphTMP loadModel (String fileName) {
    try {
      File modelFile = new File(fileName);
      FileInputStream modelStream = new FileInputStream(modelFile);
      InputStreamReader modelISR = new InputStreamReader(modelStream);
      BufferedReader modelBufferedReader = new BufferedReader(modelISR);

      TMPProcessor tmpProcessor = new TMPProcessor(this);
      SAXParserFactory factory = SAXParserFactory.newInstance();
      factory.setNamespaceAware(true);
      factory.setValidating(false);
      SAXParser saxParser = factory.newSAXParser();
      InputSource inSource = new InputSource(modelBufferedReader);

      try {
        while (inSource.getCharacterStream().ready()) {
          saxParser.parse(inSource, tmpProcessor);
        }
      } catch (Exception e) {
        logWriter.error("Error processing source:"+fileName, e);
      }

      return tmpProcessor.getTMP();
    } catch (Exception e) {
      logWriter.error(" Exception processing "+fileName, e);
    }
    return null;
  }
  
  public void displayRows(Distribution dist) {
    for (DomainConcept rowDC: dist.getHasDistributionRows()) {
      DistributionRow row = (DistributionRow) rowDC;
      System.out.print("<dpi indexes=\""+row.indexVectorAsString()+"\"> ");
      for (double p: row.getRow()) {
        System.out.print(" "+p);
      }
      System.out.println("</dpi>");
    }
  }

  protected String listToString(List<Long> ids, List<Integer> states) {
    StringBuffer result = new StringBuffer("");
    for (int i = 0; i < ids.size(); i++) {
      result.append("(");
      result.append(ids.get(i));
      result.append("{"+getModelVar("Q"+ids.get(i)).getHasName()+"}");
      result.append(": ");

      result.append(states.get(i));
      result.append(")");
    }
    return result.toString();
    }

  protected String listToString(List<Long> ids, int [] states) {
    StringBuffer result = new StringBuffer("");
    for (int i = 0; i < ids.size(); i++) {
      result.append("(");
      result.append(ids.get(i));
      result.append("{"+getModelVar("Q"+ids.get(i)).getHasName()+"}");
      result.append(": ");

      result.append(states[i]);
      result.append(")");
    }
    return result.toString();
    }

  /**
   * export - writes an importable current state to writer as ascii json
   * @param writer
   * @return
   */
//DL  static long nextJSONId = 0;
  
//DL  public static long nextJSONId() {
//DL    return ++nextJSONId;
//DL  }
  
  public boolean exportGraph (BufferedWriter writer) {
    /* a map from json object ids to json entries */
    JSONObject json = new JSONObject();
    /* a map from an object to its jsonDictionary id */
    HashMap<Object, Long> objectIndex = new HashMap<Object, Long>();
   
    try {
      exportModelVars(writer, json, objectIndex);// HashMap<String, Node>();
      exportHasDomainNodes(writer, json, objectIndex);//
      exportHasDistributions(writer, json, objectIndex);// ArrayList<Distribution>(0)
    //exportOperations(writer, json, objectIndex);// HashMap<String, TwoOpIndexStructure> ();
    //exportD1Operations(writer, json, objectIndex);// HashMap<Distribution, ArrayList<TwoOpIndexStructure>> ();
    //exportD2Operations(writer, json, objectIndex);// HashMap<Distribution, ArrayList<TwoOpIndexStructure>> ();
    //exportROperations(writer, json, objectIndex);// HashMap<Distribution, ArrayList<TwoOpIndexStructure>> ();
      writer.write(json.toString());
    } catch (JSONException je) {
      logWriter.error("JSON exception writing graph ", je);
      return false;
    } catch (IOException ioe) {
      logWriter.error("IO exception writing graph ", ioe);
      return false;
      
    }
    
    return true;
  }
  
  protected boolean exportModelVars(BufferedWriter writer, JSONObject json, HashMap <Object, Long> objectIndex) throws IOException, JSONException {
    /* Node 1 {"hasName":"Q5","id":"51","order":50,"hasDistributions":"1389043350133325000288,","evidence":-1,"domain":["S0","S1"],"class":"Node"} */
    for (Entry<String, Node> entry: modelVars.entrySet()) {
      if (objectIndex.get(entry.getValue()) != null) {
        continue;
      }
      entry.getValue().export(writer, json, objectIndex);
    }
    return true;
  }
  
  protected boolean exportHasDomainNodes(BufferedWriter writer, JSONObject json, HashMap <Object, Long> objectIndex) throws IOException, JSONException {
    for (int n = 0; n < getHasDomainNodes().size(); n++) {
      Node node = getHasDomainNodes().get(n);
      if (objectIndex.get(node) != null) {
        continue;
      }
      node.export(writer, json, objectIndex);
    }
    return true;
  }

  protected boolean exportHasDistributions(BufferedWriter writer, JSONObject json, HashMap <Object, Long> objectIndex) throws IOException, JSONException {
    for (int d = 0; d < getHasDistributions().size(); d++) {
      Distribution dist = getHasDistributions().get(d);
      if (objectIndex.get(dist) != null) {
        // already exported
        continue;
      }
      dist.export(writer, json, objectIndex);
    }
    return true;
  }
  
  public boolean importGraph (BufferedReader reader) {
    /* a map from json object ids to json entries */
    JSONObject json = new JSONObject();
    /* a map from an object to its jsonDictionary id 
     * one for each type so we can avoid typecasts into and out of maps */
    HashMap<String, Node> nodeMap = new HashMap<String, Node>();
    HashMap<String, Distribution> distributionMap = new HashMap<String, Distribution>();
    HashMap<String, DistributionRow> rowMap = new HashMap<String, DistributionRow>();
   
    try {
      createObjects(reader, nodeMap, distributionMap, rowMap);
      //importModelVars(reader, indexObjects);// HashMap<String, Node>();
      //importHasDomainNodes(reader, indexObjects);//
      //importHasDistributions(reader, indexObjects);// ArrayList<Distribution>(0)
    } catch (JSONException je) {
      logWriter.error("JSON exception reading graph ", je);
      return false;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
    return true;
  }
  
  protected boolean createObjects(BufferedReader reader, 
      HashMap<String, Node> nodeMap, HashMap<String, Distribution> distributionMap, HashMap<String, DistributionRow> rowMap) 
  throws JSONException, IOException {

    String graphAsString = reader.readLine();
    JSONObject dictionary = new JSONObject(graphAsString);
    /* pass one - create objects and populate with primitive values */
    for (Iterator<String> itr =  dictionary.keys(); itr.hasNext(); ) {
      String indexString = itr.next();
      JSONObject object = dictionary.getJSONObject(indexString);
      if (object.optString("class").equalsIgnoreCase("Node")) {
        nodeMap.put(indexString, createNode(object));
      } else if (object.optString("class").equalsIgnoreCase("Distribution")) {
        distributionMap.put(indexString, createDistribution(object));
      }  else if (object.optString("class").equalsIgnoreCase("DistributionRow")) {
        rowMap.put(indexString, createDistributionRow(object));
      }
    }
    /* pass two - fill in object references */
    for (Iterator<String> itr =  dictionary.keys(); itr.hasNext(); ) {
      String indexString = itr.next();
      JSONObject object = dictionary.getJSONObject(indexString);
      if (object.optString("class").equalsIgnoreCase("Node")) {
        populateNode(indexString, object, nodeMap, distributionMap, rowMap);
      } else if (object.optString("class").equalsIgnoreCase("Distribution")) {
        populateDistribution(indexString, object, nodeMap, distributionMap, rowMap);
      }  else if (object.optString("class").equalsIgnoreCase("DistributionRow")) {
        populateRow(indexString, object, nodeMap, distributionMap, rowMap);
      }
    }
    return true;
  }

  protected Node createNode(JSONObject json) throws JSONException {
    Node n = new Node();
    n.setHasName(json.optString("hasName"));
    n.setId(json.optInt("id"));
    n.setOrder(json.optInt("order"));
    n.setEvidence(json.optInt("evidence"));
    JSONArray domain = json.optJSONArray("domain");
    n.setDomain(new String[domain.length()]);
    for (int i = 0; i < domain.length(); i++) {
      n.getDomain()[i] = domain.optString(i);
    }
    JSONArray distributions = json.optJSONArray("hasDistributions");
    n.setHasDistributions(new ArrayList<Distribution>());
    for (int i = 0; i < domain.length(); i++) {
      n.add1HasDistributions(createDistribution(distributions.getJSONObject(i)));
    }
    JSONArray model = json.optJSONArray("model");
     n.setModel(new ArrayList<Distribution>());
    for (int i = 0; i < domain.length(); i++) {
      n.add1Model(createDistribution(distributions.getJSONObject(i)));
    }
    addModelVar(n);
    return n;
  }
  
  protected Distribution createDistribution(JSONObject json) {
    Distribution d = new Distribution();
    d.setHasDistributionRows(new ArrayList<DistributionRow>());
    d.setHasRangeNodes(new NodeList());
    d.setHasDomainNodes(new NodeList());
    addDistribution(d);
    return d;
  }
  
  protected DistributionRow createDistributionRow(JSONObject json) {
    DistributionRow dr = new DistributionRow();
    dr.setId(json.optInt("id"));
    JSONArray indexVector = json.optJSONArray("indexVector");
    dr.setIndexVector(new int[indexVector.length()]);
    for (int i = 0; i < indexVector.length(); i++) {
      dr.getIndexVector()[i] = indexVector.optInt(i);
    }
    JSONArray row = json.optJSONArray("row");
    dr.setRow(new double[row.length()]);
    for (int i = 0; i < row.length(); i++) {
      dr.getRow()[i] = row.optDouble(i);
    }
    return dr;
  }
  
  protected void populateNode (String indexString, JSONObject json,
      HashMap<String, Node> nodeMap, HashMap<String, Distribution> distributionMap, HashMap<String, DistributionRow> rowMap) 
          throws JSONException {
    Node n = nodeMap.get(indexString);
    if (n == null) {
      return;
    }
    JSONArray distributions = json.optJSONArray("hasDistributions");
    if (distributions == null) {
      return;
    }
    for (int i = 0; i < distributions.length(); i++) {
      if (distributionMap.get(distributions.getString(i)) != null) {
        n.add1HasDistributions(distributionMap.get(distributions.getString(i)));
      } else {
        logWriter.error("missing distribution");
      }
    }
  }

  protected void populateDistribution (String indexString, JSONObject json,
      HashMap<String, Node> nodeMap, HashMap<String, Distribution> distributionMap, HashMap<String, DistributionRow> rowMap) 
          throws JSONException {
    Distribution d = distributionMap.get(indexString);
    JSONArray rows = json.optJSONArray("hasDistributionRows");
    for (int i = 0; i < rows.length(); i++) {
      if (rowMap.get(rows.getString(i)) != null) {
        d.add1HasDistributionRows(rowMap.get(rows.getString(i)));
      } else {
        logWriter.error("missing row");
      }
    }
    JSONArray domain = json.optJSONArray("hasDomainNodes");
    NodeList domainNodes = new NodeList();
    for (int i = 0; i < domain.length(); i++) {
      if (nodeMap.get(domain.getString(i)) != null) {
        domainNodes.addNode(nodeMap.get(domain.getString(i)));
      } else {
        logWriter.error("missing node");
      }
    }
    d.setHasDomainNodes(domainNodes);
    JSONArray range = json.optJSONArray("hasRangeNodes");
    NodeList rangeNodes = new NodeList();
    for (int i = 0; i < range.length(); i++) {
      if (nodeMap.get(range.getString(i)) != null) {
        rangeNodes.addNode(nodeMap.get(range.getString(i)));
      } else {
        logWriter.error("missing node");
      }
    }
    d.setHasRangeNodes(rangeNodes);
  }

  protected void populateRow (String indexString, JSONObject json,
      HashMap<String, Node> nodeMap, HashMap<String, Distribution> distributionMap, HashMap<String, DistributionRow> rowMap) {
    DistributionRow row = rowMap.get(indexString);
  }

  
  protected boolean importModelVars(BufferedReader writer, JSONObject json, HashMap <Object, Long> objectIndex) throws IOException, JSONException {
    /* Node 1 {"hasName":"Q5","id":"51","order":50,"hasDistributions":"1389043350133325000288,","evidence":-1,"domain":["S0","S1"],"class":"Node"} */
    for (Entry<String, Node> entry: modelVars.entrySet()) {
      if (objectIndex.get(entry.getValue()) != null) {
        continue;
      }
      //entry.getValue().export(writer, json, objectIndex);
    }
    return true;
  }
  
  protected boolean importHasDomainNodes(BufferedReader writer, JSONObject json, HashMap <Object, Long> objectIndex) throws IOException, JSONException {
    for (int n = 0; n < getHasDomainNodes().size(); n++) {
      Node node = getHasDomainNodes().get(n);
      if (objectIndex.get(node) != null) {
        continue;
      }
      //node.export(writer, json, objectIndex);
    }
    return true;
  }

  protected boolean importHasDistributions(BufferedReader writer, JSONObject json, HashMap <Object, Long> objectIndex) throws IOException, JSONException {
    for (int d = 0; d < getHasDistributions().size(); d++) {
      Distribution dist = getHasDistributions().get(d);
      if (objectIndex.get(dist) != null) {
        // already exported
        continue;
      }
      //dist.export(writer, json, objectIndex);
    }
    return true;
  }

//DL  public static class HierarchicalDomain {
//DL    ArrayList<HierarchicalDomain> children;
//DL    public static final HierarchicalDomain DAY = new HierarchicalDomain();    

//DL    public HierarchicalDomain (ArrayList<HierarchicalDomain> a_childList) {
//DL      children = a_childList;
//DL    }
    
//DL    public HierarchicalDomain() {
//DL      children = new ArrayList<HierarchicalDomain>();
//DL    }
  
//DL    public static HierarchicalDomain monthOf28Days () {
//DL      ArrayList<HierarchicalDomain> children = new ArrayList<HierarchicalDomain>();
//DL      children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY); 
//DL      children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY); 
//DL      children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY); 
//DL      children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY); 
//DL      return new HierarchicalDomain(children);
//DL    }

//DL    public static HierarchicalDomain monthOf30Days () {
//DL      ArrayList<HierarchicalDomain> children = new ArrayList<HierarchicalDomain>();
//DL      children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY); 
//DL      children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY); 
//DL      children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY); 
//DL      children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY); 
//DL      children.add(DAY);  children.add(DAY);  children.add(DAY);
//DL      return new HierarchicalDomain(children);
//DL    }

//DL    public static HierarchicalDomain monthOf31Days () {
//DL      ArrayList<HierarchicalDomain> children = new ArrayList<HierarchicalDomain>();
//DL      children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY); 
//DL      children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY); 
//DL      children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY); 
//DL      children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY);  children.add(DAY); 
//DL      children.add(DAY);  children.add(DAY);  children.add(DAY);
//DL      return new HierarchicalDomain(children);
//DL    }

//DL    public static HierarchicalDomain year () {
//DL      ArrayList<HierarchicalDomain> children = new ArrayList<HierarchicalDomain>();
//DL      children.add(monthOf31Days()); children.add(monthOf28Days()); children.add(monthOf31Days()); children.add(monthOf30Days()); 
//DL      children.add(monthOf31Days()); children.add(monthOf30Days()); children.add(monthOf31Days()); children.add(monthOf31Days()); 
//DL      children.add(monthOf30Days()); children.add(monthOf31Days()); children.add(monthOf30Days()); children.add(monthOf31Days()); 
//DL      return new HierarchicalDomain(children);
//DL    }

//DL  /**
//DL   * 
//DL   */
//DL    public int depth() {
//DL      if (children == null || children.size() == 0) {
//DL        return 0;
//DL      } else {
//DL        return children.get(0).depth()+1;
//DL      }
//DL    }

//DL  /**
//DL   * 
//DL   */
//DL    public int numberOfChildren() {
//DL      if (children == null) {
//DL        return 0;
//DL      } else {
//DL        return children.size();
//DL      }
//DL    }
  
//DL  /**
//DL   * 
//DL   */
//DL    public HierarchicalDomain child(int index) {
//DL      if (children == null || children.size() < index+1) {
//DL        throw new IllegalArgumentException("index > number of children "+index);
//DL      } else {
//DL        return children.get(index);
//DL      }
//DL    }
  
  
  
  
  
//DL  }

}
