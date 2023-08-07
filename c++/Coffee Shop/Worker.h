#pragma once
#include <string>
using namespace std;
class Worker
{
public:
	int id;
	bool isIdle;
	float workTime;
	float totalBusyTime;
	string workerType;
};

