/* 
 * File: SymbolTable.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 11/08/2016
 * Purpose: This is the template for HW09 for CS 112; it is basically an implementation of the
 *          ordered symbol table specified in Sedgewick's Algorithms, http://algs4.cs.princeton.edu/31elementary/, 
 *          except that keys must be Strings and instead of keys() we use iterator().  
 */

import java.util.Iterator;

public class SymbolTable<Value> {

/*   NOTE:  This file will not compile unless it contains the iterator() method required
 *     by the iterface Iterable. So wait until you are ready to implement the iterators
 *     and then substitute the next line for the first line of the class definition
 
public class SymbolTable<Value> implements Iterable<String>{

*/
    
    // basic definition of the Node class; this is an "inner class" inside class SymbolTable
    
    private class Node {                 // Node class for LLs
        String key;
        Value value;
        Node next;
        
        public Node(String k, Value v, Node p) {
            key = k; value = v; next = p;
        }
    }
    
    // pointer to list of bindings for symbol table
    
    private Node head = null; 
    
    // length of linked list; will be updated whenever adding or deleting a node
    
    private int size = 0; 
    
    
//     put(..):  If the variable var is not in the symbol table, insert a new
//     node containing k and value into the linked list in ascending order
//     (do NOT sort the list, but insert in order of the variable field); if var is already in
//     the table, then simply replace the existing value with the new value val. 
//     The type Value is a generic type. 
    
    public void put(String k, Value value) {
        // your code here
    }
      
//  get(...): Return the value associated with the variable var, or return null
//  if k is not in the table.
    
    public Value get(String k) {
        // your code here
        return null;   // just to get it to compile
    }
    
    // Just a membership method
    
    public boolean contains(String k) {
        return (find(k) != null);
    }
    
    public boolean isEmpty() {
        // your code here
        return false;   // just to get it to compile
    } 
    
    // Delete node containing variable k from the table; do nothing if not found
    
    public void delete(String k) {
        // your code here
    }
    
    
    // Return minimal variable in lexicographic ordering, or null if table is empty
    
    public String min() {
        // your code here
        return "";   // just to get it to compile
    }
    
    // Same as last, but for maximum, which is at the end of the LL
    
    public String max() {
        // your code here
        return "";   // just to get it to compile
    }
    
    
//     floor(...): If the table is empty or if var is smaller than the smallest entry, return null; 
//     if var is in the table, return var; otherwise, return the largest variable which is less 
//     than var (the entry just before where var would be inserted into the table). (Do NOT insert
//     var into the table!) This is comparable to the mathematical function floor(...).
    
    public String floor(String k) {
        // your code here
        return "";   // just to get it to compile     
    }

    
//     ceiling(...):  If the table is empty or if var is larger than the largest entry, return null; 
//     if var is in the table, return var; otherwise, return the smallest variable which is larger 
//     than var (the entry just after where var would be inserted into the table). (Do NOT insert
//     var into the table!) This is comparable to the mathematical function ceiling(...). 
    
    public String ceiling(String k) {
        // your code here
        return "";   // just to get it to compile      
    }

    
//     rank(...)   Return the "rank" of var, i.e., the number of entries in the table which are smaller than var; the rank
//     of variables which are in the table is calculated by counting 0, 1, 2, starting at the first node (as
//     if it were an array); if var is not in the table, then it is the rank where var would be if it were 
//     inserted (do NOT insert var into the table). 
    
    public int rank(String k)   {
        // your code here
        return 0;   // just to get it to compile 
    }
    
//     select(...):   Return the variable which is at rank r in the linked list (starting
//     the count at 0 with the first node, as in an array). If r is not the 
//     rank of any element, i.e., it is negative or is equal to or larger than the length 
//     of the linked list, return null.
    
    public String select(int r) {
        // your code here
        return "";   // just to get it to compile 
    }
    
    // delete minimal element; do nothing if table is empty
    
    public void deleteMin() {
       // your code here
    }
    
    // Same as last but for max
    
    public void deleteMax() {
        // your code here
    }
    
    
    // usual ADT interface method, uses private variable to keep track of size
    
    public int size() {
        // your code here
        return 0;   // just to get it to compile 
    } 
    
//     size(...):  Return the number of entries in the table whose variables are
//     in the range [lo .. hi], that is, that are >= lo and <= hi (using
//     the appropriate String comparison operators). [Hint: just recurse through
//     the list, and count the number of nodes that are in the indicated range.]
    
    public int size(String lo, String hi) {
        // your code here
        return 0;   // just to get it to compile           
    } 
    
    
    // Recursive toString() method, used in performance tests in main
    
    public String toString() {
        return toStringHelp(head);
    }
    
    private String toStringHelp(Node q) {
        if(q == null)
            return "";
        else if(q.next == null)
            return ("(" + q.key + "," + q.value + ")");
        else
            return "(" + q.key + "," + q.value + ") : " + toStringHelp(q.next); 
    }

    private Node find(String s) {
        return find(s, head);
    }

    private Node find(String k, Node p) {
        if (k.equals(p.key)) return p;
        if (p.next != null) return find(k, p.next);
        return null;
    }
   
    /*  NOTE: The iterator code will not compile until you put all the methods required
     *    by the interface Iterator<String> inside It, and same for ItRange.
     *    So keep this commented out until you are ready to implement the iterators.
     
    public Iterator<String> iterator() {
        return new It();
    }
    
    // The iterator inner class has access to private array A inside List, and also has a local
    // variable cursor to record where in the iteration we are
    
    private class It implements Iterator<String> {
        
        // your code here, straight-forward modification of code from Lab 09
    }
    
    // similar to previous, but for a specific range
    
    public Iterator<String> iterator(String lo, String hi) {
        return new ItRange(lo,hi);
    }
    
    // The iterator inner class has access to private array A inside List, and also has a local
    // variable cursor to record where in the iteration we are. Enumerates only the range 
    // [lo .. hi].
    
    private class ItRange implements Iterator<String> {
        
          // your code here. Note that you must provide a constructor as shown
          // in the previous method, which takes lower bound lo and upper bound hi
          // and uses them to set the cursor and determine when hasNext() will return false.
          
    }
    
    */
    
    public static void main(String[] arg) {
        
        
        SymbolTable<Integer> S = new SymbolTable<Integer>(); 
        
        // Insert 3 (key,value) pairs and test basic methods
        
        S.put("e",3); 
        S.put("c",1);
        S.put("b",1);
        
 /*    Use step-wise refinement: uncover one test at a time as you develop the methods
 
        System.out.println("\nTest 01: Should print out:\n(b,1) : (c,1) : (e,3)"); 
        System.out.println(S); 
        
        System.out.println("\nTest 02: Should print out:\n3"); 
        System.out.println(S.size()); 
        
        System.out.println("\nTest 03: Should print out:\nfalse"); 
        System.out.println(S.isEmpty()); 
        
        System.out.println("\nTest 04: Should print out:\n1");  
        String testKey = "c"; 
        System.out.println(S.get(testKey)); 
        
        System.out.println("\nTest 05: Should print out:\nnull");  
        testKey = "d"; 
        
        System.out.println(S.get(testKey)); 
        
        System.out.println("\nTest 06: Should print out:\n(b,1) : (c,1) : (e,3) : (s,4)"); 
        S.put("s",4);
        System.out.println(S);    
        
        System.out.println("\nTest 07: Should print out:\ntrue"); 
        System.out.println(S.contains("c"));  
        
        System.out.println("\nTest 08: Should print out:\nfalse"); 
        System.out.println(S.contains("a")); 
        
        System.out.println("\nTest 09: Should print out:\ntrue"); 
        System.out.println(S.contains("e"));  
        
        S.put("y",3); 
        S.put("q",2);
        S.put("k",5);
     
        System.out.println("\nTest 10: Should print out:\nb c e k q s y");
        Iterator<String> it = S.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println(); 
                        
        System.out.println("\nTest 11: Should print out:\nb c e k q s y");                                       
        for(String k : S) {
            System.out.print(k + " ");
        }
        System.out.println(); 
        
        System.out.println("\nTest 12: Should print out:\nb c e k q s y");
        it = S.iterator("b","y");
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println(); 
        
        System.out.println("\nTest 13: Should print out:\nc e k q");
        it = S.iterator("c","q");
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println(); 
        
        System.out.println("\nTest 14: Should print out:\nb c e k q s y");
        it = S.iterator("a", "z");
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println(); 
        
        System.out.println("\nTest 15: Should print out:\nk q s");
        it = S.iterator("f", "t");
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
        
        S.delete("q"); 
        S.delete("e"); 
        S.delete("c");
        S.delete("s"); 
        S.delete("y"); 
        S.delete("k"); 
        System.out.println("\nTest 16: Should print out:\n(b,1)");     
        System.out.println(S); 
        
        System.out.println("\nTest 17: Should print out:\n0");  
        S.delete("b"); 
        System.out.println(S.size()); 
        
        System.out.println("\nTest 18: Should print out:\nnull");  
        System.out.println(S.min()); 
        
        System.out.println("\nTest 19: Should print out:\nnull");  
        System.out.println(S.max()); 
        
        System.out.println("\nTest 20: Should print out:\nnull");  
        System.out.println(S.floor("a")); 
        
        System.out.println("\nTest 21: Should print out:\nnull");  
        System.out.println(S.ceiling("a")); 
        
        System.out.println("\nTest 22: Should print out:\nnull");  
        System.out.println(S.select(0)); 
        
        // Now we will test it on a realistic data set from Sedgewick's Algorithms. With any luck, 
        //    all of these should work fine if the preceding worked!  
        //    Proceed with fingers crossed!
        
        SymbolTable<String> T = new SymbolTable<String>(); 
        
        T.put("09:00:00","Chicago");
        T.put("09:00:03","Phoenix");
        T.put("09:00:13","Houston");
        T.put("09:00:59","Chicago"); 
        T.put("09:36:14","Seattle");
        T.put("09:37:44","Phoenix");
        T.put("09:10:25","Seattle");
        T.put("09:14:25","Phoenix");
        T.put("09:19:32","Chicago");
        T.put("09:19:46","Chicago");
        T.put("09:21:05","Chicago");
        T.put("09:22:43","Seattle");
        T.put("09:01:10","Houston");
        T.put("09:03:13","Chicago");
        T.put("09:10:11","Seattle");
        T.put("09:25:52","Chicago");
        T.put("09:22:54","Seattle");  
        T.put("09:35:21","Chicago");
        
        System.out.println("\nTest 23: Should print out:\n(09:00:00,Chicago) : (09:00:03,Phoenix) : (09:00:13,Houston) : (09:00:59,Chicago) : (09:01:10,Houston) : (09:03:13,Chicago) : (09:10:11,Seattle) : (09:10:25,Seattle) : (09:14:25,Phoenix) : (09:19:32,Chicago) : (09:19:46,Chicago) : (09:21:05,Chicago) : (09:22:43,Seattle) : (09:22:54,Seattle) : (09:25:52,Chicago) : (09:35:21,Chicago) : (09:36:14,Seattle) : (09:37:44,Phoenix)");      
        System.out.println("\n" + T);    
        
        System.out.println("\nTest 24: Should print out:\n09:00:00");
        System.out.println(T.min()); 
        
        System.out.println("\nTest 25: Should print out:\n09:37:44");
        System.out.println(T.max()); 
        
        System.out.println("\nTest 26: Should print out:\n09:03:13");
        System.out.println(T.floor("09:05:00")); 
        
        System.out.println("\nTest 27: Should print out:\n09:35:21");
        System.out.println(T.floor("09:35:21")); 
        
        System.out.println("\nTest 28: Should print out:\n09:35:21");
        System.out.println(T.ceiling("09:30:00")); 

        System.out.println("\nTest 29: Should print out:\n09:00:00");
        System.out.println(T.ceiling("09:00:00")); 

        System.out.println("\nTest 30: Should print out:\n09:10:25");
        System.out.println(T.select(7)); 
 
        System.out.println("\nTest 31: Should print out:\n7");
        System.out.println(T.rank("09:10:25")); 
        
        System.out.println("\nTest 32: Should print out:\n15");
        System.out.println(T.rank("09:30:00"));     
        
        System.out.println("\nTest 33: Should print out:\n5");
        System.out.println(T.size("09:15:00", "09:25:00")); 
        
        System.out.println("\nTest 34: Should print out:\n18 18");
        System.out.println(T.size() + " " + T.size(T.min(), T.max())); 

        System.out.println("\nTest 35: Should print out:\n09:00:03");
        T.deleteMin(); 
        System.out.println(T.min()); 

        System.out.println("\nTest 36: Should print out:\n09:36:14");
        T.deleteMax(); 
        System.out.println(T.max()); 

        System.out.println("\nTest 37: Should print out:\n16");
        System.out.println(T.size()); 
        
        System.out.println("\nTest 38: Should print out:\n(09:00:03 : Phoenix)  (09:00:13 : Houston)  (09:00:59 : Chicago)");
        it = T.iterator("09:00:00", "09:01:00");
        while(it.hasNext()) {
            String s = it.next();
            System.out.print( "(" + s + " : " + T.get(s) + ")  ");
        }
        System.out.println(); 
  */      
        
    }
}


