#include<stdio.h>
#include<stdlib.h>

long count = 0;

void hanoi(int n, int src, int dest, int tmp){
  if (n>0){
    // move first n-1 from src to tmp using dst
    // as storage
    hanoi(n-1, src, tmp, dest);

    count++;
    // take the top from src and put on dest
    //printf("Move from %d to %d\n", src, dest);

    // move n-1 from storage to the dest
    hanoi(n-1, tmp, dest, src);
  }
}

int main(int argc, char** argv){
  // convert the argument to an int
   hanoi(atoi(argv[1]), 1, 3, 2);
  printf("Count: %ld\n", count);
  return 0;
}
