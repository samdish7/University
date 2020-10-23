//Lab 8 -> binarytree.cpp
//Sam Disharoon
#include"binarytree.h"
#include<iostream>
#include<chrono>

BinaryTree::BinaryTree(){
	root=nullptr;
}
BinaryTree::BinaryTree(int x){
	TreeNode* newNode = new TreeNode;
	newNode->key=x;
	newNode->left=nullptr;
	newNode->right=nullptr;
	newNode->parent=nullptr;
	root=newNode;
	
}
BinaryTree::BinaryTree(const BinaryTree& other){

}
BinaryTree::~BinaryTree(){
	destroy(root);
}
BinaryTree& BinaryTree::operator=(const BinaryTree&){

}
void BinaryTree::destroy(TreeNode* r){
	if(r!=nullptr){
		destroy(r->left);
		destroy(r->right);
		delete r;
	}
}
void BinaryTree::insert(int num){
	TreeNode* prev;// y
	TreeNode* cursor = root;//x

	while(cursor!=nullptr){
		prev=cursor;
		if(num<cursor->key){
			cursor=cursor->left;
		}
		else {
			cursor=cursor->right;
		}

	}
	TreeNode* z = new TreeNode;
	z->parent=prev;
	z->key=num;
	z->right = nullptr;
	z->left = nullptr;

	if(root==nullptr){
		root=z;
	}
	else{
		if(z->key<prev->key){
			prev->left=z;
		}
		else{
			prev->right=z;
		}
	
	}
	
}
void BinaryTree::printIO(){
	inOrder(root);
}
void BinaryTree::inOrder(TreeNode* x){
	
	if(x!=nullptr){
		inOrder(x->left);
		std::cout<<x->key<<" ";
		inOrder(x->right);
	}
	else{
		return;
	}
}
BinaryTree::TreeNode* BinaryTree::search(int target){
	TreeNode* cursor=root;
	while(cursor!=nullptr){
		if(cursor->key==target){
			return cursor;
		}
		if(target<=cursor->key){
			cursor=cursor->left;
		}
		else if(target>cursor->key){
			cursor=cursor->right;
		}
	}
	std::cout<<"Not found!\n";
	return nullptr;
}
void BinaryTree::transplant(TreeNode* d, TreeNode* r){
	if(d->parent==nullptr){
		root=r;
	}
	else if(d==d->parent->left){
		d->parent->left=r;
	}
	else{
		d->parent->right=r;
	}
	if(r!=nullptr){
		r->parent=d->parent;
	}
}
void BinaryTree::removeHelper(int x){
	remove(search(x));
}
void BinaryTree::remove(TreeNode* x){
	if(x==nullptr){
		std::cout<<"Can't delete!\n";
	}
	else{
		TreeNode* d=x;
		TreeNode* r=x;

		if(x->left==nullptr){
			transplant(x,x->right);
		}
		else if(x->right==nullptr){
			transplant(x,x->left);
		}
		else{
			TreeNode* y=min(x->right);
			if(y->parent!=x){
				transplant(y,y->right);
				y->right=x->right;
				y->right->parent=y;
			}
			transplant(x,y);
			y->left=x->left;
			y->left->parent=y;
		}
	}
}
BinaryTree::TreeNode* BinaryTree::successorHelper(){
	successor(root);
}
BinaryTree::TreeNode* BinaryTree::successor(TreeNode* x){
	if(x->right==nullptr){
		return min(x->right);
	}
	TreeNode* y=x->parent;
	while(y!=nullptr&&x==y->right){
		x=y;
		y=y->parent;
	}
}
BinaryTree::TreeNode* BinaryTree::minHelper(){
	min(root);
}
BinaryTree::TreeNode* BinaryTree::min(TreeNode* x){
	if(x!=nullptr){
		while(x->left!=nullptr){
			x=x->left;
		}
	}
	std::cout<<x->key<<std::endl;
	return x;
}
BinaryTree::TreeNode* BinaryTree::maxHelper(){
	max(root);
}
BinaryTree::TreeNode* BinaryTree::max(TreeNode* x){
	if(x!=nullptr){
		while(x->right!=nullptr){
			x=x->right;
		}
	}
	std::cout<<x->key<<std::endl;
	return x;
}
