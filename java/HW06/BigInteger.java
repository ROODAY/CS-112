/* 
 * File: BigInteger.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 10/15/16
 * Purpose: This class refactors the BigInt library into an object
 */

import java.util.Arrays;

public class BigInteger  {    
    
    private final int SIZE = 100;      // length of arrays representing big ints
    private final int[] NaBI = { -1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
    private int[] A; 
    
    // Constructor: create a BigInteger with value 0
    
    public BigInteger() {
        A = new int[SIZE];
    }   
    
    // Constructor: convert the String s into a big integer;
    // Note: does no error checking
    
    public BigInteger(String s) {
        A = new int[SIZE];
        if (s.length() <= SIZE) {
            int strIndex = s.length() - 1;
            int index = SIZE - 1;
            while (strIndex >= 0) {
                int digit = (int) s.charAt(strIndex);
                if (digit >= 48 && digit <= 57) {
                    A[index] = digit - 48;
                    strIndex--;
                    index--;
                }
            }
        }
    }
    
    // Constructor which takes array of ints
    // Note: does no error checking
    
    public BigInteger(int[] B) {
        A = new int[SIZE];
        int endOfA = A.length - 1;
        int endOfB = B.length - 1;
        for (int i = endOfA, j = endOfB; j >= 0; j--, i--) {
            A[i] = B[j];
        }
    }
    
    // Return a String representation of the big integer (with leading zeros suppressed).
    // Don't worry about error checking
    
    public String toString() {  
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
    
    // Compare this BigInteger B and return -1, 0, or 1, depending
    //  on whether this < B, this == B, or this > B, respectively.
    
    public int compareTo(BigInteger B) {
        return compareArrays(A, B.A, 0);    // Note how we can access A inside B even though private
    }
    
    // This helper method compares two arrays as in the BigInt class, but does it
    // recursively
    
    private int compareArrays(int[] A, int[] B, int pos) {
        if (pos >= SIZE) return 0;
        if (A[pos] < B[pos]) {
            return -1;
        } else if (A[pos] > B[pos]) {
            return 1;
        } else {
            return compareArrays(A, B, pos+1);
        }
    }
    
    
    // Add two big integers and return a new BigInteger representing their sum. 
    // Note: Ignores overflow
    
    public BigInteger add(BigInteger B) {
        int[] S = new int[SIZE];
        addArrays(A, B.A, S, SIZE-1, 0);  // Note that can refer to B.A although is private
        return new BigInteger( S );
    }
    
    // This method adds to arrays, as in the BigInt class, but does it recursively;
    // The result is stored in the array S, which will be accessed by the calling method add(...)
    
    private void addArrays(int[] A, int[] B, int[] S, int pos, int carry) { 
        if (pos < 0) return;
        int result = A[pos] + B[pos] + carry;
        int digit = result % 10;
        carry = result / 10;
        S[pos] = digit;
        addArrays(A, B, S, pos-1, carry);
    }
    
    
    // Multiply BigInteger by a single digit 
    // Note: Ignores overflow
    
    private BigInteger multByDigit(int d) {
        int[] S = new int[SIZE];
        multArrayByDigit(A, d, S, SIZE-1, 0);
        return new BigInteger( S );
    }
     
    private void multArrayByDigit(int[] A, int d, int[] S, int pos, int carry) { 
        if (pos < 0) return;
        int result = A[pos] * d + carry;
        int digit = result % 10;
        carry = result / 10;
        S[pos] = digit;
        multArrayByDigit(A, d, S, pos-1, carry);   
    }
    
    // Multiply this by another BigInteger 
    // Note: Ignores overflow
    
    public BigInteger mult(BigInteger B) {
        BigInteger a = new BigInteger(A);
        BigInteger P = new BigInteger();
        return multHelper(a, B.A, P, SIZE-1);
    }
    
    private BigInteger multHelper(BigInteger A, int[] B, BigInteger P, int pos) {
        if (pos >= 0) {
            P = P.add(A.multByDigit(B[pos]));
            return multHelper(A.multByDigit(10), B, P, pos - 1);
        } else {
            return P;
        }
    }


    
    // Unit Test Main 
    
    public static void main(String [] args) {  
        
        System.out.println("\nUnit Test for BigInteger Library.\n");
        
        System.out.println("Test 1: Should be\n123456789");
        int[] temp = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        BigInteger A = new BigInteger( temp );
        System.out.println(A);
        System.out.println(); 
        
        System.out.println("Test 2: Should be\n0");
        int[] temp2 = { 0 };
        BigInteger B = new BigInteger( temp2 );
        System.out.println(B);
        System.out.println();  
        
        System.out.println("Test 3: Should be\n60213517");
        BigInteger C = new BigInteger( "60213517" );
        System.out.println(C);
        System.out.println(); 
        
        BigInteger D = new BigInteger( "63243272" );
        
        System.out.println("Test 4: Should be\n123456789");
        System.out.println(C.add(D));
        System.out.println();
        
        System.out.println("Test 5: Should be\n123456789");
        System.out.println(D.add(C));
        System.out.println();
        
        System.out.println("Test 6: Should be\n123456789");
        System.out.println(B.add(A));
        System.out.println();
        
        System.out.println("Test 7: Should be\n123456789");
        System.out.println(A.add(B));
        System.out.println();
        
        System.out.println("Test 8: Should be \n0");
        System.out.println(B.add(B));
        System.out.println();
        
        System.out.println("Test 9: Should be \n0");
        System.out.println(C.compareTo(C));
        System.out.println();
        
        System.out.println("Test 10: Should be \n-1");
        System.out.println(C.compareTo(D));
        System.out.println();
        
        System.out.println("Test 11: Should be \n1");
        System.out.println(D.compareTo(C));
        System.out.println();
        
        System.out.println("Test 12: Should be\n0");
        System.out.println(A.multByDigit(0));
        System.out.println();
        
        System.out.println("Test 13: Should be\n123456789");
        System.out.println(A.multByDigit(1));
        System.out.println();
        
        System.out.println("Test 14: Should be\n617283945");
        System.out.println(A.multByDigit(5));
        System.out.println();
        
        System.out.println("Test 15: Should be\n1111111101");
        System.out.println(A.multByDigit(9));
        System.out.println();
        
        System.out.println("Test 16: Should be\n0");
        System.out.println(A.mult( new BigInteger("0")));
        System.out.println();
        
        System.out.println("Test 17: Should be\n123456789");
        System.out.println(A.mult( new BigInteger("1")));
        System.out.println();
        
        System.out.println("Test 18: Should be\n782");
        BigInteger E = new BigInteger("23");
        BigInteger F = new BigInteger("34");
        System.out.println(E.mult(F));
        System.out.println();
        
        System.out.println("Test 19: Should be\n782");
        System.out.println(F.mult(E));
        System.out.println(); 
        
        System.out.println("Test 20: Should be\n3808099833707624");
        System.out.println(C.mult(D));
        System.out.println();
        
        System.out.println("Test 21: Should be\n3808099833707624");
        System.out.println(D.mult(C));
        System.out.println(); 
        
        System.out.println("Test 22: Should be\n963927314415819005132336");
        System.out.println(D.mult(C.add(A.mult(C.add(D)))));
        System.out.println();
        
    }
    
    
}