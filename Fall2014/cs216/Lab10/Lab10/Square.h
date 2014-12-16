//course: cs216-002
//Project: lab10
//Date: 11/12/2014
//Purpose: This progrom defines a Square
//Author: Yu Luo

#ifndef SQUARE_H_
#define SQUARE_H_



#include "Rectangle.h"


//The Square is derived from Rectangle class. It
//Provide a method to compute area of a Square
//and to expand dimensions with
//given side.
class Square : public Rectangle{

	public: 
		Square(double s);
		double computeArea();        
		void expand(int);    
	        void display();
	
	private: 
		double side;

};

#endif
