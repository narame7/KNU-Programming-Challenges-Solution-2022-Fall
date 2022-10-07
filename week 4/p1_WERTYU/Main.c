#include <stdio.h>
#include <string.h>

#define LINES_NUM 4

const char* lines[LINES_NUM] ={"`1234567890-=","QWERTYUIOP[]\\","ASDFGHJKL;","ZXCVBNM,./"};

void main() 
{
while(!feof(stdin))
{
	int i;
	char temp[500];
	if(!gets(temp))
		break;
	
	for(i=0; i< (int)strlen(temp); i++){
		
		int j;
		int decode_ok = 0;
		
		for(j=0; j<LINES_NUM; j++)
		{
		if(strchr(lines[j],temp[i]))
			{
				printf("%c", *(strrchr(lines[j],temp[i])-1));
				decode_ok=1;
			}
		}
		
		if(!decode_ok)
			printf(" ");
	}
	printf("\n");
}
}
