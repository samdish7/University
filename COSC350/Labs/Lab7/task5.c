#include<signal.h>
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>

int main(int argc, char** argv){
	int i=0,j=0;
		
	struct sigaction act;		//Creates sigaction
	sigset_t loop_1,old_set;	//Makes variables to store old and new sets
	act.sa_flags=0;			//Sets flags -> 0
	sigemptyset(&loop_1);		//Calls sigemptyset
	sigaddset(&loop_1,SIGINT);	//Adds SIGINT and SIGQUIT to the list of signals to be blocked (loop_1)
	sigaddset(&loop_1,SIGQUIT);
	sigprocmask(SIG_BLOCK,&loop_1,&old_set);	//Blocks signals in loop_1 list
	printf("SIGINT & SIGQUIT are blocked!!!\n");
	for(i;i<5;i++){			//First loop in which both SIGINT and SIGQUIT are blocked
		printf("%d seconds have passed\n",i+1);	//Counts how many seconds have passed
		sleep(1);
	}
	sigprocmask(SIG_SETMASK,&old_set,NULL);	//Resets signals back to old_set
	sigdelset(&loop_1,SIGQUIT);		//Deletes SIGQUIT from loop_1
	sigprocmask(SIG_BLOCK,&loop_1,&old_set);	//Blocks signals in loop_1 list
	printf("Now only SIGINT is blocked!!!\n");
	for(j;j<5;j++){
		printf("%d seconds have passed\n",j+1);
		sleep(1);
	}
	sigprocmask(SIG_SETMASK,&old_set,NULL);		//Resets signals back to old_set
	printf("SIGINT works now :(\nYou have 5 seconds to try before program terminates\n");
	sleep(5);

return 0;
}
