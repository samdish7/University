//Course.h file
//Sam Disharoon

#ifndef COURSE_H
#define COURSE_H
#include <string>

	class Course{
		private:
		std::string name;
		std::string dep;
		std::string sem;
		char grade;

		public:
		void setName(std::string);
		std::string getName() const;
		void setDep(std::string);
		std::string getDep() const;
		void setSem(std::string);
		std::string getSem() const;
		void setGrade(char);
		char getGrade() const;
		
	};
#endif
