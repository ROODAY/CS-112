/* 
 * File: IntStack.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 11/20/2016
 * Purpose: This class implements exceptions for an array-based stack
 */

public class IntStack {
    
    private int [] A = new int[5]; 
    
    private int next = 0;                        // location of next available unused slot  
    
    // interface methods
    
    public void push(int key) throws QueueOverflowException {         // push the key onto the top of the stack
        if (next >= A.length) throw new QueueOverflowException("Error: Stack overflow when pushing " + key); 
        A[next++] = key; 
    }
    
    public int pop() throws QueueUnderflowException {            // remove the top integer and return it -- will cause error if empty! 
        if ((next - 1) < 0) throw new QueueUnderflowException("Error: Stack underflow!");
        return A[--next];   
    }
    
    public boolean isEmpty() {
        return (next == 0); 
    }
    
    public int size() {                 // how many integers in the stack 
        return next; 
    }
    
    // unit test
    
    public static void main(String [] args) {
        
        IntStack S = new IntStack();        
        
        System.out.println("Pushing 5, 9, 9, -3, 31 then popping and printing them out:");
        try {
            S.push(5); S.push(9); S.push(9); S.push(-3); S.push(31);
        } catch (QueueOverflowException e) {
            System.out.println(e.getMessage());
        }
        
        while(!S.isEmpty()) {
            try {
                System.out.println(S.pop()); 
            } catch (QueueUnderflowException e) {
                System.out.println(e.getMessage());
            }
        }
        
        // this one will cause an underflow error, since the stack is empty!
        System.out.println("Popping an empty stack will cause an error:");
        try {
            System.out.println(S.pop()); 
        } catch (QueueUnderflowException e) {
            System.out.println(e.getMessage());
        }
        
        // This will cause an overflow error
        
        System.out.println("Pushing too many data items will also cause an error:");
        try {
            S.push(5); S.push(9); S.push(9); S.push(-3); S.push(31); S.push(99);
        } catch (QueueOverflowException e) {
            System.out.println(e.getMessage());
        }
    }   
}

// put your class definitions for the exceptions in this file, right here:

class QueueUnderflowException extends Exception {
    public QueueUnderflowException(String text) {
        super(text);
    }
}

class QueueOverflowException extends Exception {
    public QueueOverflowException(String text) {
        super(text);
    }
}