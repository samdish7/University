//Task 3.1, Lab 4
//Sam Disharoon

#include<stdio.h>
#include<stdlib.h>
#include<fcntl.h>
#include<sys/stat.h>
#include<sys/types.h>
#include<string.h>

int main(int argc, char** argv){
 	char* home=getenv("HOME");
	char dir1[100];
	char dir2[100];
	char dir12[100];
	umask(0);
	printf("HOME:%s\n",home);
	strcpy(dir1,"/mnt/linuxlab/home/sdisharoon1/Dir1");
	printf("Dir1 path:%s\n",dir1);
	strcpy(dir2,"/mnt/linuxlab/home/sdisharoon1/Dir2");
	printf("Dir2 path:%s\n",dir2);
	strcpy(dir12,"/mnt/linuxlab/home/sdisharoon1/Dir2/Dir12");
	printf("Dir12 path:%s\n",dir12);
	mkdir(dir1,0766);
	mkdir(dir2,0766);
	mkdir(dir12,0766);
	int in,out;
	char a;
	in=open("hello",O_RDONLY);
	chdir(dir12);
	out=open("hello",O_WRONLY|O_CREAT,0777);
	while(read(in,&a,1)>0)
	{
		write(out,&a,1);
	}
	
	int toDir12,toHello;
	if((toDir12=symlink("/mnt/linuxlab/home/sdisharoon1/Dir2/Dir12","/mnt/linuxlab/home/sdisharoon1/Dir1/toDir12"))<0)
	{
	printf("Symlink error!\n");
	exit(-1);
	}
	if((toHello=symlink("/mnt/linuxlab/home/sdisharoon1/Dir2/Dir12/hello","/mnt/linuxlab/home/sdisharoon1/Dir1/toHello"))<0)
	{
	printf("Symlink error!\n");
	exit(-1);
	}

return 0;
}
