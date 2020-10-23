//Lab 8 -> main.cpp
//Sam Disharoon
#include"binarytree.h"
#include<iostream>
#include<chrono>
#include<time.h>
int main(int argc, char** argv){
	BinaryTree tree(83);	//Creates tree with root at 83;
	int x;
	srand(time(NULL));
	//Going to insert 5 nodes manually and time it
	
	auto start=std::chrono::system_clock::now();
	tree.insert(65);
	tree.insert(213);
	tree.insert(54);
	tree.insert(54);
	tree.insert(654);
	auto end=std::chrono::system_clock::now();
	std::chrono::duration<double> elapsed_seconds=end-start;
	std::time_t end_time=std::chrono::system_clock::to_time_t(end);
	std::cout<<"Insertion finished at "<<std::ctime(&end_time);
	std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";
	
	
	//Now to print the tree in order
	tree.printIO();
	std::cout<<"\n";
	std::cout<<"Max->";
	tree.maxHelper();
	std::cout<<"\nMin->";
	tree.minHelper();
	std::cout<<"\n";
	
	//Now to search for 2 nodes, 1 in, 1 not
	start=std::chrono::system_clock::now();
	tree.search(654);
	tree.search(8);//<-will print out NOT FOUND!
	end=std::chrono::system_clock::now();
	elapsed_seconds=end-start;
	end_time=std::chrono::system_clock::to_time_t(end);
	std::cout<<"Searching finished at "<<std::ctime(&end_time);
	std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";
	
	
	//Now to delete a Node	
	start=std::chrono::system_clock::now();
	tree.removeHelper(54);
	end=std::chrono::system_clock::now();
	elapsed_seconds=end-start;
	end_time=std::chrono::system_clock::to_time_t(end);
	std::cout<<"Deletion finished at "<<std::ctime(&end_time);
	std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";
	
	//Print tree after remove
	tree.printIO();
	std::cout<<"\n";
	
	/*****NEXT PHASE OF TESTING*****/
	
	BinaryTree tree2(50);	//Creates tree with root at 50
	
	//Going to insert 1000 nodes and time the functions
	
	
	start=std::chrono::system_clock::now();
	for(int n=0;n<1000;n++){
		std::cout<<n<<"\n";
		x=rand()%100+1;
		tree2.insert(x);
	}
	end=std::chrono::system_clock::now();
	elapsed_seconds=end-start;
	end_time=std::chrono::system_clock::to_time_t(end);
	std::cout<<"Insertion finished at "<<std::ctime(&end_time);
	std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";
	
	std::cout<<"\nMax-> ";
	tree2.maxHelper();
	std::cout<<"\nMin-> ";
	tree2.minHelper();
	std::cout<<"\n";

	//Now to search for random nodes
	start=std::chrono::system_clock::now();
	for(int n=0;n<100;n++){
		x=rand()%100+1;
		tree2.search(x);
	}
	end=std::chrono::system_clock::now();
	elapsed_seconds=end-start;
	end_time=std::chrono::system_clock::to_time_t(end);
	std::cout<<"Searching finished at "<<std::ctime(&end_time);
	std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";
	
	//Now to delete random nodes
	start=std::chrono::system_clock::now();
	for(int n=0;n<100;n++){
		x=rand()%100+1;
		tree2.removeHelper(x);
	}
	end=std::chrono::system_clock::now();
	elapsed_seconds=end-start;
	end_time=std::chrono::system_clock::to_time_t(end);
	std::cout<<"Searching finished at "<<std::ctime(&end_time);
	std::cout <<"elapsed time: "<<elapsed_seconds.count()<<"s\n";

	//Print inorder after delete
	tree2.printIO();
	/*****LAST PHASE OF TESTING*****

	BinaryTree tree3(50);	//Creates tree with root at 50
	
	//Going to insert 3000 nodes and time the functions
	
	start=std::chrono::system_clock::now();
	for(int i=3000;i>0;i--){
		x=rand()%100+1;
		tree.insert(x);
	}*/
	return 0;
}
