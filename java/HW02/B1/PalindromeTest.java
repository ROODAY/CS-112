/* 
 * File: PalindromeTest.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 09/15/2016
 * Purpose: This class tests if user input is a palindrome
 */

package HW02.B1;
import java.util.Scanner;

public class PalindromeTest { 
    public static void main(String[] args) {
        System.out.println("\nWelcome to the Palindrome Test Program!");

        Scanner userInput = new Scanner(System.in);

        System.out.println("\nType in a sentence or Control-d to end:"); 

        while(userInput.hasNextLine()) {
            String line = userInput.nextLine();
            line = line.toLowerCase();

            char[] charsToRemove = { '.', ',', ':', ';', '!', '?', '"', '\'', '/', '-', '(', ')', '~'};
            for (int i = 0; i < charsToRemove.length; i++) {
                line = line.replace(Character.toString(charsToRemove[i]), "");
            }

            String cleanedLine = "";

            for (int i = 0; i < line.length(); i++) {
                char a = line.charAt(i);
                if (Character.isWhitespace(a) == false) {
                    cleanedLine += Character.toString(a);
                }
            }

            boolean isPalindrome = true;

            for (int i = 0; i < cleanedLine.length(); i++) {
                if (!(Character.toString(cleanedLine.charAt(i)).equals(Character.toString(cleanedLine.charAt(cleanedLine.length() - 1 - i))))) {
                    isPalindrome = false;
                }
            }
            
            if (isPalindrome) {
                System.out.println("Palindrome!");
            } else {
                System.out.println("Not a palindrome!");
            }
        }
        System.out.println("bye!");
    }
}