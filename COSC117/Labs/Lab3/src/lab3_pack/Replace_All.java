package lab3_pack;
import java.util.Scanner;

/*
 * Sam Disharoon
 * 2/6/21
 * 
 * Description
 * =================
 * 
 * This program demostrates various methods to using the 
 * String.name.replaceAll function
 */

//main function where stuff happens
public class Replace_All {

	//main function
	public static void main(String[] args) {
		/*
		 * PART 1
		 * ============
		 * 
		 * demonstrating how the 
		 * replaceAll function works
		 */
		
		//initialize variables
		String str1 = "Yo, my name is Sam", replace = "Hi, my name is Sam"; 
		/* this is perfectly legal as long as you separate using commas
		 * could also do it like this
		 * String str1 = "Yo, my name is Sam";
		 * String str2 = "Hi, my name is Sam";
		 */
		
		//%s tells printf to display the string variable passed
		System.out.printf("Original String ~> %s\n", str1);
		//print out replaced string
		System.out.println("Replaced String ~> " + str1.replaceAll(str1, replace) + "\n\n\n");

		/*
		 * PART 2
		 * ============
		 * 
		 * using scanner to allow the user to replace a certain
		 * string with another
		 */
		
		//initialize variables
		String input1, input2, input3;
		Scanner scan = new Scanner(System.in);
		
		//ask for input
		System.out.printf("Input a long string\n");
		input1 = scan.nextLine();
		
		System.out.printf("Input what you want to replace\n");
		input2 = scan.nextLine();
		
		System.out.printf("Input what you want to replace it with\n");
		input3 = scan.nextLine();
		
		//replace it and display new string
		System.out.printf("%s\n", input1.replaceAll(input2,input3));
		
		//close buffer
		scan.close();
	}
}
