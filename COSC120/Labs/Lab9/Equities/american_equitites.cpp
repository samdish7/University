// This program tests a password for the American Equities
// web page to see if the format is correct

// Samuel Disharoon

#include <iostream>
#include <cctype>
#include <cstring>

using namespace std;

//function prototypes 

bool testPassWord(char[]);
int countLetters(char*);
int countDigits(char*);

int main()
{
	char passWord[20];

	cout << "Enter a password consisting of ONLY "
		<< "lowecase letters" << endl;
	cin.getline(passWord, 20);

	if (testPassWord(passWord))
		cout << "Please wait - your password is being verified" << endl;
	else
	{
		cout << "Invalid password. Please enter a password "
			<< "with only lowercase letters " << endl;
		cout << "For example, rwereruns is valid" << endl;
	}


	// FILL IN THE CODE THAT WILL CALL countLetters and 
	// countDigits and will print to the screen both the number of
	// letters and digits contained in the password.  

	return 0;
}


//**************************************************************
//                       testPassWord
//
// task:			determines if the word contained in the
//				    character array passed to it, contains
//					exactly 5 letters and 3 digits.
// data in:			a word contained in a character array
// data returned:   true if the word contains 5 letters & 3
//					digits, false otherwise
//
//**************************************************************
bool testPassWord(char custPass[])
{
	int numLetters, numDigits, length;

	length = strlen(custPass);
	numLetters = countLetters(custPass);
	
	if (numLetters == length)
	{
		for (int i = 0; i < length; i++)
		{
			if (!islower(custPass[i]))
			{
				return false;
			}
		}

		return true;
	}
	else
		return false;
}


// the next 2 functions are from Sample Program 10.5
//**************************************************************
//                       countLetters
//
// task:			counts the number of letters (both
//                  capital and lower case in the string
// data in:			a string 
// data returned:   the number of letters in the string
//
//**************************************************************
int countLetters(char *strPtr)
{
	int occurs = 0;

	while (*strPtr != '\0')

	{
		if (isalpha(*strPtr) )
			occurs++;
		strPtr++;
	}
	cout << "The amount of letters contained in this password are: " << occurs << endl;
	return occurs;
}

//**************************************************************
//                       countDigits
//
// task:			counts the number of digitts in the string
// data in:			a string 
// data returned:   the number of digits in the string
//
//**************************************************************
