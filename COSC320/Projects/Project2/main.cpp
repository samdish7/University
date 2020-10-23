//Project 2 ~> main.cpp
//Sam Disharoon
#include"dictionary.h"
#include<fstream>
#include<iostream>
#include<string>
#include<chrono>
int main(){
	int j=3,i;
	std::string a,w;
	std::system("clear");
	std::cout<<"* * * * * * * *Loading Database* * * * * * * * *\t\t\n";
	Dictionary D;
	std::ifstream file("english.txt");
	auto start=std::chrono::system_clock::now();
	if(file.is_open()){
		while(getline(file,w)){	
		D.insert(w);
		}
	}
	file.close();
	auto end = std::chrono::system_clock::now();
	std::chrono::duration<double> elapsed_seconds=end-start;
	std::time_t end_time = std::chrono::system_clock::to_time_t(end);
	std::cout<<"Loading finished at "<<std::ctime(&end_time)<<"Elapsed time ~> "<<elapsed_seconds.count()<<"s\n\n";
	while(j>0){
		for(int i=0;i<25;i++){
			std::cout<<"**";
		}
		std::cout<<"\n";
		j--;
	}
	std::cout<<"\nInformation about this database:\n";
	for(int i=0;i<25;i++){
		std::cout<<"\033[0;36m"<<"<>"<<"\033[0m";
	}
	std::cout<<"\nTotal words ~> "<<D.getnumWords()<<"\n";
	std::cout<<"Largest Bucket size ~> "<<D.getLarge()<<"\n";
	std::cout<<"Smallest Bucket size ~> "<<D.getSmall()<<"\n";
	std::cout<<"Total number of buckets ~> "<<D.getnumBuckets()<<"\n";
	std::cout<<"Number of used buckets ~> "<<D.getusedBuckets()<<"\n";
	std::cout<<"Average number of words in each bucket ~> "<<D.getAve()<<"\n";
	for(int i=0;i<25;i++){
		std::cout<<"\033[0;36m"<<"<>"<<"\033[0m";
	}
	//D.print();
	std::cout<<"\n\nPlease enter some text: \n";
	getline(std::cin,a);
	std::cout<<"You entered ~> "<<a<<"\n";
	for(int i=0;i<25;i++){
		std::cout<<"\033[0;36m"<<"<>"<<"\033[0m";
	}
	std::cout<<"\n";
	i=D.seperate(a);
	for(int i=0;i<25;i++){
		std::cout<<"\033[0;36m"<<"<>"<<"\033[0m";
	}
	std::cout<<"\n";
	std::cout<<"You misspelled ~> "<<"\033[1;031m"<<i<<"\033[0m"<<" words!\n";
	return 0;
}
