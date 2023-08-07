#pragma once
#include "Event.h"
struct Node
{
	Event event;
	Node* next{};

	Node(Event e) {
		event = e;
		next;
	}

};

