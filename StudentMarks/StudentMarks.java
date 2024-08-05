/**
 * Write a description of class StudentMarks here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class StudentMarks {
    public static void main(String[] args) {
        
        //Create a Scanner object for user input
        Scanner inputScanner = new Scanner(System.in);

        //Adding 100 arrays to each variable 
        String[] names = new String[100];
        String[] stuID = new String[100];
        float[] totalMarks = new float[100];
        int[] marksCount = new int[100];
        float[] assignment1Marks = new float[100];
        float[] assignment2Marks = new float[100];
        float[] assignment3Marks = new float[100];
        
        //Declaring variable for file path
        String filePath;
        //Creating a loop until the correct path has been entered
        while (true) {
            
        // Enter the file name
        System.out.print("Please enter the file name: ");
        filePath = inputScanner.nextLine().trim(); // Read and removing whitspace
        }


        //Importing the text file
        readDataFromFile(filePath, names, stuID, totalMarks, marksCount, assignment1Marks, assignment2Marks, assignment3Marks); // Calling method to read the file

        //Checking the text document values are showing in the text document
        /* for (int i = 0; i < names.length && names[i] != null; i++) {
             System.out.println("Student ID: " + stuID[i]);
             System.out.println("Student Name: " + names[i]);
             System.out.println("Total Marks: " + totalMarks[i]);
             System.out.println("Marks Count: " + marksCount[i]);
             System.out.println();
         }*/

        //Menu choices
        while (true) {
            menu(); // Displaying the menu 

            try {
                int number = inputScanner.nextInt(); //Getting the input value
                if (number < 1 || number > 3) { // Checking if the input number 1 to 5 in the main menu
                    System.out.println("Invalid Number! Please enter a number between 1 and 3");
                    continue;
                }


                switch (number) {
                    case 1:
                        AllStudents(names, stuID, totalMarks, marksCount, assignment1Marks, assignment2Marks, assignment3Marks); //Display all students
                        break;
                    case 2:    
                        System.out.print("Please enter the mark threshold that you want to check : ");
                        float threshold = inputScanner.nextFloat(); //Psssing the threshold value to the variable 
                        if(threshold >999){//Adding a if condition to allow only 3 digits numbers
                       System.out.println("Invalid input! Please enter 3 digit number ");
                        }else{
                             StudentsThreshold(names, stuID, totalMarks, marksCount, threshold);
                        }
                         break;
                    case 3:
                        Top5Students(names, stuID, totalMarks, marksCount);
                        break;
                }
            } catch (InputMismatchException e) { //Checking if the input is integer
                System.out.println("Invalid input. Please enter a number.");
                inputScanner.next();
            }

        }
    }

    private static void readDataFromFile(String filePath, String[] names, String[] stuID, float[] totalMarks, int[] marksCount, float[] assignment1Marks, float[] assignment2Marks, float[] assignment3Marks) {
        try {
            Scanner fileScanner = new Scanner(new File(filePath)); //Creating a Scanner object to read the file
            int index = 0; //Adding the index for storing data in arrays
            // Read the value and print the unit name
            while (fileScanner.hasNextLine()) {
                String unitName = fileScanner.nextLine().trim(); //Read the next line and remove any whitespace
                if (!unitName.isEmpty()) { //Adding a if condition to check if the line is not empty
                System.out.println("=======================================");
                System.out.println("    " + unitName + "   ");
                System.out.println("=======================================");
                    break; //After printing the unit name exit the loop
                }
            }
            //Creating while loop to read the text file lines
            while (fileScanner.hasNextLine() && index < 100) {
                String data = fileScanner.nextLine().trim(); //Read data in the line and removing whitespace

                //Ignoring empty lines and header lines
                if (data.isEmpty() || data.startsWith("Unit") || data.startsWith("Last Name")) {
                    continue;
                }
                //Adding commas to the data
                String[] slots = data.split(",");
                if (slots.length > 4) { //Validation if the array has more than 4 element it will coninue
                    String studentID = slots[2].trim(); //Assign StudentID data to slots array
                    String name = slots[1].trim() + " " + slots[0].trim(); //Adding last name and first name to the slots array

                    //Storing student ID
                    stuID[index] = studentID;
                    //Storing student name
                    names[index] = name;
                    //Making current student totalMarks mark as 0
                    totalMarks[index] = 0;
                    //Making current student marksCount mark as 0
                    marksCount[index] = 0;

                    try {
                        if (slots.length > 3 && !slots[3].trim().isEmpty()) { //Checking if array slots length is greater than 3 and the value is not empty
                            //Passing if there is value 
                            assignment1Marks[index] = !slots[3].trim().isEmpty() ? Float.parseFloat(slots[3].trim()) : 0;
                        } else {
                            assignment1Marks[index] = 0; //If the value is empty assign 0
                        }
                        //Adding assignemnt 1 mark to total marks
                        totalMarks[index] += assignment1Marks[index];
                        //Adding the marks
                        marksCount[index]++;

                    } catch (NumberFormatException e) {//Validate numeric values
                        System.out.println("Skipping invalid mark for assignment 1: " + slots[3]); //Displaying error message
                    }

                    try {
                        if (slots.length > 4 && !slots[4].trim().isEmpty()) { //Checking if array slots length is greater than 3 and the value is not empty
                            //Passing if there is value 
                            assignment2Marks[index] = !slots[4].trim().isEmpty() ? Float.parseFloat(slots[4].trim()) : 0;
                        } else {
                            assignment2Marks[index] = 0; //If the value is empty assign 0
                        }
                        //Adding assignemnt 1 mark to total marks
                        totalMarks[index] += assignment2Marks[index];
                        //Adding the marks
                        marksCount[index]++;
                    } catch (NumberFormatException e) {//Validate numeric values
                        System.out.println("Skipping invalid mark for assignment 2: " + slots[4]); //Displaying error message
                    }

                    try {
                        if (slots.length > 5 && !slots[5].trim().isEmpty()) { //Checking if array slots length is greater than 3 and the value is not empty
                            //Passing if there is value 
                            assignment3Marks[index] = !slots[5].trim().isEmpty() ? Float.parseFloat(slots[5].trim()) : 0;
                        } else {
                            assignment3Marks[index] = 0; //If the value is empty assign 0
                        }
                        //Adding assignemnt 1 mark to total marks
                        totalMarks[index] += assignment3Marks[index];
                        //Adding the marks
                        marksCount[index]++;

                    } catch (NumberFormatException e) {//Validate numeric values
                        System.out.println("Skipping invalid mark for assignment 3: " + slots[5]); //Displaying error message
                    }


                    index++;
                }
            }
            //Close the file scanner
            fileScanner.close();
        } catch (FileNotFoundException e) {
            //If the file is not detectable it will shows the error
            System.out.println("File not found!");
        } catch (Exception e) { //Handling genaral issues when reading the file

            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    //Method to display the menu
    private static void menu() {
        System.out.println("\n***************************************");
        System.out.println("                 Menu                ");
        System.out.println("***************************************");
        System.out.println();
        System.out.println("1.Display all students and their average grade");
        System.out.println("2.Display students that below the threshold");
        System.out.println("3.Display top 5 students with highest and lowest marks");
        System.out.println();
        System.out.print("Please write the number that you want to select? ");
    }
    //Display all student with average marks
    private static void AllStudents(String[] names, String[] stuID, float[] totalMarks, int[] marksCount, float[] assignment1Marks, float[] assignment2Marks, float[] assignment3Marks) {
        System.out.println("\n_____________________Student list_____________________ ");
        for (int i = 0; i < names.length && names[i] != null; i++) { //Creating a loop to gets the list of student 
            float avgMark = marksCount[i] > 0 ? totalMarks[i] / marksCount[i] : 0; //If the marks exists, divide the total marks by number of marks and if there is no mark the value set to 0
            System.out.println("Student Name: " + names[i] + ", Student ID: " + stuID[i] + ", Assignment 1 marks: " + assignment1Marks[i] + ", Assignment 2 marks: " + assignment2Marks[i] + ", Assignment 3 marks: " + assignment3Marks[i] +
                ", Total Marks: " + totalMarks[i] + ", Average Marks: " + avgMark);

        }

    }


    private static void StudentsThreshold(String[] names, String[] stuID, float[] totalMarks, int[] marksCount, float threshold) {
        boolean markFound = false; //Using true and false method to check if the students meets the threshold mark
        for (int i = 0; i < names.length && names[i] != null; i++) { //Adding a for loop to get the student details
            if (names[i] != null && stuID[i] != null && marksCount[i] >= 0 && totalMarks[i] >= 0) { //Checking name and student ID are not null and mark count and mark total are not negative
                //Using a if condition to check the total marks are below the threshold
                if (totalMarks[i] < threshold) {
                    markFound = true;
                    //Printing the marks
                    System.out.println("Student Name: " + names[i] + ",ID: " + stuID[i] + ",Total Marks: " + totalMarks[i]);
                }

            }
        }
        //If there is no student below the threshold it will show the error
        if (!markFound) {
            System.out.println("Sorry! There is no student below the threshold mark of  " + threshold);
        }
    }

    private static void Top5Students(String[] names, String[] stuID, float[] totalMarks, int[] marksCount) {


        //Creating an array to store student objects
        Student[] students = new Student[100];
        int count = 0; //Making count variable to 0

        for (int i = 0; i < names.length && names[i] != null; i++) { //Creating a for loop to get the student data to the student array 


            students[count++] = new Student(names[i], stuID[i], totalMarks[i]); //Creating a student object and assigning it to the array
        }
        //Sorting the students array by average marks in descending order
        bubbleSort(students, count);

        //Displaying the highest total marks fot top 5 students
        System.out.println("\nThe Top 5 Students with Highest Total Marks!");
        for (int i = 0; i < 5 && i < count; i++) { //For loop to get the five marks
            System.out.println("Student Name: " + students[i].name + ", Student ID: " + students[i].stuID + ", Total Marks: " + students[i].totalMarks);
        }
        //Displaying the lowest average marks fot top 5 students
        System.out.println("\nThe Top 5 Students with Lowest Average Marks:");
        for (int i = count - 1; i >= Math.max(0, count - 5); i--) { // starting out the loop with lowest average marks
            System.out.println("Student Name: " + students[i].name + ", Student ID: " + students[i].stuID + ", Average Marks: " + students[i].totalMarks);
        }

    }

    private static class Student { //Adding  inner class to store student details
        private String name;
        private String stuID;
        private float totalMarks; //Declaring variables and adding access control to variables  

        //Declaring a constructor to create a student object
        Student(String name, String stuID, float totalMarks) {
            this.name = name; //Assigining the parameter to the student name
            this.stuID = stuID; //Assigining the parameter to the student ID
            this.totalMarks = totalMarks; //Assigining the parameter to the total marks
        }
    }


    private static void bubbleSort(Student[] students, int n) { //Sorting students based on the average marks using bubble sort
        //End recursion if only one element 
        if (n == 1) return;
        for (int i = 0; i < n - 1; i++) { //Creating a for loop to sort the data
            if (students[i].totalMarks < students[i + 1].totalMarks) { //Swap the value if the current value is less than the next value
                Student temp = students[i]; //Creating temporary variable to hold the current value of the student
                students[i] = students[i + 1]; //Swapping the stunt with the next student
                students[i + 1] = temp; //Assigning the temporary variable to the next student
            }
        }
        bubbleSort(students, n - 1); //Continue sorting the remaining values 
    }
}