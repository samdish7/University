//task3.c ~> Lab 10
//Sam Disharoon
#include<stdlib.h>
#include<stdio.h>
#include<pthread.h>
#include<sys/sem.h>
#include<sys/types.h>
#include<sys/ipc.h>
#define N 10
#define MUTEX 0
#define EMPTY 2
#define FULL 1
int array[N];
//set as a global variable for semaphore id
int semid;

//this function is used to show that it's working
void printarr(){
	int i;
	for(i=0;i<10;i++){
	printf(" %d \n",array[i]);
	}
}
//this function just creates a random number that will be 
//out item and be inserted into the array
int produce_item(){
	int num=0;
	num=rand()%(10+1-1)+1;
return num;
}
//puts the new item in next available position
void insert_item(int x){
	int count=semctl(semid,FULL,GETVAL);
	array[count]=x;
}
//removed the item from the proper position of the array
int remove_item(){
	int removed=0;
	int count=semctl(semid,FULL,GETVAL);
	removed=array[count];
return removed;
}
//replace the actual number with 0 to show it's consumed
void consume_item(int number){
	int i;
	int count=semctl(semid,FULL,GETVAL);
	for(i=N;i>=0;i--){
		if(number==array[i]){
			array[i]=0;
			break;
		}
	}
}
//we had to define our own down function using semop()
void down(int passed){
	struct sembuf buf={passed,-1,0};
	semop(semid,&buf,1);
}
//we had to define our own up function using semop()
void up(int passed){
	struct sembuf buf={passed,1,0};
	semop(semid,&buf,1);
}
//producer thread
void* producer(){
	int item;
	while(1){
		item=produce_item();
		down(EMPTY);
		down(MUTEX);
		insert_item(item);
		up(MUTEX);
		up(FULL);

		printf("PRODUCER ~> ");
		printarr();
	}
}
//consumer thread
void* consumer(){
	int item;
	while(1){
		down(FULL);
		down(MUTEX);
		item=remove_item();
		up(MUTEX);
		up(EMPTY);
		consume_item(item);
													   printf("CONSUMER ~> ");
		printarr();
	}
}
//needed for semaphore
union semun{
	int val;
	struct semid_ds *buf;
	unsigned short *arr;
};
int main(){
	key_t key;
	union semun arg;
	umask(0);

	//gets the key value for semaphore
	key=ftok("task3.c",'S');

	//create the semaphore with three numbers
	semid=semget(key,3,0666|IPC_CREAT);

	//set the first value to MUTEX
	arg.val=1;
	semctl(semid,MUTEX,SETVAL,arg);

	//set the second value to FULL
	arg.val=0;
	semctl(semid,FULL,SETVAL,arg);

	//set the third value to EMPTY
	arg.val=N;
	semctl(semid,EMPTY,SETVAL,arg);

	//create the threads for producer and consumer
	pthread_t p,c;
	pthread_create(&p,NULL,producer,NULL);
	pthread_create(&c,NULL,consumer,NULL);

	//use join so threads will run before it is over
	pthread_join(p,NULL);
	pthread_join(c,NULL);
	
	//removes the semaphore
	semctl(semid,0,IPC_RMID,arg);

	return 0;

}
