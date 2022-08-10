# Projects COSC 120; Sam Disharoon

All 4 projects for COSC120, written in 2022 :grinning:

## Project 1; Dice Game

Ok, so I know I said I wouldn't edit anything, but I'm doing this on a mac, and _Sleep_ is a windows.h function. So I replaced that with _usleep_ so I could at least see what it is supposed to do.

This program simulates a little dice game! It lists asks you for your name, allows you to read the rules for 5 seconds, and then it simualtes a _you vs house_ style of automated play!  

### Bugs/Improvements

- Make the rules easier to read.
- Allow the player to roll instead of having them roll for us.
- Can input anything as a name, which isn't ideal, but not really a bug either.

## Project 2; Book Store Sales

This program demonstrates how to read info from a file and splay it on screen. It failes MISERABLY at this. The program doesn't crash, but it may as well. The goal appears to be a sales report for a book store. Attempting to perform a few calulations based off of the given input such as the highest/lowest number per month, as well as the total for each month. It also displays the total number of books sold by the store for that year.

### Bugs/Improvements

Where do I start? Ok, so:
- Ensure that it can actually read the data properly. 
- Implement all the functions before creating columns for them. 
- Implement the functions correctly.
- Congratulate the book store for selling over a billion books in a year. 
- Do better.

## Project 3; Grades

This program also attempts to read stuff in from a file, and as with __Book Store Sales__ fails MISERABLY. I'm honestly ashamed of seeing this and don't know how I passed that class with a B :rofl:. The bugs/improvements are literally the same as __Book Store__, so I won't waste any time reiterating myself.

This is supposed to read in a file that houses students and their CS grades, while storing the data in a class called _Student_. You can see what stats the Student class is composed of, but it won't matter as the program is unreadable. 

## Project 4; Hospital Bill

This program simulates a hospital bill creator. It allows the user to enter a patient's information, provide their bill, sort the patients, add more procedures, as well as find them once they are in the database. For the most part, this program works, but like 99% of the rest of them, needs a lil TLC :heart:
 
### Bugs/Improvements

As stated above, not bad but could use a tremendous amount of optimization. For a beginner level program and for it to be composed of 4 different .cpp files, fairly content with how this looks. There are plenty of improvements though:

- Nicer UI. Literally have no idea what my choices are for surgeries or medicine. Essentially just have to guess and pray.
- Found a :boom: where if you try to add a patient fee, it doesn't do anything or prompt you for anything, and if you enter a letter, it will crash and burn.
- __ADD INPUT VALIDATION FOR ONCE IN YOUR LIFE__. Can crash this by entering nonsense.
- Sort isn't implemented.
