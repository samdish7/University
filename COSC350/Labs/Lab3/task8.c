//Task 8 Lab 3
//Sam Disharoon
#include<stdlib.h>
#include<stdio.h>
#include<unistd.h>
#include<fcntl.h>

int main(int argc, char* argv[]){
        int in,out,offset,size;
        char c;
        if (argc!=3){
        printf("Arg error!\n");
        exit(1);
        }
        umask(0);
        in = open(argv[1],O_RDONLY);
        out = open(argv[2],O_RDWR|O_CREAT,0666);
        dup2(out,1);
        size=lseek(in,-1,SEEK_END);
	offset=lseek(in,0,SEEK_SET);
        while(size>=0){
        read(in,&c,1);
        if(c==10){
        printf("10\n");
        }
        else if (c==32){
        printf("32 ");
        }
        else {
        printf("%d ",c);
        }
	
        size--;
}
close (in);
close (out);
return 0;
}

