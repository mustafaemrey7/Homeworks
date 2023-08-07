#include<iostream>
#include<string>
#include<fstream>
#include <sstream>
#include <vector>
#include "ReadFile.h"
#include "ProcessManager.h"
using namespace std;
int main(int argc, char* argv[]) {

    ReadFile readFile;
   
    
    int mapRow = readFile.splitter(argv[1])[0];
    int mapColumn = readFile.splitter(argv[1])[1];
    int sizeKey = stoi(argv[2]);
    int** mapMatrix = new int* [mapRow];
    for (int i = 0; i < mapRow; i++) {
        mapMatrix[i] = new int[mapColumn];
    }
    int** keyMatrix = new int* [sizeKey];
    for (int i = 0; i < sizeKey; i++) {
       keyMatrix[i] = new int[sizeKey];
    }

  
    readFile.reader(argv[3], mapMatrix);
    
    readFile.reader(argv[4], keyMatrix);
    
    ProcessManager processManager(argv[5]);
    processManager.findTreasure(mapMatrix, keyMatrix, sizeKey/2, sizeKey/2, sizeKey);
    processManager.deleteMatrix(mapMatrix,mapRow);
    processManager.deleteMatrix(keyMatrix,sizeKey);
    
    return 0;
}







