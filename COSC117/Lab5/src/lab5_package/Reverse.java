package lab5_package;
import java.util.Scanner;

/*
 * Sam Disharoon
 * 2/22/21
 * 
 * Description
 * ==============
 * 
 * This program asks the user for 10 positive integers,
 * stores them in an array, and displays them in reverse order to the user,
 * as a bonus, the program will stop if you enter a negative integer or 0
 */

public class Reverse {

	public static void main(String[] args) {
		
		//initialize variables
		int arrSize, i, j;
		int[] arr = new int[10]; //initialized array
		boolean negative = false;
		Scanner scan = new Scanner(System.in);
		
		//get size of array; yes you could just set this to 10, but i did it this way
		//for display purposes
		arrSize = arr.length;
		
		//fancy way of saying while negative = false
		while(!negative) {
			System.out.println("Enter 10 positive integers:");
			for(i = 0; i < arrSize; i++) {
				arr[i] = scan.nextInt();
				if(arr[i] <= 0) {
					System.out.printf("%d is not a positive integer.\n", arr[i]);
					negative = true;//break loop if a negative int is given
					break;//need this to break the for loop
				}
			}
			//if all integers are positive, this will execute
			if(i >= arrSize) {
				System.out.println("No negative integers detected, your numbers in reverse order are as follows:");
				for(j = arrSize - 1; j >= 0; j--) {
					System.out.printf("%d ", arr[j]);
				}
				//this is to break the while loop
				negative = true;
			}
		}
		System.out.println("\n\nGoodbye!");
	
		scan.close();//close buffer
		System.gc();//free memory allocated by array
	}

}
