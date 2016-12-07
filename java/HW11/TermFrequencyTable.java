/* 
 * File: TermFrequencyTable.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 12/7/2016
 * Purpose: This class 
 */

 public class TermFrequencyTable {

 	// Standard Node subclass to store terms
    public static class Node {
        public String term;
        public int[] termFreq = new int[2];
        public Node next;
        
        public Node(String term, Node n) {
            this.term = term;
            this.next = n;
        }
    }

    private final int SIZE = 101;       // Size of array (prime number close to 100)
    private Node [] T = new Node[SIZE];  // Array to hold linkedlists of articles

    // Simple hash function
    private int hash(String x) {
        char ch[];
        ch = x.toCharArray();
        int xlength = x.length();
        
        int i, sum;
        for (sum=0, i=0; i < x.length(); i++)
            sum += ch[i];
        return sum % SIZE;
    }

    // Initialize the hashtable from two documents
    public void initialize(Article[] A) {
        for(int i = 0; i < A.length; ++i) 
            insert(A[i]); 
    }
    
    // Recursively inserts an article into the hashtable
    public void insert(Article a) {
        T[hash(a.getTitle())] = insertHelper(a, T[hash(a.getTitle())]);
    } 
    
    // Helper method for insert, inserts article into alphabetical place
    private Node insertHelper(Article a, Node n) {
        if ( n == null || a.getTitle().compareTo(n.data.getTitle()) < 0 ) {
            return new Node(a, n);
        } else if ( a.getTitle().compareTo(n.data.getTitle()) == 0 ) {
            n.data = a;
            return n;
        } else {  
            n.next = insertHelper(a, n.next);
            return n;
        }
    }

    // Return the cosine similarity of the terms for the two documents stored in this table
    double cosineSimilarity() {

    }

 }