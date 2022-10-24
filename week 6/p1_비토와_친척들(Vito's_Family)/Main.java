import java.util.Iterator;
import java.util.Scanner;
import java.util.LinkedList;
/**
* Vito가 다른 친척집과의 거리를 최소화시킬 수 있는 집으로 이사할 때 거리 합의 최소값을 출력하는 프로그램
*/
public class Main {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int testCases = scan.nextInt();
		
		for (int i = 0; i < testCases; i++) {
			LinkedList<Integer> address = new LinkedList<Integer>();
			int houses = scan.nextInt();
			
			// 친척집의 주소를 리스트에 담음.(중복 허용)
			for (int j = 0; j < houses; j++)
				address.add(scan.nextInt());
			
			address.sort(null);	// 오름차순 정렬
			
			Iterator<Integer> iterator = address.iterator();
			
			// 중앙값을 찾음.
			for(int j = 0; j < (address.size()-1) / 2; j++)
				iterator.next();
			int vitosHouse = iterator.next();
			
			// 각 친척집까지의 거리의 합을 구함.
			iterator = address.iterator();
			int sum = 0;
			while (iterator.hasNext())
				sum += Math.abs(iterator.next() - vitosHouse);
				
			System.out.println(sum);
		}
		
		scan.close();
	}
}