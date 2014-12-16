
//course: cs216-002
//Project: lab10
//Date: 11/12/2014
//Purpose: This main program read data from file and 
//         create appropriate shapes according to
//         the file input
//Author: Yu Luo

#include <vector>
#include <iostream>
#include "Circle.h"
#include "Rectangle.h"
#include "Square.h"
#include "Shape.h"
#include <fstream>
#include <sstream>
#include <string>

using namespace std;

int main(int argc, const char * argv[]) {
    ifstream inputFile;
    
    inputFile.open(argv[1], ios::in);
    
    //Create a vector of shape pointer
	vector<Shape*> v;
    //if the file is open
    if(inputFile.is_open()){
	string line;
        
	//Use to extract shape names
	string shapeName;
	double radius, side, width, length;
        
	//As long as there is still lines in the file
	//keep reading the file
	while(getline(inputFile, line)){
                //extract the shape name out of a line
		shapeName = line.substr(0, line.find(" "));

		string a;
		//In each of the foolowing condition block, 
		//read the line and extract the shape. 
		//After extracgint, create the shape
                //and call display function
		//Also, use a pointer point to the newly created
		//shape, and push to a vector of Shape pointer
		if(shapeName =="circle"){
			a =line.substr(line.find(" ")+1);
			istringstream iss(a);
			iss>>radius;
			
			
			Circle* temp = new Circle(radius);
			temp->display();
		       	cout<<"The area is: "<<temp->computeArea()<<endl;
 
			//Push the Circle pointer
			//to the vector
			v.push_back(temp);
		}

		else if(shapeName=="square"){
		       a= line.substr(line.find(" ")+1);
		       istringstream iss(a);
		       iss>>side;
		       Square* s = new Square(side);
		       s->display();
                        cout<<"The area is: "<<s->computeArea()<<endl;
			//Push the Square pointer
			//to the vector
			v.push_back(s);
 		}
		else{
		      a = line.substr(line.find(" ")+1);
		      istringstream iss(a);
		      iss>>width>>length;
		      Rectangle* rect = new Rectangle(width, length);
		      rect->display();
		      cout<<"The area is: "<<rect->computeArea()<<endl;

		     //Push the Rectangle pointer
		     // to the vector
		     v.push_back(rect);
		}
        
        
	}
    }else{
	cout <<"Unable to open file"<<endl;
    }
    
    
    
    //Ask user for expand factor
    cout<<"Please input a positive integer as the factor to expand"<<endl;
    int expandFactor=0;
    cin>>expandFactor;
    cout <<"ExpandFactor = "<<expandFactor<<endl;
    
    //Expand each shape object with a factor which
   // user inputs from the keybaord
	for(int i =0; i<v.size(); i++){
		v[i]->expand(expandFactor);
		v[i]->display();
		cout<<"The area is : "<<v[i]->computeArea()<<endl;
	}
   //Deallocate heap
        for(int i =0; i<v.size(); i++){
		delete v[i];
        }   
    return 0;
}
