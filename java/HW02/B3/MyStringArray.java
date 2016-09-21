/* 
 * File: MyStringArray.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 09/20/2016
 * Purpose: This is a template for the solution to HW02, Problem B.4; the program
 *      compiles and runs, but fails the correctness tests in the "Unit Test Main" method. You should
 *      develop one method at a time, and verify that each satisfies the test before moving on. 
 * NOTE: You MUST rewrite this header comment to describe your solution.
 *       You MUST follow the Style Guidelines with respect to basic layout (ignore "Naming" for now);
 *       You MUST fill in where indicated in this template, and make any other changes
 *           you wish to satisfy the tests in main, but:
 *       Do NOT change anything in the main method or change the declarations of the error values, or
 *           the basic syntax of the method definitions (i.e., the first line of the method should be the same). 
 *       You MUST test for errors in the appropriate methods by returning the listed error values when appropriate.
 */

 public class MyStringArray { 

    // Declare various final (i.e., constant) values to be used to indicate errors of various types
    
    public static final char[] errorString  = { 'N', 'a', 'S' };   // These next three are our choices for error outputs
    public static final char errorCharacter = '#';
    public static final int errorInteger    = Integer.MIN_VALUE;
    public static final double errorDouble  = Double.NaN;          // Nan results when dividing by 0, etc.
    
    // Your comment here, describing this method
    public static char charAt(char[] s, int i) {
        if (i < 0 || i >= s.length) {
            return errorCharacter;
        } else {
            return s[i];
        }
    }

    // Your comment here, describing this method
    public static int length(char[] s) {
        return s.length;
    }

    // Your comment here, describing this method  
    public static char[] subString(char[] s, int l, int r) {
        if (l < 0 || l >= s.length || r < 0 || r > s.length) {
            return errorString;
        } else {
            char[] sub = new char[r - l];
            for (int i = l, j = 0; i < r; i++, j++) {
                sub[j] = s[i];
            }
            return sub;
        }
    }

    // Your comment here, describing this method
    public static char[] toLowerCase(char[] c) {
        char[] lowercase = new char[c.length];
        for (int i = 0; i < c.length; i++) {
            char letter = c[i];
            if (((int) letter) >= 65 && ((int) letter) <= 90) {
                char lowerletter = (char) (((int) letter) + 32);
                lowercase[i] = lowerletter;
            } else {
                lowercase[i] = letter;
            }
        }
        return lowercase;
    }

    // Your comment here, describing this method
    public static char[] concatenate(char[] a, char[] b) {
        char[] c = new char[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i];
        }
        for (int i = a.length, j = 0; j < b.length; i++, j++) {
            c[i] = b[j];
        }
        return c;
    }

    // Your comment here, describing this method
    public static int intValueOf(char[] a) {
        boolean isNegative = false;
        int[] nums = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            if (i == 0) {
                if (((int) a[i]) == 45) {
                    isNegative = true;
                } else if (((int) a[i]) == 43) {
                    isNegative = false;
                }
            }
            if (((int) a[i]) < 48 || ((int) a[i]) > 57) {
                return errorInteger;
            else {
                
            }
        }
    }

    // Your comment here, describing this method
    public static double doubleValueOf(char[] a) {
        // Your code here
        return Double.NaN;                      // return error, just to get it to compile 
    }

    // Your comment here, describing this method
    public static char[] int2MyString(int n) {
        // Your code here
        return errorString;                      // return error, just to get it to compile 
    }

    // This method provided for debugging
    public static void printCharArray(char[] A) {
        for(int i = 0; i < A.length; ++i) {
            System.out.print(A[i]);
        }
        System.out.println(); 
    }


    public static void main(String[] args) {

        System.out.println("\nGrading program for MyStringArray library\n");
        int testNum = 0; 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n8"); 
        char[] test = "CS112 A1".toCharArray(); 
        System.out.println(length(test)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\nC"); 
        System.out.println(charAt(test,0)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n1"); 
        System.out.println(charAt(test,7)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n#"); 
        System.out.println(charAt(test,9)); 
        System.out.println();
        
        System.out.println("Test " + (++testNum) + ": Should be:\nCS112"); 
        System.out.println(subString(test,0,5)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n12 A1"); 
        System.out.println(subString(test,3,8)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\nNaS"); 
        System.out.println(subString(test,-1,4)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\nNaS"); 
        System.out.println(subString(test,1,9)); 
        System.out.println();  
        
        System.out.println("Test " + (++testNum) + ": Should be:\ncs112 a1"); 
        System.out.println(toLowerCase(test)); 
        System.out.println();
        
        System.out.println("Test " + (++testNum) + ": Should be:\nCS112 A1"); 
        System.out.println(test); 
        System.out.println();
        
        System.out.println("Test " + (++testNum) + ": Should be:\nCS112 A1CS112 A1"); 
        System.out.println(concatenate(test,test)); 
        System.out.println();
        
        // 12
        System.out.println("Test " + (++testNum) + ": Should be:\n234"); 
        test = "234".toCharArray(); 
        System.out.println(intValueOf(test)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n-234"); 
        test = "-234".toCharArray(); 
        System.out.println(intValueOf(test)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n-2147483648"); 
        test = "234.4".toCharArray(); 
        System.out.println(intValueOf(test)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n-2147483648"); 
        test = "23a4".toCharArray(); 
        System.out.println(intValueOf(test)); 
        System.out.println();
        
        System.out.println("Test " + (++testNum) + ": Should be:\n3.141592"); 
        test = "3.141592".toCharArray(); 
        System.out.println(doubleValueOf(test)); 
        System.out.println();   
        
        System.out.println("Test " + (++testNum) + ": Should be:\n-3.141592"); 
        test = "-3.141592".toCharArray(); 
        System.out.println(doubleValueOf(test)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n10.0"); 
        test = "10.".toCharArray(); 
        System.out.println(doubleValueOf(test)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\n0.5"); 
        test = ".5".toCharArray(); 
        System.out.println(doubleValueOf(test)); 
        System.out.println();
        
        System.out.println("Test " + (++testNum) + ": Should be:\n0.0"); 
        test = ".".toCharArray(); 
        System.out.println(doubleValueOf(test)); 
        System.out.println();
        
        System.out.println("Test " + (++testNum) + ": Should be:\n234.0"); 
        test = "234".toCharArray(); 
        System.out.println(doubleValueOf(test)); 
        System.out.println();
        
        System.out.println("Test " + (++testNum) + ": Should be:\nNaN"); 
        test = "3.141.592".toCharArray(); 
        System.out.println(doubleValueOf(test)); 
        System.out.println(); 
        
        System.out.println("Test " + (++testNum) + ": Should be:\nNaN"); 
        test = "3.141a592".toCharArray(); 
        System.out.println(doubleValueOf(test)); 
        System.out.println(); 
        
        int n = 12345; 
        System.out.println("Test " + (++testNum) + ": Should be:\n12345"); 
        printCharArray( int2MyString(n) ); 
        System.out.println(); 
        
        n = -45; 
        System.out.println("Test " + (++testNum) + ": Should be:\n-45"); 
        printCharArray( int2MyString(n) ); 
        System.out.println(); 
        
        
    }

}