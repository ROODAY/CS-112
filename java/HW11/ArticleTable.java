/* 
 * File: ArticleTable.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 12/03/2016
 * Purpose: This class implements a hash table that stores different articles
 */
import java.util.Iterator;

public class ArticleTable implements Iterable<Article> {
    
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
    
    private final int SIZE = 2521;
    private Node [] T = new Node[SIZE];
    
    private int hash(String x) {
        char ch[];
        ch = x.toCharArray();
        int xlength = x.length();
        
        int i, sum;
        for (sum=0, i=0; i < x.length(); i++)
            sum += ch[i];
        return sum % SIZE;
    }
    
    public void initialize(Article[] A) {
        for(int i = 0; i < A.length; ++i) 
            insert(A[i]); 
    }
    
    public void insert(Article a) {
        T[hash(a.getTitle())] = insertHelper(a, T[hash(a.getTitle())]);
    } 
    
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
    
    public void delete(String title) {
        T[hash(title)] = deleteHelper(title, T[hash(title)]);
    }
    
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
    
    public Article lookup(String title) {
        return lookupHelper(title, T[hash(title)]);
    }
    
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
        
        private void setCursors() {
            if (T[index] == null) {
                index++;
                setCursors();
            } else {
                cursor = T[index];
            }
        }
        
        public boolean hasNext() {
            //System.out.println("Index: " + index);
            //System.out.println("Cursor: " + cursor);
            if (cursor == null) return index < T.length;
            return true;
            /*if (index >= T.length) {
                return false;
            } else return index < T.length;*/
        }
        
        private boolean hasNextCursor() {
            return cursor != null;
        }
        
        public Node getCursor() {
            return cursor;
        }
        
        public int getIndex() {
            return index;
        }
        
        public Article next() {
            if(hasNext()) {
                if (hasNextCursor()) {
                    Node result = cursor;
                    cursor = cursor.next;
                    return result.data;
                } else {
                    if (++index < T.length) cursor = T[index];
                    return next();
                }
            }
            return null;
        } 
        
        public void remove() {
        }
    }      
}