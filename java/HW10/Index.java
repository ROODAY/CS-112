/* 
 * File: Index.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 11/17/2016
 * Purpose: This class 
 */

import java.io.*;
import java.util.*;

public class Index { 
  
  // Nodes for linked lists of values
  private  class Node {
    String value;
    Node next;
    
    public Node(String v) {
      value = v;
      next = null;
    }

    public Node(String v, Node n) {
      value = v;
      next = n;
    }
  }

  // nodes for the binary tree
  private class TreeNode {
    String key;
    Node values;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(String k) {
      key = k;
      values = null;
      left = null;
      right = null;
    }

    public TreeNode(String k, Node v) {
      key = k;
      values = v;
      left = null;
      right = null;
    }

    public TreeNode(String k, Node v, TreeNode l, TreeNode r) {
      key = k;
      values = v;
      left = l;
      right = r;
    }
  }
  
  
  private TreeNode root = null;
  
  // Standard utility methods
  
  
  // Size is number of nodes in the tree
  public int size() {
    return size(root);
  }
  
  private int size(TreeNode t) {
    if (t == null)
      return 0;
    else
      return 1 + size(t.left) + size(t.right);
  }
  
  // Height of a binary tree is number of links in longest path, note that
  //     empty tree has height -1.
  
  public int height() {
    return height(root);
  }
  
  private int height(TreeNode t) {
    if (t == null)
      return -1;
    else 
      return 1 + Math.max( height(t.left), height(t.right) ); 
  }
   
  public void printTree() {
    printTree(root, "");  
  }
  
  private void printTree(TreeNode t, String indent) {
    if(t != null) {
      printTree(t.right, indent + "\t");
      System.out.println(indent + t); 
      printTree(t.left, indent + "\t"); 
    }
  }
  
  public void printTreeKeys() {
    printTreeKeys(root, "");  
  }
  
  private void printTreeKeys(TreeNode t, String indent) {
    if(t != null) {
      printTreeKeys(t.right, indent + "\t");
      System.out.println(indent + t.key); 
      printTreeKeys(t.left, indent + "\t"); 
    }
  }
  
  
  // Interface methods
  
  public void insert(String key) {
    insertHelper(key, root);
  }

  private void insertHelper(String key, TreeNode n) {
    if (n == null) {
      n = new TreeNode(key);
    } else {
      if (key.compareTo(n.key) < 0) {
        insertHelper(key, n.left);
      } else if (key.compareTo(n.key) > 0) {
        insertHelper(key, n.right);
      }
    }
  }
  
  public void insert(String key, String val) {
    insertHelper2(key, val, root);
  }
  
  private void insertHelper2(String key, String val, TreeNode n) {
    if (n == null) {
      n = new TreeNode(key, new Node(val));
    } else {
      if (key.compareTo(n.key) < 0) {
        insertHelper2(key, val, n.left);
      } else if (key.compareTo(n.key) > 0) {
        insertHelper2(key, val, n.right);
      } else {
        insertIntoLL(val, n.values);
      }
    }
  }
 
  
  public void insert(String key, String[] values) {
     
  }
  
 
  
  public String getValues(String title) {
    return null;     // just to get it to compile 
  }
 
  

  public void delete(String key) {
    
  }
  
  private void insertIntoLL(String val, Node n) {
    if (n == null) n = new Node(val);
    else insertIntoLL(val, n.next);
  }
  
  // methods to build inverted index
  
  public Index getInvertedIndex() {

    return null;   // just to get it to compile
  }
 
  
  
  // unit test
  
  public static void main (String[] args) { 
    Index D = new Index(); 
    String[][] V = {
      {"Coke", "Salad", "Pasta" },            
      {"Pepsi", "Salad", "Chicken", "Salad" },   // Node duplicate value 
      {"Chicken", "Pasta"} };
     
    D.insert("Ringo",  V[0]);
    D.insert("John",  V[1]);
    D.insert("George", V[2]);
    D.insert("George", "Pasta");       // duplicate, should not be inserted
    D.insert("George", "Milk");
    D.insert("Wayne"); 
    D.insert("Paul"); 
    D.insert("Paul", "Pasta");
    D.insert("Paul", "Beef");
    D.insert("Paul", "Coke");
    D.insert("Paul", "Pasta");         // duplicates, should not be inserted
    D.insert("Paul"); 
    D.insert("Wayne"); 
    
    
    System.out.println("\n[1] Printing keys of tree, should be:\n");
    System.out.println("\tWayne\n"
                         + "Ringo\n"
                         + "\t\tPaul\n"
                         + "\tJohn\n" 
                         + "\t\tGeorge\n");
    
    D.printTreeKeys(); 
    
    System.out.println("\n[2] Printing tree, should be:\n");
    System.out.println("\tWayne:[]\n"
                         + "Ringo:[Coke;Salad;Pasta]\n"
                         + "\t\tPaul:[Pasta;Beef;Coke]\n"
                         + "\tJohn:[Pepsi;Salad;Chicken]\n" 
                         + "\t\tGeorge:[Chicken;Pasta;Milk]\n");
    
    D.printTree(); 
    
    System.out.println("\n[3]: Testing size, should print out:\n5");
    System.out.println(D.size()); 
    
    System.out.println("\n[4]: Testing height, should print out:\n2");
    System.out.println(D.height());
    
    /*System.out.println("\n[5]: Testing getValues, should print out:\n[Chicken;Pasta;Milk]");
    System.out.println(D.getValues("George")); 
    
    System.out.println("\n[6]: Testing getValues, should print out:\n[]");
    System.out.println(D.getValues("Wayne")); 
    
    System.out.println("\n[7]: Testing getValues, should print out:\nnull");
    System.out.println(D.getValues("Ozzy")); 
    
    System.out.println("\n[8]: Testing delete, should print out:\n");
    System.out.println("Wayne\n"
                         + "\t\tPaul\n"
                         + "\tJohn\n"
                         + "\t\tGeorge\n");
    D.delete("Ringo"); 
    D.printTreeKeys();  
    
    System.out.println("\n[9]: Testing getValues, should print out:\nnull");
    System.out.println(D.getValues("Ringo"));  
    
    System.out.println("\nBuilding Inverted Index....\n"); 
    Index InvD = D.getInvertedIndex(); 
   
    System.out.println("[10] Printing tree, should be:\n");
    System.out.println("\tSalad:[John]\n"
                         + "Pepsi:[John]\n"
                         + "\t\tPasta:[George;Paul]\n"
                         + "\t\t\tMilk:[George]\n"
                         + "\t\t\t\tCoke:[Paul]\n"
                         + "\tChicken:[John;George]\n"
                         + "\t\tBeef:[Paul]\n");
    
    InvD.printTree(); 
    
    System.out.println("\n[12]: Testing size, should be:\n7");
    System.out.println(InvD.size()); 
    
    System.out.println("\n[13]: Testing height, should be:\n4");
    System.out.println(InvD.height()); 
    
    System.out.println("\n[14]: Testing getValues, should be:\n[George;Paul]");
    System.out.println(InvD.getValues("Pasta")); 
    
    System.out.println("\n[15]: Testing getValues, should be:\nnull");
    System.out.println(InvD.getValues("Pork"));
    
   */    
  }
}