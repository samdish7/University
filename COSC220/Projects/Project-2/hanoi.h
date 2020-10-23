//Hanoi.h file
//Sam Disharoon

#ifndef HANOI_H
#define HANOI_H

class hanoiStack{
	private:
	struct disk{
		int size;
		disk* next;
	};
	disk* top;
	int max,stackSize;
	public:
	hanoiStack(int);//This is the default ctor to max towers 2 and 3
	hanoiStack(int,int);//This ctor makes the first tower

	int getTopSize();
	bool empty();
	void push(disk*);
	disk* pop();
	void display();
	int getStackSize();
};

#endif
