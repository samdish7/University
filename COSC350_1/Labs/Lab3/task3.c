//Lab 3 Task 3
//Sam Disharoon
#include<stdlib.h>
#include<stdio.h>
#include<unistd.h>
#include<sys/stat.h>
#include<fcntl.h>
int main(int argc, char *argv[]){
	int infile,outfile,nbyte;
	char buffer[32];
	umask(0);
	infile=open(argv[1],O_RDONLY);
	outfile=open(argv[2],O_RDWR|O_CREAT,0770);
	
	if((nbyte=read(infile,buffer,32)>0)){
		write(outfile,buffer,32);
	}
	
		close(infile);
		close(outfile);

return 0;
}
