#include <iostream>
#include <cmath>
#include <iomanip>

using namespace std;
void WinLoss(float& , int&);

void percentage(float, int, float&);

int main()
{
	int total;
	float Wins;
	int Loss;
	float Percent;
	cout << "This program calculates the total percentage of Wins a Baseball team has." << endl;
	cout << "How many games did you win?" << endl;
	WinLoss(Wins, Loss);
	percentage(Wins, Loss, Percent);
	cout << showpoint << fixed << setprecision(2) << "The percentage of wins is " << Percent << "%" << endl;

	return 0;
}

void  WinLoss(float& W , int& L)
{
	cin >> W;
	cout << "How many games did you lose?" << endl;
	cin >> L;
}

void percentage(float W, int L, float& P)
{
	int sum;
	sum = W + L;
	P = (W / sum) * 100;



}