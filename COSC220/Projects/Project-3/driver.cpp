//Driver class
//Sam Disharoon
#include <iostream>
#include <string>
#include "SUList.h"
#include "SUList.cpp"
#include "SUQueue.h"
#include "SUStack.h"
int main(){
	SUList<int> s1;
	SUList<int> s2;
	s1.getFront();
	s1.getBack();
	s1.putBack(1);
	s1.putBack(2);
	s1.putBack(3);
	s1.putBack(4);
	s1.putBack(5);
	s1.putBack(6);
	s1.putBack(7);
	s1.putBack(8);
	s1.putFront(122);
	s1.print();
	std::cout<<"I GOT BACK "<<s1.getBack()<<"\n====\n";
	std::cout<<"I GOT BACK "<<s1.getBack()<<"\n====\n";
	std::cout<<"I GOT Front "<<s1.getFront()<<"\n====\n";
	std::cout<<"I GOT Front "<<s1.getFront()<<"\n====\n";
	s1.print();
	if(s1.contains(2)){std::cout<<"FOUND!\n";}
	else {std::cout<<"HELL NAW!\n";}
return 0;
}
