/* 
 * File: Dictionary.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 10/18/16
 * Purpose: This class implements a Dictionary ADT
 */

import java.util.Arrays;

public class Dictionary { 
    
    // basic definition of the Pair class; this is an "inner class" inside class Dictionary
    
    private class Pair {
        String key;
        String[] value;
        
        public Pair() { }              // default constructor, key and value will be nulls
        
        public Pair(String k, String[] v) {
            key = k;
            value = v;
        }
        
        public String toString() { 
            String s = key + ":\t[";
            for(int i = 0; i < value.length-1; ++i)
                s += value[i] + ",";
            return s + value[value.length-1] + "]";  
        }
        
    }
    
    private int LENGTH = 10;
    
    private Pair[] A = new Pair[LENGTH];
    private int next = 0;
    
    // If k is not in dictionary, insert new Pair(k, val) in alphabetical order
    // If k is in the dictionary, it's value will be replaced with the new value
    // If A is full, it is doubled in size
    public void insertStudent(String k, String[] val) {
        if (next == LENGTH) resize();
        for (int i = 0; i < A.length; i++) {
            if (A[i] != null) {
                if (k.compareTo(A[i].key) < 0) {
                    for (int j = next; j > i; j--) {
                        A[j] = A[j - 1];
                    }
                    A[i] = new Pair(k, val);
                    break;
                } else if (k.compareTo(A[i].key) == 0) {
                    A[i].value = val;
                }
            } else {
                A[next] = new Pair(k, val);
                break;
            } 
        }
        next++;
    }

    // Returns the array of classes associated with k
    // If k is not found, returns null
    public String[] lookupClasses(String k) {
        int index = location(k, 0, next - 1);
        if (index == -1) return null;
        return A[index].value;
    }

    // Uses binary search to see if k is a key inside the dictionary
    // Returns true if k is found
    public boolean member(String k) {
        if (location(k, 0, next - 1) == -1) return false;
        return true;
    }

    // Uses binary search to find and return location of key k
    // Returns -1 if not found
    private int location(String k, int lo, int hi) {
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (k.compareTo(A[mid].key) == 0) {
                return mid;
            } else if (k.compareTo(A[mid].key) < 0) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    // If k is in the dictionary, remove it and it's value
    // Then shift all elements greater than k down one
    public void deleteStudent(String k) {
        if (!member(k)) return;
        int index = location(k, 0, next - 1);
        for (int i = index; i < next - 1; i++) {
            A[i] = A[i + 1];
        }
        next--;
    }

    // If key k is in dictionary and it's value contains class c, c will be removed from that value
    public void dropClass(String k, String c) {
        int index = location(k, 0, next - 1);
        if (index == -1) return;

        String[] oldClasses = A[index].value;

        if (!memberArray(c, oldClasses)) return;

        String[] newClasses = new String[oldClasses.length - 1];
        for (int i = 0, j = 0; i < oldClasses.length; i++) {
            if (!c.equals(oldClasses[i])) {
                newClasses[j] = oldClasses[i];
                j++;
            }
        }
        A[index].value = newClasses;
    }

    // If key k is in the dictionary and doesn't have class c in its value, add c to its value
    // If key k is not in the dictionary, insert it with c as its only class
    public void addClass(String k, String c) {
        int index = location(k, 0, next - 1);
        if (index == -1) {
            String[] val = {c};
            insertStudent(k, val);
            return;
        }

        String[] oldClasses = A[index].value;

        if (memberArray(c, oldClasses)) return;

        String[] newClasses = new String[oldClasses.length + 1];
        for (int i = 0, j = 0; i < oldClasses.length; i++) {
            if (!c.equals(oldClasses[i])) {
                newClasses[j] = oldClasses[i];
                j++;
            }
        }
        newClasses[newClasses.length - 1] = c;
        A[index].value = newClasses;
    }

    // Returns true if key k is in the dictionary and has class c in its value
    // Returns false otherwise
    public boolean enrolled(String k, String c) {
        int index = location(k, 0, next - 1);
        if (index == -1) return false;
        return memberArray(c, A[index].value);
    }
    
    // Returns the number of pairs in the dictionary
    public int size() {
        return next;
    }
    
    // Returns true if the dictionary has no elements, false otherwise
    public boolean isEmpty() {
        return (next == 0);
    }

    // Resizes the dictionary to twice its size
    private void resize() {
        LENGTH *= 2;
        Pair[] T = new Pair[LENGTH];
        for (int i = 0; i < A.length; i++) {
            T[i] = A[i];
        }
        A = T;
    }

    // Returns true if string k is in the array C, and false if not
    private boolean memberArray(String k, String[] C) {
        for (int i = 0; i < C.length; i++) {
            if (C[i].equals(k)) {
                return true;
            }
        }
        return false;
    }
    
    // Prints the dictionary in a readable fashion
    private void printDictionary() {
        for(int i = 0; i < next; ++i)
            System.out.println(i + ": " + A[i]);
    }
    
    public static void main(String[] args) {
        
        
        Dictionary D = new Dictionary(); 
        
        
        // Insert three (key,value) pairs and test basic methods
        String[] A = { "CS111", "CS131", "WR99", "EC101" };
        String[] B = { "CS111", "MA123", "WR100", "SO100" };
        String[] C = { "CS111", "MA294", "WR150", "CL212" };
        String[] E = { "CS350", "CS235", "EC101", "MU101" };
        String[] F = { "CS111", "MA124", "BI108", "SO105" };
        String[] G = { "CS591", "MA242", "EN212", "EC101" };
        
        // Use step-wise refinement: Uncover one test at a time and implement only what you need
        // to pass that test.

        D.insertStudent("Christie,Chris",A); 
        D.insertStudent("Carson,Ben", B);
        D.insertStudent("Trump,Donald", C);
        D.insertStudent("Kasich,John",E); 
        D.insertStudent("Cruz,Ted", F);
        D.insertStudent("Bush,Jeb", G);
        
        System.out.println("\n[1] Should print out:"); 
        System.out.println("0: Bush,Jeb: [CS591,MA242,EN212,EC101]");
        System.out.println("1: Carson,Ben: [CS111,MA123,WR100,SO100]");
        System.out.println("2: Christie,Chris: [CS111,CS131,WR99,EC101]");
        System.out.println("3: Cruz,Ted: [CS111,MA124,BI108,SO105]");
        System.out.println("4: Kasich,John: [CS350,CS235,EC101,MU101]");
        System.out.println("5: Trump,Donald: [CS111,MA294,WR150,CL212]\n");
        
        D.printDictionary(); 
        
        System.out.println("\n[2] Should print out:\n6"); 
        System.out.println(D.size()); 
        
        System.out.println("\n[3] Should print out:\nfalse"); 
        System.out.println(D.isEmpty()); 
        
        System.out.println("\n[4] Should print out:\ntrue"); 
        System.out.println(D.member("Cruz,Ted")); 
        
        System.out.println("\n[5] Should print out:\nfalse"); 
        System.out.println(D.member("Jindal,Bobby")); 
        
        D.deleteStudent("Bush,Jeb");
        D.deleteStudent("Christie,Chris");
        System.out.println("\n[6] Should print out:\nfalse  false"); 
        System.out.println(D.member("Bush,Jeb") + "  " + D.member("Christie,Chris")); 
        
        System.out.println("\n[7] Should print out:\n[CS111, MA123, WR100, SO100]"); 
        String name = "Carson,Ben";
        System.out.println(Arrays.toString(D.lookupClasses(name))); 
        
        name = "Jindal,Bobby";
        System.out.println("\n[8] Should print out:\nnull");
        System.out.println(Arrays.toString(D.lookupClasses(name))); 
        
        System.out.println("\n[9] Should print out:\n[CS111, WR100, SO100]");  
        D.dropClass("Carson,Ben", "MA123");
        D.dropClass("Carson,Ben", "EC102"); 
        System.out.println(Arrays.toString(D.lookupClasses("Carson,Ben"))); 
 
        
        System.out.println("\n[10] Should print out:\n[CS111, MA294, WR150, CL212, CS591]");  
        D.addClass("Trump,Donald", "CS591");
        D.addClass("Trump,Donald", "WR150"); 
        System.out.println(Arrays.toString(D.lookupClasses("Trump,Donald"))); 

        
        System.out.println("\n[11] Should print out:\nfalse  true"); 
        D.dropClass("Walker,Scott","PH150");
        System.out.print(D.member("Walker,Scott") + "  " );
        D.addClass("Walker,Scott","PH110"); 
        System.out.println(D.member("Walker,Scott"));
        
        
        System.out.println("\n[12] Should print out:\ntrue"); 
        System.out.println(D.enrolled("Trump,Donald", "CS591"));  
        
        System.out.println("\n[13] Should print out:\nfalse"); 
        System.out.println(D.enrolled("Trump,Donald", "CS101")); 
        
        
        System.out.println("\n[14] Testing resizing; should print out a dictionary with 14 pairs.\n"); 
        D.insertStudent("Clinton, Hillary",A); 
        D.insertStudent("Sanders,Bernie", B);
        D.insertStudent("Lincoln,Abraham", C);
        D.insertStudent("Kennedy,John",E); 
        D.insertStudent("Bush,George", F);
        D.insertStudent("Reagan,Ronald", G);
        D.insertStudent("Nixon,Dick",A); 
        D.insertStudent("Carter,Jimmy", B);
        D.insertStudent("Johnson,Lyndon", C);
          
        D.printDictionary(); 
    
    }
    
}




