/* 
 * File: RecursiveLinkedLists.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 11/08/2016
 * Purpose: This class is simply to practice recursive linked-list algorithms
 */

public class RecursiveLinkedLists {
    
    public static class Node {
        int item;
        Node next;
        public Node(int it, Node n) {
            item = it; next = n;
        }
        public String toString() {
            if (this.next == null) {
                return " -> " + this.item + " -> .";
            }
            else {
                return " -> " + this.item + next;
            }
        }
    }
    
    // utility method from notes on linked lists and recursion, if needed
    
    public static Node copy( Node p ) {
        if( p == null )
            return null; 
        else 
            return new Node(p.item, copy( p.next )); 
    } 
    
    
    // Problem B.2.A
    
    public static Node arrayToLinkedList(int[] A) {
        // your code here
        return null;   // just to get it to compile
    }
    
    
    // Problem B.2.B
    
    public static int count(int n, Node p) {
        // your code here
        return 0;   // just to get it to compile
    }
    
    // Problem B.2.C
    
    public static double mean(Node p) {
        // your code here
        return 0.0;   // just to get it to compile
    }
    
    // Problem B.2.D
    
    public static Node deleteNth(Node p, int n ) {
        // your code here
        return null;   // just to get it to compile
    } 
    
    
    // Problem B.2.E
    
    public static Node everyOther(Node p) {
        // your code here
        return null;   // just to get it to compile
    }
    
    // Problem B.2.F
    
    public static boolean equalLists(Node p,Node q) {
        // your code here
        return false;   // just to get it to compile        
    }    
    
    // Problem B.2.G
    
    public static boolean prefix(Node p, Node q)  {
        // your code here
        return false;   // just to get it to compile
    }
    
    // Problem B.2.H
    
    public static boolean sublist(Node p, Node q)  {
        // your code here
        return false;   // just to get it to compile
    }
    
    
    // Problem B.2.I
    
    public static Node splice(int n, Node p, Node q)  {
        // your code here
        return null;   // just to get it to compile
    }     
    
    
    // Problem B.2.J   
    
    public static Node intersection(Node p,Node q) {
        // your code here
        return null;   // just to get it to compile
    }
    
    
    
    public static void main(String [] args) {
        
         Node head = new Node(3, new Node(6, new Node( 9, new Node(12, null ) ) ) );
         
         // Sample tests just to show how toString() prints out lists (note that empty lists are null,
         // so there is no toString() to call on a null pointer, and it just prints "null")
         
         System.out.println("\nTest 00a:  should print out:\n -> 3 -> 6 -> 9 -> 12 -> .");
         System.out.println(head); 
         
         head = null; 
         System.out.println("\nTest 00b:  should print out:\nnull");
         System.out.println(head); 
         
        // your tests here 
        
    }
    
}


