//SUStack header file
//Sam Disharoon
#ifndef SUSTACK_H
#define SUSTACK_H
#include "SUList.h"
template<class DataType>
class SUStackList{
	private:
	SUList<DataType> list;

	public:
	SUStackList();
//	RULE OF 3
	SUStackList(const SUStackList&);
	~SUStackList();
	SUStackList<DataType>& operator=(const SUStackList<DataType>&);
//	Functions
	int size()const;
	bool isEmpty()const;
	void push(const DataType&);
	void pop(DataType&);
	void printStack()const;
};

template<class DataType>
class SUStackArr{
        private:
        DataType* arr;
	int capacity;
	int top;

        public:
        SUStackArr();
//      RULE OF 3
        SUStackArr(const SUStackArr&);
        ~SUStackArr();
        SUStackArr<DataType>& operator=(const SUStackArr<DataType>&);
//      Functions
        int size()const;
        bool isEmpty()const;
        void push(const DataType&);
        void pop(DataType&);
        void printStack()const;
};
#endif
