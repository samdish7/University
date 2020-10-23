//Cpp file Lab 8
//Sam Disharoon

#include "lab8.h"
#include <iostream>
void quickSort(int arr[],int s,int e){

	if(s<e){
	int p=partition(arr,s,e);
	quickSort(arr,s,p-1);
	quickSort(arr,p+1,e);
	}
}
int partition(int arr[],int s,int e){
	int pivot=arr[e];
	while(s<e){
	while(arr[s]<pivot){
	s++;}
	while(arr[e]>pivot){
	e--;}
	if(arr[s]==arr[e])
		s++;
	else if(s<e){
	swap(arr[s],arr[e]);
	}
	}
	return e;
}
void swap(int &val1,int &val2){
	int tmp=val1;
	val1=val2;
	val2=tmp;

}
