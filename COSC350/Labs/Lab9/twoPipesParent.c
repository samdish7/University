//twoPipesParent.c ~> Lab 9
//Sam Disharoon
#include<unistd.h>
#include<stdlib.h>
#include<stdio.h>
#include<string.h>

int main(){
	int data_processed;
	int fd1[2];
	int fd2[2];
	const char some_data[]="Hi there Kiddos!";
	char buffer1[BUFSIZ+1];
	char buffer2[BUFSIZ+1];
	pid_t fork_result;

	memset(buffer1,'\0',sizeof(buffer1));
	memset(buffer2,'\0',sizeof(buffer2));

	if(pipe(fd1)==0&&pipe(fd2)==0){
		fork_result=fork();
		if(fork_result==-1){
			fprintf(stderr,"FORK ERROR!\n");
			exit(EXIT_FAILURE);
		}	
		if(fork_result==0){
			sprintf(buffer1,"%d",fd1[0]);
			sprintf(buffer2,"%d",fd2[1]);
			(void)execl("twoPipesChild","twoPipesChild",buffer1,buffer2,(char*)0);
			close(fd2[1]);
			close(fd1[0]);
			exit(EXIT_FAILURE);
		}
		else{
			data_processed=write(fd1[1],some_data,strlen(some_data));
			close(fd1[1]);
			printf("%d ~> wrote %d bytes\n",getpid(),data_processed);
			wait(&fork_result);
			data_processed=read(fd2[0],buffer1,sizeof(buffer1));
			close(fd2[0]);
			printf("%d ~> read %d bytes: %s\n",getpid(),data_processed,buffer1);
		}
	}
	exit(EXIT_SUCCESS);
}
