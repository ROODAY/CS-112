/* 
 * File: WordPalindromeTest.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 09/20/2016
 * Purpose: This class tests if user input is a palindrome by words
 */

import java.util.Scanner;
import java.util.Arrays;

public class WordPalindromeTest { 
    public static void main(String[] args) {
        System.out.println("\nWelcome to the Word Palindrome Test Program!");
        
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("\nType in a sentence or Control-d to end:"); 
        
        while(userInput.hasNextLine()) {
            String line = userInput.nextLine();
            line = line.toLowerCase();
            
            char[] charsToRemove = { '.', ',', ':', ';', '!', '?', '"', '\'', '/', '-', '(', ')', '~'};
            for (int i = 0; i < charsToRemove.length; i++) {
                line = line.replace(Character.toString(charsToRemove[i]), "");
            }
            
            String[] words = line.split("\\s+");
            
            boolean isPalindrome = true;
            int max = words.length - 1;
            
            for (int i = 0; i < words.length; i++) {
                if (!(words[i].equals(words[max - i]))) {
                    isPalindrome = false;
                }
            }
            
            System.out.println(Arrays.toString(words));
            if (isPalindrome) {
                System.out.println("Word Palindrome!");
            } else {
                System.out.println("Not a word palindrome!");
            }
        }
        System.out.println("bye!");
    }
}