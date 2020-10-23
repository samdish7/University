//Task 2.1, Lab 5
//Sam Disharoon
#include<stdio.h>
#include<utmp.h>
#include<fcntl.h>
#include<stdlib.h>
#include<unistd.h>
int openUtmpFile(){
    int file = open("/var/run/utmp", O_RDONLY);
    if(file < 0){
        printf("Can't open file!\n");
        exit(-1);
    }
    return file;
}
int main(int argc, char **argv){
    int a = openUtmpFile();
    struct utmp c;
    int n = 0;
    while(read(a, &c, sizeof(c))){
        printf("Username: %s\n", c.ut_user);
        if(c.ut_type==7)
            n++;
    }
    printf("%d is the number of USER_PROCESSes found.\n", n);
    close(a);
return 0;
}

