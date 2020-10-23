#include<unistd.h>
#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#include<fcntl.h>
#define READ_END 0
#define WRITE_END 1
int main(int argc, char** argv){
	int data_written,fifo_pipe,data_read;
	const char some_data[]="123456";	//I changed the data to test if it still works
	char* buffer=calloc(strlen(some_data),sizeof(char));	//Sets the size of buffer to the length of some_data

	strcat(buffer,some_data);	//Copy the data in some_data to buffer

	fifo_pipe=open("task4_fifo",O_RDWR);
	data_written=write(fifo_pipe,some_data,strlen(some_data));	//writes to the fifo pipe
	printf("Wrote %d bytes\n",data_written);
	data_read=read(fifo_pipe,buffer,strlen(buffer));
	printf("Read %d bytes: %s\n",data_read,buffer);
		
		//This was to test what happens if you reverse read and write
		//data_processed=write(file_pipes[WRITE_END],some_data,strlen(some_data));
		//printf("Wrote %d bytes\n",data_processed);
	
		exit(EXIT_FAILURE);


	free(buffer);
	
	exit(EXIT_FAILURE);
}
//I used the command mkfifo /mnt/linuxlab/sdisharoon1/Documents/SP20_COSC350/Lab9/task_fifo to create the task4 fifo. I could have also created it in the pipeFIFO.c program
