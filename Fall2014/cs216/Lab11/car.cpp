#include "car.h"	
#include "product.h"


void Car::print(){

    cout<<"Car Make and Model: "<<Product::getName()<<endl;
	cout<<"Year Made: "<<Product::getYear()<<endl;
	cout<<"Price: "<<Product::getPrice()<<endl;
	cout<<"Number of doors: "<<doors<<endl;
	cout<<"Horse Power: "<<horsePower<<endl;
}
int Car::getHoursePower(){
	return horsePower;

}
int Car::getDoors(){
	return doors;

}