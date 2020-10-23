//SUList cpp file
//Sam Disharoon
#include<iostream>
#include"SUList.h"
template<class DataType>
SUList<DataType>::SUList(){head=nullptr;tail=nullptr;}
template<class DataType>
SUList<DataType>::SUList(const SUList& other){
	head=nullptr;
	ListNode* cursor=other.head;
	while(cursor){
	putBack(cursor->data);
	cursor=cursor->next;
	}
}
template<class DataType>
SUList<DataType>::~SUList(){
	ListNode* cursor=head;
	while(cursor){
	cursor=cursor->next;
	delete head;
	head=cursor;
	}
}
template<class DataType>
SUList<DataType>& SUList<DataType>::operator=(const SUList<DataType>& other){
	ListNode* cursor=head;
	while(cursor){
	cursor=cursor->next;
	delete head;
	head=cursor;
	}
	cursor=other.head;
	while(cursor){
	putBack(cursor->data);
	cursor=cursor->next;
	}
	return *this;	
}
template<class DataType>
DataType SUList<DataType>::getFront(){
	if(head){
	ListNode* cursor=head;
	DataType tmp=head->data;
	head=head->next;
	delete cursor;
	return tmp;
	}
	else{std::cout<<"Nothing to return\n";}
}
template<class DataType>
DataType SUList<DataType>::getBack(){
	if(head){
	DataType tmp;
	ListNode* cursor=head;
	ListNode* cursor2=head;
	tmp=tail->data;
	while(cursor->next){
	cursor2=cursor;
	cursor=cursor->next;
	}
	delete cursor;
	tail=cursor2;
	tail->next=nullptr;
	return tmp;
	}
	else{std::cout<<"Nothing to return!\n";}
	
}
template<class DataType>
void SUList<DataType>::putFront(const DataType& x){
	ListNode* newNode=new ListNode;
	ListNode* cursor=head;
	newNode->next=nullptr;
	newNode->data=x;
	if(!cursor){
	head=newNode;
	tail=newNode;
	return;
	}
	newNode->next=head;
	head=newNode;
}
template<class DataType>
void SUList<DataType>::putBack(const DataType& x){
	ListNode* newNode=new ListNode;
	ListNode* cursor=head;
	newNode->next=nullptr;
	newNode->data=x;
	if(!cursor){
	head=newNode;
	tail=newNode;
	return;
	}
	tail->next=newNode;
	tail=newNode;
}
template<class DataType>
int SUList<DataType>::size()const{
	ListNode* cursor=head;
	int count=0;
	while(cursor){
	count++;
	cursor=cursor->next;
	}
	return count;
}
template<class DataType>
bool SUList<DataType>::contains(const DataType& x){
	ListNode *cursor=head;
	while(cursor){
	if(cursor->data==x){
	return true;}
	else{
	cursor=cursor->next;
	}
	return false;
	}
}
template<class DataType>
void SUList<DataType>::print(){
	ListNode* cursor=head;
	while(cursor)
	{std::cout<<cursor->data<<"\n";
	cursor=cursor->next;
	}
	
}
