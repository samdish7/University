//Main file
//Sam Disharoon

#include "payroll.h"
#include "payrolllist.h"
#include <fstream>
#include <iostream>
#include <string>
int main (){
	PayRollList emp;
	std::string fName;
	std::string lName;
	std::string Name;
	double Rate;
	double Hours;
	std::ifstream file ("emp.txt");
	std::cout << "These are the hardcoded examples:\n";
	emp.insert("Sam Disharoon",15.75,40);
	emp.insert("Robert Williams",23.45,40);
	emp.insert("Kyrie Irving",28.65,40);
	emp.printPayChecks();
	std::cout << "\n\nAdding employees from a file:\n";
	if (file.is_open()){
	while (file>>fName>>lName>>Rate>>Hours){
	Name=fName+" "+lName;
	emp.insert(Name,Rate,Hours);
	}
	file.close();
	}
	else std::cout << "Unable to read file\n.";
	emp.printPayChecks();


return 0;
}
