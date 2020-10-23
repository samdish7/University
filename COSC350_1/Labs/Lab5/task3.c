//Task 3 Lab 5
//Sam Disharoon

#include <stdio.h>
#include <time.h>
#include <string.h>
char *myasctime(const struct tm *timeptr){
         static char r[50]; 

         static char weekday[7][3] = {
            "Sun","Mon","Tue","Wed","Thu","Fri","Sat"
        };

         static char month[12][3] = {
            "Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"
        };
        sprintf(r, "%.3s", weekday[(*timeptr).tm_wday]);
        sprintf(r, "%s %.3s", r, month[(*timeptr).tm_mon]);
    sprintf(r, "%s  %d %.2d:%.2d:%.2d %d", r, (*timeptr).tm_mday, (*timeptr).tm_hour, (*timeptr).tm_min, (*timeptr).tm_sec, (*timeptr).tm_year+1900);
    return r;
}
int main(int argc, char **argv){
    struct tm t;
    t.tm_sec=53;
    t.tm_min=10;
    t.tm_hour=3;
    t.tm_mday=15;
    t.tm_mon=9;
    t.tm_year=19;
    t.tm_wday=3;
    t.tm_yday=300;
    t.tm_isdst=0;
        printf("asctime:\n");
    	printf("%s\n",asctime(&t));
        printf("myasctime:\n");
    	printf("%s\n",myasctime(&t));

    return 0;
}
