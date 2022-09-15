#include <stdio.h>
#define BUFSIZE 1024
int main() {
    char line[BUFSIZE];
    
    while (fgets(line, BUFSIZE, stdin) != NULL) {
        int words = 0, letters = 0, i = 0;
        while (line[i] != '\n' && strlen(line) > i) {
            if ((i == 0 && (line[i] != ' ' && line[i] != '\t')) || (i > 0 && (line[i-1] == ' ' || line[i-1] == '\t')  && (line[i] != ' ' && line[i] != '\t')))
                words++;            
            if (line[i] != ' ' && line[i] != '\t')  letters++;
            i++;
        }
        printf("%d %d\n", letters, words);
    }
    
    return 0;
}
