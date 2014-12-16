//course: cs216-002
//Project: lab10
//Date: 11/12/2014
//Purpose: This progrom implements a Square
//Author: Yu Luo


#include "Square.h"

#include <iostream>
using namespace std;

//Create a square with side s
Square::Square(double s) : Rectangle(s, s){
    side = s;
}


//Print the square side and Square area
void Square::display(){
    cout<<"Square: (side = "<<side<<")"<<endl;
    cout<<"The area is: "<<computeArea()<<endl;
    
}
