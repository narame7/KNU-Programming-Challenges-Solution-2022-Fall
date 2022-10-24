import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * 입력으로 주어진 문장들을 한 줄에 72글자가 넘지 않도록 정해진 포맷으로 바꿔주는 프로그램
 */
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> array = new ArrayList<>();
		String line;
		
		// 입력을 ArrayList에 받아들이다가 강제 줄바꿈이 필요한 부분에서 재정렬/출력을 담당하는 메소드를 호출한다.
		while((line = bf.readLine()) != null) {
			// 강제줄바꿈 조건 1. 엔터나 스페이스바로만 이루어진 줄을 만나면 fmtPrint 호출
			if (line.replaceAll(" ", "").equals("")) {
				fmtPrint(array);	// 강제 줄바꿈이 필요한 문장은 아직 ArrayList에 포함시키지 않았다.
				array.clear();	// ArrayList 초기화
			}
			// 강제줄바꿈 조건 2. 문장의 첫 부분이 공백으로 시작하는 줄을 만나면 fmtPrint 호출
			else if (line.charAt(0) == ' ') {
				fmtPrint(array);	// 강제 줄바꿈이 필요한 문장은 아직 ArrayList에 포함시키지 않았다.
				array.clear();	// ArrayList 초기화
			}
			array.add(line);	// 만약 line이 강제 줄바꿈이 필요한 문장이라면 이미 fmtPrint에서 처리 및 ArrayList의 초기화가 이루어진 경우이기 때문에 0번에 배치된다.
		}
		
		fmtPrint(array);	// 마지막으로 남은 문장 정렬 및 출력
		
	}
	/**
	 * 재정렬/출력을 담당하는 메소드
	 * @param array 입력 문장을 담은 ArrayList, 이 메소드가 마지막으로 호출될 때를 제외하곤 0번 인덱스에는 무조건 강제 줄바꿈이 필요한 문장이 들어가있다.
	 */
	private static void fmtPrint(ArrayList<String> array) {
		
		int index = 0;				// String에서 한 글자 한 글자의 인덱스를 나타낼 변수
		int currentLineSize = 0;	// 현재 출력중인 라인의 글자 수
		String line;				// 재정렬과 출력에 사용될 문장
		
		for (int i = 0; i < array.size(); i++) {
			boolean keep = false;		// 중간에 while 문을 두번 연속 탈출하기 위한 변수
			line = array.get(i);		// ArrayList에 담긴 문장을 읽어옴
			int firstWordIndex = 0;		// 문장 첫 번째 단어의 길이를 나타낼 변수
			// 첫 번째 단어의 길이를 알아냄
			while (firstWordIndex < line.length() && line.charAt(firstWordIndex) != ' ')
				firstWordIndex++;
			// 현재 줄이 더 이상 다음 문장을 이어쓸 수 없을 만큼 길면 줄을 바꾸고 현재 문장 길이를 0으로
			if (currentLineSize != 0 && currentLineSize + firstWordIndex + 1 > 72) {
				System.out.println();
				currentLineSize = 0;
			}
			// 현재 줄이 비어있지 않고 더 쓸 공간이 있을 때면 공백 하나를 붙이고 현재 문장 길이에 1을 더함
			else if (currentLineSize > 0 ) {
				System.out.print(" ");
				currentLineSize++;
			}
			// 현재 줄의 길이와 지금 출력해야하는 줄의 글자수의 합이 72보다 크면 while문 안에서 각 줄마다 72글자 이하가 될 때까지 적절히 쪼개진 후에 출력된다.
			while (line.length() + currentLineSize > 72) {
				index = 71 - currentLineSize;		// 71에서 현재 문장의 길이를 뺀 index값에 해당하는 문자의 위치부터 자를 곳을 찾아낸다.
				// index+1에 해당하는 글자가 공백이 아니라면
				if (line.charAt(index+1) != ' ') {
					// 공백이 나올때까지 index를 줄여나가야한다.
					while (index > -1 && line.charAt(index) != ' ') {
						index--;
					}
					// 만약 -1까지 줄였는데도 공백이 없다면 현재 줄에 추가하면 72자를 넘어버리는 크기의 한 단어가 존재한다는 뜻이다.
					if (index == -1) {
						index++;
						// 그 단어의 길이를 알아내기 위해 다시 index를 더해간다.
						while (index < line.length() && line.charAt(index) != ' ') {
							index++;
						}
						index = index-1;
					}
					// 공백의 위치는 찾았지만 공백이 여러 칸일수도 있으므로 공백이 아닌 다른 글자의 끝 부분을 찾는다.
					while (index > -1 && line.charAt(index) == ' ') {
						index--;
					}
				}
				// 이 경우는 index+1에 해당하는 글자가 공백일 경우 공백이 아닌 다른 글자의 끝 부분을 찾아내는 역할이다.
				else {
					while (index > -1 && line.charAt(index) == ' ') {
						index--;
					}
				}
				// 정해진 index에 따라 문장은 72글자를 넘지 않고 최대한 가깝게 출력된다.
				// 여기서도 문장 끝에 공백이 남아있는 케이스가 존재하므로 뒤의 공백을 제거하며 출력한다.
				System.out.println(line.substring(0, ++index).replaceAll("$+\\s", ""));	
				currentLineSize = 0;	// 줄 바꿈이 있었으므로 현재 문장 길이를 0으로 초기화
				// 남은 문장에서 앞의 공백을 전부 없애려고 할 때 어던 index에서 끊어야 하는지 알아낸다.
				while (index < line.length() && line.charAt(index) == ' ') {
					index ++;
				}
				// 남은 문장에서 앞의 공백을 전부 없앤다.
				// 또는 이미 출력된 72자 이상의 단어를 문장에서 잘라낸다.
				line = line.substring(index, line.length());
				// 만약 line이 공백이나 다른 단어 없이 72자 이상의 한 단어로만 이루어진 문장이면 위에서 line은 ""이 된다.
				// 이 상태에서 while문에서 나가면 필요없는 줄바꿈이 한번 더 생기기 때문에 위에서 선언한 keep변수를 이용해 루프를 두번 연속으로 탈출한다.
				if (line.equals("")) {
					keep = true;
					continue;	// while문 탈출 1
				}
			}
			if (keep) continue;		// while문 탈출 2
			// 줄 바꿈 직전의 문장을 출력하게 되면 문장에서 뒤의 공백들을 전부 제거한 후 출력한다.
			if (i == array.size()-1) {
				System.out.println(line.replaceAll("$+\\s", ""));
				currentLineSize = 0;
			}
			else {
				// 엔터나 스페이스바로만 이루어진 줄은 줄바꿈을 한다.
				if (line.replaceAll(" ", "").equals("")) {
					System.out.println();
					currentLineSize = 0;
				}
				// 그 외의 경우엔 전부 뒤에 문장이 이어지는 경우이므로 print문으로 출력한다.
				else {
					System.out.print(line);
					currentLineSize += line.length();	// 출력한 문장의 길이만큼 현재 문장의 길이가 늘어난다.
				}
			}
		}
	}
}
