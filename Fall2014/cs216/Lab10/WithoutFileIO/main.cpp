

#include <iostream>
#include "Circle.h"
#include "Rectangle.h"
#include "Square.h"
using namespace std;

int main(int argc, const char * argv[]) {
    
    Circle c1(2.45);
    c1.display();
    
    Square s1(50);
    s1.display();
    
    Rectangle rect(17.53, 6.8);
    rect.display();
    
    Circle c2(9.8);
    c2.display();
    
    Rectangle rect1(88.2, 53);
    rect1.display();
    
    Circle c3(50);
    c3.display();
    
    cout<<"Please input a positive integer as the factor to expand"<<endl;
    int expandFactor=0;
    cin>>expandFactor;
    cout <<"ExpandFactor = "<<expandFactor<<endl;
    
    
    c1.expand(expandFactor);
    c1.display();
    
    s1.expand(expandFactor);
    s1.display();
    
    rect.expand(expandFactor);
    rect.display();
    
    c2.expand(expandFactor);
    c2.display();
    
    rect1.expand(expandFactor);
    rect1.display();
    
    c3.expand(expandFactor);
    c3.display();
    
    return 0;
}
