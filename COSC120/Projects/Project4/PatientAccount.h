#ifndef PATIENT_ACCOUNT_H
#define PATIENT_ACCOUNT_H

#include <iostream>
#include <string>
#include "Surgery.h"

using namespace std;

class PatientAccount {

public:
	string name;
	int days;
	double charges;
	void getName();
	void getDays();
	void updateBill();
	void displayData();
	void searchName(string n);
	void addFee(float fee, string n);
};

#endif