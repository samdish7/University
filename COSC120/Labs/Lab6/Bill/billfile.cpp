// This program will read in the quantity of a particular item and its price
// It will then print out the total price.
// The input will come from a data file and the output will go to an output file. 

// Samuel Disharoon
#include <fstream>
#include <iomanip>
#include <iostream>
using namespace std;
int main()
{
	ifstream dataIn; // defines an input stream for a data file
	ofstream dataOut; // defines an output stream for an output file
	int quantity; // contains the amount of items purchased
	float itemprice; // contains the price of each item
	float totalbill; // contains the total bill, i.e. The price of all items
	dataIn.open("transaction.dat"); // This opens the file
	dataOut.open("bill.out");
	dataOut << setprecision(2) << fixed << showpoint;//formatted output
	dataOut << "The total Bill is: $";
	dataIn >> quantity >> itemprice; 
	totalbill = quantity * itemprice;
	dataOut << totalbill;// Fill in the output statement that prints the total bill, with a label, to an output file
	return 0;
}