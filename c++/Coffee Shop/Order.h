#pragma once
class Order
{
public:
	int id{};
	float price{};
	float orderTime{};
	float makeTime{};
	float arrivalTime{};
	float TurnaroundTime{};
	Order(int id, float price, float orderTime, float makeTime, float arrivalTime, float TurnaroundTime) ;
	Order();
};

