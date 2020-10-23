//Main file
//Sam Disharoon

#include <iostream>
#include "hanoi.h"

int main(){
	const int MAX=10;
	int s,diskStack,stack;
	int moves=0;
	bool a=false,b=false;
	std::cout<<"\n||===========================||\n";
	std::cout<<"||Welcome to towers of Hanoi!||\t\t\t\t\t||==================||\n";
	std::cout<<"||===========================||\t\t\t\t\t||   *Difficulty*   ||\n";
	std::cout<<"||The object is to move the  ||\t\t\t\t\t||1-3 Disks-> Easy  ||\n";
	std::cout<<"||tower of disks one at a    ||\t\t\t\t\t||4-6 Disks-> Normal||\n";
	std::cout<<"||time, from the 1st to the  ||\t\t\t\t\t||7-9 Disks-> Hard  ||\n";
	std::cout<<"||3rd. You can't place a     ||\t\t\t\t\t||10 Disks-> Mental ||\n";
	std::cout<<"||larger disk on a smaller   ||\t\t\t\t\t||==================||\n";
	std::cout<<"||disk                       ||\n";
	std::cout<<"||===========================||\n";
	std::cout<<"||Number of Disks?           ||\n||";
	std::cin>>s;
	while(!a){
	if(s>MAX){std::cout<<"||===========================||\n||Please don't do that to    ||\n||yourself, keep it under 10 ||\n||";
	std::cin>>s;}
	else if(s<0){std::cout<<"||===========================||\n||How am I supposed to do a  ||\n||negative number of disks?  ||\n||Pick again.                ||\n||";
	std::cin>>s;}
	else if(s==0){std::cout<<"||===========================||\n||Funny, pick again          ||\n||";
	std::cin>>s;}
	else{
	std::cout<<"||===========================||\n";
	switch(s){
		case 1:
		{std::cout<<"||You picked 1 disk...       ||\n||How boring                 ||\n||===========================||\n\n\n";
		a=true;
		break;}
		case 2:
		{std::cout<<"||You picked 2 disks         ||\n||===========================||\n\n\n";
		a=true;
		break;}
		case 3:
		{std::cout<<"||You picked 3 disks         ||\n||===========================||\n\n\n";
		a=true;
		break;}
		case 4:
		{std::cout<<"||You picked 4 disks         ||\n||===========================||\n\n\n";
		a=true;
		break;}
		case 5:
		{std::cout<<"||You picked 5 disks         ||\n||===========================||\n\n\n";
		a=true;
		break;}
		case 6:
		{std::cout<<"||You picked 6 disks         ||\n||===========================||\n\n\n";
		a=true;
		break;}
		case 7:
		{std::cout<<"||You picked 7 disks         ||\n||===========================||\n\n\n";
		a=true;
		break;}
		case 8:
		{std::cout<<"||You picked 8 disks         ||\n||===========================||\n\n\n";
		a=true;
		break;}
		case 9:
		{std::cout<<"||You picked 9 disks         ||\n||===========================||\n\n\n";
		a=true;
		break;}
		case 10:
		{std::cout<<"||You picked 10 disks        ||\n||You are crazy!             ||\n||===========================||\n\n\n";
		a=true;
		break;}
   }
  }
 }
	hanoiStack stack1(s,s);//creating tower 1
	hanoiStack stack2(s);//creating tower 2
	hanoiStack stack3(s);//creating tower 3

	while(!b){
		stack1.display();
		stack2.display();
		stack3.display();
		std::cout<<"||==========================||\n";
		std::cout<<"||Please pick the tower you ||\n||like to take a disk from  ||\n";
		std::cout<<"||==========================||\n";
		std::cout<<"||1: Top Stack              ||\n";
		std::cout<<"||2: Middle Stack           ||\n";
		std::cout<<"||3: Bottom Stack           ||\n";
		std::cout<<"||-1: Quit           :)     ||\n";
		std::cout<<"||Moves: "<<moves<<"                  ||\n";
		std::cout<<"||==========================||\n||";
		std::cin>>diskStack;
	switch(diskStack){
		case 1:
		{if(stack1.empty()==false){
		std::cout<<"||==========================||\n";
		std::cout<<"||You chose tower 1         ||\n||Where will you put it?    ||\n";
		std::cout<<"||==========================||\n";
		std::cout<<"||1:Middle///2:Bottom       ||\n";
		std::cout<<"||==========================||\n||";
		std::cin>>stack;
			if(stack==1){
			 if(stack2.empty()==true||stack1.getTopSize()<stack2.getTopSize()){stack2.push(stack1.pop());moves++;}
			 else if(stack1.getTopSize()>=stack2.getTopSize()){
		std::cout<<"||==========================||\n";
		std::cout<<"||You can't do that because ||\n";
		std::cout<<"||your disk is larger than  ||\n";
		std::cout<<"||the one in tower 2        ||\n";
		std::cout<<"||==========================||\n||";}
		  }
			else if(stack==2){
		  	 if(stack3.empty()==true||stack1.getTopSize()<stack3.getTopSize()){stack3.push(stack1.pop());moves++;
			 if(stack3.getStackSize()==s){
		std::cout<<"||==========================||\n";
		std::cout<<"||YOU WIN!                  ||\n";
		std::cout<<"||You took "<<moves<<" moves          ||\n";
		std::cout<<"||==========================||\n";
		stack1.display();
		stack2.display();
		stack3.display();
       		b=true;}
			 }
			 else if(stack1.getTopSize()>=stack3.getTopSize()){
		std::cout<<"||==========================||\n";
		std::cout<<"||You can't do that because ||\n";
		std::cout<<"||your disk is larger than  ||\n";
		std::cout<<"||the one in tower 3        ||\n";
		std::cout<<"||==========================||\n||";}
		  }
			else{
		std::cout<<"||==========================||\n";
		std::cout<<"||Invalid choice            ||\n";
		std::cout<<"||==========================||\n";}
		 }//if stack 1 isn't empty
		else{
		std::cout<<"||==========================||\n";
		std::cout<<"||Tower 1 has no disks!     ||\n";
		std::cout<<"||==========================||\n";}
		break;}//case 1 stack switch statement

		case 2:
		{if(stack2.empty()==false){
		std::cout<<"||==========================||\n";
		std::cout<<"||You chose tower 2         ||\n||Where will you put it?    ||\n";
		std::cout<<"||==========================||\n";
		std::cout<<"||1:Top///2:Bottom          ||\n";
		std::cout<<"||==========================||\n||";
		std::cin>>stack;
			if(stack==1){
			 if(stack1.empty()==true||stack2.getTopSize()<stack1.getTopSize()){stack1.push(stack2.pop());moves++;}
			 else if(stack2.getTopSize()>stack1.getTopSize()){
		std::cout<<"||==========================||\n";
		std::cout<<"||You can't do that because ||\n";
		std::cout<<"||your disk is larger than  ||\n";
		std::cout<<"||the one in tower 1        ||\n";
		std::cout<<"||==========================||\n";}
			}
			else if(stack==2){
			 if(stack3.empty()==true||stack2.getTopSize()<stack3.getTopSize()){stack3.push(stack2.pop());moves++;
			 if(stack3.getStackSize()==s){
		std::cout<<"||==========================||\n";
		std::cout<<"||YOU WIN!                  ||\n";
		std::cout<<"||You took "<<moves<<" moves          ||\n";
		std::cout<<"||==========================||\n";
		stack1.display();
		stack2.display();
		stack3.display();	
		b=true;}
		}
			 else if(stack2.getTopSize()>stack3.getTopSize()){
		std::cout<<"||==========================||\n";
		std::cout<<"||You can't do that because ||\n";
		std::cout<<"||your disk is larger than  ||\n";
		std::cout<<"||the one in tower 3        ||\n";
		std::cout<<"||==========================||\n";}
		  }
			else{
		std::cout<<"||==========================||\n";
		std::cout<<"||Invalid choice            ||\n";
		std::cout<<"||==========================||\n";}
		}//if stack 2 isn't empty
		else{
		std::cout<<"||==========================||\n";
		std::cout<<"||Tower 2 has no disks!     ||\n";
		std::cout<<"||==========================||\n";}
		break;}//case 2 stack switch statement

		case 3:
		{if(stack3.empty()==false){
		std::cout<<"||==========================||\n";
		std::cout<<"||You chose tower 3         ||\n||Where will you put it?    ||\n";
		std::cout<<"||==========================||\n";
		std::cout<<"||1:Top///2:Middle          ||\n";
		std::cout<<"||==========================||\n||";
		std::cin>>stack;
			if(stack==1){
			 if(stack1.empty()==true||stack3.getTopSize()<stack1.getTopSize()){stack1.push(stack3.pop());moves++;}
			 else if(stack3.getTopSize()>stack1.getTopSize()){
		std::cout<<"||==========================||\n";
		std::cout<<"||You can't do that because ||\n";
		std::cout<<"||your disk is larger than  ||\n";
		std::cout<<"||the one in tower 1        ||\n";
		std::cout<<"||==========================||\n";}
			}
			else if(stack==2){
			 if(stack2.empty()==true||stack3.getTopSize()<stack2.getTopSize()){stack2.push(stack3.pop());moves++;}
			 else if(stack3.getTopSize()>stack2.getTopSize()){
		std::cout<<"||==========================||\n";
		std::cout<<"||You can't do that because ||\n";
		std::cout<<"||your disk is larger than  ||\n";
		std::cout<<"||the one in tower 2        ||\n";
		std::cout<<"||==========================||\n";}
			}
			else{
		std::cout<<"||==========================||\n";
		std::cout<<"||Invalid Choice            ||\n";
		std::cout<<"||==========================||\n";}
		}//if stack 3 isn't empty
		else{
		std::cout<<"||==========================||\n";
		std::cout<<"||Tower 3 has no disks!     ||\n";
		std::cout<<"||==========================||\n";}
		break;}//case 3 stack switch statement
		case -1:
		{b=true;break;}
		default:{
		std::cout<<"||==========================||\n";
		std::cout<<"||Invalid choice            ||\n";
		std::cout<<"||==========================||\n";}
  }//switch statement for diskStack
 }//While statement to run the game
		std::cout<<"||==========================||\n";
		std::cout<<"||Thank you for playing!    ||\n";
		std::cout<<"||==========================||\n";
return 0;
}
