//graph.h ~> Lab 10
//Sam Disharoon
#ifndef GRAPH_H
#define GRAPH_H
#include<vector>
#include<queue>
#include<map>
enum color_t{
	WHITE=-1,
	GRAY=0,
	BLACK=1,
};
class Graph{
	private:
		std::map<int,std::vector<int>> vetices;
		std::vector<int> distance;
		std::vector<int> parent;
		std::vector<color_t> color; 
	public:
		bool addVertex(int);
		bool addEdge(int,int);
		void print();
		void printBFS(int);
};

#endif
