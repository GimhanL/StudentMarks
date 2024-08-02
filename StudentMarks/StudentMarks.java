
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
        String[] stuID = new String[100];
        float[] totalMarks = new float[100];
        int[] marksCount = new int[100];
        
    // Importing the text file
        String filePath = "prog5001_students_grade_2022.txt";
        
        readDataFromFile(filePath, names, stuID, totalMarks, marksCount); // Calling method to read the file
        
        //checking the text document values are showing in the text document
       /* for (int i = 0; i < names.length && names[i] != null; i++) {
            System.out.println("Student ID: " + stuID[i]);
            System.out.println("Student Name: " + names[i]);
            System.out.println("Total Marks: " + totalMarks[i]);
            System.out.println("Marks Count: " + marksCount[i]);
            System.out.println();
        }*/
        // Menu choices
        while (true) {
            menu(); // Displaying the menu 
            int number = inputScanner.nextInt(); // getting the input value

            switch (number) {
                case 1:
                    AllStudentsAvg(names, stuID, totalMarks, marksCount); // Display all students
                    break;
                case 2:
                    HighAndLowMarks(totalMarks,marksCount);// Display lowest and highest marks
                    break;
                case 3:
                   System.out.println("Menu choice 3"); 
                    break;
                case 4:
                    System.out.print("Menu choice 4");
                    break;
                case 5:
                   System.out.println("Menu choice 5"); 
                    break;
                default:
                    System.out.println("Invalid choice."); 
            }
        }
    }
    
    private static void readDataFromFile(String filePath, String[] names, String[] stuID, float[] totalMarks, int[] marksCount)
    {
    try{
         Scanner fileScanner = new Scanner(new File(filePath)); // Creating a Scanner object to read the file
         int index = 0; // Initialize the index for storing data in arrays
         // Creating while loop to read the text file lines
          while (fileScanner.hasNextLine() && index < 100) { 
              String data = fileScanner.nextLine().trim(); // Read data in the line and removing whitespace
              
              if (data.isEmpty() || data.startsWith("Unit") || data.startsWith("Last Name")) {
                    continue;
                }
              // Adding commas to the data
              String[] slots =  data.split(",");
              if (slots.length > 4) {//Validation if the array has more than 3 element it will coninue
                  String studentID = slots[0].trim();//assign StudentID data to slots array
                  String name = slots[1].trim() + " " + slots[0].trim();//adding last name and first name to the slots array
                  String a1 = slots[2].trim();
                  String a2 = slots[3].trim();
                  String a3 = slots[4].trim();
               
                
                
                  //Storing student ID
                  stuID[index] = studentID;
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
        
    // Method to display the menu
    private static void menu() {
        System.out.println("\n_____________________Menu_____________________");
        System.out.println();
        System.out.println("1.Display all students and their average grade");
        System.out.println("2.Display highest and lowest marks");
        System.out.println("3.Display mean and standard deviation");
        System.out.println("4.Display students that below the threshold");
        System.out.println("5.Display top 5 students with highest and lowest average marks");
        System.out.println();
        System.out.print("Please write the number that you want to select? ");
    }
      // display all student with average marks
    private static void AllStudentsAvg(String[] names,String[] stuID,float[] totalMarks,int[] marksCount) {
        System.out.println("\n_____________________Student list_____________________ ");
        for (int i = 0; i < names.length && names[i] != null; i++) { //Creating a loop to gets the list of student 
            float avgMark = marksCount[i] > 0 ? totalMarks[i] / marksCount[i] : 0;// Calculating average marks for all students
            System.out.println("Student Name: " + names[i] + ", Student ID: " + stuID[i] + ", Average Marks: " + avgMark);

        }
        
    }    
    private static void HighAndLowMarks(float[] totalMarks,int[] marksCount) {
        
    float maxMark = Float.MIN_VALUE; // Assigning minimum mark value to the variable
    float minMark = Float.MAX_VALUE; // Assiging max mark value to the variable

    // Find the minimum mark and maximum mark using for loop
    for (int i = 0; i < totalMarks.length && totalMarks[i] != 0; i++) {
        float avgMark = marksCount[i] > 0 ? totalMarks[i] / marksCount[i] : 0; //if the mark count greater than 0 it will be true and  and dvide the marks
         if (avgMark > maxMark) {
            maxMark = avgMark; // if the average mark is higher it will update the max mark 
         
        }
        if (avgMark < minMark && avgMark > 0) {
            minMark = avgMark; //if the average mark is lower it will update the min mark 
           
        }
             
        }

    
   //Printing out the marks
    System.out.println("Average max mark: " + maxMark);
    System.out.println("Lowest Average Mark: " + minMark);
}  

}

    

