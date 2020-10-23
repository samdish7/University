//SUQueue header file
//Sam Disharoon

#ifndef SUQUEUE_H
#define SUQUEUE_H
#include "SUList.h"
template<class DataType>
class SUQueueList{
	private:
	SUList<DataType> list;

	public:
	SUQueueList();
//	RULE OF 3
	SUQueueList(const SUQueueList&);
	~SUQueueList();
	SUQueueList<DataType>& operator=(const SUQueueList<DataType>&);
//	Functions
	int size()const;
	bool isEmpty()const;
	void enqueue(const DataType&);
	void dequeue(DataType&);
	void printQueue()const;
};

template<class DataType>
class SUQueueArr{
        private:
        DataType* arr;
	int capacity;
	int front;
	int rear;

        public:
        SUQueueArr();
//      RULE OF 3
        SUQueueArr(const SUQueueArr&);
        ~SUQueueArr();
        SUQueueArr<DataType>& operator=(const SUQueueArr<DataType>&);
//      Functions
        int size()const;
        bool isEmpty()const;
        void enqueue(const DataType&);
        void dequeue(DataType&);
        void printQueue()const;
};
#endif
