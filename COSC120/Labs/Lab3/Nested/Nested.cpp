// This program finds the average time spent programming by a student
// each day over a three day period.
// Samuel Disharoon
#include <iostream>
using namespace std;
int main()
{
	int numStudents;
	float pronumHours, prototal, proaverage, bionumHours, biototal, bioaverage;
	int student, day; 
	int numdays;
	cout << "This program will find the average number of hours a day"
		<< " that a student spent studying over a long weekend\n\n" << endl;
		cout << "How many days have you studied?" << endl;
		cin >> numdays;

	cout << "How many students are there ?" << endl << endl;
	cin >> numStudents;

	for (student = 1; student <= numStudents; student++)
	{
		prototal = 0;
		for (day = 1; day <= numdays; day++)
		{
			cout << "Please enter the number of hours spent programming by student "
				<< student << " on day " << day << "." << endl;
			cin >> pronumHours;
			prototal = prototal + pronumHours;
		}
		proaverage = prototal / numdays;
		cout << endl;
		cout << "The average number of hours per day spent programming by "
			<< "student " << student << " is " << proaverage
			<< endl << endl << endl;
		
		
			biototal = 0;
			for (day = 1; day <= numdays; day++)
			{
				cout << "Please enter the number of hours spent on biology by student "
					<< student << " on day " << day << "." << endl;
				cin >> bionumHours; 
				biototal = biototal + bionumHours;

			}
			bioaverage = biototal / numdays;
			cout << endl;
			cout << "The average number of hours per day spent on biology by "
				<< "student " << student << " is " << bioaverage << endl << endl << endl;
			if (bioaverage > proaverage)
				{
				cout <<  "Biology was the subject studied most by student " << student << "." << endl;
				}
			else
			{
				cout << "Programming was the subject studied most by student " << student << "." << endl;
			}
		}
	
	
	
	
	return 0;
}