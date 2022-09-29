#include <stdio.h>
#include <string.h>

#define MAXN 5000
#define ERDOS "Erdos, P."

static int p, n, nn, erdos;
static char name[MAXN + 1][100];
static int graph[MAXN][4000], find[MAXN], result[MAXN + 1], check[MAXN + 1];

int putname(const char *str)
{ 
	int i;
 
	for(i = 0;i < nn;i++)
	{
		if(strcmp(name[i], str) == 0)
		{
			return i;
		}
	}
	strcpy(name[nn++], str);
  
	return nn - 1;
  
}
void input(void){
  
	int i, j, k, l, len;
	int pn, samepaper[100];
	char line[1000], name1[100];
	nn = 0;
	for(i = 0;i < 5000;i++)
		graph[i][0] = 0;
				  
	scanf("%d %d ", &p, &n);
  
	for(i = 0;i < p;i++)
	{
		gets(line);
		
		k = 0; 
		l = 0; 
		pn = 0;
		len = strlen(line);
		for(j = 0;j < len;j++){
   
			if(line[j] == ':')
			{
				name1[k] = '\0';
				samepaper[pn++] = putname(name1);
				break;
			}
			else if(line[j] == ',')
			{
				l++;
				if(l == 2)
				{
					name1[k] = '\0';
					samepaper[pn++] = putname(name1);
					k = 0; 
					l = 0;
					j++;
					continue;
				}
			}
			name1[k++] = line[j];
		}
		for(j = 0;j < pn;j++)
		{
			for(k = 0;k < pn;k++)
			{
				if(j == k) continue;
	
				for(l = 1;l <= graph[samepaper[j]][0];l++)
					if(graph[samepaper[j]][l] == samepaper[k]) break;
				if(l > graph[samepaper[j]][0])
					graph[samepaper[j]][++graph[samepaper[j]][0]] = samepaper[k];
			}
		}
	}
	for(i = 0;i < n;i++)
	{
		gets(line);
		
		find[i] = putname(line);
	}
	erdos = putname(ERDOS);
}

void solve(void)
{
	int i, j, now;
 
	for(i = 0;i < nn;i++)
	{
		result[i] = 10000;
		check[i] = 0;
	}
	result[erdos] = 0;
	for(i = 0;i < nn;i++)
	{
		now = -1;
		for(j = 0;j < nn;j++)
		{
			if(check[j]) continue;
			if(now == -1 || result[now] > result[j]) now = j;
		}
		if(now == -1 || result[now] == 10000) break;
		check[now] = 1;
		for(j = 1;j <= graph[now][0];j++)
		{
			if(result[graph[now][j]] > result[now] + 1)
				result[graph[now][j]] = result[now] + 1;
		}							   
	}
}
  
int main()
{
	int i, j, t;
	scanf("%d", &t);
	for(i = 1;i <= t;i++)
	{
		input();
		solve();
  
		printf("Scenario %d\n", i);
		for(j = 0;j < n;j++)
		{
			if(result[find[j]] == 10000) printf("%s infinity\n", name[find[j]]);
	   
			else printf("%s %d\n", name[find[j]], result[find[j]]);
		}
	}
	return 0;
}
