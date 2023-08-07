#include "ProcessManager.h"
#include "Worker.h"
#include <iostream>
#include <string>
#include "EventQueue.h"
#include "Event.h"
using namespace std;

int main(int argc, char* argv[]) {
	ProcessManager reader;
	reader.readFile(argv[1],argv[2]);

	

	
}
