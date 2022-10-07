import java.util.Scanner;
import java.util.ArrayList;
/**
* 암호 해독용 문장을 하나 아는 경우 다른 암호화된 문장들까지 해독하는 프로그램
*/
public class Main {
	
	private static char[] perfect_key = new char[26];	// 암호 해독용 키 값을 담을 배열
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int testCases = scan.nextInt();
		scan.nextLine();
		scan.nextLine();
		
		String line;	// 암호문을 입력받을 문자열 변수
		int count;	// 암호문의 개수를 셀 배열
		
		for (int i = 0; i < testCases; i++) {
			boolean keyCaptured = false;
			ArrayList<String> crypts = new ArrayList<>();	// 암호문들을 담을 배열
			count = 0;
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				if (line.equals(""))
					break;
				else {
					if (!keyCaptured)
						keyCaptured = findCryptKey(line);	// 빈 줄이 아닌 라인을 입력받으면 키 값을 찾는 메소드를 호출하고 찾는데 성공할 시 더 이상 찾지 않음
					crypts.add(line);
					count++;
				}
			}
			
			if (!keyCaptured)
				System.out.println("No solution.");	// 키 값을 찾지 못하면 No solution. 출력
			else {
				for (int j = 0; j < count; j++)
					System.out.println(decode(perfect_key, crypts.get(j))); // 암호를 해독하고 해독된 문장을 반환하는 메소드 호출 및 출력
				System.out.println();
			}
		}
		
		scan.close();
	}
	/**
	* 키 값이 존재하는지 찾는 메소드
	*	@param line 암호화된 문장
	* @return 키 값이 존재하는지
	*/
	private static boolean findCryptKey(String line) {
		
		String target = "the quick brown fox jumps over the lazy dog";	// 암호화 된 문장 중 이 문장을 찾아야 함
		char[] key = new char[26];	// 각각의 암호화된 알파벳을 저장할 배열
		
		if (line.length() != 43)	// 일단 문장의 길이가 43이 아니면 키 값이 아니다
			return false;
		
		// 문장의 공백 위치 일치 여부를 확인하고 대응되는 알파벳을 저장한다
		for (int i = 0; i < 43; i++) {
			if (line.charAt(i) == ' ' && target.charAt(i) == ' ') {}
			else if (line.charAt(i) != ' ' && target.charAt(i) != ' ') {
				if (key[target.charAt(i) - 97] == 0)
					key[target.charAt(i) - 97] = line.charAt(i);
			}
			else
				return false;
		}
		
		// 키 값에는 a부터 z까지 모든 알파벳이 필요하니 대응되는 알파벳이 부족하면 키 값이 아니다
		for (int i = 0; i < 26; i++)
			if (key[i] == 0)
				return false;
		
		// 마지막으로 대응된 알파벳을 저장한 배열을 이용해 암호화된 문장을 해독하고 target 문장과 완벽히 같은 문장임을 검증한다
		if (!target.equals(decode(key, line)))
			return false;
		
		// 위 과정을 모두 통과하면 대응된 알파벳의 배열을 perfect_key 로 저장한다.
		perfect_key = key;
		
		return true;	// 키 값을 찾았음을 알림
	}
	/**
	* 주어진 알파벳 배열에 따라 주어진 문장을 해독하는 메소드
	* @param key 키 값으로부터 얻은 알파벳 배열
	* @cryptedLine 암호화된 문장
	* @return 해독된 문장
	*/
	private static String decode(char[] key, String cryptedLine) {
		
		StringBuilder decodedLine = new StringBuilder();	// 한글자씩 해독하면서 저장할 StringBuilder
		
		for (int i = 0; i < cryptedLine.length(); i++) {
			for (int j = 0; j < 26; j++) {
				if (cryptedLine.charAt(i) == ' ') {
					decodedLine.append(" ");	// 문장에 공백이 있으면 해독 후 문자에 공백 추가
					break;
				}
				else if (cryptedLine.charAt(i) == key[j])
					decodedLine.append((char)(j+97));	// 해독해서 한글자씩 추가
			}
		}
		
		return decodedLine.toString();	// 결과 반환
	}
}