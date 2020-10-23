//mesQrcv.c ~> Lab 10
//Sam Disharoon
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<errno.h>
#include<sys/types.h>
#include<sys/ipc.h>
#include<sys/msg.h>
typedef struct my_msgbuf{
	long mtype;
	int uno;
	int dos;
}MSGBUF;
int main(int argc, char** argv){
	MSGBUF buf;
	int msqid;
	key_t key;
	
	if((key=ftok("msgQsnd.c",'B'))==-1){
		perror("ftok error!\n");
		exit(1);
	}
	if((msqid=msgget(key,0644))==-1){
		perror("msgget error!\n");
		exit(1);
	}
	for(;;){
		if(msgrcv(msqid, (MSGBUF*)&buf, 2*sizeof(int), 0, 0)==-1){
			perror("msgrcv error!\n");
			exit(1);
		}
		printf("The sum of the digits revceived is ~> %d\n",buf.uno+buf.dos);
	}
	
	

	return 0;
}
