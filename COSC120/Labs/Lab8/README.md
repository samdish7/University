# Lab 8 COSC 120; Sam Disharoon

README for Lab 8, written in 2022 :grinning:

## Binary

This program is the first instance of searching/sorting algorithms. Yay! :partying_face: An array that the user cannot see is given, and they are prompted to search for a number. If the number is in the list, it states so, if not, it also states so. 

### Bugs/Improvements
- Could be a little more user friendly. Display the list afterwards maybe? Idk
- Doesn't handle any bad inputs. Seraching for a letter results in finding it at position 1 and gives it a value of 0.
- Searching for a decimal results in just cutting off the decimal and taking the remaining integer value. Ex. Searching for 5.4 gives an output of __The value 5 is in position number 5 of the list__. 

## Bubble

This program demonstrates a simple bubble sorting algorithm. It takes a pre-determined array and displays each step done to it as it sorts,

### Bugs/Improvments
- Seems to work fine, doesn't appear to use reals, but it works as intended
- Can easily be condensed by quite a few lines. But overall proud of past me :tada: 

## Linear

Demonstrates a basic linear searching algorithm. Same as Binary, it prompts the user to search for an integer, and responds accordingly. 

### Bugs/Improvements
- Same bugs as Binary. No decimal/invalid input handling and can be more user friendly.

## Student

Attempts to demonstrate the different algorithms discovered in above programs.... fails miserably :smiling_imp:

### Bugs/Improvments
- Oh man where do I start? Firstly there is a little input validation regarding integers. If given an int > 50 or < 0, the program will get mad and ask for another one. But decimals/letters will cause the entire program to run with garbage values and then end. Its kinda funny.
- Giving valid input gives a correctly sorted array, but searching for anything (in the array or not) results in coming back as not found. There is something wrong with the algorithm. I don't feel like going in depth to find out why. 
- States the array is in ascending order... when it is really in descending order hehe.
- Mean gives some trash number. This is most likely due to being passed by reference as well as being set to the return value of the function. Amusing either way
