/* 
 * File: HW03Client.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 09/28/2016
 * Purpose: This class is a client to test the BigInt and Set ADT libraries
 */

package HW03.B4;

import HW03.B2.BigInt;
import HW03.B3.Set;

public class HW03Client {
    
    public static void main(String[] args) {
        System.out.println("Testing the BigInt Library using client program....");
        
        int[] population = BigInt.stringToBigInt("7452483716"); // Create a BigInt for the world population from a String
        
        System.out.println("\nThe current population of the earth is:");
        System.out.println(BigInt.bigIntToString(population)); // Print the BigInt using the library function
        
        System.out.println("\nIf we double every 63 years, then in 2205 (after 3 doublings),");
        System.out.println("the population of the earth will be:");
        
        // Double the population 3 times using the BigInt.add() function
        int[] double1 = BigInt.add(population,population);
        int[] double2 = BigInt.add(double1,double1);
        int[] double3 = BigInt.add(double2,double2);
        
        System.out.println(BigInt.bigIntToString(double3)); // Print the BigInt using the library function
        
        System.out.println("\nTesting the Set ADT using client program....");
        System.out.println("\nUsing the Sieve of Eratosthenes to find prime numbers:");
        System.out.println("\nFirst we create a set consisting of the numbers 1..1000, then");
        System.out.println("for all numbers n = 2,...,500, for k > 1, remove");
        System.out.println("any multiples k*n <= 1000.");
        System.out.println("\nHere are the prime numbers between 1 and 1000:");
        
        Set primes = new Set(); // Create a new Set using the Set ADT
        for (int i = 1; i <= 1000; i++) { // Insert numbers [1..1000] into the Set
            primes.insert(i);
        }
        for (int i = 2; i <= 500; i++) { // Apply the Sieve of Eratosthenes to find prime numbers
            int k = 2;
            while (k * i <= 1000) {
                primes.delete(k * i);
                k++;
            }
        }
        
        System.out.println(primes); // Print the Set
    }
}