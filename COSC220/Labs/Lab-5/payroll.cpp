//PayRoll cpp file
//Sam Disharoon

#include "payroll.h"
#include <iostream>
PayRoll::PayRoll(std::string n, double r, double h){
	name=n;
	rate=r;
	hours=h;
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
void PayRoll::printCheck(){
	std::cout<<getName()<<"///";
	std::cout<<"Pay Rate: $"<<getRate()<<"///";
	std::cout<<"Total Pay: $"<<getTotal()<<"\n";
	std::cout<<"**************************************************************\n";}
