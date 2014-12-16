#course: cs216-002
#Project: lab 10
#Date: 11/14/2014
#Purpose: This program auto compiles the c++ files
#Author: Yu Luo

#main.o has a dependency
OBJECTS = main.o Square.o Rectangle.o Circle.o 

#executable file lab10 
lab10: $(OBJECTS)
	g++ $^ -o lab10

%.o: %.cpp
	g++ -c $< -o $@


HEADERS = Square.h Rectangle.h Circle.h Shape.h

%.o: %.cpp $(HEADERS)
	g++ -c $< -o $@

clean: 
	rm -f *.o lab10
