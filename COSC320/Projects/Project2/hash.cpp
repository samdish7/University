//Project 2 ~> hash.cpp
//Sam Disharoon
#include"hash.h"
#include<algorithm>
#include<stdlib.h>
#include<iomanip>
#include<stdio.h>
#include<sstream>


size_t hash(size_t x){
	int w=31,p=29,a=617;
	long int h=(((a*x)&((1<<w)-1))>>(w-p));
	h=(h/p)*a;
	return h;
}
size_t hash_str(std::string word){
	long int s=0;
	for(int i=0;i<word.length();i++){
		char h=word.at(i);
		s+=int(h);	
	}
	s=hash(s);
	return s;
}
std::string hex(size_t hash){
	std::stringstream a;
	a << std::hex << hash;
	return a.str();
}
