//Lab 3 Task 6
//Sam Disharoon
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<fcntl.h>
#include<sys/stat.h>
int main(int argc, char* argv[]){
	int infile,outfile;
	char c;
	int offset;
	if(argc!=3){
	printf("Arg error!\n");
	exit(1);
	}
	if((infile=open(argv[1],O_RDONLY)) < 0)
	{
	printf("Can't open file\n");
	exit(1);
	}
	umask(0);
	if((outfile=open(argv[2],O_WRONLY|O_CREAT,0760))<0)
	{
	printf("Can't make file\n");
	exit(1);
	}
	offset=0;

	while(read(infile,&c,1)==1){	
		offset++;
	}
	while(offset>=0){
	pread(infile,&c,1,offset);
	write(outfile,&c,1);
	offset--;
	}
	close(infile);
	close(outfile);
	exit(0);
return 0;
}
