package project3_pack;
import java.util.Scanner;

public class MainClass {

	public static void displayBoard(ConnectFourBoard board) {
		//use nested for loops to create each object in your 2-D array and display first board
		int i, k;
		//to display, need to start at the "last" row
		for(i = 5; i > -1; i--) {
			for(k = 0; k < 7; k++) {
				System.out.printf("|%s", board.getSquare(i, k));
			}
			System.out.println("|");
		}
		
		for(i = 0; i < 7; i++) {
			System.out.print("==");
		}
		System.out.println("=");
		for(i = 0; i < 7; i++) {
			System.out.printf("|%d", i+1);
		}
		System.out.println("|");
	}
	
	
	public static void main(String[] args) {
		//initialize variables, yes there are a lot
		Scanner scan = new Scanner(System.in);
		int i, k, input = 0, player = 1, p1Wins = 0, p2Wins = 0;
		boolean playAgain = true, winner = false, valid, crash;
		
		while(playAgain) {
			System.out.println("Welcome to Connect Four!");
			
			//create board object
			ConnectFourBoard board = new ConnectFourBoard();
			int capacity;
	
			//start game
			while(!winner) {
				crash = true;
				
				//display starting board
				displayBoard(board);
				
				System.out.printf("Player %d goes! What column do you want?\n", player);
				
				//this is to check if the input given is a valid column
				while(crash) {
					input = scan.nextInt();
					//check to see if column exists
					if(input > 7 || input < 1) {
						System.out.printf("There is no column %d, pick 1-7 please:\n", input);
					} else {
						//check if the column is full; input-1 for index purposes!
						if(board.getnumChips(input-1) == 6) {
							System.out.printf("Column %d is full! Pick different one\n", input);
						} else {
							crash = false;
						}
					}
					
				}//end crash while loop
				
				//insert chip
				if(player == 1) {
					board.insert("R", input);
				} else {
					board.insert("Y", input);
				}
				
			
				//check to see if winner
				if(board.checkWinner()) {
					winner = true;
					System.out.printf("Player %d wins!\n", player);
					displayBoard(board);
				}
				
				//shift to other player
				if(player == 2) {
					player--;
				} else {
					player++;
				}
			}//end game while loop
		
			playAgain = false;
			
		}//end playAgain while loop
		
	}

}
