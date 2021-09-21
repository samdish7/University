#include <iostream>

using namespace std;
int main()
{
		int miles;
		int hours;
		double mph;
	cout << "How many miles have you traveled? ";
		cin >> miles;
		cout << "How many hours did it take you to travel ?";
		cin >> hours;
	mph = miles / hours;
	cout << "The average miles per hour is: " << mph<<". ";
	return 0;


}