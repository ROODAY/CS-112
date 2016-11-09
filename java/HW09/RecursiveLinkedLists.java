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
        if (q == null) return false;
        return prefixHelper(p,q);
    }

    private static boolean prefixHelper(Node p, Node q) {
        if (p.item != q.item) return false;
        if (p.next != null && q.next != null) return prefixHelper(p.next, q.next);
        if (p.next == null) return true;
        return false;
    }
    
    // Problem B.2.H
    
    public static boolean sublist(Node p, Node q)  {
        if (p == null) return true;
        return sublistHelper(p,q);
    }

    private static boolean sublistHelper(Node p, Node q) {
        if (prefix(p,q)) return true;
        if (q.next == null) return false;
        return sublistHelper(p, q.next);
    }
    
    
    // Problem B.2.I
    
    public static Node splice(int n, Node p, Node q)  {
        if (p == null) return q;
        if (q == null) return p;
        if (n < 1) return append(q, p);
        Node c = spliceHelper(n, p, q, 1);
        return p;
    }

    private static Node spliceHelper(int n, Node p, Node q, int count) {
        if (n == count) {
            q = append(q, p.next);
            p.next = null;
            return append(p, q);
        } else {
            if (p.next == null) return append(p, q);
            return spliceHelper(n, p.next, q, ++count);
        }
    }

    private static Node append( Node p, Node q ) {
        if ( p == null) 
           return q; 
        else {
          p.next = append( p.next, q );
          return p;
        }
     }
    
    // Problem B.2.J   
    
    public static Node intersection(Node p,Node q) {
        Node c = null;
        intersectionHelper(p,q,c);
        return c;
    }
    
    private static void intersectionHelper(Node p, Node q, Node c) {
        if (p != null && q != null) {
            System.out.println("Somewhere");
            if (p.item == q.item) {
                System.out.println("Ayy");
                c = new Node(p.item, null);
                intersectionHelper(p.next, q.next, c.next);
            } else {
                intersectionHelper(p, q.next, c);
            }
        }
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

        head = new Node(3, new Node(6, new Node( 9, new Node(12, null ) ) ) );
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

        p = new Node(4, new Node(2, new Node(5, new Node(9, new Node(1, new Node(6, null))))));
        q = new Node(5, new Node(9, new Node(1, null)));
        r = new Node(1, null);
        Node s = new Node(1, new Node(6, new Node(6, null)));
        Node t = null;
        Node u = new Node(2, new Node(5, new Node(1, null)));

        System.out.println("\nTest 22: Should print out:\ntrue");
        System.out.println(sublist(p,p));

        System.out.println("\nTest 23: Should print out:\ntrue");
        System.out.println(sublist(t,p));

        System.out.println("\nTest 24: Should print out:\ntrue");
        System.out.println(sublist(t,t));

        System.out.println("\nTest 25: Should print out:\ntrue");
        System.out.println(sublist(q,p));

        System.out.println("\nTest 26: Should print out:\nfalse");
        System.out.println(sublist(p,q));

        System.out.println("\nTest 27: Should print out:\ntrue");
        System.out.println(sublist(r,p));

        System.out.println("\nTest 28: Should print out:\nfalse");
        System.out.println(sublist(s,p));

        System.out.println("\nTest 29: Should print out:\nfalse");
        System.out.println(sublist(u,p));

        head = new Node(1, new Node(2, new Node(3, null)));
        Node other = new Node(5, new Node(6, new Node(7, null)));
        System.out.println("\nTest 30: Should print out:\n -> 1 -> 5 -> 6 -> 7 -> 2 -> 3 -> .");
        System.out.println(splice(1,head,other));

        head = new Node(1, new Node(2, new Node(3, null)));
        other = new Node(5, new Node(6, new Node(7, null)));
        System.out.println("\nTest 31: Should print out:\n -> 1 -> 2 -> 3 -> 5 -> 6 -> 7 -> .");
        System.out.println(splice(3,head,other));

        head = new Node(1, new Node(2, new Node(3, null)));
        other = new Node(5, new Node(6, new Node(7, null)));
        System.out.println("\nTest 32: Should print out:\n -> 5 -> 6 -> 7 -> 1 -> 2 -> 3 -> .");
        System.out.println(splice(0,head,other));

        head = new Node(1, new Node(2, new Node(3, null)));
        other = new Node(5, new Node(6, new Node(7, null)));
        System.out.println("\nTest 33: Should print out:\n -> 1 -> 2 -> 3 -> 5 -> 6 -> 7 -> .");
        System.out.println(splice(10,head,other));

        Node a = new Node(2, new Node(3, new Node(4, new Node(5, new Node(6, null)))));
        Node b = new Node(1, new Node(2, new Node(4, new Node(5, null))));
        System.out.println("\nTest 34: Should print out:\n -> 2 -> 4 -> 5 -> .");
        System.out.println(intersection(a,b));

        a = new Node(2, new Node(3, null));
        b = new Node(1, new Node(4, new Node(5, null)));
        System.out.println("\nTest 35: Should print out:\nnull");
        System.out.println(intersection(a,b));

        a = new Node(2, new Node(3, new Node(4, new Node(5, new Node(6, null)))));
        b = null;
        System.out.println("\nTest 36: Should print out:\nnull");
        System.out.println(intersection(a,b));

        b = new Node(2, new Node(3, new Node(4, new Node(5, new Node(6, null)))));
        a = new Node(1, new Node(2, new Node(4, new Node(5, null))));
        System.out.println("\nTest 34: Should print out:\n -> 2 -> 4 -> 5 -> .");
        System.out.println(intersection(a,b));
    }   
}