#include <fstream>
#include <iostream>
using namespace std;

// Samuel Disharoon

const int MAXNAME = 20;

int main()
{
	ifstream inData;
	inData.open("grades.txt");

	char name[MAXNAME + 1];// holds student name
	float average;  // holds student average


	
	
	while (!inData.eof())
	{
		inData.get(name, MAXNAME + 1);
		inData >> average;
		cout << name << "has a(n) " << average << " average" << endl;
		inData.ignore();

	}

	return 0;
}