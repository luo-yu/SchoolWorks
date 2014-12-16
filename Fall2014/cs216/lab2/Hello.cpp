//course: CS216-002
//Project: Lab Assignment 2
//Date: 09/10/2014
//Purpose: To display a greeting message to the users
//         listed in the file users.txt
//Author: Yu Luo

#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main(){
	
	int numUsers =0;
	string line;
	ifstream myUserFile ("users.txt");

	if (myUserFile.is_open()){

		while (getline (myUserFile, line)){
			//line.find(' ') will find the first white space in 
			//each line, and the line.substr function will get
			//the substr from position 0 to one position before
			// the first white space
			cout << "Hello, " << line.substr(0,line.find(' ')) << "\n";
			numUsers++;
		}

		myUserFile.close();
		cout << "The number of users currently logged on is: " <<numUsers<<"\n";
         }
	else 
		cout << "Unable to open file";
	return 0;
}
