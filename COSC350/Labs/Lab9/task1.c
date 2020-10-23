#include<unistd.h>
#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#define READ_END 0
#define WRITE_END 1
int main(int argc, char** argv){
	int data_processed;
	int file_pipes[2];
	const char some_data[]="123";	//I changed the data to test if it still works
	char* buffer=calloc(strlen(some_data),sizeof(char));	//Sets the size of buffer to the length of some_data

	strcat(buffer,some_data);	//Copy the data in some_data to buffer

	if(pipe(file_pipes)==0){
		data_processed=write(file_pipes[WRITE_END],some_data,strlen(some_data));
		printf("Wrote %d bytes\n",data_processed);
		data_processed=read(file_pipes[READ_END],buffer,strlen(buffer));
		printf("Read %d bytes: %s\n",data_processed,buffer);
		
		//This was to test what happens if you reverse read and write
		data_processed=write(file_pipes[WRITE_END],some_data,strlen(some_data));
		printf("Wrote %d bytes\n",data_processed);
		
		exit(EXIT_FAILURE);
	}
		free(buffer);
		exit(EXIT_FAILURE);
}
		//Reversing the read and write causes the program to run infinetly.  It does this because the pipe is trying to read data that doesn't exist. Therefore it keeps reading forever, and since there is no error catch, the program doesn't terminate
