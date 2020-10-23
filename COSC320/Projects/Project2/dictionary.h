//Project 2 ~> dictionary.h
//Sam Disharoon

#ifndef DICTIONARY_H
#define DICTIONARY_H
#include<string>
class Dictionary{
	private:
		struct Node{
			Node* prev;
			Node* next;
			std::string word;
			std::string key;
	};
		Node** table;
		int used,totalWord,totalBuckets;
		
	public:
		std::string upper[26]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		std::string lower[26]={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		//ctors & rule of 3
		Dictionary();
		Dictionary(const Dictionary&);
		~Dictionary();
		Dictionary& operator=(const Dictionary&);
		//Functions
		void insert(std::string);
		bool search(std::string);
		int seperate(std::string);
		int checker(std::string);
		void one_edit_dis(std::string);
		void two_edit_dis(std::string);
		//Get info about the table
		int getnumWords();
		int getnumBuckets();
		int getusedBuckets();
		int getLarge();
		int getSmall();
		int getAve();
		void print();
		
};

#endif
