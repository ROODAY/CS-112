/* 
 * File: BigInt.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 09/26/2016
 * Purpose: This class implements a library to store large integers containing up to 20 digits
 */

import java.util.Arrays;                    // so that we can print out arrays using Arrays.toString(...)

public class BigInt  {
    
    public static final int SIZE = 20;      // length of arrays representing big ints
    
    public static final int[] NaBI = { -1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };  // error value
    
    public static int[] intToBigInt(int n) {
        int[] result = new int[SIZE];
        if (n < 0) return NaBI;
        int index = 19;
        while (n > 0) {
            int digit = n % 10;
            result[index] = digit;
            n /= 10;
            index--;
        }
        return result;
    }
    
    public static int[] stringToBigInt(String s) {
        int[] result = new int[SIZE];
        if (s.length() > SIZE) return NaBI;
        int strIndex = s.length() - 1;
        int index = 19;
        while (strIndex >= 0) {
            int digit = (int) s.charAt(strIndex);
            if (digit < 48 || digit > 57) return NaBI;
            result[index] = digit - 48;
            strIndex--;
            index--;
        }
        return result;
    }
    
    
    public static String bigIntToString(int[] A) {
        int startIndex = SIZE;
        for (int i = 0; i < SIZE; i++) {
            int test = A[i];
            if (A[i] == 0) {
                continue;
            } else {
                startIndex =  i;
                break;
            }
        }
        String s = "";
        for (int i = startIndex; i < SIZE; i++) {
            if (A[i] < 0 || A[i] > 9) return "NaBI";
            s += A[i];
        }
        if (s.equals("")) return "0";
        return s; 
    }
    
    // Compare the two big integers A and B and return -1, 0, or 1, depending
    //  on whether A < B, A == B, or A > B, respectively.
    
    public static int compareTo(int[] A, int[] B) {
        for (int i = 0; i < SIZE; i++) {
            if (A[i] < B[i]) {
                return -1;
            } else if (A[i] > B[i]) {
                return 1;
            } else if (A[i] == B[i]) {
                continue;
            }
        }
        return 0;   
    }
    
    // Add two big integers and return a new array representing their sum; if the result overflows,
    //                                         i.e., contains more than SIZE digits, return NaBI. 
    
    public static int[] add(int[] A, int[] B) {
        int[] C = new int[SIZE];
        int index = 19;
        int carry = 0;
        while (index >= 0) {
            int digit = A[index] + B[index] + carry;
            if (digit > 9) {
                digit %= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            C[index] = digit;
            index--;
        }
        if (carry > 0) {
            return NaBI;
        } else {
            return C;
        } 
    }
    
    
// Unit Test: Here is where we put tests to verify that this class works properly; it is
// not used except for debugging and testing purposes, and will be ignored when you use
// this class as as a static library.
    
    public static void main(String [] args) {  
        
        System.out.println("\nUnit Test for BigInt Library.\n");
        
        // Note that we may call the methods without the prefix BigInt.
        // because we are testing from inside the class; if you call these
        // methods from outside the class you must use the prefix, e.g., BigInt.bigIntToString(A).
        
        System.out.println("Test 1: Should be\n1234567");
        int[] A = { 0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,3,4,5,6,7 };
        System.out.println(bigIntToString(A));
        System.out.println(); 
        
        System.out.println("Test 2: Should be\n0");
        int[] B = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        System.out.println(bigIntToString(B));
        System.out.println();  
        
        System.out.println("Test 3: Should be\nNaBI");
        int[] C = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        System.out.println(bigIntToString(C));
        System.out.println(); 
        
        System.out.println("Test 4: Should be\n[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7]");
        A = intToBigInt( 1234567 );
        System.out.println(Arrays.toString(A));
        System.out.println(); 
        
        System.out.println("Test 5: Should be\n1234567");
        System.out.println(bigIntToString(A));
        System.out.println();
        
        System.out.println("Test 6: Should be \n0");
        B = intToBigInt( 0 );
        System.out.println(bigIntToString(B));
        System.out.println();
        
        System.out.println("Test 7: Should be\nNaBI");
        C = intToBigInt( -4 );
        System.out.println(bigIntToString(C));
        System.out.println();
        
        System.out.println("Test 8: Should be\n[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 4, 1, 5, 9, 2]");
        System.out.println(Arrays.toString( stringToBigInt( "3141592" ) ));
        System.out.println();
        
        System.out.println("Test 9: Should be\n0");
        System.out.println(bigIntToString( stringToBigInt( "0" ) ));
        System.out.println();
        
        System.out.println("Test 10: Should be \nNaBI");
        System.out.println(bigIntToString( stringToBigInt( "234h56" ) ));
        System.out.println(); 
        
        System.out.println("Test 11: Should be\nNaBI");
        System.out.println(bigIntToString( stringToBigInt( "23456379238472937829384272939472937429374" ) ));
        System.out.println();
        
        System.out.println("Test 12: Should be\n0");
        System.out.println(compareTo( stringToBigInt("12375"), stringToBigInt("12375")));
        System.out.println();
        
        System.out.println("Test 13: Should be\n-1");
        System.out.println(compareTo( stringToBigInt("12375"), stringToBigInt("12378")));
        System.out.println();
        
        System.out.println("Test 14: Should be\n1");
        System.out.println(compareTo( stringToBigInt("12395"), stringToBigInt("12375")));
        System.out.println();
        
        System.out.println("Test 15: Should be\n0");
        System.out.println(compareTo( stringToBigInt("0"), stringToBigInt("0")));
        System.out.println();
        
        System.out.println("Test 16: Should be\n-1");
        System.out.println(compareTo( stringToBigInt("0"), stringToBigInt("12375")));
        System.out.println();
        
        System.out.println("Test 17: Should be\n1");
        System.out.println(compareTo( stringToBigInt("111"), stringToBigInt("0")));
        System.out.println();
        
        System.out.println("Test 18: Should be\n123456789123456789");
        System.out.println(bigIntToString(add( stringToBigInt("36182736036182736"), stringToBigInt("87274053087274053"))));
        System.out.println();
        
        System.out.println("Test 19: Should be\n3141592653598");
        System.out.println(bigIntToString(add( stringToBigInt("0"), stringToBigInt("3141592653598"))));
        System.out.println();
        
        System.out.println("Test 20: Should be\n10000000000000000000");
        System.out.println(bigIntToString(add( stringToBigInt("9999999999999999999"), stringToBigInt("1"))));
        System.out.println();
        
        System.out.println("Test 21: Should be\nNaBI");
        System.out.println(bigIntToString(add( stringToBigInt("99999999999999999999"), stringToBigInt("1"))));
        System.out.println();
        
        
    }
    
    
}