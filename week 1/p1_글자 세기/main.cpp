#include <iostream>
#include <string>
using namespace std;
int main() {
    string line;
    
    while (getline(cin, line)) {
        int words = 0, letters = 0;
        for (int i = 0; i < line.length(); i++) {
            if ((i == 0 && (line[i] != ' ' && line[i] != '\t')) || (i > 0 && (line[i-1] == ' ' || line[i - 1] == '\t') && (line[i] != ' ' && line[i] != '\t')))
                words++;
            if(line[i] != ' ' && line[i] != '\t')   letters++;
        }
        cout << letters << " " << words << endl;
    }
    return 0;
}
