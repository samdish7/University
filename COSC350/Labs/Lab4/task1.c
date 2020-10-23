//Lab 4, Task :
//Sam Disharoon
#include<stdio.h>
#include<stdlib.h>
#include<fcntl.h>
#include<unistd.h>
#include<sys/stat.h>
#include<string.h>
int convIntToStr(char* str, int x){
	sprintf(str,"%d",x);
	return(strlen(str));
}
int str_to_int(char* str){
	int i=0;
	int n=0;
	while(str[i]!='\0'){
	n=10*n+(str[i]-'0');
	i++;
}
return n;

}
int main(int argc, char* argv[]){
	int in,size,g;
	char c;
	char str[80];
	int num=0;
	//This checks for number of args error
	if(argc!=2){
	printf("Argument error!\n");
	exit(1);
	}
	//This checks if the file exists or not
	if((in=open(argv[1],O_RDONLY))<0){
	printf("Can't open file!\n");
	exit(1);
	}
	int i=0;
	printf("%d fd\n",in);
	//This extracts the digits from the file
	while(read(in,&c,1)!=0){	
		if(c>='0'&&c<='9'){
		str[i]=c;
		i++;
		}
	}
	//This converts the string to and integer
	num=str_to_int(str);
	//This displays the integer
	printf("The integer found in file is: %d\n",num);
	num=num+10;
	//This displays the integer after adding 10
	printf("The integer now equals: %d\n",num);
	//This converts the integer back into a string
	g=convIntToStr(str,num);
	//This displays it to standard output
	printf("The integer is now a string:%s\nThe length of the string is: %d\n",str,g);
	close(in);
return 0;
}
