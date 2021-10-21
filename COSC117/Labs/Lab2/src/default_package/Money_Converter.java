package default_package;
import java.util.Scanner; //import for user input

/*
 * Sam Disharoon
 * 1/31/21
 * 
 * Description
 * ============
 * 
 * This program converts US Dollars to Euros based on the current value
 * of each dollar
 */

//main class where stuff happens
public class Money_Converter {

	//main function
	public static void main(String[] args) {
		//initialize variables for dollar and euro
		double dollars, euros; 
		
		//initialize scanner variable for input
		Scanner scan = new Scanner(System.in);
		System.out.println("Dollars:");
		
		//reads in a "double" value from user
		dollars = scan.nextDouble(); 
		
		//do calculations
		euros = dollars * 0.83; 
		
		//print result
		System.out.println("Euros ~> " + euros); 
		
		scan.close();//close buffer

	}

}
