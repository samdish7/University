//Lab3 Task7
//Sam Disharoon
#include <stdio.h>

int st_to_int(char *str){
    int n = 0;
    int i = 0;
    while (str[i] != '\0'){
        n = 10 * n + (str[i] - '0');
        i++;
    }
    return n;
}

int main(int argc, char *argv[])
{
        int a,b,sum;
        int i;
        if(argc<2) //if there can be one integer
        //if(argc<3) //if there has to be at least 2
        {
        printf("Too few arguments \n");
                return -1;
        }

        sum=0;
        for(i=1; i<argc; i++)
        {
                sum += st_to_int(argv[i]);
        }

        printf("sum of all integers: %d\n",sum);

        return 0;
}
