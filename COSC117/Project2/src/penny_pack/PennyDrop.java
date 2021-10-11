package penny_pack;
import java.util.Scanner;
/*
 * Sam Disharoon
 * 3/27/21
 * 
 * Description
 * ===============
 * 
 * This program simulates the Penny Drop game by using various structures learned in class
 * There are a lot of variables used, so for clarity,
 * I will split them up based on when they will be needed
 */


public class PennyDrop {
	
	//printBoard function implementation
	public static void printBoard(int[] pennies, int[] box, int startIndex, int numPlayers) {
		int i;
		//display each slot #
		System.out.print(" ");//formatting purposes
		for(i = 0; i < 6; i++) {
			if(i == 5) {
				System.out.println(" Box  |");
			} else {
				System.out.printf(" S%d | ", i + 1);
			}
		}
	
		//display each slot's pennies
		for(i = 0; i < 6; i++) {
			if(i == 5) {
				System.out.printf("---%d---|", box[i]);
			} else {
				System.out.printf("--%d--|", box[i]);
			}
		}
		System.out.println("");
	
		//display the current player
		System.out.printf("-----------Player %d's turn------------\n",startIndex + 1);

		//display each player's pennies
		printPennies(numPlayers, pennies);
	
		System.out.println("========================");//display purposes
	}
	//printPenies function implementation
	public static void printPennies(int numPlayers, int[] pennies) {
		//display each player's pennies
		int i;
			for(i = 0; i < numPlayers; i++) {
				System.out.printf("Player %d ~> %d pennies\n", i + 1, pennies[i]);
			}
	}
	//printResult function implementation
	public static void printResult(int gameWinner, int numPlayers, int[] pennies) {
		//display winner
		System.out.printf("Congrats Player %d! You won!\nFinal tally of pennies:\n", gameWinner);
		printPennies(numPlayers, pennies);//display the final pennies result
	}
	//main function implementation
	public static void main(String[] args) {
		
		//Initialize first set of variables
		Scanner scan = new Scanner(System.in);
		int numPlayers;
		boolean valid, startAgain = true;
		String again;
		
		//begin game screen
		while(startAgain) {
			valid = false;
			numPlayers = 0;
			System.out.println("Welcome to the Penny Drop game!\nHow many players would you like? (2-6)");
		
			//check to see if input is valid
		
			while(!valid) {
				numPlayers = scan.nextInt();
				if(numPlayers > 6 || numPlayers < 2) {
					System.out.printf("%d is not a valid choice. Please choose from 2-6\n", numPlayers);
				} else {
					System.out.printf("Great! There are %d players in this game!\n", numPlayers);
				valid = true;
				}
			}//end while
			/*
			 * NOTE
			 * If a string/character is given, it will crash the program
			 * In order to avoid this, you could you use a try/catch
			 * and then catch the exception to avoid crashing.
			 * I will leave that to you to look into if you would like
			 */
			
			
			/* 
			 * now that the number of players has been chosen, declare arrays of that size for game
			 * and initialize next set of variables
			 * player 1 is index 0, player 2 index 1, etc...
			 */
			int[] pennies = new int[numPlayers];//number of pennies each player has
			int[] start = new int[numPlayers];//keep track of who rolled what
			int i = 0, max = 0, tieCount, dice = 0, rolls = 0, startIndex = 0;
			boolean noTie = false;//here to see if there is a tie or not
		
			//start each player with 12 pennies
			for(; i < numPlayers; i++) {
				pennies[i] = 12;
			}
		
			/* each player rolls to see whom goes first
			 * for time purposes, if there is a tie, all players will roll again
			 */
			while(!noTie) {
				rolls++;//book keeping
				tieCount = 0;//reset tie counter
				
				//each player roll the dice
				for(i = 0; i < numPlayers; i++) {
					dice = (int)(Math.random()*6 + 1);
					start[i] = dice;
					if(start[i] > max) {
						max = start[i];
						startIndex = i;
					}
				}
			
				//check to see if there is a tie, no tie means game can start
				for(i = 0; i < numPlayers; i++) {
					if(start[i] == max) {
						tieCount++;
					}
				}
				//if one player rolled the max, game will start, otherwise, repeat
				if(tieCount == 1) {
					System.out.printf("Player %d starts!\n", startIndex + 1);
					noTie = true;
				}
			}
			//see how many times the players had to roll, not needed, just wanted it
			System.out.println(rolls + " rolls were done");
		
			/*
			 * GAME STARTS HERE
			 * create more arrays/variables needed
			 */
			int[] box = new int[6];
			int gameWinner = 0;
			boolean winner = false, rollAgain;
			String input;
		
			//start each box with 0 pennies
			for(i = 0; i < 6; i++) {
				box[i] = 0;
			}
			
			//while loop to drive game
			while(!winner) {
				rollAgain = true;
				
				//display the board at the beginning of the turn
				printBoard(pennies, box, startIndex, numPlayers);
			
				//check to see if there is a winner
				for(i = 0; i < numPlayers; i++) {
					if(pennies[i] == 0) {
						gameWinner = i+1;
						winner = true;
						continue;//will break out of while loop
					}
				}
				System.out.println("");
			
				//this is the while loop to drive the rolling
				while(rollAgain) {
					//player rolls, decrease the player pennies by 1, increase box slot by one
					dice = (int)(Math.random()*6) + 1;
					pennies[startIndex]--;
					box[dice-1]++;
					System.out.println("=====================");
					System.out.printf("Player %d rolled a %d\n", startIndex + 1, dice);
					System.out.println("=====================");
				
					//check to see if there is a winner
					for(i = 0; i < numPlayers; i++) {
						if(pennies[i] == 0) {
							gameWinner = i+1;
							winner = true;
							continue;//will break out of while loop
						}
					}
					
						//check slots to see if player dropped a 2nd penny, if so, player takes all pennies in slots DO NOT CHECK TOTAL BOX
					if(box[dice-1] > 1 && (dice-1) != 5) {
						System.out.println("You must take all the pennies!");
					
						//add slot pennies to player pennies, and empty each slot
						for(i = 0; i < 5; i ++) {
							pennies[startIndex] += box[i];
							box[i] = 0;
						}
						
						if(startIndex == numPlayers - 1) {
							startIndex = 0;
						} else {
							startIndex++;
						}//end startIndex check if
					
						rollAgain = false;
						continue;
					}
				
					//display the board after roll
					printBoard(pennies, box, startIndex, numPlayers);
					
					//check to see if there is a winner
					for(i = 0; i < numPlayers; i++) {
						if(pennies[i] == 0) {
							gameWinner = i+1;
							winner = true;
							continue;//will break out of for loop
						}
					}
					if(winner) {
						rollAgain = false;
						continue;//break out of rollAgain while loop
					}
			
					//ask if player wants to roll again
					System.out.println("Would you like to roll again?");
					valid = false;
					while(!valid) {
						input = scan.next();
						if(input.equals("No") || input.equals("no")) {
							if(startIndex == numPlayers - 1) {
								startIndex = 0;//resets the index if you reach the highest number player, this avoids out of bounds errors!
							} else {
								startIndex++;
							}//end startIndex check if
							rollAgain = false;
							valid = true;
						} else if(input.equals("Yes") || input.equals("yes")) {
							System.out.printf("Player %d rolls again!\n", startIndex + 1);
							valid = true;
						} else {
							System.out.println("This is not a valid input\nWould you like to roll again? (Yes or No)");
						}
					}//end of input checking while loop
				
				}//end roll while loop
			
			}//end current game while loop
		
			//display winner
			printResult(gameWinner, numPlayers, pennies);
			System.out.println("Would you like to play again?");
			valid = false;
			while(!valid) {
				again = scan.next();//can't do next line or else it will skip the check and automatically restart!
				if(again.equals("No") || again.equals("no")) {
					startAgain = false;//will end the program
					valid = true;
				} else if(again.equals("Yes") || again.equals("yes")) {
					valid = true;
				} else {
					System.out.println("This is not a valid input\nWould you like to play again? (Yes or No)");
				}
			}//end of input checking while loop
		}//end of main while loop
		
		System.out.println("Thanks for playing!");
		//clean up memory usage!
		scan.close();
		System.gc();
	}
}
