import java.util.Scanner;

public class Main{
	final static int MAX_N = 100;
	final static int MAX_M = 100;

	public static void main(String[] args) {
			Scanner sc=new Scanner(System.in);
			char[] row=new char[MAX_M+1];
			String line;
			int n, m, i, j, i2, j2, field_id;
			/* mf�� char�迭�μ�, ���� '0'(�� ASCII ��)���� �ʱ�ȭ�Ѵ�. ������ ���ڰ� ���������� �迭 ����
				1�� ������Ű�� �� ���� '1', '2', '3', ... �� �ǹǷ� ���� �״�� ����ϸ� �ȴ�. */
			char[][] mf=new char[MAX_N + 2][MAX_M + 2];
			
			field_id = 1;
			n=sc.nextInt();
			m=sc.nextInt();
			sc.nextLine();
			
			while (sc.hasNext()) {
				/* mine field �ʱ�ȭ */

				for (i = 1; i <= n; i++)
					for (j = 1; j <= m; j++)
						mf[i][j] = '0';
				
				/* �Է��� �����鼭 ���ÿ� �� ���� ó���� */
				for (i = 1; i <= n; i++) {
					/*�� ���� �Է¹���*/
					line=sc.nextLine();
					
					for(int k=0;k<m;k++)
						row[k]=line.charAt(k);
					/* for���� ��ȸ�ϸ� *�� ������ �ִ� ���� �ֺ� ���ڸ����� ��Ŵ*/	
					for (j = 1; j <= m; j++)
						if (row[j - 1] == '*') {
							mf[i][j] = '*';
							
							for (i2 = i - 1; i2 <= i + 1; i2++)
								for (j2 = j - 1; j2 <= j + 1; j2++)
									if (mf[i2][j2] != '*')
										mf[i2][j2]++;
						}
				}
				/* ��� */
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


