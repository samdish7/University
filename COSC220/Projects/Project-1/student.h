//Student.h file
//Sam Disharoon

#ifndef STUDENT_H
#define STUDENT_H
#include <string>
#include "course.h"
	class Student{
		private:
		std::string name;
		std::string major;
		std::string month;
		int day;
		int year;
		struct cNode{
		cNode* nextC;
		Course c;
		};
		cNode* headC;

		Course* search(std::string);
		
		public: 
		void updateC();
		void createC(std::string,std::string,std::string,char);
		void removeC();
		
		void setName(std::string);
		std::string getName() const;
		void setMajor(std::string);
		std::string getMajor() const;
		void setMonth(std::string);
		std::string getMonth() const;
		void setDay(int);
		int getDay() const;
		void setYear(int);
		int getYear() const;
};
#endif

