#include<stdio.h>
#include<unistd.h>
#include<fcntl.h>
#include<stdlib.h>
int main(int argc, char** argv){
	pid_t pid;
	int in,chout,pin,size,i;
	int j=0;
	char c;
	char* child="Hi, mom";	//Message that child writes to foo
	char message[10];	//Char array that stores message
	
	pid=fork();	//creates child

	if(pid==0){	//Child process

		chout=open("foo",O_WRONLY|O_CREAT,0666);//creates foo

		while(child[j]!='\0'){	//Continues writing until foo is empty

			write(chout,child[j],1);	
			j++;
	}

	}
	else{		//Parent process
	int stat_val;
	pid_t child_pid=wait(&stat_val);		//waits for child
		pin=open("foo",O_RDWR);
		size=lseek(pin,0,SEEK_END);	//Finds size of file

		for(i=0;i<size;i++){			//Reads each char from file and puts them in message
			lseek(pin,i,SEEK_SET);	
			read(pin,&c,1);
			message[i]=c;
		}
		printf("My son said %s\n",message);	//Prints out message
	}
	close(chout);
	close(pin);
return 0;
}
