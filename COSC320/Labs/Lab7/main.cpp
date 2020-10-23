//Lab7
//main.cpp
//Sam Disharoon

#include<iomanip>
#include<iostream>
#include<algorithm>
#include<stdlib.h>
#include<string.h>
#include<math.h>
#include<sstream>
int str_to_int(std::string c){
	int i=0,n=0;

	while(c[i]!='\0'){
	n=n*10+(c[i]-'0');
	i++;
	}
	return n;
}
size_t hash(size_t x){

	int w=31,p=29,a=70901;
	
	long long int h=(((a*x)&((1<<w)-1))>>(w-p));
	h = (h/p)*a;
	return h;
}
size_t hash_str1(std::string str){
	long int s=0;
	for(int i=0;i<str.length();i++){
	char h=str.at(i);
	s+=int(h);	
	}
	s=hash(s);
	return s;
}
size_t hash_str2(std::string str){
	long int s=0;
	for(int i=0;i<str.length();i++){
	char h=str.at(i);
	s+=int(h);
	s=s/2;
	}
	s=hash(s);
	return s;
}
std::string hex(size_t n){
	std::stringstream a;
	a << std::hex << n;
	return a.str();
}
int main(int argc, char** argv){
	std::string n,a,b;

	//std::cout<<"Pick a number and I will give you a hash value for it\n";
	//std::cin>>num;
	for(int i=0;i<20;i++){
	std::cout<<"Hash of the int "<<i<<": "<<hex(hash(i))<<std::endl;
	}

	std::cout<<"Enter a word\n";
	std::getline(std::cin,n);
	std::cout<<"-> "<<hash_str1(n)<<"\n";
	std::cout<<"Hash of the word is\n"<<n<<":"<<hex(hash_str1(n))<<"\n";
	std::cout<<"Here is another hash using a different function\n"<<n<<":"<<hex(hash_str2(n))<<"\n";
return 0;
}
