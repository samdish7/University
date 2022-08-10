#include <iostream>
#include "Surgery.h"
#include <string>
using namespace std;

void Surgery::requestInput()
{

	int i;
	float c = 100.00;
	string input;

	for (i = 0; i < 5; i++)
	{
		didhave[i] = 0;
		costs[i] = c;
		c += 100;
	}


	do {

		cout << "Which types of surgery did the patient have?\nPress 1 to if there is no other surgery to be inputted.\n";
		cin >> input;

		if (input[0] == '1')
			break;
		if (input.compare("Tonsillectomy") == 0 || input.compare("tonsillectomy") == 0)
		{
			didhave[0] = 1;
		}
		else if (input.compare("Foot") == 0 || input.compare("foot") == 0)
		{
			didhave[1] = 1;
		}
		else if (input.compare("Knee") == 0 || input.compare("knee") == 0)
		{
			didhave[2] = 1;
		}
		else if (input.compare("Shoulder") == 0 || input.compare("shoulder") == 0)
		{
			didhave[3] = 1;
		}
		else if (input.compare("Appendectomy") == 0 || input.compare("appendectomy") == 0)
		{
			didhave[4] = 1;
		}
		else
			cout << "Invalid Input";
	} while (input[0] != '1');
}
void Surgery::sendTotal(double *charges)
{
	int i;
	float total = 0;

	for (i = 0; i<5; i++) {
		if (didhave[i] == 1)
			total += costs[i];
	}
	*charges = *charges + total;
}