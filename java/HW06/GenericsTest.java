/* 
 * File: GenericsTest.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 10/15/16
 * Purpose: This class tests the Queue.java class with StringStacks as items
 */

public class GenericsTest {
    
    public static void main(String[] args) {
        System.out.println("Test: Should print out:\nYou'd have to be looney to run for President! So Trump is a looney!");  
        
        // Create a StringStack S and push on it (in order):  "looney!", "a", "is", "Trump", "So"  (so that "So" is on top at the end)
        StringStack S = new StringStack(); 
        S.push("looney!");
        S.push("a");
        S.push("is");
        S.push("Trump");
        S.push("So");
        
        // Create a StringStack T and push on it:  "President!", "for", "run", "to"
        StringStack T = new StringStack(); 
        T.push("President!");
        T.push("for");
        T.push("run");
        T.push("to");
        
        // Create a StringStack U and push on it:  "looney", "be", "to", "have", "You'd"
        StringStack U = new StringStack(); 
        U.push("looney");
        U.push("be");
        U.push("to");
        U.push("have");
        U.push("You'd");
        
        // Now enqueue S, T, and U on a suitably-defined queue Q (using your solution Queue.java,
        // and so that S is at the front and U is at the rear).
        Queue<StringStack> Q = new Queue<StringStack>();
        Q.enqueue(U);
        Q.enqueue(T);
        Q.enqueue(S);
        
        // Nothing to do below this line, this will print out each of the stacks in turn
        
        while(!Q.isEmpty()) {
            StringStack V = Q.dequeue();
            
            while(!V.isEmpty()) {
                System.out.print(V.pop() + " ");
            }
        } 
        
        System.out.println();       
    }
}