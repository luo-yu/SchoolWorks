//course: cs216-002
//Project: lab10
//Date: 11/12/2014
//Purpose: This progrom defines a Rectangle
//Author: Yu Luo

#ifndef RECTANGLE_H_
#define RECTANGLE_H_

#include "Shape.h"

//The Rectangle is derived from Shape class. It
//Provide a method to compute area of a Rectangle
//and to expand dimensions with
//given width and length;
class Rectangle : public Shape{

	public: 
		Rectangle(double w, double l);
        double computeArea();
        void expand(int factor);
        void display();
    
    
	private:
		double width;
		double length;

};
#endif /*RECTANGLE_H_*/