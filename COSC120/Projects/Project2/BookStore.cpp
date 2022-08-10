#include <iostream>
#include <cmath>
#include <iomanip>
#include <fstream>
#include <string>
using namespace std;

int totalBooks(int[][6], int);
void TotalRow(int[][6], int);
void LowRow(int[][6], int);
void showArray(int[][6], int);

const int numrows = 12;
const int numcols = 6;

int main()
{

	int numBooks[numrows][numcols];
	int totalrow = 0;
	int total = 0, size = 0;
	int lowR;
	int highR;
	int lowC;
	int highC;
	ifstream InSale;
	InSale.open("Sale.dat");
	for (int rows = 0; rows < numrows; rows++)
	{

		for (int cols = 0; cols < numcols; cols++)
		{
			InSale >> numBooks[rows][cols];
			totalBooks(numBooks, total);
		}

	}


	cout << "The book store sold a total of " << totalBooks(numBooks, total) << " books." << endl << endl;
	showArray(numBooks, size);
	TotalRow(numBooks, totalrow);
	cout << endl;
	InSale.close();
	return 0;
}

int totalBooks(int numbooks[][6], int total)
{

	for (int rows = 0; rows < numrows; rows++)
	{

		for (int cols = 0; cols < numcols; cols++)
		{
			total += numbooks[rows][cols];
		}

	}

	return total;
}

void TotalRow(int array[][6],  int total)

{
	

for (int rows = 0; rows < numrows; rows++)
	{
		int total = 0;
		for (int cols = 0; cols < numcols; cols++)
		{
			total += array[rows][cols];
		
		}
		cout << total << endl;
	}


}




void showArray(int books[][6], int size)
{

	int rows;
	int cols;
cout << "Month\t Math\t CS\t Phy\t Chem\t Bio\t Geo\t Total\t High\t Low\n"<<endl;
	for (rows = 0; rows < numrows; rows++)
	{
		if (rows == 0)
		{
			cout << "Jan\t    ";
		}
		else if (rows == 1)
		{
			cout << "Feb\t    ";
		}
		else if (rows == 2)
		{
			cout << "Mar\t    ";
		}
		else if (rows == 3)
		{
			cout << "Apr\t    ";
		}
		else if (rows == 4)
		{
			cout << "May\t    ";
		}
		else if (rows == 5)
		{
			cout << "Jun\t    ";
		}
		else if (rows == 6)
		{
			cout << "Jul\t    ";
		}
		else if (rows == 7)
		{
			cout << "Aug\t    ";
		}
		else if (rows == 8)
		{
			cout << "Sep\t    ";
		}
		else if (rows == 9)
		{
			cout << "Oct\t    ";
		}
		else if (rows == 10)
		{
			cout << "Nov\t    ";
		}
		else if (rows == 11)
		{
			cout << "Dec\t    ";
		}
		for (cols = 0; cols < numcols; cols++)
		{
			cout << books[rows][cols] << "\t    ";
		}
		
		cout << endl;
	}
	
}

void Lowest(int array[][6], int row[], int col[])
{
	int low;
	low = array[0][0];
	for (int rows = 0; rows < numrows; rows++)
	{
		for (int cols = 0; cols < numcols; cols++)
		{
			if (array[rows][cols] < low)
				low = array[rows][cols];
		}
	}

	cout << low;
}