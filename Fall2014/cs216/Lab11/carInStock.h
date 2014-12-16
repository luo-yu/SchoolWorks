#ifndef CARINSTOCK_H
#define CARINSTOCK_H
#include "car.h"
#include <string>
#include <iostream>
using namespace std;

class CarInStock {
private:
 string make;

    const int MaxCars = 50;
    typedef Car CarArrayType[MaxCars];
    

public:
    
    void displayAll();
    
    void search(CarArrayType carArray, int NumCars);
    
    void searchByYearRange(int year1, int year2);

    void searchByPriceRange(double price1, double price2);
    
    void insertCar(Car c);
    
    void deleteCar(Car c);


}; 

#endif
