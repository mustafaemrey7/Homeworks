#pragma once

#include <exception>
#include <iostream>
#include <string>
#include <fstream>
using namespace std;
class ProcessManager
{

public:
	ofstream outputFile;
	ProcessManager(string);
	void findTreasure(int**, int**, int, int, int);
	void writerOutput(int,int,int);
	void deleteMatrix(int**,int);
	~ProcessManager();
}
;

