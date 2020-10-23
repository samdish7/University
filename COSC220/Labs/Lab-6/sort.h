//Sort.h
//Sam Disharoon
#ifndef SORT_H
#define SORT_H
class Sort{
	private:
	int length;
	int array[];
	public:
	void swap(int&, int&);
	void bubbleSort(int[],int);
	void selectionSort(int[],int);
	void insertionSort(int[],int);
	bool isSorted(int[],int);
	double timeTaken();
};
#endif
