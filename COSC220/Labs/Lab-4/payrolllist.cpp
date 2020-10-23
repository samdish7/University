//PayRollList cpp file
//Sam Disharoon

#include "payrolllist.h"
#include "payroll.h"
#include <iostream>
PayRollList::PayRollList(){
	head = nullptr;
}
PayRollList::PayRollList(const PayRollList& other){
	ListNode* cursor=other.head;
	while(cursor){
	insert(cursor->p);
	cursor=cursor->next;

	}
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
	while(cursor->next){
	cursor=cursor->next;
	}
	cursor->next=newNode;
}
void PayRollList::insert(PayRoll pay){
	insert(pay.getName(),pay.getRate(),pay.getHours());
}
void PayRollList::printPayChecks(){
	ListNode* cursor=head;
	std::cout << "*************************\n";
	if(cursor == nullptr){
	std::cout << "HEAD IS NULL!\n";
	}
	while(cursor){
	std::cout<<"Employee: "<<cursor->p.getName()<<"\n"<<"Pay Rate: $"<<cursor->p.getRate()<<"\n"<<"Total pay this week: $"<<cursor->p.getTotal()<<"\n*************************\n" ;
	cursor=cursor->next;
	}

}
int PayRollList::length(){
	ListNode* cursor=head;
	int count=0;
	
	while(cursor){
	cursor=cursor->next;
	count++;
	}
	return count;
}
PayRoll& PayRollList::operator[](int index){
	ListNode* cursor=head;
	for(int i=0; i<index;i++){
	if(cursor){
	cursor=cursor->next;
	}
	}
	if(cursor==nullptr||index<0){
	}
	return cursor->p;
}
void PayRollList::remove(int x){
	ListNode* cursor=head;
	int count = 0;
	while(cursor){
	
	}

}
void PayRollList::assign(int, PayRoll){

}
void PayRollList::insert(int x, PayRoll pay){
	//insert(pay[x]); DOESN'T WORK, NOT SURE WHY
}

PayRollList::~PayRollList(){
	ListNode* cursor = head;
	while(cursor){
	head = cursor->next;
	delete cursor;
	cursor = head;
	}
}

