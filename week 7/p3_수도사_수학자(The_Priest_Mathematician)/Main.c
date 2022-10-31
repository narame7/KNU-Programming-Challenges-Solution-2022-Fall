#include <stdio.h>
#define MAXN 10000
#define NUMBERLENGTH 12
#define ONEDIGIT 10000

typedef struct _longlong {
	unsigned int h[NUMBERLENGTH];
} longlong;

static int n, hn, h4n;
static longlong hanoi[MAXN + 1], hanoi4[MAXN + 1], zero, one;

void assign(longlong *a, unsigned int b)
{
	int i;
	for (i = 0; i < NUMBERLENGTH; i++)
	{
		a->h[i] = b%ONEDIGIT;
		b /= ONEDIGIT;
	}
}

void add(longlong *c, const longlong *a, const longlong *b)
{
	int i, carry;
	
	carry = 0;
	for(i = 0; i < NUMBERLENGTH; i++)
	{
		c->h[i] = a->h[i] + b->h[i] + carry;
		carry = c->h[i]/ONEDIGIT;
		c->h[i] %= ONEDIGIT;
	}
}

int compare(const longlong *a, const longlong *b)
{
	int i;
	
	for (i = NUMBERLENGTH - 1; i >= 0; i--)
	{
		if(a->h[i] < b->h[i])
			return -1;
		if(a->h[i] > b->h[i])
			return 1;
	}
	
	return 0;
}

void print(const longlong *a)
{
	int sw;
	int i;
	sw = 0;
	for(i = NUMBERLENGTH - 1; i >= 0; i--)
	{
		if(!(sw == 0 && a->h[i] == 0))
		{
			if(sw == 0)
			{
				printf("%d", a->h[i]);
				sw = 1;
			}
			else {
				printf("%04d", a->h[i]);
			}
		}
	}
	if(sw == 0)
		printf("0");
}

void calhanoi(int n) {
	for(; hn <= n; hn++)
	{
		add(&hanoi[hn], &hanoi[hn-1], &hanoi[hn-1]);
		add(&hanoi[hn], &hanoi[hn], &one);
	}
}

void solve(void)
{
	int k;
	longlong temp;
	
	for(; h4n <= n; h4n++)
	{
		add(&hanoi4[h4n], &hanoi4[h4n-1], &hanoi4[h4n-1]);
		add(&hanoi4[h4n], &hanoi4[h4n], &hanoi[1]);
		for(k = h4n-2; k > 0; k--)
		{
			calhanoi(h4n - k);
			add(&temp, &hanoi4[k], &hanoi4[k]);
			add(&temp, &temp, &hanoi[h4n-k]);
			if(compare(&hanoi4[h4n], &temp) == 1)
				add(&hanoi4[h4n], &temp, &zero);
			else
				break;
		}
	}
}

void main(void)
{
	assign(&zero, 0);
	assign(&one, 1);
	assign(&hanoi[1], 1);
	assign(&hanoi4[0], 0);
	assign(&hanoi4[1], 1);
	hn = h4n = 2;
	while(scanf("%d", &n) != EOF)
	{
		solve();
		
		char temp[0] = " ";
		print(&hanoi4[n]);
		printf("\n");
	}
}


















