//File: Matrix.h
//Purpose: to declare the class Matrix
//Author: Yu Luo

#ifndef MATRIX_H
#define	MATRIX_H

class Matrix
{
  public:

	  //Overload << operator
	  friend std::ostream& operator<<(std::ostream& os, const Matrix& m);

	  //Overload  * operator, int * Matrix form
	  friend Matrix operator*(int i, Matrix m);

	   //Overload  * operator, Matrix* int form
	  friend Matrix operator*(Matrix m, int i);

	  //Overload * operator, Matrix * Matrix
	  friend Matrix operator*(Matrix m1, Matrix m2);

	  //Overload + operator, Matrix + Matrix
	  friend Matrix operator+ (Matrix m1, Matrix m2);

	  

	//Constructor
	Matrix(int sizeX, int sizeY);

	//copy constructor
	Matrix(const Matrix& m);

	//Overload Assignment operator
	Matrix& operator=(const Matrix& source);




	//Destructor
	~Matrix();


	int GetSizeX() const { return dx; }
	int GetSizeY() const { return dy; }
	long &Element(int x, int y);        // return reference to an element
	void Print() const;

	//Overloading() operator
	long& operator() (int x, int y);

	


  private:
	long **p;       // pointer to a pointer to a long integer
	int dx, dy;
};

#endif	/* MATRIX_H */

