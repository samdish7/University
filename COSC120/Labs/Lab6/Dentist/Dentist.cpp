#include <iostream>
#include <iomanip>
#include <cmath>

using namespace std;
void serviceCharge(float service);
void testCharge(float test);
void medicineCharge(float med);
int main()
{
	int number;
	float service;
	float test;
	float med;
	float total;
	cout << "Please input at one if you are a member of the dental plan\n Input any other number if you are not\n";
	cin >> number;
	if (number == 1)
	{
		cout << "Please input the service charge\n";
		serviceCharge(service);

	}
	


}
void serviceCharge(float service)
	{
		cin >> service;

	}