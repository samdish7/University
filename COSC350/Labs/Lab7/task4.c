#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>
#include<signal.h>

void sig_handler(int sig){		//Catches signals
	if(sig==SIGUSR1)
		printf("Hi Honey! Anything wrong?\n");
	
	if(sig==SIGUSR2)
		printf("Do you make trouble again?\n");
}

int main(int argc, char** argv){
	
	pid_t pid,pid2;
	int stat_val=0;
	signal(SIGUSR1,sig_handler);	//To catch SIGUSR1
	signal(SIGUSR2,sig_handler);	//To catch SIGUSR2
	
	pid=fork();		//Creates first child
	
	if(pid==0){		//Code for the first child
		
			kill(getppid(),SIGUSR1);	//Sends signal to parent
			exit(0);		
		}
		waitpid(pid,NULL,0);	//Waits for second child to finish
		pid2=fork();		//Creates second child
		
		if(pid2==0){		//Code for the second child
			kill(getppid(),SIGUSR2);	//Sends signal to parent
			exit(0);
		}
		waitpid(pid2,NULL,0);	//Waits for second child to finish
		
	return 0;
}
