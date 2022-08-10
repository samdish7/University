#include <iostream>
#include "Pharmacy.h"
#include <string>
using namespace std;

void Pharmacy::requestInput()
{

	int i;
	float c = 10.00;
	string input;

	for (i = 0; i < 5; i++) {
		didhave[i] = 0;
		costs[i] = c;
		c += 10;
	}


	do {
		cout << "Which types of medication did the patient have?\nPress 1 if there was no other medication used.\n";
		cin >> input;

		if (input[0] == '1')
			break;
		if (input.compare("Antibiotics") == 0 || input.compare("antibiotics") == 0)
		{
			didhave[0] = 1;
		}
		else if (input.compare("Antinausea") == 0 || input.compare("antinausea") == 0)
		{
			didhave[1] = 1;
		}
		else if (input.compare("Antiflamatory") == 0 || input.compare("antiflamatory") == 0)
		{
			didhave[2] = 1;
		}
		else if (input.compare("Weak_pain") == 0 || input.compare("weak_pain") == 0)
		{
			didhave[3] = 1;
		}
		else if (input.compare("Strong_pain") == 0 || input.compare("strong_pain") == 0)
		{
			didhave[4] = 1;
		}
		else
			cout << "Invalid Input";
	} while (input[0] != '1');
}
void Pharmacy::sendTotal(double *charges)
{
	int i;
	float total = 0;

	for (i = 0; i<5; i++) {
		if (didhave[i] == 1)
			total += costs[i];
	}
	*charges = *charges + total;
}