#include <iostream>
#include <cmath>
using namespace std;
//_________________________________________________________________________
// This program declares a class for a circle that will have 
// member functions that set the center, find the area, find
// the circumference and display these attributes.
// The program as written does not allow the user to input data, but
// rather has the radii and center coordinates of the circles (spheres in the program) 
// initialized  at declaration or set by a function.

//class declaration section   (header file)

class Circles
{
public:
	double findArea();
	double findCircumference();
	void printCircleStats(); // This outputs the radius and center of the circle. 
	Circles(float r, int x, int y); 
	Circles(float r);
	Circles(int x, int y);
	Circles();
	~Circles();
private:
	float  radius;
	int    center_x;
	int    center_y;
};


const double PI = 3.14;

//Client section   

int main()
{
	Circles sphere(8,9,10);
	sphere.printCircleStats();
	cout << "The area of the circle is " << sphere.findArea() << endl;
	cout << "The circumference of the circle is "
		<< sphere.findCircumference() << endl;
	Circles sphere1(2);
	sphere1.printCircleStats();
	cout << "The area of the circle is " << sphere1.findArea() << endl;
	cout << "The circumference of the circle is "
		<< sphere1.findCircumference() << endl;
	Circles sphere2;
	sphere2.printCircleStats();
	cout << "The area of the circle is " << sphere2.findArea() << endl;
	cout << "The circumference of the circle is "
		<< sphere2.findCircumference() << endl;
	Circles sphere3(15, 16);
	sphere3.printCircleStats();
	cout << "The area of the circle is " << sphere3.findArea() << endl;
	cout << "The circumference of the circle is "
		<< sphere3.findCircumference() << endl;
	return 0;
}

//___________________________________________________________________________
//Implementation section     Member function implementation

Circles::Circles()
{
	radius = 1;
	center_x = 0;
	center_y = 0;
}
Circles::Circles(float r , int x, int y)
{
	radius = r;
	center_x = x;
	center_y = y;
}
Circles::Circles(float r)
{
	radius = r;
	center_x = 0;
	center_y = 0;
}
Circles::Circles(int x, int y)
{
	center_x = x;
	center_y = y;
	radius = 1;
}

double Circles::findArea()
{
	double area;
	area = PI*pow(radius, 2);
	return area;
}

double Circles::findCircumference()
{
	double circum;
	circum = 2 * PI*radius;
	return circum;
}



void Circles::printCircleStats()
// This procedure prints out the radius and center coordinates of the circle
// object that calls it.

{
	cout << "The radius of the circle is " << radius << endl;
	cout << "The center of the circle is (" << center_x
		<< "," << center_y << ")" << endl;
}

Circles::~Circles()
{
	cout << "This concludes this Circles class" << endl;
}