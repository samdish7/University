#include<iostream>
#include "bst.h"

int main(){
  BinarySearchTree<int> myTree;
  myTree.insert(20);
  myTree.insert(15);
  myTree.insert(22);
  myTree.insert(8);
  myTree.insert(2);
  myTree.insert(41);

  myTree.inOrderPrint();
  myTree.preOrderPrint();
  myTree.postOrderPrint();

  return 0;
}
