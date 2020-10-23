#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>
#include<pthread.h>
#define MAX 256

int main(int argc, char** argv){
	int n,fd[2],num1,num2,count=0;
	pid_t pid;

	char s[MAX],r[MAX];
	pipe(fd);	//~> create pipe
	pid=fork();	//~> create child
	
	if(pid>0){
		close(fd[0]);
		printf("Enter two integers\n");
		while((n=read(STDIN_FILENO,s,MAX))>0){
			
			write(fd[1],s,n);
			printf("Enter 2 integers\n");
		}
	}
	else{
		close(fd[1]);
		while((n=read(fd[0],r,MAX))>0){
			if(sscanf(r,"%d%d",&num1,&num2)==2){
				sprintf(r,"%d\n",num1+num2);
				n=strlen(r);
				(write(STDOUT_FILENO,r,n));
				
			}
			else{
				write(STDOUT_FILENO,"ERROR!\n",7);
			}
		}
	}
	return 0;
}
