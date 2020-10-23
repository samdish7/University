#include "intdlist.h"

LinkedList::LinkedList(){
}

// gets called:
//   - instantiating a new LinkedList
//   explicity, with a LL as the
//   argument
//   - pass a LinkedList by value to
//   a function!
// the "const" tells the compiler
// to make sure we don't screw up
// the "other" list
LinkedList::LinkedList(const LinkedList& other){
  std::cout << "Copying a list...\n";
  //head = other.head;
  Node* cursor = other.head;
  while(cursor){
    append(cursor->val);
    cursor = cursor->next;
  }
  // modifies "other"
  // won't compile with the "const"
  //other.head = new Node;
}

// destructor
LinkedList::~LinkedList(){
  Node* cursor = head;
  while(cursor){
    head = cursor->next;
    delete cursor;
    cursor = head;
  }
}

// Doesn't need to return,
// takes the int to add onto
// the list
void LinkedList::append(int n){
  // we have to "walk" to the 
  // end of the list
  //Node* cursor = head; // not for dll

  //printf("Appending %d...\n",n);

  // Allocating the new list node
  // and setting up its data
  Node* newNode = new Node;
  newNode->next = nullptr;
  newNode->prev = nullptr;
  newNode->val  = n;

  // for safety...
  // check if list is empty
  if( head == nullptr ){
    head = newNode;
    tail = newNode;
    return;
  }

  tail->next = newNode;
  newNode->prev = tail;
  tail = newNode; // move the marker
}

// Takes a target int and removes it
// from the list, if present
void LinkedList::remove(int n){
  // step 1: figure out if n is present
  Node* target = search(n);

  // NB: target may be nullptr!
  if( target ){
    //printf("%p\n", target);
    //printf("%d\n", target->val);
    
    if( head == tail ){
      // target must be both
      head = tail = nullptr;
    } else {
      // list has at least 2 nodes
      if( target == head ){
        // only have a next
        target->next->prev = target->prev;
        head = target->next;
      } else if( target == tail ){
        // only have a prev
        target->prev->next = target->next;
        tail = target->prev;
      } else {
        // case: in the middle
        target->next->prev = target->prev;
        target->prev->next = target->next;
      }
    }

    delete target;
  }
}

// TODO: contains

// TODO: search
// Determines if n is stored in
// the list, and returns the address
// of its node if yes, null o/w.
//
// We want to use this as a
// subroutine to remove a node,
// and let main test for membership
LinkedList::Node* LinkedList::search(int n){
  Node* cursor = head;
  /*
   * If we don't find it, return
   * a null pointer!
   */
  while(cursor && cursor->val != n){
    cursor = cursor->next;
  }

  return cursor;
}


// TODO: print
void LinkedList::print(){
  printf("List contents:\n");

  Node* cursor = head;

  //while(cursor != nullptr){
  while(cursor){
    printf("%d\n", cursor->val);
    cursor = cursor->next;
  }
}

