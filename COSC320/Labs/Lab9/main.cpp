//Lab 9 ~> main.cpp
//Sam Disharoon
#include"RBtree.h"
#include<iostream>
#include<chrono>
#include<time.h>
int main(int argc, char** argv){
	std::system("clear");
	int x=0;
	srand(time(NULL));
	//making the first redblack tree
	auto start=std::chrono::system_clock::now();
	RBtree R(50);
	R.insert(5);
	R.insert(500);
	R.insert(54);
	R.insert(745);
	R.insert(1);
	auto end=std::chrono::system_clock::now();
        std::chrono::duration<double> elapsed_seconds=end-start;
        std::time_t end_time=std::chrono::system_clock::to_time_t(end);
        std::cout<<"Insertion finished at "<<std::ctime(&end_time);
        std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";
	R.printIO();
	std::cout<<"\n";
	R.search(5);
	R.search(65);
	R.search(500);
	R.removeHelper(5);
	R.printIO();
	std::cout<<"\n";
	R.search(5);
	RBtree R2(49);
	//MAKING THE 2ND TREE
	//INSERT 1000 NODES into another tree
	/*start=std::chrono::system_clock::now();
	for(int i=0;i<1000;i++){
		std::cout<<i<<"\n";
		x=rand()%10000+1;
		R2.insert(x);
	}						//THIS SEGFAULTS AGAIN AND I DON'T KNOW WHY!!!
	end=std::chrono::system_clock::now();
        elapsed_seconds=end-start;
        end_time=std::chrono::system_clock::to_time_t(end);
        std::cout<<"Insertion finished at "<<std::ctime(&end_time);
        std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";
	R2.printIO();
	*/
	
	//Making a thrid tree to let the user decide on the input
	RBtree R3(41);//The tree will start with root at 1;
	bool a=true;
	while (a){
		std::cout<<"Enter a number (-1 to quit,-2 to print in order)\n~> ";
		std::cin>>x;
		if(x==-1){
			a=false;
		}
		else if(x==-2){
			R3.printIO();
			std::cout<<"\n";
		}
		else if(x<-2){
			std::cout<<"Positive numbers only please!\n";
			std::cin>>x;
		}
		else{
		R3.insert(x);
		}
	}
	std::cout<<"Goodbye!\n";
return 0;
}
