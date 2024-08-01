
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
        
        //checking the text document values are showing in the text document
        for (int i = 0; i < names.length && names[i] != null; i++) {
            System.out.println("Student ID: " + studentIDs[i]);
            System.out.println("Student Name: " + names[i]);
            System.out.println("Total Marks: " + totalMarks[i]);
            System.out.println("Marks Count: " + marksCount[i]);
            System.out.println();
        }
    }
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
              if (slots.length > 4) {//Validation if the array has more than 3 element it will coninue
                  String studentID = slots[0].trim();//assign StudentID data to slots array
                  String name = slots[1].trim() + " " + slots[0].trim();//adding last name and first name to the slots array
                  String a1 = slots[2].trim();
                  String a2 = slots[3].trim();
                  String a3 = slots[4].trim();
                  
                 // Checking marks has any text
                 if (a1.isEmpty() && a2.isEmpty() && a3.isEmpty()) {
                     continue;
                
                }
                  //Storing student ID
                  studentIDs[index] = studentID;
                  // Storing student name
                  names[index] = name;
                  //making current student totalMarks mark as 0
                  totalMarks[index] = 0;
                  //making current student marksCount mark as 0
                  marksCount[index] = 0;

                
                   //Creating a for loop to get marks values after 3rd array slot
                   for (int i = 3; i < slots.length; i++) {
                        try {
                            //checking for whitspace
                            if (!slots[i].trim().isEmpty()) {
                                // Adding mark to total marks
                                totalMarks[index] += Float.parseFloat(slots[i].trim());
                                // Adding the marks for the student
                                marksCount[index]++;
                            }
                            //if the value isn't a number throws a error 
                        }catch (NumberFormatException e) {
                            System.out.println("Skipping invalid mark: " + slots[i]);
                        }
                    }
                        
                        index++;
                    }
                }
                // Close the file scanner
                fileScanner.close();
            }catch(FileNotFoundException e){
                // if the file is not detectable it will shows the error
                System.out.println("File not found!");
            }
        }
}

    

