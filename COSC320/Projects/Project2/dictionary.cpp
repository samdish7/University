//Project 2 -> dictionary.cpp
//Sam Disharoon
#include"dictionary.h"
#include"hash.h"
#include<chrono>
#include<iostream>
#include<fstream>
#include<sstream>
#include<string.h>
#include<stdio.h>
static int num_sugg;
Dictionary::Dictionary(){
	used=0;
	totalWord=0;
	totalBuckets=8000;
	table= new Node*[totalBuckets];
	for(int i=0;i<totalBuckets;i++){
		table[i]=nullptr;
	}
}
Dictionary::~Dictionary(){
	for(int i=0;i<totalBuckets;i++){
		Node* cursor=table[i];
		Node* p;
		while(cursor){
			p=cursor;
			cursor=cursor->next;
			delete p;
		}
	}
	delete[] table;
}
int Dictionary::getnumWords(){
	return totalWord;
}
int Dictionary::getnumBuckets(){
	return totalBuckets;
}
int Dictionary::getusedBuckets(){
	return used;
}
int Dictionary::getLarge(){
	int large=0;
	for(int i=0;i<totalBuckets;i++){
		int count=0;
		Node* cursor=table[i];
		while(cursor){
			count++;
			cursor=cursor->next;
		}
		if(count>large){
			large=count;
		}
	}
	return large;
}
int Dictionary::getSmall(){
	int small=getLarge();
	for(int i=0;i<totalBuckets;i++){
		int count=0;
		Node* cursor=table[i];
		while(cursor){
			count++;
			cursor=cursor->next;
		}
		if(count!=0&&count<small){
			small=count;
		}
	}
	return small;
}
int Dictionary::getAve(){
	int ave=totalWord/used;
	return ave;
}
void Dictionary::insert(std::string w){
	Node* newNode = new Node;
	newNode->next=nullptr;
	newNode->prev=nullptr;
	newNode->word=w;
	newNode->key=hex(hash_str(w));
	int index=hash_str(w)%8000;
	//std::cout<<"Hash of the word "<<newNode->word<<" ~>"<<newNode->key<<" the index is ~> "<<index<<"\n";
	if(table[index]==nullptr){	//if table[index] is empty, make newNode the head and increment the used variable
		table[index]=newNode;
		totalWord++;
		used++;
	}
	else{
		Node* cursor;
		Node* p;
		cursor=table[index];
	
		while(cursor->next){
			p=cursor;
			cursor=cursor->next;
		}
		cursor->next=newNode;
		newNode->prev=p;
		totalWord++;
	}
}
bool Dictionary::search(std::string target){
	bool found=false;
	for(int i=0;i<totalBuckets;i++){
		Node* cursor=table[i];
		while(cursor){
			if(!cursor->word.compare(target)){
				found=true;
			}
				cursor=cursor->next;
		}
	}
	return found;
}
void Dictionary::print(){
	for(int i=0;i<totalBuckets;i++){
	Node* cursor=table[i];
	std::cout<<i<<"~>";
	while(cursor){
		std::cout<<"*";
		cursor=cursor->next;
	}
	std::cout<<"\n";
	}
	
}
int Dictionary::seperate(std::string w){
	std::istringstream ss(w);
	int i=0;
	while(ss){
		std::string x;
		ss>>x;
		i+=checker(x);
	}
	return i;
}
int Dictionary::checker(std::string w){
	std::string sugg;
	int num_wrong=0;
	auto start=std::chrono::system_clock::now();
	if(!search(w)&&w!=""){
		std::cout<<"\033[1;31m"<<w<<"\033[0m"<<" is an invalid word!\nHere are a list of possible words within one edit distance:\n";
		num_wrong++;
		one_edit_dis(w);
		std::cout<<"\nWithin two edit distances:\n";
		two_edit_dis(w);
		if(num_sugg==0){
			std::cout<<"Wow, you really botched that word eh?\n";
		}
		else{
			std::cout<<"\nNumber of suggestions ~> "<<num_sugg<<"\n";
		}
		for(int x=0;x<25;x++){
			std::cout<<"\033[0;36m"<<"<>"<<"\033[0m";
		}
		std::cout<<"\n";
		auto end = std::chrono::system_clock::now();
		std::chrono::duration<double> elapsed_seconds=end-start;
		std::time_t end_time = std::chrono::system_clock::to_time_t(end);
		std::cout<<"Searching for suggestions finished at ~> "<<std::ctime(&end_time)<<"Elapsed time ~> "<<elapsed_seconds.count()<<"s\n\n";
		for(int x=0;x<25;x++){
			std::cout<<"\033[0;36m"<<"<>"<<"\033[0m";
		}
		std::cout<<"\n";
	}
	return num_wrong;

	
}
void Dictionary::one_edit_dis(std::string w){
	std::string sugg;
	for(int x=0;x<25;x++){
		std::cout<<"\033[0;36m"<<"<>"<<"\033[0m";
	}
	std::cout<<"\nSuggestions for "<<w<<" ~> ";
	for(int i=0;i<w.length();i++){
		std::string tmp=w;
		for(int j=0;j<26;j++){
			std::string tmp2=tmp.replace(i,1,lower[j]);
			sugg=tmp2;
			if(w!=sugg&&search(sugg)){
				num_sugg++;
				std::cout<<" *"<<sugg<<"* ";
			}
		}
	}
	for(int i=0;i<w.length();i++){
		std::string tmp=w;
		for(int j=0;j<26;j++){
			std::string tmp2=tmp.replace(i,1,upper[j]);
			sugg=tmp2;
			if(w!=sugg&&search(sugg)){
				num_sugg++;
				std::cout<<" *"<<sugg<<"* ";
			}
		}
	}
	for(int i=0;i<w.length();i++){
		std::string tmp=w;
		for(int j=0;j<26;j++){
			tmp.insert(i,lower[j]);
			sugg=tmp;
			if(w!=sugg&&search(sugg)){
				num_sugg++;
				std::cout<<" *"<<sugg<<"* ";
			}
		}
	}
	for(int i=0;i<w.length();i++){
		std::string tmp=w;
		for(int j=0; j<26;j++){
			tmp.insert(i,upper[j]);
			sugg=tmp;
			if(w!=sugg&&search(sugg)){
				num_sugg++;
				std::cout<<" *"<<sugg<<"* ";
			}
		}
	}
	for(int i=0;i<w.length();i++){
		std::string tmp=w;
		tmp.erase(tmp.begin()+i);
		sugg=tmp;
		if(w!=sugg&&search(sugg)){
			num_sugg++;
			std::cout<<" *"<<sugg<<"* ";
		}
		
	}
	for(int i=0;i<w.length();i++){
		std::string tmp=w;
		std::swap(tmp[i],tmp[i+1]);
		sugg=tmp;
		if(w!=sugg&&search(sugg)){
			num_sugg++;
			std::cout<<" *"<<sugg<<"* ";
		}

	}
	std::cout<<"\n";
	for(int x=0;x<25;x++){
		std::cout<<"\033[0;36m"<<"<>"<<"\033[0m";
	}

}
void Dictionary::two_edit_dis(std::string w){
	std::string sugg;
	std::cout<<"Here are a list of possible suggestions:\n";
	for(int i=0;i<w.length();i++){
		std::string tmp=w;
		for(int j=0;j<26;j++){
			tmp.replace(i,1,lower[j]);
			sugg=tmp;
			if(search(sugg)){
				one_edit_dis(sugg);
			}
		}
	}
	for(int i=0;i<w.length();i++){
		std::string tmp=w;
		for(int j=0;j<26;j++){
			tmp.replace(i,1,upper[j]);
			sugg=tmp;
			if(search(sugg)){
				one_edit_dis(sugg);
			}
		}
	}
	for(int i=0;i<w.length();i++){
		std::string tmp=w;
		for(int j=0;j<26;j++){
			tmp.insert(i,lower[j]);
			sugg=tmp;
			if(search(sugg)){
				one_edit_dis(sugg);
			}
		}
	}
	for(int i=0;i<w.length();i++){
		std::string tmp=w;
		for(int j=0; j<26;j++){
			tmp.insert(i,upper[j]);
			sugg=tmp;
			if(search(sugg)){
				one_edit_dis(sugg);
			}
		}
	}
	for(int i=0;i<w.length();i++){
		std::string tmp=w;
		tmp.erase(tmp.begin()+i);
		sugg=tmp;
		if(search(sugg)){
			one_edit_dis(sugg);
		}
		
	}
	for(int i=0;i<w.length();i++){
		std::string tmp=w;
		std::swap(tmp[i],tmp[i+1]);
		sugg=tmp;
		if(search(sugg)){
			one_edit_dis(sugg);
		}

	}
}
