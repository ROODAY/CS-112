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
        Node head = new Node(A[0], null);
        arrayToLinkedListHelper(A, head, 1);
        return head;
    }
    
    private static void arrayToLinkedListHelper(int[] A, Node current, int index) {
        if (index < A.length) {
            current.next = new Node(A[index], null);
            arrayToLinkedListHelper(A, current.next, ++index);
        }
    }
    
    // Problem B.2.B
    
    public static int count(int n, Node p) {
        return countHelper(n, p, 0);
    }

    private static int countHelper(int n, Node p, int count) {
        if (p.item == n) count++;
        if (p.next == null) return count;
        return countHelper(n, p.next, count);
    }
    
    // Problem B.2.C
    
    public static double mean(Node p) {
        if (p == null) return 0.0;
        return meanHelper(p, 0, 0);
    }

    private static double meanHelper(Node p, double sum, int count) {
        if (p == null) return sum / count;
        else return meanHelper(p.next, (sum + p.item), ++count);
    }
    
    // Problem B.2.D
    
    public static Node deleteNth(Node p, int n ) {
        if (n == 1) return p.next;
        deleteNthHelper(p, n, 1);
        return p;
    }

    private static void deleteNthHelper(Node p, int delIndex, int curIndex) {
        if (curIndex == (delIndex - 1)) p.next = p.next.next;
        else if (p.next != null) deleteNthHelper(p.next, delIndex, ++curIndex);
    }
    
    
    // Problem B.2.E
    
    public static Node everyOther(Node p) {
        if (p == null) return p;
        everyOtherHelper(p);
        return p;
    }

    private static void everyOtherHelper(Node p) {
        if (p.next != null) p.next = p.next.next;
        if (p.next != null) everyOtherHelper(p.next);
    }
    
    // Problem B.2.F
    
    public static boolean equalLists(Node p,Node q) {
        if (p.item != q.item) return false;
        if (p.next != null && q.next != null) return equalLists(p.next, q.next);
        if (p.next == null && q.next == null) return true;
        return false;
    }    
    
    // Problem B.2.G
    
    public static boolean prefix(Node p, Node q)  {
        if (p == null) return true;
        return prefixHelper(p,q);
    }

    private static boolean prefixHelper(Node p, Node q) {
        if (p.item != q.item) return false;
        if (p.next != null && q.next != null) return prefixHelper(p.next, q.next);
        if (p.next == null && q.next != null) return true;
        return false;
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
         
        int[] B = { 2, 5, 4 };
        Node p = arrayToLinkedList(B);
        System.out.println("\nTest 1:  should print out:\n -> 2 -> 5 -> 4 -> .");
        System.out.println(p);

        p = new Node(2, new Node(5, new Node(4, new Node(6, new Node(2, new Node(9, null))))));
        System.out.println("\nTest 2:  should print out:\n2");
        System.out.println(count(2,p));

        System.out.println("\nTest 3:  should print out:\n0");
        System.out.println(count(7,p));

        p = new Node(2, new Node(5, new Node(4, new Node(3, new Node(2, new Node(5, null))))));
        System.out.println("\nTest 4:  should print out:\n3.5");
        System.out.println(mean(p));

        p = null;
        System.out.println("\nTest 5:  should print out:\n0.0");
        System.out.println(mean(p));

        p = new Node(2, new Node(5, new Node(4, new Node(6, new Node(2, new Node(9, null))))));
        System.out.println("\nTest 6:  should print out:\n -> 2 -> 5 -> 6 -> 2 -> 9 -> .");
        System.out.println(deleteNth(p,3));

        p = new Node(2, new Node(5, new Node(4, new Node(6, new Node(2, new Node(9, null))))));
        System.out.println("\nTest 7:  should print out:\n -> 5 -> 4 -> 6 -> 2 -> 9 -> .");
        System.out.println(deleteNth(p,1));

        p = new Node(2, new Node(5, new Node(4, new Node(6, new Node(2, new Node(9, null))))));
        System.out.println("\nTest 8:  should print out:\n -> 2 -> 5 -> 4 -> 6 -> 2 -> 9 -> .");
        System.out.println(deleteNth(p,13));

        p = null;
        System.out.println("\nTest 9:  should print out:\nnull");
        System.out.println(everyOther(p));

        p = new Node(4, new Node(5, new Node(7, null)));
        System.out.println("\nTest 10:  should print out:\n -> 4 -> 7 -> .");
        System.out.println(everyOther(p));

        p = new Node(4, new Node(5, new Node(7, new Node(2, new Node(8, new Node(1, null))))));
        System.out.println("\nTest 11:  should print out:\n -> 4 -> 7 -> 8 -> .");
        System.out.println(everyOther(p));

        Node q = new Node(2, new Node(5, new Node(4, new Node(6, new Node(2, new Node(9, null))))));
        Node w = new Node(2, new Node(5, new Node(4, new Node(6, new Node(2, new Node(9, null))))));
        Node e = new Node(2, new Node(5, new Node(2, new Node(6, new Node(2, new Node(9, null))))));
        Node r = new Node(2, new Node(5, new Node(4, new Node(6, new Node(2, null)))));
        p = new Node(1, new Node(5, new Node(4, new Node(6, new Node(2, new Node(9, null))))));

        System.out.println("\nTest 12:  should print out:\ntrue");
        System.out.println(equalLists(q,w));

        System.out.println("\nTest 13:  should print out:\nfalse");
        System.out.println(equalLists(q,e));

        System.out.println("\nTest 14:  should print out:\nfalse");
        System.out.println(equalLists(q,r));

        System.out.println("\nTest 15:  should print out:\nfalse");
        System.out.println(equalLists(q,p));

        Node head2 = arrayToLinkedList(new int[] {2,5,4,6,2,9});

        System.out.println("\nTest 16: Should print out:\ntrue");
        System.out.println(prefix(null,head));      
        
        System.out.println("\nTest 17: Should print out:\ntrue");
        System.out.println(prefix(head, arrayToLinkedList(new int[] {3, 6, 9, 12, 16})));
        
        System.out.println("\nTest 18: Should print out:\ntrue");
        System.out.println(prefix(head,head)); 
        
        System.out.println("\nTest 19: Should print out:\nfalse");
        System.out.println(prefix(head,head2));
        
        System.out.println("\nTest 20: Should print out:\nfalse");
        System.out.println(prefix(head,null));
        
        System.out.println("\nTest 21: Should print out:\nfalse");
        System.out.println(prefix(head, arrayToLinkedList(new int[] {3, 6, 9} )));
    }   
}