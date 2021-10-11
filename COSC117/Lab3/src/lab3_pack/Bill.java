package lab3_pack;
import java.util.Scanner;

/*
 * Sam Disharoon
 * 1/31/21
 * 
 * Description
 * ============
 * 
 * This program takes a price (perhaps from a restaurant)
 * and calculates the tip, tax, and total after the 
 * price of the food is given
 */

//main function where stuff happens
public class Bill {

	//main function
	public static void main(String[] args) {
		
		//initialize variables
		Scanner scan = new Scanner(System.in);
		double price, tax, tip, total;
		double tax_constant = 0.06, tip_constant = 0.15;//given in lab
		/*
		 * This is perfectly legal as long as you separate
		 * each variable using commas
		 * could also do it like this
		 * 
		 * double price;
		 * double tax;
		 * double tip;
		 * double total;
		 */
		
		//get price of the food
		System.out.printf("How much was the food?\n");
		price = scan.nextDouble();
		
		//calculations
		//tax
		tax = price * tax_constant;
		
		//tip
		tip = price * tip_constant;
		
		//total
		total = price + tip + tax;
		
		//display; %f is how you tell printf to display doubles or floats
		//.2 is how many places after the decimal you want to display
		System.out.printf("Bill ~> $%.2f\nTax ~> $%.2f\nTip ~> $%.2f\nTotal ~> $%.2f\n", price, tax, tip, total);
		scan.close();
	}

}
