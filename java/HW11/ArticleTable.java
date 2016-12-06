/* 
 * File: ArticleTable.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 12/03/2016
 * Purpose: This class implements an iterable hash table that stores different articles
 */
import java.util.Iterator;

public class ArticleTable implements Iterable<Article> {
    
    // Standard Node subclass to store articles
    public static class Node {
        public Article data;
        public Node next;
        
        public Node(Article data, Node n) {
            this.data = data;
            this.next = n;
        }
        
        public Node(Article data) {
            this(data, null);
        }
    }
    
    private final int SIZE = 2521;       // Size of array (prime number close to 2500)
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
    
    // Initialize the hashtable from an array of articles
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
    
    // Recursively deletes an article from the hashtable
    public void delete(String title) {
        T[hash(title)] = deleteHelper(title, T[hash(title)]);
    }
    
    // Helper method for delete, finds article and cuts it from its linkedlist
    public Node deleteHelper(String title, Node t) {
        if (t == null)
            return t;
        else if (title.compareTo(t.data.getTitle()) == 0)  
            return t.next; 
        else {
            t.next = deleteHelper(title, t.next); 
            return t;
        }
    }
    
    // Recursively finds article from the hashtable
    public Article lookup(String title) {
        return lookupHelper(title, T[hash(title)]);
    }
    
    // Helper method for lookup
    private Article lookupHelper(String title, Node n) {
        if (n == null)
            return null;
        else if (title.compareTo(n.data.getTitle()) == 0)  
            return n.data; 
        else {
            return lookupHelper(title, n.next);
        }
    }
    
    public Iterator<Article> iterator() {
        return new It();
    }
    
    
    private class It implements Iterator<Article> {
        private int index = 0;
        private Node cursor;
        
        public It() { 
            setCursors();                       
        }
        
        // Finds next non-null index in array of article linkedlists
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
        
        // Returns next article Node, calls setCursors when it reaches the end of a linkedlist
        public Article next() {
            if(hasNext()) {
                Node result = cursor;
                cursor = cursor.next;
                if (cursor == null) {
                    index++;
                    setCursors();
                }
                return result.data;
            }
            return null;
        } 
        
        public void remove() {
        }
    }      
}