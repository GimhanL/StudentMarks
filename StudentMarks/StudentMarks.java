
/**
 * Write a description of class StudentMarks here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class StudentMarks
{
    public static void main(String[] args) {
      // Create a Scanner object for user input
        Scanner inputScanner = new Scanner(System.in);   
        
        // Adding 100 arrays to each variable 
        String[] names = new String[100];
        String[] studentIDs = new String[100];
        float[] totalMarks = new float[100];
        int[] marksCount = new int[100];
        
    // Importing the text file
        String filePath = "prog5001_students_grade_2022.txt";
        
        readDataFromFile(filePath, names, studentIDs, totalMarks, marksCount); // Calling method to read the file
        
    private static void readDataFromFile(String filePath, String[] names, String[] studentIDs, float[] totalMarks, int[] marksCount)
    {
    try{
         Scanner fileScanner = new Scanner(new File(filePath)); // Creating a Scanner object to read the file
         int index = 0; // Initialize the index for storing data in arrays
         // Creating while loop to read the text file lines
          while (fileScanner.hasNextLine() && index < 100) { 
              String Data = fileScanner.nextLine().trim(); // Read data in the line and removing whitespace
              // Adding commas to the data
              String[] slots =  Data.split(",");
              if (slots.length > 3) {//Validation if the array has more than 3 element it will coninue
                  String studentID = slots[0].trim();//assign StudentID data to slots array
                  String name = slots[1].trim() + " " + slots[0].trim();//adding last name and first name to the slots array
                  String a1 = parts[2].trim();
                  String a2 = parts[3].trim();
                  String a3 = parts[4].trim();
                  
                 // Checking marks has any text
                 if (!a1.isEmpty() || !a2.isEmpty() || !a3.isEmpty()) {
                     continue;
                
                }
                
             
            }
        }
        } catch (NumberFormatException e) {
            // Handle invalid marks
            System.out.println("Skipping invalid mark: " + parts[i]);
        }
    }
  
}
             
        
 
    
    

