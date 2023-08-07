#pragma once
#include <string>
#include <sstream>
#include<iostream>
#include "Order.h"

using namespace std;

class Event
{

public:
	string type="";
	double time=0;
	int workerId=0;
	Order order;
	Event(string,double,int,Order);
	Event();
};

