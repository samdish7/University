//PayRollList cpp file
//Sam Disharoon

#include "payrollstack.h"
#include "payroll.h"
#include <iostream>
PayRollStack::PayRollStack(){
head = nullptr;
}
PayRollStack::PayRollStack(const PayRollStack& other){
	StackNode* cursor=other.head;
	while(cursor){
	push(cursor->p);
	cursor=cursor->next;
	}
}
/*PayRollStack PayRollStack::operator=(const PayRollStack& other){
	StackNode* cursor=head;
	other->p.setName(cursor->p.getName());
	other->p.setRate(cursor->p.getRate());
	other->p.setHours(cursor->p.getHours());

}*/
void PayRollStack::push(PayRoll pay){
	StackNode* node = new StackNode;
	node->next=head;
	head=node;
	
	node->p=pay;
}
PayRoll PayRollStack::pop(){
	StackNode* node=nullptr;
	StackNode* tmp=head;
	PayRoll pay;
	head=head->next;
	pay=tmp->p;
	tmp->p.printCheck();
	delete tmp;
	return pay;
}
int PayRollStack::size(){
	StackNode* cursor=head;
	int count=0;

	while(cursor){
	cursor=cursor->next;
	count++;
	}
	return count;
}
void PayRollStack::printPayChecks(){
	StackNode* cursor=head;
	while(cursor){
	cursor->p.printCheck();
	cursor=cursor->next;
	}
}
PayRollStack::~PayRollStack(){
	StackNode* cursor = head;
	while(cursor){
	head = cursor->next;
	delete cursor;
	cursor = head;
	}
}
