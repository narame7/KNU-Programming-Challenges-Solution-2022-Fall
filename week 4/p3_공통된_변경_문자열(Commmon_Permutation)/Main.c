#include <stdio.h>
#include <string.h>

#define min(x, y) ((x) < (y) ? (x) : (y))

void main(void)
{
	unsigned char *s, line1[1001], line2[1001];
	unsigned int count1[256], count2[256];
	unsigned int i, j;
	
	while (gets(line1)) {
		gets(line2);
		for (i = 0; i < 256; i++) {
			count1[i] = count2[i] = 0;
		}
		for (s = line1; *s; s++)
			count1[*s]++;
		for (s = line2; *s; s++)
			count2[*s]++;
		for (i = 0; i < 256; i++) {
			for (j = 0; j < min(count1[i], count2[i]); j++)
				putchar((char)i);
		}
		putchar('\n');
	}
}
