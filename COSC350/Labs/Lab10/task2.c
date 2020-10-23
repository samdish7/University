//task2.c ~> Lab 10
//Sam Disharoon

#include<stdlib.h>
#include<stdio.h>
#include<unistd.h>
#include<pthread.h>
#define N 10
#define NUM_THREADS 2
int count=0;
void *producer(){
	while(1){
		if(count==N){
			printf("FULL!\n");
			sleep(1);
		}
		else{
			printf("%d\n",count);
			count=count+1;
		}
	}
	pthread_exit(NULL);
}
void *consumer(){
	while(1){
		if(count==0){
			printf("EMPTY!\n");
			sleep(1);
		}
		else{
			printf("%d\n",count);
			count=count-1;
		}
	}
	pthread_exit(NULL);
}
int main(int argc, char** argv){
	pthread_t threads[NUM_THREADS];
	int exe1,exe2;
	exe1=pthread_create(&threads[0],NULL,producer,NULL);
	if(exe1){
		printf("THREAD ERROR! Return code from pthread_create ~> %d\n",exe1);
		exit(1);
	}
	exe2=pthread_create(&threads[1],NULL,consumer,NULL);
	if(exe2){
		printf("THREAD ERROR! Return code fomr pthread_create ~> %d\n",exe2);
		exit(1);
	}
	pthread_exit(NULL);
	return 0;
}
