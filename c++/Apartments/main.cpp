#include <iostream>
#include "FunctionManager.h"
#include <string>

using namespace std;
int main(int argc, char* argv[]) {
	FunctionManager f(argv[2]);

	f.process(argv[1]);

}
