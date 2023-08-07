#include<iostream>
#include<string>
#include<fstream>
#include <sstream>
#include <vector>

using namespace std;

int main() {
    ifstream dosyaOku("mapmatrix1.txt");
    string satir = "";
    int mapMatrix[18][18];
    if (dosyaOku.is_open()) {
        int row=0;
        while (getline(dosyaOku, satir)) {
            vector <string> tokens;
            stringstream check1(satir);

            string intermediate;
            while (getline(check1, intermediate, ' '))
            {
                tokens.push_back(intermediate);
            }
            for (int i = 0; i < tokens.size(); i++) {
                mapMatrix[row][i] = stoi(tokens[i]);
                cout << tokens[i]<<" ";
            }
            cout << endl;
                row = row + 1;
        }
        dosyaOku.close();
    }
    ifstream dosyaOku1("keymatrix1.txt");
    string satir1 = "";
    int keyMatrix[3][3];
    if (dosyaOku1.is_open()) {
        int row1 = 0;
        while (getline(dosyaOku1, satir1)) {
          
            vector <string> tokens;
            stringstream check1(satir1);
            string intermediate;
            while (getline(check1, intermediate, ' '))
            {
                tokens.push_back(intermediate);
            }
            for (int i = 0; i < tokens.size(); i++) {
                mapMatrix[row1][i] = stoi(tokens[i]);
                cout << "aaaaarow: " << row1 << " column: " << i << " deðer: " << tokens[i] << endl;
            }
            row1 = row1 + 1;
        }
        dosyaOku.close();
    }
        return 0;
}







