#include <stdio.h>
#define ARRAYSIZE 1000000

static long sequence[ARRAYSIZE];

void main(void)
{
	int i, n, count, f, sum;
	
	while(scanf("%d", &n), n != 0)
	{
		sequence[1] = 1;
		sequence[2] = 2;
		count = 3;
		f = 2;
		sum = 3;
		for(i = 3; i < ARRAYSIZE; i++)
		{
			sequence[i] = f;
			sum += f;
			if(sum >= n)
				break;
			
			if(i == count)
			{
				f++;
				count += sequence[f];
			}
		}
		if(n == 1)
			i = 1;
		else if(n <= 3)
			i = 2;
		
		printf("%d\n", i);
	}
}