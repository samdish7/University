#include<sys/types.h>
#include<signal.h>
#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>

static int alarm_fired=0;

void ding(int sig){
	alarm_fired=1;
}

int main(int argc,char* argv[]){

	pid_t pid;
				
	struct sigaction act;			//sigaction def
	act.sa_handler=ding;			//handler def
	sigemptyset(&act.sa_mask);		//setting emptyset

	printf("Alarm application starting\n");
	
	pid=fork();	//Creates child
	switch(pid){
	case -1:	//If fork fails
		perror("FORK FAILED!\n");
		exit(0);
	
	case 0:		//Child process
	sleep(5);
	kill(getppid(),SIGALRM);	//Sends alarm signal to parent
	exit(0);
	}


	printf("Waiting for alarm to go off\n");
	sigaction(SIGALRM,&act,0);	//catches signal
	pause();
	if (alarm_fired){
	printf("Ding!\n");
	}
	printf("Done!\n");
	exit(0);
}
