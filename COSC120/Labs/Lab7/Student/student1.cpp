// This program will input an undetermined number of student names
// and a number of grades for each student. The number of grades is 
// given by the user. The grades are stored in an array.
// Two functions are called for each student. 
// One function will give the numeric average of their grades.
// The other function will give a letter grade to that average. 
// Grades are assigned on a 10 point spread.  
// 90-100 A   80- 89 B  70-79 C   60-69 D   Below 60 F

// Sam Disharoon


#include <iostream>
using namespace std;


const  int MAXGRADE = 25;              // maximum number of grades per student
const  int MAXCHAR = 30;              // maximum characters used in a name


typedef char StringType30[MAXCHAR + 1];// character array data type used for names 
// having 30 characters or less.
typedef  float GradeType[MAXGRADE];    // one dimensional integer array data type

float findGradeAvg(GradeType, int);    // finds grade average by taking array of 
// grades and number of grades as parameters

char  findLetterGrade(float);          // finds letter grade from average given 
// to it as a parameter


int main()

{
	StringType30 firstname, lastname;  // two arrays of characters defined
	int numOfGrades;                   // holds the number of grades
	GradeType  grades;                 // grades is defined as a one dimensional array 
	float average;                     // holds the average of a student's grade
	char moreinput;                    // determines if there is more input

	// Input the number of grades for each student

	cout << "Please input the number of grades each student will receive." << endl
		<< "This number must be a number between 1 and " << MAXGRADE << " inclusive" << endl;

	cin >> numOfGrades;

	while (numOfGrades > MAXGRADE || numOfGrades < 1)
	{
		cout << "Please input the number of grades for each student." << endl
			<< "This number must be a number between 1 and " << MAXGRADE << " inclusive" << endl;

		cin >> numOfGrades;

	}

	// Input names and grades for each student

	cout << "Please input a y if you want to input more students"
		<< " any other character will stop the input" << endl;
	cin >> moreinput;

	while (moreinput == 'y' || moreinput == 'Y')

	{
		cout << "Please input the first name of the student" << endl;
		cin >> firstname;
		cout << endl << "Please input the last name of the student" << endl;
		cin >> lastname;

		for (int count = 0; count < numOfGrades; count++)

		{

			cout << endl << "Please input a grade" << endl;

			cin >> grades[count];

		}

		cout << firstname << ' ' << lastname << " has an average of ";

		average = findGradeAvg(grades, numOfGrades);
		cout << average;
		cout << " which gives the letter grade of " << findLetterGrade(average) << endl;
		cout << endl << endl << endl;
		cout << "Please input a y if you want to input more students"
			<< " any other character will stop the input" << endl;
		cin >> moreinput;

	}

	return 0;
}

//***********************************************************************
//                              findGradeAvg
//
//  task:           This function finds the average of the 
//                  numbers stored in an array.
//            
//  data in:        an array of integer numbers
//  data returned:  the average of all numbers in the array
//
//***********************************************************************

float findGradeAvg(GradeType array, int numgrades)

{

	float sum = 0;
	for (int count = 0; count < numgrades; count++)
		sum = sum + array[count];

	return (sum / numgrades);
}

//***********************************************************************
//                              findLetterGrade
//
//  task:           This function finds the letter grade for the number
//				    passed to it by the calling function
//            
//  data in:        a floating point number
//  data returned:  the grade (based on a 10 point spread) of the number 
//                  passed to the function
//
//***********************************************************************

char  findLetterGrade(float numgrade)

{
	char letter;
	if (numgrade >= 90 && numgrade <= 100)
		letter = 'A';
	else if (numgrade >= 80)
		letter = 'B';
	else if (numgrade >= 70)
		letter = 'C';
	else if (numgrade >= 60)
		letter = 'D';
	else
		letter = 'E';
	return letter;
}