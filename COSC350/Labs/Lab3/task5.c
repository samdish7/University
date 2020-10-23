//Lab 3 Task 5
#include<stdlib.h>
#include<stdio.h>
#include<fcntl.h>
#include<sys/stat.h>
#include<unistd.h>
 
int main(int argc, char *argv[]) {
 
    int infile, outfile, n;
    char buf;
    int filesize;
    int i;
 
    if (argc != 3) {
        printf("Need 3 arguments!\n");
        exit(-1);
    }
 
    if ((infile = open(argv[1], O_RDONLY)) < 0) {
        printf("Open file error\n");
        exit(-1);
    }
	umask(0); 
    if ((outfile = creat(argv[2], 0760)) < 0) {
        printf("Create file error\n");
        exit(-1);
    }
 
    filesize = lseek(infile, 0, SEEK_END);
 
    for (i = filesize - 1; i >= 0; i--) {
        lseek(infile,  i, SEEK_SET);
        n = read(infile, &buf, 1);
        if (n != 1) {
            printf("Read error\n");
            exit(-1);
        }
        n = write(outfile, &buf, 1);
        if (n != 1) {
            printf("Write error\n");
            exit(-1);
        } 
    }
    close(infile);
    close(outfile);
    return 0;
}
