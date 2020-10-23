//Task 3.2 Lab 4
//Sam Disharoon

#include <stdio.h>
 #include <stdlib.h>
 #include <sys/stat.h>
 #include <fcntl.h>
 #include <sys/types.h>

 int main(){
   remove("toHello");
   remove("toDir12");
   remove("hello");
   mkdir("toDir12/foo3",0777);
   return 0;
}
