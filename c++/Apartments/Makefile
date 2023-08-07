networkmap: main.o FunctionManager.o
	g++ main.o FunctionManager.o -o networkmap

main.o: main.cpp
	g++ -std=c++11 -c main.cpp

FunctionManager.o: FunctionManager.cpp FunctionManager.h
	g++ -std=c++11 -c FunctionManager.cpp

clean:
	rm*.o networkmap