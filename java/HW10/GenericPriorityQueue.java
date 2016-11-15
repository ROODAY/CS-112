/* 
 * File: GenericPriorityQueue.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 11/15/2016
 * Purpose: This class implements a Generic Priority Queue
 */

public class GenericPriorityQueue<Item extends Comparable<Item>> {
    
    private class Node {
        Item item; 
        public Node next; 
        
        // constructors 
        public Node() { 
            item = null;
            next = null; 
        } 
        
        public Node(Item item, Node next) { 
            this.item = item;
            this.next = next; 
        } 
    }
    
    private Node head = null; 
    
    private int size = 0; 
    
    // Insert a new item into the list so that it is in descending order (largest at the head)
    // Hint: use a variation of insertInOrder(...) as a helper method
    
    public void insert(Item key) {
        if (head == null || head.item.compareTo(key) < 0) {
            head = new Node(key, head);
        } else {
            insertInOrder(key, head);
        }
        size++; 
    }

    private void insertInOrder(Item key, Node current) {
        Node next = current.next;
        if (next == null) {
            current.next = new Node(key, null);
        } else {
            if (next.item.compareTo(key) < 0) {
                Node n = new Node(key, next);
                current.next = n;
            } else {
                insertInOrder(key, next);
            }
        }
    }

    
    // Remove and return the item at the head of the list
    
    public Item getMax()  {
        if (head == null)
            return null;
        Item temp = head.item;
        head = head.next;
        --size;
        return temp;
    }
    
    // Return the item at the head of the list without removing it
    
    public Item peek() {
        return head.item;
    }
    
    public boolean isEmpty() {
        return (head == null);
    }
    
    public int size() {
        return size;
    }
    
  
    public String toString() {
       return "Front:  " + queueToString(head);  
    }
 

    private String queueToString(Node p) {
        if (p == null)
            return ":Rear";
        else {
            return p.item + "  " + queueToString(p.next);
        }
    }
 
    public static void main(String[] args) {
        
        // Here is a simple test on Integers, which satisfy the Comparable interface.
        // You should write others to test if your code works on empty queues, 
        // inserts at the end and beginning of the queue, and so on.
        
        GenericPriorityQueue<Integer> S = new GenericPriorityQueue<Integer>(); 
        
        S.insert(3);
        S.insert(6);
        S.insert(1);
        S.insert(5);
        S.insert(2);
        S.insert(4);
        S.insert(0);
        S.insert(9);
        S.insert(7);
        S.insert(8);
        System.out.println(S);
        System.out.println("Peek: " + S.peek());
        System.out.println(S);
        System.out.println("Max: " + S.getMax());
        System.out.println(S);
        
 
        // Now try it on Strings, which also satisfy the Comparable interface
        
        GenericPriorityQueue<String> T = new GenericPriorityQueue<String>();

        T.insert("Giraffe");
        T.insert("Zebra");
        T.insert("Antelope");
        T.insert("Bear");
        T.insert("Marten");
        T.insert("Ocelot");
        T.insert("Stallion");
        T.insert("Dugong");
        T.insert("Crab");
        T.insert("Finch");
        System.out.println(T);
        System.out.println("Peek: " + T.peek());
        System.out.println(T);
        System.out.println("Max: " + T.getMax());
        System.out.println(T);

        // Now try it on Students -- you must implement the compareTo in this class to get it to work
        
        GenericPriorityQueue<Student> U = new GenericPriorityQueue<Student>();
        
        U.insert(new Student("Evelyn", "Greene", 80));
        U.insert(new Student("Paul", "Becker", 81));
        U.insert(new Student("Wilbur", "Pratt", 82));
        U.insert(new Student("Lewis", "Glover", 83));
        U.insert(new Student("Minnie", "Larson", 84));
        U.insert(new Student("Jacquelyn", "Floyd", 85));
        U.insert(new Student("Paulette", "Murray", 86));
        U.insert(new Student("Bridget", "Moran", 87));
        U.insert(new Student("Alonzo", "Todd", 88));
        U.insert(new Student("Gertrude", "Huff", 89));
        System.out.println(U);
        System.out.println("Peek: " + U.peek());
        System.out.println(U);
        System.out.println("Max: " + U.getMax());
        System.out.println(U);
    }
}