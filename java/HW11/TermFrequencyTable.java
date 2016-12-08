/* 
 * File: TermFrequencyTable.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 12/07/2016
 * Purpose: This class calculates the cosine similarity between two documents
 */
import java.util.Iterator;

public class TermFrequencyTable implements Iterable<int[]>{
    
    // Standard Node subclass to store terms
    public static class Node {
        public String term;
        public int[] termFreq = new int[2];
        public Node next;
        
        public Node(String term, Node n, int docNum) {
            this.term = term;
            this.next = n;
            this.termFreq[docNum]++;
        }
    }
    
    private final int SIZE = 101;        // Size of array (prime number close to 100)
    private Node [] T = new Node[SIZE];  // Array to hold linkedlists of articles
    private final String [] blackList = { "the", "of", "and", "a", "to", "in", "is", 
        "you", "that", "it", "he", "was", "for", "on", "are", "as", "with", 
        "his", "they", "i", "at", "be", "this", "have", "from", "or", "one", 
        "had", "by", "word", "but", "not", "what", "all", "were", "we", "when", 
        "your", "can", "said", "there", "use", "an", "each", "which", "she", 
        "do", "how", "their", "if", "will", "up", "other", "about", "out", "many", 
        "then", "them", "these", "so", "some", "her", "would", "make", "like", 
        "him", "into", "time", "has", "look", "two", "more", "write", "go", "see", 
        "number", "no", "way", "could", "people",  "my", "than", "first", "water", 
        "been", "call", "who", "oil", "its", "now", "find", "long", "down", "day", 
        "did", "get", "come", "made", "may", "part" }; // black list of common strings
    
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
    public void initialize(String one, String two) {
        String[] temp1 = one.toLowerCase().split(" ");
        String[] temp2 = two.toLowerCase().split(" ");
        String[] ones = new String[temp1.length];
        String[] twos = new String[temp2.length];
        
        int index = 0;
        for (int i = 0; i < temp1.length; i++) {
            boolean safe = true;
            for (int j = 0; j < blackList.length; j++) {
                if (temp1[i].equals(blackList[j])) {
                    safe = false;
                    break;
                }
            }
            if (safe) ones[index++] = temp1[i];
        }
        index = 0;
        for (int i = 0; i < temp2.length; i++) {
            boolean safe = true;
            for (int j = 0; j < blackList.length; j++) {
                if (temp2[i].equals(blackList[j])) {
                    safe = false;
                    break;
                }
            }
            if (safe) twos[index++] = temp2[i];
        }
        
        for (int i = 0; i < temp1.length; i++) {
            if (!temp1[i].equals("")) insert(temp1[i], 0);
        }
        for (int i = 0; i < temp2.length; i++) {
            if (!temp2[i].equals("")) insert(temp2[i], 1);
        }
    }
    
    // Recursively inserts a term frequency into the hashtable
    public void insert(String term, int docNum) {
        T[hash(term)] = insertHelper(term, T[hash(term)], docNum);
    } 
    
    // Helper method for insert, inserts term into alphabetical place and updates its frequency
    private Node insertHelper(String term, Node n, int docNum) {
        if ( n == null || term.compareTo(n.term) < 0 ) {
            return new Node(term, n, docNum);
        } else if ( term.compareTo(n.term) == 0 ) {
            n.termFreq[docNum]++;
            return n;
        } else {  
            n.next = insertHelper(term, n.next, docNum);
            return n;
        }
    }
    
    // Return the cosine similarity of the terms for the two documents stored in this table
    public double cosineSimilarity() {
        Iterator<int[]> it = iterator();
        int[] A =  new int[SIZE];
        int[] B = new int[SIZE];
        int index = 0;
        while (it.hasNext()) {
            int[] n = it.next();
            A[index] = n[0];
            B[index++] = n[1];
        }
        return dotProduct(A,B) / (Math.sqrt(dotProduct(A,A)) * Math.sqrt(dotProduct(B,B)));
    }
    
    private double dotProduct(int[] A, int[] B) {
        double sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i] * B[i];
        }
        return sum;
    }
    
    public Iterator<int[]> iterator() {
        return new It();
    }
    
    private class It implements Iterator<int[]> {
        private int index = 0;
        private Node cursor;
        
        public It() { 
            setCursors();                       
        }
        
        // Finds next non-null index in array of string linkedlists
        private void setCursors() {
            if (T[index] == null) {
                if (++index < SIZE) {
                    setCursors();
                }
            } else {
                cursor = T[index];
            }
        }
        
        public boolean hasNext() {
            return index < SIZE && cursor != null;
        }
        
        private boolean hasNextCursor() {
            return cursor != null;
        }
        
        // Returns next string Node, calls setCursors when it reaches the end of a linkedlist
        public int[] next() {
            if(hasNext()) {
                Node result = cursor;
                cursor = cursor.next;
                if (cursor == null && ++index < SIZE) {
                    setCursors();
                }
                return result.termFreq;
            }
            return null;
        } 
        
        public void remove() {
        }
    }
    
    public static void main(String[] args) {
        TermFrequencyTable L = new TermFrequencyTable(); 
        String one = "A B";
        String two = "A A B B";
        L.initialize(one, two);
        System.out.println("One: " + one + ", Two: " + two + ", Similarity: " + L.cosineSimilarity());
        
        L = new TermFrequencyTable(); 
        one = "A B";
        two = "C D";
        L.initialize(one, two);
        System.out.println("One: " + one + ", Two: " + two + ", Similarity: " + L.cosineSimilarity());
        
        L = new TermFrequencyTable(); 
        one = "CS112 HW11";
        two = "CS112 HW11 HW11";
        L.initialize(one, two);
        System.out.println("One: " + one + ", Two: " + two + ", Similarity: " + L.cosineSimilarity());
    }
}