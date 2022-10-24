import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * 차량별 고속도로 이용요금을 계산하는 프로그램
 */
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int testCases = scan.nextInt();		// 테스트 케이스 수

		// 테스트 케이스 수 만큼 반복
		for (int i = 0; i < testCases; i++) {
			int[] billsT = new int[24];		// 시간별 이용요금을 저장할 배열

			// 시간별 이용요금 배열에 값 저장
			for (int j = 0; j < 24; j++)
				billsT[j] = scan.nextInt();
				
			scan.nextLine();

			String line;
			LinkedList<String> list = new LinkedList<>();	// 입구마다 설치된 카메라에서 찍힌 정보를 저장할 리스트

			// 빈줄이거나 입력이 없을 때 까지 정보를 입력받음
			while (scan.hasNextLine()) {
				do {
					line = scan.nextLine();
					if (!line.equals(""))
						list.add(line);
				} while (!line.equals("") && scan.hasNextLine());
				break;
			}
			
			list.sort(null);	// 번호판 순, 시간 순서대로 리스트 정렬
			
			autoToll(billsT, list);	// 요금 계산 및 출력
		}

		scan.close();
	}
	/**
	 * 차량별로 요금을 계산하고 출력하는 프로그램
	 * @param billsT 시간별 요금을 저장한 배열
	 * @param list 차량별 도로 이용 정보가 담긴 리스트
	 */
	private static void autoToll(int[] billsT, LinkedList<String> list) {
		Iterator<String> iterator = list.iterator();	// 리스트를 순서대로 참조할 수 있도록 Iterator 객체생성
		
		boolean isStartEnter = true;	// 리스트의 다음 사진에 enter가 있어야 하는지 exit가 있어야 하는지에 대한 정보를 담을 변수
		boolean hasPair = false;		// 특정 차량이 최소 한쌍의 enter/exit 짝을 가졌는지에 대한 정보를 담을 변수
		
		String[] tempInfo = new String[4];		// 읽어온 줄이 enter에 대한 정보인지 exit에 대한 정보인지 판별 전에 임시로 정보를 저장할 배열
		String[] enterInfo = new String[4];		// enter에 대한 정보를 저장할 배열
		String[] exitInfo = new String[4];		// exit에 대한 정보를 저장할 배열
		String currentLicense = "";				// 현재 처리중인 차 번호판
		
		double totalbill = 2.00;		// 총 이용 요금(기본 2달러)
		
		while (iterator.hasNext()) {
			tempInfo = iterator.next().toString().split(" ");	// 한 줄을 읽고 '차 번호판 / 시간 / enter or exit / 위치'정보로 나눠서 4칸 임시 배열에 저장
			
			// 차 번호판 구분
			// 현재 처리중인 차 번호판이 없으면 임시 배열에서 번호판 정보를 읽어 저장
			if (currentLicense.equals(""))
				currentLicense = tempInfo[0];
			// 현재 처리중인 번호판에서 다른 번호판으로 대상이 바뀔 때
			else if (!currentLicense.equals(tempInfo[0])) {
				// 요금 계산중이던 차의 이용 요금 출력 및 요금 초기화
				// hasPair를 통해 최소한 짝이 한 개가 있는지 검사한다.
				if (hasPair) {
					System.out.print(currentLicense + " $");
					System.out.format("%.2f\n", totalbill);
					totalbill = 2.00;
					hasPair = false;
				}
				
				// 현재 처리중인 번호판 갱신 및 짝 검사용 변수 초기화
				currentLicense = tempInfo[0];
				isStartEnter = true;
			}
			
			// enter, exit 짝 구분
			if (isStartEnter && tempInfo[2].equals("enter")) {
				System.arraycopy(tempInfo, 0, enterInfo, 0, 4);
				isStartEnter = false;
				continue;
			}
			
			// 최소한 짝이 한 쌍이라도 있는 경우에만 이 else if 구문을 통과할 수 있다.
			else if (!isStartEnter && tempInfo[2].equals("exit")) {
				System.arraycopy(tempInfo, 0, exitInfo, 0, 4);
				isStartEnter = true;
				hasPair = true;
			}
			// 짝이 잘못됐을 경우엔 검사용 변수 초기화
			else {
				isStartEnter = true;
				continue;
			}
			
			// 키로수 계산해서 더함
			int startTime = Integer.parseInt(enterInfo[1].split(":")[2]);
			double km = Math.abs(Integer.parseInt(enterInfo[3]) - Integer.parseInt(exitInfo[3]));
			totalbill += (km * (double) (billsT[startTime] / 100.00) + 1.00);
		}
		
		// while문을 빠져나오면 아직 출력하지 못한 리스트 마지막 차의 요금 출력
		// hasPair를 통해 최소한 짝이 한 개가 있는지 검사한다.
		if (hasPair) {
			System.out.print(currentLicense + " $");
			System.out.format("%.2f\n", totalbill);
		}
		System.out.println();
	}
}