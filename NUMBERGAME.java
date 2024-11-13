package TACK1;
import java.util.Scanner;
public class NUMBERGAME {
public static void main(String[] args) {
int number = (int)(Math.random() * 100) + 1; 
Scanner scanner = new Scanner(System.in);
int userGuess = 0;
  System.out.println("random number between 1 and 100have been generated. Can you guess it");
	       while (userGuess != number) {
	            System.out.print("Enter your guess ");
	            userGuess = scanner.nextInt();

	            if (userGuess < number) {
	                System.out.println("Too low! Try again.");
	            } else if (userGuess > number) {
	                System.out.println("Too high! Try again.");
	            } else {
	                System.out.println("You've guessed the number: " + number);
	            }
	        }
	    }
	}


