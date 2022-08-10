#include <iostream>
using namespace std;

class Square
{
private:
	float side;
public:
	Square(float side);
	Square();
	~Square();
	void setSide(float length);
	float findArea();
	float findPerimeter();
};

int main()
{
	Square  box1(9);          // box is defined as an object of the Square class
	        // size contains the length of a side of the square  

	
	
	
	cout << "The area of this square is: " << box1.findArea() << endl;

	cout << "The perimeter of this square is: " << box1.findPerimeter() << endl;

	return 0;
}

//__________________________________________________________________
//Implementation section     Member function implementation




//**************************************************
//                 findArea
//
// task:    This finds the area of a square
// data in: none (uses value of data member side)
// data returned:  area of square
//***************************************************

float Square::findArea()
{
	return side * side;
}
//**************************************************
//                 findPerimeter
//
// task:    This finds the perimeter of a square
// data in: none (uses value of data member side)
// data returned:  perimeter of square
//***************************************************	
float Square::findPerimeter()
{
	return 4 * side;
}

Square::Square()
{
	side = 1;
}
Square::Square(float length)
{
	side = length;
}
Square::~Square()
{}