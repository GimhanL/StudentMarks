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

        //Importing the text file
        String filePath = "prog5001_students_grade_2022.txt";

        readDataFromFile(filePath, names, stuID, totalMarks, marksCount); // Calling method to read the file

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
                if (number < 1 || number > 5) { // Checking if the input number 1 to 5 in the main menu
                    System.out.println("Invalid Number! Please enter a number between 1 and 5.");
                    continue;
                }


                switch (number) {
                    case 1:
                        AllStudentsAvg(names, stuID, totalMarks, marksCount); //Display all students
                        break;
                    case 2:
                        HighAndLowMarks(totalMarks, marksCount); //Display lowest and highest marks
                        break;
                    case 3:
                        MeanAndStandardDeviation(totalMarks, marksCount);
                        break;
                    case 4:
                        System.out.print("Please enter the mark threshold that you want to check : ");
                        float threshold = inputScanner.nextFloat(); //Psssing the threshold value to the variable 
                        StudentsThreshold(names, stuID, totalMarks, marksCount, threshold);
                        break;
                    case 5:
                        Top5Students(names, stuID, totalMarks, marksCount);
                        break;
                }
            } catch (InputMismatchException e) { //Checking if the input is integer
                System.out.println("Invalid input. Please enter a number.");
                inputScanner.next();
            }

        }
    }

    private static void readDataFromFile(String filePath, String[] names, String[] stuID, float[] totalMarks, int[] marksCount) {
        try {
            Scanner fileScanner = new Scanner(new File(filePath)); //Creating a Scanner object to read the file
            int index = 0; //Adding the index for storing data in arrays
            // Read the value and print the unit name
            while (fileScanner.hasNextLine()) {
            String name = fileScanner.nextLine().trim(); //Read the next line and remove any whitespace
             if (!name.isEmpty()) { //Adding a if condition to check if the line is not empty
                System.out.println(name);
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
                    String studentID = slots[0].trim(); //Assign StudentID data to slots array
                    String name = slots[1].trim() + " " + slots[0].trim(); //Adding last name and first name to the slots array
                    String a1 = slots[2].trim();
                    String a2 = slots[3].trim();
                    String a3 = slots[4].trim();

                    //Storing student ID
                    stuID[index] = studentID;
                    //Storing student name
                    names[index] = name;
                    //Making current student totalMarks mark as 0
                    totalMarks[index] = 0;
                    //Making current student marksCount mark as 0
                    marksCount[index] = 0;

                    //Creating a for loop to get marks values after 3rd array slot
                    for (int i = 3; i < slots.length; i++) {
                        try {
                            //Checking for whitspace
                            if (!slots[i].trim().isEmpty()) {
                                //Adding mark to total marks
                                totalMarks[index] += Float.parseFloat(slots[i].trim());
                                //Adding the marks for the student
                                marksCount[index]++;
                            }
                            //If the value isn't a number throws a error 
                        } catch (NumberFormatException e) {
                            System.out.println("Skipping invalid mark: " + slots[i]);
                        }
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
    //Display all student with average marks
    private static void AllStudentsAvg(String[] names, String[] stuID, float[] totalMarks, int[] marksCount) {
        System.out.println("\n_____________________Student list_____________________ ");
        for (int i = 0; i < names.length && names[i] != null; i++) { //Creating a loop to gets the list of student 
            float avgMark = marksCount[i] > 0 ? totalMarks[i] / marksCount[i] : 0; // Calculating average marks for all students
            System.out.println("Student Name: " + names[i] + ", Student ID: " + stuID[i] + ", Average Marks: " + avgMark);

        }

    }
    private static void HighAndLowMarks(float[] totalMarks, int[] marksCount) {

        float maxMark = Float.MIN_VALUE;//Assigning minimum mark value to the variable
        float minMark = Float.MAX_VALUE;//Assiging max mark value to the variable

        //Find the minimum mark and maximum mark using for loop
        for (int i = 0; i < totalMarks.length && totalMarks[i] != 0; i++) {
            float avgMark = marksCount[i] > 0 ? totalMarks[i] / marksCount[i] : 0; //if the mark count greater than 0 it will be true and  and dvide the marks
            if (avgMark > maxMark) {
                maxMark = avgMark; //If the average mark is higher it will update the max mark 

            }
            if (avgMark < minMark && avgMark > 0) {
                minMark = avgMark; //If the average mark is lower it will update the min mark 

            }

        }


        //Printing out the marks
        System.out.println("Average max mark: " + maxMark);
        System.out.println("Lowest Average Mark: " + minMark);
    }
    private static void MeanAndStandardDeviation(float[] totalMarks, int[] marksCount) {
        double mean = CalMean(totalMarks, marksCount); //Calling Calmean method and assigning it to the variable
        double standardDeviation = CalStandardDeviation(totalMarks, marksCount, mean); //Calling CalStandardDeviation method and assigning it to the variable

        System.out.println("\nMean: " + mean);
        System.out.println("Standard Deviation: " + standardDeviation);

    }

    private static double CalMean(float[] marks, int[] marksCount) {
        double total = 0; //Making total to 0
        int count = 0; //Making count 0
        for (int i = 0; i < marks.length && marks[i] != 0; i++) { //Creating a for loop
            if (marksCount[i] > 0) { //If any mark value is greater than 0 it will continue
                total += marks[i] / marksCount[i]; //getting average marks by dividing the total marks
                count++;
            }
        }
        return count > 0 ? total / count : 0; // returning mean if the count is greater than 0

    }
    private static double CalStandardDeviation(float[] marks, int[] marksCount, double mean) {
        double totalOfSquares = 0; //Making total to 0
        int count = 0; //Making count 0
        for (int i = 0; i < marks.length && marks[i] != 0; i++) { //Creating a for loop
            if (marksCount[i] > 0) { //If any mark value is greater than 0 it will continue
                //Calculating average marks from the current student
                double averageMark = marks[i] / marksCount[i]; 
                double differenceFromMean = averageMark - mean; //Calculating difference bitwwen average mark and the mean
                totalOfSquares += differenceFromMean * differenceFromMean; //Adding the diffference to the total of squares
                count++;
            }
        }
        double varianceVal = count > 0 ? totalOfSquares / count : 0; // Calculating variance
        return Math.sqrt(varianceVal); //Calculating standard deviation and return

    }

    private static void StudentsThreshold(String[] names, String[] stuID, float[] totalMarks, int[] marksCount, float threshold) {
        boolean markFound = false; //Using true and false method to check if the students meets the threshold mark
        for (int i = 0; i < names.length && names[i] != null; i++) { //Adding a for loop to get the student details
            if (names[i] != null && stuID[i] != null && marksCount[i] >= 0 && totalMarks[i] >= 0) { //Checking name and student ID are not null and mark count and mark total are not negative
            // Calculating the average marks for the current student
            float averageMark = marksCount[i] > 0 ? totalMarks[i] / marksCount[i] : 0; 
            //Using a if condition to check the average marks are below the threshold
            if (averageMark < threshold) {
                markFound = true;
                //Printing the marks
                System.out.println("Student Name: " + names[i] + ",ID: " + stuID[i] + ",Average Marks: " + averageMark);
            }

        }
        //If there is no student below the threshold it will show the error
       if (!markFound) {
        System.out.println("Sorry! There is no student below the threshold mark of  " + threshold);
      }
    }
}

    private static void Top5Students(String[] names, String[] stuID, float[] totalMarks, int[] marksCount) {


        //Creating an array to store student objects
        Student[] students = new Student[100];
        int count = 0; //Making count variable to 0

        for (int i = 0; i < names.length && names[i] != null; i++) { //Creating a for loop to get the student data to the student array 
            
            //Calculating the average mark to the current student 
            float avgMarks = marksCount[i] > 0 ? totalMarks[i] / marksCount[i] : 0; 
            students[count++] = new Student(names[i], stuID[i], avgMarks); //Creating a student object and assigning it to the array
        }
        //Sorting the students array by average marks in descending order
        bubbleSort(students, count);

        //Displaying the highest average marks fot top 5 students
        System.out.println("\nThe Top 5 Students with Highest Average Marks!");
        for (int i = 0; i < 5 && i < count; i++) { //For loop to get the five marks
            System.out.println("Student Name: " + students[i].name + ", Student ID: " + students[i].stuID + ", Average Marks: " + students[i].avgMarks);
        }
        //Displaying the lowest average marks fot top 5 students
        System.out.println("\nThe Top 5 Students with Lowest Average Marks:");
        for (int i = count - 1; i >= Math.max(0, count - 5); i--) { // starting out the loop with lowest average marks
            System.out.println("Student Name: " + students[i].name + ", Student ID: " + students[i].stuID + ", Average Marks: " + students[i].avgMarks);
        }

    }

    private static class Student { //Adding  inner class to store student details
        String name;
        String stuID;
        float avgMarks; //Declaring variables  

        //Declaring a constructor to create a student object
        Student(String name, String stuID, float avgMarks) {
            this.name = name; //Assigining the parameter to the student name
            this.stuID = stuID; //Assigining the parameter to the student ID
            this.avgMarks = avgMarks; //Assigining the parameter to the average marks
        }
    }


    private static void bubbleSort(Student[] students, int n) { //Sorting students based on the average marks using bubble sort
        //End recursion if only one element 
        if (n == 1) return;
        for (int i = 0; i < n - 1; i++) { //Creating a for loop to sort the data
            if (students[i].avgMarks < students[i + 1].avgMarks) { //Swap the value if the current value is less than the next value
                Student temp = students[i]; //Creating temporary variable to hold the current value of the student
                students[i] = students[i + 1]; //Swapping the stunt with the next student
                students[i + 1] = temp; //Assigning the temporary variable to the next student
            }
        }
        bubbleSort(students, n - 1); //Continue sorting the remaining values 
    }
}