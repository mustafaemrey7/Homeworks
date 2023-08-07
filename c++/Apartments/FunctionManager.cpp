#include "FunctionManager.h"
#include <iostream>

FunctionManager::FunctionManager(string outName) {
	outputFile.open(outName);
	head = NULL;
	last = NULL;
}

void FunctionManager::add_apartment(string name,string location,int max)
{
	if (head==NULL||location=="head") {
		head = new NodeA;
		last = new NodeA;
		head->a.name = name;
		head->a.max_bandwidth = max;
		head->a.currentBand = 0;
		head->a.flatCount = 0;
		head->nextApartment = last;
		head->nextApartment = head;
		head->firstFlat = NULL;
		last = head;
		last->nextApartment = head;
	}
	else
	{
		string order = "";
		try
		{
			order = splitter(location,'_')[0];
			location = splitter(location,'_')[1];
		}
		catch (const std::exception&){}
		if (order=="before")
		{
			NodeA* iter = head;			
			if (location==head->a.name) {	
				NodeA* temp = new NodeA;
				temp->a.flatCount = 0;
				temp->a.currentBand = 0;
				temp->a.name = name;
				temp->a.max_bandwidth = max;
				temp->firstFlat = NULL;
				temp->nextApartment = head;
				head = temp;
				last->nextApartment = head;		
			}
			else
			{		
				while (iter->nextApartment != head && iter->nextApartment->a.name != location) {
					iter = iter->nextApartment;
				}
				NodeA* temp = new NodeA;
				temp->a.name = name;
				temp->a.max_bandwidth = max;
				temp->a.flatCount = 0;
				temp->a.currentBand = 0;
				temp->firstFlat = NULL;
				temp->nextApartment = iter->nextApartment;
				iter->nextApartment = temp;		
			}
		}
		else if (order=="after") {
		
		NodeA* iter = head;
		while (iter->nextApartment != head && iter->a.name != location) {
			iter = iter->nextApartment;
		}
		NodeA* temp = new NodeA;
		temp->a.name = name;
		temp->a.max_bandwidth = max;
		temp->a.currentBand = 0;
		temp->a.flatCount = 0;
		temp->firstFlat = NULL;
		if (location==last->a.name) {
			last->nextApartment = temp;
			temp->nextApartment = head;
			last = temp;
		}
		else { 
			temp->nextApartment = iter->nextApartment;
			iter->nextApartment = temp;
		}
		}
	}
}

void FunctionManager::add_flat(string name,int banwidth,int index,int id,NodeA * head){
	NodeA* iter = head;
	while (iter->nextApartment != head && iter->a.name != name) {
		iter = iter->nextApartment;
	}
	int band = 0;
	if (iter->a.currentBand + banwidth>iter->a.max_bandwidth) {
	
		band = iter->a.max_bandwidth - iter->a.currentBand;
	}
	else
	{
		
		band = banwidth;
	}
	iter->a.currentBand = iter->a.currentBand + band;
	NodeF* temp = new NodeF;
	temp->f.id = id;
	temp->f.bandwith = band;
	temp->f.isEmpty = 0;
	temp->nextFlat = NULL;
	temp->prevFlat = NULL;
	if (band == 0) { temp->f.isEmpty = 1; }
	if (iter->a.flatCount==0) {
		
		iter->firstFlat = temp;
		iter->a.flatCount++;
	}
	else if (iter->a.flatCount > 0) {
		
	
		
		if (index==0) {
			temp->nextFlat = iter->firstFlat;
			iter->firstFlat->prevFlat = temp;
			iter->firstFlat = temp;
			temp->prevFlat = NULL;	
		}
		else {
			NodeF* iterFlat = iter->firstFlat;
		
			int i = 0;
			while (iterFlat->nextFlat != NULL && index != i+1) {
				iterFlat = iterFlat->nextFlat;
				
				i = i + 1;
			}

			temp->nextFlat = iterFlat->nextFlat;
			iterFlat->nextFlat = temp;
			temp->prevFlat = iterFlat;
			if (temp->nextFlat != NULL) {
				temp->nextFlat->prevFlat = temp;
			}
		}
	}
}
void FunctionManager::remove_apartment(NodeA* head,string name) {
	NodeA* iter = head;
	if (head == NULL) {
		outputFile << "there is no apartment";
	}
	else if (iter->a.name == name && head->nextApartment == head) {
		if (iter->a.flatCount == 0) {
			last = NULL;
			head = NULL;
			free(head);	
		}
		else {
			NodeF* iterFlat = iter->firstFlat;
			NodeF* temp;
			while (iterFlat != NULL) {
				temp = iterFlat;
				iterFlat = iterFlat->nextFlat;
				free(temp);
			}
			last = NULL;
			head = NULL;
			free(head);	
		}
	}
	else {

		if (head->a.name == name) {
			NodeF* iterFlat = head->firstFlat;
			NodeF* temp = new NodeF();
			while (iterFlat != NULL) {
				temp = iterFlat;
				iterFlat = iterFlat->nextFlat;
				delete(temp);
			}
			while (last->nextApartment != head) {
				last = last->nextApartment;
			}
			last->nextApartment = head->nextApartment;
			free(head);
			head = last->nextApartment;
		}
		else {
		NodeA* iter = head;
		while (iter->nextApartment != head && iter->nextApartment->a.name != name) {
			iter = iter->nextApartment;
		}
	
		NodeF* iterFlat = iter->nextApartment->firstFlat;
		NodeF* temp = new NodeF();
		
		while (iterFlat != NULL) {
			
			temp = iterFlat;
			iterFlat = iterFlat->nextFlat;
			delete(temp);
		}
		if (iter->nextApartment == last) {
			
			delete(iter->nextApartment);

			iter->nextApartment = head;
			last = iter;
		}
		else {
		
			NodeA* tempA;
			tempA = iter->nextApartment;
			iter->nextApartment = iter->nextApartment->nextApartment;
			free(tempA);
		}
		}
	}
}
void FunctionManager::process(string file) {
	
	ifstream input(file);

	for (std::string line; getline(input, line); )
	{
		string command = splitter(line, '\t')[0];
		if (command=="add_apartment") {
			string name = splitter(line, '\t')[1];
			string location = splitter(line, '\t')[2];
			int max = stoi(splitter(line, '\t')[3]);
			add_apartment(name,location,max);	
		}
		else if (command=="add_flat") {
			string name = splitter(line, '\t')[1];
			int index = stoi(splitter(line, '\t')[2]);
			int max = stoi(splitter(line, '\t')[3]);
			int id = stoi(splitter(line, '\t')[4]);
			add_flat(name,max,index,id,head);	
		}
		else if (command=="remove_apartment") {
			remove_apartment(head, splitter(line, '\t')[1]);
		}
		else if (command == "merge_two_apartments")
		{
			merge_two_apartments(head, splitter(line, '\t')[1], splitter(line, '\t')[2]);  
		}
		else if (command == "find_sum_of_max_bandwidths")
		{
			outputFile <<"sum of bandwidth: " << find_sum_of_max_bandwidth(head)<<endl;
		}
		else if (command == "make_flat_empty")
		{
			make_flat_empty(head, splitter(line, '\t')[1], stoi(splitter(line, '\t')[2]));
		}
		else if (command == "relocate_flats_to_same_apartment")
		{
			int k = splitter(line, '\t')[3].length();
			string idList =  splitter(line, '\t')[3].substr(1,k-2);
			relocate_flats_to_same_apartments(head, splitter(line, '\t')[1],idList,stoi(splitter(line, '\t')[2]));
		}
		else if (command == "list_apartments")
		{
			outputFile << endl;
			list_apartments(head);
			outputFile << endl;
		}
	}
}
vector<string> FunctionManager::splitter(string argv,char c)
{
	stringstream commands(argv);
	string element;
	vector<string> argvList;
	while (std::getline(commands, element, c))
	{
		argvList.push_back(element);
	}
	return argvList;
}
void FunctionManager::make_flat_empty(NodeA* head,string name,int id) {

	NodeA* iter = head;
	while (iter->nextApartment != head && iter->nextApartment->a.name != name) {
		iter = iter->nextApartment;
	}
	NodeF* iterFlat = iter->nextApartment->firstFlat;

	while (iterFlat != NULL&&iterFlat->f.id!=id) {	
		iterFlat = iterFlat->nextFlat;
	}
	if (iterFlat==NULL) {
		outputFile << "There is no flat";
	}
	else
	{
	iterFlat->f.isEmpty = 1;
	iter->a.currentBand = iter->a.currentBand - iterFlat->f.bandwith;
	iterFlat->f.bandwith = 0;
	}
}
void FunctionManager::list_apartments(NodeA*) {
	if (head==NULL) {
		outputFile << "There is no apartment";
	}
	else {
		{
		NodeA* iter = head;
			do
			{
			outputFile << iter->a.name<< "("<<iter->a.max_bandwidth<<")"<<"\t";
				
			NodeF* iterFlat = iter->firstFlat;
			do {
				if (iterFlat == NULL) {		
					}
				else
				{
					outputFile << "Flat" <<iterFlat->f.id << "("<< iterFlat->f.bandwith << ")" << "\t";
				 
				iterFlat = iterFlat->nextFlat;
				}
			} while (iterFlat != NULL);	
			iter = iter->nextApartment;
			outputFile << endl;
			} while (iter != head);
		}
	}
}
int FunctionManager::find_sum_of_max_bandwidth(NodeA*) {
	if (head==NULL) {
		return 0;
	}
	else
	{
		int total = 0;
		NodeA* iter = head;
		do
		{
			total = total + iter->a.max_bandwidth;
			iter = iter->nextApartment;
			
		} while (iter != head);
		return total;
	}
}
void FunctionManager::merge_two_apartments(NodeA* head,string a1,string a2) {
	if (head == NULL) {
	}
	else
	{
		NodeA* iter1 = head;
		do
		{
			if (iter1->a.name == a1) {
				break;
			}
			else
			{
			iter1 = iter1->nextApartment;	
			}	
		} while (iter1 != head);
		NodeA* iter2 = head;
		do
		{
			if (iter2->a.name==a2)
			{
				break;
			}
			else {		
			iter2 = iter2->nextApartment;	
			}
		} while (iter2 != head);
		iter1->a.max_bandwidth = iter1->a.max_bandwidth + iter2->a.max_bandwidth;
		if (iter1->a.flatCount==0) {
			iter1->firstFlat = iter2->firstFlat;
			iter2->a.flatCount = 0;
			iter2->firstFlat = NULL;
			remove_apartment(head, a2);
		}
		else
		{
			NodeF* iterFlat1 = iter1->firstFlat;
			while (iterFlat1->nextFlat!=NULL) {
			iterFlat1 = iterFlat1->nextFlat;
			}
			if (iter2->a.flatCount==0) {	
				iter2->firstFlat == NULL;
				remove_apartment(head,a2);
			}
			else {
				iterFlat1->nextFlat = iter2->firstFlat;
				iter2->firstFlat->prevFlat = iterFlat1;
				iter2->a.flatCount = 0;
			
				iter2->firstFlat = NULL;
				remove_apartment(head, a2);
			}
		}		
	}	
}
void FunctionManager::relocate_flats_to_same_apartments(NodeA* head,string name,string idList,int indexShift) {
	
	if (head == NULL) {
		writerOutput("There is no apartment");
	}
	else {
		NodeA* iter = head;
		while (iter->nextApartment!=head&&iter->a.name!=name) {
			iter = iter->nextApartment;
		}
		NodeF* iterF = iter->firstFlat;
		bool isHead = 0;
		if (iterF->f.id == indexShift) {
			isHead = 1;
		}
		while (iterF->f.id!=indexShift)
		{
			iterF = iterF->nextFlat;
		}	
		vector<string> list = splitter(idList,',');
		for (size_t i = 0; i <list.size(); i++)
		{
			NodeA* iterA = head;
			while (iterA->nextApartment != head) {
				NodeF* iterF1 = iterA->firstFlat;
			while (iterF1!=NULL)
			{
				if (iterF1->f.id==stoi(list[i])) {
					iter->a.max_bandwidth = iter->a.max_bandwidth + iterF1->f.bandwith;
					iterA->a.max_bandwidth = iterA->a.max_bandwidth - iterF1->f.bandwith;
					iter->a.currentBand = iter->a.currentBand + iterF1->f.bandwith;
					if (isHead==1) {
						if (iterF1->nextFlat==NULL) {
						iter->firstFlat = iterF1;
						iterF1->prevFlat->nextFlat = iterF1->nextFlat;
						iterF1->nextFlat = iterF;
						iterF->prevFlat = iterF1;
						iterF1->prevFlat = NULL;
						isHead = 0;
						}
						else if (iterF1->nextFlat!=NULL&&iterF1->prevFlat!=NULL) {
							iter->firstFlat = iterF1;
							iterF1->prevFlat->nextFlat = iterF1->nextFlat;
							iterF1->nextFlat->prevFlat = iterF1->prevFlat;
							iterF->prevFlat = iterF1;
							iterF1->nextFlat = iterF;
							iterF1->prevFlat = NULL;
							isHead = 0;
						}
						else if (iterF1->prevFlat==NULL) {
							iterA->firstFlat = iterF1->nextFlat;
							iterF1->nextFlat = iterF;
							iterF->prevFlat = iterF1;
							iterF1->prevFlat = NULL;
							iterA->firstFlat->prevFlat = NULL;
							isHead = 0;
						}			
					}
					else
					{
						if (iterF1->nextFlat == NULL) {
							iterF->prevFlat->nextFlat = iterF1;
							iterF1->prevFlat = iterF->prevFlat;
							iterF1->nextFlat = iterF;
							iterF->prevFlat = iterF1;	
						}
						else if (iterF1->nextFlat != NULL && iterF1->prevFlat != NULL) {
							iterF1->prevFlat->nextFlat = iterF1->nextFlat;
							iterF1->nextFlat->prevFlat = iterF1->prevFlat;
							iterF->prevFlat->nextFlat = iterF1;
							iterF1->prevFlat = iterF->prevFlat;
							iterF->prevFlat = iterF1;
							iterF1->nextFlat = iterF;	
						}
						else if (iterF1->prevFlat == NULL) {
							iterA->firstFlat = iterF1->nextFlat;
							iterF->prevFlat->nextFlat = iterF1;
							iterA->firstFlat->prevFlat = NULL;
							iterF1->nextFlat = iterF;
							iterF->prevFlat = iterF1;
							iterF1->prevFlat = NULL;
						}
					}		
					break;
				}
				else
				{
					iterF1 = iterF1->nextFlat;
				}			
			}
				iterA = iterA->nextApartment;
			}
		}
	}
}
void FunctionManager::writerOutput(string text) {

	outputFile << text << endl;
}