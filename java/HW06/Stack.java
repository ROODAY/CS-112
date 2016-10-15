/* File: Stack.java
 * Date: 10/13/2016
 * Author:  Wayne Snyder (waysnyder@gmail.com)
 * Class: CS 112, Fall 2016
 * Purpose: This is a sample program using Java Generics for HW 06, Problem B.1 (lab problem)
 */

public class Stack<Item> {
    
    private Node head = null; 
    
    private int size = 0; 
    
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
    
    public void push(Item s) {
        head = new Node(s, head);
        ++size; 
    }
    
    public Item pop()  {
        Item temp = head.item;
        head = head.next;
        --size;
        return temp;
    }
    
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
        return "| " + toStringAux(head);
    }
    
    private String toStringAux(Node p) {
        if(p == null)
            return "";
        else
            return toStringAux(p.next) + " " + p.item ;
    }
   
    public static void main(String[] args) {
        
       Stack<String> S = new Stack<String>(); 
        
        System.out.println("\n[1] First test toString on empty StringStack... Should print out:"); 
        System.out.println("| "); 
        System.out.println(S); 
        
        System.out.println("\n[2] Test size and isEmpty... Should print out:\n0  true"); 
        System.out.println(S.size() + "  " + S.isEmpty()); 
        
        System.out.println("\n[3] Push 4 strings... Should print out:\n|  looney a is Trump"); 
        S.push("looney");
        S.push("a");
        S.push("is");
        S.push("Trump");
        System.out.println(S);
        
        System.out.println("\n[4] Test size and isEmpty... Should print out:\n4  false"); 
        System.out.println(S.size() + "  " + S.isEmpty()); 
        
        System.out.println("\n[5] Test peek... Should print out:\nTrump"); 

            System.out.println(S.peek());
        
        System.out.println("\n[6] Pop and print... Should print out:\nTrump is a looney");
        while(!S.isEmpty()) {
                String s = S.pop(); 
                System.out.print(s+" ");
        }
        System.out.println();        
        
        
        System.out.println("\n[7] Just for fun... Should print out a long String!");

            S.push("looney! ");
            S.push("a ");
            S.push("is ");
            S.push("Trump ");
            String t = "";
            while(!S.isEmpty())
                t += S.pop();
            for(int i = 0; i < 5; ++i)
                S.push(t);
            S.push("\n");
            String u = "";
            while(!S.isEmpty())
                u += S.pop();
            for(int i = 0; i < 5; ++i)
                S.push(u);
            while(!S.isEmpty())
                System.out.println(S.pop());
       
    }
}

