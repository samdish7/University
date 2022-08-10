#ifndef PHARMACY_H
#define PHARMACY_H
#include <string>
#include "PatientAccount.h"

using namespace std;

class Pharmacy {

private:
	int didhave[5];
	float costs[5], total;
	string types[5];
public:
	void requestInput();
	void sendTotal(double *charges);

};

#endif