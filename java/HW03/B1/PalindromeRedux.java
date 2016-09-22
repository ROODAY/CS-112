/*
 * Palindrome.java
 * 
 * Author: Wayne Snyder
 * Boston University CS 112, Spring 2016
 * 
 * Purpose: This is an exercise in debugging by implementing a
 * palindrome-checker using an auxiliary array. 
 */


public class PalindromeRedux {
    
    public static final int SIZE = 10; 
    
    // Check whether str is a palindrome by moving first half of the string
    // to an array, and then go backward through array and forward
    // through str and compare. 
    
    public static boolean palindrome(String str) {
        
        char[] A = new char[SIZE];        // array to hold first half of string
        int next = 0;                     // next location to put a character                  
        
        // Move first half of str into the array.
        for (int i = 0; i < str.length() / 2; i++) {   
            A[next] = str.charAt(i);
            ++next;
        }
        
        
        // Compare array backwards with rest of str. 
        
        for (int i = str.length() / 2; i < str.length(); i++) {                                 
            --next; 
            char c = A[next];
            if (c == str.charAt(i))            
                return true;
        }
        
        return false;
    }     
    
    public static void main(String[] args) {
        
        System.out.println("\nIs redder a palindrome? Should be true:");
        System.out.println(palindrome("redder"));
        
        System.out.println("\nIs reddet a palindrome? Should be false:");
        System.out.println(palindrome("reddet"));
        
        // Additional test cases should follow the same pattern.....

        
    }
    
}