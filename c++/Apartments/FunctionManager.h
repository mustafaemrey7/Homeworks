#pragma once
#include <string>
#include <vector>
#include <sstream>
#include <fstream>
using namespace std;


class FunctionManager
{
	struct Apartment
	{
		int currentBand;
		string name;
		int max_bandwidth;
		int flatCount;

	};
	struct Flat
	{	
		int isEmpty;
		int index;
		int id;
		int bandwith;


	};
	struct NodeF
	{
		Flat f;
		NodeF *nextFlat;
		NodeF *prevFlat;

	};
	struct NodeA
	{

		Apartment a;
		NodeA *nextApartment;
		NodeF *firstFlat;

	};

public:
	NodeA *head;
	NodeA *last;
	ofstream outputFile;
	FunctionManager(string);
	void add_apartment(string,string,int);
	void process(string);
	vector<string> splitter(string,char);
	

	void add_flat(string,int,int,int,NodeA*);
	void remove_apartment(NodeA*,string);
	void make_flat_empty(NodeA*,string,int);
	void list_apartments(NodeA*);
	int find_sum_of_max_bandwidth(NodeA*);
	void merge_two_apartments(NodeA*,string,string);
	void relocate_flats_to_same_apartments(NodeA*,string,string,int);
	void writerOutput(string);
private:
		
};



