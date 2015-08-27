package com.Tuuyi.TMP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.Tuuyi.TDM.DomainConcept;
import com.Tuuyi.TDM.Workspace;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.*;
import com.ibatis.sqlmap.client.SqlMapClient;

public class ComputeDistributions {
  private static final Logger logWriter = Logger.getLogger(ComputeDistributions.class.getName());
  String fileName;
  Workspace workspace = null;
  SqlMapClient sqlMap = null;

  public static void main(String [] args) {

    Options options = new Options();
    options.addOption("file", true, "input file");
    CommandLineParser parser = new PosixParser();
    CommandLine cmd = null;

    try {
      cmd = parser.parse(options, args);
    } catch (ParseException e) {
      logWriter.error("Error processing command line args", e);
      HelpFormatter help = new HelpFormatter();
      help.printHelp("RSSIngest", options);
      System.exit(-1);
    }
    ComputeDistributions TMPUpdate= new ComputeDistributions();
    TMPUpdate.fileName = cmd.getOptionValue("file");

    if (TMPUpdate.fileName != null) {
      //TMPUpdate.computeDists();
      TMPUpdate.queryDists();
    }
  }

  public ComputeDistributions() {
  }
  
  public void computeDists() {
    GraphTMP TMP = new GraphTMP();
    TMP.loadModel(fileName);
    System.out.println(TMP.getHasDomainNodes().size());
    System.out.println(TMP.asJSONTree().toString());
    
    Distribution outputDist = null;
    TwoOpIndexStructure [] toisSet = new TwoOpIndexStructure[TMP.getHasDomainNodes().size()];
    Distribution inputDist = TMP.getHasDistributions().get(0);
    for (int n = 1; n < TMP.getHasDistributions().size(); n++) {
      NodeList out = TMP.getHasDistributions().get(n).getHasDomainNodes().union(TMP.getHasDistributions().get(n).getHasRangeNodes());
      Distribution nodeDist = TMP.getHasDistributions().get(n);
      toisSet[n] = nodeDist.makeMultiplyIndexStructure(inputDist, out);
    }
    System.gc();
    Runtime rt = Runtime.getRuntime();
    long start = System.currentTimeMillis();
    for (int i = 0; i < 1000; i++) {
      inputDist = TMP.getHasDistributions().get(0);
      for (int n = 1; n < TMP.getHasDistributions().size(); n++) {
        NodeList out = TMP.getHasDistributions().get(n).getHasDomainNodes().union(TMP.getHasDistributions().get(n).getHasRangeNodes());
        Distribution nodeDist = TMP.getHasDistributions().get(n);
        TwoOpIndexStructure tois = toisSet[n];
        outputDist = inputDist.doMultiply(tois);
        Distribution output = nodeDist.doMultiply(tois);
        outputDist = inputDist.doMultiply(tois);
        inputDist = outputDist;  
      }
    }
    long interval = System.currentTimeMillis() - start;
    System.out.println(" time: "+interval);
    System.gc();
    System.out.println("memory usage total: "+(rt.totalMemory()/(1024*1024))+" free: " + (rt.freeMemory()/(1024*1024)));

    System.out.println("OutDistSize: "+outputDist.getHasRangeNodes().size()+": "+interval);

  }

  public void queryDists() {
    GraphTMP TMP = new GraphTMP();
    TMP.loadModel(fileName);
    System.out.println("Nodes: "+TMP.getHasDomainNodes().size());
    System.out.println("Graph: "+TMP.asJSONTree().toString());
    
    System.gc();
    Runtime rt = Runtime.getRuntime();
    long start = System.currentTimeMillis();
    Query [] queries = new Query[TMP.getHasDomainNodes().size()];
    for (int n = 0; n < TMP.getHasDomainNodes().size(); n++) {
      Node node = TMP.getHasDomainNodes().get(n);
      Query q = TMP.makeQuery(new NodeList(node));
      q.buildReducePlan(new NodeList(node));
      TMP.indexQuery(q);
      if (q.getHasPlan().getHasResults().size() > 1) {
      System.out.println("UnReduced Result Expression! "+node.getHasName());
      }
      queries[n] = q;
    }
    long interval = System.currentTimeMillis() - start;
    System.out.println(" time: "+interval+", operation reuse count: "+Query.OPERATION_REUSE_COUNT);
    System.gc();
    System.out.println("memory usage total: "+(rt.totalMemory()/(1024*1024))+" free: " + (rt.freeMemory()/(1024*1024)));
    System.gc();
    start = System.currentTimeMillis();
    int itrs = 1000;
    for (int i = 0; i < itrs; i++) {
      for (int q = 0; q < queries.length; q++) {
        Node node = TMP.getHasDomainNodes().get(q);
        queries[q].invalidate(node);
        queries[q].evalPlan();
      }
    }
    interval = System.currentTimeMillis() - start;
    System.out.println(" time: "+((interval*1000.0)/itrs)+"us");
    System.gc();
    System.out.println("memory usage total: "+(rt.totalMemory()/(1024*1024))+" free: " + (rt.freeMemory()/(1024*1024)));
    for (int q = 0; q < queries.length; q++) {
      displayRows(queries[q].getHasResult());
    }

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

  
}

