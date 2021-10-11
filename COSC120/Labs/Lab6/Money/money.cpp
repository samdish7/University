#include <iostream>
#include <iomanip>
using namespace std;
// Samuel Disharoon
void normalizeMoney(float& dollars, int cents = 150);
// This function takes cents as an integer and converts it to do dollars and cents.
// The default value for cents is 150 which is converted to 1.50 and stored in dollars
int main()
{
	int cents=0;
	float dollars;
	cout << setprecision(2) << fixed << showpoint;
	
	cout << "We start out with 0 cents in our dollar total";
		
	cout << "\n We will now add 95 cents to our dollar total\n";
			normalizeMoney(dollars, 95);
	cout << "Converting cents to dollars resulted in " << dollars << " dollars\n";
	cout << "\n We will now add 193 cents to our dollar total\n";
			normalizeMoney(dollars, 193);
	cout << "Converting cents to dollars resulted in " << dollars << " dollars\n";
	cout << "\n We will now add the default value to our dollar total\n";
			normalizeMoney(dollars);
	cout << "Converting cents to dollars resulted in "  << dollars << " dollars\n";
	return 0;
}
// ***************************************************************
//
// normalizeMoney
//
// task: This function is given a value in cents. It will convert cents to dollars
// and cents which is stored in a local variable called total which is sent
// back to the calling function through the parameter dollars. It will keep 

// a running total of all the money processed in a local static variable called
// sum.
//
// data in: cents which is an integer
// data out: dollars (which alters the corresponding actual parameter
//
//********************************************************************
void normalizeMoney(float& dollars, int cents)
{
	float total = 0;
	static float sum = 0.0;
	dollars = cents / 100.0;
	total = total + dollars;
	sum = sum + dollars;
	cout << "We have added another $" << dollars << " to our total" << endl;
	cout << "Our total so far is $" << sum << endl;
	cout << "The value of our local variable total is $" << total << endl;
}