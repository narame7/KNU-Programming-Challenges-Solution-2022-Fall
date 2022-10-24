import java.util.LinkedList;
import java.util.Scanner;
/**
* 여러사람이 특정한 조건에서 다리를 건너는 최소 시간을 구함
*/
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int testCases = scan.nextInt();
		
		for (int i = 0; i < testCases; i++) {
			int n = scan.nextInt();
			LinkedList<Integer> people = new LinkedList<Integer>();
			
			// 리스트에 각 사람들의 다리 건너는데 걸리는 시간 입력
			for (int j = 0; j < n; j++)
				people.add(scan.nextInt());
			
			people.sort(null);	// 오름차순 정렬
			
			cross(people);	// 다리를 건너는 시간의 최소값을 구하고 출력
		}
		
		scan.close();
	}
	/**
	* 모든 사람들이 다리를 건너는 시간의 최소값을 구하고 출력
	* @param people 각 사람들의 다리 건너는 시간 리스트
	*/
	private static void cross(LinkedList<Integer> people) {
		
		int sum = 0;
		
		// 남은 사람이 3명 이하일 때까지 첫 번째와 두 번째로 느린 사람을 다리 건너편으로 보내며 리스트에서 삭제하기를 반복
		while (people.size() > 3) {
			int a = people.get(0);	// 첫 번째로 빠른 사람
			int b = people.get(1);	// 두 번째로 빠른 사람
			int c = people.get(people.size()-2);	// 두 번째로 느린 사람
			int d = people.get(people.size()-1);	//	첫 번째로 느린 사람
			
			// 다리 건너로 c와 d를 보내는 두 가지 방법의 경우 중 더 시간이 적게 걸리는 경우를 채택해 시간을 합함
			sum += 2*a + c + d <  a + 2*b + d ? 2*a + c + d : a + 2*b + d;
			people.remove(people.size()-1);	// 이미 건넌 사람은 리스트에서 제거(d)
			people.remove(people.size()-1);	// 이미 건넌 사람은 리스트에서 제거(c)
		}
		
		// people이 3, 2, 1명씩 남은 경우 적절히 시간을 더해 처리함
		switch(people.size()) {
		case 3:
			sum += people.get(0) + people.get(1) + people.get(2);
			break;
		case 2:
			sum += people.get(1);
			break;
		case 1:
			sum += people.get(0);
		}
		
		System.out.println(sum);	// 결과 출력
		System.out.println();
	}
}
