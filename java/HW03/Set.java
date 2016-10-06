/* 
 * File: Set.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 09/26/2016
 * Purpose: This class implements a set, a collection in which 
 * order does not matter and there are no duplicates
 */

public class Set  {
    
    private int SIZE = 20;  // length of the array
    
    private int[] S;              // array holding the set
    
    private int next = 0;             // pointer to next available slot in array
    
    // Default constructor for an emtpy set
    public Set() {
        S = new int[SIZE];
        next = 0;
    }
    
    // Constructs a set containing the elements in A
    public Set(int[] A) {
        S = new int[SIZE];
        next = 0;
        for (int i = 0; i < A.length; i++) {
            insert(A[i]);
        }
    }
    
    // Returns an exact copy of this set
    public Set clone() {
        int[] ints = new int[next];
        for (int i = 0; i < next; i++) {
            ints[i] = S[i];
        }
        return new Set(ints); 
    }
    
    // This method reallocates the array S to twice as big and copies all the elements over.
    // It is called only by insert.
    private void resize() {
        int[] T = new int[SIZE * 2];
        for(int i = 0; i < S.length; ++i) {
            T[i] = S[i];
        }
        SIZE = SIZE * 2;
        S = T;
    }
    
    // Returns a string representation of this set
    public String toString()  {
        if (next == 0) return "[]";
        String result = "[";
        for (int i = 0; i < next; i++) {
            result += S[i];
            if (i < next - 1) result += ",";
        }
        return result + "]";
    } 
    
    // Returns the number of elements in this set
    public int size() {
        return next;
    }
    
    // Returns true if this set contains no elements, false otherwise
    public  boolean isEmpty() {
        if (next == 0) return true;
        return false; 
    }
    
    // Returns true if k is in this set
    public boolean member(int k) {
        for (int i = 0; i < next; i++) {
            if (S[i] == k) return true;
        }
        return false; 
    }    
    
    // Returns true if all elements in this set occur in T, false otherwise
    public boolean subset(Set T) {
        for (int i = 0; i < next; i++) {
            if (!T.member(S[i])) return false;
        }
        return true;  
    }
    
    // Returns true if T contains all the same elements as this set, false otherwise
    public boolean equal(Set T) {
        if (T.next == next && subset(T)) return true;
        return false;
    }
    
    // Inserts k into this set if it is not already a member
    public void insert(int k) {
        if (next >= S.length) resize();
        if (!member(k)) {
            S[next] = k;
            next++;
        }
    }
    
    // Deletes k from this set if it is a member
    public void delete(int k) {
        if (member(k)) {
            int index = 0;
            for (int i = 0; i < next; i++) {
                if (S[i] == k) {
                    index = i;
                    break;
                }
            }
            S[index] = 0;
            for (int i = index; i < next - 1; i++) {
                S[i] = S[i + 1];
            }
            next--;
            S[next] = 0;
        }
    }
    
    // Returns a set that contains all the elements of this set and T
    public Set union(Set T) {
        Set joinedSet = clone();
        for (int i = 0; i < T.next; i++) {
            joinedSet.insert(T.S[i]);
        }
        return joinedSet;
    }
    
    public Set intersection(Set T) {
        Set inter = new Set();
        for (int i = 0; i < next; i++) {
            if (T.member(S[i])) inter.insert(S[i]);
        }
        return inter;  
    }
    
    public Set setdifference(Set T) {
        Set difference = new Set();
        for (int i = 0; i < next; i++) {
            if (!T.member(S[i])) difference.insert(S[i]);
        }
        return difference;   
    }
    
    public static void main(String [] args) {
        
        System.out.println("\nUnit Test for Set: note that your answers, when they are");
        System.out.println("  sets, could be in a different order (since order does");
        System.out.println("  not matter), this is the meaning of \"same set as...\"\n");
        
        Set A = new Set();
        Set B = new Set( new int[] { 5 } );
        Set C = new Set( new int[] { 5, 3, 7, 4, 1 } );
        Set D = new Set( new int[] { 4, 3, -3, 10, 8 } );
        Set E = new Set( new int[] { 8, 4, 10 } );
        Set F = new Set( new int[] { 10, 8, 4 } );
        
        System.out.println("Test 01: Should be\n[]");
        System.out.println(A);
        System.out.println(); 
        
        System.out.println("Test 02: Should be\n[5]");
        System.out.println(B);
        System.out.println(); 
        
        System.out.println("Test 03: Should be same set as\n[5,3,7,4,1]");
        System.out.println(C);
        System.out.println(); 
        
        System.out.println("Test 04: Should be\n[]");
        System.out.println(A.clone());
        System.out.println(); 
        
        System.out.println("Test 05: Should be same set as\n[5,3,7,4,1]");
        System.out.println(C.clone());
        System.out.println(); 
        
        System.out.println("Test 06: Should be\n0");
        System.out.println(A.size());
        System.out.println(); 
        
        System.out.println("Test 07: Should be\n5");
        System.out.println(D.size());
        System.out.println(); 
        
        System.out.println("Test 08: Should be\ntrue");
        System.out.println(A.isEmpty());
        System.out.println(); 
        
        System.out.println("Test 09: Should be\nfalse");
        System.out.println(F.isEmpty());
        System.out.println(); 
        
        System.out.println("Test 10: Should be\nfalse");
        System.out.println(A.member(4));
        System.out.println();
        
        System.out.println("Test 11: Should be\ntrue");
        System.out.println(C.member(1));
        System.out.println();       
        
        System.out.println("Test 12: Should be\nfalse");
        System.out.println(D.member(1));
        System.out.println();
        
        System.out.println("Test 13: Should be\ntrue");
        System.out.println(A.subset(D));
        System.out.println();
        
        System.out.println("Test 14: Should be\nfalse");
        System.out.println(D.subset(C));
        System.out.println();       
        
        System.out.println("Test 15: Should be\ntrue");
        System.out.println(E.subset(D));
        System.out.println();
        
        System.out.println("Test 16: Should be\nfalse");
        System.out.println(D.subset(E));
        System.out.println();
        
        System.out.println("Test 17: Should be\nfalse");
        System.out.println(D.equal(E));
        System.out.println();       
        
        System.out.println("Test 18: Should be\ntrue");
        System.out.println(E.equal(F));
        System.out.println();
        
        System.out.println("Test 19: Should be\ntrue");
        System.out.println(F.equal(E));
        System.out.println();
        
        System.out.println("Test 20: Should be\ntrue");
        System.out.println(A.equal(A));
        System.out.println();       
        
        System.out.println("Test 21: Should be same set as\n[4,6]");
        Set A1 = A.clone(); 
        A1.insert(4);
        A1.insert(6);
        A1.insert(4);
        System.out.println(A1);
        System.out.println();
        
        System.out.println("Test 22: Should be same set as\n[10,8,4,5]");
        Set F1 = F.clone(); 
        F1.insert(5);
        F1.insert(4);
        System.out.println(F1);
        System.out.println();       
        
        System.out.println("Test 23: Should be same set as\n[8,4,10]");
        Set E1 = E.clone(); 
        E1.insert(10);
        System.out.println(E1);
        System.out.println();
        
        System.out.println("Test 24: Should be\n[]");
        A1 = A.clone(); 
        A1.delete(5);
        System.out.println(A1);
        System.out.println();       
        
        System.out.println("Test 25: Should be\n[]");
        Set B1 = B.clone(); 
        B1.delete(5);
        System.out.println(B1);
        System.out.println();  
        
        System.out.println("Test 26: Should be same set as\n[8,4,10]");
        E1 = E.clone(); 
        E1.delete(5);
        System.out.println(E1);
        System.out.println(); 
        
        System.out.println("Test 27: Should be same set as\n[4,10]");
        E1 = E.clone(); 
        E1.delete(8);
        System.out.println(E1);
        System.out.println();
        
        System.out.println("Test 28: Should be same set as\n[3,4]");
        System.out.println(C.intersection(D));
        System.out.println();
        
        System.out.println("Test 29: Should be\n[8,4,10]");
        System.out.println(E.intersection(F));
        System.out.println();       
        
        System.out.println("Test 30: Should be same set as\n[]");
        System.out.println(A.intersection(F));
        System.out.println();
        
        System.out.println("Test 31: Should be same set as\n[]");
        System.out.println(B.intersection(F));
        System.out.println();
        
        System.out.println("Test 32: Should be same set as\n[4,3,-3,10,8,5,7,1]");
        System.out.println(C.union(D));
        System.out.println();
        
        System.out.println("Test 33: Should be same set as\n[10,8,4]");
        System.out.println(E.union(F));
        System.out.println();       
        
        System.out.println("Test 34: Should be same set as\n[10,8,4]");
        System.out.println(A.union(F));
        System.out.println();
        
        System.out.println("Test 35: Should be same set as\n[5,3,7,4,1]");
        System.out.println(C.union(B));
        System.out.println();
        
        System.out.println("Test 36: Should be same set as\n[5,7,1]");
        System.out.println(C.setdifference(D));
        System.out.println();       
        
        System.out.println("Test 37: Should be same set as\n[]");
        System.out.println(E.setdifference(F));
        System.out.println();
        
        System.out.println("Test 38: Should be same set as\n[5,3,7,4,1]");
        System.out.println(C.setdifference(A));
        System.out.println();
        
        System.out.println("Test 39: Should be same set as\n[]");
        System.out.println(C.setdifference(C));
        System.out.println();
        
        System.out.println("Test 40: Should be same set as\n[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31]");
        Set G = new Set();
        for(int i = 0; i < 32; ++i) {
            G.insert(i);
        }
        System.out.println(G);
        System.out.println();
        
    }  
}