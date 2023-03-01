# Lab 4 COSC 120; Sam Disharoon

*README written in 2021* :grinning:

## Baseball

This program displays your winning % after being asked how many games a team has won/lost. 

### Bugs

- Entering a negative number for _wins_ results in a percentage of __-inf%__
- Entering a negative number for _losses_ results in a percentage of >100.00%
- Entering a letter in the _wins_ section results in the program not allowing for the _losses_ to be entered and yeilds the result of __-0.00%__
- Entering a number for _wins_ and a letter for _losses_ results in a percentage of __100.00__

## Paycheck

This program asks the user for their pay rate and the hours they worked. Then displays the gross and net pays.

### Bugs
- There is no input validation, so negative numbers would give their respective outputs. Negative payrate & negative hours results in positive pay. Negative payrate or hours with a positive payrate or hours results in negative pay.
- Entering letters will result in a payrate of **0.00** as it should. 

## Proverb

This program asks the user to finish the sentence ~> "Now is the time for all good men to come to the aid of their ... ". The choices are:
- 1 ~> party
- 2 ~> country

There shockingly IS input validation for numbers. So inputting any other number will not be allowed.

### Bugs
- Although there is input for numbers, inputting any other character other than a number will result in an infinite loop.

## Swap

This program is supposed to allow the user to input 2 numbers, and then swap them using the "pass by reference" operators... it does not, all it does is say "Your numbers are num1 & num2".

### Bugs
- In addition to not working right, there is no input validation. Entering a letter as num1 will yield **You input the numbers as 0 and 32766**. 
- Entering a number first and then a letter will result in **You input the numbers as num1 & 0**

## Feedback

Tons more bugs, but seems as if most programs appear to do what they are asked of them minus **input validation** (except *Proverb* which validates numbers) and the *Swap* program. 

***Overall Rating 6.5/10***
