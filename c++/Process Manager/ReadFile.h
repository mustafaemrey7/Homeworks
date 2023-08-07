#pragma once
#include<iostream>
#include<string>
#include<fstream>
#include <sstream>
#include <vector>
using namespace std;
class ReadFile
{
public:
	
	static void reader(string,int**);
	static void writer(int, int, int**);
	vector<int> splitter(string);
};

