//Sam Disharoon 
//Lab 2
//This lab will demostrate the quicksort and mergesort methods and will test their times on different sized/sorted arrays.
#include<chrono>
#include<iostream>
#include<stdio.h>
#include<time.h>
#include"qmsort.h"
int main(){
	int small1[]={4,6,3,5,7};
	int small2[]={89,9,0,-2,4};
	int i=0,num=100000;
	std::cout<<"WE FINNA QUICKSORT THIS ARRAY:\n";
	for(i=0;i<5;i++){
		std::cout<<small1[i]<<std::endl;
	}
	std::cout<<"============\n";
	testSortQ(quickSort,small1,0,4);
	for(i=0;i<5;i++){
		std::cout<<small1[i]<<std::endl;
	}
	std::cout<<"\n\nNow for a medium sized array randomly generated:\n";
	int med1[10000];
	for(i=0;i<10000;i++){
		med1[i]=rand() % 1000+1;
	}
	testSortQ(quickSort,med1,0,9999);
	/*std::cout<<"\n\n";
	  for(i=0;i<10000;i++){
	  std::cout<<med1[i]<<" ";
	  }*///This is the output of the medium array to show it is sorted
	/*std::cout<<"\nNow for a huge array in descending order:\n";
	  int big1[100000];
	  for(i=0;i<100000;i++){
	  big1[i]=num;
	  num--;}
	  testSortQ(quickSort,big1,0,99999);
	  std::cout<<"\nNow that we have the time it takes for worst case, lets do best case: \n";
	  int big2[100000];
	  for(i=0;i<100000;i++){
	  big2[i]=i;}
	  testSortQ(quickSort,big2,0,99999);
	  for(i=0;i<100000;i++){
	  std::cout<<big1[i]<<" ";
	  }
	  */	//This is the output of the big array to show it is sorted
	std::cout<<"\n============\nWE FINNA MERGESORT THIS ARRAY:\n";
	for(i=0;i<5;i++){
		std::cout<<small2[i]<<std::endl;
	}
	mergeSort(small2,0,4);
	std::cout<<"============\n";
	for(i=0;i<5;i++){
		std::cout<<small2[i]<<std::endl;
	}
	return 0;
}
