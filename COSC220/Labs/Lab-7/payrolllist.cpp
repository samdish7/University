//PayRollList cpp file
//Sam Disharoon

#include "payrolllist.h"
#include "payroll.h"
#include <iostream>
PayRollList::PayRollList(){
	head = nullptr;
	error.setName("ERROR");
	error.setRate(-1);
	error.setHours(0);
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
PayRollList::~PayRollList(){
	ListNode* cursor = head;
	while(cursor){
	head = cursor->next;
	delete cursor;
	cursor = head;
	}
}
PayRoll& PayRollList::search(std::string target){
	return recursiveSearch(head,target);
}
PayRoll& PayRollList::recursiveSearch(PayRollList::ListNode* start, std::string target){
	if(!start){std::cout<<target<< " is not in the list.\n";
	return error;}
	else if(start->p.getName()==target){std::cout<<target<<" is in the list/\n";
	return start->p;}
	else
		return recursiveSearch(start->next,target);
}
void PayRollList::reverse(){
	ListNode* tmp=recursiveReverse(head);
	tmp->next=nullptr;
}
PayRollList::ListNode* PayRollList::recursiveReverse(PayRollList::ListNode* start){
	if(start->next==nullptr){head=start;
	return start;}
	else{
	ListNode* tmp=recursiveReverse(start->next);
	tmp->next=start;
	return start;}
}
