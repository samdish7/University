package lab5_package;
import java.util.Scanner;

/*
 * Sam Disharoon
 * 2/22/21
 * 
 * Description
 * ==============
 * 
 * This program asks the user for 3 numbers,
 * then displays them in increasing order.
 */

public class Compare {

	public static void main(String[] args) {
		
		//initialize variables
		int num1, num2, num3, min = 0, mid = 0, max = 0;
		Scanner scan = new Scanner(System.in);
		
	
		
		//read in data
		System.out.printf("Enter 3 numbers\n");
		num1 = scan.nextInt();
		num2 = scan.nextInt();
		num3 = scan.nextInt();
		
		//get max
		if(num1 > num2 && num1 > num3) {
			max = num1;
		} else if(num2 > num1 && num2 > num3) {
			max = num2;
		} else {
			max = num3;
		} 

		//get min
		if(num1 < num2 && num1 < num3) {
			min = num1;
		} else if(num2 < num1 && num2 < num3) {
			min = num2;
		} else {
			min = num3;
		}
		
		//get mid; need more than just 1 and statement to make sure all edge cases are covered!
		if((num1 > num2 && num1 < num3) || (num1 < num2 && num1 > num3)) {
			mid = num1;
		} else if((num2 > num1 && num2 < num3) || (num2 < num1 && num2 > num3)) {
			mid = num2;
		} else {
			mid = num3;
		}
		
		//display to the user
		System.out.printf("Your numbers in order are as follows: %d %d %d\n", min, mid, max);
		
		scan.close();
	}

}
