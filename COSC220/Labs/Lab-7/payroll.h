//PayRoll header file
//Sam Disharoon
#ifndef PAYROLL_H
#define PAYROLL_H

#include <string>
class PayRoll{
	private:
	std::string name;
	double rate;
	double hours;
	
	public:
	PayRoll*  makeEmp(std::string, double, double);
	double getTotal();
	double getRate();
	double getHours();
	std::string getName();
	void setRate(double);
	void setName(std::string);
	void setHours(double);
};



#endif
