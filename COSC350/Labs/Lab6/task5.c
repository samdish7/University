#include<stdio.h>
#include<unistd.h>
#include<string.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<stdlib.h>
#include<signal.h>

int main(int argc, char* argv[]){
	int in,chout,pout,size,e,j;
	char c;
	
	if(argc!=2){
		printf("ARG ERROR!\n");
		exit(1);
	}
	if((in=open(argv[1],O_RDONLY))<0){
		printf("Open error!\n");
		exit(1);
	}
	size=lseek(in,(off_t)0,SEEK_END);
	pid_t pid;
	pid=fork();
	
	if(pid==0){
		
		if((chout=open("child.txt",O_WRONLY|O_CREAT,0666))==-1){
			printf("Output file error!\n");
			exit(1);
		}
		for(j=0;j<size;j++){
			lseek(in,(off_t) j,SEEK_SET);
			read(in,&c,1);
			if(c!='0'&&c!='1'&&c!='2'&&c!='3'&&c!='4'&&c!='5'&&c!='6'&&c!='7'&&c!='8'&&c!='9'){
				write(chout,&c,1);
			}	
		}	
	}

	else{
		if((pout=open("parent.txt",O_WRONLY|O_CREAT,0666))==-1){
			printf("Output file error!\n");
			exit(1);
		}
		for(e=-1;e<size;e++){
			lseek(in,(off_t) e,SEEK_SET);
			read(in,&c,1);
			if(c>='0'&&c<='9'){
				write(pout,&c,1);
			}
		}
	}
return 0;
}
