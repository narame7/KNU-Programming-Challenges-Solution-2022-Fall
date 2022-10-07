import java.util.Scanner;
/**
* 키보드에서 우측 한 칸씩 밀린 오타를 수정하는 프로그램
*/
public class Main {
	
	// 키보드의 자판 순서
	private static char[] qwerty = {'`', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '=', '\\',
									'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', '[', ']',
									'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', ';', '\'',
									'Z', 'X', 'C', 'V', 'B', 'N', 'M', ',', '.', '/'};
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			decode(line);
		}
		
		scan.close();
	}
	/**
	* 각 글자를 키보드의 한 칸씩 좌측에 대응되는 글자로 바꾼다.
	* @param 정해진 문장
	*/
	private static void decode(String line) {
		
		for(int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == ' ')
				System.out.print(" ");
			else
				for(int j = 1; j < 47; j++)
					if (line.charAt(i) == qwerty[j])
						System.out.print(qwerty[j-1]);
		}
		
		System.out.println();
	}
}
