//Shuffle.h
//Sam Disharoon
#ifndef SHUFFLE_H
#define SHUFFLE_H
	struct Heap {
	int length,heap_size;
	int *arr;
	int& operator[](int i){return arr[i-1];}
};
class People{
	struct info{
	int rank,ID;
};
	info* pArr;
	void create();

};
	void shuffle(int*,int);
	hirePeeps(peeps);
	void testSort(void(*i_hate_testsort)(int*,int),int*,int);
	void testSortQ(void(*i_hate_testsort)(int*,int,int),int*,int,int);
	void testSortH(void(*i_hate_testsort)(Heap)Heap);
	bool isSorted(int*,int);

	//Sorting algorithms
	void quick(int*,int,int);
	void rand_quick(int*,int,int);
	int partition(int*,int,int);
	void ms(int*,int,int);
	int* m(int*,int,int*,int);
	void heapsort(Heap);

	//heap functions
	int parent(int);
	int left(int);
	int right(int);
	void MaxHeapiy(Heap,int);
	void BuildMaxHeap(Heap);
	
	//print functions
	void printA(int*,int);
	void printH();
#endif
