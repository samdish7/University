//Lab 3, Task 2
//Sam Disharoon
#include<unistd.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<stdlib.h>
#include<stdio.h>
int main(int argc, char *argv[]){
	int infile,outfile;
	char a;
	
	if(argc!=3){
		printf("Arg Error!\n");
		exit (0);
	}
	
	if((infile=open(argv[1],O_RDONLY))==-1)
		printf("Open Error\n");
	
	umask(0);
	outfile=open(argv[2],O_RDWR|O_CREAT,0666);
	
	//printf("int~>%d\nout~>%d\n",infile,outfile);

       	while (read(infile,&a,1)==1)
       		write (outfile,&a,1);
	close (infile);
 	close (outfile);
exit (0);	
}
