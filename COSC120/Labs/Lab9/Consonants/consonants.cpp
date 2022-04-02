#include <iostream>
#include <string>

using namespace std;
int consonantFind(char[]);
const int MAXCHAR = 50;
int main()
{
	
	char str[MAXCHAR];
	cout << "Enter a string with less than 50 lowercase letters:" << endl;
	cin >> str;
	
	consonantFind(str);
	

	








	return 0;
}



int consonantFind(char str[])

{
	int i=0;
	int length = strlen(str);
	for (int j = 0; j < length; j++)
{
	
	{
		if (str[j] != 'a' && str[j] != 'e' && str[j] != 'i' && str[j] != 'o' && str [j] != 'u')
		{
			i++;
		}
	}
}
	cout << "The amount of consonants in this string are: " << i << endl;

	return i;
}