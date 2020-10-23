//Main file
//Sam Disharoon

#include <fstream>
#include <iostream>
#include <string>

#include "payroll.h"
#include "payrolllist.h"

int main (){
	PayRollList emp;
	PayRoll* bro = new PayRoll;
	std::string fName;
	std::string lName;
	std::string Name;
	double Rate;
	double Hours;
	int pos;
	bool a=false;
	std::ifstream file ("emp.txt");
	
	std::cout << "These are the hardcoded examples:\n";
	emp.insert("Sam Disharoon",15.75,40);
	emp.insert("Robert Williams",23.45,40);
	emp.insert("Kyrie Irving",28.65,40);
	emp.printPayChecks();
	std::cout <<"The number of employees in this list are: " <<emp.length() << "\n";

	PayRollList emp2 = emp;
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
	std::cout<<"\n\n\nThis shows off the copy CTOR:\n";
	emp2.printPayChecks();
	std::cout<<"The number of employees in this list are: "<<emp2.length();
	std::cout<<"\n\n\n";
	
	std::cout<<"What position would you like to insert a new employee in the first list? The number of employees in this list is "<<emp.length()<<"\n";
	std::cin>>pos;
	if(pos>emp.length()||pos<=1){
	std::cout<<"This position doesn't exist! Please pick a postion between 1 and "<<emp.length()<<"\n";
	std::cin>>pos;
	}
	bro->setName("Dick Chaney"); bro->setRate(19.80); bro->setHours(40);
	emp.printPayChecks();

		
return 0;
}
