//Project 1
//Main.cpp
//Sam Disharoon
#include"matrix.h"
#include<iostream>

int main(int argc, char** argv){
	long unsigned int r,c;

        std::cout<<"How many rows?\n";
        std::cin>>r;
        std::cout<<"How many columns?\n";
        std::cin>>c;
        Matrix A1(r,c);
	A1.fill();
	std::cout<<"Matrix 1:\n";
	A1.print();
//******************************************************************	
	Matrix A2;
	A2=A1;
	std::cout<<"\n\nNow to show off = operator\nMatrix 2:\n";
	A2.print();
//******************************************************************
	Matrix A3=A1;
	std::cout<<"\n\nNow to show off copy ctor\nMatrix 3:\n";
	A3.print();
//******************************************************************
	Matrix A4=A1+A2;
	std::cout<<"\n\nNow adding A1 and A2\nMatrix 4:\n";
	A4.print();
//*******************************************************************
	Matrix A5=(A1*2);
	std::cout<<"\n\nMultiplying A1 by a scalar\nMatrix 5:\n";
	A5.print();
return 0;
}
