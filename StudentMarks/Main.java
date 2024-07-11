
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
        String[] userNames = new String[10];  // Creating an array to store 10 student names
        int[] marks = new int[10];  // Create an array to store 10 usernames

        System.out.println("Enter Assignment Name:");
        String assignmentName = myObj.nextLine();  // Storing assignment name

        // Loop to get 10 usernames
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter Student Name " + (i + 1) + ":");
            userNames[i] = myObj.nextLine();  //Storing in a array
             boolean validInput = false;  // Variable to check valid input
            while (!validInput) {
                System.out.println("Enter marks of " + userNames[i] + ":");
                String markInput = myObj.nextLine();  // Read user input as String
                try {
                   int mark = Integer.parseInt(markInput);  // converting to int
                     
                     if (mark >= 0 && mark <= 30) {  marks[i] = mark;
                        validInput = true;  // Input is valid, exit the loop
                    } else {
                        System.out.println("Invalid input. Marks must be between 0 and 30.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("This is not a number. Please enter a valid number.");
                }
            }
        }
        // Close the Scanner
        myObj.close();
        
        //Displaying the assignment name
        System.out.println("\nAssignment Name : " + assignmentName);
        
        // Displaying all usernames and marks
        System.out.println("\nStudent Name and marks has been entered:");
        
        for (int i = 0; i < 5; i++) {
            System.out.println("Student Name " + (i + 1) + ": " + userNames[i]);
             System.out.println("marks " + (i + 1) + ": " + marks[i]);
        }
        
        
        //Getting Highest and lowest mark
        int maxMark = marks[0];  // Checking the first mark
        int minMark = marks[0];  
        
        // Find the lowest mark  and highest mark using for loop
        for (int i = 1; i < marks.length; i++) {
            
            if (marks[i] < minMark) {  // If the current value is lower than the lowest value
                minMark = marks[i];    // Update the lowest mark
            }
            
            if (marks[i] > maxMark) { 
                maxMark = marks[i];    
            }
        }
        
        // Print the highest and lowest marks
        System.out.println("\nHighest Mark: " + maxMark);
        System.out.println("Lowest Mark: " + minMark);
    }

    
    
}


    