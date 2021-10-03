# Lab 3 COSC 120; Sam Disharoon

README for lab 3, written in 2021 :grinning:

## Beverage

This program displays a simple menu asking the user what they would beverage they would like to order. Once a beverage is chosen, asks the quantity, then displays total cost. Repeats until user opts to exit. 

### Bugs
  
Entering something other than the choices given may result in multiple runs of the program or an infinite loop as there is no error checking. Didn't know what that was back then :poop:.

_Exhibit A ~>_ The input "rrr" will iterate the menu 3 times before asking the user for another input.

_Exhibit B ~>_ The input "ar" will cause an infinite loop. A is processed fine, then r is passed as the "int" given and causes it to spaz.

_Exhibit C ~>_ The input "a4" will pick "Coffee" and then take "4" as the "int" given and then display the following:
	- "How many cups would you like?"
	- "The total cost is $ 4.00"

_Exhibit D ~>_ The input "rrra" will cause the menu to iterate 4 times, with the 4th processing the "a" as a valid input, then will proceed to ask the user the quantity.

## Mean

This program asks the user for two integers, and displays the mean of the two. Simple as that.

### Bugs
	
Entering a positive number, and then a number lower will give the result "nan". There is validation of negative integers, but only at the end. 0 is not valid either.

_Exhibit A ~>_ Input 1: 6; Input 2: 5; This will result in:

**The mean average from 6 to 5 is nan**

_Exhibit B ~>_ If input 1 is negative, it will allow the user to give input 2, then tell you it is invalid.

_Exhibit C ~>_ Any combination of letters will result in the program finishing and then displaying the invalid message

## Nested

This program displays the functionality of nested for loops by asking the user about student study habits for two subjects; Biology and Computer Science. After giving the number of students and number of days studied. 

### Bugs

If you enter the same amount of hours for each subject, it will default to say "programming" was studied the most. Pretty sure I was biased in that regard but still :)

Other bugs include the typical typing in letters/words for inputs will cause crashes or infinite loops.
