package Task2;
import java.util.Scanner;
public class STUDENTGRADECALCULATOR {
 

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        
	        Student student = new Student();  // Create a Student object
	        student.inputMarks(scanner);      // Input marks using the Student's method
	        student.calculateTotal();         // Calculate total using the Student's method
	        student.calculateAverage();       // Calculate average using the Student's method
	        student.assignGrade();            // Assign grade using the Student's method
	        student.displayResults();         // Display results using the Student's method
	        
	        scanner.close();
	    }
	}

	class Student {
	    private double[] marks;
	    private double totalMarks;
	    private double averagePercentage;
	    private char grade;

	    public void inputMarks(Scanner scanner) {
	        System.out.print("Enter the number of subjects: ");
	        int numSubjects = scanner.nextInt();
	        marks = new double[numSubjects];
	        
	        for (int i = 0; i < numSubjects; i++) {
	            System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
	            marks[i] = scanner.nextDouble();
	        }
	    }

	    public void calculateTotal() {
	        totalMarks = 0;
	        for (double mark : marks) {
	            totalMarks += mark;
	        }
	    }

	    public void calculateAverage() {
	        averagePercentage = totalMarks / marks.length;
	    }

	    public void assignGrade() {
	        if (averagePercentage >= 90) {
	            grade = 'A';
	        } else if (averagePercentage >= 80) {
	            grade = 'B';
	        } else if (averagePercentage >= 70) {
	            grade = 'C';
	        } else if (averagePercentage >= 60) {
	            grade = 'D';
	        } else {
	            grade = 'F';
	        }
	    }

	    public void displayResults() {
	        System.out.println("\nResults:");
	        System.out.printf("Total Marks: %.2f\n", totalMarks);
	        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
	        System.out.println("Grade: " + grade);
	    }
	}

