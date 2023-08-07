#pragma once
#include "Node.h"
#include "Event.h"
#include <iostream>
class CashierQueue
{



public:
	int maxQueue = 0;
	int size = 0;
	struct Node* front = NULL;
	struct Node* rear = NULL;
	void enQueue(Event);
	void deQueue();
	bool isEmpty();
	Node* getTop();
	void writerQueue();
};

