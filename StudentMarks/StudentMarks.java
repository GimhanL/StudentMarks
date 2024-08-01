
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
        
    }catch(NumberFormatException e){
        
    }
}

    }
    
}
