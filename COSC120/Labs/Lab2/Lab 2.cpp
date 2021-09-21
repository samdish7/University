#include <iostream>;
#include <fstream>;

using namespace std;

int main()

{
	ofstream outFile;
	ifstream inFile;
	outFile.open("student.dat");

		char name[20];
		char address[90];
		char ID[10];

		cout << "Writing your name, address, and student id number..." << endl;
		

		outFile << "Samuel Disharoon" << endl;
		outFile << "4548 Kristin Lane Salisbury MD, 21801" << endl;
		outFile << "3048433" << endl;
		outFile.close();

		inFile.open("student.dat");
			cout << "Reading the data from the file..." << endl;
		inFile >> name;
		cout << name << endl;
		inFile >> address;
		cout << address << endl;
		inFile >> ID;
		cout << ID << endl;

		inFile.close();


		cout << "Done" << endl;;
	return 0;
}
