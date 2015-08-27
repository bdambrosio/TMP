package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import org.json.JSONArray;

public class AbstractionAddlMethods extends Abstraction {

  private Abstractor abstractor;
  
  public AbstractItem abstractItem(CatalogItem item) {
    return abstractor.map(item);
  }
    
  public Abstractor getAbstractor() {
    return abstractor;
  }
  public void setAbstractor(Abstractor abstractor) {
    this.abstractor = abstractor;
  }

  public void useDefaultAbstractor() {
    this.abstractor = new Abstraction.DefaultAbstractor();
  }

  
//DL  public Abstraction(Abstractor a) {
//DL    abstractor = a;
//DL  }
  
  long now = System.currentTimeMillis();
  public class DefaultAbstractor implements Abstractor {
    HashSet<String> rootCategories = new HashSet<String>();
    public AbstractItem map(CatalogItem item) {
      AbstractItem abstractItem = new AbstractItem(false);
      abstractItem.setItemId(item.getItemId());
      HashSet<MapReduceItem> abstraction = new HashSet<MapReduceItem> ();
      Iterator<String> keys = item.getRawCatalogData().keys();
      while ( keys.hasNext() ) {
        String key = keys.next();
        if (key.startsWith("Description") || key.startsWith("Content")) {
          continue;
        }
        if (key.startsWith("Title")) {
          continue;
        }
        Object value = item.getRawCatalogData().opt(key);
        if (value != null && JSONArray.class.isInstance(value)) {
          JSONArray valueArray = item.getRawCatalogData().optJSONArray(key);
          for (int i = 0; i < valueArray.length(); i++) {
            String valueArrayElement = valueArray.optString(i);
            MapReduceItem st = new MapReduceItem(key+":"+valueArrayElement, 1.0);
            abstraction.add(st);
          }
        } else if (value != null && String.class.isInstance(value)) {
          MapReduceItem st = new MapReduceItem(key+":"+value, 1.0);
          abstraction.add(st);
        } else if (value != null && Date.class.isInstance(value) && key.equals("PublicationDate")) {
          MapReduceItem st = new MapReduceItem(key+":"+(now -((Date)value).getTime())/84600000 , 1.0);
          abstraction.add(st);
        } else if (value != null) {
          logWriter.warn("Unknown data type in catalog data, skipping "+key+": "+value.getClass().getSimpleName());
        }
      }
      //[category:Sheers, Attributes:Colour.Black, category:Classic>Sheer Tights, category:Specialist>Extra Tall>5'10'' - 5'11'', 
      // Attributes:Colour.African, Brand:Levante, category:Fashion>Coloured Hosiery>Nude>Sunkissed, Attributes:Size.Small, 
      // category:Fashion>Coloured Hosiery>Nude>English Rose, category:findit>Top 10, Attributes:Colour.Asian, 
      // Title:Levante Class Tights, Attributes:Colour.English Rose, category:Specialist>Extra Tall>6'1'' +, 
      // Attributes:Size.Medium, category:findit>Sheer: 10 - 20 Denier, Attributes:Size.Tall, category:Classic, 
      // Content:These Levante Class 12 denier glossy tights are made in Italy using quality hosiery yarns and are some of the longest-lasting sheer tights around..., 
      // category:findit>Reinforced Toe, category:findit>Levante Class, category:Specialist>Extra Tall>6'0'', Attributes:Colour.Sunkissed, category:Specialist>Extra Tall, 
      // category:findit>Shiny, clientEntityId:1017, 
      // Description:These Levante Class 12 denier glossy tights are made in Italy using quality hosiery yarns and are some of the longest-lasting sheer tights around.., 
      // category:findit>Mother's Day Gift Ideas, category:Tights, category:Fashion>Coloured Hosiery>Navy, Attributes:Colour.Mediterranean, 
      // Description.length:long, category:Fashion>Coloured Hosiery>Grey, category:findit>Catalogue Sheer's, Attributes:Colour.Navy, Attributes:Size.X Tall, 
      // Attributes:Colour.Grey, category:Fashion>Coloured Hosiery>Black, 
      // PublicationDate:1277, category:Sheers>Classic Pantyhose, category:Tights>Sheer Tights]
      // first find main category to prepend to attributes      
      ArrayList<String> rootCategories = new ArrayList<String>();
      for (MapReduceItem token: abstraction) {
        if (!token.getItem().startsWith("category:")) {
          continue;
        }
        
        int colon = token.getItem().indexOf(":");
        if (colon > 0) {
          String root = token.getItem().substring(0, colon);
          String tail = token.getItem().substring(token.getItem().indexOf(":")+1);
          if (root.equalsIgnoreCase("category") && !tail.startsWith("Brand") && tail.matches("^[a-zA-Z]*$")) {
            rootCategories.add(tail);
          }
        }
      }
      // now create actual strings to be parsed. 
      ArrayList<MapReduceItem> newTokens = new ArrayList<MapReduceItem>();
      for (MapReduceItem token: abstraction) {
        if (!token.getItem().startsWith("category:") && !token.getItem().startsWith("PublicationDate") && rootCategories.size() > 0) {
          String tail = token.getItem();
          String expandedValue = "category:"+rootCategories.get(0)+";"+tail;
          token.setItem(expandedValue);
          for (int i = 1; i < rootCategories.size(); i++) {
            MapReduceItem addlToken = new MapReduceItem();
            expandedValue = "category:"+rootCategories.get(i)+";"+tail;
            addlToken.setItem(expandedValue);
            addlToken.setScore(1.0/rootCategories.size());
            newTokens.add(addlToken);
          }
        }
      }
      abstraction.addAll(newTokens);
      abstractItem.setHasAbstractionMapping(abstraction);
      return abstractItem;
    }

  }

}
