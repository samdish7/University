package lab11_pack;
import java.util.Scanner;

/*
 * 4/21/21
 * Sam Disharoon
 * 
 * Description
 * ================
 * 
 * This program further implements the class instance in Java.
 * Rather than using the default ctor, we will implement the non default ctor
 * by simulating an addition quiz generator.
 * 
 * There are two classes, MainClass and AdditionQuestion. The MainClass drives the program,
 * the AdditionQuestion class will hold the values, and check if the given input is correct or not,
 * then will display the quiz results after all 10 questions have been asked. 
 */

public class MainClass {

	public static void main(String[] args) {
		//initialize variables
		Scanner scan = new Scanner(System.in);
		int i = 0, input, answer, percent = 0;
		char p = '%';//because of the way printf works, I sent a char to % to make displaying easier
		
		//initialize array of AdditionQuestion objects
		AdditionQuestion[] quiz = new AdditionQuestion[10];
		
		//create objects in the array, pass random values, ask question, and tally right/wrong answers
		//yes, I am doing everything in one for loop to save space and time :)
		for(; i < 10; i++) {
			quiz[i] = new AdditionQuestion();//create AdditionQuestion object at index i
			quiz[i].question();//display question
			input = scan.nextInt();//get user input
			answer = quiz[i].check();//check to see if the answer is right
			if(input == answer) {
				System.out.println("Correct!");
				percent += 10;
			} else {
				System.out.printf("Incorrect, the correct answer is %d\n", answer);
			}
			
		}
		//display score
		System.out.printf("Your score: %d%c\n", percent, p);
		
		//clean up memory usage!
		scan.close();
		System.gc();
		
	}

}
