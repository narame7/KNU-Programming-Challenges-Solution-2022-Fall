import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNextLine()) {
			String line1 = scan.nextLine();
			String line2 = scan.nextLine();
			int[] count1 = new int[26];
			int[] count2 = new int[26];
			
			// 1번째 문장에 포함된 알파벳의 개수 세기
			for (int i = 0; i < line1.length(); i++)
				count1[line1.charAt(i)-97]++;
			
			// 2번째 문장에 포함된 알파벳의 개수 세기
			for (int i = 0; i < line2.length(); i++)
				count2[line2.charAt(i)-97]++;
			
			// 각 문장에 공통적으로 포함된 알파벳을 찾고, 공통적으로 포함된 개수 만큼 출력
			for (int i = 0; i < count1.length; i++)
				if (count1[i] > 0 && count2[i] > 0)
					for (int j = 0; j < Math.min(count1[i], count2[i]); j++)
						System.out.print((char)(i+97));
			System.out.println();
		}
	}
}