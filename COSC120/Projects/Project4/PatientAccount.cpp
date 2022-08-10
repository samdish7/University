#include <iostream>
#include "PatientAccount.h"
#include <string>
using namespace std;

void PatientAccount::getName() {
	cout << "Please enter the patient's name: ";
	cin >> name;
}

void PatientAccount::getDays() {
	cout << "Please enter the number of days " << name << " has spent in the hosptial: ";
	cin >> days;
	charges = 0;
}

void PatientAccount::updateBill() {
	charges += (500.00)*days;
}

void PatientAccount::displayData() {
	cout << endl;
	cout << "Name: " << name << endl << "Days: " << days << endl << "Total charge: $" << charges << endl;
}
void PatientAccount::searchName(string n) {

	if (n.compare(name) == 0)
	{
		cout << "Patient: " << name << "'s " << "charges are... $" << charges << endl;
		cout << "The patients amount of days spent in the hospital was: " << days << endl;
	}
}
void PatientAccount::addFee(float fee, string n)
{
	if (n.compare(name) == 0)
	{
		charges += fee;
	}
}