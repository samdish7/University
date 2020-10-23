//Main.cpp
//Lab5
//Sam Disharoon
#include"matrix.h"
#include<iostream>
#include<chrono>
int main(int argc, char** argv){
	long unsigned int r,c;

	std::cout<<"How many rows?\n";
	std::cin>>r;
	std::cout<<"How many columns?\n";
	std::cin>>c;
	Matrix A1(r,c);
	A1.print();
	std::cout<<"====\n";
	Matrix A2;
	A2=A1;//Shows off assignment operator
	A2.print();
	std::cout<<"====\n";
	Matrix A3=A1;//Shows off copy ctor
	std::cout<<"Adding 2 matricies\n";
	A3.add(A1,A2);
	//A3.print();
	Matrix A4;
	std::cout<<"Multiplying 2 matricies\n";
	A4.mul(A1,A2);
	A4.print();
	Matrix A5;
	std::cout<<"Subtracting 2 matricies\n";
	A5.sub(A4,A2);
	//A5.print();


return 0;
}
