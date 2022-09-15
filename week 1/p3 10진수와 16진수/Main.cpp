#include <iostream>
#include <sstream>
#include <string>
#include <cstring>
using namespace std;

int main(void) {
	string line;
	int n;
	while (getline(cin, line)) {
		istringstream iss(line);
		if (line[0] == '0' && line[1] == 120) {
			cout << dec;
			iss >> hex >> n;
			cout << n << endl;
		} else {
			iss >> n;
			cout << "0x";
			cout << hex << uppercase << n << endl;
		}
	}
	return 0;
}