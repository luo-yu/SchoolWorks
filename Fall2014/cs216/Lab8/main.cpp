
#include "secondGreatest.h"
#include "readData.h"
#include <iostream>
using namespace std;

/*
The main function calls read_data to collect user input
and it then calls secondGreatest function. The function
print out the second greatest value aomng the data items
entered by the user
*/
int main(int argc, const char* argv[]){
	

	int size = 1;
	int* readArray = read_data(size);
	
	
	int secHighest = secondGreatest(readArray, size);

	cout<<"\nThe second greatest value is: "<<secHighest<<endl;

	return 0;
}
