#include <stdio.h>
#define get_value(x) ((x) / 10)
#define get_suit(x) ((x) % 10)

/* 문자열 형태의 카드 값을 숫자 표현으로 변환 */
int encode_card(char *card)
{
	int result;
	
	switch (card[0]) {
		case 'T': result = 100; break;
		case 'J': result = 110; break;
		case 'Q': result = 120; break;
		case 'K': result = 130; break;
		case 'A': result = 140; break;
		default : result = (card[0] - '0') * 10;
	}
	switch (card[1]) {
		case 'H': result += 1; break;
		case 'D': result += 2; break;
		case 'S': result += 3; break;
		case 'C': result += 4; break;
	}
	return result;
}

/* ranking function */
long get_hand_value(int hand[5])
{
	int i, j, max, temp;
	int value[5], suit[5];
	long result;
	
	/* 패를 정렬해두면 족보 판정을 훨씬 쉽게 할 수 있고 같은 족보일 때 비교하기 위한
	제일 높은 카드도 쉽게 찾을 수 있으므로 hand value를 쉽게 계산할 수 있다. */
	for (i = 0; i < 4; i++) {
		max = i;
		for (j = i + 1; j < 5; j++)
			if (hand[j] > hand[max])
				max = j;
		temp = hand[i];
		hand[i] = hand[max];
		hand[max] = temp;
	}
	
	for (i = 0; i < 5; i++) {
		value[i] = get_value(hand[i]);
		suit[i] = get_suit(hand[i]);
	}
	
	/* straight flush */
	if (value[1] + 1 == value[0] && suit[1] == suit[0]
		 && value[2] + 2 == value[0] && suit[2] == suit[0]
		 && value[3] + 3 == value[0] && suit[3] == suit[0]
		 && value[4] + 4 == value[0] && suit[4] == suit[0])
		
		result = (9 << 20) + (value[0] << 16);
	
	/* four of a kind 정렬된 상태이므로 카드를 일일히 세지 않고도
	1, 4번째 카드가 같거나 또는 2, 5번째 카드가 같은지만 검사하면 된다. */
	else if (value[0] == value[3] || value[1] == value[4])
		
		result = (8 << 20) + (value[1] << 16); /* 2번째 카드는 무조건 포카드에 포함된다 */
	
	/* full house. 2장+3장 또는 3장+2장 형태이므로
	다음과 같이 아주 간단하게 검사할 수 있다. */
	else if (value[0] == value[2] && value[3] == value[4]) /* 3장 + 2장 */
		
		result = (7 << 20) + (value[0] << 16);
	
	else if (value[0] == value[1] && value[2] == value[4]) /* 2장 + 3장 */
		
		result = (7 << 20) + (value[2] << 16);
	
	/* flush */
	else if (suit[1] == suit[0] && suit[2] == suit[0]
					&& suit[3] == suit[0] && suit[4] == suit[0])
		
		result = (6 << 20) + (value[0] << 16) + (value[1] << 12)
		+ (value[2] << 8) + (value[3] << 4) + value[4];
	
	/* straight */
	else if (value[1] + 1 == value[0] && value[2] + 2 == value[0]
					&& value[3] + 3 == value[0] && value[4] + 4 == value[0])
		
		result = (5 << 20) + (value[0] << 16);
	
	/* three of a kind */
	else if (value[0] == value[2] || value[1] == value[3]
					|| value[2] == value[4])
		
		result = (4 << 20) + (value[2] << 16); /* 3번째 카드는 무조건 쓰리카드에 포함된다! */
	
	/* two pairs 다음과 같은 세가지 가능성만 존재한다. */
	else if (value[0] == value[1] && value[2] == value[3])
		
		result = (3 << 20) + (value[1] << 16) + (value[3] << 12) + (value[4] << 8);
	
	else if (value[0] == value[1] && value[3] == value[4])
		
		result = (3 << 20) + (value[1] << 16) + (value[3] << 12) + (value[2] << 8);
	
	else if (value[1] == value[2] && value[3] == value[4])
		
		result = (3 << 20) + (value[1] << 16) + (value[3] << 12) + (value[0] << 8);
	
	/* pare */
	else if (value[0] == value[1])
		
		result = (2 << 20) + (value[0] << 16) + (value[2] << 12) + (value[3] << 8) + (value[4] << 4);
	
	else if (value[1] == value[2])
		
		result = (2 << 20) + (value[1] << 16) + (value[0] << 12) + (value[3] << 8) + (value[4] << 4);
	
	else if (value[2] == value[3])
		
		result = (2 << 20) + (value[2] << 16) + (value[0] << 12) + (value[3] << 8) + (value[4] << 4);
	
	else if (value[3] == value[4])
		
		result = (2 << 20) + (value[3] << 16) + (value[0] << 12) + (value[3] << 8) + (value[4] << 4);
	
	/* high card */
	else
		result = (1 << 20) + (value[0] << 16) + (value[1] << 12) + (value[2] << 8)
		+ (value[3] << 4) + value[4];
	
	return result;
}

void main(void)
{
	char line[100];
	int hand[2][5];
	long hand_value[2];
	int i, j, t;
	
	while (gets(line) && *line) {
		t = 0;
		
		for (i = 0; i < 2; i++) {
			for (j = 0; j < 5; j++) {
				while (line[t] == ' ')
					t++;
				hand[i][j] = encode_card(line + t);
				t += 2;
			}
			hand_value[i] = get_hand_value(hand[i]);
		}
		
		if (hand_value[0] > hand_value[1])
			puts("Black wins.");
		else if (hand_value[0] < hand_value[1])
			puts("White wins.");
		else
			puts("Tie.");
	}
}














