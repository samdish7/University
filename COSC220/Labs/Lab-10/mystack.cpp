//mystack.cpp
//Sam Disharoon
#include<string>
#include<iostream>
#include"mystack.h"
#include"payroll.h"
template<class T>
MyStack<T>::MyStack(const MyStack& other){
	head=nullptr;
	Node* cursor=other.head;
	while(cursor){
	insert(cursor->data);
	cursor=cursor->next;
	}
}
template<class T>
MyStack<T> MyStack<T>::operator=(const MyStack& other){
	Node* cursor=head;
	while(cursor){
	head=head->next;
	delete cursor;
	cursor=head;
	}
	cursor=other.head;
	while(cursor){
	insert(cursor->data);
	cursor=cursor->next;
	}
	return *this;
}
template<class T>
MyStack<T>::~MyStack(){
	Node* cursor=head;
	while(cursor){
	head=cursor->next;
	delete cursor;
	cursor=head;
	}
}
template<class T>
void MyStack<T>::insert(T x){
	Node* cursor=head;
	if(head==nullptr){
	Node* n = new Node;
	n->data=x;
	n->next=nullptr;
	head=n;
	return;
	}
	while(cursor->next){
	cursor=cursor->next;
	}
	Node* n= new Node;
	n->data=x;
	n->next=nullptr;
	cursor->next=n;
}
template<class T>
void MyStack<T>::push(T k){
	Node* x = new Node;
	x->next=head;
	x->data=k;
	head=x;
}
template<class T>
T MyStack<T>::pop(){
	Node* x=head;
	T stuff;
	stuff=x->data;
	head=head->next;
	delete x;
	return stuff;
}
template<class T>
T MyStack<T>::peek(){
	return head->data;
}
template<class T>
void MyStack<T>::print(){
	Node* cursor=head;
	while(cursor){
		std::cout<<cursor->data<<"\n";
		cursor=cursor->next;
	}

}
