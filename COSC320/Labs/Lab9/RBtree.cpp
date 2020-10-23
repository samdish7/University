//Lab 9 ~> RBtree.cpp
//Sam Disharoon
#include"RBtree.h"
#include<iostream>

RBtree::TreeNode* const RBtree::nil=new TreeNode({0,BLACK,nullptr,nullptr,nullptr});

RBtree::RBtree(){
	root=nullptr;
}
RBtree::RBtree(int x){
	TreeNode* newNode = new TreeNode;
	newNode->key=x;
	newNode->color=BLACK;
	newNode->parent=nil;
	newNode->left=nil;
	newNode->right=nil;
	root=newNode;
}
RBtree::~RBtree(){
	destroy(root);	
	
}
void RBtree::destroy(TreeNode* r){
	if(r&&r!=nil){
		destroy(r->left);
		destroy(r->right);
		delete r;
	}
}
void RBtree::printIO(){
	inOrder(root);
}
void RBtree::inOrder(TreeNode* r){
	if(r&&r!=nil){
		inOrder(r->left);
		if(r->color==BLACK){
			std::cout<<r->key<<" ";
		}
		else{
			std::cout<<"\033[0;31m"<<r->key<<"\033[0m"<<" ";
		}
		inOrder(r->right);
	}
	else{
		return;
	}
}
RBtree::TreeNode* RBtree::successorHelper(){
	successor(root);
}
RBtree::TreeNode* RBtree::successor(TreeNode* x){
	if(!x->right){
		return min(x->right);
	}
	TreeNode* y=x->parent;
	while(y&&x==y->right){
		x=y;
		y=y->parent;
	}
}
RBtree::TreeNode* RBtree::minHelper(){
	min(root);
}
RBtree::TreeNode* RBtree::min(TreeNode* x){
	if(x){
		while(x->left){
			x=x->left;
		}
	}
	std::cout<<x->key<<"\n";
	return x;
}
RBtree::TreeNode* RBtree::maxHelper(){
	max(root);
}
RBtree::TreeNode* RBtree::max(TreeNode* x){
	if(x){
		while(x->right){
			x=x->right;
		}
	}
	std::cout<<x->key<<"\n";
	return x;
}
RBtree::TreeNode* RBtree::search(int target){
	TreeNode* cursor=root;
	while(cursor){
		if(cursor->key==target){
			std::cout<<target<<" found!\n";
			return cursor;
		}
		if(target<cursor->key){
			cursor=cursor->left;
		}
		else if(target>cursor->key){
			cursor=cursor->right;
		}
	}
	std::cout<<target<<" Not found!\n";
	return nullptr;
}
void RBtree::insert(int num){
	TreeNode* prev=nil;//y
	TreeNode* cursor=root;//x
	
	while(cursor!=nil){
		prev=cursor;
		if(num<cursor->key){
			cursor=cursor->left;
		}
		else{
			cursor=cursor->right;
		}
	}
	TreeNode* z=new TreeNode;
	z->parent=prev;
	z->key=num;
	if(prev==nil){
		root=z;
	}
	else if(z->key<prev->key){
		std::cout<<"left\n";
		prev->left=z;
	}
	else{
		std::cout<<"right\n";
		prev->right=z;
	}
	z->left=nil;
	z->right=nil;
	z->color=RED;
	std::cout<<z->key<<"\n";
	insert_fixup(z);
}
void RBtree::insert_fixup(TreeNode* z){
	TreeNode* cursor;//y
	while(z->parent->color==RED){
		if(z->parent==z->parent->parent->left){
			std::cout<<"Parent parent left\n";
			cursor=z->parent->parent->right;
		
			if(cursor->color==RED){
				z->parent->color=BLACK;
				cursor->color=BLACK;
				z->parent->parent->color=RED;
				z=z->parent->parent;
			}
			else {
				if(z==z->parent->right){
					z=z->parent;
					left_rotate(z);
				}
				z->parent->color=BLACK;
				z->parent->parent->color=RED;
				right_rotate(z->parent->parent);
			}
		}
		else if(z->parent==z->parent->parent->right){
			std::cout<<"Parent parent right\n";
			cursor=z->parent->parent->left;
			if(cursor->color==RED){
				z->parent->color=BLACK;
				cursor->color=BLACK;
				z->parent->parent->color=RED;
				z=z->parent->parent;
			}
			else {
				if(z==z->parent->left){
					z=z->parent;
					right_rotate(z);
				}
				z->parent->color=BLACK;
				z->parent->parent->color=RED;
				left_rotate(z->parent->parent);
			}
		}
	}
	root->color=BLACK;
}
void RBtree::left_rotate(TreeNode* x){
	TreeNode* cursor=x->right;//y
	x->right=cursor->left;
	if(cursor->left!=nil){
		cursor->left->parent=x;
	}
	cursor->parent=x->parent;
	if(x->parent==nil){
		root=cursor;
	}
	else if(x==x->parent->left){
		x->parent->left=cursor;
	}
	else{
		x->parent->right=cursor;
	}
	cursor->left=x;
	x->parent=cursor;
}
void RBtree::right_rotate(TreeNode* x){
	TreeNode* cursor=x->left;//y
	x->left=cursor->right;
	if(cursor->right!=nil){
		cursor->right->parent=x;
	}
	cursor->parent=x->parent;
	if(x->parent==nil){
		root=cursor;
	}
	else if(x==x->parent->right){
		x->parent->right=cursor;
	}
	else{
		x->parent->left=cursor;
	}
	cursor->right=x;
	x->parent=cursor;
}
void RBtree::transplant(TreeNode* d, TreeNode* r){
	if(d->parent==nil){
		root=r;
	}
	else if(d==d->parent->left){
		d->parent->left=r;
	}
	else{
		d->parent->right=r;
	}
	r->parent=d->parent;
}
void RBtree::removeHelper(int x){
	remove(search(x));
}
void RBtree::remove(TreeNode* z){
	if(!z||z==nil){
		std::cout<<"Can't delete!\n";
	}
	else{
		TreeNode* cursor=z;//y
		TreeNode* x;
		int OG=cursor->color;
		if(z->left==nil){
			x=z->right;	
			transplant(z,z->right);
		}
		else if(z->right==nil){
			x=z->left;
			transplant(z,z->left);
		}
		else{
			cursor=min(z->right);
			OG=cursor->color;
			x=cursor->right;
			if(cursor->parent=x){
				x->parent=cursor;
			}
			else{
				transplant(cursor,cursor->right);
				cursor->right=z->right;
				cursor->right->parent=cursor;
			}
		transplant(z,cursor);
		cursor->left=z->left;
		cursor->left->parent=cursor;
		cursor->color=z->color;
		}
	
		if(OG==BLACK){
			remove_fixup(x);
		}
	}
}
void RBtree::remove_fixup(TreeNode* z){
	TreeNode* w;
	while(z==root&&z->color==BLACK){
		if(z==z->parent->left){
			w=z->parent->right;
			if(w->color==RED){
				w->color=BLACK;
				z->parent->color=RED;
				left_rotate(z->parent);
				w=z->parent->right;
			}
			if(w->left->color==BLACK&&w->right->color==BLACK){
				w->color=RED;
				z=z->parent;
			}
			else if(w->right->color==BLACK){
				w->left->color=BLACK;
				w->color=RED;
				right_rotate(w);
				w=z->parent->right;
			}
			w->color=z->parent->color;
			z->parent->color=BLACK;
			w->right->color=BLACK;
			left_rotate(z->parent);
			z=root;
		}
		else{
			w=z->parent->left;
			if(w->color==RED){
				w->color=BLACK;
				z->parent->color=RED;
				right_rotate(z->parent);
				w=z->parent->left;
			}
			if(w->right->color==BLACK&&w->left->color==BLACK){
				w->color=RED;
				z=z->parent;
			}
			else if(w->left->color==BLACK){
				w->right->color=BLACK;
				w->color=RED;
				left_rotate(w);
				w=z->parent->left;
			}
			w->color=z->parent->color;
			z->parent->color=BLACK;
			w->left->color=BLACK;
			right_rotate(z->parent);
		
		}
	}
	z->color==BLACK;
}
