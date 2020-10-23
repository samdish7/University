//Task 1 Lab 3
//Sam Disharoon
//This program copies the contents of a file to another file with input/output redirection
#include<unistd.h>
#include<stdlib.h>
#include<stdio.h>

int main(int argc, char** argv){
	int nread;
	char a[1];
	/*if(argc != 3){
		printf("Argument error!\n");
		exit (0);
	}*/
	while((nread=read(STDIN_FILENO,a,1)>0)){
		if(write (STDOUT_FILENO,a,nread)!=nread){
			printf("Write Error!\n");
			exit (0);
		}
		if (nread<0){
			printf("Read Error!\n");
			exit (0);
		}
	}
	


return 0;
}
