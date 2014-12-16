

#include <iostream>
#include "secondGreatest.h"
using namespace std;

/*

The function returns the second greatest value that is
found among the elements of an integer array pointed 
by the pointer parameter data.
If there is no second largest elemnet, the function
will return the largest element
*/
int secondGreatest(int* data, int size){

	int highest = data[0];
	int sec_highest = data[0];

	
	for(int i =0; i<size; i++){
		if(data[i]>highest){
			sec_highest = highest;
			highest = data[i];
		}
		else if(data[i]>sec_highest && data[i] != highest){
			sec_highest = data[i];
		}
		
	}

	return sec_highest;
}
