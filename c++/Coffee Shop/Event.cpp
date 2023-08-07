#include "Event.h"

Event::Event(string n, double t, int id,Order o)
{
	this->order = o;
	this->type = n;
	this->workerId = id;
	this->time = t;
}
Event::Event()
{}
