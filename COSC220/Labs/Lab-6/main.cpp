//Lab-6
//Sam Disharoon

#include <iostream>
#include <time.h>
#include <stdio.h>
#include "sort.h"
#include<chrono>
int main(){
	Sort s;
	int i,num=10000;
	srand (time(NULL));
	const int small=5,med=100,big=10000;
	int x[small]={12,54,1,43,21};
	int x2[big],x3[med];
	std::cout<<"Now sorting the small array using bubble sort:\n";
	auto start = std::chrono::system_clock::now();
	s.bubbleSort(x,small);
	auto end = std::chrono::system_clock::now();
	std::chrono::duration<double> elapsed_seconds = end-start;
	std::time_t end_time = std::chrono::system_clock::to_time_t(end);
	std::cout << "finished at " << std::ctime(&end_time)<< "elapsed time: " << elapsed_seconds.count() << "s\n";
//This is the output for the small array
/*	
	for(i=0;i<small;i++){
	std::cout<<x[i]<<" ";	}
	std::cout<<"\n";
*/
//---------------------------------
	std::cout<<"\nNow creating a large array that is in descending order\n";
	for(i=0;i<big;i++){
	x2[i]=num;
	num--;}
	std::cout<<"Now sorting the large array using bubble sort:\n";
	start = std::chrono::system_clock::now();
	s.bubbleSort(x2,big);
	end = std::chrono::system_clock::now();
        elapsed_seconds = end-start;
        end_time = std::chrono::system_clock::to_time_t(end);
        std::cout << "finished at " << std::ctime(&end_time)<< "elapsed time: " << elapsed_seconds.count() << "s\n";
//This is the output for the large array
/*	for(i=0;i<big;i++){
	std::cout<<x2[i]<<" ";}
	std::cout<<"\n";
*/
//---------------------------------
	std::cout<<"\nNow creating a medium size array that is randomly generated\n";
	for(i=0;i<med;i++){
	x3[i]=rand() % 100+1;
	}
	std::cout<<"Now sorting the medium array using bubble sort:\n";
	start = std::chrono::system_clock::now();
	s.bubbleSort(x3,med);
	end = std::chrono::system_clock::now();
        elapsed_seconds = end-start;
        end_time = std::chrono::system_clock::to_time_t(end);
        std::cout << "finished at " << std::ctime(&end_time)<< "elapsed time: " << elapsed_seconds.count() << "s\n";
//This is the output for the medium array
/*
	for(i=0;i<med;i++){
	std::cout<<x3[i]<<" ";}
	std::cout<<"\n";
*/
//---------------------------------
	std::cout<<"\nNow creating 3 new arrays, one that is small and hardcoded\nOne that is large and in descending order, and one that is medium and is randomly generated\n\n";
	int y[small]={65,32,7,12,5};
	int y2[big],y3[med];
	std::cout<<"Now sorting the small array using selection sort:\n";
	start = std::chrono::system_clock::now();
	s.selectionSort(y,small);
	end = std::chrono::system_clock::now();
        elapsed_seconds = end-start;
	end_time = std::chrono::system_clock::to_time_t(end);
 	std::cout << "finished at " << std::ctime(&end_time)<< "elapsed time: " << elapsed_seconds.count() << "s\n";
//This is the output for the small array *NOTE*The last element is not sorted for some reason, Can't figure out why
/*	for(i=0;i<small;i++){
 *	std::cout<<y[i]<<" ";}
 *	std::cout<<"\n";
*/	
	std::cout<<"\nNow inserting elements into the large array\n";
	num=10000;
	for(i=0;i<big;i++){
	y2[i]=num;
	num--;}
	std::cout<<"Now sorting the large array using selection sort:\n";
	start=std::chrono::system_clock::now();
	s.selectionSort(y2,big);
	end = std::chrono::system_clock::now();
        elapsed_seconds = end-start;
        end_time = std::chrono::system_clock::to_time_t(end);
        std::cout << "finished at " << std::ctime(&end_time)<< "elapsed time: " << 	elapsed_seconds.count() << "s\n";
//This is the output for the large array, same issue as the small one
/*	for(i=0;i<big;i++){
 *	std::cout<<y2[i]<<" ";}
 *	std::cout<<"\n";
 */
	std::cout<<"\nNow inserting elements into the medium array\n";
	for(int i=0;i<med;i++){
	y3[i]=rand()%100+1;}
	std::cout<<"\nNow sorting the medium array using selection sort:\n";
	start=std::chrono::system_clock::now();
	s.selectionSort(y3,med);
	end = std::chrono::system_clock::now();
        elapsed_seconds = end-start;
        end_time = std::chrono::system_clock::to_time_t(end);
        std::cout << "finished at " << std::ctime(&end_time)<< "elapsed time: " << elapsed_seconds.count() << "s\n";
//This is the output for the medium array, same issues as the other 2
/*	for(i=0;i<med;i++){
 *	std::cout<<y3[i]<< " ";}
 *	std::cout<<"\n"; 
 */
	std::cout<<"\n\nNow creating 3 more arrays similar to the ones above, but they will be sorted using Insertion sort, so small is hardcoded, large is in reverse order, and medium is randomly generated\n\n";
	int z[small]={45,23,1,32,9};
	int z2[big],z3[med];
	std::cout<<"Now sorting the small array using insertion sort:\n";
	start=std::chrono::system_clock::now();
	s.insertionSort(z,small);
	end = std::chrono::system_clock::now();
        elapsed_seconds = end-start;
        end_time = std::chrono::system_clock::to_time_t(end);
        std::cout << "finished at " << std::ctime(&end_time)<< "elapsed time: " << elapsed_seconds.count() << "s\n";
//This is the output for the small array
/*	for(i=0;i<small;i++){
	std::cout<<z[i]<<" ";}
	std::cout<<"\n";
*/
	std::cout<<"\nNow inserting elements into the large array\n";
	num=10000;
	for(i=0;i<big;i++){
	z2[i]=num;
	num--;}
	std::cout<<"\nNow sorting the large array using insertion sort:\n";
	start=std::chrono::system_clock::now();
	s.insertionSort(z2,big);
	end = std::chrono::system_clock::now();
        elapsed_seconds = end-start;
        end_time = std::chrono::system_clock::to_time_t(end);
        std::cout << "finished at " << std::ctime(&end_time)<< "elapsed time: " << elapsed_seconds.count() << "s\n";
//This is the output for the large array
/*	for(i=0;i<big;i++){
	std::cout<<z2[i]<<" ";}
	std::cout<<"\n";
*/
	std::cout<<"\nNow inserting elements into the medium array\n\n";
	for(i=0;i<med;i++){
	z3[i]=rand()% 100+1;}
	std::cout<<"Now sorting the medium array using insertion sort\n";
	start=std::chrono::system_clock::now();
	s.insertionSort(z3,med);
	end = std::chrono::system_clock::now();
        elapsed_seconds = end-start;
        end_time = std::chrono::system_clock::to_time_t(end);
        std::cout << "finished at " << std::ctime(&end_time)<< "elapsed time: " << elapsed_seconds.count() << "s\n";
//This is the output for the medium array
/*	for(i=0;i<med;i++){
	std::cout<<z3[i]<<" ";}
	std::cout<<"\n";	
*/	
	return 0;
}
