/* 
 * File: PalindromeRedux.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 09/24/2016
 * Purpose: This class is a fixed version of a bugged Palindrome Test class.
 * It uses a debug method to ensure the palindrome checking logic functions properly.
 */

import java.util.Arrays;

public class PalindromeRedux {
    
    public static final int SIZE = 10;
    public static boolean debug = true;
    
    // Check whether str is a palindrome by moving first half of the string
    // to an array, and then go backward through array and forward
    // through str and compare. 
    
    public static boolean palindrome(String str) {
        
        char[] A = new char[SIZE];        // array to hold first half of string
        int next = 0;                     // next location to put a character                  
        
        
        db("Half index = " + (str.length() / 2));
        
        // Move first half of str into the array.
        for (int i = 0; i < str.length() / 2; i++) {   
            A[next] = str.charAt(i);
            ++next;
        }
        
        db("String = " + str);
        db("Array = " + Arrays.toString(A));
        
        // Compare array backwards with rest of str. 
        
        boolean isPalindrome = true;
        
        if (str.length() % 2 == 0) {
            for (int i = str.length() / 2; i < str.length(); i++) {
                --next;
                db("Next = " + next);
                db("i = " + i);
                char c = A[next];
                db("c = " + c);
                db("char at = " + str.charAt(i));
                if (c != str.charAt(i)) {       
                    isPalindrome = false;
                    break;
                }
            }
        } else {
            for (int i = str.length() / 2 + 1; i < str.length(); i++) {
                --next;
                db("Next = " + next);
                db("i = " + i);
                char c = A[next];
                db("c = " + c);
                db("char at = " + str.charAt(i));
                if (c != str.charAt(i)) {       
                    isPalindrome = false;
                    break;
                }
            }
        }
        
        
        return isPalindrome;
    }
    
    public static void db(String s) { 
        if(debug)
            System.err.println("\t" + s);
    }
    
    public static void main(String[] args) {
        
        System.out.println("\nIs redder a palindrome? Should be true:");
        System.out.println(palindrome("redder"));
        
        System.out.println("\nIs reddet a palindrome? Should be false:");
        System.out.println(palindrome("reddet"));
        
        // Additional test cases should follow the same pattern.....
        
        System.out.println("\nIs abcba a palindrome? Should be true:");
        System.out.println(palindrome("abcba"));
        
        System.out.println("\nIs abcda a palindrome? Should be false:");
        System.out.println(palindrome("abcda"));
        
        System.out.println("\nIs string with one symbol a palindrome? Should be true:");
        System.out.println(palindrome("a"));
        
        System.out.println("\nIs empty string a palindrome? Should be true:");
        System.out.println(palindrome(""));
        
        
    }
    
}