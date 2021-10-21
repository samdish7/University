package lab4_package;
import java.util.Scanner;//needed for user input

/*
 * Sam Disharoon
 * 2/12/21
 * 
 * Description
 * =============
 * 
 * This program demonstrates a car dealership's payroll
 * Given the name, length of employment, and how much the employee has sold
 * it will tell how much their commission rate is as well as 
 * their total salary
 */

//main class where stuff happens
public class Salary {
	//main function where stuff happens
	public static void main(String[] args) {
		
		//initialize variables
		double comm, sales, salary;
		int years;
		String fName, lName;
		Scanner scan = new Scanner(System.in);//for user input
		
		//read in names and length of employment
		System.out.printf("What is your first name?\n");
		fName = scan.nextLine();
		System.out.printf("What is your last name?\n");
		lName = scan.nextLine();
		System.out.printf("How many years have you worked here?\n");
		years = scan.nextInt();
		System.out.printf("What were your sales?\n");
		sales = scan.nextDouble();
		
		//create if statement to calculate commission
		//&& means and, is a way to do more than one calculation in an if statement
		if(sales <= 15000) {
			comm = 0.10;//10%
		}
		else if(sales >= 15001 && sales <= 60000) {
			comm = 0.15;//15%
		}
		else {
			comm = 0.33;//33%
		}
		
		//calculate salary bases on years of employment
		if(years >= 5) {
			salary = sales * comm;
			salary += 10000;//+= is short for adding to the value, so this means salary = 10000 + salary
		}
		else {
			salary = sales * comm;
		}
		
		//display pretty output
		System.out.printf("\n===========================\n");
		System.out.printf("First name   ~>\t%s\n", fName);
		System.out.printf("Last name    ~>\t%s\n", lName);
		System.out.printf("Total Salary ~>\t$%.2f", salary);
		System.out.printf("\n===========================\n");

		//close buffer
		scan.close();

	}

}
