#include <iostream>
#include <fstream>
#include <iomanip>
#include <string>
#include <cctype>

using namespace std;





const int MAX = 100;
struct  Student
{
	string lname;
	string fname;
	string ID;
	string userName;
	int score1;
	int score2;
	int score3;
	int score4;
	int score5;
	float average;
	char grade;
};



int main()
{


	ifstream inFile;
	inFile.open("grade.dat");
	int t = 0, a=0,b=0,c=0,d=0,f=0;
	float totalscore=0,classAve=0;
	Student stu[MAX];

	cout << fixed << left << showpoint << setprecision(2);
	cout << "LastName  FirstName\tID\tUsername Scores: 1  2  3  4  5 Average Grade" << endl;
	cout << "--------------------------------------------------------------------------------" << endl;
	while (!inFile.eof())
	{

		inFile >> stu[t].lname;
		inFile >> stu[t].fname;
		inFile >> stu[t].ID;
		inFile >> stu[t].score1;
		inFile >> stu[t].score2;
		inFile >> stu[t].score3;
		inFile >> stu[t].score4;
		inFile >> stu[t].score5;

		stu[t].userName += tolower(stu[t].lname[0]);
		stu[t].userName += tolower(stu[t].fname[0]);
		stu[t].userName += stu[t].ID;

		stu[t].average = static_cast<float>((stu[t].score1 + stu[t].score2 + stu[t].score3 + stu[t].score4 + stu[t].score5)) / 5;
		if (stu[t].average >= 90 && stu[t].average <= 100)
		{
			stu[t].grade = 'A';
			a++;
		}
		else if (stu[t].average >= 80)
		{
			stu[t].grade = 'B';
			b++;
		}
		else if (stu[t].average >= 70)
		{
			stu[t].grade = 'C';
			c++;
		}
		else if (stu[t].average >= 60)
		{
			stu[t].grade = 'D';
			d++;
		}
		else
		{
			stu[t].grade = 'F';
			f++;
		}
		totalscore += stu[t].average;
		cout << stu[t].lname << " ";
		cout << stu[t].fname << "\t";
		cout << stu[t].ID << " ";
		cout << stu[t].userName << " " << " ";
		cout << stu[t].score1 << " " << " ";
		cout << stu[t].score2 << " " << " ";
		cout << stu[t].score3 << " " << " ";
		cout << stu[t].score4 << " " << " ";
		cout << stu[t].score5 << " " << " ";
		cout << stu[t].average << " " << " ";
		cout << stu[t].grade << endl;
		t++;
	}
	classAve = totalscore / t;
	cout << endl << "The Class Average is " << classAve << " " << endl;
	cout << "The total number of students with grade A is: " << a << endl;
	cout << "The total number of students with grade B is: " << b << endl;
	cout << "The total number of students with grade C is: " << c << endl;
	cout << "The total number of students with grade D is: " << d << endl;
	cout << "The total number of students with grade F is: " << f << endl;

	inFile.close();
	return 0;
}
