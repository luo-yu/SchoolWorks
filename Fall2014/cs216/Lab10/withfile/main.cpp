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
    if(inputFile.is_open()){
	string line;
        
	string shapeName;
	double radius, side, width, length;
        
	//As long as there is still lines in the file
	//keep reading the file
	while(getline(inputFile, line)){
                //extract the shape name out of a line
		shapeName = line.substr(0, line.find(" "));

		string a;
		//if the shapeName is a circle
		//extract the shapeName, create the shape
                //and call display function
		if(shapeName =="circle"){
			a =line.substr(line.find(" ")+1);
			istringstream iss(a);
			iss>>radius;
			Circle c(radius);
			c.display();
		        
			//Push the Circle pointer
			//to the vector
			v.push_back(&c);
		}

		else if(shapeName=="square"){
		       a= line.substr(line.find(" ")+1);
		       istringstream iss(a);
		       iss>>side;
		       Square s(side);
		       s.display();

			//Push the Square pointer
			//to the vector
			v.push_back(&s);
 		}
		else{
		      a = line.substr(line.find(" ")+1);
		      istringstream iss(a);
		      iss>>width>>length;
		      Rectangle rect(width, length);
		      rect.display();
		     //Push the Rectangle pointer
		     // to the vector
		     v.push_back(&rect);
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
    vector<Shape*>::iterator it;
    for(it =v.begin(); it!=v.end(); it++){
       (*it)->expand(expandFactor);
       (*it)->display();
    }
	    
    return 0;
}
