package project1_package;
import java.util.Scanner;//user input
import java.util.concurrent.TimeUnit;//for time second delay

/*
 * Sam Disharoon
 * 2/19/21
 * 
 * Description
 * =============
 * 
 * This program demonstrates many of the concepts learned over the past 5 labs
 * such concepts include variables, user input, calculations, arrays, and 
 * displaying in a legible format. 
 * 
 * The user is asked a series of questions such as their
 * first & last names, and their grades on a series of exams/projects.
 * Once all info has been read in, ask if there is an attendance policy or not.
 * The grade (% and letter) for the class will be displayed once all info has been provided 
 * along with first and last name
 */

//main class where stuff happens
public class Grade {

	//main function where stuff happens
	public static void main(String[] args) {
		
		//initialize variables
		String fName, lName, attendIn;
		double finalGrade, finalExam, midtermAve, projectAve, labAve, total = 0;
		int arrSize,i=0;
		char letterGrade = 'X';
		boolean attend = false;
		Scanner scan = new Scanner(System.in);
		
		//initialize arrays
		double projects[], midterms[], labs[];
		
		//allocated memory for arrays
		projects = new double[3];//3 projects
		midterms = new double[2];//2 midterms
		labs = new double[10];//10 labs
		
		//read in user input
		System.out.printf("Welcome to the grade calculator for Ms. Gerhold's COSC 117\n<><><><><><><><><><><><><><><><><><><><><><><><><><><><>\nWhat is your first name?\n");
		fName = scan.nextLine();
		System.out.printf("What is your last name?\n");
		lName = scan.nextLine();
		System.out.printf("\n<><><><><><><><><><><><><><><><><><><><><><><><><><><><>\nHello %s %s! Now we have to input your grades.\n<><><><><><><><><><><><><><><><><><><><><><><><><><><><>\n", fName, lName);
		
		/*
		 * This section is where the calculations happen
		 * the user inputs their grades into the corresponding array,
		 * the average is found, and the cycle repeats for each array
		 * total and arrSize are reused numerous times, this is legal 
		 * and a good way to save space
		 */
		
		//get size of projects array and calculate project average grade
		arrSize = projects.length;
		for(;i < arrSize; i++) {
			System.out.printf("What did you get on project %d?\n", i+1);
			projects[i] = scan.nextDouble();
			total += projects[i];
		}
		projectAve = total / arrSize;
		System.out.printf("\n<><><><><><><><><><><><><><><><><><><><><><><><><><><><>\nGreat! Now for labs\n<><><><><><><><><><><><><><><><><><><><><><><><><><><><>\n");
		total = 0;//reset total to 0
		
		//get size of labs array and calculate lab average grade
		arrSize = labs.length;
		for(i=0; i < arrSize; i++) {
			System.out.printf("What did you get on lab %d?\n", i+1);
			labs[i] = scan.nextDouble();
			total += labs[i];
		}
		labAve = total / arrSize;
		System.out.printf("\n<><><><><><><><><><><><><><><><><><><><><><><><><><><><>\nGreat! Now for exams\n<><><><><><><><><><><><><><><><><><><><><><><><><><><><>\n");
		total = 0;//reset total to 0
		
		//get size of exams array and calculate exam average grade
		arrSize = midterms.length;
		for(i=0; i < arrSize; i++) {
			System.out.printf("What did you get on midterm %d?\n", i+1);
			midterms[i] = scan.nextDouble();
			total += midterms[i];
		}
		midtermAve = total / arrSize;
		System.out.printf("\n<><><><><><><><><><><><><><><><><><><><><><><><><><><><>\nGreat! What did you get on your final?\n<><><><><><><><><><><><><><><><><><><><><><><><><><><><>\n");
		
		//get final exam score
		finalExam = scan.nextDouble();
		
		//ask about attendance policy
		System.out.printf("Did you show up to class regularly? y/n\n");
		attendIn = scan.next();
		
		if(attendIn == "Y" || attendIn == "y") {
			attend = true;
		}
		
		System.out.printf("\n<><><><><><><><><><><><><><><><><><><><><><><><><><><><>\nOk, calculating your total now...\n<><><><><><><><><><><><><><><><><><><><><><><><><><><><>\n");
		//add a few second delay for suspense :)
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
		finalGrade = (projectAve * .30) + (midtermAve * .30) + (labAve * .20) + (finalExam * .25);
		if(attend == false) {
			finalGrade -= 5;
		}
		
		//calculate letter grade
		//if finalGrade is above 100, end program
		if(finalGrade > 100) {
			System.out.printf("Invalid Grade\n");
			System.exit(0);
		} else if(finalGrade <= 100 && finalGrade >= 90) {
			letterGrade = 'A';
		} else if(finalGrade <= 89 && finalGrade >= 80) {
			letterGrade = 'B';
		} else if(finalGrade <= 79 && finalGrade >= 70) {
			letterGrade = 'C';
		} else if (finalGrade <= 69 && finalGrade >= 60) {
			letterGrade = 'D';
		} else {
			letterGrade = 'F';
		}
		
		
		
		//display to the user
		System.out.printf("\nGrade Report:\n~~~~~~~~~~~~~~~~~\n");
		System.out.printf("Name: %s %s\n", fName, lName);
		System.out.printf("Grade Letter: %c\n", letterGrade);
		System.out.printf("Grade Average: %.2f\n", finalGrade);
		
		//include so you don't leak memory!
		System.gc();
		scan.close();
	
	}

}
