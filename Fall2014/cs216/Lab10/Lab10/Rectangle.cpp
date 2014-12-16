//course: cs216-002
//Project: lab10
//Date: 11/12/2014
//Purpose: This progrom implements a Rectangle
//Author: Yu Luo


#include "Rectangle.h"
#include <iostream>
using namespace std;

//Create a rectangle with width w and length l
Rectangle::Rectangle(double l, double w){
    
    width = w;
    length = l;
}

//Return the area of a Rectangle
double Rectangle::computeArea(){
    return width * length;
}

//Expand the width and length of a
//rectangle by factor
void Rectangle::expand(int factor){
    width = width * factor;
    length = length * factor;

}

//print out rectangle length and width
//print out the area of a rectangle;
void Rectangle::display(){
    cout<<"Rectangle: (length = "<<length<<", width = "<<width<<")"<<endl;
}
