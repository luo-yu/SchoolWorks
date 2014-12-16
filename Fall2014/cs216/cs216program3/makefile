#course: cs216-002
#Project: Programming assignment3
#Date: 11/01/2014
#Purpose: This program auto compiles the c++ files
#Author: Yu Luo

#main.o has a dependency
OBJECTS = main.o BST.o 

#executable file CS216PA3 
CS216PA3: $(OBJECTS)
	g++ $^ -o CS216PA3

%.o: %.cpp
	g++ -c $< -o $@


HEADERS = sset.h BST.h

%.o: %.cpp $(HEADERS)
	g++ -c $< -o $@

clean: 
	rm -f *.o CS216PA3

