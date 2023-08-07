#include "ProcessManager.h"
#include "Order.h"
#include "Worker.h"
#include "EventQueue.h"
#include "Event.h"
#include "CashierQueue.h"
#include "BaristaQueue.h"
#include <iostream>
using namespace std;
void ProcessManager::readFile(string txt,string outTxt) {
	ifstream inputFile;
	int i = 0;
	inputFile.open(txt);
	inputFile >> cashierNumber;
	baristaNumber = cashierNumber / 3;
	inputFile >> orderNumber;
	outputFile.open(outTxt);
	for (int k =0;k<orderNumber;k++)
	{
			float arrivalTime;
			float orderTime;
			float brewTime;
			float priceOfOrder;
			inputFile>>arrivalTime >>orderTime >>brewTime >>priceOfOrder;	
			Order temp = Order(k+1,priceOfOrder,orderTime,brewTime,arrivalTime,0);
			orderList.push_back(temp);
	}
	model1(cashierNumber);
	model2(cashierNumber);
}
vector<std::string> ProcessManager::splitter(std::string argv, char c) {
	stringstream commands(argv);
	std::string element;
	vector<std::string> argvList;
	while (getline(commands, element, c))
	{
		argvList.push_back(element);
	}
	return argvList;
}

void ProcessManager::model1(int n)
{
	vector<Worker> cashiers;
	vector<Worker> baristas;
	cashierNumber = n;
	baristaNumber = n / 3;
	for (size_t i = 0; i < cashierNumber; i++)
	{
		Worker temp;
		temp.id = i + 1;
		temp.isIdle = true;
		temp.workerType = "c";
		temp.workTime = 0;
		temp.totalBusyTime = 0;
		cashiers.push_back(temp);
	}
	for (size_t i = 0; i < baristaNumber; i++)
	{
		Worker temp;
		temp.id = i + 1;
		temp.isIdle = true;
		temp.workerType = "b";
		temp.workTime = 0;
		temp.totalBusyTime = 0;
		baristas.push_back(temp);
	}
	EventQueue time;
	CashierQueue cashierQ;
	BaristaQueue baristaQ;
	for (size_t i = 0; i < orderNumber; i++)
	{
		Event e = Event("come", orderList[i].arrivalTime, 0, orderList[i]);
		time.enQueue(e);
	}
	
	Node* iter = time.front;
	while (iter!= NULL)
	{
		
		if (iter->event.type=="come") {
		
			bool isAnyIdle = false;
			int idleId = 0;
			for (size_t i = 0; i < cashierNumber; i++)
			{
				if (cashiers[i].isIdle == true) {
					idleId = cashiers[i].id;
					isAnyIdle = true;
					cashiers[i].isIdle = false;
					cashiers[i].totalBusyTime = cashiers[i].totalBusyTime + iter->event.order.orderTime;
					Event e2 = Event("free", iter->event.time+iter->event.order.orderTime, idleId, iter->event.order);
					
					time.enQueue(e2);
					break;
				}
			}
			if (isAnyIdle == false) {
				
				Event e = Event("orderQueue",iter->event.time, 0, iter->event.order);
				cashierQ.enQueue(e);

			}
		
		}
		else if (iter->event.type == "free") {
			for (size_t i = 0; i < cashierNumber; i++)
			{
				if (cashiers[i].id == iter->event.workerId) {
					cashiers[i].isIdle = true;
					if (!cashierQ.isEmpty()) {
						Event e = Event("come", iter->event.time, 0, cashierQ.getTop()->event.order);
						time.enQueue(e);
						cashierQ.deQueue();
					}
					Event e1 = Event("goBarista", iter->event.time , cashiers[i].id, iter->event.order);
					
					time.enQueue(e1);
				
					break;
				}
			}
		}
		else if (iter->event.type == "goBarista") {
			
			bool isAnyIdle = false;
			int idleId = 0;
			for (size_t i = 0; i < baristaNumber; i++)
			{
				if (baristas[i].isIdle == true) {
					idleId = baristas[i].id;
					isAnyIdle = true;
					baristas[i].isIdle = false;
					baristas[i].totalBusyTime = baristas[i].totalBusyTime + iter->event.order.makeTime;
					Event e2 = Event("freeBarista", iter->event.time + iter->event.order.makeTime, idleId, iter->event.order);

					time.enQueue(e2);
					break;
				}
			}
			if (isAnyIdle == false) {
				Event e = Event("baristaQueue", iter->event.time, 0, iter->event.order);
				baristaQ.enQueue(e);
			}	
		}
		else if (iter->event.type == "freeBarista") {
	
			for (size_t i = 0; i < baristaNumber; i++)
			{
				if (baristas[i].id == iter->event.workerId) {
					baristas[i].isIdle = true;
					if (!baristaQ.isEmpty()) {
						Event e = Event("goBarista", iter->event.time, 0, baristaQ.getTop()->event.order);
						time.enQueue(e);
						baristaQ.deQueue();
					}
						Event e1 = Event("finish", iter->event.time, baristas[i].id, iter->event.order);
						 time.totalTime = iter->event.time;
						time.enQueue(e1);
					break;
				}
			}
		}
		else if (iter->event.type == "finish") {
			for (size_t i = 0; i < orderNumber; i++)
				{
				if (orderList[i].id==iter->event.order.id) {
					orderList[i].TurnaroundTime = iter->event.time - orderList[i].arrivalTime;			
				}
			}
		}
		iter = iter->next;	
		time.deQueue();
	}
	outputFile << "OUTPUT FOR MODEL1" << endl;
	outputFile << time.totalTime << endl;
	outputFile << cashierQ.maxQueue<<endl;
	outputFile << baristaQ.maxQueue<<endl;
	for (size_t i = 0; i < cashierNumber; i++){
		double result =roundf ((cashiers[i].totalBusyTime / time.totalTime)*100)/100 ;
		outputFile << (result) << endl;
	}
	for (size_t i = 0; i < baristaNumber; i++) {
		double result = roundf((baristas[i].totalBusyTime / time.totalTime)*100)/100;
		outputFile << (result) << endl;
	}
	for (size_t i = 0; i < orderNumber; i++){
		outputFile << orderList[i].TurnaroundTime <<endl;
	}
}

void ProcessManager::model2(const int n)
{
	vector<Worker> cashiers;
	vector<Worker> baristas;
	cashierNumber = n;
	baristaNumber = n / 3;
	for (size_t i = 0; i < cashierNumber; i++)
	{
		Worker temp;
		temp.id = i + 1;
		temp.isIdle = true;
		temp.workerType = "c";
		temp.workTime = 0;
		temp.totalBusyTime = 0;
		cashiers.push_back(temp);
	}
	for (size_t i = 0; i < baristaNumber; i++)
	{
		Worker temp;
		temp.id = i + 1;
		temp.isIdle = true;
		temp.workerType = "b";
		temp.workTime = 0;
		temp.totalBusyTime = 0;
		baristas.push_back(temp);
	}
	EventQueue time;
	CashierQueue cashierQ;
	vector<BaristaQueue> baristaQueues;
	for (int i = 0; i < baristaNumber; i++)
	{
		BaristaQueue temp;
		baristaQueues.push_back(temp);
	}
	for (size_t i = 0; i < orderNumber; i++)
	{
		Event e = Event("come", orderList[i].arrivalTime, 0, orderList[i]);
		time.enQueue(e);
	}
	
	Node* iter = time.front;
	while (iter != NULL)
	{
		if (iter->event.type == "come") {
			bool isAnyIdle = false;
			int idleId = 0;
			for (size_t i = 0; i < cashierNumber; i++)
			{
				if (cashiers[i].isIdle == true) {
					idleId = cashiers[i].id;
					isAnyIdle = true;
					cashiers[i].isIdle = false;
					cashiers[i].totalBusyTime = cashiers[i].totalBusyTime + iter->event.order.orderTime;
					Event e2 = Event("free", iter->event.time + iter->event.order.orderTime, idleId, iter->event.order);

					time.enQueue(e2);
					break;
				}
			}
			if (isAnyIdle == false) {
				Event e = Event("orderQueue", iter->event.time, 0, iter->event.order);
				cashierQ.enQueue(e);
			}
		}
		else if (iter->event.type == "free") {
			for (size_t i = 0; i < cashierNumber; i++)
			{
				if (cashiers[i].id == iter->event.workerId) {
					cashiers[i].isIdle = true;
					if (!cashierQ.isEmpty()) {
						Event e = Event("come", iter->event.time, 0, cashierQ.getTop()->event.order);
						time.enQueue(e);
						cashierQ.deQueue();
					}
					Event e1 = Event("goBarista", iter->event.time, cashiers[i].id, iter->event.order);

					time.enQueue(e1);

					break;
				}
			}
		}
		else if (iter->event.type == "goBarista") {
			int e = iter->event.workerId;
			int k = (e-1)/3;

			if (baristas[k].isIdle == true) {

					baristas[k].isIdle = false;
					baristas[k].totalBusyTime = baristas[k].totalBusyTime + iter->event.order.makeTime;
					Event e2 = Event("freeBarista", iter->event.time + iter->event.order.makeTime,iter->event.workerId, iter->event.order);
					time.enQueue(e2);
				}
				else
				{		
					Event e = Event("baristaQueue", iter->event.time, iter->event.workerId, iter->event.order);
					baristaQueues[k].enQueue(e);
				}
		}
		else if (iter->event.type == "freeBarista") {
			int e = iter->event.workerId;
			int k =( e - 1 )/ 3;
			
			
			baristas[k].isIdle = true;	
					if (!baristaQueues[k].isEmpty()) {
						Event e = Event("goBarista", iter->event.time, baristaQueues[k].getTop()->event.workerId, baristaQueues[k].getTop()->event.order);
						time.enQueue(e);	
						baristaQueues[k].deQueue();
					}
					Event e1 = Event("finish", iter->event.time, iter->event.workerId, iter->event.order);
					time.totalTime = iter->event.time;
					time.enQueue(e1);	
		}
		else if (iter->event.type == "finish") {
			for (size_t i = 0; i < orderNumber; i++)
			{
				if (orderList[i].id == iter->event.order.id) {
					orderList[i].TurnaroundTime = iter->event.time - orderList[i].arrivalTime;
				}
			}
		}
		iter = iter->next;
		time.deQueue();
		
	}
	outputFile << "" << endl;
	outputFile << "" << endl;
	outputFile << "OUTPUT FOR MODEL2" << endl;
	outputFile << time.totalTime << endl;
	outputFile << cashierQ.maxQueue << endl;
	for (size_t i = 0; i < baristaQueues.size(); i++)
	{
		outputFile << baristaQueues[i].maxQueue << endl;
	}
	for (size_t i = 0; i < cashierNumber; i++) {
		double result = roundf((cashiers[i].totalBusyTime / time.totalTime)*100.0)/100;
		outputFile << result << endl;
	}
	for (size_t i = 0; i < baristaNumber; i++) {
		double result =roundf ((baristas[i].totalBusyTime / time.totalTime)*100)/100;
		outputFile << (result) << endl;
	}
	for (size_t i = 0; i < orderNumber; i++) {
		outputFile << orderList[i].TurnaroundTime << endl;
	}
}

void ProcessManager::writerOutput(string)
{
}




