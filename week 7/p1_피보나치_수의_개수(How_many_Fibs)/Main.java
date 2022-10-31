import java.util.Scanner;
import java.math.BigInteger;

class Main {
	final static int MAX_NUM=1001;
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);

		BigInteger[] number = new BigInteger[MAX_NUM];
		number[0] = BigInteger.valueOf(1);
		number[1] = BigInteger.valueOf(1);


		for(int i = 2; i < MAX_NUM; i++) {
			number[i] = number[i-1].add(number[i-2]);
		}

		while(input.hasNext()) {
			BigInteger a = input.nextBigInteger();
			BigInteger b = input.nextBigInteger();
			if (a.compareTo(b)==0&&a.compareTo(BigInteger.ZERO)==0)
				break;
			int count =0;
			for(int i=1;i<MAX_NUM;i++) {
				if(number[i].compareTo(a)>=0){
					if(number[i].compareTo(b)<=0)
						count++;
					else break;

				}
			}
			System.out.println(count);

		}
	}

}
