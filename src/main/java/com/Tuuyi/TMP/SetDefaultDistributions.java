package com.Tuuyi.TMP;

import com.Tuuyi.TDM.DomainConcept;
import com.Tuuyi.TDM.IBatisConceptSource;
import com.Tuuyi.TDM.Workspace;
import com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.ibatis.sqlmap.client.SqlMapClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

public class SetDefaultDistributions {
  private static final Logger logWriter = Logger.getLogger(SetDefaultDistributions.class.getName());
  String fileName;
  Workspace workspace = null;
  SqlMapClient sqlMap = null;
  TMP tmp = null;

  public static void main(String [] args) {

    Options options = new Options();
    options.addOption("file", true, "input file");
    CommandLineParser parser = new PosixParser();
    CommandLine cmd = null;

    try {
      cmd = parser.parse(options, args);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      logWriter.error("Error processing command line args", e);
      HelpFormatter help = new HelpFormatter();
      help.printHelp("RSSIngest", options);
      System.exit(-1);
    }
    HelpFormatter help = new HelpFormatter();

    SetDefaultDistributions compDists= new SetDefaultDistributions();
    compDists.fileName = cmd.getOptionValue("file");
    if (compDists.fileName != null) {
      //TMPExtension TMPExt = new TMPExtension();
      //TMPExt.load(compDists.fileName);
      /** default computation is currently done in load. We'll need to change that, but for now all that remains is to save file! **/
      //TMPExt.exportAsEtk(compDists.fileName+".etk");
    }
  }

  public SetDefaultDistributions() {
    workspace = new Workspace();
    Model ontology = workspace.init("TMPDataModel.owl",  "com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel");
    sqlMap = workspace.loadIBatisConfig();
    workspace.setDefaultDataSource(new IBatisConceptSource(sqlMap, workspace));
    workspace.setClassLoader(TMP.class.getClassLoader());
    
  }
  
  public void computeDists() {

  }

}

