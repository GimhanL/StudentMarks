
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;  // Import the Scanner class
public class Main
{
  
  public static void main(String[] args) {
    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    System.out.println("Enter Student Marks");

    String marks = myObj.nextLine();  // Read user input
    System.out.println("Marks is: " + marks);  // Output user input
  }
}

    
