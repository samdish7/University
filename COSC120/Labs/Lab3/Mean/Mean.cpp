// Samuel Disharoon
#include <iostream>
using namespace std;
int main()
{
	int n;
	int m;
	int total = 0;
	int number;
	float mean;
	cout << "Please enter a positive integer" << endl;
	cin >> n;
	cout << "Please enter another positive integer that is larger than the first" << endl;
	cin >> m;
	if (n > 0 && m > 0)
	{
		for (number = n; number <= m; number++)

			total = total + number;
	

		mean = float(total) / (m-n+1); 
		cout << "The mean average from " << n
			<< " to " << m << " is " << mean << endl;
	}
	else
		cout << "Invalid input - integer must be positive" << endl;
	return 0;
}
