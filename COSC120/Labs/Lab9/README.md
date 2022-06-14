# Lab 9 COSC 120; Sam Disharoon

Lab 9 for COSC 120, written in 2022 :grinning:

## Case_Conversion 

Demonstrates using **iomanip** by asking the user for 4 weeks worth of food costs, then displaying their monthly average. Run by a do/while loop. Also uses **cctype** to demonstrate lower/upper casing manipulation.

### Bugs/Improvements

- Like all previous labs, no input validation. So decimals/letters will cause an infinite loop. 
- Inputs for exiting are flipped. Y will cancel the program and n will keep it going.

## Equities

Demonstrates a very very basic version of a password verifier. It allows the user to input a string. It wants all lowercase letters for some reason. All input is accepted, but only lowercase letters are considered valid. Interesting... :thinking:

## Consonants

Attempts to provide the number of consonants given in a string. It does well... if you do as it says and type only lowercase letters in. 

### Bugs/Improvements

If you decide to NOT do ass it says, it will give us some beautiful results. Uppercase letters, numbers, and symbols are all counted as consonants. You cannot give it a space as it will only accept characters previous to the first space. Everything else will get _yeeted_

## Grades

This is supposed to read from the file __grades.txt__ and output the contents of it. It does not meet expectations at all.

### Bugs/Improvements

Welp, for starters, make the damn thing run. If you try and execute this program, it will run infintly. So that's a plus!
