#include <iostream>
#include <iomanip>
#include <cmath>
using namespace std;
// This program will demonstrate the scope rules.
// Samuel Disharoon
const double PI = 3.14;
const double RATE = 0.25;
void findArea(float, float&);
void findCircumference(float, float&);
int main()
{
	cout << fixed << showpoint << setprecision(2);
	float radius = 12;
	cout << " Main function outer block "<< endl;
		cout << "Pi, RATE, and radius are active here." << endl << endl;
	{
		float area;
		cout << "Main function first inner block" << endl;
		cout << "PI, RATE, radius, and area are active here" << endl << endl;
		findArea(radius, area);
		cout << "The radius = " << radius << endl;
		cout << "The area = " << area << endl << endl;
	}
	{
		float radius = 10;
		float circumference;
		cout << "Main function second inner block" << endl;
		cout << "PI, RATE, radius, and circumference are active here" << endl << endl;

		findCircumference(radius, circumference);
			cout << "The radius = " << radius << endl;
		cout << "The circumference = " << circumference << endl << endl;
	}
	cout << "Main function after all the calls" << endl;
	cout << "PI, RATE, and radius are active here" << endl << endl;
	cout << "The radius is now " << radius << endl;
	return 0;
}
// ****************************************************************
//
// findArea
//
// task: This function finds the area of a circle given its radius
// data in: radius of a circle
// data out: answer (which alters the corresponding actual parameters
//
//******************************************************************
void findArea(float rad, float& answer)
{
	cout << "Area = PI*r2(squared)" << endl << endl;
	cout << "PI, RATE, rad, and answer are active here" << endl << endl;
	answer = pow(rad,2)*PI;
}
// ****************************************************************
//
// findCircumference
//
// task: This function finds the circumference of a circle given its radius
// data in: radius of a circle
// data out: distance (which alters the corresponding actual parameters
//
//******************************************************************
void findCircumference(float length, float& distance)
{
	cout << "Circumference = 2*PI*r" << endl << endl;
	cout << "PI, RATE, length, and distance are actvie here" << endl << endl;
	distance = PI * length * 2;
}