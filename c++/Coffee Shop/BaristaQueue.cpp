#include "BaristaQueue.h"
#include "Node.h"
#include <string>

void BaristaQueue::enQueue(Event e)
{
	size = size + 1;
	if (size>=maxQueue)
	{
		maxQueue = size;
	}
	Node* iter = front;
	struct Node* temp = new Node(e);
	if (front==NULL) {
		front = rear = temp;
		return;
	}
	
	if ((e.order.price)>(front->event.order.price)) {
		temp->next = front;
		front = temp;
		return;
	 
	}
	while (iter->next!=NULL) {
		if (e.order.price>=iter->next->event.order.price)
		{
			temp->next = iter->next;
			iter->next = temp;
			return;
		}	
		iter = iter->next;
	}
	if (iter->next==NULL)
	{
		rear->next = temp;
		rear = temp;
	}
}
				
void BaristaQueue::deQueue()
{
	size = size - 1;
	struct Node* temp;
	
	if (front == NULL) {
		return;
	}
	temp = front;
	front = front->next;
	delete temp;
}
bool BaristaQueue::isEmpty()
{
	return (front==NULL);
}
Node* BaristaQueue::getTop()
{
	return front;
}
void BaristaQueue::writerQueue()
{
	Node* iter = front;
	cout << "------------------" << endl;
	while (iter != NULL) {
		cout << "order id: " << iter->event.order.id << "time: " << iter->event.time << " type: " << iter->event.type << " worker id: " << iter->event.workerId <<" priice: " <<iter->event.order.price<< endl;
		iter = iter->next;
	}
}
