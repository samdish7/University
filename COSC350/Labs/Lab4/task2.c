//Lab 4 Task 2 
//Sam Disharoon
#include<stdio.h>
#include<stdlib.h>
#include<fcntl.h>
#include<unistd.h>
int main(int argc, char* argv[]){
	int in,out,num;
	char c[1];
	umask(0);
	if(argc !=3){
		printf("ARG ERROR!\n");
		exit(0);
	}
	in=open(argv[1],O_RDONLY);
	if (in<0){
		printf("File Open Error!\n");
		exit(0);
	}
	out=open(argv[2],O_RDWR|O_CREAT,0666);
	if(out<0){
		printf("File Create Error!\n");
		exit(0);
	}
	dup2(out,1);
	while(read(in,&c,1)>0){
		if(num<65 && c[0] != ' '){
			num = num * 10 + (c[0]-'0');
		}
		else{
			printf("%c",num);
			num=0;
		}
	}
	close(in);
	close(out);
	return 0;
}
