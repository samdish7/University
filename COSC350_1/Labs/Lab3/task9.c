//Task 9 Lab 3
//Sam Disharoon
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<fcntl.h>
int palind(int fd1, int fd2){
        char a,b;
        int n;
        int size=lseek(fd1,-1,SEEK_END)+1;
        for(n=0;n<size-1;n++){
        lseek(fd1,n,SEEK_SET);
        read(fd1,&a,1);

        lseek(fd2,(-2-n),SEEK_END);
        read(fd2,&b,1);
        if(a!=b){
        return 0;
        }
}
return 1;
}

int main(int argc, char *argv[]){
        int desc1,p;
        int desc2;
        if(argc!=2){
        printf("Need 2 arguments!\n");
        exit(1);
        }
        if((desc1=open(argv[1],O_RDONLY))<0){
        printf("Can't open file!\n");
        exit(1);
        }
        desc2=dup(desc1);
        p=palind(desc1,desc2);
        if(p==1){
        printf("Contains a palindrome!\n");
        }
        else{
        printf("Does not contain a palindrome!\n");
        }
        close(desc1);
        close(desc2);
return 0;
}

