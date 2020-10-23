//PayRollList cpp file
//Sam Disharoon

#include "payrolllist.h"
#include "payroll.h"
#include <iostream>
PayRollList::PayRollList(){
	head = nullptr;
}
PayRollList::PayRollList(const PayRollList& other){
	head=nullptr;
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
PayRollList PayRollList::operator=(PayRollList& other){
	ListNode* cursor=other.head;
	while(cursor){
	insert(cursor->p);
	cursor=cursor->next;
	}
	return other;
}
bool PayRollList::operator<(PayRollList other){
	ListNode* cursor=this->head;
	ListNode* cursor2=other.head;
	double total1=0;
	double total2=0;
	
	if(!cursor)
	{std::cout<<"List 1 is empty.\n\n";return false;}
	else if(!cursor2)
	{std::cout<<"List 2 is empty.\n\n";return false;}
	while(cursor){
	total1+=cursor->p.getTotal();
	cursor=cursor->next;
	}
	std::cout<<"List 1 made a total of $"<<total1<<" this week.\n";
	while(cursor2){
	total2+=cursor2->p.getTotal();
	cursor2=cursor2->next;
	}
	std::cout<<"List 2 made a total of $"<<total2<<" this week.\n";
	if(total1==total2)
	{std::cout<<"They made the same amount.\n\n";return false;}
	return (total1<total2 ? true : false);
}
std::ostream& operator<<(std::ostream& o, const PayRollList& list){
	PayRollList x=list;
	x.printPayChecks();
}
PayRollList::~PayRollList(){
	ListNode* cursor = head;
	while(cursor){
	head = cursor->next;
	delete cursor;
	cursor = head;
	}

}

