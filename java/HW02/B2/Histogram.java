/* 
 * File: Histogram.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 09/20/2016
 * Purpose: Creates a histogram based on user input of numbers
 * Credits: Used example 2 from top answer of https://stackoverflow.com/questions/3059333/validating-input-using-java-util-scanner to terminate input loop when user types 'done'
 */

package HW02.B2;
import java.util.Scanner;
import java.util.Arrays;

public class Histogram {

	public static final int MAX_NUMBERS = 20;
    public static final int NUM_BINS    = 10;
    public static final int BIN_SIZE    = 100 / NUM_BINS;

    public static void printHeading() {
    	System.out.println("\nWelcome to the Histogram Program!");
    	System.out.println("This program will print out a histogram of the numbers you input.");
    	System.out.println("\nType in up to 20 numbers in range [0..100]. Type 'done' when finished. " + MAX_NUMBERS + " number(s) left.");
    }

    public static boolean validateInput(double num) {
		if (num < 0.0 || num > 100.0) {
			return false;
		} else {
			return true;
		}
    }

    public static void printHistogram(int[] histogram) {
    	System.out.println("Histogram of Values in Decades from 0 to 100:");
		for (double i = 10.0, j = 0; i <= 100.0; i += 10.0, j++) {
			if ((int) j > 0) {
				String count = "";
				for (int k = 0; k < histogram[(int) j]; k++) {
					count += "*";
				}
				System.out.println("(" + (i - 10.0) + ".." + i + "]: " + "\t" + count);
			} else {
				String count = "";
				for (int k = 0; k < histogram[(int) j]; k++) {
					count += "*";
				}
				System.out.println("[" + (i - 10.0) + ".." + i + "]: " + "\t" + count);
			}
		}
    }

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);

		printHeading();

		double[] numbers = new double[MAX_NUMBERS];
		int[] histogram = new int[NUM_BINS];
		int currentInput = 0;

		while(!(userInput.hasNext("done")) && currentInput < MAX_NUMBERS) {
			if (!(userInput.hasNextDouble())) {
				System.out.println("That is not a valid input!");
				System.out.println("\nType in another number in range [0..100]. Type 'done' when finished. " + (MAX_NUMBERS - currentInput) + " number(s) left.");
				userInput.next();
			} else {
				double num = userInput.nextDouble();
				if (!validateInput(num)) {
					System.out.println("Inputs must be a double in range [0..100], try again!");
					System.out.println("\nType in another number in range [0..100]. Type 'done' when finished. " + (MAX_NUMBERS - currentInput) + " number(s) left.");
				} else {
					numbers[currentInput] = num;
					currentInput++;
					if (currentInput != MAX_NUMBERS) {
						System.out.println("\nType in another number in range [0..100]. Type 'done' when finished. " + (MAX_NUMBERS - currentInput) + " number(s) left.");
					}
					
					for (double i = 10.0, j = 0; i <= 100.0; i += 10.0, j++) {
						if (num <= i) {
							histogram[(int) j]++;
							break;
						}
					}
				}
				if (currentInput == MAX_NUMBERS) {
					System.out.println("Maximum number of inputs reached, proceeding to calculate histogram.");
					break;
				}
			}
		}

		double[] cleanArray = new double[currentInput];
		for (int i = 0; i < currentInput; i++) {
			cleanArray[i] = numbers[i];
		}

		System.out.println("You input " + currentInput + " numbers: " + Arrays.toString(cleanArray));
		printHistogram(histogram);
	}
}