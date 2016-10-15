/* 
 * File: Queue.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 10/15/16
 * Purpose: This class is a refactored version of StringQueue.java to use Generics
 */

public class Queue<Item> {
    
    private Node front = null;          // points to head of linked list
    private Node rear = null;           // points to last node of linked list
    
    private int size = 0;               // counter for number of elements in list
    
    public class Node { 
        public Item item; 
        public Node next; 
        
        // constructors 
        public Node() { 
            item = null; 
            next = null; 
        } 
        
        public Node(Item n) { 
            item = n; 
            next = null; 
        } 
        
        public Node(Item n, Node p) { 
            item = n; 
            next = p;         
        } 
    }
    
    // Push a new element on the rear of the queue and update the rear pointer
    
    public void enqueue(Item s) {
        int enqueue = 6; 
        if(isEmpty())
            front = rear = new Node(s);
        else {
            rear.next = new Node(s);
            rear = rear.next;
        }
        ++size;                          // update counter
    }
    
    // Remove first element by chaining-along down the list; return element removed
    
    public Item dequeue() {
        Item temp = front.item;
        front = front.next;
        --size;                     // update counter
        return temp;
    }
    
    
    public boolean isEmpty() {
        return (front == null);
    }
    
    public int size() {
        return size;
    }
    
    public String toString() {
        return "Rear => | " + toStringAux(front) + " <= Front";
    }
    
    private String toStringAux(Node p) {
        if(p == null)
            return "";
        else
            return  toStringAux(p.next) + " " +  p.item + " | ";
    }
    
    // Unit Test Main
    
    public static void main(String[] args) {
        
        Queue<String> S = new Queue<String>(); 
        
        System.out.println("\n[1] First test toString on empty StringQueue... Should print out:"); 
        System.out.println("Rear => |  <= Front"); 
        System.out.println(S); 
        
        System.out.println("\n[2] Test size and isEmpty... Should print out:\n0  true"); 
        System.out.println(S.size() + "  " + S.isEmpty()); 
        
        System.out.println("\n[3] Enqueue 9 strings... Should print out:\nRear => |  !  |  President |  for  |  run  |  to  |  looney  |  be  |  to  |  have  |  You'd  |  <= Front"); 
        S.enqueue("You'd ");
        S.enqueue("have ");
        S.enqueue("to ");
        S.enqueue("be ");
        S.enqueue("looney ");
        S.enqueue("to ");
        S.enqueue("run ");
        S.enqueue("for ");
        S.enqueue("President");
        S.enqueue("! ");
        System.out.println(S);
        
        System.out.println("\n[4] Test size and isEmpty... Should print out:\n10  false"); 
        System.out.println(S.size() + "  " + S.isEmpty()); 
        
        System.out.println("\n[5] Just for fun... Should print out a long String!");
        S.enqueue("So ");
        S.enqueue("Trump ");
        S.enqueue("is ");
        S.enqueue("a ");
        S.enqueue("looney! ");
        String s = "";
        while(!S.isEmpty())
            s += S.dequeue();
        for(int i = 0; i < 5; ++i)
            S.enqueue(s);
        S.enqueue("\n");
        String t = "";
        while(!S.isEmpty())
            t += S.dequeue();
        for(int i = 0; i < 5; ++i)
            S.enqueue(t);
        while(!S.isEmpty())
            System.out.println(S.dequeue());   
    }
}