#ifndef SURGERY_H
#define SURGERY_H
#include <string>

using namespace std;

class Surgery {

private:
	int didhave[5];
	float costs[5], total;
	string types[5];
public:
	void requestInput();
	void sendTotal(double *charges);
};
#endif