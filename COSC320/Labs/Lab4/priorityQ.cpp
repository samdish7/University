//Sam Disharoon
//priorityQ.cpp
#include"priorityQ.h"
#include<iostream>
#include<algorithm>
#include<limits>
#include<string>
HeapQ::HeapQ(){
	length=10;
	arr=new Heapobj[length];
	heap_size=0;
		
}
int HeapQ::parent(int i){return i/2;}
int HeapQ::left(int i){return 2*i;}
int HeapQ::right(int i){return (2*i)+1;}
/*std::string HeapQ::extract_Max(HeapQ A){
	std::string tmp=A[0];
	A[0]=A[A.heap_size];
	MaxHeapify(A,1);
	return tmp;
}*/
/*std::string HeapQ::peek(){
	return arr[0];
}*/
void HeapQ::insert(std::string n, int i){
	heap_size=heap_size+1;
	arr[heap_size].name=n;
	arr[heap_size-1].key=-1;
	increase_key(heap_size-1,i);
}
void HeapQ::increase_key(int i, int k){
	arr[i].key=k;
	while(arr[i].key>arr[parent(i)].key){
	Heapobj tmp = arr[i];
	arr[i]=arr[parent(i)];
	arr[parent(i)] = tmp;
	i=parent(i);
	}
}
void HeapQ::print(){
	for(int i=0;i<heap_size;i++){
		std::cout<<arr[i].name<<" is number "<<arr[i].key<<"\n";
       	}
}
