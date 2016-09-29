/* File: Stack.java
 * Date: 9/27/16
 * Author:  Wayne Snyder (waysnyder@gmail.com)
 * Class: CS 112, Fall 2016
 * Purpose: Example of Stack ADT
 */

package HW04.A;

public class Stack {
    
    private final int INITIAL_LENGTH = 10;
    private int[] S = new int[INITIAL_LENGTH];     // array holding the stack, bottom at 0
    private int next = 0;                          // next available slot in the array
    
    // Insert a new element on the top of the stack
    // If stack is full, resizes
    
    public void push(int n) {
        if(next == S.length) {
            resize();
        }
        S[next] = n;
        ++next;
    }
    
    // Remove and return the top element of stack
    // Note: Does not check for underflow
    
    public int pop() {
        --next;
        return S[next];
    }
    
    // Return top element of stack without removing it
    // Note: Does not check for underflow
    
    public int peek() {
        return S[next-1];
    }
    
    // Return the number of integers in the stack
    
    public int size() {
        return next;
    }
    
    // Return true iff there are no elements in the stack
    
    public boolean isEmpty() {
        return (next == 0);
    }
    
    // replace S by array twice as big, but with same elements
    
    private void resize() {
        int[] T = new int[ S.length * 2 ];
        for (int i = 0; i < S.length; ++i) {
            T[i] = S[i];
        }
        S = T;
    }
    
    // Unit test main method
    
    public static void main(String[] args) {
        Stack S = new Stack();
        
        System.out.println("Unit Test for Stack");
        
        System.out.println("\nTest 1: Should be\ntrue\t0");
        System.out.println(S.isEmpty() + "\t"+ S.size());
        System.out.println();
        
        System.out.println("Test 2: Should be\nfalse\t5");
        S.push(3);
        S.push(1);
        S.push(4);
        S.push(1);
        S.push(5);
        System.out.println(S.isEmpty() + "\t"+ S.size());
        System.out.println();
        
        System.out.println("Test 3: Should be\n5\t1\t1\t4");
        System.out.print( S.pop() );
        System.out.print( "\t" + S.peek() );
        System.out.print( "\t" + S.pop() );
        System.out.println( "\t" + S.peek() );
        System.out.println();
        
        System.out.println("Test 4: Should be\n669\t666\t665\t665\t664");
        for (int i = 1; i <= 666; ++i) {            // force it to resize several times
            S.push(i);
        }
        System.out.print( S.size() );
        System.out.print( "\t" + S.pop() );
        System.out.print( "\t" + S.peek() );
        System.out.print( "\t" + S.pop() );
        System.out.println( "\t" + S.peek() );
        System.out.println();
        
           
        System.out.println("Test 5: Should be\ntrue");
                while (!S.isEmpty()) {
            S.pop();               // throw away the value returned
        }
        System.out.println(S.isEmpty() );
        System.out.println();
    }
    
}
