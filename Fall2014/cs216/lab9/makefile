#course: cs216-002
#Project: Lab9
#Date: 11/01/2014
#Purpose: This program auto compiles the c++ files
#Author: Yu Luo

#main.o has a dependency
OBJECTS = main.o Matrix.o 

#executable file lab9 
lab9: $(OBJECTS)
	g++ $^ -o lab9

%.o: %.cpp
	g++ -c $< -o $@


HEADERS = Matrix.h

%.o: %.cpp $(HEADERS)
	g++ -c $< -o $@

clean: 
	rm -f *.o lab9

