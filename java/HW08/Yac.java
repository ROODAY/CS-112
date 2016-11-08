/* 
 * File: Yac.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 11/01/16
 * Purpose: This class implements a primitive calculator using generic Stacks and hierarchical linked lists
 */

import java.util.Arrays;

public class Yac {
    
    private static boolean traceOn = false;       // set to true to print out stacks each time through loop
    
    private static class Node {
        String op;                  // see homework writeup for thorough explanation of these
        double num;
        Node exp;
        Node next;
        
        // May not need all these constructors, but just in case
        
        public Node(String op, double num, Node exp, Node next) {
            this.op = op;
            this.num = num;
            this.next = next;
            this.exp = exp;
        }
        
        public Node(String op) {
            this.op = op;
            this.num = 0.0;
            this.next = null;
            this.exp = null;
        }
        
        public Node(double num) {
            this.op = "";
            this.num = num;
            this.next = null;
            this.exp = null;
        }
        
        // returns prefix expression corresponding to the hierarchical LL
        // if just a single operator on Ops stack, will print out the operator
        
        public String toString() {
            if (!op.equals("")) {
                if (next == null)
                    return op;            // just a single operator on the stack
                else
                    return op + "(" + next + "," + next.next + ")";   // recursive toString!
            }
            else if (exp == null)
                return "" + num;
            else
                return "" + exp;
            
        }
        
        // will tell you what kind of node this is
        
        public boolean isOp() {
            return !op.equals("");
        }
        
        public boolean isNum() {
            return op.equals("") && exp == null;
        }
        
        public boolean isExpr() {
            return exp != null;
        }
    }   
    
    
    // Split a String into separate tokens, either positive doubles, operators, or right or left parentheses,
    //     ignoring whitespace; assumes there are less than 50 tokens.
    // DO NOT CHANGE THIS UNLESS YOU KNOW WHAT YOU ARE DOING
    
    private static int numTokens = 50;                    // maximum number of tokens allowed
    
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
            }
            else if (c == '(' || c == ')' || c == '*' || c == '+' || c == '-' || c == '/') {  // parens and operators are single-char tokens
                if (beginning < i) {        // must be number preceding this token
                    inputTokens[next++] = s.substring(beginning, i);
                    beginning = i;
                }
                inputTokens[next++] = s.substring(beginning, i + 1);
                ++beginning;
            }
            else if ((s.charAt(i) < '0' || s.charAt(i) > '9') && s.charAt(i) != '.') {
                System.err.println("Error in input String!");
                return errorResult;
            }
            // else must be digit, keep going
        }
        if (beginning < i) {                      // must be number preceding this ws char
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
    
    public static double evaluate(String inputExpression) {
        
        return eval( buildExpression(inputExpression) );
        
    }
    
    // takes a string representation of an expression and returns a corresponding
    // hierarchical linked list.  Does not error checking, assumes all expressions
    // are fully parenthesized.
    
    public static Node buildExpression(String inputExpression) {
        
        Stack<Node> Ops = new Stack<Node>();
        Stack<Node> Exprs = new Stack<Node>(); 
        
        String[] tokens = parse(inputExpression);
        
        for (int i = 0; i < tokens.length; i++) {
            String t = tokens[i];
            if (t.equals("(")) {
                continue;
            } else if (t.equals("/") || t.equals("*") || t.equals("-") || t.equals("+")) {
                Node operator = new Node(t);
                Ops.push(operator);
            } else if (t.equals(")")) {
                Node two = Exprs.pop();
                if (two.isOp()) two = new Node("", 0.0, two, null);
                Node one = Exprs.pop();
                if (one.isOp()) one = new Node("", 0.0, one, null);
                Node operator = Ops.pop();
                operator.next = one;
                one.next = two;
                Exprs.push(new Node("", 0.0, operator, null));
            } else {
                Node expression = new Node(Double.parseDouble(t));
                Exprs.push(expression);
            }
            
            if (traceOn) {
                System.out.println("\nOps: " + Ops); 
                System.out.println("Exprs: " + Exprs);
            }
        }
        return Exprs.pop();
    }
    
    // recursive evaluator, returns double value calculated by a hierarchical linked list
    
    public static double eval(Node e) {
        if (e.isNum()) {
            return e.num;
        } else if (e.isExpr()) {
            return eval(e.exp);
        } else if (e.isOp()) {
            if (e.op.equals("/")) {
                return eval(e.next) / eval(e.next.next);
            } else if (e.op.equals("*")) {
                return eval(e.next) * eval(e.next.next);
            } else if (e.op.equals("-")) {
                return eval(e.next) - eval(e.next.next);
            } else if (e.op.equals("+")) {
                return eval(e.next) + eval(e.next.next);
            }
        }
        return 0.0; // this should never be reached but Java will yell at me if there isn't a return outside an if
    }
    
    // Unit Test Main
    
    public static void main(String[] args) {
        
        System.out.println("Unit Test for Yac");
        
        String solution;
        String answer; 
        
        //  Step-wise refinement:  Uncomment one test at a time as you develop your methods
        
        Node e = buildExpression("3.141592");
        solution = "3.141592";
        answer = "" + e; 
        System.out.println("\nTest 01:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        e = buildExpression("2.718281");
        solution = "2.718281";
        answer = "" + e; 
        System.out.println("\nTest 02:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        e = buildExpression("0.0");
        solution = "0.0";
        answer = "" + e; 
        System.out.println("\nTest 03:  Should print out:\n" + solution);
        System.out.println(answer);
        
        e = buildExpression("( 1.0 + 2.0)");
        solution = "+(1.0,2.0)";
        answer = "" + e; 
        System.out.println("\nTest 04:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        e = buildExpression("(3.141592 - 2.718182)");
        solution = "-(3.141592,2.718182)";
        answer = "" + e; 
        System.out.println("\nTest 05:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        e = buildExpression("(3.141592 * 2.718182)");
        solution = "*(3.141592,2.718182)";
        answer = "" + e; 
        System.out.println("\nTest 06:  Should print out:\n" + solution);
        System.out.println(answer);
        
        e = buildExpression("(3.141592 - 2.718182)");
        solution = "-(3.141592,2.718182)";
        answer = "" + e; 
        System.out.println("\nTest 07:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        e = buildExpression("(3.141592 / 2.718182)");
        solution = "/(3.141592,2.718182)";
        answer = "" + e; 
        System.out.println("\nTest 08:  Should print out:\n" + solution);
        System.out.println(answer);     
        
        e = buildExpression("( (2.0 * 2.0) + 1.0)");
        solution = "+(*(2.0,2.0),1.0)";
        answer = "" + e; 
        System.out.println("\nTest 09:  Should print out:\n" + solution);
        System.out.println(answer);   
        
        e = buildExpression("( 10.0 * (4.0 / 2.0))");
        solution = "*(10.0,/(4.0,2.0))";
        answer = "" + e; 
        System.out.println("\nTest 10:  Should print out:\n" + solution);
        System.out.println(answer);    
        
        e = buildExpression("( (10.0 - 4.0) * 2.0)");
        solution = "*(-(10.0,4.0),2.0)";
        answer = "" + e; 
        System.out.println("\nTest 11:  Should print out:\n" + solution);
        System.out.println(answer);    
        
        e = buildExpression("( (4 * 2.3) + 7)");
        solution = "+(*(4.0,2.3),7.0)";
        answer = "" + e; 
        System.out.println("\nTest 12:  Should print out:\n" + solution);
        System.out.println(answer);
        
        e = buildExpression("( (2.0 - 1.0) * ( 3.0 + 2.0 ))");
        solution = "*(-(2.0,1.0),+(3.0,2.0))";
        answer = "" + e; 
        System.out.println("\nTest 13:  Should print out:\n" + solution);
        System.out.println(answer);
        
        e = buildExpression("( (5.0 * 2.0) - ( 3.0 / 2.0 ))");
        solution = "-(*(5.0,2.0),/(3.0,2.0))";
        answer = "" + e; 
        System.out.println("\nTest 14:  Should print out:\n" + solution);
        System.out.println(answer);
        
        e = buildExpression("( (10.0 - 1.0) / ( 3.0 + 0.0 ))");
        solution = "/(-(10.0,1.0),+(3.0,0.0))";
        answer = "" + e; 
        System.out.println("\nTest 15:  Should print out:\n" + solution);
        System.out.println(answer);
        
        // repeat all previous tests, this time returning a value
        
        solution = "3.141592";
        answer = "" + evaluate("3.141592"); 
        System.out.println("\nTest 16:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        solution = "2.718281";
        answer = "" + evaluate("2.718281"); 
        System.out.println("\nTest 17:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        solution = "0.0";
        answer = "" + evaluate("0.0"); 
        System.out.println("\nTest 18:  Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "3.0";
        answer = "" + evaluate("( 1.0 + 2.0)"); 
        System.out.println("\nTest 19:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        solution = "1.0";
        answer = "" + evaluate("(3.0 - 2.0)"); 
        System.out.println("\nTest 20:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        solution = "12.0";
        answer = "" + evaluate("(3.0 * 4.0)"); 
        System.out.println("\nTest 21:  Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "5.0";
        answer = "" + evaluate("(10.0 / 2.0)"); 
        System.out.println("\nTest 22:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        solution = "0.0";
        answer = "" + evaluate("(0.0 / 2.0)"); 
        System.out.println("\nTest 23:  Should print out:\n" + solution);
        System.out.println(answer);     
        
        solution = "5.0";
        answer = "" + evaluate("( (2.0 * 2.0) + 1.0)"); 
        System.out.println("\nTest 24:  Should print out:\n" + solution);
        System.out.println(answer);    
        solution = "20.0";
        answer = "" + evaluate("( 10.0 * (4.0 / 2.0))"); 
        System.out.println("\nTest 25:  Should print out:\n" + solution);
        System.out.println(answer);    
        
        solution = "12.0";
        answer = "" + evaluate("( (10.0 - 4.0) * 2.0)"); 
        System.out.println("\nTest 26:  Should print out:\n" + solution);
        System.out.println(answer);    
        
        solution = "15.0";
        answer = "" + evaluate("( (4 * 2.0) + 7)"); 
        System.out.println("\nTest 27:  Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "5.0";
        answer = "" + evaluate("((2.0 - 1.0) * ( 3.0 + 2.0 ))"); 
        System.out.println("\nTest 28:  Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "6.0";
        answer = "" + evaluate("((5.0 * 2.0) - ( 8.0 / 2.0 ))"); 
        System.out.println("\nTest 29:  Should print out:\n" + solution);
        System.out.println(answer);
        
        solution = "3.0";
        answer = "" + evaluate("((10.0 - 1.0) / ( 3.0 + 0.0 ))"); 
        System.out.println("\nTest 30:  Should print out:\n" + solution);
        System.out.println(answer); 
        
        // Just for fun (no credit or penalty)
        e = buildExpression("((((4.5234 * 23.241) - 23.242) - ((2.3 / 1.232) + 1.0)) / ( (3.0 + 0.0) * (23.432 / 8.23 )))");
        solution = "9.251310532747977";
        System.out.println("\nJFF:  Should print out:\n/(-(-(*(4.5234,23.241),23.242),+(/(2.3,1.232),1.0)),*(+(3.0,0.0),/(23.432,8.23)))");
        System.out.println(e);
        solution = "9.251310532747977";
        answer = "" + eval(e);
        System.out.println("\nJFF:  Should print out:\n" + solution);
        System.out.println(answer);
    }
}