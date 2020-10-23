#include<iostream>
#include<stdio.h>

#include "intdlist.h"

void takesListByValue(LinkedList l){
  // if copy ctor is implemented
  // properly, modifying l does not
  // modify the actual parameter
  // passed by the calling function!
  l.append(1000);
  l.append(1000);
  l.append(1000);
  l.append(1000);
  l.print();
  l.remove(10);
  l.print();
}

int main(){
  LinkedList list;
  list.append(10);
  list.append(5);
  list.append(7);
  list.append(17);

  list.print();

  list.remove(17);
  list.print();
  list.append(20);
  list.print();
  list.remove(5);
  list.print();
  list.remove(10);

  list.print();

  return 0;
}
