//course: CS216-00x
//Project: Lab Assignment 9
//Date: 11/05/2014
//File: Matrix.cpp
//Purpose: to define the class Matrix
//Author: Yu Luo

#include <iostream>
#include <cassert>
#include "Matrix.h"

using namespace std;

//Constructor with two int parameters. 
Matrix::Matrix(int sizeX, int sizeY) : dx(sizeX), dy(sizeY)
{
	assert(sizeX > 0 && sizeY > 0);
	p = new long*[dx];		
				// create array of pointers to long integers
	assert(p != 0);
	for (int i = 0; i < dx; i++)
	{	  // for each pointer, create array of long integers
		p[i] = new long[dy];  
		assert(p[i] != 0);
		for (int j = 0; j < dy; j++)
			p[i][j] = 0;
	}
}

Matrix::~Matrix()
{
	for (int i = 0; i < dx; i++)
		delete [] p[i];	// delete arrays of long integers
	delete [] p;	// delete array of pointers to long
}

long &Matrix::Element(int x, int y)
{
	assert(x >= 0 && x < dx && y >= 0 && y < dy);
	return p[x][y];
}

void Matrix::Print() const
{
	cout << endl;
	for (int x = 0; x < dx; x++)
	{
		for (int y = 0; y < dy; y++)
			cout << p[x][y] << "\t";
		cout << endl;
	}
}

//Implement overloading for ()
long& Matrix::operator() (int x, int y){
	assert(x>=0 && x<dx && y>=0 && y<dy);
	return p[x][y];
}




//Implements copy Constructor
Matrix::Matrix(const Matrix &m) : dx(m.dx), dy(m.dy)
{
	p = new long*[dx];            // create array of pointers to long integers
	assert(p != 0);
	for (int i = 0; i < dx; i++)
	{
		p[i] = new long[dy];  // for each pointer, create array of l.i.s
		assert(p[i] != 0);
		for (int j = 0; j < dy; j++)
			p[i][j] = m.p[i][j];
	}
}


//Implement overloading assignment operator. 
//This will make sure that = will copy the 
//actual elements of a matrix and let the pointer
//point to the copied data
Matrix &Matrix::operator=(const Matrix &m)
{
	if (this != &m)
	{
		for (int i = 0; i < dx; i++)
			for (int j = 0; j < dy; j++)
				p[i][j] = m.p[i][j];
	}
	return *this;
}


//Overloading << operator
ostream &operator<<(ostream &out, const Matrix &m)
{
	out << endl;
	for (int x = 0; x < m.dx; x++)
	{
		for (int y = 0; y < m.dy; y++)
			out << m.p[x][y] << "\t";
		out << endl;
	}
	return out;
}

//Overload * operator. int * Matrix form
Matrix operator*(int k, Matrix m1){
	Matrix mul(m1.dx, m1.dy);

	for( int i = 0; i < m1.dx; i ++ )
		for( int j = 0; j < m1.dy; j ++ )
			mul.p[i][j] = k* m1.p[i][j];

	return mul;
}

//Overload  * operator, Matrix* int form
Matrix operator*(Matrix m1, int k){
	Matrix mul(m1.dx, m1.dy);

	for( int i = 0; i < m1.dx; i ++ )
		for( int j = 0; j < m1.dy; j ++ )
			mul.p[i][j] = m1.p[i][j]*k;

	return mul;
	
}

//Overloading + operator, Matrix + Matrix form
Matrix operator+(const Matrix m1, const Matrix m2){

	Matrix sum(m1.dx, m1.dy);

	//If two matrix does not have same dimension
	if( m1.dx != m2.dx || m1.dy != m2.dy)	return sum;

	for( int i = 0; i < m1.dx; i ++ )
		for( int j = 0; j < m1.dy; j ++ )
			sum.p[i][j] = m1.p[i][j] + m2.p[i][j];

	return sum;
}


//Overloading * operator for multiplying two matrix
Matrix operator*(const Matrix m1, const Matrix m2){
	Matrix mul(m1.dx, m1.dy);


	for( int i = 0; i < m1.dx; i ++ )
		for( int j = 0; j < m1.dy; j ++ )
			mul.p[i][j] = m1.p[i][j]* m2.p[i][j];

	return mul;
}

