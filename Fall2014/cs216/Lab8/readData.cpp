
#include <iostream>
#include<string>
#include<sstream>
#include "readData.h"

using namespace std;


/*
 Reads data from the keyboard util user
 terminates input by entering Q or q.
 The function set the size reference 
 parameter to the number of numeric inputs
 return a pointer to an array on the heap.
*/
int* read_data(int &size){

	const int blockSize = 10;

	string choice = "a";

	cout<<"Please input integers (type Q to quit): "<<endl;
	

	int* inputArray = new int[blockSize];

	//used to track array index
	int i =0;
	
	//reset the size parameter to 0
	size = 0;

	//used to manipulate the arraySize
	int arraySize = 1;
	//initial arraySize is the blockSize, 10
	arraySize = arraySize * blockSize; 

	cin >>choice;

	while(choice !="Q" && choice != "q"){
		
		//if the array size is greater than 
		// the number of elements the user typed in
		//add the user input into the array
		if(size < arraySize && isdigit(choice[0])){
			int item = 0;
			istringstream(choice)>>item;
			inputArray[i]=item;
			i++;
			size++;
		}
		else{
			//double the size of the array
			arraySize = arraySize * 2;
			int* newInputArray = new int[arraySize];
			//copy elements in the old array to the
			//new array
			for(unsigned int j =0; j<size; j++){
				newInputArray[j] = inputArray[j];

			}
			//delete the old array
			delete[] inputArray;
			//point the old array to the new array
			inputArray = newInputArray;
		}


		cin>>choice;
	}


	return inputArray;


}
