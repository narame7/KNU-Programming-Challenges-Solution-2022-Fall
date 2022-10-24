import java.util.Scanner;
/**
 * 연체료가 붙은 일의 순서를 정하는 프로그램
 */
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int testCases = scan.nextInt();		// 테스트 케이스 수
		
		// 테스트 케이스 수 만큼 반복해서 입력받음
		for (int i = 0; i < testCases; i++) {
			int count = 1;
			int jobs = scan.nextInt();
			int[][] list = new int[jobs][3];
			
			// 일 하는데 걸리는 일수 , 하루당 벌금, 일의 번호 순으로 배열에 넣음
			for (int j = 0; j < jobs; j++) {
				list[j][0] = scan.nextInt();
				list[j][1] = scan.nextInt();
				list[j][2] = count++;
			}
			
			sort(list, jobs);	// 정렬
			
			// 정렬된 배열에서 일의 번호만 출력
			for (int j = 0; j < jobs - 1; j++) {
				System.out.print(list[j][2] + " ");
			}
			
			System.out.println(list[jobs - 1][2]);
			System.out.println();
		}
		
		scan.close();
	}
	/**
	 * 일의 우선순위에 따라 정렬하는 메소드
	 * @param list 일 목록
	 * @param jobs 일의 수
	 */
	private static void sort(int[][] list, int jobs) {
		
		// 벌금 / 일하는데 걸리는 시간이 큰 순으로 내림차순 정렬
		for (int i = 0; i < jobs - 1; i++) {
			for (int j = i; j < jobs; j++) {
				if ((double) list[i][1] / (double) list[i][0] < (double) list[j][1] / (double) list[j][0]) {
					swap(list, i, j);
				}
				// 만약 그 비율이 같으면 일하는 데 걸리는 시간이 적은 순으로 정렬
				else if ((double) list[i][1] / (double) list[i][0] == (double) list[j][1] / (double) list[j][0]) {
					if (list[i][0] > list[j][0]) {
						swap(list, i, j);
					}
				}
			}
		}
	}
	/**
	 * 배열의 특정 인덱스 두개의 내용을 서로 바꾸는 메소드
	 * @param list 배열
	 * @param i 내용을 바꿀 인덱스1
	 * @param j 내용을 바꿀 인덱스2
	 */
	private static void swap(int[][] list, int i, int j) {
		int temp = list[i][0];
		list[i][0] = list[j][0];
		list[j][0] = temp;
		
		temp = list[i][1];
		list[i][1] = list[j][1];
		list[j][1] = temp;
		
		temp = list[i][2];
		list[i][2] = list[j][2];
		list[j][2] = temp;
	}
}
