/* 
 * File: Deque.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 10/22/2016
 * Purpose: This class implements a Deque ADT using a doubly-linked list
 */

import java.util.Arrays;

public class Deque<Item> {
    
    private class Node {                     // inner class for linked list inside the ADT
        Item item;
        Node next;
        Node previous;
        
        public Node(Item i) { 
            item = i; 
            next = null;
            previous = null;
        }
        
        public Node(Item i, Node n, Node p) { 
            item = i; 
            next = n;
            previous = p;
        }
    }
    
    private Node front = null;
    private Node rear = null;
    private int size = 0; 
    
    
// Return true if Deque is empty, false otherwise
    
    public boolean isEmpty() {
        return (size == 0);
    }
    
    // Return number of elements in dequeue
    
    public int size() {
        return size;
    }
    
// Return item at front or null if empty
    
    public Item peekFront()  {
        if (isEmpty()) return null;
        return front.item;
    }
    
// Return item at rear or null if empty
    
    public Item peekRear()  {
        if (isEmpty()) return null;
        return rear.item;
    }
    
    private void resetPointers() {
        front = rear = null;
    }
    
// Remove front and return its item; return null if empty 
    
    public Item dequeueFront()  {
        if (isEmpty()) return null;
        Item val = front.item;
        front = front.previous;
        if (front != null) front.next = null;
        size--;
        if (isEmpty()) resetPointers();
        return val;
    }
    
    // Remove rear and return its item; return null if empty 
    
    public Item dequeueRear()  {
        if (isEmpty()) return null;
        Item val = rear.item;
        rear = rear.next;
        if (rear != null) rear.previous = null;
        size--;
        if (isEmpty()) resetPointers();
        return val;
    }
    
    // Insert an item on the front of the dequeue
    
    public void enqueueFront(Item n) {
        if (isEmpty()) {
            Node newNode = new Node(n);
            front = rear = newNode;
            size++;
            return;
        }
        Node newFront = new Node(n, null, front);
        front.next = newFront;
        front = newFront;
        size++;
    }
    
    // Insert an item on the rear of the dequeue
    
    public void enqueueRear(Item n) {
        if (isEmpty()) {
            Node newNode = new Node(n);
            front = rear = newNode;
            size++;
            return;
        }
        Node newRear = new Node(n, rear, null);
        rear.previous = newRear;
        rear = newRear;
        size++;
    }
    
    public String toString() {
        String s = "| ";
        for (Node p = rear; p != null; p = p.next) {
            s += (p.item + " ");
        }
        return s + "|";
    }
    
    public static void main(String[] args) {
        
        System.out.println("Unit Test for Deque");
        System.out.println("Note on toString():  rear of deque is to left, front is to right"); 
        
        String solution;
        String answer; 
        int n;              // number removed by dequeue operations
        
        Deque<Integer> D = new Deque<Integer>();
        
        // testing enqueueRear and toString
        
        solution = "| |";
        answer = D.toString(); 
        System.out.println("\nTest 01:  Should print out:\n" + solution);
        System.out.println(answer);
        
        D.enqueueRear(6);
        solution = "| 6 |";
        answer = D.toString(); 
        System.out.println("\nTest 02:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        D.enqueueRear(7);
        solution = "| 7 6 |";
        answer = D.toString(); 
        System.out.println("\nTest 03:  Should print out:\n" + solution);
        System.out.println(answer);
        
        D.enqueueRear(8);
        D.enqueueRear(9);
        D.enqueueRear(10);
        solution = "| 10 9 8 7 6 |";
        answer = D.toString(); 
        System.out.println("\nTest 04:  Should print out:\n" + solution);
        System.out.println(answer);      
        
        // testing enqueueFront
        
        D.enqueueFront(5);
        solution = "| 10 9 8 7 6 5 |";
        answer = D.toString(); 
        System.out.println("\nTest 05:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        D.enqueueFront(4);
        solution = "| 10 9 8 7 6 5 4 |";
        answer = D.toString(); 
        System.out.println("\nTest 06:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        D.enqueueFront(3);
        solution = "| 10 9 8 7 6 5 4 3 |";
        answer = D.toString(); 
        System.out.println("\nTest 07:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        D.enqueueFront(2);
        solution = "| 10 9 8 7 6 5 4 3 2 |";
        answer = D.toString(); 
        System.out.println("\nTest 08:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        D.enqueueFront(1);
        solution = "| 10 9 8 7 6 5 4 3 2 1 |";
        answer = D.toString(); 
        System.out.println("\nTest 09:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        // testing isEmpty and size
        
        solution = "false 10";
        answer = D.isEmpty() + " " + D.size();  
        System.out.println("\nTest 10:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        // testing dequeueRear
        
        n = D.dequeueRear(); 
        solution = "| 9 8 7 6 5 4 3 2 1 |\tdequeued: 10";
        answer = D.toString() + "\tdequeued: " + n; 
        System.out.println("\nTest 11:  Should print out:\n" + solution);
        System.out.println(answer);
        
        n = D.dequeueRear();
        n = D.dequeueRear();
        n = D.dequeueRear();
        solution = "| 6 5 4 3 2 1 |\tdequeued: 7";
        answer = D.toString() + "\tdequeued: " + n; 
        System.out.println("\nTest 12:  Should print out:\n" + solution);
        System.out.println(answer);
        
        n = D.dequeueFront(); 
        solution = "| 6 5 4 3 2 |\tdequeued: 1";
        answer = D.toString() + "\tdequeued: " + n; 
        System.out.println("\nTest 13:  Should print out:\n" + solution);
        System.out.println(answer);
        
        // testing dequeFront
        
        n = D.dequeueFront();
        n = D.dequeueFront(); 
        n = D.dequeueFront(); 
        solution = "| 6 5 |\tdequeued: 4";
        answer = D.toString() + "\tdequeued: " + n; 
        System.out.println("\nTest 14:  Should print out:\n" + solution);
        System.out.println(answer);
        
        n = D.dequeueRear(); 
        solution = "| 5 |\tdequeued: 6";
        answer = D.toString() + "\tdequeued: " + n; 
        System.out.println("\nTest 15:  Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "false\t1";
        answer = D.isEmpty() + "\t" + D.size();  
        System.out.println("\nTest 16:  Should print out:\n" + solution);
        System.out.println(answer);     
        
        n = D.dequeueFront(); 
        solution = "| |\tdequeued: 5";
        answer = D.toString() + "\tdequeued: " + n; 
        System.out.println("\nTest 17:  Should print out:\n" + solution);
        System.out.println(answer);      
        
        solution = "true\t0";
        answer = D.isEmpty() + "\t" + D.size();  
        System.out.println("\nTest 18:  Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "null\tnull";
        answer = D.dequeueFront() + "\t" + D.dequeueRear();  
        System.out.println("\nTest 17:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        solution = D.peekRear() + "\t" + D.peekRear(); 
        answer = "null\tnull";
        System.out.println("\nTest 18:  Should print out:\n" + solution);
        System.out.println(answer);
        
        D.enqueueFront(2);
        D.enqueueFront(5);
        D.enqueueRear(4);
        D.enqueueRear(3);
        solution = "3\t5";
        answer = D.peekRear() + "\t" + D.peekFront();
        System.out.println("\nTest 19:  Should print out:\n" + solution);
        System.out.println(answer);
        
        
        // one last test, just for fun....
        
        Deque<String> E = new Deque<String>();
        
        String[] GettysburgAddress = { "Four","score","and","seven","years","ago","our","fathers",
            "brought","forth","on","this","continent,","a","new","nation,","conceived","in","Liberty,",
            "and","dedicated","to","the","proposition","that","all","men","are","created","equal.", "Now",
            "we","are","engaged","in","a","great","civil","war,","testing","whether","that","nation,",
            "or","any","nation","so","conceived","and","so","dedicated,","can","long","endure.","We",
            "are","met","on","a","great","battle-field","of","that","war.","We","have","come","to",
            "dedicate","a","portion","of","that","field,","as","a","final","resting","place","for",
            "those","who","here","gave","their","lives","that","that","nation","might","live.","It",
            "is","altogether","fitting","and","proper","that","we","should","do","this.", "But,","in","a",
            "larger","sense,","we","can","not","dedicate","--","we","can","not","consecrate","--","we",
            "can","not","hallow","--","this","ground.","The","brave","men,","living","and","dead,","who",
            "struggled","here,","have","consecrated","it,","far","above","our","poor","power","to","add",
            "or","detract.","The","world","will","little","note,","nor","long","remember","what","we",
            "say","here,","but","it","can","never","forget","what","they","did","here.","It","is","for",
            "us","the","living,","rather,","to","be","dedicated","here","to","the","unfinished","work",
            "which","they","who","fought","here","have","thus","far","so","nobly","advanced.","It","is",
            "rather","for","us","to","be","here","dedicated","to","the","great","task","remaining",
            "before","us","--","that","from","these","honored","dead","we","take","increased","devotion",
            "to","that","cause","for","which","they","gave","the","last","full","measure","of","devotion",
            "--","that","we","here","highly","resolve","that","these","dead","shall","not","have","died",
            "in","vain","--","that","this","nation,","under","God,","shall","have","a","new","birth","of",
            "freedom","--","and","that","government","of","the","people,","by","the","people,","for","the",
            "people,","shall","not","perish","from","the","earth."};
        
        for(int i = 0; i < GettysburgAddress.length; ++i) {
            E.enqueueFront(GettysburgAddress[i]);
        }
        
        System.out.println("\nTest 20:  Should print out something very different from" );
        System.out.println("the political rhetoric you've been hearing recently...\n");
        System.out.println(E);   
    }
}
