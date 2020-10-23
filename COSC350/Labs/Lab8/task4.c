#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define SIZE 512
int main(int argc,char** argv){
	int len=0;
	int i,j;
	for(i=1;i<argc;i++){
		len+=strlen(argv[i])+1;	//Gets the length of the arguments passed
	}
	char* c[len];
	for(i=1;i<argc;i++){
		strcat(c,argv[i]);	//puts the arguments passed into a char* c
		strcat(c," ");
	}
	printf("Shell command~> %s\nOutput~> ",c);	//Prints out the args passed
	char b[SIZE];					//Creates a new char* with size 512 as defined above
	FILE *f;					//File for popen
	if((f=popen(c,"r"))!=NULL){	//If popen with the char* c does not equal NULL
		while(fgets(b,SIZE,f) != NULL){		//Gets the full line in the file f (f~>c with popen) and prints it out
			printf("%s",b);
		}
	}
	pclose(f);	//Closes file
	exit(0);
}

