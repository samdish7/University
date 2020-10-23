//hanoi.cpp file
//Sam Disharoon

#include "hanoi.h"
#include <iostream>

hanoiStack::hanoiStack(int m){top=nullptr;stackSize=0;max=m;}
hanoiStack::hanoiStack(int s,int m){
	stackSize=s;
	max=m;
	disk*cursor=nullptr;

	for(int i=0;i<stackSize;i++){
	disk* tmp = new disk;
	tmp->size=stackSize-i;
	tmp->next=cursor;
	top=tmp;
	cursor=tmp;
	}
}
int hanoiStack::getStackSize(){return stackSize;}
int hanoiStack::getTopSize(){return top->size;}
void hanoiStack::push(disk* d){
	d->next=top;
	top=d;
	stackSize++;
}
hanoiStack::disk* hanoiStack::pop(){
	disk* tmp=top;
	top=top->next;
	stackSize--;

	return tmp;
}
bool hanoiStack::empty(){
	if(stackSize==0){return true;}
	else {return false;}
}
void hanoiStack::display(){ //gives a visual representation of the stack
	disk* cursor=top;

	std::cout<<"                       |                       \n";
	if(stackSize==0){
	for(int j=0;j<max;j++){
	std::cout<<"                       |                       \n";
		}
	}
	for(int i=0;i<stackSize;i++){
	if(cursor->size==1){
	std::cout<<"                     ##|##                     \n";
	}
	else if(cursor->size==2){
	std::cout<<"                   ####|####                   \n";
	}
	else if(cursor->size==3){
	std::cout<<"                 ######|######                 \n";
	}
	else if(cursor->size==4){
	std::cout<<"               ########|########               \n";
	}
	else if(cursor->size==5){
	std::cout<<"             ##########|##########             \n";
	}
	else if(cursor->size==6){
	std::cout<<"           ############|############           \n";
	}
	else if(cursor->size==7){
	std::cout<<"         ##############|##############         \n";
	}
	else if(cursor->size==8){
	std::cout<<"       ################|################       \n";
	}
	else if(cursor->size==9){
	std::cout<<"     ##################|##################     \n";
	}
	else if(cursor->size==10){
	std::cout<<"   ####################|####################    \n";
	}
	cursor=cursor->next;
 }
	std::cout<<"\n\n";
}
