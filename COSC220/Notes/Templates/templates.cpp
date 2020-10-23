#include<iostream>
#include<stdio.h>

/*
 * Demonstrate: templates
 *
 * - actually "meta-programming": a language
 *   to allow the compiler to generate code
 *   that you didn't write!
 */

struct organism{
  int numCells;
  double rate;
};

organism operator*(organism x, organism y){
  organism rtn;
  rtn.numCells = x.numCells * y.numCells;
  rtn.rate = x.rate/y.rate;
  return rtn;
}

std::ostream& operator<<(std::ostream& o, organism org){
  o << "Cells: " << org.numCells << std::endl;
  o << "Rate: " << org.rate << std::endl;
  return o;
}

template<class T>
void doStuff(T obj){
  obj.rate = 4;
}


//template <typename
template <class T>
T square(T x){
  return x * x;
}

int main(){
  // g++ sees square(int) so it compiles
  // the version with T=int
  printf("Square of %d is %d\n", 4, square(4));
  printf("Square of %d is %d\n", 10, square(10));
  // g++ sees the need for square(double)...
  printf("Square of %f is %f\n", 5.8, square(5.8));


  organism o1 = {3, 4.5};
  //organism o2({5, 4.1});;

  std::cout << square(o1) << std::endl;
  
  doStuff(o1);

  return 0;
}



















