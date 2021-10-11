#include <iostream>
#include <iomanip>
using namespace std;
// This program will input American money and convert it to foreign currency
// Samuel Disharoon
//Prototypes of the functions
void convertMulti(float dollars, float& euros, float& pesos);
void convertMulti(float dollars, float& euros, float& pesos, float& yen);
float convertToYen(float dollars, float& yen);
float convertToEuros(float dollars, float& euros);
float convertToPesos(float dollars, float& pesos);
const float E = .701;
const float P = 12.66;
const float Y = 91.25;
int main()
{
	float dollars;
	float euros;
	float pesos;
	float yen;
	cout << fixed << showpoint << setprecision(2);
	cout << "Please input the amount of American Dollars you want converted " << endl;
	cout << "to euros and pesos" << endl;
	cin >> dollars;
	convertMulti(dollars, euros, pesos);	// Fill in the code to call convertMulti with parameters dollars, euros, and pesos
	cout << "$" << dollars << " is converted to " << euros << " euros and " << pesos << " pesos.\n" << endl;// Fill in the code to output the value of those dollars converted to both euros and pesos
	cout << "Please input the amount of American Dollars you want converted\n";
	cout << " to euros, pesos and yen" << endl;
	cin >> dollars;
	convertMulti(dollars, euros, pesos, yen);	// Fill in the code to call convertMulti with parameters dollars, euros, pesos, and yen
	cout << "$" << dollars << " is converted to " << euros << " euros and " << pesos << " pesos and " << yen << " yen\n" << endl;// Fill in the code to output the value of those dollars converted to yen
	cout << "Please input the amount of American Dollars you want converted\n";
		cout << " to yen" << endl;
	cin >> dollars;
	convertToYen(dollars, yen);	// Fill in the code to call convertToYen
	cout << "$" << dollars << " is converted to " << yen << " yen\n" << endl; // Fill in the code to output the value of those dollars converted to both euros and pesos
	cout << "Please input the amount of American Dollars you want converted\n ";
		cout << " to euros" << endl;
	
	cin >> dollars;
	convertToEuros(dollars, euros);	// Fill in the code to call convertToEuros
	cout << "$" << dollars << " is converted to " << euros << " euros\n" << endl;// Fill in the code to output the value of those dollars converted to euros
		cout << "Please input the amount of American Dollars you want converted\n";
		cout << " to pesos " << endl;
	cin >> dollars;
	convertToPesos(dollars, pesos);	// Fill in the code to call convertToPesos
	cout << "$" << dollars << " is converted to " << pesos << " pesos\n" << endl;// Fill in the code to output the value of those dollars converted to pesos
	return 0;
}
// All of the functions are stubs that just serve to test the functions
// Replace with code that will cause the functions to execute properly
// ***************************************************************
// convertMulti
//
// task: This function takes a dollar value and converts it to euros and pesos
// data in: dollars
// data out: euros and pesos
//
//*****************************************************************
void convertMulti(float dollars, float& euros, float& pesos)
{
	euros = dollars * E;
	pesos = dollars * P;
}
// ***************************************************************
// convertMulti
//
// task: This function takes a dollar value and converts it to euros pesos and yen 

// data in: dollars
// data out: euros pesos yen
//
//*****************************************************************
void convertMulti(float dollars, float& euros, float& pesos, float& yen)
{
	euros = dollars * E;
	pesos = dollars * P;
	yen = dollars * Y ;
}
// ***************************************************************
// convertToYen
//
// task: This function takes a dollar value and converts it to yen
// data in: dollars
// data returned: yen
//
//*****************************************************************
float convertToYen(float dollars , float& yen)
{
	yen = dollars * Y;
	return 0;
}
// ***************************************************************
// convertToEuros
//
// task: This function takes a dollar value and converts it to euros
// data in: dollars
// data returned: euros
//
//*****************************************************************
float convertToEuros(float dollars , float& euros)
{
	euros = dollars * E;
	
		return 0;
}
// ***************************************************************
// convertToPesos
//
// task: This function takes a dollar value and converts it to pesos
// data in: dollars
// data returned: pesos
//
//*****************************************************************
float convertToPesos(float dollars , float& pesos)
{
	pesos = dollars * P;
	return 0;
}