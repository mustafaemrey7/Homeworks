#pragma once
#include <string>
#include <sstream>
#include <iostream>
#include "Event.h"
#include "Node.h"
using namespace std;
class EventQueue
{
	


public:
	double totalTime = 0;
	struct Node* rear = NULL;
	struct Node* front = NULL;
	void enQueue(Event);
	void deQueue();
	bool isEmpty();
	void getTop();
	void writerQueue();
};

