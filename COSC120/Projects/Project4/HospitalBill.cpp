#include <iostream>
#include "Surgery.h"
#include "PatientAccount.h"
#include <string>
#include "Pharmacy.h"
#include <new>
using namespace std;

void sortData(PatientAccount a[], const int num);

int main(void) {

	int num = 0;

	Surgery *p1;
	PatientAccount *p2;
	Pharmacy *p3;
	cout << "Welcom to the Hosipital database.\nIf there is more than one word, please separate them using a _ instead of a space.\nPlease enter the number of patients: ";
	cin >> num;
	const int NUMBER = num;
	p1 = new (nothrow)Surgery[num];
	p2 = new (nothrow)PatientAccount[num];
	p3 = new (nothrow)Pharmacy[num];
	if (p1 == nullptr || p2 == nullptr || p3 == nullptr)
	{
		cout << "Invalid " << endl;
	}
	else {
		for (int i = 0; i < num; i++)
		{

			p2[i].getName();
			p2[i].getDays();

			p1[i].requestInput();
			p1[i].sendTotal(&p2[i].charges);

			p3[i].requestInput();
			p3[i].sendTotal(&p2[i].charges);
			p2[i].updateBill();

		}
	}

	char input;

	do
	{
		cout << "Enter a menu choice" << endl;
		cout << endl;
		cout << "==============================" << endl;
		cout << "Patient Fee Information Database Menu" << endl;
		cout << "== == == == == == == == == == == == == == == " << endl;
		cout << "V - View Patient Fee Information Database" << endl;
		cout << "F - Find Patient Fee Information by Patient Name" << endl;
		cout << "S - Sort Patient Fee Information" << endl;
		cout << "A - Add Patient Fee" << endl;
		cout << "Q - Quit the program" << endl;
		cout << endl;
		cin >> input;

		string inputt = "";
		float fee;
		if (input == 'Q' || input == 'q')
			break;

		switch (input)
		{
		case 'V': case 'v':
		{
					  for (int i = 0; i < num; i++)
					  {
						  p2[i].displayData();
						  cout << endl;
					  }
					  break;
		}
		case 'F': case 'f':
		{
					  cout << "Enter a patients name to search for : ";
					  cin >> inputt;
					  for (int i = 0; i < num; i++)
						  p2[i].searchName(inputt);


					  break;
		}
		case 'S': case 's':
		{
					  cout << "I'm sorry, but the Sort Function is not available.  Please pick a different input." << endl;
					  break;
		}
		case 'A': case 'a':
		{

					  cout << "Enter a patients name to add a fee, and enter a fee to add: ";
					  cin >> inputt >> fee;

					  for (int i = 0; i < num; i++)
						  p2[i].addFee(fee, inputt);
					  break;
		}
		case 'Q': case 'q':
		{

					  break;
		}
		}
	} while (input != 'q' || input != 'Q');







	return 0;


}
void sortData(PatientAccount a[], const int num)
{
	int i, j, flag = 1;
	PatientAccount temp;
	PatientAccount b[5];
	string input;
	for (int i = 0; i < num; i++)
	{
		b[i] = a[i];
	}
	cout << "Would you like to sort by name or charges? " << endl;
	cin >> input;

	if (input.compare("name")) {
		for (i = 1; i <= num && flag; i++) {
			flag = 0;
			for (j = 0; j < num - 1; j++) {
				if (b[j + 1].name.compare(b[j].name) == 1) {
					temp = b[j];
					b[j] = b[j + 1];
					b[j + 1] = temp;
					flag = 1;
				}
			}
		}
	}
	else if (input.compare("charges")) {

	}
	else {
		cout << endl;
		cout << "Invalid input";
	}
}