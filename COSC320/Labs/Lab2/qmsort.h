//Sam Disharoon
//qmsort.h
#ifndef QMSORT_H
#define QMSORT_H

void quickSort(int*,int,int);//quicksort function prototype
int partition(int*,int,int);//partition function prototype
void swap(int&,int&);//swap function prototype
bool isSorted(int*,int);//isSorted function prototype
//int* mergeSort(int*,int,int);//MergeSort function prototype
void mergeSort(int*,int,int);//MergeSort function prototype
int* Merge(int*,int,int*,int);//Merge function prototype
void testSortQ(void(*sort)(int*,int,int),int*,int,int);//testsort for QS
int* testSortM(void(*sort)(int*,int,int),int*,int,int);//testsort for MS
#endif
