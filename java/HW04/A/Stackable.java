/* File: Stackable.java
 * Date: 9/27/16
 * Author:  Wayne Snyder (waysnyder@gmail.com)
 * Class: CS 112, Fall 2016
 * Purpose: Example of Interface for Stack.java in Lab 04
 */

package HW04.A;

public interface Stackable {
    
    // Insert a new element on the top of the stack
    // If stack is full, resizes
    
    public void push(int n); 
    
    // Remove and return the top element of stack
    // Note: Does not check for underflow
    
    public int pop(); 
    
    // Return top element of stack without removing it
    // Note: Does not check for underflow
    
    public int peek(); 
    
    // Return the number of integers in the stack
    
    public int size(); 
    
    // Return true iff there are no elements in the stack
    
    public boolean isEmpty(); 
    
}