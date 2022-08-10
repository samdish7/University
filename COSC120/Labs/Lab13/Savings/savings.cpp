// This program introduces the use of static member variables.
// Samuel Disharoon

#include <iostream>
using namespace std;

class SavingsAcct
{
private:
	int acctNum;                        // "regular" member variables
	double balance;
	static int nextAcctNumber;      
	static double totalDeposits;
	void newAcctInfo();
public:
	SavingsAcct();                       // default constructor
	SavingsAcct(double);                 // constructor
	static void firstAcctNumber();
	double getTotalDeposits();
	void displayAcctInfo();
	void deposit(double);
	void withdraw(double);
};

double SavingsAcct::totalDeposits = 0;

// Member Function Implementation Section

SavingsAcct::SavingsAcct()                 // default constructor
{
	acctNum = 0;
	nextAcctNumber = 0;

	balance = 0;
	newAcctInfo();
}

SavingsAcct::SavingsAcct(double startBal)  // constructor
{
	acctNum = nextAcctNumber;
	
	nextAcctNumber++;

	balance = startBal;

	totalDeposits += balance;

	newAcctInfo();
}
void SavingsAcct::firstAcctNumber()
{
	cout << "What is the starting account number?" << endl;
	cin >> nextAcctNumber;
}
void SavingsAcct::newAcctInfo()
{
	cout << "New account number: " << acctNum << "   Initial Balance: $"
		<< balance << endl;
}

double SavingsAcct::getTotalDeposits()
{
	return totalDeposits;
}

void SavingsAcct::displayAcctInfo()
{
	cout << "Account number " << acctNum << " has a balance of: $" << balance << endl;
}

void SavingsAcct::deposit(double amt)
{
	balance += amt;
	totalDeposits += amt;
}

void SavingsAcct::withdraw(double amt)
{   
}

// Client code

int main()
{
	SavingsAcct acct1(100);
	SavingsAcct acct2(250);

	acct1.deposit(50);
	acct2.deposit(50);
	acct1.displayAcctInfo();
	acct2.displayAcctInfo();

	cout <<"Total deposits for account 1: $"<< acct1.getTotalDeposits()<<endl;
	cout <<"Total deposits for account 2: $"<< acct2.getTotalDeposits()<<endl;

	return 0;
}
