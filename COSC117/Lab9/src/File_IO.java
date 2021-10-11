import java.io.File;//need for file I/O
import java.io.PrintWriter;//need for file I/O

/*
 * Sam Disharoon
 * 4/1/21
 * 
 * Description
 * ==================
 * 
 * This program simulates the card game, High-Low.
 * There will be 4 functions in this program;
 * --- Main ---
 * --- getFace ---
 * --- getSuit ---
 * --- getWinner ---
 * 
 * These will drive the program and will be explained/implemented before the main function
 * Because there is just one deck, there cannot be any ties, 
 * there will be a catch to prevent that from happening
 * 
 * Rules of High-Low
 * ==================
 * 
 * Both players draw a card from a single regular deck,
 * the player with the higher number wins,
 * if numbers are the same, the higher suit wins, the hierarchy is as follows:
 * Spades > Diamonds > Clubs > Hearts
 * 
 * THIS PROGRAM DIRECTS ALL OUTPUT INTO THE FILE output.txt
 * The way this happens is through the PrintWriter function,
 * in order for the output of the subroutines (non-main functions) to be captured,
 * you must pass the PrintWriter object through each function that has output
 * i.e. getWinner
 */

public class File_IO {
	
	/* getFace function definition
	 * This function uses the Math.random function to produce a value for the card drawn
	 */
	public static int getFace(){ 
		int value = (int)(Math.random()*13) + 1;//13 card values, so *13
		return value;
	}

	/* getSuit function definition
	 * This function also uses the Math.random function,
	 * but instead of returning a value, it will return a suit
	 */
	public static String getSuit() {
		int suit_num = (int)(Math.random()*4) + 1; //4 suits, so *4
		String suit;
		
		switch(suit_num) {
			case 1:
				suit = "Spade";
				break;
			case 2:
				suit = "Diamond";
				break;
			case 3:
				suit = "Club";
				break;
			case 4:
				suit = "Heart";
				break;
			default:
				suit = "";
				break;
		}
		
		return suit;
	}
	
	/* getWinner function definition
	 * This function determines the winner of the game;
	 * it will return either true or false,
	 * true if player 1 wins, false if player 2 wins
	 * 
	 * In the rare case they both draw the same card, 
	 * the function will draw a different card for player two,
	 * this process will continue until both players have different cards
	 * 
	 * The cards of each player will NOT be shown until the function
	 * confirms the cards are not identical
	 */
	public static boolean getWinner(int f1, String s1, int f2, String s2, PrintWriter write) {
		
		boolean same = true;
		String face1 = Integer.toString(f1);
		String face2 = Integer.toString(f2);
		
		//this checks to see if both players drew the same card
		while(same) {
			if(f1 == f2) {
				if(s1.equals(s2)) {
					//if this is called, p2 will redraw
					write.println("CAUGHT!");//this is to test to see if it will redraw for same cards
					write.printf("f1: %d s1: %s f2: %d s2: %s \n", f1, s1, f2, s2);
					
					//redraw for player 2
					f2 = getFace();
					s2 = getSuit();
				} else {
					same = false;
				}
			} else {
				same = false;
			}
		}
		
		//rename face1 to proper facecard if needed
				switch(f1) {
					case 11:
						face1 = "Jack";
						break;
					case 12:
						face1 = "Queen";
						break;
					case 13:
						face1 = "King";
						break;
					case 14:
						face1 = "Ace";
						break;
					default:
						break;
				}
				
				//rename face2 to proper facecard if needed
				switch(f2) {
					case 11:
						face2 = "Jack";
						break;
					case 12:
						face2 = "Queen";
						break;
					case 13:
						face2 = "King";
						break;
					case 14:
						face2 = "Ace";
						break;
					default:
						break;
				}
		
		//display cards
		write.printf("=================================\nPlayer 1 ~> %s of %ss\n", face1, s1);
		write.printf("Player 2 ~> %s of %ss\n=================================\n", face2, s2);
		/* 
		 * once the cards are confirmed to be different, 
		 * then compare to see who won this hand,
		 * true = player 1
		 * false = player 2
		 */
		
		//if p1's number is higher, p1 wins
		if(f1 > f2) {
			return true;	
		}
		//if the numbers are the same, compare suits
		else if(f1  == f2) {
			if(s1.equals("Spade")) {
				return true;
			} else if(s1.equals("Diamond") && s2.equals("Heart") || s2.equals("Club")) {
				return true;
			} else if(s1.equals("Club") && s2.equals("Heart")) {
				return true;
			}
		}
		
		//ONLY CALLED ONCE ALL IF STATEMENTS ARE CHECKED (p1 doesn't win)
		return false;
		
	}
	
	public static void main(String[] args) {
	
		String p1Value, p2Value, p1Suit, p2Suit;
		int p1Num, p2Num, p1Wins = 0, p2Wins = 0;
		
		//file handling
		PrintWriter wr;
		try {
			wr = new PrintWriter(new File("output.txt"));//create a file named output.txt
		
			//run the game until a player reaches 7
			while(p1Wins < 7 && p2Wins < 7) {
		
				p1Num = getFace();
				p2Num = getFace();
		
				p1Suit = getSuit();
				p2Suit = getSuit();
		
				if(getWinner(p1Num, p1Suit, p2Num, p2Suit, wr)) {
					wr.println("Player 1 Wins!");
					p1Wins++;
				} else {
					wr.println("Player 2 Wins!");
					p2Wins++;
				}
			
			
			
			}
		
			wr.printf("\n============\nFinal Score\n============\nPlayer 1 ~> %d\nPlayer 2 ~> %d\n", p1Wins, p2Wins);
			wr.close();//need this to close file!
		} catch(Exception e){
			System.out.println("ERROR!");
		}

	}

}
