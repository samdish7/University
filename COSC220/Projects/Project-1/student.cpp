//Student.cpp file
//Sam Disharoon

#include "student.h"

void Student::setName(std::string n){name=n;}
std::string Student::getName()const{return name;}
void Student::setMajor(std::string m){major=m;}
std::string Student::getMajor()const{return major;}
void Student::setMonth(std::string m){month=m;}
std::string Student::getMonth()const{return month;}
void Student::setDay(int d){day=d;}
int Student::getDay()const{return day;}
void Student::setYear(int y){year=y;}
int Student::getYear()const{return year;}
void Student::createC(std::string n,std::string d,std::string s,char g){
	cNode* cursor=headC;
	cNode* newCourse=new cNode;
	newCourse->nextC=nullptr;

	newCourse->c.setName(n);
	newCourse->c.setDep(d);
	newCourse->c.setSem(s);
	newCourse->c.setGrade(g);

	if(headC==nullptr){headC=newCourse;
	return;}
	else{
	while(cursor){
	cursor=cursor->nextC;
	cursor->nextC=newCourse;}
	}
}

