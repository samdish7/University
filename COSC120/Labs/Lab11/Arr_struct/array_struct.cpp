#include <iostream>
#include <iomanip>

using namespace std;


// This program demonstrates how to use an array of structures
// Sam Disharoon
const int MAXCITIZENS = 5;
struct taxPayer
{
	float taxRate;
	float income;
	float taxes;


};

typedef taxPayer  citizen[MAXCITIZENS];
int main()
{
	
	citizen cits;
	cout << fixed << showpoint << setprecision(2);

	cout << "Please enter the annual income and tax rate for 5 tax payers: ";
	cout << endl << endl << endl;

	for (int count = 0; count < 5; count++)
	{

		cout << "Enter this year's income for tax payer " << (count + 1);
		cout << ": ";

		cin >> cits[count].income; 

		cout << "Enter the tax rate for tax payer # " << (count + 1);
		cout << ": ";

		cin >> cits[count].taxRate;

		cits[count].taxes = cits[count].income * cits[count].taxRate;


		cout << endl;
	}

	cout << "Taxes due for this year: " << endl << endl;

	for (int index = 0; index < MAXCITIZENS; index++)
	{
		cout << "Tax Payer # " << (index + 1) << ": " << "$ "
			<< cits[index].taxes << endl;
	}

	return 0;
}
