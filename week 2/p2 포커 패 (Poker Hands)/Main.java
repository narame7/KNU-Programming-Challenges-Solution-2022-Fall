import java.util.Scanner;

/**
 * 포커 승패 판독 프로그램
 */
class Main {
	
	// 두 플레이어의 패가 같을 경우 승패를 판단할 가장 높은 카드
	private static int blackKeyCard = 0;
	private static int whiteKeyCard = 0;
	
	public static void main(String[] args) throws Exception{
		
		Scanner scan = new Scanner(System.in);
		String line;
		String[] blackCards = new String[5];	// 블랙의 카드 패
		String[] whiteCards = new String[5];	// 화이트의 카드 패
		int blackHands, whiteHands;				// 블랙, 화이트의 족보
		
		while (scan.hasNextLine()) {
			line = scan.nextLine();
			for (int i = 0; i < 5; i++) {
				blackCards[i] = line.split(" ")[i];
				whiteCards[i] = line.split(" ")[i + 5];
			}
			
			// 각 플레이어의 패를 내림차순으로 정렬
			blackCards = reRange(blackCards);
			whiteCards = reRange(whiteCards);
			
			blackKeyCard = 0;
			whiteKeyCard = 0;
			// 각 플레이어의 족보 판독
			// 0 = 하이카드, 1 = 원 페어, 2 = 투 페어, 3 = 쓰리 카드, 4 = 스트레이트, 5 = 플러시, 6 = 풀 하우스, 7 = 포 카드, 9 = 스트레이트 플러시(스트레이트4 + 플러시5)
			blackHands = checkHands(blackCards, true);
			whiteHands = checkHands(whiteCards, false);
			
			printresult(blackHands, whiteHands, blackCards, whiteCards);	// 결과 출력
			
		}
		
		scan.close();
	}
	
	/**
	 * 플레이어의 패를 내림차순으로 정렬
	 * @param cards 카드패
	 * @return 정렬된 카드패
	 */
	private static String[] reRange(String[] cards) {
		
		String temp = null;
		
		for (int i = 0; i < 4; i++) {
			for (int j = i+1; j < 5; j++) {
				if (number(cards[i]) < number(cards[j])) {
					temp = cards[i];
					cards[i] = cards[j];
					cards[j] = temp;
				}
			}
		}
		return cards;
	}
	
	/**
	 * 원하는 카드의 값이 얼마나 높은지 판단
	 * @param cardnumber 원하는 카드의 값 (2 ~ A)
	 * @return 각 값에 대응하는 2~14의 자연수
	 */
	private static int number(String cardnumber) {
		
		int value = 0;
		
		switch (cardnumber.charAt(0)) {
			case '2': value = 2; break;
			case '3': value = 3; break;
			case '4': value = 4; break;
			case '5': value = 5; break;
			case '6': value = 6; break;
			case '7': value = 7; break;
			case '8': value = 8; break;
			case '9': value = 9; break;
			case 'T': value = 10; break;
			case 'J': value = 11; break;
			case 'Q': value = 12; break;
			case 'K': value = 13; break;
			case 'A': value = 14; break;
		}
		
		return value;
	}
	
	/**
	 * 특정 플레이어의 족보를 판단 및 가장 높은 값을 가지는 카드의 값을 black/whiteKeyCard에 저장
	 * @param cards 카드패
	 * @param isBlack 패의 주인이 블랙인지?
	 * @return 족보에 대응하는 자연수
	 */
	private static int checkHands(String[] cards, boolean isBlack) {
		
		int sequentialNum = checkSequentialNum(cards, isBlack);
		int sameNum = checkSameNum(cards, isBlack);
		int sameShape = checkSameShape(cards, isBlack);
			
		return sequentialNum + sameNum + sameShape;	// 예) 스트레이트(4)와 플러시(5)를 동시에 만족하면 스트레이트 플러시를 나타내는 9가 반환됨
																								// 예) 모든 값이 전부 0이면 하이카드를 나타내는 0이 반환됨
	}
	
	/**
	 * 카드패가 스트레이트 족보를 가졌는지 판단
	 * @param cards 카드패
	 * @param isBlack 패의 주인이 블랙인지?
	 * @return 스트레이트가 아니면 0, 맞으면 스트레이트 족보에 대응하는 4
	 */
	private static int checkSequentialNum(String[] cards, boolean isBlack) {
		
		for (int i = 0; i < 4; i++)
			if (number(cards[i]) - number(cards[i+1]) != 1)
				return 0;	// 스트레이트 아님
		
		// 키 카드는 스트레이트 중 가장 높은(0번째 배열에 위치한) 카드임
		if (isBlack) blackKeyCard = number(cards[0]);
		else whiteKeyCard = number(cards[0]);
		
		return 4;	// 스트레이트 맞음
	}
	
	/**
	 * 포카드, 풀하우스, 쓰리카드, 투 페어, 원 페어 족보를 가졌는지 판단
	 * @param cards 카드패
	 * @param isBlack 패의 주인이 블랙인지?
	 * @return 각 족보에 대응하는 자연수(0, 1, 2, 3, 6, 7)
	 */
	private static int checkSameNum(String[] cards, boolean isBlack) {
		
		int[] sameNums = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};	// 각 숫자에 해당하는 카드들이 몇개씩 있는지 카운트할 배열
		
		for (int i = 0; i < 5; i++)			// 특정 숫자를 가진 카드가 몇개씩 있는지 카운트함 (예) K가 4개 있으면 13번 배열의 값이 4가 됨
			sameNums[number(cards[i])]++;
		
		if (isBlack) {	// 플레이어가 블랙인 경우
			for (int i = 2; i <= 14; i++) {
				if (sameNums[i] == 4) {
					blackKeyCard = i;	// 키 카드는 포 카드를 이루고있는 카드임
					return 7;	// 포카드임
				}
			}
			
			for (int i = 2; i <= 14; i++) {
				if (sameNums[i] == 3) {
					for (int j = 2; j <= 14; j++) {
						if (sameNums[j] == 2) {
							blackKeyCard = i;	// 키 카드는 풀하우스(쓰리 카드 + 원 페어) 중 쓰리카드를 이루고 있는 카드임
							return 6;	// 풀 하우스임
						}
					}
					blackKeyCard = i;
					return 3;	// 쓰리 카드임
				}
			}
			
			for (int i = 2; i <= 14; i++) {
				if (sameNums[i] == 2) {
					for (int j = 2; j <= 14; j++) {
						if (sameNums[j] == 2 && i != j) {
							if (i > j)
								blackKeyCard = i;	// 키 카드는 투 페어 중 큰 값을 가진 카드임
							else
								blackKeyCard = j;	// 위와 동일
							return 2;	// 투 페어임
						}
					}
					blackKeyCard = i;	// 키 카드는 원 페어를 이루고 있는 카드임
					return 1;	// 원 페어임
				}
			}
		}
		else {	// 플레이어가 화이트인 경우 (위의 블랙의 경우와 구조는 전부 같고 whiteKeyCard에 키카드 값을 저장하는것만 다름)
			for (int i = 2; i <= 14; i++) {
				if (sameNums[i] == 4) {
					whiteKeyCard = i;
					return 7;
				}
			}
			
			for (int i = 2; i <= 14; i++) {
				if (sameNums[i] == 3) {
					for (int j = 2; j <= 14; j++) {
						if (sameNums[j] == 2) {
							whiteKeyCard = i;
							return 6;
						}
					}
					whiteKeyCard = i;
					return 3;
				}
			}
			
			for (int i = 2; i <= 14; i++) {
				if (sameNums[i] == 2) {
					for (int j = 2; j <= 14; j++) {
						if (sameNums[j] == 2 && i != j) {
							if (i > j)
								whiteKeyCard = i;
							else
								whiteKeyCard = j;
							return 2;
						}
					}
					whiteKeyCard = i;
					return 1;
				}
			}
		}
		
		return 0;
	}
	
	/**
	 * 플러시 여부 판단
	 * @param cards 카드패
	 * @param isBlack 패의 주인이 블랙인지?
	 * @return 플러시가 아니면 0, 플러시가 맞으면 5
	 */
	private static int checkSameShape(String[] cards, boolean isBlack) {
		
		for (int i = 0; i < 4; i++)
			if (cards[i].charAt(1) != cards[i+1].charAt(1)) return 0;	// 플러시가 아님
		
		// 키 카드는 플러시 중 가장 높은(0번째 배열에 위치한) 카드임
		if (isBlack) blackKeyCard = number(cards[0]);
		else whiteKeyCard = number(cards[0]);
		
		return 5;	// 플러시임
	}
	
	/**
	 * 결과를 출력함
	 * @param blackHands 블랙의 족보
	 * @param whiteHands 화이트의 족보
	 * @param blackCards 블랙의 손패
	 * @param whiteCards 화이트의 손패
	 */
	private static void printresult(int blackHands, int whiteHands, String[] blackCards, String[] whiteCards) {
		
		boolean allsame = true;		// 모든 손패가 전부 같은지를 판단할 변수
		
		if (blackHands > whiteHands)											// 블랙의 족보가 화이트의 족보보다 높은경우
			System.out.println("Black wins.");
		else if (blackHands == whiteHands) {									// 블랙의 족보와 화이트의 족보가 같은 경우
			if (blackKeyCard > whiteKeyCard)										// 블랙의 키 카드가 화이트의 키 카드보다 높은 경우
				System.out.println("Black wins.");
			else if (blackKeyCard == whiteKeyCard) {								// 블랙과 화이트의 키 카드까지 같은 경우에는 다른 나머지 카드들의 값을 비교해 승패를 내린다.
				for (int i = 0; i < 5; i++) {
					if (number(blackCards[i]) > number(whiteCards[i])) {
						System.out.println("Black wins.");								// 다른 나머지 카드들 중 블랙이 더 높은 카드를 가짐
						allsame = false;
						break;
					}
					else if (number(blackCards[i]) < number(whiteCards[i])) {
						System.out.println("White wins.");								// 다른 나머지 카드들 중 화이트가 더 높은 카드를 가짐
						allsame = false;
						break;
					}
				}
				if (allsame) System.out.println("Tie.");								// 모든 손패가 전부 같음
			}
			else
				System.out.println("White wins.");									// 블랙의 키 카드가 화이트의 키 카드보다 낮은 경우
		}
		else
			System.out.println("White wins.");									// 블랙의 족보가 화이트의 족보보다 낮은 경우
	}
}
