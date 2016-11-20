/* File: TestExceptions.java
 * Date: 11/17/16
 *  Author: Wayne Snyder (waysnyder@gmail.com)
 *  Purpose: Just a simple program to show
 *     how exceptions work for Lab 11.
 */

import java.util.Scanner;

public class TestExceptions {
    
    static Scanner in = new Scanner(System.in);
    
    public static int promptAndInputInteger() throws  TooLargeException,TooSmallException {
        
        System.out.println("Input Positive Integer between 5 and 10:");
        int n = inputInteger(); 
        if(n > 10)
            throw new TooLargeException("Integer is too large: ", n);
        return n;
    }
    
    public static int inputInteger() throws TooSmallException {
        int m = in.nextInt(); 
        if(m < 5)
            throw new TooSmallException();
        else
            return m;
    }
    
    public static void main(String[] args) {
        try {                                          // try to execute this code; if an exception
            int k = promptAndInputInteger();           // occurs, it will be caught by the catch blocks
            System.out.println("Correct: " + k);     
        }
        // these will be executed ONLY if an exception occurred above
        catch (TooSmallException e) {                  // this will catch any TooSmallException
            System.out.println("Number too small!");
        }
        catch (Exception e) {                          // this will catch ANY Exception
            System.out.println(e.getMessage());   
        }     
        finally {                                      // do this at the end no matter what happens
            System.out.println("Done");
        } 
        
    }  
}

//  Note that you can include other class definitions in the same file, as long
//  as they are not declared as public: declare them without the access modifier.
//  They will be accessible to any code in the same directory as this file. 

//  This simply defines an exception as a class with no information in it; it is just
//  an object with a name, which can be thrown and caught, and a generic message can be printed out.

class TooSmallException extends Exception {}

//  This is a more complicated example of an exception, in which a constructor
//  is used to create an object which stores a message to be printed out using getMessage(),
//  now we can actually print out a message with information from the exact context in which
//  the error occurred. 

class TooLargeException extends Exception {
    public TooLargeException(String msg, int n) {
        // this invokes the constructor of the Exception class, which creates the
        // message which can be accessed by getMessage().
        super(msg + n);
    }
}