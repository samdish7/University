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
	PayRoll error;
	ListNode* head;
	PayRoll& recursiveSearch(ListNode*,std::string);
	ListNode* recursiveReverse(ListNode*);
	public:
	PayRollList();
	~PayRollList();
	PayRollList(const PayRollList&);
	
	PayRoll& search(std::string);
	void reverse();
	int length();
	void insert(std::string, double, double);
	void insert(PayRoll);
	void printPayChecks();

};

#endif
