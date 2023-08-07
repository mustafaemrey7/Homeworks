findtreasure: main.o ReadFile.o ProcessManager.o
	g++ main.o ReadFile.o ProcessManager.o -o findtreasure 

main.o: main.cpp
	g++ -std=c++11 -c main.cpp

ReadFile.o: ReadFile.cpp ReadFile.h  
	g++ -std=c++11 -c ReadFile.cpp

ProcessManager.o: ProcessManager.cpp ProcessManager.h  
	g++ -std=c++11 -c ProcessManager.cpp

clean:
	rm*.o findtreasure