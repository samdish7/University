#include<iostream>
#include<stdio.h>

// indepedent list (database)
// of integers

class LinkedList{
  private:
    // structured data type,
    // whose precise, fully-qualified
    // type is LinkedList::Node
    struct Node{
      Node* next;
      int   val;
    };

    // a crude way to initialize
    // to the null address
    // use -std=c++11 to compile
    Node* head = nullptr;

  public:
    LinkedList(){
      /*
      printf("Sizeof(Node): %d\n",
          sizeof(LinkedList::Node));
      printf("sizof(int): %d\n",
          sizeof(int));
      printf("sizof(Node*): %d\n",
          sizeof(Node*));
      */
    }
    // TODO: destructor
    ~LinkedList(){
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
    void append(int n){
      // we have to "walk" to the 
      // end of the list
      Node* cursor = head;

      printf("Inserting %d...\n",n);

      // Allocating the new list node
      // and setting up its data
      Node* newNode = new Node;
      newNode->next = nullptr;
      newNode->val  = n;

      // for safety...
      // check if list is empty
      if( head == nullptr ){
        head = newNode;
        return;
      }

      // while there is another node
      // on the list, move "right"
      while(cursor->next != nullptr){
        // move to the "right"
        printf("stepping past %d\n",
            cursor->val);
        cursor = cursor->next;
      }
      cursor->next = newNode;
    }

    // TODO: remove
    // Takes a target int and removes it
    // from the list, if present
    void remove(int n){
      // step 1: figure out if n is present
      Node* target = search(n);

      // NB: target may be nullptr!
      if( target ){
        //printf("%p\n", target);
        //printf("%d\n", target->val);
        Node* cursor = head;
        // safe?
        // cursor == nullptr if
        // 1) head is target, or
        if( cursor == target ){
          // means target is the first elem.
          // cursor == head == target
          head = head->next;
        } else {
          // 2) moves off end, not possible
          while(cursor->next != target){
            cursor = cursor->next;
          }
          // cursor->next is now target
          cursor->next = target->next;
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
    Node* search(int n){
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
    void print(){
      printf("List contents:\n");

      Node* cursor = head;

      //while(cursor != nullptr){
      while(cursor){
        printf("%d\n", cursor->val);
        cursor = cursor->next;
      }
    }
};

int main(){
  LinkedList list;
  list.append(10);
  list.append(5);
  list.append(7);
  list.append(17);

  list.print();

  list.remove(17);

  list.print();

  /*
  printf("Location of 10: %p\n",
      list.search(10));
  printf("Location of 17: %p\n",
      list.search(17));
  */

  return 0;
}
