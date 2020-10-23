#include<stdio.h>
#include<unistd.h>
#include<sys/wait.h>
#include<sys/types.h>
#include<stdlib.h>
int st_to_int(char* str){
	int i=0,n=0;
	while(str[i]!='\0'){
	n=10*n+(str[i]-'0');
	i++;
}
return n;
}

int main(int argc, char* argv[]){
	printf("Now we are in child.c\nNum args passed is: %d\n",(argc-1));
	int stat_val,Nc,Tc,exit_code;
	if(argc!=4){
	printf("ARG ERROR!\n");
	exit(0);
}	
	exit_code=37;
	char* word=argv[0];
	Nc=st_to_int(argv[1]);
	Tc=st_to_int(argv[2]);
	pid_t child_pid;
	printf("The values of the child program are as follows:\nNc=%d\nTc=%d\nMessage=%s\n",Nc,Tc,word);
	child_pid=wait(&stat_val);
	for(;Nc>0;Nc--){
	printf("%s %d\n",word,Nc);
	sleep(Tc);
	}
	printf("Child has finished  PID=%d\n",child_pid);
	if(WIFEXITED(stat_val)){
		printf("Child died with exit code %d\n",WEXITSTATUS(stat_val));
	}
	else{
	printf("Child died abnormally\n");
	}
	
	
exit(exit_code);

}
