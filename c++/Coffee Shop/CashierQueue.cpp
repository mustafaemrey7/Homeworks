#include "CashierQueue.h"
#include "Event.h"

void CashierQueue::enQueue(Event e)
{
	size = size + 1;
	if (size >= maxQueue)
	{
		maxQueue = size;
	}
	struct Node* temp = new Node(e);
	if (front==NULL&&rear==NULL) {
		front = rear = temp;
		return;
	}
	rear->next = temp;
	rear = temp;
}
void CashierQueue::deQueue()
{
	size = size - 1;
	struct Node* temp = front;
	if (front==NULL) {
		return;
	}
	if (front==rear) {
		front = rear = NULL;
	}
	else
	{
		front = front->next;

	}
	delete temp;
}
bool CashierQueue::isEmpty()
{
	return (front==NULL);
}
Node* CashierQueue::getTop()
{
	return front;
}
void CashierQueue::writerQueue()
{
	cout << "baþla" << endl;
	Node* iter = front;
	while (iter != NULL) {
		cout << "SAA kasiyer" << iter->event.time << "orderid :  " << iter->event.order.id << endl;
		iter = iter->next;
	}
	
}