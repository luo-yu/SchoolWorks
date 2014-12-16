#include "product.h"


Product::Product(string iName, int iYear, double iPrice){
	Year = iYear;
	Name=iName;
	Price = iPrice;
}
	 
void Product::print() const{

	cout<<"Product Name: "<<Name<<endl;
	cout<<"Year Made: "<<Year<<endl;
	cout<<"Price: "<<Price<<endl;
}
string Product::getName() const{
	return Name;

} 

int Product::getYear() const{
	return Year;
}
double Product::getPrice() const{
	return Price;

}
void Product::changePrice(double NewPrice){

	Price=NewPrice;
}