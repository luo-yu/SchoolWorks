OBJECTS = main.o LinkedList.o

lab7: $(OBJECTS)
	g++ $^ -o lab7

%.o: %.cpp
	g++ -c $< -o $@

HEADERS = LinkedList.h
%.o: %.cpp $(HEADERS)
	g++ -c $< -o $@

clean: 
	rm -f *.o lab7
