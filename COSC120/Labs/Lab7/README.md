# Lab 7 COSC 120; Sam Disharoon

README for lab 7, written in 2022 :grinning:

## Student

This program demonstrates arrays in c++. It asks the user how many grades for each student they will enter, asks if they want to enter a student, then prompts for first and last names. Once given, it will ask for the grades depending on how many options chosen at the start. After all grades are given, it displays the name, average, and letter grade. Then asks if they want to add another student or not... very weird format.

### Bugs
- Entering a letter in basically any area that requires a number will result in an infinite loop. 
- Input validation is an option for the beginning prompt, so if you put a whole number < 1 or > 25 will result in being asked again to enter a valid number. However, if you enter a float, it will just stop the program. 
- Entering negative numbers or > 100 will be accepted. However, negatives result in a grade of E, and > 100 averages will result in a grade of B... wild

## Test

This program allows the user to demonstrate function and array usage. It will ask the user to enter up to 20 grades, and then displays the stats about them.

### Bugs
- Segfaults immediately after entering grades. This is because I call _findAverage_ by passing in an uninitalized variable. Therefore when trying to calcuate the size of the array, it goes out of bounds and segfaults. :smile:
- Pretty sure **testscore.cpp** was someone else's program that past me used as a reference, but wasn't smart enough to not add it to the submission file. Bad me :facepalm:

## Source,cpp

Literally just a basic program that displays *Hello*. No clue why it is here.
