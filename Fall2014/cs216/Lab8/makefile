OBJECTS = main.o readData.o secondGreatest.o


lab8: $(OBJECTS)
	g++ $^ -o lab8

%.o: %.cpp
	g++ -c $< -o $@


HEADERS = readData.h secondGreatest.h

%.o: %.cpp $(HEADERS)
	g++ -c $< -o $@

clean: 
	rm -f *.o lab8
