#include <iostream>
#include <iomanip>

using namespace std;


// This program uses a structure to hold data about a rectangle 
// It calculates the area and perimeter of a box 

// Sam Disharoon

struct rect 
{
	float length;
	float width;
};
struct results 
{
	float area;
	float perimeter;
};

struct rectangle
{
	results result;
	rect size;

};

float getArea(float, float);
float getPerimeter(float, float);

int main()
{
	rectangle box;

	cout << "Enter the length of a rectangle: ";

	cin >> box.size.length;

	cout << "Enter the width of a rectangle: ";

	cin >> box.size.width;

	cout << endl << endl;
	box.result.area = getArea(box.size.length, box.size.width);
	box.result.perimeter = getPerimeter(box.size.length, box.size.width);
	cout << fixed << showpoint << setprecision(2);
	cout << "The area of the rectangle is " << box.result.area << endl;
	cout << "The perimeter of the recangle is " << box.result.perimeter << endl;

	return 0;
}

float getArea(float leg, float wid)
{
	float area;
	area = leg*wid;

	return area;

}
float getPerimeter(float leg, float wid)
{
	float perimeter;
	perimeter = (2 * leg) + (2 * wid);
	return perimeter;


}
