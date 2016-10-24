/* 
 * File: LinkedListPractice.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 10/22/2016
 * Purpose: This class is simply to practice iterative linked-list algorithms
 */

public class LinkedListPractice {
    
    private static class Node {
        int item;
        Node next;
        
        public Node(int n) {
            item = n; next = null;
        }
        
        public Node(int n, Node p) {
            item = n; next = p;
        }
    }
    
    // Utility method to print out the list (from the Notes on Iterative LL Algorithms on website)
    
    private static String listToString(Node h) {
        String s = "";
        for(Node p = h; p!=null; p = p.next) {
            s += (p.item + " -> ");
        }
        return s + ".";
    }
    
    // Utility method to make a copy of the list (from the Notes on Iterative LL Algorithms on website)
    
    private static Node copy(Node h) {
        Node p = h; 
        if(p == null)                   // Case 1: list is empty
            return null;       
        else {                          // Case 2: list has at least one node
            Node q = new Node(p.item);    //    put first node in place in copy
            Node last = q;                // Maintain a pointer to the last node to facilitate adding to the end of the list
            p = p.next;
            while( p != null ) {
                last.next = new Node(p.item); 
                last = last.next;           // chain along original list and with the last node in new list
                p = p.next; 
            }
            return q; 
        }
    }   
    
    // create a linked list from an array, in the same order
    
    public static Node arrayToLinkedList(int[] A) {
        Node head = new Node(A[0], null);
        Node p = head;
        for (int i = 1; i < A.length; i++) {
            Node n = new Node(A[i], null);
            p.next = n;
            p = n;
        }
        return head;
    }
    
    // find the maximum of the integers in the list 
    // if the list is empty return Integer.MIN_VALUE (the largest magnitude negative int)
    
    public static int maximum(Node h) {
        if (h == null) return Integer.MIN_VALUE;
        int max = 0;
        Node p = h;
        while (p != null) {
            if (p.item > max) max = p.item;
            p = p.next;
        }
        return max;
    }
    
    // find the mean (average) of the integers in the list with one pass through the list
    // i.e., you may not chain-along through the list more than once
    // if the list is empty return 0.0
    
    public static double mean(Node h) {
        if (h == null) return 0.0;
        double total = 0.0;
        double count = 0.0;
        Node p = h;
        while (p != null) {
            total += p.item;
            count += 1.0;
            p = p.next;
        }
        return total / count;
    }
    
    // find the population standard deviation of the integers in the list with one pass through the list
    // if the list is empty return 0.0
    // Hint: look at the formula just above the phrase "(derived using...." here:
    //    https://en.wikipedia.org/wiki/Standard_deviation#Definition_of_population_values
    // where E[X] is just the mean of the array of values X.  Keep a running sum, a running
    // sum of the squares, and do the calculation at the end using Math.sqrt(....)
    
    public static double stdDev(Node h) {
        if (h == null) return 0.0;
        double mean = mean(h);
        Node p = h;
        while (p != null) {
            total += p.item;
            count += 1.0;
            p = p.next;
        }
               // your code here
        return 0.0;          // just to get it to compile
    }
    
    // Add list q to the end of list p. You do not have to make a copy of either list, just
    // find the last node of p and point that node to q, so that both lists are combined.
    
    public static Node append(Node p, Node q) { 
        // your code here
        return null;          // just to get it to compile          
    }
    
    // Return the reverse list consisting of the 1st, 3rd, 5th, etc. members; 
    // you may modify the list h (you don't have to make a copy).
    // Look at the Unit Tests to see examples of this; should work for
    // lists of any length. Hint: modify reverse from the Notes using
    // a counter to see where the odd-numbered nodes are
    
    public static Node reverseEveryOther(Node h) { 
        // your code here
        return null;          // just to get it to compile
    }
    
    // Return the list h with the nth node deleted, where 1 = first node in list, 2 = second, etc.
    // If n is bigger than the length of the list, do nothing. 
    // Hint: add a counter to the delete method from the Notes
    
    public static Node deleteNth(Node h, int n ) {
        // your code here
        return null;          // just to get it to compile
    } 
    
    // return true if the two lists contain the same numbers in the same order
    // Hint: Chain-along with two pointers instead of one
    
    public static boolean equalLists(Node p,Node q) {
        // your code here
        return false;          // just to get it to compile
    }
    
    // return true if p is a prefix of q, i.e., p has all the same
    // numbers as q in the same positions, but perhaps q has additional elements at the end
    
    public static boolean prefix(Node p, Node q) {
        // your code here
        return false;          // just to get it to compile
    }
    
    // return the zip of two ordered lists (1st of p, then 1st of q, then 2nd of p, then 2nd of q, etc.
    // should work for lists of any size
    
    public static Node zip(Node p, Node q) {
        // your code here
        return null;          // just to get it to compile
    }
    
    
    
// rewrite the insertInOrder method from the Notes on Iterative LL Algorithms
// so that it takes a node n instead of an int n, and it inserts this node
// into the list h in ascending order. You should not create any new nodes in this method. 
    
    private static Node insertInOrder(Node n, Node h) {
        // your code here
        return null;          // just to get it to compile
    }
    
    
// Perform insertion sort on the list p;  
// You must use the previous method as a helper, and you may NOT create any new nodes,
// but must rearrange the existing nodes into ascending order. 
    
    private static Node insertionSort(Node p) {
        // your code here
        return null;          // just to get it to compile
    }
    
    
    
    
    
    public static void main(String [] args) {
        
        System.out.println("Unit Test for LinkedListPractice");
        
        String solution;
        String answer; 
        
        Node head = new Node(3, new Node(6, new Node( 9, new Node(12, null ) ) ) );
        
        solution = "3 -> 6 -> 9 -> 12 -> .";
        answer = listToString(head); 
        System.out.println("\nTest 01:  Should print out:\n" + solution);
        System.out.println(answer); 
        
 //    Use step-wise refinement: uncomment one test at a time and work on one method at a time
  
        solution = "."; 
        answer = listToString(null);
        System.out.println("\nTest 02: Should print out:\n" + solution);
        System.out.println(answer); 
       
        solution = "-2147483648"; 
        answer = "" + maximum(null);
        System.out.println("\nTest 03: Should print out:\n" + solution);
        System.out.println(answer);  
        
        solution = "12"; 
        answer = "" + maximum(arrayToLinkedList( new int[] { 3, 5, 12, 8 }));
        System.out.println("\nTest 04: Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "0.0"; 
        answer = "" + mean(null);
        System.out.println("\nTest 05: Should print out:\n" + solution);
        System.out.println(answer);  
        
        solution = "7.5"; 
        answer = "" + mean(head);
        System.out.println("\nTest 06: Should print out:\n" + solution);
        System.out.println(answer);
        /*
        solution = "0.0"; 
        answer = "" + stdDev(null);
        System.out.println("\nTest 07: Should print out:\n" + solution);
        System.out.println(answer);  
        
        solution = "7.7781745930520225"; 
        answer = "" + stdDev(head);
        System.out.println("\nTest 08: Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "3 -> 6 -> 9 -> 12 -> ."; 
        answer = "" + listToString(append(head,null));
        System.out.println("\nTest 09: Should print out:\n" + solution);
        System.out.println(answer); 
        
        solution = "3 -> 6 -> 9 -> 12 -> ."; 
        answer = "" + listToString(append(null,head));
        System.out.println("\nTest 10: Should print out:\n" + solution);
        System.out.println(answer);       
        
        Node head2 = arrayToLinkedList(new int[] {2,5,4,6,2,9});
        
        solution = "3 -> 6 -> 9 -> 12 -> 2 -> 5 -> 4 -> 6 -> 2 -> 9 -> ."; 
        answer = "" + listToString(append(copy(head),copy(head2)));
        System.out.println("\nTest 11: Should print out:\n" + solution);
        System.out.println(answer); 
        
        solution = "."; 
        answer = "" + listToString(reverseEveryOther(null));
        System.out.println("\nTest 12: Should print out:\n" + solution);
        System.out.println(answer);  
        
        solution = "9 -> 3 -> ."; 
        answer = "" + listToString(reverseEveryOther(head));
        System.out.println("\nTest 13: Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "12 -> 6 -> ."; 
        answer = "" + listToString(reverseEveryOther( head.next ));
        System.out.println("\nTest 14: Should print out:\n" + solution);
        System.out.println(answer);        
        
        Node head3 = arrayToLinkedList(new int[] {4,5,7});
        Node head4 = arrayToLinkedList(new int[] {4,5,7,2,8,1});
        
        solution = "2 -> 5 -> 6 -> 2 -> 9 -> ."; 
        answer = "" + listToString(deleteNth(copy(head2),3));
        System.out.println("\nTest 15: Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "5 -> 4 -> 6 -> 2 -> 9 -> ."; 
        answer = "" + listToString(deleteNth(copy(head2),1));
        System.out.println("\nTest 16: Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "2 -> 5 -> 4 -> 6 -> 2 -> 9 -> ."; 
        answer = "" + listToString(deleteNth(copy(head2),13));
        System.out.println("\nTest 17: Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "."; 
        answer = "" + listToString(deleteNth(null,1));
        System.out.println("\nTest 18: Should print out:\n" + solution);
        System.out.println(answer);
        
        int[] D = {2,6,7,9};
        int[] E = {4,5,8,10};
        int[] F = {4,5,8,10};
        Node headD = arrayToLinkedList(D);
        Node headE = arrayToLinkedList(E);
        Node headF = arrayToLinkedList(F);      
        
        solution = "true"; 
        answer = "" + equalLists(null,null);
        System.out.println("\nTest 19: Should print out:\n" + solution);
        System.out.println(answer);      
                
        solution = "false"; 
        answer = "" + equalLists(null,headD);
        System.out.println("\nTest 20: Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "false"; 
        answer = "" + equalLists(headE,headD);
        System.out.println("\nTest 21: Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "true"; 
        answer = "" + equalLists(headF,headE);
        System.out.println("\nTest 22: Should print out:\n" + solution);
        System.out.println(answer);         
        
        solution = "true"; 
        answer = "" + prefix(null,head);
        System.out.println("\nTest 23: Should print out:\n" + solution);
        System.out.println(answer);      
        
        solution = "true"; 
        answer = "" + prefix(head, arrayToLinkedList(new int[] {3, 6, 9, 12, 16}));
        System.out.println("\nTest 24: Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "true"; 
        answer = "" + prefix(head,head);
        System.out.println("\nTest 25: Should print out:\n" + solution);
        System.out.println(answer); 
        
        solution = "false"; 
        answer = "" + prefix(head,head2);
        System.out.println("\nTest 26: Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "false"; 
        answer = "" + prefix(head,null);
        System.out.println("\nTest 27: Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "false"; 
        answer = "" + prefix(head, arrayToLinkedList(new int[] {3, 6, 9} ));
        System.out.println("\nTest 28: Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "3 -> 6 -> 9 -> 12 -> ."; 
        answer = "" + listToString(zip(copy(head), null));
        System.out.println("\nTest 29: Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "3 -> 6 -> 9 -> 12 -> ."; 
        answer = "" + listToString(zip(null, copy(head)));
        System.out.println("\nTest 30: Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "2 -> 4 -> ."; 
        answer = "" + listToString(zip(new Node(2), new Node(4)));
        System.out.println("\nTest 31: Should print out:\n" + solution);
        System.out.println(answer); 
        
        solution = "5 -> 4 -> 2 -> ."; 
        answer = "" + listToString(zip(new Node(5, new Node(2)), new Node(4)));
        System.out.println("\nTest 32: Should print out:\n" + solution);
        System.out.println(answer);   
        
        solution = "4 -> 7 -> 1 -> ."; 
        answer = "" + listToString(zip(new Node(4), new Node(7, new Node(1)) ));
        System.out.println("\nTest 33: Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "3 -> 14 -> 6 -> 5 -> 9 -> -3 -> 12 -> 15 -> 25 -> ."; 
        answer = "" + listToString(zip(copy(head), arrayToLinkedList(new int[] {14,5,-3,15,25} ) ));
        System.out.println("\nTest 34: Should print out:\n" + solution);
        System.out.println(answer);
           
         solution = "2 -> 3 -> 6 -> 9 -> 12 -> ."; 
         answer = "" + listToString(insertInOrder(new Node(2), copy(head)));
         System.out.println("\nTest 35: Should print out:\n" + solution);
         System.out.println(answer);
         
         solution = "3 -> 6 -> 8 -> 9 -> 12 -> ."; 
         answer = "" + listToString(insertInOrder(new Node(8), copy(head)));
         System.out.println("\nTest 36: Should print out:\n" + solution);
         System.out.println(answer);
         
         solution = "3 -> 6 -> 9 -> 12 -> 15 -> ."; 
         answer = "" + listToString(insertInOrder(new Node(15), copy(head)));
         System.out.println("\nTest 37: Should print out:\n" + solution);
         System.out.println(answer);
         
         solution = "15 -> ."; 
         answer = "" + listToString(insertInOrder(new Node(15), null));
         System.out.println("\nTest 38: Should print out:\n" + solution);
         System.out.println(answer);
         
         solution = "3 -> 6 -> 9 -> 12 -> 15 -> ."; 
         answer = "" + listToString(insertionSort(arrayToLinkedList(new int[] {15, 6, 3, 9, 12} )));
         System.out.println("\nTest 39: Should print out:\n" + solution);
         System.out.println(answer);    
         
         // just for fun....
         
         Node list1 = arrayToLinkedList( new int[] { 5, 23, 73, 104, 7, 9 } );
         Node list2 = arrayToLinkedList( new int[] { 15, 70, 3, 99, 73, 91 } );
         Node list3 = arrayToLinkedList( new int[] { 1, -2, 45, 64, 8, 12, 66, 999, 23 } );
         Node list = append(list1,list2);
         list = deleteNth(deleteNth(list,3),8);
         list = reverseEveryOther(list);
         list = zip(list,list3);
         list = insertionSort(list);
         solution = "-2 -> 1 -> 5 -> 8 -> 9 -> 12 -> 23 -> 45 -> 64 -> 66 -> 70 -> 73 -> 104 -> 999 -> ."; 
         answer = "" + listToString(list);
         System.out.println("\nTest 40: Should print out:\n" + solution);
         System.out.println(answer);
 */ 
    }
    
    
}


