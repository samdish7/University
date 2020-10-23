//Lab 6, main.cpp
//Sam Disharoon

#include"shuffle.h"
#include<iostream>
#include<chrono>
#include<time.h>

int main(int argc, char** argv){
	long int i=10000;
	/*for(int j=0;j<21;j++){
	int* A1=new int[i];
	for(int h=0;h<i;h++){
		A1[h]=h+1;
	}	
	std::cout<<"QuickSort runtime for array size "<<i<<":\n";
	shuffle(A1,i);
	//printA(A1,i);
	testSortQ(quick,A1,0,i-1);
	//printA(A1,i);
	delete [] A1;
	i+=50000;
	}*/
	i=10000;
	/*for(int j=0;j<21;j++){}
	int* A2=new int[i];
	for(int h=0;h<i;h++){
		A2[h]=h+1;
	}
	std::cout<<"Randome quicksort runtime for array size "<<i<<":\n";
	shuffle(A2,i);
	printA(A2,i);
	testSortQ(rand_quick,A2,0,i-1);
	printA(A2,i);
	delete [] A2;*/
	i=10000;
	/*for(int j=0;j<21;j++){
	int* A3=new int[i];
	for(int h=0;h<i;h++){
		A3[h]=h+1;
	}
	std::cout<<"MergeSort runtime for array size "<<i<<":\n";
	shuffle(A3,i);
	//printA(A3,i);
	testSortQ(ms,A3,0,i-1);
	//printA(A3,i);
	delete [] A3;
	i+=50000;
	}*/
	i=10000;
	Heap A4;
	for(int j=0;j<21;j++){
	A4.length=i;
	A4.arr=new int[A4.length];
	A4.heap_size=A4.length;
	for(int h=0;h<i;h++){
	A4.arr[h]=h+1;
	}
	std::cout<<"HeapSort runtime for Heap size "<<i<<":\n";
	shuffle(A4.arr,i);
	//printA(A4.arr,i);
	testSortH(heapsort,A4);
	//printA(A4.arr,i);
	delete [] A4.arr;
	i+=50000;
	}
	

return 0;
}
