//LAB 1 BY SAM DISHAROON
//ARRAYS
#include <iostream>
#include <cmath>

double mean(int[],int);//mean function 1
double mean2(int[],int);//mean function 2
int main()//main function
{

int num,length,c;//counter,length of the array, and number to be inputted
int arr[length];//array the numbers will be store in
std::cout <<"How many numbers would you like?\n";
std::cin >> length;
for(num = 0; num < length; num++)//for loop for entering the numbers
{
std::cout << "Enter a number: ";
std::cin >> c;
arr[num] = c;
}

std::cout << "The mean of your numbers is: " << mean(arr,length) << "\n";//calls the first mean funcion
std::cout << "The mean of your numbers is: " << mean2(arr,length) << "\n";//calls the second main function
	
	
return 0;
}
double mean(int a[], int b)//code for mean function 1
{
int t;
double total = 0;
double MEAN = 0;
for(t=0;t<b;t++)
{
total += a[t];
}
MEAN = (total/b);
return MEAN;//returns the mean
}

double mean2(int a[], int b)//code for mean fucntion 2
{
int t;
double total = 0;
double MEAN = 0;
for(t=0;t<b;t++)
{
total+= *(a+t);
}
MEAN = (total/b);
return MEAN;//returns the mean
}
