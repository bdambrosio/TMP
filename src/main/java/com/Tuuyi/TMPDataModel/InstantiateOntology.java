package com.Tuuyi.TMPDataModel;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.Tuuyi.TDM.IBatisConceptSource;
import com.Tuuyi.TDM.Workspace;
import com.hp.hpl.jena.rdf.model.Model;
import com.ibatis.sqlmap.client.SqlMapClient;

public class InstantiateOntology {
  /**
   * instantiate logger
   */
  private static final Logger logWriter = Logger.getLogger(InstantiateOntology.class.getName());

  /**
   * @param args
   */
  public static void main(String[] args) {
    Workspace workspace = new Workspace();
    Model ontology = workspace.init("src/main/sqlmaps/TMPDataModel.owl",  "com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel");
    File destDirectory = new File("src/main/java/com/Tuuyi/TMPDataModel/generatedClasses/TMPDataModel");
    // TODO - generateJava shouldn't need package name
    ArrayList<File> javaFiles = workspace.generateJava("com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel", destDirectory);

    workspace.compileJavaClassFiles(javaFiles);

    destDirectory = new File("src/main/sql/");
    workspace.generateDBSchema(destDirectory);

    destDirectory = new File(System.getProperty("user.dir")+"/src/main/sqlmaps/maps/");
    // TODO - generateIBatis shouldn't need package name
    workspace.generateIBatisFiles("com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel", destDirectory);

    /** skip the following, no db files for TMP classes **/
    //SqlMapClient sqlMap = workspace.loadIBatisConfig();
    //workspace.setDefaultDataSource(new IBatisConceptSource(sqlMap, workspace));
    workspace.setClassLoader(Model.class.getClassLoader());

  }
}
