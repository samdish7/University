//sort.cpp
//Sam Disharoon

#include "sort.h"
#include <chrono>
#include <iostream>

int count;

void Sort::swap(int& a, int& b){
	int tmp=0;
	tmp = a;
	a = b;
	b = tmp;
}
bool Sort::isSorted(int arr[],int l){
	for(int i=0;i<l-1;i++){
	if(arr[i]<=arr[i+1]){
	i++;}
	else{return false;}

	}
	return true;
}
void Sort::bubbleSort(int arr[], int l){
	count=0;
	bool swapped=true;
	while(swapped){
	if(isSorted(arr,l)){
	swapped=false;
	}
	for(int i=0;i<l-1;i++){
	if (arr[i]>arr[i+1]){
	swap(arr[i],arr[i+1]);
	swapped=true;
	count++;
	}
	}
     }
	std::cout<<"This array had "<<count<<" comparisons\n";
}
void Sort::selectionSort(int arr[], int l){
	int i,j,min;
	count=0;
	for(i=0;i<l-1;i++){
	min=i;
	for(j=i+1;j<l-1;j++){
	
	if(arr[j]<arr[min]){min=j;}
	count++;
	}
	swap(arr[i],arr[min]);
	;
	}
	std::cout<<"This array had "<<count<<" comparisons\n";
}
void Sort::insertionSort(int arr[], int l){
	int i,j;
	count=0;
	for(i=0;i<l;i++){
	j=i;
	while(j>0 && arr[j]<arr[j-1]){
	swap(arr[j],arr[j-1]);
	j=j-1;
	count++;
	}
}
	std::cout<<"This array had "<<count<<" comparisons\n";
}
