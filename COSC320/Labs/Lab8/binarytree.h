//Lab 8 -> binarytree.h
//Sam Disharoon
#ifndef BINARYTREE_H
#define BINARYTREE_H
class BinaryTree{
	private:
		struct TreeNode{
		int key;
		TreeNode* left;
		TreeNode* right;
		TreeNode* parent;
	};
		TreeNode* root;
		void transplant(TreeNode*,TreeNode*);
	public:
		//rule of 3 and ctors
		BinaryTree();
		BinaryTree(int);
		BinaryTree(const BinaryTree&);
		void destroy(TreeNode*);
		~BinaryTree();
		BinaryTree& operator=(const BinaryTree&);

		//functions
		void insert(int);//inserts node into tree
		TreeNode* search(int);//searches for node in tree

		TreeNode* minHelper();//Helps the min by passing root
		TreeNode* min(TreeNode*);//returns minimum element in the tree
		
		TreeNode* maxHelper();//Helps the max by passing root
		TreeNode* max(TreeNode*);//returns maximum element in the tree

		TreeNode* successorHelper();//Helps the successor by passing root
		TreeNode* successor(TreeNode*);//returns the parent element of a node 
		void removeHelper(int);//Helps remove by passing root
		void remove(TreeNode*);//removes an element in the tree

		void print();//prints tree as is

		void inOrder(TreeNode*);//helps print tree in order
		void printIO();//prints tree in order
};


#endif
