//PayRoll cpp file
//Sam Disharoon
#include<iostream>
#include "payroll.h"
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
PayRoll PayRoll::operator+(PayRoll& x2){
	PayRoll newguy;
	newguy.name="newguy";
	newguy.rate=this->rate + x2.rate;
	newguy.hours=this->hours+x2.hours;
	return newguy;

}
bool::PayRoll::operator<(PayRoll x2){
	PayRoll comp=*this;
	return (comp.rate<x2.rate ? true : false);
}
std::ostream& operator<<(std::ostream&, const PayRoll& g){
	PayRoll guy=g;
	std::cout<<"Employee: "<<guy.getName()<<"\nPay rate: $"<<guy.getRate()<<"\nHours worked: "<<guy.getHours()<<"\n";}
