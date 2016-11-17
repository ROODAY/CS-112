/* 
 * File: Student.java
 * Author: Rudhra Raveendran (rooday@bu.edu)
 * Date: 11/15/2016
 * Purpose: This class implements a comparable Student
 */

public class Student implements Comparable<Student> {
    
    private int score;
    private String firstName;
    private String lastName;
    
    public Student(String firstName, String lastName, int score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
    }
    
    public String toString() {
        return "(" + lastName + "," + firstName + "," + score + ")";
    }
    
    public int compareTo(Student s) {
        if (this.lastName.compareTo(s.lastName) < 0) {
            return -1;
        } else if (this.lastName.compareTo(s.lastName) > 0) {
            return 1;
        } else {
            if (this.firstName.compareTo(s.firstName) < 0) {
                return -1;
            } else if (this.firstName.compareTo(s.firstName) > 0) {
                return 1;
            }
        }
        return 0;
    }   
}