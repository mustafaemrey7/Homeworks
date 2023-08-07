#include "ProcessManager.h"

using namespace std;


ProcessManager::ProcessManager(string outName) {
	outputFile.open(outName);
}

void ProcessManager::findTreasure(int** m1,int** m2,int row,int column,int sizeOfKeyMatrix){

	int bound = sizeOfKeyMatrix / 2;
	int result = 0;
	int startRow = row - bound;
	int startColumn = column - bound;
		for (size_t i = 0; i < sizeOfKeyMatrix; i++)
		{
			for (size_t k = 0; k < sizeOfKeyMatrix;k++) {
				result = result + m1[startRow +i][startColumn +k] * m2[i][k];
			}
		}

	if (result < 0) {
		while (result < 5) {
			result = result + 5;
		}
	}
	string direct = "";
	if (result % 5 == 0) {
		direct = "T";
	}
	else if (result % 5 == 1) {
		direct = "U";
	}
	else if (result % 5 == 2) {
		direct = "D";
	}
	else if (result % 5 == 3) {
		direct = "R";
	}
	else if (result % 5 == 4) {
		direct = "L";
	}
	writerOutput(row,column,result);
	
	if (direct == "T") {  }
	else if (direct == "U") {	
		if (m1[row- bound][column- bound]==0&& m1[row - bound][column] == 0&&m1[row - bound][column + bound] == 0) {
			findTreasure(m1, m2, row + sizeOfKeyMatrix, column, sizeOfKeyMatrix);	
		}
		else
		{
			findTreasure(m1, m2, row - sizeOfKeyMatrix, column, sizeOfKeyMatrix);
		}
	}
	else if (direct == "D") {
		if (m1[row + bound][column - bound] == 0 && m1[row + bound][column] == 0 && m1[row + bound][column + bound] == 0) {
			findTreasure(m1, m2, row - sizeOfKeyMatrix, column, sizeOfKeyMatrix);
		}
		else
		{
			findTreasure(m1, m2, row + sizeOfKeyMatrix, column, sizeOfKeyMatrix);
		}
	}
	else if (direct == "R") {	
		if (m1[row - bound][column + bound] == 0 && m1[row][column+bound] == 0 && m1[row + bound][column + bound] == 0) {
			findTreasure(m1, m2, row, column-sizeOfKeyMatrix, sizeOfKeyMatrix);
		}
		else
		{
			findTreasure(m1, m2, row, column+sizeOfKeyMatrix, sizeOfKeyMatrix);
		}
	}
	else if (direct == "L") {
		if (m1[row - bound][column - bound] == 0 && m1[row ][column-bound] == 0 && m1[row +bound][column - bound ] == 0) {
			findTreasure(m1, m2, row, column+sizeOfKeyMatrix, sizeOfKeyMatrix);
		} 
		else
		{
			findTreasure(m1, m2, row , column-sizeOfKeyMatrix, sizeOfKeyMatrix);
		}
	}	
}

void ProcessManager::writerOutput(int row,int column,int result) {

	outputFile<< row << "," << column << ":" << result << endl;
}

void ProcessManager::deleteMatrix(int** m1,int row) {

	for (auto i = 0; i <row; i++)
	{
		delete[] m1[i];
	}

	delete[] m1;
}

ProcessManager::~ProcessManager()
{
}
