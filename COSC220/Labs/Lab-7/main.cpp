//Main file
//Sam Disharoon

#include <fstream>
#include <iostream>
#include <string>

#include "payroll.h"
#include "payrolllist.h"

int main (){
	PayRollList emp;
	std::string fName,lName,Name,x;
	double Rate,Hours;
	bool a=false;
	std::ifstream file ("emp.txt");
	
	std::cout << "These are the hardcoded examples:\n";
	emp.insert("Sam Disharoon",15.75,40);
	emp.insert("Robert Williams",23.45,40);
	emp.insert("Kyrie Irving",28.65,40);
	emp.printPayChecks();
	std::cout <<"The number of employees in this list are: " <<emp.length() << "\n";

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
	std::cout<<"The number of employees in thsi list are: "<<emp.length() << "\n";
	
	std::cout<<"What employee would you like to search for?\n";
	std::cin>>x;
	emp.search(x);

		
return 0;
}
