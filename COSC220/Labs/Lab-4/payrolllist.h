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
	PayRollList(const PayRollList&);
	
	
	int length();
	PayRoll& operator[](int);
	void remove(int);
	void assign(int, PayRoll);
	void insert(std::string, double, double);
	void insert(PayRoll);
	void insert(int, PayRoll);
	void printPayChecks();

};

#endif
