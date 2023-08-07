#include "ReadFile.h"

using namespace std;
void ReadFile::reader(string txtName,int** matrix) {
    ifstream dosyaOku(txtName);
    string satir = "";   
    if (dosyaOku.is_open()) { 
        int row = 0;
        while (getline(dosyaOku, satir)) {
            vector <string> tokens;
            stringstream check1(satir);
            string intermediate;
            while (getline(check1, intermediate, ' '))
            {
                tokens.push_back(intermediate);
            }
            for (int i = 0; i < tokens.size(); i++) {
                matrix[row][i] = stoi(tokens[i]);    
            }     
            row = row + 1;
        }
        dosyaOku.close();
    }
}
void ReadFile::writer(int x,int y ,int** m){
    for (int i = 0; i < x; i++) {
        for (int j = 0; j < y; j++) {
            cout << m[i][j]<<" ";
        }
        cout << endl;
    }
}

vector<int> ReadFile::splitter(string argv)
{
    stringstream commands(argv);
    string element;
    vector<int> argvList;
    while (std::getline(commands, element, 'x'))
    {
        argvList.push_back(std::stoi(element));
    }
    return argvList;
}


