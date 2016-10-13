/* 
 * File: AverageCaseSorting.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 10/12/16
 * Purpose: This class compares the average number of comparisons for multiple sorting algorithms
 */

import java.awt.Color;
import java.util.Random; 

public class AverageCaseSorting {
    private static Random R = new Random();
    private static int counter = 0; 
    
    private static int[] generateRandomArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i < result.length; i++) {
            result[i] = R.nextInt(10000);
        }
        return result;
    }

    // swap A[i] and A[j]
    
    private static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    private static int runInsertion(int n) {
        counter = 0;
        insertionSort(generateRandomArray(n));
        return counter;
    }

    private static int runSelection(int n) {
        counter = 0;
        selectionSort(generateRandomArray(n));
        return counter;
    }

    private static int runMerge(int n) {
        counter = 0;
        mergeSort(generateRandomArray(n));
        return counter;
    }
    
// insertion sort from Algorithms, Sedgewick and Wayne, p.251
    
    private static void insertionSort(int[] A) {
        int N = A.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(A[j], A[j-1]); j--) {
                swap(A, j, j-1);
            }
        }
    } 
       
    // insertion sort from Algorithms, Sedgewick and Wayne, p.249
    
    private static void selectionSort( int[] A) {
        int N = A.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i+1; j < N; j++) {
                if (less(A[j], A[min])) min = j;
            }
            swap(A, i, min);
            
        }
        
    }
    
    // Mergesort from Sedgewick
    
    private static void mergeSort(int[] A) {
        int[] aux = new int[A.length];
        msHelper(A, aux, 0, A.length-1);
        
    }
    
    // stably merge A[lo .. mid] with A[mid+1 ..hi] using aux[lo .. hi]
    private static void merge(int[] A, int[] auxArray, int lo, int mid, int hi) {
         
        // copy to auxArray[]
        for (int k = lo; k <= hi; k++) {
            auxArray[k] = A[k]; 
        }
        
        // merge back to A[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                        A[k] = auxArray[j++];   // this copying is unnecessary
            else if (j > hi)                         A[k] = auxArray[i++];
            else if (less(auxArray[j], auxArray[i])) A[k] = auxArray[j++];
            else                                     A[k] = auxArray[i++];
        }    
    }
    
    // mergesort A[lo..hi] using auxiliary array auxArray[lo..hi]
    private static void msHelper(int[] A, int[] auxArray, int low, int hi) {
        if (hi <= low) return;
        int mid = low + (hi - low) / 2;
        msHelper(A, auxArray, low, mid);
        msHelper(A, auxArray, mid + 1, hi);
        merge(A, auxArray, low, mid, hi);
    }
     
    // just implements the comparison and counts the number
    
    private static boolean less(int v, int w) {
        counter++;
        return v < w; 
    }
    
    // uses StdDraw to open a window and draw a graph of a parabola; you must
    // use this as a basis for completing the problem. 

    private static void drawGraph() {
        int N = 100;
        
        double pointRadius = 0.005;
        double lineRadius = 0.001;
        
        StdDraw.setXscale(0, N);            // now scale of x axis is [0 .. 100]
        StdDraw.setYscale(0, 10*N);         // now scale of y axis is [0 .. 1000]
        StdDraw.setPenRadius(pointRadius);
        
        // Google "Java Color"; the first link gives all the colors
        StdDraw.setPenColor(Color.black);    
        StdDraw.line(0,0,0,10*N); 
        StdDraw.line(0,0,N,0);
        
        double prevX, prevY;
        
        StdDraw.setPenColor(Color.black);
        prevX = 0; prevY = 0; 
        for (int x = 1; x <= N; x++) {
            
            // here is where you put a call to a method
            // which returns the number of comparisons
            // for sorting an array of size i; right now it just
            // draws a parabola from the points (x, x^2) for x = 1, ...., N. 
            
            
            int y = x*x;                         // note that x and y are ints                     
            
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(x, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, x, y);
            prevX = x; prevY = y;
        }
        prevX = 0; prevY = 0; 
        for (int x = 1; x <= N; x++) {
            int y = (int) ((double) x * Math.log(x));                 
            
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(x, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, x, y);
            prevX = x; prevY = y;
        }
        StdDraw.setPenColor(Color.green);
        prevX = 0; prevY = 0; 
        for (int x = 1; x <= N; x++) {
            int y = runInsertion(x);                 
            
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(x, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, x, y);
            prevX = x; prevY = y;
        }
        StdDraw.setPenColor(Color.blue);
        prevX = 0; prevY = 0; 
        for (int x = 1; x <= N; x++) {
            int y = runSelection(x);                 
            
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(x, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, x, y);
            prevX = x; prevY = y;
        }
        StdDraw.setPenColor(Color.red);
        prevX = 0; prevY = 0; 
        for (int x = 1; x <= N; x++) {
            int y = runMerge(x);                 
            
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(x, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, x, y);
            prevX = x; prevY = y;
        }
            
    }
    
    // This main method just calls drawGraph
    
    public static void main(String[] args) {
        
        drawGraph(); 
    
    }
    
}