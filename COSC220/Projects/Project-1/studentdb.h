//StudentDB.h file
//Sam Disharoon
#include "student.h"
#ifndef STUDENTDB_H
#define STUDENTDB_H
	class StudentDB{
		private:
		struct  sNode{
		sNode* next;
		Student s;
		};
		sNode* head;
		
		Student* search(std::string);
		public:
		StudentDB(){head=nullptr;}

		void update(std::string);
		void remove();
		void create(Student);
		void print();

};
#endif
