//Sam Disharoon
//qmsort.cpp
#include"qmsort.h"
#include<iostream>
#include<chrono>
void swap(int &val1,int &val2){
	int tmp=val1;
	val1=val2;
	val2=tmp;
}
void quickSort(int *arr,int s,int e){
	if(s<e){
		int p=partition(arr,s,e);
		quickSort(arr,s,p-1);
		quickSort(arr,p+1,e);
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
			swap(arr[s],arr[e]);
		}
	}
	return e;
}
//To test if it is sorted
bool isSorted(int *arr,int n){
	for(int i=0;i<n-1;i++){
		if (arr[i]<=arr[i+1]){i++;}
		else{return false;}
	}
	return true;
}

//Test Sort function to display the time it takes mergesort to sort
/*int* testSortM(void (*sort)(int*,int,int),int *arr,int n1,int n2){
  auto start=std::chrono::system_clock::now();
  sort(arr,n1,n2);
  auto end=std::chrono::system_clock::now();
  std::chrono::duration<double> elapsed_seconds=end-start;
  std::time_t end_time=std::chrono::system_clock::to_time_t(end);
  std::cout<<"finished at "<<std::ctime(&end_time);
  std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";
  }*///not sure, but it keeps telling me I have an invalid conversion when i use this

//Test Sort function to display the time it takes quickSort to sort
void testSortQ(void (*sort)(int*,int,int),int *arr,int n1,int n2){
	auto start=std::chrono::system_clock::now();
	sort(arr,n1,n2);
	auto end=std::chrono::system_clock::now();
	std::chrono::duration<double> elapsed_seconds=end-start;
	std::time_t end_time=std::chrono::system_clock::to_time_t(end);
	std::cout<<"finished at "<<std::ctime(&end_time);
	std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";

}
// sors Arr[s, ..., e]
void mergeSort(int* Arr,int s,int e){
	if(s<e){
		int mid=(s+e)/2;
		/*
		int* L1=mergeSort(Arr,s,mid);
		int* L2=mergeSort(Arr,mid+1,e);
		int* tmp = Merge(L1,mid-s+1,L2,e-mid); // e - (mid + 1) - 1
		*/
		mergeSort(Arr,s,mid);
		mergeSort(Arr,mid+1,e);
		int* merged = Merge(Arr+s, mid-s+1, Arr+(mid+1), e-mid);
		for(int i=0; i<e-s+1; i++)
			Arr[s+i] = merged[i];
		delete [] merged;
		//return Arr; // wrong
	}
}
int* Merge(int* L1,int s1,int* L2,int s2){
	int i=0,j=0,k=0;
	int *A = new int[s1+s2]; // may want to use "new" for this
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
	return A; // when does this get freed???
}
