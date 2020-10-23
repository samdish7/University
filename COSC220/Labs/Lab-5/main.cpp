//Main file
//Sam Disharoon

#include "payroll.h"
#include "payrollstack.h"
#include <fstream>
#include <iostream>
#include <string>
int main (){
	PayRollStack emp;
	PayRoll x;
	std::string fName;
	std::string lName;
	std::string Name;
	double Rate;
	double Hours;
	std::ifstream file ("emp.txt");
	std::cout << "Adding employees from a file:\n";
	if (file.is_open()){
	while (file>>fName>>lName>>Rate>>Hours){
	Name=fName+" "+lName;
	x.setName(Name);
	x.setRate(Rate);
	x.setHours(Hours);
	std::cout<< "Inserting: "<<x.getName()<<"\n";
	emp.push(x);
	}
	file.close();
	}
	else {std::cout << "Unable to read file\n.";}
	std::cout<<"There are "<<emp.size()<<" employees in this stack\n\n";
	std::cout<<"Here are the list of Employees:\n\n";
	emp.printPayChecks();
//	std::cout<<"\n\nThis list shows off the Copy CTOR:\n";
//	PayRollStack emp2=emp;
//	emp2.printPayChecks();
	std::cout<<"\n\nPopping the employees!\n\n";
	emp.pop();
	emp.pop();
	emp.pop();
	emp.pop();
	emp.pop();
	emp.pop();
	emp.pop();
	PayRoll x2;
	x2.setName("New Guy");
	x2.setRate(5.05);
	x2.setHours(40);
	std::cout<<"Pushing: "<<x2.getName()<<"\n";
	emp.push(x2);
	std::cout<<"Popping: "<<x2.getName()<<"\n";
	emp.pop();
return 0;
}
