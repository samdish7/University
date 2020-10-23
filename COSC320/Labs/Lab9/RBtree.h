//Lab 9 ~> RBtree.h
//Sam Disharoon
#ifndef	RBTREE_H
#define	RBTREE_H
enum color_t{
	RED,
	BLACK
};
class RBtree{
	private:
		struct TreeNode{	//RB tree node definition
			int key;
			color_t color;
			TreeNode* left;
			TreeNode* right;
			TreeNode* parent;
		};
		TreeNode* root;	//root of the tree
		void transplant(TreeNode*,TreeNode*);	//helps the remove function
		static TreeNode* const nil;	//nil node that all leaves point to
	public:
		//rule of 3 and ctors
		RBtree();	//default ctor
		RBtree(int);	//main ctor
		RBtree(const RBtree&);	//copy ctor
		~RBtree();	//destructor
		void destroy(TreeNode*);	//destructor helper
		RBtree& operator=(const RBtree&);	//assignment operator
		
		//functions
		void insert(int);	//insert a node
		void insert_fixup(TreeNode*);	//balance the tree
		
		TreeNode* search(int);	//search for a node
		
		void right_rotate(TreeNode*);	//rotate the tree right
		void left_rotate(TreeNode*);	//rotate the tree left
		

		TreeNode* minHelper();	//helps find the min node
		TreeNode* min(TreeNode*);	//returns the min node
		
		TreeNode* maxHelper();	//helps find the max node
		TreeNode* max(TreeNode*);	//returns the max node

		TreeNode* successorHelper();	//helps find the successor to a node
		TreeNode* successor(TreeNode*);	//returns the successor of a node

		void removeHelper(int);	//helps delete a node
		void remove(TreeNode*);	//deletes a node
		void remove_fixup(TreeNode*);	//balances the tree after deletion
		
		void print();	//prints the tree
		
		void inOrder(TreeNode*);	//prints the tree in order
		void printIO();	//helps print the tree in order
};

#endif
