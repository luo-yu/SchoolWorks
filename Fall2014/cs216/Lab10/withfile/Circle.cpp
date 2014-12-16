//course: cs216-002
//Project: lab10
//Date: 11/12/2014
//Purpose: This progrom implements a Circle
//Author: Yu Luo



#include "Circle.h"
#include <iostream>
using namespace std;

//Circle constructor with one parameter
Circle::Circle(double r){
    radius = r;
}

//Return the area of a circle
double Circle::computeArea(){

    return PI * (radius * radius);
}

//Expand radius by factor
void Circle::expand(int factor){
    radius = radius*factor;
}

//Print out circle radius and circle area
void Circle::display(){
    cout<<"Circle: (radius = "<<radius<<")"<<endl;
    cout<<"The area is : "<<computeArea()<<endl;
}

