package project3_pack;

public class ConnectFourBoard {
	private String[][] squares;//game board
	private int[] numChips;//number of chips in each column
	
	//default ctor creates 2-d array which is the "board"
	public ConnectFourBoard() {
		int i = 0, k;
		numChips = new int[7];
		squares = new String[6][7];
		//set each square to default
		for(; i < 6; i++) {
			for(k = 0; k < 7; k++) {
				squares[i][k] = " ";
			}
		}
		//set number of chips in each col to 0
		for(i = 0; i < 7; i++) {
			numChips[i] = 0;
		}
	}
	//return square color, for display
	public String getSquare(int row, int col) {
		return this.squares[row][col];
	}
	//insert chip function; this is called AFTER checking to see if column is full
	public void insert(String c, int col) {
		int counter = 0;
		col--;//decrease col by one for index purposes
		//get the number of chips in the column
		counter = getnumChips(col);
		
		//insert chip into column and increment numChips counter
		squares[counter][col] = c;
		numChips[col]++;
	}
	//check if there is a winner, i put all 3 winner functions into one large function for space purposes
	public boolean checkWinner() {
		int i, k, j, count = 0;
		
		//check rows
		for(i = 0; i < 6; i++) {
			count = 0;//set count to 0 at the start of the row
			//check 4 different win conditions
			if(squares[i][0] == squares[i][1] && squares[i][1] == squares[i][2] && squares[i][2] == squares[i][3] && squares[i][0] != " ") {
				count = 4;
			} else if(squares[i][1] == squares[i][2] && squares[i][2] == squares[i][3] && squares[i][3] == squares[i][4] && squares[i][1] != " "){
				count = 4;
			} else if(squares[i][2] == squares[i][3] && squares[i][3] == squares[i][4] && squares[i][4] == squares[i][5] && squares[i][2] != " ") {
				count = 4;
			} else if(squares[i][3] == squares[i][4] && squares[i][4] == squares[i][5] && squares[i][5] == squares[i][6] && squares[i][3] != " ") {
				count = 4;
			} else {
				count = -1;
			}
			//returns true if someone wins
			if(count == 4) {
				return true;
			}
		}
		
		//check columns
		for(i = 0; i < 7; i++) {
			count = 0;//set count to 0 at the start of the row
			//check 3 different win conditions
			if(squares[0][i] == squares[1][i] && squares[1][i] == squares[2][i] && squares[2][i] == squares[3][i] && squares[0][i] != " ") {
				count = 4;
			} else if(squares[1][i] == squares[2][i] && squares[2][i] == squares[3][i] && squares[3][i] == squares[4][i] && squares[1][i] != " "){
				count = 4;
			} else if(squares[2][i] == squares[3][i] && squares[3][i] == squares[4][i] && squares[4][i] == squares[5][i] && squares[2][i] != " ") {
				count = 4;
			}  else {
				count = -1;
			}
			//returns true if someone wins
			if(count == 4) {
				return true;
			}
		}
		
		/*
		 * Check diagonals section
		 * this is a little more in depth
		 * and each part will be explained.
		 * This can also be condensed quite a bit, but I wrote more code 
		 * for clarity purposes and to show each of the 10 diagonals needed to be checked
		 */
		
		/*//check 2 of the 4 main diagonals, starts at [0][0] and [0][6], respectively
		for(i = 0, k = 6; i < 3; i++, k--) {
			count = 0;//set count to 0 at the start of the row
			if(squares[i][i] == squares[i+1][i+1] && squares[i+1][i+1] == squares[i+2][i+2] && squares[i+2][i+2] == squares[i+3][i+3] && squares[i][i] != " ") {
				count = 4;
			} else if(squares[i][k] == squares[i+1][k-1] && squares[i+1][k-1] == squares[i+2][k-2] && squares[i+2][k-2] == squares[i+3][k-3] && squares[i][k] != " ") {
				count = 4;
			} else {
				count = -1;
			}
			//returns true if someone wins
			if(count == 4) {
				return true;
			}
		}*/
		
		//check diagonals
		for(j = 0; j < 3; j++) {
			for(i = 1, k = 5; i < 4; i++, k--) {
				count = 0;//set count to 0 at the start of the row
				if(squares[j][i] == squares[j+1][i+1] && squares[j+1][i+1] == squares[j+2][i+2] && squares[j+2][i+2] == squares[j+3][i+3] && squares[j][i] != " ") {
					count = 4;
				} else if(squares[j][k] == squares[j+1][k-1] && squares[j+1][k-1] == squares[j+2][k-2] && squares[j+2][k-2] == squares[j+3][k-3] && squares[j][k] != " ") {
					count = 4;
				} else {
					count = -1;
				}
				//returns true if someone wins
				if(count == 4) {
					return true;
				}
			}
		}
		
	/*	//check diagonals starting at [0][2] & 
		for(j = 0, i = 1, k = 5; i < 4; i++, k--, j++) {
			count = 0;//set count to 0 at the start of the row
			if(squares[j][i] == squares[j+1][i+1] && squares[j+1][i+1] == squares[j+2][i+2] && squares[j+2][i+2] == squares[j+3][i+3] && squares[j][i] != " ") {
				count = 4;
			} else if(squares[j][k] == squares[j+1][k-1] && squares[j+1][k-1] == squares[j+2][k-2] && squares[j+2][k-2] == squares[j+3][k-3] && squares[j][k] != " ") {
				count = 4;
			} else {
				count = -1;
			}
			//returns true if someone wins
			if(count == 4) {
				return true;
			}
		}
		
		//check diagonals starting at 
		for(j = 0, i = 1, k = 5; i < 4; i++, k--, j++) {
			count = 0;//set count to 0 at the start of the row
			if(squares[j][i] == squares[j+1][i+1] && squares[j+1][i+1] == squares[j+2][i+2] && squares[j+2][i+2] == squares[j+3][i+3] && squares[j][i] != " ") {
				count = 4;
			} else if(squares[j][k] == squares[j+1][k-1] && squares[j+1][k-1] == squares[j+2][k-2] && squares[j+2][k-2] == squares[j+3][k-3] && squares[j][k] != " ") {
				count = 4;
			} else {
				count = -1;
			}
			//returns true if someone wins
			if(count == 4) {
				return true;
			}
		}
		
		//check last two diagonals*/
		
		
		//will only return false if ALL conditions have been checked
		return false;
		
	}
	//return the number of chips for this given space
	public int getnumChips(int col) {
		
		return numChips[col];
	}
}
