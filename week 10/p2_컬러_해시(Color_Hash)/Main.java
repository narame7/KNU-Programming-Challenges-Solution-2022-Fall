import java.io.*;
import java.util.*;

/* Stack의 toString 메소드 재정의, 공백과 특수기호 등 제거*/
class Moves extends Stack<Integer> {
	@Override
	public String toString() {
		return super.toString().replace("[", "").replace("]", "").replace(", ", "");
	}
}

class Main {
	static Moves moves;	// 백트래킹 움직임을 기록하는 스택
	static int[] finalState = {0, 3, 4, 3, 0, 5, 6, 5, 0, 1, 2, 1, 0, 7, 8, 7, 0, 9, 10, 9, 0, 1, 2, 1};	// 최종 상태
	static int[][] requiredMove = {{0, 1, 1, 0, 1, 2, 3, 2, 3, 3, 3}, 	// 각 판마다 final state에 도달시 필요한 회전수 기록
																 {1, 2, 2, 0, 0, 1, 2, 3, 4, 4, 4}, 	// 탐색시간을 줄이기 위해 배열을 하드코딩해서 사용
																 {0, 2, 3, 1, 1, 0, 1, 4, 5, 3, 5}, 
																 {1, 1, 2, 2, 2, 0, 0, 3, 4, 2, 4}, 
																 {0, 0, 1, 2, 3, 1, 1, 2, 3, 1, 3}, 
																 {1, 0, 0, 1, 2, 2, 2, 1, 2, 2, 2}, 
																 {0, 1, 1, 2, 3, 3, 3, 0, 1, 2, 3}, 
																 {1, 2, 2, 3, 4, 4, 4, 0, 0, 1, 2}, 
																 {0, 2, 3, 4, 5, 3, 5, 1, 1, 0, 1}, 
																 {1, 1, 2, 3, 4, 2, 4, 2, 2, 0, 0}, 
																 {0, 0, 1, 2, 3, 1, 3, 2, 3, 1, 1}};
	static int minLen;	// 백트래킹 도중에 마주치는 정답 중 최소 길이를 구함
	static String minSolution;	// 최소 길이를 가진 정답
	
	/* 해당 상태가 final state인지 판별 */
	private static void check(int[] puzzle) {
		if (Arrays.equals(puzzle, finalState) && moves.size() < minLen) {
				minLen = moves.size();
				minSolution = moves.toString();
		}
	}
	
	/* 현재 상태에서 각 판마다 final state의 판과 몇 회전 차이나는지 구하고 그 중 최대값을 반환
		 만약 현재 14번 회전한 상태에서 특정 판이 final state와 같은 위치에 도달하기 위해 최소 3번의 회전을 필요로 한다면, 회전수가 16번을 초과하게 되므로 그 상태는 더이상 탐색할 필요가 없어진다*/
	private static int getRequiredMove(int[] puzzle) {
		int[] minimumMove = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};	// 각 판마다 final state와 위치가 동일해지기 위해 필요한 최소 회전수
		
		for (int i = 0; i < 21; i ++)
			minimumMove[i] = requiredMove[i / 2][puzzle[i]];
		
		int max = 0;
		
		for (int i : minimumMove)		// 필요 회전수 최대값 구하기
			max = max < i ? i : max;
		
		return max;
	}
	
	/* Rotate Left Wheel ClockWise */
	private static void move1(int[] puzzle) {
		for (int i = 9; i >= 0; i--)
			puzzle[i + 2] = puzzle[i];
		
		puzzle[0] = puzzle[22];
		puzzle[1] = puzzle[23];
		puzzle[21] = puzzle[9];
		puzzle[22] = puzzle[10];
		puzzle[23] = puzzle[11];
	}
	
	/* Rotate Right Wheel ClockWise */
	private static void move2(int[] puzzle) {		
		for (int i = 11; i <= 23; i++)
			puzzle[i - 2] = puzzle[i];
		
		puzzle[22] = puzzle[10];
		puzzle[23] = puzzle[11];
	}
	
	/* Rotate Left Wheel Counter-ClockWise */
	private static void move3(int[] puzzle) {
		puzzle[21] = puzzle[11];
		puzzle[22] = puzzle[0];
		puzzle[23] = puzzle[1];
		
		for (int i = 2; i <= 11; i++)
			puzzle[i - 2] = puzzle[i];

		puzzle[10] = puzzle[22];
		puzzle[11] = puzzle[23];
	}
	
	/* Rotate Right Wheel Counter-ClockWise */
	private static void move4(int[] puzzle) {		
		for (int i = 21; i >= 9; i--)
			puzzle[i + 2] = puzzle[i];
		
		puzzle[9] = puzzle[21];
		puzzle[10] = puzzle[22];
	}
	
	public static void solve(int[] puzzle, int step) {
		step++;	// 백트래킹 깊이

		check(puzzle);	// 정답 도달여부 확인
		
		int requiredMove = getRequiredMove(puzzle);	// 현재 상태에서 각 판마다 final state의 판과 몇 회전 차이나는지 구하고 그 중 최대값을 반환
		
		if (step + requiredMove > minLen) return;  // 만약 더 이상 탐색해도 도달 불가능한 상황이 확정되면 그 방향으론 더이상 탐색하지 않고 back
		
		String path = "";
		
		int	last1 = 0;  // 바로 이전 동작
		int last2 = 0;  // 2번째 이전 동작
		int last3 = 0;  // 3번째 이전 동작
		
		if (moves.size() >= 1) {
			path = moves.toString();
			last1 = path.charAt(moves.size() - 1) - '0';	// 바로 이전 동작의 번호를 구함
		}
		
		if (moves.size() >= 3) {
			last2 = path.charAt(moves.size() - 2) - '0';	// 2번째 이전 동작의 번호를 구함
			last3 = path.charAt(moves.size() - 3) - '0';	// 3번째 이전 동작의 번호를 구함
		}
		
		if (last1 != 3 && !(last1 == 1 && last2 == 1 && last3 == 1)) {	// 바로 이전 동작과 같은 바퀴를 반대로 회전시키는 동작, 연속으로 같은 바퀴를 같은 방향으로 네번 움직이는 동작은 의미가 없으므로 탐색하지 않는다
			int[] copy1 = new int[24];
			System.arraycopy(puzzle, 0, copy1, 0, 24);
			move1(copy1);
			moves.push(1);	// 스택에 움직임 기록
			solve(copy1, step);	// 백트래킹 다음단계 탐색
			moves.pop();	// 스택에서 이미 탐색이 끝난 움직임 제거
		}
		
		if (last1 != 4 && !(last1 == 2 && last2 == 2 && last3 == 2)) {
			int[] copy2 = new int[24];
			System.arraycopy(puzzle, 0, copy2, 0, 24);
			move2(copy2);
			moves.push(2);
			solve(copy2, step);
			moves.pop();
		}
		
		if (last1 != 1 && !(last1 == 3 && last2 == 3 && last3 == 3)) {
			int[] copy3 = new int[24];
			System.arraycopy(puzzle, 0, copy3, 0, 24);
			move3(copy3);
			moves.push(3);
			solve(copy3, step);
			moves.pop();
		}
		
		if (last1 != 2 && !(last1 == 4 && last2 == 4 && last3 == 4)) {
			int[] copy4 = new int[24];
			System.arraycopy(puzzle, 0, copy4, 0, 24);
			move4(copy4);
			moves.push(4);
			solve(copy4, step);
			moves.pop();
		}
	}
	
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();

		for (int i = 0; i < n; i++) {
			int[] puzzle = new int[24];
			for (int j = 0; j < 24; j++)
				puzzle[j] = scan.nextInt();

			if (Arrays.equals(puzzle, finalState))
				System.out.println("PUZZLE ALREADY SOLVED");
			else {
				moves = new Moves();
				minLen = 17;
				minSolution = "NO SOLUTION WAS FOUND IN 16 STEPS";
				solve(puzzle, 0);
				System.out.println(minSolution);
			}
		}
		
		scan.close();
	}
}
