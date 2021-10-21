# Lab 8; Subroutines

## Description

This lab introduces how to use subroutines in Java by implementing the game **High-Low**.
The rules of the game are simple, two players draw a card from a single normal deck without replacement. Whomever has the highest number wins. In an event of a tie, you look at suits. The suit hierarchy is as follows:

	- Spades
	- Diamonds
	- Clubs
	- Hearts

For example:

	- 6 of Spades beats all other 6s
	- 6 of Diamonds beats a 6 of Clubs and a 6 of Hearts
	- 6 of Clubs beats 6 of Hearts

At the end of the program, the cards are "placed" back in the deck and the players draw again. This process repeats until one player hits 7. That player wins!

### Functionality

There are a total of 3 subroutines in this program:

	- getFace
	- getSuit
	- getWinner

Each are described in the program, but in short, getFace gets a face value from 2-14, 11, 12, 13 & 14 are Jack, Queen, King, & Ace, respectively.

getSuit gets a random suit to go along with each card. 

*NOTE* in the event of a tie, the program will redraw both cards. Since programming non replacement is not required, I use a simple work around to catch a tie and have them both draw again.

getWinner returns either true or false depending on whom is the determined winner. True = player 1 wins, and false = player 2 wins.

Combine these functions with a little display manipulation and High-Low works as intended
