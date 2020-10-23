#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<pthread.h>
#include<string.h>
#define NUM_THREADS 2

void *Fact(void *threadid){
	int fact=1;
	long unsigned int num=(int) threadid;
	
	printf("Welcome to the Factorial thread!\nThe Factorial of %d is -> ",num);
	while(num>0){
	fact=fact*num;
	num--;
	}
	printf("%d\n",fact);
	pthread_exit(NULL);
}
void *Sum_up(void *threadid){
	int sum=0;
	int num=(int)threadid;

	printf("Welcome to the Sum up thread!\nThe Sum_up of %d is -> ",num);
	while(num>0){
	sum=num+sum;
	num--;
	}
	printf("%d\n",sum);
	pthread_exit(NULL);
}
int main(int argc, char** argv){

	pthread_t threads[NUM_THREADS];
	int exe1,exe2,num;
	if(argc!=2){
		printf("ARG ERROR!\n");
		exit(0);
	}
	num=atoi(argv[1]);
	exe1=pthread_create(&threads[0],NULL,Fact,num);
	if(exe1){
		printf("ERROR! Return code from pthread_create() -> %d\n",exe1);
		exit(0);
	}
	exe2=pthread_create(&threads[1],NULL,Sum_up,num);
	if(exe2){
		printf("ERROR! Return code from pthread_create() -> %d\n",exe2);
		exit(0);
	}
	pthread_exit(NULL);
}
