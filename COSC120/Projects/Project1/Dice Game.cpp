// This program simulates a Casino Dice Game
#include <iostream>
#include <iomanip>
#include <string>
#include <cstdlib>
#include <fstream>
#include <ctime>
#include <unistd.h>
using namespace std;

int main()
{
	const int min = 1;
	const int max = 6;
	int Roll;
	int round=0;
	int target;

	string name;
	ofstream outputFile;
	outputFile.open("game.dat");
	cout << "Hello! Welcome to the Casino Dice Game!  May I please have your name?" << endl;
	cin >> name;
	cout << name << "! That is a wonderful name indeed! Hello " << name << "! And thank you for playing!\n";
	cout << "The rules of the game are quite simple:\n";
	cout << "On the first roll, if you roll a 2,3, or 12, the house wins.\nIf you roll a 7 or an 11, You win!\n"
		"If niether house nor you win on the first roll, the number that you must roll to win becomes the value of the first roll\n"
		"You continue rolling until you roll the target number or a 7, if it is a 7, the house wins." << endl;
		usleep(5000000);
		
			int Die1;
			int Die2;
			unsigned seed = time(0);
			srand(seed);
			Die1 = (rand() % (max - min + 1)) + min;
			Die2 = (rand() % (max - min + 1)) + min;
			target = Die1 + Die2;
			Roll = Die1 + Die2;
			round++;
			cout << name << " rolled a " << Die1 << " & a " << Die2 << " for a total of " << Roll << endl;
			if (Roll == 2 || Roll == 3 || Roll == 12)	
				{
				cout << "The house wins in " << round << " round!!!" << endl;
				outputFile << Die1;
				outputFile << Die2;
				outputFile << Roll;
				}
			else if (Roll == 7 || Roll == 11)
				{
				cout << name << " wins in " << round << " round!!!" << endl;
				outputFile << Die1;
				outputFile << Die2;
				outputFile << Roll;
				}
			else
			{
				Roll = 0;
				while (Roll != target && Roll != 7)
				{
					cout << "We must roll again!" << endl;
					usleep(2000000);

					unsigned seed = time(0);
					srand(seed);
					Die1 = (rand() % (max - min + 1)) + min;
					Die2 = (rand() % (max - min + 1)) + min;
					Roll = Die1 + Die2;
					round++;
					cout << name << " rolled a " << Die1 << " & a " << Die2 << endl;

					outputFile << "Next die roll:" << endl;
					outputFile << "First Die: " << Die1 << endl;
					outputFile << "Seconde Die: " << Die2 << endl;
					outputFile << "Total Roll" << Roll << endl;
					
				
			}
				if (Roll == target)
				{
					cout << name << "Wins!!!   It took "<< round << " rounds to win!" << endl;
					cout << "Thanks for playing!" << endl;

					outputFile << "Final results:" << endl;
					outputFile << "Winner: " << name;
					outputFile << "Number of rolls:" << round;
				}
				else 
				{
					cout << "The house wins!!!  It took "<< round << " rounds to win!" << endl;
					outputFile << "Final results:" << endl;
					outputFile << "Winner: House" << endl;
					outputFile << "Number of rolls: " << round;
				}

				}
			 
		
		

	outputFile.close();
	return 0;
}
