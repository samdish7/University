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
	
	bool operator<(PayRollList);
	PayRollList operator=(PayRollList&);	
	int length();
	void insert(std::string, double, double);
	void insert(PayRoll);
	void printPayChecks();
	friend std::ostream& operator<<(std::ostream&, const PayRollList&);
};

#endif
