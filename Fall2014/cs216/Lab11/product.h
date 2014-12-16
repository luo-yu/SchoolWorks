#ifndef PRODUCT_H
#define PRODUCT_H

#include <string>
#include <iostream>
using namespace std;

class Product {
private:
	 string Name; 
	 int Year; 
	 double Price;
public:
	 Product(string iName, int iYear, double iPrice);
	 void print() const;
	 string getName() const; int getYear() const;
	 double getPrice() const;
	 void changePrice(double NewPrice);
}; 

#endif