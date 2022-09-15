import java.util.Scanner;

public class Main{
	final static int MAX_N = 100;
	final static int MAX_M = 100;

	public static void main(String[] args) {
			Scanner sc=new Scanner(System.in);
			char[] row=new char[MAX_M+1];
			String line;
			int n, m, i, j, i2, j2, field_id;
			/* mf는 char배열로서, 문자 '0'(의 ASCII 값)으로 초기화한다. 주위에 지뢰가 있을때마다 배열 값을
				1씩 증가시키면 그 값이 '1', '2', '3', ... 가 되므로 문자 그대로 출력하면 된다. */
			char[][] mf=new char[MAX_N + 2][MAX_M + 2];
			
			field_id = 1;
			n=sc.nextInt();
			m=sc.nextInt();
			sc.nextLine();
			
			while (sc.hasNext()) {
				/* mine field 초기화 */

				for (i = 1; i <= n; i++)
					for (j = 1; j <= m; j++)
						mf[i][j] = '0';
				
				/* 입력을 받으면서 동시에 그 줄을 처리함 */
				for (i = 1; i <= n; i++) {
					/*한 줄을 입력받음*/
					line=sc.nextLine();
					
					for(int k=0;k<m;k++)
						row[k]=line.charAt(k);
					/* for문을 순회하며 *를 가지고 있는 곳의 주변 숫자를증가 시킴*/	
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
				if (field_id > 1) 
					System.out.println();
				
				System.out.println("Field #"+field_id++ +":");
				for (i = 1; i <= n; i++) {
					for (j = 1; j <= m; j++)
						System.out.print(mf[i][j]);
					System.out.println();
				}
				n=sc.nextInt();
				m=sc.nextInt();
				sc.nextLine();
			}
		}

}


