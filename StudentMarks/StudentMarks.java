
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
                    MeanAndStandardDeviation(totalMarks, marksCount);
                    break;
                case 4:
                    System.out.print("Please enter the mark threshold that you want to check : ");
                    float threshold = inputScanner.nextFloat(); //psssing the threshold value to the variable 
                    StudentsThreshold(names,stuID,totalMarks,marksCount,threshold); 
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
              
              //ignoring empty lines and header lines
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
  private static void MeanAndStandardDeviation(float[] totalMarks, int[] marksCount) {
      double mean = CalMean(totalMarks, marksCount);//Calling Calmean method and assigning it to the variable
      double standardDeviation = CalStandardDeviation(totalMarks, marksCount, mean);//Calling CalStandardDeviation method and assigning it to the variable

      System.out.println("\nMean: " + mean);
      System.out.println("Standard Deviation: " + standardDeviation);
 
    }
  
    private static double CalMean(float[] marks, int[] marksCount) {
         double total = 0; // Making total to 0
         int count = 0;// Making count 0
          for (int i = 0; i < marks.length && marks[i] != 0; i++) {//creating a for loop
              if (marksCount[i] > 0) {// if any mark value is greater than 0 it will continue
                    total += marks[i] / marksCount[i];//getting average marks by dividing the total marks
                    count++;
                }
            }
               return count > 0 ? total / count : 0;// returning mean if the count is greater than 0

}
private static double CalStandardDeviation(float[] marks, int[] marksCount, double mean) {
     double totalOfSquares = 0;// Making total to 0
     int count = 0;// Making count 0
      for (int i = 0; i < marks.length && marks[i] != 0; i++) {//creating a for loop
             if (marksCount[i] > 0) {// if any mark value is greater than 0 it will continue
                     double averageMark = marks[i] / marksCount[i];//calculating average marks from the current student
                     double differenceFromMean = averageMark - mean;//calculating difference bitwwen average mark and the mean
                     totalOfSquares += differenceFromMean * differenceFromMean;//Adding the diffference to the total of squares
                     count++;
                }
        }
        double varianceVal = count > 0 ? totalOfSquares / count : 0;// Calculating variance
        return Math.sqrt(varianceVal);// Calculating standard deviation and return

    }
    
    private static void StudentsThreshold(String[] names, String[] stuID, float[] totalMarks, int[] marksCount, float threshold) {
    for (int i = 0; i < names.length && names[i] != null; i++) {//adding a for loop to get the student details
        float averageMark = marksCount[i] > 0 ? totalMarks[i] / marksCount[i] : 0;  // Calculating the average marks for the current student
        // using a if condition to check  the average marks are below the threshold
        if (averageMark < threshold) {
             // Printing the marks
            System.out.println("Student Name: " + names[i] + ",ID: " + stuID[i] + ",Average Marks: " + averageMark);
        }
        
    }
    
    private static void Top5Students(String[] names,String[] stuID, float[] totalMarks,int[] marksCount) {
        
private static class Student {// adding  inner class to store student details
    String name;        
    String studentID;  
    float avgMarks; // declaring variables  

 // Declaring a constructor to create a student object
 Student(String name, String stuID, float avgMarks) {
              this.name = name;         //Assigining the parameter to the student name
    this.studentID = studentID;        //Assigining the parameter to the student ID
    this.avgMarks = avgMarks;  //Assigining the parameter to the average marks
        }
    
    //Creating an array to store student objects
    Student[] students = new Student[100];
    int count = 0;  //making count variable to 0
    
     for (int i = 0; i < names.length && names[i] != null; i++) {//Creating a for loop to get the student data to the student array 
        
         float avgMarks = marksCount[i] > 0 ? totalMarks[i] / marksCount[i] : 0;//Calculating the average mark to the current student
        students[count++] = new Student(names[i], studentIDs[i], avgMarks);//Creating a student object and assigning it to the array
        }
}

}

private static void bubbleSort(Student[] students, int n) {//sorting students based on the average marks using bubble sort
    // end recursion if only one element 
    if (n == 1) return;

    
    
}
}





