#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
extern char** environ;

char* mygetenv(char* n){
	int nlen = strlen(n),x;
	for(x=0;environ[x]!='\0';x++){
		int j=0;
	while(environ[x][j]!='\0'){
		if(j>nlen&&environ[x][j]=='='){
		break;
		}
		else if(nlen==j&&environ[x][j]=='='){
		return environ[x];
		}
		else if(n[j]!=environ[x][j]){
		break;
		}
		else
		j++;
		}


	}
	
}

int main(int argc, char** argv){
	printf("The environment(using mygetenv) to home is: %s\n\n\n",mygetenv("HOME"));
	printf("The environment(using mygetenv) to path is: %s\n\n\n",mygetenv("PATH"));
	printf("The environment(using getenv) to home is: %s\n\n\n",getenv("HOME"));
	printf("The environment(using getenv) to path is: %s\n\n\n",getenv("PATH"));
	
return 0;
}
