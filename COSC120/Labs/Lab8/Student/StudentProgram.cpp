#include <iostream>
using namespace std;
void displayArray(int[], int);
int binarySearch(int[], int, int);
void bubbleSort(int[], int);
float meanData(int[], int, float&);



const int SIZE = 50;



int main()
{
	int n, found, value;
	int number[SIZE];
	float mean = 0;

	cout << "Hello, how many numbers would you like to input? (No more than 50 please)" << endl;
	cin >> n;

	while (n > 50 || n < 0)
	{
		cout << "This is an invalid input, please try again.";
		cin >> n;
	}
	for (int count = 0; count < n; count++)
	{
		cout << "Enter a number:" << endl;
		cin >> number[count];
	}
	cout << "Here is your array before it is sorted:" << endl;
	displayArray(number, n);

	bubbleSort(number, n);
	cout << "Ive just sorted your array in ascending order...." << endl << endl;


	cout << "Now what number would you like to search for?" << endl;
	cin >> value;

	found = binarySearch(number, n, value);

	if (found == -1)
	{
		cout << "The number " << value << " is not in this array." << endl;
	}
	else
	{
		cout << "The number " << value << "has been found in position " << found + 1 << "in the array" << endl;

	}

	cout << "Your array after being sorted in acsending order is:\n" << endl;
	displayArray(number, n);



	mean = meanData(number, n, mean);
	cout << "The mean of the data set is " << mean << "." << endl;
	return 0;
}
int binarySearch(int array[], int e, int value)

{
	int first = 0;				    
	int last = e - 1;	    
	int middle;					   
	

	while (first <= last)
	{
		middle = first + (last - first) / 2;

		if (array[middle] == value)
			return middle;		       

		else if (array[middle]>value)
			last = middle - 1;		  
		else
			first = middle + 1;		   
	}

	return -1;					


}
void bubbleSort(int array[], int e)
{

	bool swap;
	int temp;
	int bottom = e - 1;

	
	do
	{
		swap = false;
		for (int count = 0; count < bottom; count++)
		{
			if (array[count] < array[count + 1])
			{	            
				temp = array[count];
				array[count] = array[count + 1];
				array[count + 1] = temp;
				swap = true; 
			}
		}
		bottom--;     

	} while (swap != false);
	

}
void displayArray(int array[], int elems)    	
{
	for (int count = 0; count < elems; count++)
	{
		cout << array[count] << "  ";
	}
	cout << endl;
}

float meanData(int array[], int e, float& mean)
{
	int total;
	for (int count = 0; count < e; count++)
	{
		total += array[count];
	}
	mean = total / e;
	return mean;

}
