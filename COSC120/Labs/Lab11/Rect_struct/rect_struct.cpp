#include <iostream>
#include <iomanip>
using namespace std;


// This program uses a structure to hold data about a rectangle 
// Sam Disharoon

struct rectangle
{
	float leng;
	float wid;
	float area;
	float per;
};
	

int main()
{
	rectangle box;

	cout << "Enter the length of a rectangle: ";

	cin >> box.leng;

	cout << "Enter the width of a rectangle: ";

	cin >> box.wid;

	cout << endl << endl;

	box.area = box.leng * box.wid;
	box.per = (2 * box.leng) + (2 * box.wid);

	cout << fixed << showpoint << setprecision(2);


	cout << "The area of the rectangle is: " << box.area << endl;
	cout << "The perimeter of the rectangle is: " << box.per << endl;
	if (box.leng == box.wid)
	{
		cout << "This is a square." << endl;
	}


	return 0;
}