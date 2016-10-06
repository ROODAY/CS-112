/* 
 * File: Queueable.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 10/04/16
 * Purpose: This interface declares the methods required of a naive queue implementation
 */

public interface Queueable {
    
    // Enqueues a new key in the front of the queue
    // If the queue is full, resizes queue to twice its size
    public void enqueue(int key);
    
    // Removes and returns the first element in the queue
    // Returns an error value if the queue is empty
    public int dequeue();
    
    // Returns the first element in the queue without removing it
    public int peek();
    
    // Returns the number of elements in the queue
    public int size();
    
    // Returns true iff there are no elements in the queue
    public boolean isEmpty();
}