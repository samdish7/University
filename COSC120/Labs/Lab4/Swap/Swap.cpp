#include <iostream>
#include <cmath>
#include <iomanip>

using namespace std;
void firstNum(int&);
void secondNum(int&);
void swap();

int main()
{
	int Num1;
	int Num2;
	cout << "Enter the first number\n Then hit Enter" << endl;
	firstNum(Num1);
	cout << "Enter the second number\n Then hit Enter" << endl;
	secondNum(Num2);
	cout << "You input the numbers as " << Num1 << " and " << Num2 << "." << endl;
	swap(Num1, Num2);




	return 0;
}

void firstNum(int& num1)
{
	cin >> num1;

}
void secondNum(int& num2)
{

	cin >> num2;

}
void swap(int& num1 , int& num2)
{
	cout << "After swapping, the first number has the value of " << num2 << " which was the value of the second number.";

}
