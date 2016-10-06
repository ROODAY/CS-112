/*
 * File: PrintDay.java
 * Date: 09/08/2016
 * Author:  Rudhra Raveendran (rooday@bu.edu)
 * Purpose: This class prints out the day of the week that Unix was born
 */

public class PrintDay {
    public static void main(String[] args) {
        int day = 1;
        int month = 1;
        int year = 1970;

        int y0 = year - (14 - month) / 12;
        int x = y0 + y0/4 - y0/100 + y0/400;
        int m0 =  month + 12 * ((14 - month) / 12) - 2;
        int d0 = (day + x + 31 * m0 / 12) % 7;

        String[] dayOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        System.out.println("Unix\'s birthday was on a " + dayOfWeek[d0]); 
    }
}