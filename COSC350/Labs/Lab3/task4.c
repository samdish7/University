//Lab 3 Task 4
//Sam Disharoon
#include<stdlib.h>
#include<stdio.h>
#include<unistd.h>
#include<sys/stat.h>
#include<fcntl.h>
int main(int argc, char *argv[]){
	int infile1,infile2,outfile;
	int n;
	char a,b;
	if ((infile1=open(argv[1],O_RDONLY))<0)
	printf("File1 Error!\n");
	if((infile2=open(argv[2],O_RDONLY))<0)
	printf("File2 Error!\n");
	umask(0);	
	outfile=open(argv[3],O_RDWR|O_CREAT,0770);
	if((n=lseek(outfile,1,SEEK_END)<0)){
		printf("lseek Error!\n");
		exit(0);
	}
	while(read(infile1,&a,1)==1){	
		write(outfile,&a,1);	
	}
	while(read(infile2,&b,1)==1){	
		write(outfile,&b,1);	
	}
	
	close(infile1);
	close(infile2);
	close(outfile);
return 0;
}
