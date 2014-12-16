//course: cs216-002
//Project: lab10
//Date: 11/12/2014
//Purpose: This progrom dedines an abstract Shape class
//Author: Yu Luo

#ifndef SHAPE_H_
#define SHAPE_H_

//This is the abstract base class. 
class Shape{

	public:
		Shape(){};
                virtual double computeArea()=0;
                virtual void expand(int factor)=0;
                virtual void display()=0;
                virtual ~Shape() {};
};

#endif /* SHAPE_H_*/
