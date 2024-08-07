/**
 * This program takes an assignment name, students' names, and marks under 30. 
 * It then calculates the highest, lowest, mean, and standard deviation of the marks entered.
 *
 * @author Gimhan Lakshitha
 * @version (a version number or a date)
 */
import java.util.Scanner; // Import the Scanner class

class Student_Marks {
  public static void main(String[] args) {
    Scanner myObj = new Scanner(System.in); // Create a Scanner object
    String[] userNames = new String[30]; // Creating an array to store 30 student names
    float[] marks = new float[30]; // Create an array to store 30 marks as float

    System.out.println("Enter Assignment Name:");
    String assignmentName = myObj.nextLine(); // Storing assignment name

    // Loop to get 10 usernames
    for (int i = 0; i < 30; i++) {
      System.out.println("Enter Student Name " + (i + 1) + ":");
      userNames[i] = myObj.nextLine(); //Storing in a array
      boolean validInput = false; // Variable to check valid input
      while (!validInput) {
        System.out.println("Enter marks of " + userNames[i] + ":");
        String markInput = myObj.nextLine(); // Read user input as String
        try {
          float mark = Float.parseFloat(markInput); // converting to float

          if (mark >= 0 && mark <= 30) {
            marks[i] = mark;
            validInput = true; // Input is valid, exit the loop
          } else {
            System.out.println("Please enter marks that is below 30 and above 0");
          }
        } catch (NumberFormatException e) {
          System.out.println("The input is not a number! Please enter a correct number."); //validating non numeric inputs
        }
      }
    }
    // Close the Scanner
    myObj.close();

    //Displaying the assignment name
    System.out.println("\nAssignment Name : " + assignmentName);

    // Displaying all usernames and marks
    System.out.println("\nStudent Name and marks has been entered:");

    for (int i = 0; i < 30; i++) {
      System.out.println("Student Name " + (i + 1) + ": " + userNames[i]);
      System.out.println("marks " + (i + 1) + ": " + marks[i]);
    }

    // Getting highest and lowest mark
    float maxMark = MaxMark(marks);
    float minMark = MinMark(marks);

    // Print the highest and lowest marks
    System.out.println("\nHighest Mark: " + maxMark);
    System.out.println("Lowest Mark: " + minMark);

    //returning value from CalMean method
    double mean = CalMean(marks);
    //returning value from CalStandardDeviaton method 
    double standardDeviation = CalStandardDeviaton(marks, mean);
    // Print the mean value and standard deviation value
    System.out.println("\nMean: " + mean); // Print the mean
    System.out.println("Standard Deviation: " + standardDeviation); // Print the standard deviation

  }
  // Method for heighest mark
  public static float MaxMark(float[] marks) {
    float maxMark = marks[0]; // Assigning maxmark with the first value in the array
    for (int i = 1; i < marks.length; i++) { // Find the highest mark using for loop

      if (marks[i] > maxMark) {
        maxMark = marks[i];
      }
    }
    return maxMark;

  }
  // Method for minimum mark
  public static float MinMark(float[] marks) {
    float minMark = marks[0]; // Assigning minmark with the first value in the array
    for (int i = 1; i < marks.length; i++) { // Find the minimum mark using for loop

      if (marks[i] < minMark) {
        minMark = marks[i];
      }
    }
    return minMark;

  }

  // Method for mean
  public static double CalMean(float[] marks) {

    double total = 0; // Making total to 0

    // Add up all the marks
    for (int i = 0; i < marks.length; i++) {
      total += marks[i]; // Add each mark to the total variable
    }

    return total / marks.length; // Getting the mean by dividing total from the marks

  }

  // Method for standard deviation
  public static double CalStandardDeviaton(float[] marks, double mean) {
    double totalOfSquares = 0; // Starting with a assigning 0 as the first value 
    // Making a for loop to add up squared differences from the mean
    for (int i = 0; i < marks.length; i++) {
      double differenceFromMean = marks[i] - mean; // Calculate the difference from the mean
      totalOfSquares += differenceFromMean * differenceFromMean; // Adding squared difference to the variable
    }
    double varianceVal = totalOfSquares / marks.length; // Calculating variance
    return Math.sqrt(varianceVal); // Calculating standard deviation and retun
  }
}