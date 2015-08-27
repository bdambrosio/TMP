package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class CatalogOntologyAddlMethods extends CatalogOntology {

  HashMap <String, OntologyTerm> rootTerms = new HashMap<String, OntologyTerm>();
  HashMap <String, OntologyTerm> terms = new HashMap<String, OntologyTerm>();
  HashMap <String, OntologyRelation> relations = new HashMap<String, OntologyRelation>();
  
//DL  public CatalogOntology(String catalogName) {
//DL    setDomainOfDiscourse(catalogName);
//DL    TYPE_TERM = new OntologyTerm("type");
//DL    DOMAIN_TERM = new OntologyTerm("domain");    
//DL  }
  
  public Collection <OntologyTerm> getTerms() {
    return terms.values();
  }
  
  public ItemProjection index(AbstractItem ai) {
    // [category:findit>Reinforced Toe, category:findit>Sheer: 10 - 20 Denier, category:Classic;attribute:Colour.Grey, category:Sheers>Classic Pantyhose, 
    // category:Fashion>Coloured Hosiery>Nude>English Rose, category:Sheers;attribute:Colour.Grey, category:Tights;attribute:Colour.Mediterranean, 
    // category:Classic;attribute:Size.Tall, category:Tights;attribute:Colour.African, category:Sheers;Brand:Levante, category:Classic;attribute:Colour.Sunkissed, 
    // category:Tights>Sheer Tights, category:Classic;attribute:Size.X Tall, category:Specialist>Extra Tall>6'1'' +, category:Sheers;attribute:Colour.Mediterranean, 
    // category:Classic;attribute:Colour.English Rose, category:Brand>Levante, category:Sheers;attribute:Colour.Sunkissed, category:Specialist>Extra Tall>5'10'' - 5'11'', 
    // category:Sheers, category:findit>Levante Class, category:Classic>Sheer Tights, category:Sheers;attribute:Colour.English Rose, 
    // category:Fashion>Coloured Hosiery>Grey, category:Classic;attribute:Colour.Black, category:Tights;attribute:Colour.Navy, category:Sheers;attribute:Size.Tall, 
    // category:Classic;attribute:Colour.Mediterranean, category:findit>Top 10, category:Classic, category:Classic;attribute:Colour.African, 
    // category:Tights;attribute:Colour.English Rose, clientEntityId:1017, category:Specialist>Extra Tall>6'0'', category:Brand, category:Sheers;attribute:Colour.African, 
    // category:findit>Mother's Day Gift Ideas, category:Sheers;attribute:Colour.Navy, category:Tights;attribute:Colour.Asian, category:Classic;attribute:Colour.Asian, 
    // Description:These Levante Class 12 denier glossy tights are made in Italy using quality hosiery yarns and are some of the longest-lasting sheer tights around....
    // category:Tights;Title:Levante Class Tights, category:Specialist>Extra Tall, category:Fashion>Coloured Hosiery>Black, Description.length:long, 
    // category:Sheers;attribute:Size.X Tall, category:Sheers;attribute:Colour.Black, category:Tights;attribute:Size.Tall, category:Tights;attribute:Size.Small, 
    // Content:These Levante Class 12 denier glossy tights are made in Italy using quality hosiery yarns and are some of the longest-lasting sheer tights around....
    // category:Classic;Brand:Levante, category:Tights;attribute:Size.Medium, category:Sheers;Title:Levante Class Tights, category:Tights;attribute:Size.X Tall, 
    // category:findit>Catalogue Sheer's, category:Sheers;attribute:Colour.Asian, category:Sheers;attribute:Size.Small, category:Classic;attribute:Colour.Navy, 
    // category:findit>Shiny, category:Fashion>Coloured Hosiery>Nude>Sunkissed, category:Tights;attribute:Colour.Grey, category:Classic;Title:Levante Class Tights, 
    // category:Tights;attribute:Colour.Black, category:Classic;attribute:Size.Medium, category:Classic;attribute:Size.Small, category:Tights;attribute:Colour.Sunkissed, 
    // category:Fashion>Coloured Hosiery>Navy, category:Sheers;attribute:Size.Medium, category:Tights, category:Tights;Brand:Levante]
    
    // first find and create if necessary all types
    Collection<ScoredTerm> itemTerms = parse(ai, true);
    ItemProjection item = new ItemProjection(ai, itemTerms);
    return item;
  }
  
  public HashSet<ScoredTerm> parse (AbstractItem ai, boolean extendOntology) {
    // first find and create if necessary all types
    HashSet <ScoredTerm> itemTerms = new HashSet<ScoredTerm> ();
    // (1) add all prior types via TYPE relations
    // (2) add all left subterms via GENERALIZATION relations
    ArrayList<OntologyTerm> typeTerms = new ArrayList<OntologyTerm> ();
    ArrayList<OntologyTerm> generalizationTerms = new ArrayList<OntologyTerm> ();
    OntologyTerm parentTerm = DOMAIN_TERM;
    for (MapReduceItem token: ai.getHasAbstractionMapping()) {
      if (token.getItem().startsWith("Description") || token.getItem().startsWith("Content")) {
        continue;
      } else if (token.getItem().startsWith("Title")) { // special processing for title
      }
      typeTerms.clear();
      generalizationTerms.clear();
      parentTerm = DOMAIN_TERM;
      parentTerm = processSection(token.getItem(), extendOntology, itemTerms, typeTerms, parentTerm);
    }
    return itemTerms;
  }

  /** 
   * processCatalog builds projection tables once catalog has been loaded.
   * items are already indexed under terms, and term lattice is built during item loading
   * remaining working data is to recursively build counts of total items indexed under each term and its children
   */

  public void processCatalog() {
    for (OntologyTerm term: terms.values()) {
      term.initializeRecursiveItemCount();
      }
    for (OntologyTerm term: terms.values()) {
      term.computeRecursiveItemCount();
      }
    }
  
  
  private OntologyTerm processSection(String section, boolean extendOntology, HashSet<ScoredTerm> itemTerms, ArrayList<OntologyTerm> typeTerms, OntologyTerm parentTerm) {
    section = section.trim();
    String remainingString = section;
    // now process domain term within section
    String [] subTerms = remainingString.split("(?<=[>/.:;])");
    for (int subTermIndex = 0; subTermIndex < subTerms.length; subTermIndex++) {
      String subTerm = subTerms[subTermIndex];
      if (subTerm.endsWith(":") ) { // it's a type 
        String type = subTerm.replace(":", ""); 
        OntologyTerm typeTerm = createOntologyTerm(type, false);
        if (typeTerm == null && extendOntology) {
          // create type term
          typeTerm = createOntologyTerm(type, true);
          createOntologyRelation(OntologyRelation.TYPE_RELATION, typeTerm, TYPE_TERM);
        }
        if (typeTerm != null) {
          if (!typeTerms.contains(typeTerm)) {
            typeTerms.add(typeTerm);
          }
        }
        continue;
      }
      subTerm = subTerm.replaceAll("[>/.:;]", "");
      String newTermName = parentTerm+"."+subTerm;
      OntologyTerm newTerm = createOntologyTerm(newTermName, extendOntology);
      itemTerms.add(new ScoredTerm(newTerm, 1.0));

      createOntologyRelation(OntologyRelation.GENERALIZATION_RELATION, newTerm, parentTerm);
      for (OntologyTerm typeTerm: typeTerms) {
        createOntologyRelation(OntologyRelation.TYPE_RELATION, newTerm, typeTerm);
      }
      parentTerm = newTerm;
    }
    return parentTerm;
  }

  public OntologyTerm createOntologyTerm(String name, boolean createIfMissing) {
    if (terms.containsKey(name)) {
      return terms.get(name);
    } else if (createIfMissing) {
      OntologyTerm newTerm = new OntologyTerm(name);
      terms.put(name,  newTerm);
      return newTerm;
    } else {
      return null;
    }
  }
  
  public OntologyRelation createOntologyRelation(String relation, OntologyTerm arg1, OntologyTerm arg2) {
    if (arg1 == arg2 || arg1.getHasValue().equals(arg2.getHasValue())) {
      logWriter.error("invalid self arc, ignoring "+arg1.getHasValue()); 
      return null;
    }
    String key = relation+"("+arg1.getHasValue()+","+arg2.getHasValue()+")";
    if (relations.containsKey(key)) {
      return relations.get(key);
    } else {
      OntologyRelation newRelation = new OntologyRelation(relation, arg1, arg2);
      relations.put(key,  newRelation);
      return newRelation;
    }

  }

}
