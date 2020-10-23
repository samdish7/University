//SUList header file
//Sam Disharoon
#ifndef SULIST_H
#define SULIST_H
template<class DataType>
class SUList{
	private:
	struct ListNode{
	DataType data;
	ListNode* next;
	};
	ListNode* head;
	ListNode* tail;

	public:
	SUList();
//	RULE OF 3
	SUList(const SUList&);
	~SUList();
	SUList<DataType>& operator=(const SUList<DataType>&);
//	Functions
	DataType getFront();
	DataType getBack();
	void putFront(const DataType&);
	void putBack(const DataType&);
	int size()const;
	bool contains(const DataType&);
	void print();
};
#endif
