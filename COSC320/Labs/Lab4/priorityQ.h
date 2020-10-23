//Sam Disharoon
//priorityQ.h
#ifndef PRIORITYQ_H
#define PRIORITYQ_H
class HeapQ {
	private:
	struct Heapobj {
	std::string name;
	int key;
	};
	Heapobj* arr;
	int length,heap_size;
	void increase_key(int,int);
	public:
	HeapQ();
	int parent(int);
	int left(int);
	int right(int);
	void print();
	std::string extract_Max(HeapQ);
	std::string peek();
	void insert(std::string,int);

};
#endif
