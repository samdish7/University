//Course.cpp file
//Sam Disharoon
#include "course.h"

void Course::setName(std::string n){name=n;}
std::string Course::getName()const{return name;}
void Course::setDep(std::string d){dep=d;}
std::string Course::getDep()const{return dep;}
void Course::setSem(std::string s){sem=s;}
std::string Course::getSem()const{return sem;}
void Course::setGrade(char g){grade=g;}
char Course::getGrade()const{return grade;}
