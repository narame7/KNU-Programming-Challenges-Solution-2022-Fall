import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/**
* 셀룰러 오토마타에서 특정 상태에 도달 가능한지 판별하는 프로그램
*/
public class Main {
	private static int[] rull = new int[8];	// 다음 상태 변화 방식 (8자리의 이진수)
	private static Set<String> checkedStat;	// 이미 검사가 완료된 상태를 저장할 Set, 만약 검사 없이 모든 중복된 경우까지 탐색하면 탐색시간이 상당히 길기에 시간절약을 위함.
	private static boolean findDest;	// 도달하고자 하는 상태에 도달 했는지?
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while(scan.hasNext()) {
			int id = scan.nextInt();	// 규칙 번호
			int n = scan.nextInt();		// 코드 길이
			String dest = scan.next();	// 목적 코드
			
			generateRull(id);	// 입력받은 id값으로 rull[]에 담길 8자리 이진수 생성(변화 규칙 생성)
			String[] currentCode = new String[n];	// 변화가 이뤄질 코드가 한글자씩 담길 배열
			checkedStat = new HashSet<>();
			findDest = false;
			
			backTrack(currentCode, 0, n, dest);	// 백트래킹으로 입력으로 주어진 dest에 도달할 수 있는지 찾음
			
			// 도달 가능하면 REACHABLE, 아니면 GARDEN OF EDEN 출력
			if (findDest)
				System.out.println("REACHABLE");
			else
				System.out.println("GARDEN OF EDEN");
			
		}
		
		scan.close();
	}
	/**
	* 입력받은 id값으로 rull[]에 담길 8자리 이진수 생성(변화 규칙 생성)
	* param id 규칙 번호
	*/
	private static void generateRull(int id) {
		for (int i = 0; i < 8; i++) {
			rull[i] = id % 2;
			id = id / 2;
		}
	}
	/**
	* 백트래킹 메소드
	* param currentCode 현재 검사할 코드가 한글자씩 담긴 배열
	* param k 백트래킹 현재 깊이
	* param n 주어진 코드의 길이
	* param dest 목적 코드
	*/
	private static void backTrack(String[] currentCode, int k, int n, String dest) {
		// 이미 목적 코드가 도달 가능한 상태인지 찾은 경우에는 더 탐색할 필요가 없다.
		if (!findDest) {
			if (k == n) {
				if (process_solution(currentCode, k, dest))
					findDest = true;
			}
			else {
				k = k + 1;
				currentCode[k-1] = "0";	// 먼저 0 탐색
				backTrack(currentCode, k, n, dest);
				currentCode[k-1] = "1";	// 그 다음 1 탐색
				backTrack(currentCode, k, n, dest);
			}
		}
	}
	/**
	* 특정 n자리 코드가 완성됐을 때, 이후 목적 코드에 도달 가능한지 검사
	* param currentCode 현재 검사할 코드가 한글자씩 담긴 배열
	* param k 백트래킹 현재 깊이
	* param dest 목적 코드
	* return 목적 코드를 찾았으면 true, 아니면 false
	*/
	private static boolean process_solution(String[] currentCode, int k, String dest) {
		String circularCode = generateCircularCode(currentCode, k);	// 현재 코드 앞 뒤에 각각의 맨 뒷글자와 맨 앞글자를 더해서 원형으로 인식하기 쉽게 바꾼다.
		String[] nextCode;	// 다음 상태가 들어갈 코드 배열
		
		boolean notInSetYet = !checkedStat.contains(circularCode.substring(1, k+1));	// 만약 현재 코드가 아직 검사하지 않은 코드라면 Set에 포함되있지 않으므로 notInSetYet = true
		
		// 이미 검사한 코드가 발견될 때 까지 계속해서 반복한다
		while(notInSetYet) {
			StringBuilder nextCodeSb = new StringBuilder();
			
			// 다음 상태 생성
			for (int i = 0; i < k; i++)
				nextCodeSb.append(rull[binaryToDecimal(circularCode.substring(i, i+3))]);
			
			notInSetYet = checkedStat.add(nextCodeSb.toString());	// 다음 상태를 Set에 넣음, 이미 Set에 있다면 notInSetYet은 false가 됨
			
			if (nextCodeSb.toString().equals(dest))
				return true;	// 찾는 상태가 맞다면 true 반환
			
			nextCode = nextCodeSb.toString().split("");	// 다음 코드도 한 글자씩 나눠서 배열 형태로 만들어줌
			circularCode = generateCircularCode(nextCode, k);	// 다음 코드도 원형으로 인식하기 쉽게 바꿈
		}
		
		return false;
	}
	/**
	* 세 자리 2진수를 십진수로 바꾸는 메소드
	*/
	private static int binaryToDecimal(String binary) {
		int sum = 0;
		
		if (binary.charAt(0) == '1')
			sum += 4;
		if (binary.charAt(1) == '1')
			sum += 2;
		if (binary.charAt(2) == '1')
			sum += 1;
		
		return sum;
	}
	/**
	* 현재 코드 앞 뒤에 각각의 맨 뒷글자와 맨 앞글자를 더해서 원형으로 인식하기 쉽게 바꾼다.
	*/
	private static String generateCircularCode(String[] currentCode, int k) {
		StringBuilder circularCodeSb = new StringBuilder(currentCode[k-1].equals("0") ? "0" : "1");
		
		for (int i = 0; i < k; i++)
			circularCodeSb.append(currentCode[i].equals("0") ? "0" : "1");
		
		circularCodeSb.append(currentCode[0].equals("0") ? "0" : "1");
		
		return circularCodeSb.toString();
	}
}
