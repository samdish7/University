//pipe4.c ~> twoPipesChild.c ~> Lab 9
//Sam Disharoon
#include<unistd.h>
#include<stdlib.h>
#include<stdio.h>
#include<string.h>

int main(int argc, char** argv){
	int data_processed;
	char buffer[BUFSIZ+1];
	int file_descriptor[2];
	char message[]="Hi mom";
	
	memset(buffer,'\0',sizeof(buffer));
	sscanf(argv[1],"%d",&file_descriptor[0]);
	data_processed=read(file_descriptor[0],buffer,BUFSIZ);
	close(file_descriptor[0]);
	printf("%d ~> read %d bytes: %s\n",getpid(),data_processed,buffer);
	sscanf(argv[2],"%d",&file_descriptor[1]);
	data_processed=write(file_descriptor[1],message,sizeof(message));
	close(file_descriptor[1]);
	printf("%d ~> wrote %d bytes\n",getpid(),data_processed);
	exit(EXIT_SUCCESS);

}
