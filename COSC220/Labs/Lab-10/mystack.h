//mystack.h
//Sam Disharoon
#ifndef MYSTACK_H
#define MYSTACK_H
#include "payroll.h"
template<class T>
class MyStack{
	private:
	struct Node{
		T data;
		Node* next;
	};
	Node* head;
	void insert(T);

	public:
	MyStack(){head=nullptr;}
	~MyStack();
	MyStack(const MyStack&);
	MyStack operator=(const MyStack&);
		

	void push(T);
	T pop();
	T peek();
	void print();
};
#include "mystack.cpp"
#endif
