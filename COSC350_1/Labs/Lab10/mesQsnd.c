//mesQsnd.c ~> Lab 10
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

	if((key = ftok("msgQsnd.c",'B'))==-1){
		perror("ftok error!\n");
		exit(1);
	}

	if((msqid=msgget(key,0644|IPC_CREAT))==-1){
		perror("msgget error!\n");
		exit(1);
	}
	
	printf("Enter two integers, ^D to quit ~> ");
	char* input = calloc(100,sizeof(int));
	buf.mtype=1;
	while(fgets(input,100,stdin),!feof(stdin)){
		if(sscanf(input,"%d%d",&buf.uno,&buf.dos)==2){
			if(msgsnd(msqid,(MSGBUF*)&buf,2*sizeof(int),0)==-1){
				perror("msgsnd error\n");
			}
			printf("Enter two integers ~> ");
		}
		else{
			printf("NOT TWO INTEGERS!!!\n");
		}
	}
	if(msgctl(msqid,IPC_RMID,NULL)==-1){
		perror("msgctl error!\n");
		exit(1);
	}
	free(input);
	return 0;
}
