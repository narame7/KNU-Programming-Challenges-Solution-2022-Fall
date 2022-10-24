#include <stdio.h>
#include <string.h>

#define LIMIT 72
#define MAX_CHAR 2048

char line[MAX_CHAR] = "";
char line_next[MAX_CHAR] = "";

void main()
{
	int enter_present = 0;
	char temp[0] = "";
	
	while (1)
	{
		if ( !fgets(line, MAX_CHAR, stdin) )
		{
			if ( strlen(line_next) > 0 )
			{
				printf("%s", line_next);
				if ( enter_present )
					printf("\n");
			}
			break;
		}
		
		if ( line[strlen(line) - 1] == 10 )
		{
			enter_present = 1;
			line[strlen(line) - 1] = 0;
		}
		else
			enter_present = 0;
		
		while (strlen(line) > 0 && line[strlen(line) - 1] == ' ')
			line[strlen(line)-1] = 0;
		
		if ( strlen(line) == 0 )
		{
			if ( strlen(line_next) > 0)
				printf("%s\n", line_next);
			if ( enter_present )
				printf("\n");
			strcpy(line_next, "");
			
			continue;
		}
		
		{
			int length = 0;
			while ( (line[length] != ' ' && line[length] != 0) )
			{
				length ++;
			}
			
			if ( strlen(line_next) + length <= LIMIT && line[0] != ' ' )
			{
				char* line_start = line;
				if ( strlen(line_next) > 0 )
				{
					while (line_start[0] == ' ') line_start ++;
					strcat(line_next, " ");
				}
				strcat(line_next, line_start);
				strcpy(line, line_next);
				strcpy(line_next, "");
			}
			else
			{
				char* line_pointer;
				
				if ( strlen(line_next) > 0 )
					printf("%s\n", line_next);
				strcpy(line_next, "");
				
				line_pointer = line;
				/*while ( line_pointer[0] == ' ' )
					line_pointer ++;*/
				strcpy(line, line_pointer);
			}
		}
		
		{
			while ( 1 )
			{
				char* line_pointer;
				
				if ( strlen(line) <= LIMIT )
				{
					strcpy(line_next, line);
					break;
				}
				
				line_pointer = line + LIMIT;
				while ( line_pointer >= line && line_pointer[0] != ' ' )
					line_pointer --;
				
				if ( line_pointer < line )
				{
					line_pointer = line + LIMIT;
					while ( line_pointer[0] != ' ' && line_pointer[0] != 0 )
						line_pointer ++;
					
					if ( line_pointer[0] != 0 )
					{
						line_pointer[0] = 0;
						
						line_pointer ++;
						while ( line_pointer[0] == ' ' )
							line_pointer ++;
					}
					else
					{
						strcpy(line_next, line);
						break;
					}
				}
				else
				{
					while ( line_pointer[0] == ' ' )
						line_pointer --;
					line_pointer ++;
					
					line_pointer[0] = 0;
					
					line_pointer ++;
					while ( line_pointer[0] == ' ' )
						line_pointer ++;
				}
				
				printf("%s", line);
				if ( line_pointer[0] != 0 || enter_present )
					printf("\n");
				
				strcpy(line, line_pointer);
			}
		}
	}
}