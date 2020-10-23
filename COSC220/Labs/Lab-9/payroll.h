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
	PayRoll(){name="N/A";rate=0;hours=0;}
	PayRoll(std::string, double, double);
	PayRoll operator+(PayRoll&);
	bool operator<(PayRoll);
	double getTotal();
	double getRate();
	double getHours();
	std::string getName();
	void setRate(double);
	void setName(std::string);
	void setHours(double);

	friend std::ostream& operator<<(std::ostream&, const PayRoll&);
};



#endif
