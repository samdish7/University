//Main file lab 8
//Sam Disharoon

#include <iostream>
#include "lab8.h"

int main(){
	int a=5,b=10;
	int arr[]={1,2};
	std::cout<<"Before swapping:\ta:"<<a<<" b:"<<b<<"\n";
	swap(a,b);
	std::cout<<"After swapping: \ta:"<<a<<" b:"<<b<<"\n";
	std::cout<<"Now swapping two indexes in an array:\nBefore swapping:\t1st:"<<arr[0]<<" 2nd:"<<arr[1]<<"\n";
	swap(arr[0],arr[1]);
	std::cout<<"After swapping: \t1st:"<<arr[0]<<" 2nd:"<<arr[1]<<"\n\n\n";
	int arr2[]={4,54,8,3,3,65,3432,6,0,6};
	std::cout<<"Here is an array with duplicates:\n";
	for(int i=0;i<10;i++){std::cout<<arr2[i]<<"\n";}
	std::cout<<"Here is the pivot position after partition is called:\n";
	std::cout<<partition(arr2,0,9)<<"\n";
	std::cout<<"Now to quicksort the array:\n";
	quickSort(arr2,0,9);
	for(int i=0;i<10;i++){std::cout<<arr2[i]<<"\n";}
return 0;
}
