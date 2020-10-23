//payroll.cpp
//Sam Disharoon
#include <string>
#include "payroll.h"
#include <iostream>
PayRoll::PayRoll(std::string n,double r,double h){
	name=n;rate=r;hours=h;
}
void PayRoll::setName(std::string n){name=n;}
void PayRoll::setRate(double r){rate=r;}
void PayRoll::setHours(double h){hours=h;}
std::string PayRoll::getName(){return name;}
double PayRoll::getRate(){return rate;}
double PayRoll::getHours(){return hours;}
double PayRoll::getTotal(){return (rate * hours);}
std::ostream& operator<<(std::ostream& o, const PayRoll& pay){
	std::cout<<"\nName: "<<pay.name;
	std::cout<<"\nPay rate: $"<<pay.rate;
	std::cout<<"\nHours worked: "<<pay.hours;
	std::cout<<"\nTotal pay: $"<<pay.hours*pay.rate<<"\n";
return o;}
