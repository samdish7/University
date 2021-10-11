package lab3_pack;

/*
 * Sam Disharoon
 * 2/6/21
 * 
 * Description
 * =============
 * 
 * This program simulates rolling a pair of die 
 * using the Math.random function
 */

// main class where stuff happens
public class Dice {

	// main function
	public static void main(String[] args) {
		// initialize variables
		int die_1, die_2, total;
		
		// roll the dice!
		die_1 = (int)(Math.random()*6) + 1;
		die_2 = (int)(Math.random()*6) + 1;
		total = die_1 + die_2;
		
		// print out results using printf
		System.out.printf("Die 1 ~> %d\nDie 2 ~> %d\nTotal ~> %d\n",die_1, die_2, total); 
		//%d is how you tell printf to display your integers
		//\n is to create a new line

	}

}
