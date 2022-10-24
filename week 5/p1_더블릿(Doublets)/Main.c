#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_WORDS 25143
#define MAX_WORDLEN 16
#define MAX_DOUBLET_NUM (16*(26-1))

typedef struct
{
	char str[MAX_WORDLEN+1];
	char comp_str[MAX_WORDLEN+1];
	int index;
	int doublet_num;
	short int doublets[MAX_DOUBLET_NUM];
	int backlink;
}word;

word* word_ptr[MAX_WORDS];

int queue[MAX_WORDS];
int head,tail;
int num_words;

int compare_by_str(const void *arg1,const void *arg2){
	word* ptr1;
	word* ptr2;
	ptr1=*(word **)arg1;
	ptr2=*(word **)arg2;
	
	if(strlen(ptr1 -> comp_str)!=strlen(ptr2->comp_str))
		return strlen(ptr1->comp_str)-strlen(ptr2->comp_str);
	return strcmp(ptr1->comp_str,ptr2->comp_str);
}

int compare_by_index(const void *arg1,const void *arg2){
	word* ptr1;
	word *ptr2;

	ptr1 = *(word **)arg1;
	ptr2 = *(word **)arg2;
	return ptr1 -> index - ptr2->index;
}
void find_doublet()
{
	int i,j;
	for(i=0;i<MAX_WORDLEN;i++){
		for(j=0;j<num_words;j++){
			if(strlen(word_ptr[j]->str)<=i){
				word_ptr[j]->comp_str[0]=0;
			}
			else{
				strncpy(word_ptr[j]->comp_str,word_ptr[j]->str,i);
				word_ptr[j]->comp_str[i]=0;
				strcat(word_ptr[j]->comp_str,word_ptr[j]->str+i+1);
				strncat(word_ptr[j]->comp_str,word_ptr[j]->str+i,1);
				word_ptr[j]->comp_str[strlen(word_ptr[j]->str)]=0;
			}
		}
		qsort((void *)word_ptr,(size_t)num_words,sizeof(word *),compare_by_str);
		
		for(j=0;j<num_words;j++){
			int end = j;
			while(end < num_words -1){
				int length1=strlen(word_ptr[j]->comp_str);
				if (length1==strlen(word_ptr[end+1]->comp_str)&&
						!strncmp(word_ptr[j]-> comp_str,word_ptr[end+1]->comp_str,length1-1)&&
						word_ptr[j]->comp_str[length1-1]!= word_ptr[end+1]-> comp_str[length1-1])
					end++;
				else
					break;
			}
			if(end >j)
			{
				int k,l;
				for(k=j;k<end;k++)
					for(l=k+1;l<= end;l++)
					{
						word_ptr[k]->doublets[word_ptr[k]->doublet_num++]=word_ptr[l]->index;
						word_ptr[l]->doublets[word_ptr[l]->doublet_num++]=word_ptr[k]->index;
					}
				j=end;
			}
		}
	}
			qsort((void *)word_ptr,(size_t)num_words,sizeof(word *),compare_by_index);
}
int find_index(char *s)
{
	int i;
	for(i=0;i<num_words;i++)
		if(!strcmp(s,word_ptr[i]->str))
			return i;
	return -1;
}
int queue_empty(void){
	return (tail-head +1 <=0);
}
void init_queue(void){
	head =0;
	tail=-1;
}
void enqueue(int k){
	tail++;
	queue[tail]=k;
}
int dequeue(void)
{
	return queue[head++];
}
void print(int k){
	int order[MAX_WORDS];
	int i;
	i=0;
while(k>=0){
	order[i++]=k;
	k=word_ptr[k]->backlink;
	}
	while(--i>=0)
		puts(word_ptr[order[i]]->str);
}
	
void main() {
	char line[256];
	int src_idx,dest_idx;
	int t,i,k,solved;
	int first = 1;
	num_words=0;
	while(gets(line) && strlen(line)>0)
	{
		word_ptr[num_words]=malloc(sizeof(word));
		word_ptr[num_words]->index=num_words;
		word_ptr[num_words]->doublet_num =0;
		strcpy(word_ptr[num_words++]->str, line);
	}
	find_doublet();
	
	while(gets(line)){
		if(strlen(line)==0)
			break;
		
		if(!first)putchar('\n');
		first=0;
		
		t=0;
		while(line[t]!=' ')
			t++;
		line[t]='\0';
		src_idx=find_index(line);
		dest_idx=find_index(line+(t+1));
		
		for(i=0;i<num_words;i++)
			word_ptr[i]->backlink=-1;
		
		solved=0;
		init_queue();
		enqueue(src_idx);
		
		while(!queue_empty()){
			k=dequeue();
			if(k==dest_idx){
				print(k);
				solved=1;
				break;
			}
			for(i=0;i<word_ptr[k]->doublet_num;i++){
				int target =word_ptr[k]->doublets[i];
				if(target != src_idx
					&& word_ptr[target]->backlink<0){
					word_ptr[target]->backlink=k;
					enqueue(target);
				}
					
			}
		}
		if(!solved)
			printf("No solution.\n");
	}
for(i=0;i<num_words;i++)
	free(word_ptr[i]);
}
