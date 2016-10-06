/* 
 * File: IntQueue.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 10/04/16
 * Purpose: This class implements a queue ADT using two stacks
 */

public class IntQueue implements Queueable {
    
    Stackable inStack = new AnotherStack();
    Stackable outStack = new AnotherStack();
    private int errorNumber = Integer.MIN_VALUE;
    
    // Enqueues a new key in the front of the queue
    // If the queue is full, resizes queue to twice its size
    public void enqueue(int key) {
        inStack.push(key);
    }
    
    // Removes and returns the first element in the queue
    // Returns an error value if the queue is empty
    public int dequeue() {
        if (isEmpty()) return errorNumber;
        if (outStack.isEmpty()) {
            int size = inStack.size();
            for (int i = 0; i < size; i++) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }
    
    public int peek() {
        return outStack.peek();
    }
    
    public int size() {
        return inStack.size() + outStack.size();
    }
    
    public boolean isEmpty() {
        return (inStack.isEmpty() && outStack.isEmpty());
    }
    
    public String toString() {
        
        String s = "";
        int size = size();
        for(int i = 0; i < size; i++) {
            int n = dequeue();
            s = n + "\t" + s;
            enqueue(n);
        }
        return s;   
    }
    
    // unit test
    
    public static void main(String [] args) {
        
        Queueable Q = new IntQueue();  
        
        System.out.println("Unit Test for IntQueue");
        
        System.out.println("\n[1] Should be:\n0\ttrue");
        System.out.println(Q.size() + "\t" + Q.isEmpty()); 
        
        Q.enqueue(3); 
        Q.enqueue(5);
        Q.enqueue(9);
        Q.enqueue(7);
        
        System.out.println("\n[2] Should be:\n4\tfalse");
        System.out.println(Q.size() + "\t" + Q.isEmpty()); 
        
        System.out.println("\n[3] Should be:\n7\t9\t5\t3\t (front of queue is to right)");
        System.out.println(Q); 
        
        System.out.println("\n[4] Should be:\n3\t3");
        System.out.print(Q.dequeue());
        System.out.println("\t" + Q.size()); 
        
        System.out.println("\n[5] Should be:\n5\t3");
        System.out.print(Q.peek());
        System.out.println("\t" + Q.size());
        
        System.out.println("\n[6] Should be:\n5\t7\t9");
        Q.enqueue(Q.dequeue()); 
        System.out.println(Q);     
        
        System.out.println("\nTesting resizing...");
        
        for(int i = 10; i <= 25; ++i) {
            Q.enqueue(i); 
        }
        
        for(int i = 0; i < 13; ++i) {
            Q.dequeue();
        }
        
        System.out.println("\n[7] Should be:\n25\t24\t23\t22\t21\t20");
        System.out.println(Q);    
        
    }
    
}

