/* 
 * File: MyStringArray.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 09/20/2016
 * Purpose: This class tests various methods on char arrays using only one library (Java Standard Math Library)
 */

package HW02.B3;

public class MyStringArray { 
    
    // Declare various final (i.e., constant) values to be used to indicate errors of various types
    
    public static final char[] errorString  = { 'N', 'a', 'S' };   // These next three are our choices for error outputs
    public static final char errorCharacter = '#';
    public static final int errorInteger    = Integer.MIN_VALUE;
    public static final double errorDouble  = Double.NaN;          // Nan results when dividing by 0, etc.
    
    // charAt(s, i) returns the char value at index i, or an error value if the index is out of range
    public static char charAt(char[] s, int i) {
        if (i < 0 || i >= s.length) {
            return errorCharacter;
        } else {
            return s[i];
        }
    }
    
    // length(s) returns the length of char array s
    public static int length(char[] s) {
        return s.length;
    }
    
    // subString(s, l, r) returns a char array made up of chars from s, starting at index l and ending at index r - 1
    // it will return an erro value if the given indices are out of range
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
    
    // toLowerCase(c) returns a char array the same as c, except with all uppercase chars converted to their lowercase counterparts
    // chars in c that are already lowercase are simply copied to the new char array
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
    
    // concatenate(a, b) returns a char array consisting of the chars in a followed by the chars in b
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
    
    // intValueOf(a) returns an integer representation of the chars in a
    // ex. ['2', '3', '4'] returns 234
    // if a non-digit is present in a, an error value is returned
    public static int intValueOf(char[] a) {
        int value = 0;
        for (int i = 0; i < a.length; i++) {
            if (((int) a[i]) == 45) {
                continue;
            } else if (((int) a[i]) == 43) {
                continue;
            } else if (((int) a[i]) >= 48 || ((int) a[i]) <= 57) {
                continue;
            } else {
                return errorInteger;
            }
        }
        boolean isNegative = false;
        if (((int) a[0]) == 45) {
            isNegative = true;
            a = subString(a, 1, a.length);
        } else if (((int) a[0]) == 43) {
            isNegative = false;
            a = subString(a, 1, a.length);
        }
        for (int i = 0; i < a.length; i++) {
            value *= 10;
            if (((int) a[i]) < 48 || ((int) a[i]) > 57) {
                return errorInteger;
            } else {
                value += (((int) a[i]) - 48); // I based this logic off an answer from SO: http://stackoverflow.com/a/30414620
            }
        }
        if (isNegative) {
            value *= -1;
        }
        return value;
    }
    
    // doubleValueOf(a) returns an floating point representation of the chars in a
    // ex. ['2', '.', '3', '4'] returns 2.34
    // if a non-digit or extra decimals are present in a, an error value is returned
    public static double doubleValueOf(char[] a) {
        boolean isNegative = false;
        if (((int) a[0]) == 45) {
            isNegative = true;
            a = subString(a, 1, a.length);
        } else if (((int) a[0]) == 43) {
            isNegative = false;
            a = subString(a, 1, a.length);
        }
        if ((a[a.length - 1] == '.') && a.length > 1) {
            a = subString(a, 0, a.length - 1);
        }
        double value = 0.0;
        double divideBy = 0;
        boolean decimalExists = false;
        int decimalIndex = 0;
        for (int i = 0; i < a.length; i++) {
            if (((int) a[i]) == 46 && !decimalExists) {
                decimalExists = true;
                divideBy = Math.pow(10, ((a.length - 1) - i));
                decimalIndex = i;
                continue;
            } else if (((int) a[i]) == 46 && decimalExists) {
                return Double.NaN;
            }
        }
        for (int i = 0; i < a.length; i++) {
            if (((int) a[i]) == 45) {
                continue;
            } else if (((int) a[i]) == 43) {
                continue;
            } else if (((int) a[i]) >= 48 || ((int) a[i]) <= 57) {
                continue;
            } else {
                return Double.NaN;
            }
        }
        if (a.length == 1 && decimalExists) {
            a = new char[1];
            a[0] = '0';
        } else if (decimalExists) {
            char[] b = subString(a, 0, decimalIndex);
            char[] c = subString(a, decimalIndex + 1, a.length);
            a = concatenate(b, c);
        }
        for (int i = 0; i < a.length; i++) {
            value *= 10;
            if (((int) a[i]) == 46) {
                continue;
            } else if (((int) a[i]) < 48 || ((int) a[i]) > 57) {
                return Double.NaN;
            } else {
                value += (((int) a[i]) - 48);
            }
        }
        if (decimalExists) {
            value /= divideBy;
        }
        if (isNegative) {
            value *= -1;
        }
        return value;
    }
    
    // int2MyString(n) returns a char array where each char is a digit in n
    // ex. 234 returns ['2', '3', '4']
    public static char[] int2MyString(int n) {
        boolean isNegative = false;
        int count = 0;
        if (n < 0) {
            isNegative = true;
            count += 1;
            n *= -1;
        }
        int digitTest = n;
        while (digitTest > 0) {
            digitTest /= 10;
            count += 1;
        }
        char[] result = new char[count];
        for (int i = result.length - 1; i >= 0; i--) {
            if (isNegative && i == 0) {
                result[i] = '-';
            } else {
                result[i] = (char) ((n % 10) + 48);
                n /= 10;
            }
        }
        return result;
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