package com.Tuuyi.TMPDataModel.generatedClasses.TMPDataModel;

import java.util.ArrayList;
import java.util.List;

public class Path {

  /**
   * 
   * @param rootId - external questionId the root of a possible hierarchical question
   * @param path - a path if hierarchical, or null or empty list if not
   * @return
   */
  public static String constructNodeName(Long rootId, List<Integer> path) {
    // find question and assumptions
    StringBuffer nameBuf = new StringBuffer();
    nameBuf.append("Q"+rootId);
    if (path != null) {
      for (Integer step: path) {
        nameBuf.append("."+step);
      }
    }
    return nameBuf.toString();
  }
  
  public static ArrayList<HierarchicalNode> nodeCover(HierarchicalNode node, List<Integer> start, List<Integer> end) {
    ArrayList<HierarchicalNode> cover = new ArrayList<HierarchicalNode> ();
    nodeCoverAux(node, start, end, cover, new ArrayList<Integer>());
    return cover; 
  }
  
  public static ArrayList<HierarchicalNode> nodeCoverAux(HierarchicalNode node, List<Integer> start, List<Integer> end, 
      ArrayList<HierarchicalNode> cover, ArrayList<Integer> pathFromRoot) {
    
    if (le(start, pathFromRoot) && le(pathFromRoot, end) ) {
      cover.add(node);
      return cover;
    } else {
      int childIndex = 0;
      for (HierarchicalNode child: node.getDown()) {
        ArrayList<Integer> childPathFromRoot = new ArrayList<Integer>(pathFromRoot);
        childPathFromRoot.add(childIndex);
        nodeCoverAux(child, start, end, cover, childPathFromRoot );
        childIndex++;
      }
    }
    return cover; 
  }
  
  public static HierarchicalNode getNextSibling(HierarchicalNode node) {
    if (node.getParent() == null) {
      return null;
    } else {
      int loc = node.getParent().getDown().indexOf(node);
      if (loc < 0) {
        throw new IllegalArgumentException("node not found in parent's children");
      } else {
        if (loc >= node.getParent().getDown().size()) {
          // this is last child
          return null;
        } else {
          return node.getParent().getDown().get(loc+1);
        }
      }
    }
  }
  public static boolean le(List<Integer> start, List<Integer> end) {
    if ( start == null || end == null ) {
      throw new IllegalArgumentException("null argument to path lessThanOrEqual ");
    }
    if (start.size() != end.size()) {
      //throw new IllegalArgumentException("path lessThanOrEqual args must be same length");
    }
    return leAux(start, end, 0);
  }
  
  public static boolean leAux(List<Integer> path, List<Integer> bound, int index) {
    if (path.size() <= index && bound.size() <= index) {
      return true;
    } else  if (bound.size() <= index) {
      // end doesn't index this level, so path must be all zero from here.
      for (int pathStep = index; pathStep < path.size(); pathStep++) {
        if (path.get(pathStep) > 0) {
          return false;
        }
      }
      return true;
    } else if (path.size() <= index) {
      // end does index this level, but start doesn't
      throw new IllegalArgumentException("path lessThanOrEqual args must be same length");
    } else if (path.get(index) < bound.get(index)) {
      return true;
    } else if (path.get(index) == bound.get(index)) {
      return leAux(path, bound, index+1);
    } else {
      return false;
    }
  }
  
  public static Node leftMostLeaf(HierarchicalNode node) {
    if (node.getDown() == null || node.getDown().size() == 0) {
      return node;
    } else {
      return leftMostLeaf(node.getDown().get(0));
    }
  }
  
  public static Node rightMostLeaf(HierarchicalNode node) {
    if (node.getDown() == null || node.getDown().size() == 0) {
      return node;
    } else {
      return rightMostLeaf(node.getDown().get(node.getDown().size()-1));
    }
  }
  
  static public void main(String [] args) {
    ArrayList<Integer> path1 = new ArrayList<Integer>();
    ArrayList<Integer> path2 = new ArrayList<Integer>();
    System.out.println("{} <= {}: "+le(path1, path2));

    path1.clear(); path2.clear();
    path1.add(1); path2.add(0);
    System.out.println("{1} <= {0}: "+le(path1, path2));

    path1.clear(); path2.clear();
    path1.add(0); path2.add(0);
    System.out.println("{0} <= {0}: "+le(path1, path2));

    path1.clear(); path2.clear();
    path1.add(0); path2.add(1);
    System.out.println("{0} <= {1}: "+le(path1, path2));

    
    path1.clear(); path2.clear();
    path1.add(1); path2.add(0);
    path1.add(0); path2.add(0);
    System.out.println("{1,0} <= {0,0}: "+le(path1, path2));

    path1.clear(); path2.clear();
    path1.add(0); path2.add(0);
    path1.add(0); path2.add(0);
    System.out.println("{0,0} <= {0,0}: "+le(path1, path2));

    path1.clear(); path2.clear();
    path1.add(0); path2.add(0);
    path1.add(0); path2.add(1);
    System.out.println("{0,0} <= {0,1}: "+le(path1, path2));

    path1.clear(); path2.clear();
    path1.add(0); path2.add(0);
    path1.add(1); path2.add(0);
    System.out.println("{0,1} <= {0,0}: "+le(path1, path2));

    
  }
  
  
}
