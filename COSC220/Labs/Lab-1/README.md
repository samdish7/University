# Lab 1; COSC 220; Sam Disharoon

*README written in 2023* :grinning:

## Overview

This was mainly a review lab for COSC 120. Demonstrated how pointer work by using arrays as an example. Nice to see that after three attempts at this course, past me finally learned how to comment. Still can't format worth a damn though :grinning:.

### Data Sheet Results

I have removed the .odt lab sheet and formatted it below for all to see. *No modifications to the original file have been made.*

**Computer Science 220 Lab 1 Sam Disharoon**

|OutPut|Output 2|Scrolling through the terminal|
|------|--------|------------------------------|
|How many numbers would you like?|How many numbers would you like?|dish@dish-VirtualBox:~/Documents/COSC220$ ls|
|2|5|a.out  arrays.cpp|
|Enter a number: 4|Enter a number: 82|dish@dish-VirtualBox:~/Documents/COSC220$ cd ..|
|Enter a number: 7|Enter a number: 19|dish@dish-VirtualBox:~/Documents$ cd ..|
|The mean of your numbers is: 5.5|Enter a number: 1|dish@dish-VirtualBox:~$ ls|
|The mean of your numbers is: 5.5|Enter a number: 2|Desktop  Documents  Downloads  Music  Pictures  Public  Templates  Videos|
||Enter a number: 768|dish@dish-VirtualBox:~$ cd Documents|
||The mean of your numbers is: 174.4|dish@dish-VirtualBox:~/Documents$ ls|
||The mean of your numbers is: 174.4|a.out  COSC220  file.cpp  Text.cpp|
|||dish@dish-VirtualBox:~/Documents$ cd COSC220|
|||dish@dish-VirtualBox:~/Documents/COSC220$ ls|
|||a.out  arrays.cpp|

## Feedback

The mean function appears to be called twice, however upon further inspection, they are not the same. They access the array elements differently, but the end-user wouldn't know that was happening as there is no indication of this.

**No input validation**. Apparently still haven't quite gotten that concept down yet. Any non-integer input will crash the program and give garbage results. :poop:

Not too bad though still does what it seems to need to do and semi-demonstrates different ways to access array elements using both pointers and indexes.

***Overall rating: 7.5/10***
