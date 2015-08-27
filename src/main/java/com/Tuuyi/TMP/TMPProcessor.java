package com.Tuuyi.TMP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.ClassModel;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.Distribution;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.DistributionRow;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.GraphTMP;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.Instance;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.Node;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.NodeList;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.NodeModel;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.Property;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.PropertySet;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.TMP;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.TMPClass;


public class TMPProcessor extends DefaultHandler {
  private static final Logger logWriter = Logger.getLogger(TMPProcessor.class.getName());

  private final long LOOKING_FOR_TMP = 0;
  private final long TMP = 1;
  private final long SCHEMA = 2;
  private final long CLASS = 3;
  private final long CLASSPROPERTY = 4;
  private final long CLASSPROPVALUE = 5;
  private final long VAR = 6;
  private final long VARPROPERTY = 7;
  private final long VARPROPVALUE = 8;
  private final long BNMODEL = 9;
  private final long MODEL_DYNAMICPROPERTIES = 10;
  private final long MODEL_DYNAMICPROPERTY = 11;
  private final long MODEL_DYNAMICPROPVALUE = 12;
  private final long MODEL_VARIABLES = 13;
  private final long MODEL_VAR = 14;
  private final long MODEL_VAR_STATE = 15;
  private final long MODEL_VAR_PROPERTY = 16;
  private final long MODEL_VAR_PROPVALUE = 17;
  private final long MODEL_STRUCTURE = 18;
  private final long MODEL_STRUCTURE_ARC = 19;
  private final long DISTRIBUTIONS = 20;
  private final long DIST = 21;
  private final long CONDSET = 22;
  private final long CONDELEM = 23;
  private final long PRIVATE = 24;
  private final long VARSET = 25;
  private final long VARELEM = 26;
  private final long DPIS = 27;
  private final long DPI = 28;
  private final long INSTANCES = 29;

  private long currentlyParsing = LOOKING_FOR_TMP;
  private Stack<Long> previouslyParsing = new Stack<Long> ();
  
  private GraphTMP currentTMP = new GraphTMP();
  private TMPClass currentTMPClass  = null;
  private Node currentNode = null;
  private ArrayList<String> currentDomain = new ArrayList<String>();
  private String currentStateName = "";
  private ClassModel currentClassModel = null;
  private NodeModel currentNodeModel = null;
  private ArrayList<Distribution> currentDistributions = new ArrayList<Distribution> ();
  private Distribution currentDistribution = null;
  private PropertySet currentPropertySet = null;
  private Property currentProperty = null;
  private String currentDistString = "";
  private DistributionRow currentDistRow = new DistributionRow();
  private HashMap<String, Node> nodes = new HashMap<String, Node>();
  private ArrayList<Node> conds = new ArrayList<Node>();
  private ArrayList<Node> vars = new ArrayList<Node>();
  public TMPProcessor(GraphTMP tmp) {
    super();
    currentTMP = tmp;
  }

  public GraphTMP getTMP() {
    return currentTMP;
  }
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if(currentlyParsing==TMP && (localName.equalsIgnoreCase("tmp") || localName.equalsIgnoreCase("prm"))) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==SCHEMA && localName.equalsIgnoreCase("schema")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==CLASS && localName.equalsIgnoreCase("class")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==CLASSPROPERTY && localName.equalsIgnoreCase("property")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==CLASSPROPVALUE && localName.equalsIgnoreCase("propvalue")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==VAR && localName.equalsIgnoreCase("var")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==VARPROPERTY && localName.equalsIgnoreCase("property")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==VARPROPVALUE && localName.equalsIgnoreCase("propvalue")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==BNMODEL && localName.equalsIgnoreCase("bnmodel")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==MODEL_DYNAMICPROPERTIES && localName.equalsIgnoreCase("dynamicproperties")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==MODEL_DYNAMICPROPERTY && localName.equalsIgnoreCase("property")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==MODEL_DYNAMICPROPVALUE && localName.equalsIgnoreCase("propvalue")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==MODEL_VARIABLES && localName.equalsIgnoreCase("variables")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==MODEL_VAR && localName.equalsIgnoreCase("var")) {
      String [] domain = new String [currentDomain.size()];
      currentNode.setDomain(currentDomain.toArray(domain));
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==MODEL_VAR_STATE && localName.equalsIgnoreCase("statename")) {
      currentDomain.add(currentStateName);
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==MODEL_VAR_PROPERTY && localName.equalsIgnoreCase("property")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==MODEL_VAR_PROPVALUE && localName.equalsIgnoreCase("propvalue")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==MODEL_STRUCTURE && localName.equalsIgnoreCase("structure")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==MODEL_STRUCTURE_ARC && localName.equalsIgnoreCase("arc")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==DISTRIBUTIONS && localName.equalsIgnoreCase("distributions")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==DIST && localName.equalsIgnoreCase("dist")) {
      //System.out.println("");
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==CONDSET && localName.equalsIgnoreCase("condset")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==CONDELEM && localName.equalsIgnoreCase("condelem")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==PRIVATE && localName.equalsIgnoreCase("private")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }

    if(currentlyParsing==VARSET && localName.equalsIgnoreCase("varset")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==VARELEM && localName.equalsIgnoreCase("varelem")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==DPIS && localName.equalsIgnoreCase("dpis")) {
      currentTMP.addDistribution(currentDistribution);
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    if(currentlyParsing==DPI && localName.equalsIgnoreCase("dpi")) {
      currentDistRow.setRow(transformRow(currentDistRow.getIndexVector(), parseDistVector(currentDistString)));
      currentDistribution.add1HasDistributionRows(currentDistRow);
      currentlyParsing = previouslyParsing.pop();
      return;
    }
   if (currentlyParsing==INSTANCES && localName.equalsIgnoreCase("instances")) {
      currentlyParsing = previouslyParsing.pop();
      return;
    }
    //logWriter.warn("Parser is confused, ignoring end of "+localName);
  }

  public void startElement(String uri, String localName, String qName, Attributes attributes)
  throws SAXException {
    try {
      if(currentlyParsing == LOOKING_FOR_TMP) {
        if(localName.equalsIgnoreCase("tmp") || localName.equalsIgnoreCase("prm")) {
          previouslyParsing.push(currentlyParsing);
          currentlyParsing = TMP;        
          currentTMP.setHasInstances(new ArrayList<Instance> ());
          currentTMP.setHasDomainNodes(new NodeList ());
          }
        return;
      }
      if(currentlyParsing==TMP && localName.equalsIgnoreCase("schema")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = SCHEMA;     
        return;
      }
      if(currentlyParsing==SCHEMA && localName.equalsIgnoreCase("class")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = CLASS;
        currentTMPClass = new TMPClass();
        return;
      }
      if(currentlyParsing==CLASS && localName.equalsIgnoreCase("property")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = CLASSPROPERTY;             
        return;
      }
      if(currentlyParsing==CLASSPROPERTY && localName.equalsIgnoreCase("propvalue")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = CLASSPROPVALUE;             
        return;
      }
      if(currentlyParsing==CLASSPROPVALUE && localName.equalsIgnoreCase("propvalue")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = TMP;             
        return;
      }
      if(currentlyParsing==CLASS && localName.equalsIgnoreCase("var")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = VAR; 
        return;
      }
      if(currentlyParsing==VAR && localName.equalsIgnoreCase("property")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = VARPROPERTY;             
        return;
      }
      if(currentlyParsing==VARPROPERTY && localName.equalsIgnoreCase("propvalue")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = VARPROPVALUE;             
        return;
      }
      if(currentlyParsing==TMP && localName.equalsIgnoreCase("bnmodel")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = BNMODEL;             
        currentClassModel = new ClassModel();
        return;
      }
      if(currentlyParsing== BNMODEL && localName.equalsIgnoreCase("dynamicproperties")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = MODEL_DYNAMICPROPERTIES;             
        return;
      }
      if(currentlyParsing==MODEL_DYNAMICPROPERTIES && localName.equalsIgnoreCase("property")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = MODEL_DYNAMICPROPERTY;             
        return;
      }
      if(currentlyParsing==MODEL_DYNAMICPROPERTY && localName.equalsIgnoreCase("propvalue")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = MODEL_DYNAMICPROPVALUE;             
        return;
      }
      if(currentlyParsing==BNMODEL && localName.equalsIgnoreCase("variables")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = MODEL_VARIABLES;             
        return;
      }
      if(currentlyParsing==MODEL_VARIABLES && localName.equalsIgnoreCase("var")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = MODEL_VAR;    
        String varName = attributes.getValue("name");
        if (varName.startsWith(".")) {
          varName = varName.substring(1);
        }
        currentNode = Node.newNode(varName);
        currentNode.setHasName(varName);
        currentNode.setId(Integer.parseInt(varName));
        nodes.put(varName, currentNode);
        currentTMP.addModelVar(currentNode);    
        //NodeManager.getInstance().insert(node);
        // workspace.getDefaultDataSource().persist();
        currentDomain = new ArrayList<String>(); // initialize domain to empty
        //Node var = (Node) NodeManager.getInstance().get(varName);
        return;
      }
      if(currentlyParsing==MODEL_VAR && localName.equalsIgnoreCase("statename")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = MODEL_VAR_STATE;             
        currentStateName = "";
        return;
      }
      if(currentlyParsing==MODEL_VAR && localName.equalsIgnoreCase("property")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = MODEL_VAR_PROPERTY;             
        return;
      }
      if(currentlyParsing==MODEL_VAR_PROPERTY && localName.equalsIgnoreCase("propvalue")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = MODEL_VAR_PROPVALUE;             
        return;
      }
      if(currentlyParsing==BNMODEL && localName.equalsIgnoreCase("structure")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = MODEL_STRUCTURE;             
        return;
      }
      if(currentlyParsing==MODEL_STRUCTURE && localName.equalsIgnoreCase("arc")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = MODEL_STRUCTURE_ARC;             
        return;
      }
      if(currentlyParsing==BNMODEL && localName.equalsIgnoreCase("distributions")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = DISTRIBUTIONS;   
        currentDistributions = new ArrayList<Distribution> ();
        return;
      }
      if(currentlyParsing==DISTRIBUTIONS && localName.equalsIgnoreCase("dist")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = DIST;   
        currentDistribution = new Distribution();
        currentDistributions.add(currentDistribution);
        System.out.print("  Dist");
        return;
      }
      if(currentlyParsing==DIST && localName.equalsIgnoreCase("condset")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = CONDSET;
        currentDistribution.setHasDomainNodes(new NodeList());
       return;
      }
      if(currentlyParsing==CONDSET && localName.equalsIgnoreCase("condelem")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = CONDELEM;             
        String condName = attributes.getValue("name");
        if (condName.startsWith(".")) {
          condName = condName.substring(1);
        }
        Node cond = currentTMP.getModelVar(condName);
        currentDistribution.addDomainNode(cond);
        System.out.print(" "+cond.getId());
        return;
      }
      if(currentlyParsing==DIST && localName.equalsIgnoreCase("private")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = PRIVATE;             
        return;
      }

      if(currentlyParsing==DIST && localName.equalsIgnoreCase("varset")) {
        previouslyParsing.push(currentlyParsing);
        NodeList vars = new NodeList();
        currentDistribution.setHasRangeNodes(vars);
        currentlyParsing = VARSET;             
        return;
      }
      if(currentlyParsing==VARSET && localName.equalsIgnoreCase("varelem")) {
        previouslyParsing.push(currentlyParsing);
        currentlyParsing = VARELEM;   
        String varName = attributes.getValue("name");
        if (varName.startsWith(".")) {
          varName = varName.substring(1);
        }
        Node var = currentTMP.getModelVar(varName);
        System.out.print(" "+var.getId());
        NodeList rangeNodes = new NodeList(); // only one var per dist!
        rangeNodes.add1HasNodes(var);
        var.add1HasDistributions(currentDistribution);
        currentDistribution.setHasRangeNodes(rangeNodes);
        
       return;
      }
      if(currentlyParsing==DIST && localName.equalsIgnoreCase("dpis")) {
        previouslyParsing.push(currentlyParsing);
        currentDistribution.setHasDistributionRows(new ArrayList<DistributionRow>());
        currentlyParsing = DPIS;             
        System.out.println("");
        return;
      }
      if(currentlyParsing==DPIS && localName.equalsIgnoreCase("dpi")) {
        previouslyParsing.push(currentlyParsing);
        int [] currentIndexVector = parseIndexString(attributes.getValue("indexes"));
        currentDistRow = new DistributionRow();
        currentDistRow.setIndexVector(currentIndexVector);
        currentDistString = "";
        currentlyParsing = DPI;             
       return;
      }
     if (currentlyParsing==TMP && localName.equalsIgnoreCase("instances")) {
       previouslyParsing.push(currentlyParsing);
       currentlyParsing = TMP;             
        return;
      }
    } catch (Exception e) {
      logWriter.error("Error processing feed",e);
    }
  }

  public void characters(char[] ch, int start, int length)        throws SAXException {
    try {
      String arg = new String(ch, start, length);
      //System.out.println(currentlyParsing+" "+arg);
      if(currentlyParsing == LOOKING_FOR_TMP) {
        return;
      }
      if(currentlyParsing==TMP) {
        return;
      }
      if(currentlyParsing==SCHEMA) {
        return;
      }
      if(currentlyParsing==CLASS) {
        return;
      }
      if(currentlyParsing==CLASSPROPERTY) {
        return;
      }
      if(currentlyParsing==CLASSPROPVALUE) {
        return;
      }
      if(currentlyParsing==CLASS) {
        return;
      }
      if(currentlyParsing==VAR) {
        return;
      }
      if(currentlyParsing==VARPROPERTY) {
        return;
      }
      if(currentlyParsing==TMP) {
        return;
      }
      if(currentlyParsing== BNMODEL) {
        return;
      }
      if(currentlyParsing==MODEL_DYNAMICPROPERTIES) {
        return;
      }
      if(currentlyParsing==MODEL_DYNAMICPROPERTY) {
        return;
      }
      if(currentlyParsing==BNMODEL) {
        return;
      }
      if(currentlyParsing==MODEL_VARIABLES) {
        return;
      }
      if(currentlyParsing==MODEL_VAR) {
        return;
      }
      if(currentlyParsing==MODEL_VAR_STATE) {
        currentStateName = arg;
        return;
      }
      if(currentlyParsing==MODEL_VAR_PROPERTY) {
        return;
      }
      if(currentlyParsing==BNMODEL) {
        return;
      }
      if(currentlyParsing==MODEL_STRUCTURE) {
        return;
      }
      if(currentlyParsing==BNMODEL) {
        return;
      }
      if(currentlyParsing==DISTRIBUTIONS) {
        return;
      }
      if(currentlyParsing==DIST) {
       return;
      }
      if(currentlyParsing==CONDSET) {
         return;
      }
      if(currentlyParsing==CONDELEM) {
        System.out.print(arg);
       return;
      }

      if(currentlyParsing==VARSET) {
        return;
      }
      if(currentlyParsing==VARELEM) {
        System.out.print(arg);
        return;
      }
      if(currentlyParsing==DPIS) {
        return;
       }
      if(currentlyParsing==DPI) {
        currentDistString += arg;
        //System.out.print(arg);
        return;
       }
     if (currentlyParsing==TMP) {
        return;
      }
    } catch (Exception e) {
      logWriter.error("Error processing feed",e);
    }
  }
  
  public double [] parseDistVector(String currentRowString) {
    //System.out.println("   dist: "+currentDistString);
    String [] elemStrings = currentRowString.trim().split(" ");
    double [] row = new double[elemStrings.length];
    int rowIndex = 0;
    int rowOffset = 0;
    if (elemStrings != null && elemStrings[0].equals("")) {
      row = new double[elemStrings.length-1];
      rowOffset=1;
    }
    for (; rowIndex< elemStrings.length-rowOffset; rowIndex++) {
      row[rowIndex] = Double.parseDouble(elemStrings[rowIndex+rowOffset]);
    }
    return row;
  }
  
  
  public int [] parseIndexString(String indexString) {
    String [] indexStrings = indexString.split(" ");
    if (indexStrings.length <= 0) {
      return new int[0];
    }
    int [] indecies = new int [indexStrings.length-1];
    for (int i = 0; i < indexStrings.length-1; i++) {
      indecies[i] = Integer.parseInt(indexStrings[i+1]);
    }
    return indecies;
  }
  
  public double [] transformRow(int [] indecies, double [] inputRow) {

    double [] row = new double[inputRow.length];
    if (indecies.length < 1) {
      return inputRow;
    }
    for (int i = 0; i < row.length; i++) {
      row[i] = 1;
    }
    double sum = 0.0;
    for (int i = 0; i < indecies.length; i++) {
      for (int j = 0; j < row.length; j++) {
        row[j] *= 1.0/((indecies[i]-j)*(indecies[i]-j)+1.1);
      }
    }
    sum = 0.0;
    for (int i = 0; i < row.length; i++) {
      sum += row[i];
    }
    for (int i = 0; i < row.length; i++) {
      row[i] /= sum;
    }
    return row;
  }
  
  public TMP getResult() {
    return currentTMP;
  }

  class SaxErrorHandler implements ErrorHandler {


    public void error(SAXParseException arg0) throws SAXException {
      // TODO Auto-generated method stub      
    }

    public void fatalError(SAXParseException arg0) throws SAXException {
      // TODO Auto-generated method stub      
    }

    public void warning(SAXParseException arg0) throws SAXException {
      // TODO Auto-generated method stub      
    }
  }

}

