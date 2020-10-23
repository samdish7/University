//Main file
//Sam Disharoon

#include <fstream>
#include <iostream>
#include <string>

#include "payroll.h"
#include "payrolllist.h"

int main (){
	PayRollList emp;
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
	PayRollList emp2=emp;
	std::cout<<"\n\n\nThis shows off the copy CTOR:\n";
	emp2.printPayChecks();
	std::cout<<"The number of employees in this list are: "<<emp2.length();
	std::cout<<"\n\n\n";
	std::cout<<"This shows off the assignment operator:\n";
	PayRollList emp3;
	emp3=emp;
	emp3.printPayChecks();
	std::cout<<"The number of employees in this list are: "<<emp3.length();
	std::cout<<"\n\n\n";
	std::cout<<"To test different overloaded operators, we are going to create some new PayRoll objects\n";
	PayRoll test1={"Sally",10.89,42};
	PayRoll test2={"Bobby",11.2,23};
	std::cout<<"The two new Employees are:\n"<<test1.getName()<<" who's rate is: $"<<test1.getRate()<<"/hr while working "<<test1.getHours()<<" hours\n&\n"<<test2.getName()<<" who's rate is: $"<<test2.getRate()<<"/hr while working "<<test2.getHours()<<" hours\n";
	PayRoll test3;
	test3 = test1+test2;
	std::cout<<"Now we will create another employee whom is named "<<test3.getName()<<". This employee's pay checks will be "<<test1.getName()<<" and "<<test2.getName()<<" combined hours and rate.\nHis rate is: $"<<test3.getRate()<<"/hr while working "<<test3.getHours()<<" hours\n\n";
	std::cout<<"Now to test the < operator\n";
	if(test1<test2)
	{std::cout<<test1.getName()<<" makes less than "<<test2.getName()<<"\n\n";}
	else
	{std::cout<<test1.getName()<<" makes more than "<<test2.getName()<<"\n\n";}
	std::cout<<"We will insert "<<test1.getName()<<" into the first list.\n";
	emp.insert(test1);
	std::cout<<emp;
	std::cout<<"We are doing this to test which list makes more in a week.\n";
	if(emp<emp2)
	{std::cout<<"List 1 made more this week than List 2.\n\n";}
	else
	{std::cout<<"List 1 did not make more this week than List 2.\n\n";}
	std::cout<<"Now testing on empty lists.\n";
	PayRollList emp4;
	emp<emp4;
	std::cout<<"Now testing on two lists that are the same.\n";
	emp2<emp3;
	std::cout<<"\nTime to use << to print an Employee (I already used << on the list when I inserted "<<test1.getName()<<" into her list).\n";
	std::cout<<test2;	
	return 0;
}
