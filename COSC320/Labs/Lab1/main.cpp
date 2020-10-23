//sorts.cpp
//Sam Disharoon
//COSC320
//This program takes different sized arrays an sorts them using 3 common sorting methods, bubble, selection, and insertion sorts. It times them and displays how much time each took

#include <time.h>
#include <stdio.h>
#include <chrono>
#include <iostream>

int count;
//To test if it is sorted
bool isSorted(int *arr,int n){
	for(int i=0;i<n-1;i++){
	if (arr[i]<=arr[i+1]){i++;}
	else{return false;}
	}
	return true;
}
//Swap function
void swap(int& a,int& b){
	int tmp=a;
	a=b;
	b=tmp;
}
//Bubble Sort function
void bubbleSort(int *arr,int n){
	std::cout<<"BUBBLESORT\n";
	count=0;
	bool swapped=true;
	while(swapped){
	if(isSorted(arr,n)){
	swapped=false;
	}
	for(int i=0;i<n-1;i++){
	if (arr[i]>arr[i+1]){swap(arr[i],arr[i+1]);
	swapped=true;
	count++;
	  }
	 }
	}
	std::cout<<"This array had "<<count<<" comparisons.\n";
}
//Selesction Sort function
void selectionSort(int *arr,int n){
	std::cout<<"SELECTIONSORT\n";
	int i,j,min;
	count=0;
	for(i=0;i<n-1;i++){
	min=i;
	for(j=i+1;j<n-1;j++){
	if(arr[j]<arr[min]){min=j;}
	count++;
	}
	swap(arr[i],arr[min]);
	}

	std::cout<<"This array had "<<count<<" comparisons.\n";
}
//Insertion Sort function
void insertionSort(int *arr,int n){
	std::cout<<"INSERTIONSORT\n";
	int i,j;
	count=0;
	for(i=0;i<n;i++){
	j=i;
	while(j>0 && arr[j]<arr[j-1]){
	swap(arr[j],arr[j-1]);
	j=j-1;
	count++;
	 }
	}
	std::cout<<"This array had "<<count<<" comparisons.\n";
}
//Test Sort function to display the time it takes to sort
void testSort(void (*sort)(int*,int),int *arr,int n){
	auto start=std::chrono::system_clock::now();
	sort(arr,n);
	auto end=std::chrono::system_clock::now();
	std::chrono::duration<double> elapsed_seconds=end-start;
	std::time_t end_time=std::chrono::system_clock::to_time_t(end);
	std::cout<<"finished at "<<std::ctime(&end_time);
	std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";
	
}
//main function
int main(){

	int i=0,small=100,med=10000,big=1000000,num=10000;
	int* lilArr=new int[small];
	int* medArr=new int[med];
	int* bigArr=new int[big];
	srand(time(NULL));
	std::cout<<"Now creating a randomly generated small array, a medium array that is in decending order, and a large array that is sorted\nThese will all be bubble sorted\n";
	for(i;i<small;i++){
	lilArr[i]=rand()% 100+1;
	}
	for(i=0;i<med;i++){
	medArr[i]=num;
	num--;
	}
	for(i=0;i<big;i++){
	bigArr[i]=i;
	}
	testSort(bubbleSort,lilArr,small);
	testSort(bubbleSort,medArr,med);
	testSort(bubbleSort,bigArr,big);
	
	num=10000;
	int* lilArr2=new int[small];
	int* medArr2=new int[med];
	int* bigArr2=new int[big];
	std::cout<<"\n\nNow creating 3 more arrays that resemble above, but will sesction sorted\n";
	for(i;i<small;i++){
	lilArr2[i]=rand()% 100+1;
	}
	for(i=0;i<med;i++){
	medArr2[i]=num;
	num--;
	}
	for(i=0;i<big;i++){
	bigArr2[i]=i;
	}
	testSort(selectionSort,lilArr2,small);
	testSort(selectionSort,medArr2,med);
//	testSort(selectionSort,bigArr2,big); Doesn't work with a large sorted array
	std::cout<<"\n\nNow creating 3 more arrays that resemble aboce, but will be insertion sorted\n";
	num=10000;
	int* lilArr3=new int[small];
	int* medArr3=new int[med];
	int* bigArr3=new int[big];
	for(i;i<small;i++){
	lilArr3[i]=rand()% 100+1;
	}
	for(i=0;i<med;i++){
	medArr3[i]=num;
	num--;
	}
	for(i=0;i<big;i++){
	bigArr3[i]=i;
	}
	testSort(insertionSort,lilArr3,small);
	testSort(insertionSort,medArr3,med);
	testSort(insertionSort,bigArr3,big);
	return 0;
}
