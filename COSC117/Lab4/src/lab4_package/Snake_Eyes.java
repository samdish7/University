package lab4_package;
import java.util.concurrent.TimeUnit;//for time second delay
/*
 * Sam Disharoon
 * 2/12/21
 * 
 * Description
 * ===============
 * 
 * This program simulates rolling two dice and
 * records how many rolls it takes until snake-eyes
 * are rolled.  Will display messages for all dice rolls
 */

//main class where stuff happens
public class Snake_Eyes {

	//main function where stuff happens
	public static void main(String[] args) {
		
		//initialize variables
		int die_1, die_2, total, counter = 0;
		boolean snake_eyes = false; //this is what will control the while loop
		
		while(!snake_eyes) {
			
			//this is to add a 1 second delay for each roll
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
			
			die_1 = (int)(Math.random()*6)+1;
			die_2 = (int)(Math.random()*6)+1;
			total = die_1 + die_2;
			counter++;
			System.out.printf("Die 1 ~> %d\n", die_1);
			System.out.printf("Die 2 ~> %d\n", die_2);
			System.out.printf("Total ~> %d\n", total);
			if(total == 12) {
				System.out.printf("\n========\nBoxCars!\n========\n\n");
			} else if(total == 11) {
				System.out.printf("\n=========\nYO-leven!\n=========\n\n");
			} else if(total == 2) {
				snake_eyes = true;
			}
			else {
				System.out.printf("\nBoring\n\n");
			}

		}
		System.out.printf("\n=================================\nYou got snake-eyes in %d rolls!!\n=================================\n", counter);
	}

}
