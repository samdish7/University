//Sam Disharoon
//Lab 3
//main.cpp
#include <iostream>
#include <algorithm>
#include <time.h>
#include <stdio.h>
#include <chrono>
struct Heap {
	int length,heap_size;
	int *arr;
	int& operator[](int i){return arr[i-1];}
};
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
void HeapSort(Heap A){
	BuildMaxHeap(A);
	for(int i=A.length;i>1;i--){
	std::swap(A[1],A[i]);
	A.heap_size=A.heap_size-1;
	MaxHeapify(A,1);
	}
}
void print(Heap A){
	for(int i=0;i<A.heap_size;i++){
	std::cout<<A.arr[i]<<" ";
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
int main(){
	Heap A;
	A.length=10;
	A.arr=new int[A.length];
	A.heap_size=A.length;
	srand(time(NULL));
	std::cout<<"Now creating a small array to turn into a heap:\n";
	for(int i=0;i<A.length;i++){
	A.arr[i]=rand() % 100+1;
	}
	//print(A);
	BuildMaxHeap(A);
	std::cout<<"\nAfter Heapify:\n";
	//printH(A);
	std::cout<<"Time to sort!\n\n";
	testSort(HeapSort,A);
	std::cout<<"After sorting:\n";
	//print(A);
	delete [] A.arr;
	std::cout<<"\n\n\nNow for a medium sized array:\n";
	Heap A2;
	A2.length=1000;
	A2.arr=new int[A2.length];
	A2.heap_size=A2.length;
	std::cout<<"Adding random elements:\n";
	for(int j=0;j<A2.length;j++){
	A2.arr[j]=rand() % 10000+1;
	}
	//print(A2);
	BuildMaxHeap(A2);
	std::cout<<"\nAfter Heapify:\n";
	//printH(A2);
	std::cout<<"Sorting time!\n";
	testSort(HeapSort,A2);
	std::cout<<"Afte sorting:\n";
	//print(A2);
	delete [] A2.arr;
	std::cout<<"\n\n\nNow making a huge array:\n";
	Heap A3;
	A3.length=800000;
	A3.arr=new int[A3.length];
	A3.heap_size=A3.length;
	std::cout<<"Adding elements to this beast:\n";
	for(int k=0;k<A3.length;k++){
	A3.arr[k]=rand() % 1000000+1;
	}
	//print(A3);
	BuildMaxHeap(A3);
	std::cout<<"After Heapify:\n";
	//printH(A3);
	std::cout<<"Time to sort!\n\n";
	testSort(HeapSort,A3);
	std::cout<<"After sorting:\n";
	//print(A3);
	delete [] A3.arr;
	return 0;
}
