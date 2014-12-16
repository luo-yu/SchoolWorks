#ifndef CAR_H
#define CAR_H
#include "product.h"
#include <string>
#include <iostream>
using namespace std;

class Car : public Product {
private:
 
 int horsePower=0;
 int doors=0;

public: 
	
    Car(string InitialName ="", int InitialYear =0, double InitialPrice =0.0, int InitialDoors=0, int InitialHP=0):Product(InitialName , InitialYear, InitialPrice),doors(InitialDoors), horsePower(InitialHP){}
    /*
     
     Given: Nothing
     Task: To print the data contained in this object
     Return: Nothing
     */
	void print();
	int getHoursePower();
	int getDoors();
}; 
#endif