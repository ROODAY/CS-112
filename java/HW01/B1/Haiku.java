/*
 * File: Haiku.java
 * Date: 09/08/2016
 * Author:  Rudhra Raveendran (rooday@bu.edu)
 * Purpose: This class prints out a haiku, using two variables for the author and year and only one println statement
 */

package B1;

public class Haiku {
    public static void main(String[] args) {
        String author = "Jack Kerouac";
        int year = 1968;
        System.out.println("\t Arms folded \n\t To the moon \n\t Among the cows. \n\n\t\t -" + author + " (" + year + ")");
    }
}
