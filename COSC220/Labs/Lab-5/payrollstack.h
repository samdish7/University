//PayRollList header file
//Sam Disharoon

#ifndef PAYROLLSTACK_H
#define PAYROLLSTACK_H

#include <string>
#include "payroll.h"
class PayRollStack{
	private:
	struct StackNode {
	PayRoll p;
	StackNode* next;
	};

	StackNode* head;

	public:
	PayRollStack();
	~PayRollStack();
	PayRollStack(const PayRollStack&);
	PayRollStack operator=(const PayRollStack&);

	void push(PayRoll p);
	PayRoll pop();
	int size();
	void printPayChecks();

};

#endif
