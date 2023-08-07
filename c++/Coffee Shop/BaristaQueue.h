#pragma once
#include<string>
#include"Event.h"
using namespace std;


class BaristaQueue
{
public:
	int maxQueue=0;
	int size;
	struct Node* rear = NULL;
	struct Node* front = NULL;
	void enQueue(Event);
	void deQueue();
	bool isEmpty();
	Node* getTop();
	void writerQueue();
};


