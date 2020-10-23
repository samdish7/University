//PayRoll cpp file
//Sam Disharoon

#include "payroll.h"

PayRoll* PayRoll::makeEmp(std::string n, double r, double h){
	PayRoll* a = new PayRoll;
	
	a->setName(n);
	a->setRate(r);
	a->setHours(h);

	return a;
}
void PayRoll::setName(std::string n){
	name=n;
}
std::string PayRoll::getName(){
	return name;
}
void PayRoll::setRate(double r){
	rate=r;
}
double PayRoll::getRate(){
	return rate;
}
void PayRoll::setHours(double h){
	hours=h;
}
double PayRoll::getHours(){
	return hours;
}
double PayRoll::getTotal(){
	return (rate*hours);
}
