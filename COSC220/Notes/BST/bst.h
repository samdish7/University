#ifndef BST_H
#define BST_H

#include<iostream>
#include<stdio.h>

// we NEED the class T to have valid
// operator<= and operator== (at least),
// but others (<, >, >=) will be helpful
template <class T>
class BinarySearchTree{
  private:
    struct Node{
      T value;
      Node* left;
      Node* right;
    };

    Node* root = nullptr;

    void _inOrderPrint(Node*) const;
    void _postOrderPrint(Node*) const;
    void _preOrderPrint(Node*) const;

  public:
    void insert(T);
    void inOrderPrint() const;
    void postOrderPrint() const;
    void preOrderPrint() const;
    void search(T);
    //void remove(T);
};

template <class T>
void BinarySearchTree<T>::_inOrderPrint(Node* n) const{
  if(n){
    _inOrderPrint(n->left);
    std::cout << n->value << " ";
    _inOrderPrint(n->right);
  }
}

template <class T>
void BinarySearchTree<T>::_preOrderPrint(Node* n) const{
  if(n){
    std::cout << n->value << " ";
    _preOrderPrint(n->left);
    _preOrderPrint(n->right);
  }
}

template <class T>
void BinarySearchTree<T>::_postOrderPrint(Node* n) const{
  if(n){
    _postOrderPrint(n->left);
    _postOrderPrint(n->right);
    std::cout << n->value << " ";
  }
}

template <class T>
void BinarySearchTree<T>::inOrderPrint() const{
  std::cout << "=== IN ORDER ===\n";
  _inOrderPrint(root);
  std::cout << std::endl;
}

template <class T>
void BinarySearchTree<T>::preOrderPrint() const{
  std::cout << "=== PRE ORDER ===\n";
  _preOrderPrint(root);
  std::cout << std::endl;
}

template <class T>
void BinarySearchTree<T>::postOrderPrint() const{
  std::cout << "=== POST ORDER ===\n";
  _postOrderPrint(root);
  std::cout << std::endl;
}



/*
 * Needs time O(h) where h is the height
 * of the BST
 */
template <class T>
void BinarySearchTree<T>::insert(T data){
  Node* p = nullptr; // parent of cursor
  Node* cursor = root;

  // use implicit non-default
  Node* newNode = new Node({data, nullptr, nullptr});

  while(cursor){
    p = cursor; // remember where we were
    if(data <= cursor->value){
      cursor = cursor->left;
    } else {
      cursor = cursor->right;
    }
  }
  

  if( p ){ // adding below root
    // p is the last non-null node
    if( data <= p->value ){
      p->left = newNode;
    } else {
      p->right = newNode;
    }
  } else { // making new root
    root = newNode;
  }
}

//#include "bst.cpp" // no, because lazy

#endif















