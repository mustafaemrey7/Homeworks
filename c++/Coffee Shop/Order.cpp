#include "Order.h"

Order::Order(int id,float price,float orderTime,float makeTime,float arrivalTime,float TurnaroundTime) {
	this->id= id;
	this->price=price;
	this->orderTime = orderTime;
	this->makeTime = makeTime;
	this->arrivalTime = arrivalTime;
	this->TurnaroundTime = TurnaroundTime;
}
Order::Order()
{
}
