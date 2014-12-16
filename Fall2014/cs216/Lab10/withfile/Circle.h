//course: cs216-002
//Project: lab10
//Date: 11/12/2014
//Purpose: This progrom defines a Circle
//Author: Yu Luo

#ifndef CIRCLE_H_
#define CIRCLE_H_

#include "Shape.h"

//The Circle is derived from Shape class. It
//Provide a method to compute area of a Circle
//and to expand the dimension with
//given radius
class Circle : public Shape{

	public: 
		Circle(double r);
        double computeArea();
       
    
        void expand(int factor);
        void display();
    
	private:
		double radius;
    
        //Used to calculate the area of a circle
               static double const PI = 3.1416;

};
#endif/*CIRCLE_H_*/
