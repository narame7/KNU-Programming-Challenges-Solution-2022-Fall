import java.util.Scanner;
/**
* 카드를 섞은 후의 배열을 예상해 출력하는 프로그램
*/
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int testCase = scan.nextInt();	// 테스트 케이스의 개수
		int n;													// 카드를 섞는 방법의 수
		String skill;										// 카드를 섞는 방법 중 하나의 번호
		
		for (int i = 0; i < testCase; i++) {
			n = scan.nextInt();
			int cards[] = new int[53];
			
			for (int j = 1; j <= 52; j++)
				cards[j] = j;								// 순서가 정렬된 카드뭉치 하나를 만든다
			
			int[][] shuffleData = new int[n][53];
			
			for (int j = 0; j < n; j++) {
				for (int k = 1; k <= 52; k++)
					shuffleData[j][k] = scan.nextInt();	// 카드를 섞는 방법들을 입력받는다
				scan.nextLine();
			}
			
			// 카드를 몇 번째 방법으로 섞을지 입력받고 섞는다.
			skill = scan.nextLine();
			while(!skill.equals("")) {
				cards = shuffle(cards, shuffleData, Integer.parseInt(skill));
				if (!scan.hasNextLine()) break;
				skill = scan.nextLine();
			}
			
			printResult(cards);	// 결과를 출력한다.
		}
		
		scan.close();
	}
	/**
	* 주어진 방법으로 카드를 섞는다
	* param cards 섞을 카드뭉치
	* param shuffleData 카드를 섞는법에 대한 정보
	* param skill 카드를 몇 번째 방법으로 섞는지
	*/
	private static int[] shuffle(int[] cards, int[][] shuffleData, int skill) {
		int[] shuffledCards = new int[53];
		
		for (int i = 1; i <= 52; i++)
			shuffledCards[i] = cards[shuffleData[skill-1][i]]; 
		
		return shuffledCards;
	}
	/**
	* 카드뭉치를 출력한다.
	* param cards 카드뭉치
	*/
	private static void printResult(int[] cards) {
		int shape;	// 카드의 모양(C, D, H, S)
		
		for (int i = 1; i <= 52; i++) {
			shape = (cards[i]-1) / 13;
			switch(shape) {
			case 0:
				System.out.println(values(cards[i]) + " of Clubs"); break;
			case 1:
				System.out.println(values(cards[i]) + " of Diamonds"); break;
			case 2:
				System.out.println(values(cards[i]) + " of Hearts"); break;
			case 3:
				System.out.println(values(cards[i]) + " of Spades"); break;
			}
		}
		
		System.out.println();
	}
	/**
	* 카드에 부여된 번호를 2부터 Ace와 같은 문자로 치환한다.
	* param card 특정 카드
	*/
	private static String values(int card) {
		
		card = card % 13;
		
		switch (card) {
		case 1:
			return "2";
		case 2:
			return "3";
		case 3:
			return "4";
		case 4:
			return "5";
		case 5:
			return "6";
		case 6:
			return "7";
		case 7:
			return "8";
		case 8:
			return "9";
		case 9:
			return "10";
		case 10:
			return "Jack";
		case 11:
			return "Queen";
		case 12:
			return "King";
		default:
			return "Ace";
		}
	}
}
