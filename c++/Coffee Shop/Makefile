Assignment3: main.o BaristaQueue.o CashierQueue.o Event.o EventQueue.o Node.o Order.o Worker.o ProcessManager.o
	g++ main.o BaristaQueue.o CashierQueue.o Event.o EventQueue.o Node.o Order.o Worker.o ProcessManager.o -o Assignment3 

main.o: main.cpp
	g++ -std=c++11 -c main.cpp

BaristaQueue.o: BaristaQueue.cpp BaristaQueue.h  
	g++ -std=c++11 -c BaristaQueue.cpp

CashierQueue.o: CashierQueue.cpp CashierQueue.h  
	g++ -std=c++11 -c CashierQueue.cpp

Event.o: Event.cpp Event.h  
	g++ -std=c++11 -c Event.cpp

EventQueue.o: EventQueue.cpp EventQueue.h  
	g++ -std=c++11 -c EventQueue.cpp

Node.o: Node.cpp Node.h  
	g++ -std=c++11 -c Node.cpp

Order.o: Order.cpp Order.h  
	g++ -std=c++11 -c Order.cpp

Worker.o: Worker.cpp Worker.h  
	g++ -std=c++11 -c Worker.cpp

ProcessManager.o: ProcessManager.cpp ProcessManager.h  
	g++ -std=c++11 -c ProcessManager.cpp

clean:
	rm*.o Assignment3