//Lab 6, shuffle.cpp
//Sam Disharoon
#include"shuffle.h"
#include<iostream>
#include<time.h>
#include<algorithm>
#include<chrono>
#include<stdlib.h>
void shuffle(int* arr, int t){
	for(int i=0;i<t;i++){
	int j=rand()%(t+1);
	std::swap(arr[i],arr[j]);
	}
}
bool isSorted(int *arr,int n){
	for(int i=0;i<n-1;i++){
		if (arr[i]<=arr[i+1]){i++;}
		else{return false;}
	}
	return true;
}
void quick(int* arr, int s, int e){
	if(s<e){
		int p=partition(arr,s,e);
		quick(arr,s,p-1);
		quick(arr,p+1,e);
	}
}
void rand_quick(int *arr,int s,int e){
	if(s<e){
		std::swap(arr[e],arr[(rand()%(e))]);
		int p=partition(arr,s,e);
		rand_quick(arr,s,p-1);
		rand_quick(arr,p+1,e);
	}
}
int partition(int *arr,int s,int e){
	int pivot=arr[e];
	while(s<e){
		while(arr[s]<pivot){
			s++;}
		while(arr[e]>pivot){
			e--;}
		if(arr[s]==arr[e])
			s++;
		else if(s<e){
			std::swap(arr[s],arr[e]);
		}
	}
	return e;
}
void ms(int* Arr,int s,int e){
	if(s<e){
		int mid=(s+e)/2;
		ms(Arr,s,mid);
		ms(Arr,mid+1,e);
		int* merged = m(Arr+s, mid-s+1, Arr+(mid+1), e-mid);
		for(int i=0; i<e-s+1; i++)
			Arr[s+i] = merged[i];
		delete [] merged;
	}
}
int* m(int* L1,int s1,int* L2,int s2){
	int i=0,j=0,k=0;
	int *A = new int[s1+s2]; 
	while(j<s1 && k<s2){
		if (L1[j]>L2[k])
		{A[i]=L2[k];
			k++;}
		else
		{A[i]=L1[j];
			j++;}
		i++;
	}
	if(k<s2){
		for(;k<s2;k++)
		{A[i]=L2[k];
			i++;}
	}
	else{ // j < s1
		for(;j<s1;j++)
		{A[i]=L1[j];
			i++;}
	}
	return A;
}
int parent(int i){return i/2;}
int left(int i){return 2*i;}
int right(int i){return (2*i)+1;}
void MaxHeapify(Heap A, int i){
	int l=left(i);
	int r=right(i);
	int largest=0;
	if(l<=A.heap_size && A[l]>A[i]){
	largest=l;
	}
	else {largest=i;}
	if(r<=A.heap_size && A[r]>A[largest]){
	largest=r;
	}
	if(i!=largest){std::swap(A[i],A[largest]);
	MaxHeapify(A,largest);
	}
}
void BuildMaxHeap(Heap A){
	A.heap_size=A.length;
	for(int i=A.length/2;i>0;i--){MaxHeapify(A,i);}
}
void heapsort(Heap A){
	BuildMaxHeap(A);
	for(int i=A.length;i>1;i--){
	std::swap(A[1],A[i]);
	A.heap_size=A.heap_size-1;
	MaxHeapify(A,1);
	}
}
void printA(int* A,int x){
	for(int i=0;i<x;i++){
	std::cout<<A[i]<<" ";
	}
	std::cout<<std::endl;

}
void printH(Heap A){
	for(int i=0;i<A.heap_size;i++){
	std::cout<<A.arr[i]<<" ";
	}
	std::cout<<std::endl;
}
void testSort(void (*i_hate_testsort)(Heap),Heap A){
	auto start=std::chrono::system_clock::now();
	i_hate_testsort(A);
	auto end=std::chrono::system_clock::now();
	std::chrono::duration<double> elapsed_seconds=end-start;
	std::time_t end_time=std::chrono::system_clock::to_time_t(end);
	std::cout<<"finished at "<<std::ctime(&end_time);
	std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";

}
void testSortQ(void (*i_hate_testsort)(int*,int,int),int *arr,int n1,int n2){
	auto start=std::chrono::system_clock::now();
	i_hate_testsort(arr,n1,n2);
	auto end=std::chrono::system_clock::now();
	std::chrono::duration<double> elapsed_seconds=end-start;
	std::time_t end_time=std::chrono::system_clock::to_time_t(end);
	std::cout<<"finished at "<<std::ctime(&end_time);
	std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";

}
void testSortH(void (*i_hate_testsort)(Heap),Heap A){
	auto start=std::chrono::system_clock::now();
	i_hate_testsort(A);
	auto end=std::chrono::system_clock::now();
	std::chrono::duration<double> elapsed_seconds=end-start;
	std::time_t end_time=std::chrono::system_clock::to_time_t(end);
	std::cout<<"finished at "<<std::ctime(&end_time);
	std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";
}
