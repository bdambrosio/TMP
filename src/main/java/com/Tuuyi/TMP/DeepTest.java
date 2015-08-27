package com.Tuuyi.TMP;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.Tuuyi.TDM.DomainConcept;
import com.Tuuyi.TDM.Workspace;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.Distribution;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.DistributionRow;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.GraphTMP;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.Node;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.NodeList;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.Query;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.TwoOpIndexStructure;
import com.ibatis.sqlmap.client.SqlMapClient;


public class DeepTest {
    private static final Logger logWriter = Logger.getLogger(DeepTest.class.getName());
    String fileName;
    Workspace workspace = null;
    SqlMapClient sqlMap = null;

    public static void main(String [] args) {

      Options options = new Options();
      options.addOption("graphFile", true, "export file");
      CommandLineParser parser = new PosixParser();
      CommandLine cmd = null;

      String fileName = "";
      try {
        cmd = parser.parse(options, args);
        if (cmd.hasOption("graphFile")) {
          fileName = cmd.getOptionValue("graphFile");
        } else {
          logWriter.error ("Missing graphFile arg");
          System.exit(-1);
        }
      } catch (ParseException e) {
        logWriter.error("Error processing command line args", e);
        HelpFormatter help = new HelpFormatter();
        help.printHelp("DeepSRMTest", options);
        System.exit(-1);
      }
      DeepTest test= new DeepTest();
      test.runEMTest();
    }

    public DeepTest() {
    }
    
    public void runEMTest() {
 
      System.out.println("\n\n****---- starting test ----****\n");
      GraphTMP srm = GraphTMP.newGraph();
      NodeList graphNodes = new NodeList();
      graphNodes.setHasNodes(new ArrayList<Node>());
      srm.setHasDomainNodes(graphNodes);

      Node n1 = Node.newNode("n1", new String[] {"one", "two", "three"} );
      srm.getHasDomainNodes().addNode(n1);

      JSONObject srmAsJson = srm.asJSONTree();
      System.out.println(srmAsJson.toString());

      // Distribution and arithmetic tests
      // Node 1 distribution
      Distribution d1 = Distribution.newDistribution(new NodeList(new Node[] {}), new NodeList(new Node [] {n1}));
      ArrayList<DistributionRow> d1Rows = new ArrayList<DistributionRow> (1);
      DistributionRow d1Row = DistributionRow.newRow(new int [0], new double [] {0.33, 0.33, 0.34} );
      d1Rows.add(d1Row);
      d1.setHasDistributionRows(d1Rows);
      n1.add1HasDistributions(d1);
      srm.addDistribution(d1);

      Node n2 = Node.newNode("n2", new String [] {"f", "t"});

      srm.getHasDomainNodes().addNode(n2);
      Distribution d2 = Distribution.newDistribution(new NodeList(new Node [] {n1}), new NodeList(new Node [] {n2}));
      ArrayList<DistributionRow> d2Rows = new ArrayList<DistributionRow> (3);
      d2Rows.add(DistributionRow.newRow(new int [0], new double [] {0.5, 0.5}));
      d2Rows.add(DistributionRow.newRow(new int [0], new double [] {0.3, 0.7}));
      d2Rows.add(DistributionRow.newRow(new int [0], new double [] {0.2, 0.8}));
      d2.setHasDistributionRows(d2Rows);
      srm.addDistribution(d2);
      n2.add1HasDistributions(d2);
      
      Node n3 = Node.newNode("n3", new String [] {"f", "t"});
      srm.getHasDomainNodes().addNode(n3);
      Distribution d3 = Distribution.newDistribution(new NodeList(new Node [] {n1}), new NodeList(new Node [] {n3}));
      ArrayList<DistributionRow> d3Rows = new ArrayList<DistributionRow> (3);
      d3Rows.add(DistributionRow.newRow(new int [0], new double [] {0.5, 0.5}));
      d3Rows.add(DistributionRow.newRow(new int [0], new double [] {0.3, 0.7}));
      d3Rows.add(DistributionRow.newRow(new int [0], new double [] {0.2, 0.8}));
      d3.setHasDistributionRows(d3Rows);
      n3.add1HasDistributions(d3);
      srm.addDistribution(d3);

      FileOutputStream fos;
      try {
        fos = new FileOutputStream("/Users/bda/wearables/emtestGraph.json");
      OutputStreamWriter osr = new OutputStreamWriter(fos, "UTF-8");
      BufferedWriter writer = new BufferedWriter(osr);
      srm.exportGraph (writer);
      writer.flush();
      writer.close();
      fos.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } 

      
      Distribution d0 = Distribution.newDistribution(new NodeList(new Node[] {}), new NodeList(new Node [] {n1, n2, n3}));
      ArrayList<DistributionRow> d0Rows = new ArrayList<DistributionRow> (1);
      DistributionRow d0Row = DistributionRow.newRow(new int [0], new double [] {0.0805, 0.081, 0.0815, 0.082, 0.0825, 0.083, 0.0835, 0.084, 0.0845, 0.085, 0.0855, 0.086} );
      d0Rows.add(d0Row);
      d0.setHasDistributionRows(d0Rows);

      Distribution sum = Distribution.newDistribution(new NodeList(new Node[] {}), new NodeList(new Node [] {n1, n2, n3}));
      ArrayList<DistributionRow> sumRows = new ArrayList<DistributionRow> (1);
      DistributionRow sumRow = DistributionRow.newRow(new int [0], new double [] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0} );
      sumRows.add(sumRow);
      sum.setHasDistributionRows(sumRows);

      NodeList allTargets = new NodeList(new Node [] {n1, n2, n3} );
      NodeList noTarget = new NodeList(new Node [] {} );
      NodeList n1Target = new NodeList(new Node [] {n1} );
      NodeList n2Target = new NodeList(new Node [] {n2} );
      NodeList n3Target = new NodeList(new Node [] {n3} );
      Query.invalidate();
      n2.setEvidence(-1);
      n3.setEvidence(-1);
      Query q1 = srm.makeQuery(allTargets);
      q1.buildReducePlan(allTargets);
      q1.evalPlan();
      Distribution r1 = q1.getHasResult();
      //displayDistribution(sum);
      System.out.print(" Initial joint: ");displayDistribution(r1);

      TwoOpIndexStructure sStruct = sum.makeMultiplyIndexStructure(r1, allTargets);
      sum = sum.doAdd(sStruct);
      //sum.normalize();
      System.out.print(" Test sum: ");      displayDistribution(sum);

      sum.normalize();
      Distribution newN2Dist = sum.computeConditional(n1Target, n2Target);
      System.out.print(" n2|n1: ");displayDistribution(newN2Dist);
      Distribution newN3Dist = sum.computeConditional(n1Target, n3Target);
      System.out.print(" n3|n1: ");displayDistribution(newN3Dist);
      Distribution newN1Dist = sum.computeConditional(noTarget, n1Target);
      System.out.print(" n1: ");displayDistribution(newN1Dist);

      
      long start = System.currentTimeMillis();
      /* EM iterations */
      for (int i = 0; i < 10; i++) {
        System.out.println("ITERATION: "+i);
        sum.zero();
        Query.invalidate();
        n2.setEvidence(0);
        n3.setEvidence(0);
        q1.evalPlan();
        q1.getHasResult().normalize();
        sum = sum.doAdd(sStruct);
        displayDistribution(sum);
        //displayDistribution(q1.getHasResult());

        n2.setEvidence(1);
        n3.setEvidence(1);
        Query.invalidate();
        q1.evalPlan();
        q1.getHasResult().normalize();
        //displayDistribution(q1.getHasResult());
        sum = sum.doAdd(sStruct);
        //sum.normalize();
        displayDistribution(sum);

        n2.setEvidence(1);
        n3.setEvidence(1);
        Query.invalidate();
        q1.evalPlan();
        q1.getHasResult().normalize();
        sum = sum.doAdd(sStruct);
        displayDistribution(sum);

        n2.setEvidence(0);
        n3.setEvidence(0);
        Query.invalidate();
        q1.evalPlan();
        q1.getHasResult().normalize();
        sum = sum.doAdd(sStruct);
        //displayDistribution(q1.getHasResult());
        displayDistribution(sum);
        
        sum.normalize();
        n2.setEvidence(-1);
        n3.setEvidence(-1);
        newN2Dist = sum.computeConditional(n1Target, n2Target);
        newN2Dist.normalize();
        System.out.print(" n2|n1: ");displayDistribution(newN2Dist);
        newN3Dist = sum.computeConditional(n1Target, n3Target);
        newN3Dist.normalize();
        System.out.print(" n3|n1: ");displayDistribution(newN3Dist);
        newN1Dist = sum.computeConditional(noTarget, n1Target);
        newN1Dist.normalize();
        System.out.print(" n1: ");displayDistribution(newN1Dist);
        
        d1.copyRows(newN1Dist);
        d2.copyRows(newN2Dist);
        d3.copyRows(newN3Dist);

      }

      /* final result */

      //sum.normalize();
      System.out.print(" Final result: ");     displayDistribution(sum);
      long interval = System.currentTimeMillis() - start;
      System.out.println("Time: "+interval);

    }


 
    boolean match(Distribution d1, Distribution d2) {
      if (d1 == null || d2 == null) {
        return false;
      }
      if (d1.getHasDistributionRows() == null || d2.getHasDistributionRows() == null) {
        return false;
      }
      if (d1.getHasDistributionRows().size() !=  d2.getHasDistributionRows().size()) {
        return false;
      }
      for (int i = 0; i < d1.getHasDistributionRows().size(); i++) {
        DistributionRow d1Row = d1.getHasDistributionRows().get(i);
        DistributionRow d2Row = d2.getHasDistributionRows().get(i);
        if (d1Row.getIndexVector().length != d2Row.getIndexVector().length) {
          return false;
        }
        if (d1Row.getRow().length != d2Row.getRow().length) {
          return false;
        }
        for (int j = 0; j < d1Row.getRow().length; j++) {
          double d1Prob = d1Row.getRow()[j];
          double d2Prob = d2Row.getRow()[j];
          if ((d1Prob == 0.0 && d2Prob > 0.0) || (d1Prob > 0.0 && d2Prob == 0.0)) {
            return false;
          }
          if (Math.abs(d1Prob-d2Prob) > Math.abs(d1Prob+d2Prob)/200) {
            return false;
          }
        }
      }
      return true;
    }

    public static void displayFloatArray(String label, List<Float> floatList) {
      System.out.print(label+": {");
      for (Float f: floatList) {
        if (f == 0.0F) {
          System.out.print("0.0 ");
        } else {
          System.out.print(f+" ");
        }
      }
      System.out.println("}");
    }

    public static void displayDistribution(Distribution dist) {
      System.out.println(dist.toString());
      displayRows(dist);
    }

    public static void displayRows(Distribution dist) {
      for (DomainConcept rowDC: dist.getHasDistributionRows()) {
        DistributionRow row = (DistributionRow) rowDC;
        System.out.print("  <dpi indexes=\""+row.indexVectorAsString()+"\"> ");
        for (double p: row.getRow()) {
          System.out.print(" "+p);
        }
        System.out.println("</dpi>");
      }
    }


}
