
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;  // Import the Scanner class

class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String[] userNames = new String[10];  // Create an array to store 10 usernames
        String[] marks = new String[10];  // Create an array to store 10 usernames


        // Loop to get 10 usernames
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter Student Name " + (i + 1) + ":");
            userNames[i] = myObj.nextLine();  // Read user input and store it in the array
             System.out.println("Enter marks of " +(i + 1)  + " student " + userNames[i]+ ":" );
            marks[i] = myObj.nextLine();  // Read user input and store it in the array
        }

        // Close the Scanner
        myObj.close();

        // Print all usernames and marks
        System.out.println("\nStudent Name and marks has been entered:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Student Name " + (i + 1) + ": " + userNames[i]);
             System.out.println("marks " + (i + 1) + ": " + marks[i]);
        }
    }
}


    
