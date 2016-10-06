/*
 * File: Statistics.java
 * Date: 09/13/2016
 * Author:  Rudhra Raveendran (rooday@bu.edu)
 * Purpose: This class calculates various statistics of three numbers from user input
 */
 
import java.util.Scanner;

public class Statistics {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("\nType in the first number and then hit return:"); 
        int numOne = userInput.nextInt();
        System.out.println("\nType in the second number and then hit return:"); 
        int numTwo = userInput.nextInt();
        System.out.println("\nType in the third number and then hit return:"); 
        int numThree = userInput.nextInt();

        int numSum = numOne + numTwo + numThree;
        int numMax = Math.max(Math.max(numOne, numTwo), numThree);
        int numMin = Math.min(Math.min(numOne, numTwo), numThree);
        int numRange = numMax - numMin;
        double numMean = numSum / 3.0;
        double standardDeviation = Math.sqrt((Math.pow(numOne - numMean, 2) + Math.pow(numTwo - numMean, 2) + Math.pow(numThree - numMean, 2)) / 3.0);
        int numMid = numOne + numTwo + numThree - numMax - numMin;

        System.out.printf("The sum is %d\n", numSum);
        System.out.printf("The max is %d\n", numMax);
        System.out.printf("The range is %d\n", numRange);
        System.out.printf("The mean is %.4f\n", numMean);
        System.out.printf("The standard deviation is %.4f\n", standardDeviation);
        System.out.printf("The three numbers in order are: %d %d %d\n", numMin, numMid, numMax);
    }
}