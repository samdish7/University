package default_package;
import java.util.Scanner;

/*
 * Sam Disharoon
 * 1/31/21
 * 
 * Description
 * ============
 * 
 * This program asks the user to tell how many nickels, dimes, and quarters
 * they have, and then displays the total
 */

//main class where stuff happens
public class Coin {
	
	//main function
	public static void main(String[] args) {
		//initialize variables
		int quarters, dimes, nickels, pennies;
		double total;
		
		//initialize input variable
		Scanner scan = new Scanner(System.in);
		System.out.printf("Nickels ~> ");
		
		//read input from user
		nickels = scan.nextInt();
		
		//repeat for dimes, quarters, and pennies
		System.out.printf("Dimes ~> ");
		dimes = scan.nextInt();
		System.out.printf("Quarters ~> ");
		quarters = scan.nextInt();
		System.out.printf("Pennies ~> ");
		pennies = scan.nextInt();
		
		//add up the total an display
		total = (nickels * 0.05) + (dimes * 0.10) + (quarters * 0.25) + (pennies * 0.01);
		System.out.printf("Total ~> $");
		System.out.printf("%.2f", total); //set the precision so it only displays 2 decimal places
		
		scan.close();//close buffer
	}

}
