/* 
 * File: BigIntegerCalculator.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 10/15/16
 * Purpose: This class implements a primitive calculator using generic Stacks
 */

import java.io.*;               
import java.util.*;

public class BigIntegerCalculator {
    
    // Split a String into separate tokens, either positive numbers, * or +, or right or left parentheses,
    //     ignoring whitespace; assumes there are less than 50 tokens
    
    private static int numTokens = 20;                    // maximum number of tokens allowed
    
    private static String[] parse(String s) {
        
        String[] errorResult = { "0" };
        
        String[] inputTokens = new String[numTokens];     // token array for initial list of tokens
        int next = 0;                                     // pointer to end of list of tokens in previous
        
        int beginning = 0;                                // beginning index of next token
        
        int i = 0;
        for ( ; i < s.length(); ++i) {
            char c = s.charAt(i); 
            
            if(Character.isWhitespace(c)) {               // ignore whitespace
                if (beginning < i) {                      // must be number preceding this ws char
                    inputTokens[next++] = s.substring(beginning, i);
                    beginning = i;
                }
                ++beginning; 
                continue;         
            }                                             // parens and operators are single-char tokens
            else if (c == '(' || c == ')' || c == '*' || c == '+') {  
                if (beginning < i) {                      // must be number preceding this token
                    inputTokens[next++] = s.substring(beginning, i);
                    beginning = i;
                }
                inputTokens[next++] = s.substring(beginning, i + 1);
                ++beginning;
            }
            else if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                System.err.println("Error in input String!");
                return errorResult;
            }
            // else must be digit, keep going
        }
        if (beginning < i) {                              // must be number preceding this ws char
            inputTokens[next++] = s.substring(beginning, i);
            beginning = i;
        }
        
        // right-size the array
        String[] result = new String[next];
        for (int j = 0; j < next; ++j) {
            result[j] = inputTokens[j];
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        Stack<String> Ops = new Stack<String>();
        Stack<BigInteger> Nums = new Stack<BigInteger>();
        
        Scanner sc = new Scanner(System.in);
        
        while(sc.hasNext()) {
            String input = sc.nextLine();
            String[] tokens = parse(input);
            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i].equals("+") || tokens[i].equals("*")) {
                    Ops.push(tokens[i]);
                } else if (tokens[i].equals(")")) {
                    BigInteger A = Nums.pop();
                    BigInteger B = Nums.pop();
                    String Op = Ops.pop();
                    if (Op.equals("*")) {
                        Nums.push(A.mult(B));
                    } else if (Op.equals("+")) {
                        Nums.push(A.add(B));
                    }
                } else if (!tokens[i].equals("(")) {
                    Nums.push(new BigInteger(tokens[i]));
                }
            }
            System.out.println(Nums.pop()); 
        }
        
        System.out.println("Bye!");        
    }
}