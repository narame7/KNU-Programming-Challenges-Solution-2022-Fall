#include <stdio.h>

#define MAX_CONTESTANTS 100
#define MAX_PROBLEMS 9
#define UNIT_PENALTY 20

typedef struct {
	int num_solved;
	int tot_penalty;
	int ever_submitted;
}contestans;

typedef struct {
	int solved;
	int penalty;
}problem_record;

int lessthan(contestans a,contestans b) 
{		
		if(a.ever_submitted<b.ever_submitted) return 1;
		if(a.ever_submitted>b.ever_submitted) return 0;
	 	if(a.num_solved<b.num_solved) return 1;
		if(a.num_solved>b.num_solved) return 0;
		if(a.tot_penalty > b.tot_penalty) return 1;

		return 0;
	}


int main() {
	
	
	char line[100],result[10],*r;
	int num_cases;
	int i,j,c,p,t,max,temp;
	problem_record rec[MAX_CONTESTANTS+1][MAX_PROBLEMS+1];
	contestans cont[MAX_CONTESTANTS+1];
	int perm[MAX_CONTESTANTS+1];
	
	scanf("%d",&num_cases);
	gets(line);
	gets(line);
	while(num_cases-->0){
		for(i =1;i<=MAX_CONTESTANTS;i++){
			cont[i].num_solved=cont[i].tot_penalty
								=cont[i].ever_submitted=0;
			for(j=1;j<=MAX_PROBLEMS;j++)
				rec[i][j].penalty=rec[i][j].solved=0;
		}
		while(gets(line)&& *line){
			sscanf(line,"%d %d %d %s",&c,&p,&t,(r=result));
			while(*r==' ')
				r++;
			cont[c].ever_submitted=1;
			if(*r=='C'&& !rec[c][p].solved){
				rec[c][p].solved=1;
				rec[c][p].penalty+=t;
			}
			else if (*r =='I'&& !rec[c][p].solved)
				rec[c][p].penalty +=20;
		}
		for(i=1;i<=MAX_CONTESTANTS;i++)
			for(j=1;j<=MAX_PROBLEMS; j++)
				if(rec[i][j].solved){
					cont[i].num_solved++;
					cont[i].tot_penalty+= rec[i][j].penalty;
					
				}
	for(i=1;i<=MAX_CONTESTANTS;i++)
		perm[i]=i;
		
	for(i=1;i<MAX_CONTESTANTS; i++){
		max=i;
			for(j=i+1;j<=MAX_CONTESTANTS;j++)
				if(lessthan(cont[perm[max]],cont[perm[j]]))
					max=j;
		
			temp=perm[max];
			perm[max]=perm[i];
			perm[i]=temp;
	
		
		}
		
		for(i=1;i<=MAX_CONTESTANTS;i++)
			if(cont[perm[i]].ever_submitted)
			printf("%d %d %d\n",perm[i],cont[perm[i]].num_solved,
						cont[perm[i]].tot_penalty);
		if(num_cases)
			putchar('\n');
	}
}
