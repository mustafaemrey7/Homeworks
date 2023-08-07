#include "EventQueue.h"
#include <iostream>


void EventQueue::enQueue(Event e)
{
	Node* iter =front;
	struct Node* temp = new Node(e);
	if (front==NULL) {
		front = temp;
		front->next = NULL;
	}
	else
	{
		while (iter != NULL) {
			if (iter->next==NULL) {	
				iter->next = temp;
				temp->next = NULL;
				break;
			}
			else
			{
				if (iter->next->event.time>temp->event.time&& temp->event.time>=iter->event.time) {
						temp->next = iter->next;
						iter->next = temp;				
				break;
				}
			}
			iter = iter->next;
		}
	}
}
void EventQueue::deQueue()
{
	struct Node* temp;
	if (front == NULL) {
		return;
	}
	temp = front;
	front = front->next;
	delete temp;
}
bool EventQueue::isEmpty()
{
	return (front == NULL);
}
void EventQueue::getTop()
{
}
void EventQueue::writerQueue()
{
	Node* iter = front;
	cout << "------------------" << endl;
	while (iter != NULL) {
		cout <<"order id: " <<iter->event.order.id<<"time: " << iter->event.time << " type: " << iter->event.type << " worker id: " << iter->event.workerId << endl;
		iter = iter->next;
	}
}
