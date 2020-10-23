//StudentDB.cpp
//Sam Disharoon

#include "studentdb.h"
#include <string.h>
#include <iostream>
void StudentDB::create(Student other){
	sNode* cursor=head;
	sNode* newNode=new sNode;
	newNode->next=nullptr;
	newNode->s.setName(other.getName());
	newNode->s.setMajor(other.getMajor());
	newNode->s.setDay(other.getDay());
	newNode->s.setMonth(other.getMonth());
	newNode->s.setYear(other.getYear());


	if(head==nullptr){head=newNode;
	return;}
	else{
	while(cursor->next){
	cursor=cursor->next;}
	cursor->next=newNode;}
}
Student* StudentDB::search(std::string name){
	sNode* cursor=head;
	Student* error;
	
	while (cursor){
	if(name==cursor->s.getName()){
	
	return &cursor->s;
	}
	else
	cursor=cursor->next;
	}
	if(cursor==nullptr)
	{return nullptr;}
}
void StudentDB::print(){
	sNode* cursor=head;
	while(cursor){
	std::cout<<"Name:\t"<<cursor->s.getName()<<"\nMajor:\t"<<cursor->s.getMajor()<<"\nDate of Birth:\t"<<cursor->s.getMonth()<<"/"<<cursor->s.getDay()<<"/"<<cursor->s.getYear()<<"\n<><><><><><><><><><><><><><><><><><><><>\n";
	cursor=cursor->next;}
}
void StudentDB::update(std::string other){
	bool leave2=false;
	char x,b;
	std::string a,c;
	int m=0,d=0,y=0;
	sNode* cursor=head;
	if(search(other)){std::cout<<"What aspect do you want to change about "<<other<<"?\nN) Name\tM) Major\t D) Date of birth\n";
	std::cin>>x;
	switch(x){
	case 'N':
	case 'n':
	{
	std::cout<<"What name did you want to give "<<other<<"?\n";
	std::cin.ignore();
	std::getline(std::cin,a);
	if(cursor->s.getName()==other){cursor->s.setName(a);return;}
	else{cursor=cursor->next;}
	break;}
	case 'M':
	case 'm':
	{std::cout<<"What did you want to change "<<other<<"'s major to?\n";
	std::cin.ignore();
	std::getline(std::cin,a);
	if(cursor->s.getName()==other){cursor->s.setMajor(a);return;}
	else{cursor=cursor->next;}
	break;}
	case 'D':
	case 'd':
	{std::cout<<"What did you want to change "<<other<<"'s date of birth to? Start with the month:\n";
	std::cin>>m;
	if(cursor->s.getName()==other){
 while(leave2==false){
        switch(m){
        case 1:
        {
        c="January";
        cursor->s.setMonth(c);
        leave2=true;
        break;}
        case 2:
        {c="Feburary";
        cursor->s.setMonth(c);
        leave2=true;
        break;}
        case 3:
        {
        c="March";
        cursor->s.setMonth(c);
        leave2=true;
        break;}
        case 4:
        {c="April";
        cursor->s.setMonth(c);
        leave2=true;
        break;}
        case 5:
        {c="May";
        cursor->s.setMonth(c);
        leave2=true;
        break;}
        case 6:
        {c="June";
        cursor->s.setMonth(c);
        leave2=true;
        break;}
	case 7:
        {c="July";
        cursor->s.setMonth(c);
        leave2=true;
        break;}
        case 8:
        {c="August";
        cursor->s.setMonth(c);
        leave2=true;
        break;}
        case 9:
        {c="September";
        cursor->s.setMonth(c);
        leave2=true;
        break;}
        case 10:
        {c="October";
        cursor->s.setMonth(c);
        leave2=true;
        break;}
        case 11:
        {c="November";
        cursor->s.setMonth(c);
        leave2=true;
        break;}
        case 12:
        {c="December";
        cursor->s.setMonth(c);
        leave2=true;
        break;}
        case -1:
        {std::cout<<"<><><><><><><><><><><><><><><><><><><><>\n1) January\t2) Febuary\t3) March\n4) April\t5) May\t\t6) June\n7) July\t\t8) August\t9) September\n10) October\t11) November\t12) December\nPlease choose a month\n";
        std::cin>>m;
        break;}
        default:
        {std::cout<<"This is an invalid option, please enter 1-12, or -1 to see the     menu for the months\n";
        std::cin>>m;
        break;
        }//default bracket
        }// month switch
	}
	std::cout<<"Now input the day:";
	std::cin>>d;
        int maxDay=0;
        if (cursor->s.getMonth() == "January"||cursor->s.getMonth()=="March"||cursor->s.getMonth()=="May"||cursor->s.getMonth()=="July"||cursor->s.getMonth()=="August"||cursor->s.getMonth()=="October"||cursor->s.getMonth()=="December"){maxDay=31;}
        else if(cursor->s.getMonth()=="April"||cursor->s.getMonth()=="June"||cursor->s.getMonth()=="September"||cursor->s.getMonth()=="November"){maxDay=30;}
        else {maxDay=29;}
        if(d>0&&d>maxDay){std::cout<<"<><><><><><><><><><><><><><><><><><><><>\nThis     is an invalid day, please enter a number between 1 and "<<maxDay<<":\n";
        std::cin>>d;}
        else {cursor->s.setDay(d);

	}
	std::cout<<"Now input the Year:";
	std::cin>>y;
	cursor->s.setYear(y);
 	}
	else{cursor=cursor->next;}
	}//case D switch statement
	
	}//update switch statement
	}//if search is true bracket
	else{std::cout<<other<<" is not in the database\n";}

}
