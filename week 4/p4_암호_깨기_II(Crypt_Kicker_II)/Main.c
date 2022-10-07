#include <stdio.h>
#include <string.h>

void main() {
	static char pltxt[]="the quick brown fox jumps over the lazy dog";
	static char line[100][100];
	static char map[256],inv[256];
	int num_cases,num_lines;
	int i,j,possible;
	char c;
	
	scanf("%d", &num_cases);
	gets(line[0]);
	gets(line[0]);
	
	while(num_cases-- >0){
		num_lines =0;
		while(gets(line[num_lines])&&line[num_lines][0])
			num_lines++;
		
		possible =0;
		for(i=0;i<num_lines;i++){
			if(strlen(pltxt)!=strlen(line[i]))
				continue;
			for(c='a';c<='z';c++){
				map[c]='\0';
				inv[c]='\0';

			}
			map[' ']=' ';
			for(j=0;line[i][j];j++)
				if(map[line[i][j]] && map[line[i][j]] != pltxt[j]
					 || inv[pltxt[j]] && inv[pltxt[j]]!=line[i][j]){
					goto end_of_loop_i;
				}
			else{
				map[line[i][j]] = pltxt[j];
				inv[pltxt[j]] =line[i][j];
			}
			possible =1;
			break;
		end_of_loop_i: ;
	}
		
	if(possible){
	
		for(i=0;i<num_lines;i++){
			for(j=0;line[i][j];j++)
				putchar(map[line[i][j]]);
			putchar('\n');
		}
	}
	else
		printf("No solution.\n");
	
	if(num_cases >0)
		putchar('\n');
}
}
