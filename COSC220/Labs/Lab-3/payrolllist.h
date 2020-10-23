//PayRollList header file
//Sam Disharoon

#ifndef PAYROLLLIST_H
#define PAYROLLLIST_H

#include <string>
#include "payroll.h"
class PayRollList{
	private:
	struct ListNode {
	PayRoll p;
	ListNode* next;
	};

	ListNode* head;

	public:
	PayRollList();
	~PayRollList();

	void insert(std::string, double, double);
	void printPayChecks();

};

#endif
