/* File: Student.java
 * Date: 11/10/2016
 * Author:  Wayne Snyder (waysnyder@gmail.com)
 * Class: CS 112, Fall 2016
 * Purpose: This is the template for HW 10, Problem B.1 (lab problem)
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

