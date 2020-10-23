#include<stdio.h>
#include<unistd.h>
#include<sys/types.h>
#include<stdlib.h>

int st_to_int(char *str){
    int n = 0;
    int i = 0;
    while (str[i] != '\0'){
        n = 10 * n + (str[i] - '0');
        i++;
    }
    return n;
}

int main(int argc, char* argv[]){
	pid_t pid;
	char *message;
	int n,t,Nc,Np,Tc,Tp;
	
	if(argc!=5){
		printf("ARG ERROR!\n");
		exit(-1);
	}
	Nc = st_to_int(argv[1]);
	Np = st_to_int(argv[2]);
	Tc = st_to_int(argv[3]);
	Tp = st_to_int(argv[4]);
	printf("Fork program starting\n");
	printf("Nc=%d\nNp=%d\nTc=%d\nTp=%d\n",Nc,Np,Tc,Tp);
	pid=fork();
	
	switch(pid){
	case -1:
	printf("FORK ERROR!\n");
	exit(1);
	
	case 0:
	message="This is child";
	n=Nc;
	t=Tc;
	break;
	
	default:
	message="This is parent";
	n=Np;
	t=Tp;
	break;
}
	
	for(;n>0;n--){
	printf("%s %d\n",message,n);
	sleep(t);
	}

return 0;
}
