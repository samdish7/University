//payroll.h
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
	PayRoll(){name="N/A";rate=-1;hours=0;}
	PayRoll(std::string,double,double);
	
	void setName(std::string);
	void setRate(double);
	void setHours(double);
	std::string getName();
	double getRate();
	double getHours();
	double getTotal();
	
	friend std::ostream& operator<<(std::ostream&, const PayRoll&);
};

#endif
