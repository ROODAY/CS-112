/* 
 * File: LinkedList.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 11/08/2016
 * Purpose: This class implements an iterable LinkedList
 */

import java.util.Iterator;

public class LinkedList implements Iterable<Integer> { 

    // Private class for Node so that the ADT is a LinkedList
    private class Node {
        int item;
        Node next;
        
        public Node(int i) { 
            item = i; 
            next = null;
        }
        
        public Node(int i, Node n) { 
            item = i; 
            next = n;
        }
    }
    
    private Node head = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, null)))));
    
    // This method will return a new iterator that will allow us to enumerate the linked list
    // Note an an iterator is a class defined inside the ADT class.
    
    // This is the method which is required by the Iterable interface, and allows us to
    // use the special form of the for loop shown at the bottom of main
    
    public Iterator<Integer> iterator() {
        return new It();
    }
    
    // The iterator inner class has access to private array A inside List, and also has a local
    // variable cursor to record where in the iteration we are
    
    private class It implements Iterator<Integer> {
        
        private Node cursor;                   // where in the enumeration we are
        
        // constructor, sets cursor so enumeration starts at 0
        public It() { 
            cursor = head;                         
        }
        
        // interface methods required by Iterator interface
        
        public boolean hasNext() {
            return cursor != null;
        }
        
        public Integer next() {
            if(hasNext()) {
                Node result = cursor;
                cursor = cursor.next;
                return result.item;
            } else {
                return null;
            }
        } 
        
        public void remove() {             // required by interface but we won't implement it
        }
    }
    
    // Example of using an iterator
    
    public static void main(String[] args) {
        LinkedList L = new LinkedList();
        
        // First way to use an iterator by explictly creating one
        
        Iterator<Integer> it = L.iterator();       // declare an iterator to do an enumeration
        
        if (it.hasNext()) {
            System.out.println(it.next() + " ");
        }
        // here we pause the enumeration and can do something else
        System.out.println("...something else..."); 
        
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println(); 
        
        // try once more to show what happens when there is no next element
        System.out.println(it.next()); 
        
        // A more complex example: we can create two iterators which work separately through
        // the same data!
        
        Iterator<Integer> it1 = L.iterator();
        Iterator<Integer> it2 = L.iterator();
        
        while (it1.hasNext()) {
            int n = it1.next();
            System.out.print(n + " ");
            if (n == 2) {
                System.out.print("[ ");
                while (it2.hasNext()) {
                    System.out.print(it2.next() + " ");
                }
                System.out.print("] ");
            }
        }
        System.out.println(); 
        
        // Second way to do an enumeration of all the elements using the ADT itself
        // If the ADT implements Iterable, then you can use this kind of for loop:
        // think of ":" as the same as the Python "in"
        
        for(Integer i : L) {        
            System.out.print(i + " ");   
        }
        
        System.out.println(); 
        
        // Showing that you can do the same thing with arrays
        
        char[] B = { 'A', 'B', 'C' };
        
        for(char c : B) {        
            System.out.print(c + " ");   
        }
        System.out.println();        
    }
}