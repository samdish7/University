//main.cpp
//Sam Disharoon
#include<iostream>
#include<string>
#include "payroll.h"
#include "mystack.h"
int main(){
	MyStack<int> intStack;
	MyStack<std::string> strStack;
	std::cout<<"Now creating a stack of Ints...\n";
	std::cout<<"Now pushing some numbers in the stack...\n";
	intStack.push(1);
	intStack.push(3);
	intStack.push(4);
	std::cout<<"The stack is currently:\n";
	intStack.print();
	std::cout<<"Now to pop a value off of the stack...\n";
	intStack.pop();
	std::cout<<"Now to peek at the current top value of the stack...\n";
	std::cout<<intStack.peek();
	std::cout<<"\nNow to print the entire stack...\n";
	intStack.print();
	MyStack<int> intStack2=intStack;
	std::cout<<"Now to copy a stack...\n";	
	intStack2.print();	
	MyStack<int> intStack3;
	intStack3=intStack;
	std::cout<<"Now to copy another stack...\n";
	intStack3.print();
	std::cout<<"\n\nPushing strings into a stack of strings...\n";
	strStack.push("Bob");
	strStack.push("Stuff");
	strStack.push("~~~");
	std::cout<<"The contents of the string stack are:\n";
	strStack.print();
	std::cout<<"Now to pop an item off...\n";
	strStack.pop();
	std::cout<<"Let us peek onto the stack to see what is on top now...\n"<<strStack.peek()<<"\nThe stack now contains:\n";
	strStack.print();
	std::cout<<"\n\nPushing PayRoll objects into a stack of PayRolls...\n";
	PayRoll pay={"Bob",4.50,40};
	PayRoll pay2={"Sam",7.50,40};
	PayRoll pay3={"YAAAAAAAA",2.32,40};
	MyStack<PayRoll> payStack;
	payStack.push(pay);
	payStack.push(pay2);
	payStack.push(pay3);
	std::cout<<"This stack contains:\n";
	payStack.print();
	std::cout<<"Popping...\n";
	payStack.pop();
	std::cout<<"We shall peek again...\n"<<payStack.peek()<<"\nNow to see the entire stack...\n";
	payStack.print();


return 0;
}
