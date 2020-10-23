#include<stdio.h>
#include<string.h>
#include<unistd.h>
#include<sys/types.h>
#include<stdlib.h>
int main(){
	int pid;
	pid=fork();
	
	if(pid>0){

		sleep(30);
	}
	else{exit(0);}

	return 0;
}
