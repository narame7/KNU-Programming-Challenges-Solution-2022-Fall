import java.util.Scanner;
/**
* 단어찾기 퍼즐 프로그램
*/
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int testCases = scan.nextInt();
		
		for (int i = 0; i < testCases; i++) {
			int row = scan.nextInt();
			int col = scan.nextInt();
			scan.nextLine();
			
			char[][] matrix = new char[row][col];	// 단어 퍼즐을 저장할 2차원 배열
			String line;
			
			for (int r = 0; r < row; r++) {
				line = scan.nextLine();
				for (int c = 0; c < col; c++)
					matrix[r][c] = line.charAt(c);
			}
			
			int words = scan.nextInt();
			scan.nextLine();
			
			for (int w = 0; w < words; w++) 
				find(scan.nextLine(), matrix, row, col);	// 단어를 찾는다
			
			System.out.println();
		}
		
		scan.close();
	}
	/**
	* 찾고자 하는 단어의 첫 글자의 위치를 찾는다.
	* @param word 찾고자 하는 단어
	* @param matrix 퍼즐 배열
	* @param row 퍼즐 행의 크기
	* @param col 퍼즐 열의 크기
	*/
	private static void find(String word, char[][] matrix, int row, int col) {
		
		char firstChar = word.charAt(0);	// 찾고자 하는 단어의 첫 글자
		char changedCase = changingCase(firstChar);	// 첫 글자가 소문자면 대문자로, 대문자면 소문자로 바꿈
		
		boolean checked = false;
		
		for (int r = 0; r < row; r++)
			for (int c = 0; c < col; c++)
				if (matrix[r][c] == firstChar || matrix[r][c] == changedCase)	// 대, 소문자 둘 중 하나가 일치하면
					for (int ns = -1; ns <= 1; ns++)	// 이어서 상하 방향으로 단어가 맞는지 확인할 때 상하방향을 지정하는 용도
						for (int we = -1; we <= 1; we++)	// 이어서 좌우 방향으로 단어가 맞는지 확인할 때 좌우방향을 지정하는 용도
							if ((ns != 0 || we != 0) && !checked)	// 상하좌우가 전부 0이면 무한루프에 빠지므로 이 경우는 제외, 그리고 이미 단어를 찾은 경우도 제외
								checked = check(word, matrix, row, col, r, c, we, ns);	// 단어를 찾는 메소드 호출

	}
	/**
	* 첫 글자의 위치를 중심으로 정해진 단어를 찾음
	* @param word 찾고자 하는 단어
	* @param matrix 퍼즐 배열
	* @param row 퍼즐 행의 크기
	* @param col 퍼즐 열의 크기
	* @param r 첫 글자의 행
	* @param c 첫 글자의 열
	* @param we 탐색할 좌우 방향(-1 = 좌, 0 = 고정, 1 = 우)
	* @param ns 탐색할 상하 방향(-1 = 상, 0 = 고정, 1 = 하)
	* @return 단어 찾기 성공여부
	*/
	private static boolean check(String word, char[][] matrix, int row, int col, int r, int c, int we, int ns) {
		
		int i = 1;
		int r2 = r + we;
		int c2 = c + ns;
		char indexChar;
		char changedCase;
		
		while(r2 < row && r2 >= 0 && c2 < col && c2 >= 0) {
			indexChar = word.charAt(i);
			changedCase = changingCase(indexChar);
			
			if (matrix[r2][c2] == indexChar || matrix[r2][c2] == changedCase) {
				if (word.length()-1 == i) {	// return없이 단어 길이만큼 탐색하는데 성공하면 단어를 찾은걸로 판단
					System.out.println((r+1) + " " + (c+1));	// 좌표 출력
					return true;
				}
			}
			else
				return false;
			// 방향변수가 더해지며 일정한 방향으로 단어를 검사
			r2 += we;
			c2 += ns;
			i++;
		}
		
		return false;
	}
	/**
	* 주어진 글자가 소문자면 대문자로, 대문자면 소문자로 바꿈 (아스키 코드 이용)
	* @param character 글자
	* @return 소/대문자가 전환된 글자
	*/
	private static char changingCase(char character) {
		
		if (character < 91)
			character = (char)(character + 32);
		else
			character = (char)(character - 32);
		
		return character;
	}
}
