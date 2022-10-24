#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAXN 1000

static int n, fare[24];
static int data[MAXN][3], tag[MAXN];
static int rn, amount[MAXN/2];
static char carno[MAXN][21], car[MAXN/2][21];

void input(void)
{
	int i;
	int month, day, hour, min;
	char line[50], type[10];
	
	for(i = 0; i < 24; i++)
		scanf("%d", &fare[i]);

	n = 0;
	
	gets(line);
	
	while(gets(line) && strlen(line) != 0)
	{
		sscanf(line, "%s %d:%d:%d:%d %s %d", carno[n], &month, &day, &hour, &min, type, &data[n][2]);
		
		data[n][0] = day*10000 + hour*100 + min;
			
		if(strcmp(type, "enter") == 0)
			data[n][1] = 0;
		else
			data[n][1] = 1;
		
		n++;
	}
}

int compare(int a, int b)
{
	int cmp = strcmp(carno[a], carno[b]);
	
	if(cmp != 0) return cmp;
	
	if(data[a][0] < data[b][0])
		return -1;
	else if(data[a][0] > data[b][0])
		return 1;
	
	return 0;
}

void sort(int left, int right) {
	int k = tag[(left + right) / 2];
	int l = left, r = right;
	int temp;
	
	while(l < r) {
		for(; compare(tag[l], k) < 0; l++);
		for(; compare(tag[r], k) > 0; r--);
		
		if(l <= r) {
			temp = tag[l];
			tag[l] = tag[r];
			tag[r] = temp;
			l++;
			r--;
		}
	}
	if(left < r) sort(left, r);
	if(l < right) sort(l, right);
}

void solve(void)
{
	int i, j, time;
	int valid, prev;
	
	for(i = 0; i < n; i++)
		tag[i] = i;
	
	sort(0, n-1);
	
	rn = 0;
	valid = 0;
	prev = tag[0];
	for(i = 0; i < n; i++)
	{
		j = tag[i];
		
		if(strcmp(carno[j], carno[prev]) != 0)
			valid = 0;
		
		if(data[j][1] == 0)
		{
			valid = 1;
			prev = j;
		}
		else if(valid)
		{
			if(rn == 0 || strcmp(car[rn-1], carno[j]) != 0)
			{
				strcpy(car[rn++], carno[j]);
				amount[rn-1] = 200;
			}
			amount[rn-1] += 100;
			
			time = (data[prev][0]/100)%100;
			amount[rn-1] += fare[time]*abs(data[prev][2] - data[j][2]);
			valid = 0;
		}
	}
}

void output(void)
{
	int i;
	
	for(i = 0; i < rn; i++)
		printf("%s $%d.%02d\n", car[i], amount[i]/100, amount[i]%100);
}

void main(void)
{
	int i, t;

	scanf("%d", &t);
	
	for(i = 0; i < t; i ++)
	{
		input();
		solve();
		if(i > 0)
			printf("\n");
		output();
	}
}