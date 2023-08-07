#pragma once
#include <fstream>
#include <string>
#include <sstream>
#include <algorithm>
#include <fstream>
#include <iostream>
#include <vector>
#include "Order.h"
using namespace std;
class ProcessManager
{
	
public:
	ofstream outputFile;
	vector<Order> orderList;
	int cashierNumber=0;
	int baristaNumber = 0;
	int orderNumber;
	int TOTAL_RUNNING_TIME;
	void readFile(string txtName,string outTxt);
	vector<string> splitter(string,char);
	void model1(int);
	void model2(const int);
	void writerOutput(string);
private:
	
};

