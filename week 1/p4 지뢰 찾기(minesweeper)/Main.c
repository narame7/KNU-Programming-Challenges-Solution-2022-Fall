#include <stdio.h>

#define MAX_N 100
#define MAX_M 100

void main(void) {
	char row[MAX_M + 1];
	int n, m, i, j, i2, j2, field_id;
	/* mf는 char배열로서, 문자 '0'(의 ASCII 값)으로 초기화한다. 주위에 지뢰가 있을때마다 배열 값을
		1씩 증가시키면 그 값이 '1', '2', '3', ... 가 되므로 문자 그대로 출력하면 된다. */
	char mf[MAX_N + 2][MAX_M + 2];
	
	field_id = 1;
	scanf("%d %d", &n, &m);
	while (n || m) {
		/* mine field 초기화 */
		for (i = 1; i <= n; i++)
			for (j = 1; j <= m; j++)
				mf[i][j] = '0';
		
		/* 입력을 받으면서 동시에 그 줄을 처리함 */
		for (i = 1; i <= n; i++) {
			scanf("%s", row);
			for (j = 1; j <= m; j++)
				if (row[j - 1] == '*') {
					mf[i][j] = '*';
					for (i2 = i - 1; i2 <= i + 1; i2++)
						for (j2 = j - 1; j2 <= j + 1; j2++)
							if (mf[i2][j2] != '*')
								mf[i2][j2]++;
				}
		}
		/* 출력 */
		if (field_id > 1) putchar('\n');
		printf("Field #%d:\n", field_id++);
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= m; j++)
				putchar(mf[i][j]);
			putchar('\n');
		}
		scanf("%d %d", &n, &m);
	}
}
