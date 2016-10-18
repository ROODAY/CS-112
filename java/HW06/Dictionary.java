/* File: Dictionary.java
 * Author: Wayne Snyder
 * Date: 10/14/2016
 * Purpose: This is the template for HW06, Problem B.2 for CS 112; it is a fairly standard
 *          implementation of a dictionary data structure. 
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
    
    
     // YOUR CODE HERE!
    
    
    
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
        String[] G = { "CS591", "MA442", "EN212", "EC101" };
        
        // Use step-wise refinement: Uncover one test at a time and implement only what you need
        // to pass that test.
  /*      
        D.insertStudent("Christie,Chris",A); 
        D.insertStudent("Carson,Ben", B);
        D.insertStudent("Trump,Donald", C);
        D.insertStudent("Kasich,John",E); 
        D.insertStudent("Cruz,Ted", F);
        D.insertStudent("Bush,Jeb", G);
        
        System.out.println("\n[1] Should print out:"); 
        System.out.println("0: Bush,Jeb: [CS591,MA442,EN212,EC101]");
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
   */     
    }
    
}




