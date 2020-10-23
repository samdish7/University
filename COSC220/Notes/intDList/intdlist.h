#ifndef DLINKEDLIST_H
#define DLINKEDLIST_H

#include<iostream>
#include<stdio.h>

class LinkedList{
  private:
    // structured data type,
    // whose precise, fully-qualified
    // type is LinkedList::Node
    struct Node{
      Node *next, *prev;
      int   val;
    };

    // a crude way to initialize
    // to the null address
    // use -std=c++11 to compile
    Node* head = nullptr;
    Node* tail = nullptr;

  public:
    LinkedList();

    // gets called:
    //   - instantiating a new LinkedList
    //   explicity, with a LL as the
    //   argument
    //   - pass a LinkedList by value to
    //   a function!
    // the "const" tells the compiler
    // to make sure we don't screw up
    // the "other" list
    LinkedList(const LinkedList& other);

    // destructor
    ~LinkedList();

    // Doesn't need to return,
    // takes the int to add onto
    // the list
    void append(int n);

    // remove
    // Takes a target int and removes it
    // from the list, if present
    void remove(int n);

    // TODO: contains

    // search
    // Determines if n is stored in
    // the list, and returns the address
    // of its node if yes, null o/w.
    //
    // We want to use this as a
    // subroutine to remove a node,
    // and let main test for membership
    Node* search(int n);

    // print
    void print();
};

#endif // LINKEDLIST_H
