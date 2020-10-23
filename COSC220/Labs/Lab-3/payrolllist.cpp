//PayRollList cpp file
//Sam Disharoon

#include "payrolllist.h"
#include "payroll.h"
#include <iostream>
PayRollList::PayRollList(){
head = nullptr;
}
void PayRollList::insert(std::string n, double r, double h){
	ListNode* cursor=head;

	ListNode* newNode=new ListNode;
	newNode->next = nullptr;

	newNode->p.setName(n);
	newNode->p.setRate(r);
	newNode->p.setHours(h);

	if(head==nullptr){
	head=newNode;
	return;
	}
	while(cursor->next!=nullptr){
	cursor=cursor->next;
	}
	cursor->next=newNode;
}
void PayRollList::printPayChecks(){
	ListNode* cursor=head;
	std::cout << "*************************\n";
	while(cursor){
	std::cout<<"Employee: "<<cursor->p.getName()<<"\n"<<"Pay Rate: $"<<cursor->p.getRate()<<"\n"<<"Total pay this week: $"<<cursor->p.getTotal()<<"\n*************************\n" ;
	cursor=cursor->next;
	}
}
PayRollList::~PayRollList(){
	ListNode* cursor = head;
	while(cursor){
	head = cursor->next;
	delete cursor;
	cursor = head;
	}
}
