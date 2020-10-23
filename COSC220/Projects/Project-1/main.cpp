// Project 1
// Sam Disharoon

#include <iostream>
#include "course.h"
#include "student.h"
#include "studentdb.h"

int main(){

	StudentDB list;
	Student stu;
	char a,e,x;
	bool leave=false,leave2=false,leave3=false,leave4=false,leave5=false;
	int b=0;
	std::string c,n,m1,m2,m3;
	std::cout<<"<><><><><><><><><><><><><><><><><><><><>\n";
	std::cout<<"Welcome to the Student DataBase!\nPlease choose one of the following options:\nC) Create a new student\nU) Update a current student\nD) Delete a current student\nP) Print the current list of students\nE) Exit the database\n<><><><><><><><><><><><><><><><><><><><>\n";
	std::cin>>a;
	while(leave==false){
	leave2=false;
	leave3=false;
	switch (a){
	case 'C':
	case 'c':
	{std::cout<<"<><><><><><><><><><><><><><><><><><><><>\n";
	std::cout<<"You chose Create\n";
	std::cout<<"<><><><><><><><><><><><><><><><><><><><>\nWhat is the name of the Student?\n";
	std::cin.ignore();
	std::getline(std::cin,n);
	stu.setName(n);
	std::cout<<"What is the major of "<<stu.getName()<<"?\n";
	std::getline(std::cin,n);
	stu.setMajor(n);
	std::cout<<"What month was "<<stu.getName()<<" born? (Enter 1-12 for the months, press -1 to see what each month the numbers represent\n";
	std::cin>>b;
	while(leave2==false){
	switch(b){
	case 1:
	{
	c="January";
	stu.setMonth(c);
	leave2=true;
	break;}
	case 2:
	{c="Feburary";
	stu.setMonth(c);
	leave2=true;
	break;}
	case 3:
	{
	c="March";
	stu.setMonth(c);
	leave2=true;
	break;}
	case 4:
	{c="April";
	stu.setMonth(c);
	leave2=true;
	break;}
	case 5:
	{c="May";
	stu.setMonth(c);
	leave2=true;
	break;}
	case 6:
	{c="June";
	stu.setMonth(c);
	leave2=true;
	break;}
	case 7:
	{c="July";
	stu.setMonth(c);
	leave2=true;
	break;}
	case 8:
	{c="August";
	stu.setMonth(c);
	leave2=true;
	break;}
	case 9:
	{c="September";
	stu.setMonth(c);
	leave2=true;
	break;}
	case 10:
	{c="October";
	stu.setMonth(c);
	leave2=true;
	break;}
	case 11:
	{c="November";
	stu.setMonth(c);
	leave2=true;
	break;}
	case 12:
	{c="December";
	stu.setMonth(c);
	leave2=true;
	break;}
	case -1:
	{std::cout<<"<><><><><><><><><><><><><><><><><><><><>\n1) January\t2) Febuary\t3) March\n4) April\t5) May\t\t6) June\n7) July\t\t8) August\t9) September\n10) October\t11) November\t12) December\nPlease choose a month\n";
	std::cin>>b;
	break;}
	default:
	{std::cout<<"This is an invalid option, please enter 1-12, or -1 to see the menu for the months\n";
	std::cin>>b;
	break;
	}
	}
	std::cout<<"What day was "<<stu.getName()<<" born?\n";
	std::cin>>b;
	int maxDay=0;
	if (stu.getMonth() == "January"||stu.getMonth()=="March"||stu.getMonth()=="May"||stu.getMonth()=="July"||stu.getMonth()=="August"||stu.getMonth()=="October"||stu.getMonth()=="December"){maxDay=31;}
	else if(stu.getMonth()=="April"||stu.getMonth()=="June"||stu.getMonth()=="September"||stu.getMonth()=="November"){maxDay=30;}
	else {maxDay=29;}
	if(b>0&&b>maxDay){std::cout<<"<><><><><><><><><><><><><><><><><><><><>\nThis is an invalid day, please enter a number between 1 and "<<maxDay<<":\n";
	std::cin>>b;}
	else {stu.setDay(b);
	leave3=true;
	}
	std::cout<<"<><><><><><><><><><><><><><><><><><><><>\nWhat year was "<<stu.getName()<<" born?\n";
	std::cin>>b;
	stu.setYear(b);
	}
	list.create(stu);
	std::cout<<"Has "<<stu.getName()<<" taken any courses? (Y/N)\n";
	std::cin>>e;
	while(leave4==false){
	if(e=='N'||e=='n'){leave4=true;}
	else if(e=='Y'||e=='y'){std::cout<<"What courses has "<<stu.getName()<<"taken?\n";
	leave4=true;}
	else{std::cout<<"This is an invalid input, please type Y/N\n";
	std::cin>>e;}
	}
	std::cout<<"<><><><><><><><><><><><><><><><><><><><>\n";
	std::cout<<"Please choose what you want to do next:\nC) Create a new student\nU) Update a current student\nD) Delete a current student\nP) Print the current list of students\nE) Exit the database\n<><><><><><><><><><><><><><><><><><><><>\n";
	std::cin>>a;
	break;
	}
	case 'U':
	case 'u':
	{std::cout<<"<><><><><><><><><><><><><><><><><><><><>\n";
	std::cout<<"You chose Update\n<><><><><><><><><><><><><><><><><><><><>\nPlease enter the name of the student you would like to search for:\n";
	std::cin.ignore();
	std::getline(std::cin,n);
	list.update(n);
	
	std::cout<<"Please choose what you want to do next:\nC) Create a new student\nU) Update a current student\nD) Delete a current student\nP) Print the current list of students\nE) Exit the database\n<><><><><><><><><><><><><><><><><><><><>\n";
        std::cin>>a;
        break;
	}
	case 'D':
	case 'd':
	{std::cout<<"<><><><><><><><><><><><><><><><><><><><>\n";
	std::cout<<"You chose Delete\n<><><><><><><><><><><><><><><><><><><><>\n";
	//list.remove();
	std::cout<<"Please choose what you want to do next:\nC) Create a new student\nU) Update a current student\nD) Delete a current student\nP) Print the current list of students\nE) Exit the database\n<><><><><><><><><><><><><><><><><><><><>\n";
        std::cin>>a;
        break;}
	case 'P':
	case 'p':
	{std::cout<<"<><><><><><><><><><><><><><><><><><><><>\n";
	std::cout<<"You chose Print\n<><><><><><><><><><><><><><><><><><><><>\n";
	list.print();
	std::cout<<"Please choose what you want to do next:\nC) Create a new student\nU) Update a current student\nD) Delete a current student\nP) Print the current list of students\nE) Exit the database\n<><><><><><><><><><><><><><><><><><><><>\n";
        std::cin>>a;
        break;}
	case 'E':
	case 'e':
	{leave=true;
	break;}
	default:
	{std::cout<<"<><><><><><><><><><><><><><><><><><><><>\n";
	std::cout<<"This is an invalid choice\n";
	std::cout<<"<><><><><><><><><><><><><><><><><><><><>\n";
	std::cout<<"Please choose what you want to do next:\nC) Create a new student\nU) Update a current student\nD) Delete a current student\nP) Print the current list of students\nE) Exit the database\n<><><><><><><><><><><><><><><><><><><><>\n";
        std::cin>>a;
        break;}	
	}
	}
	std::cout<<"<><><><><><><><><><><><><><><><><><><><>\n"<<"Thank you for using the Student Database! Have a great day!\n";	

return 0;
	
}


