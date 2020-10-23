#include<stdio.h>
#include<stdlib.h>
#include<pthread.h>
#include<unistd.h>
#include<string.h>
#define NUM_THREADS 4

pthread_mutex_mutex1=PTHREAD_MUTEX_INITIALIZER;
int scores[20];
int size=0;

void *th1(){
	int k,num;
	char i[20];
	printf("Enter -1 to stop entering scores\n");
		for(k=0;k<20;k++){
			scanf("%s",i);
			num=atoi(i);
			if(num==-1){
				break;
			}
			else{
				scores[k]=num;
				size++;
			}
		}	
	pthread_exit(NULL);
}
void *th2(){
	
	int i,j,sum=0,x,y;
	for(i=0;i<20;i++){
		sum+=scores[i];
	}
	int average;
	average=sum/20;
	int copy[20];
	memcpy((void*)copy,scores,20*sizeof(int));
	
	for(i=0;i<size-1;i++){
		for(j=i;j<size;j++){
			if(copy[j]<copy[i]){
				x=copy[i];
				y=copy[j];
				copy[j]=x;
				copy[i]=y;
			}
		}
	}
	
	printf("Median ~> %d\n",copy[size/2]);
	printf("Average -> %d\n",average);
	pthread_exit(NULL);
}
void *th3(){
	int x,y,i,j;
	for(i=0;i<size-1;i++){
		for(j=i;j<size;j++){
			if(scores[i]>scores[j]){
				x=scores[i];
				y=scores[j];
				scores[j]=x;
				scores[i]=y;
			}
		}
	}
	printf("Max ~> %d----Min ~> %d\n",scores[size-1],scores[0]);
	pthread_exit(NULL);
}
void *th4(){
	int i=0;
	for(i;i<size;i++){
		scores[i]=0;
	}
	pthread_exit(NULL);
}
int main(int argc, char** argv){
	int i;
	pthread_t threads[NUM_THREADS];

	pthread_create(&threads[0],NULL,th1,NULL);
	pthread_join(threads[0],NULL);

	printf("Array filled\n");
	for(i=0;i<size;i++){
		printf("%d ",scores[i]);
	}
	printf("\n");
	
	pthread_create(&threads[1],NULL,th2,NULL);
	pthread_create(&threads[2],NULL,th3,NULL);
	pthread_join(threads[1],NULL);
	pthread_join(threads[2],NULL);

	pthread_create(&threads[3],NULL,th4,NULL);
	pthread_join(&threads[3],NULL);
	printf("Array is cleared ~>\n");
	for(i=0;i<size;i++){
		printf("%d ",scores[i]);
	}
	printf("\n");

exit(0);
}
