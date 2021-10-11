# Lab 6 COSC 120; Sam Disharoon

README for Lab 2, written in 2021 :grinning:

## Bill

This program introduces file I/O. It will read in the data from the file **transaction.dat**. The data in the file is 2 lines, first line is quantity and seconds is item price. The output is sent to the file **bill.out**. 

### Bugs
- Any invalid file format will result in the program not creating the file. There is no error checking besides 
- Now that I look at it, I tried to delete the file and recreate it and it didn't work. So I honestly don't know if this program even works.

## Dentist

This program asks the user if they are part of a dental plan and allows them to input the service charge.... that's literally it :poop:

### Bugs
- Doesn't actually do anything with the input, so inputing ANYHTING other than 1 will not prompt you to input the service charge. 
- Inputting anything for the service charge will not cause an infinite loop because the service charge isn't used other than to get an input. No other calculations were done on it.

## Money

This file contains two seperate programs. _money.cpp_ & _convertmoney.cpp_. I will go over them both seperately. 

### convertmoney.cpp

This program demonstrates converting American dollars to Pesos, Yen, & Euros at the rate as of late 2016. Obviously those values are never constant so it would be useless today, but it at least gives a good demonstration of the use of global variables, passing by reference, and the key word _const_.

#### Bugs
- No error checking (shocker)
- Inputting negative numbers will run the program normally, and the outputs are as expected, they multiply the conversion factor by whatever is given
	* Inputting a any combination of only letters will display all values as _0.00_; _Example ~>_ *r*, *rr*, & *rrr* all yield the given result
	* Inputting a number(positive/negative) then a letter followed by any other combo will give the correct display for the first function, then all other values will default to _0.00_; _Example ~>_ *4r*, *4rr*, *4r4r* all yield  _$4.00 is converted to 2.80 euros and 50.64 pesos_, and then _0.00_ for all other functions

### money.cpp

This program demostrates the differences between the global and local variables by performing various tasks on a virtual amount of money. Since there is no user input, there are no chances to crash the program. It seems to work as intended but I didn't go in depth at the code because I frankly don't feel like it :grinning:.

## Scope

This program displays similarly to _money.cpp_ differences of scope for local/global variables. Again, where there are no user inputs, the program has no way for the user to try and crash it. Yet again, program seems to work fine even displaying messages when entering certain functions. Therefore I don't feel like looking at the code to see if it actually works like intended. 
