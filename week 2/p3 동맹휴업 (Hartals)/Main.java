import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		//정수값을 입력받아 얼마나 반복할지 정해줌
		int time=sc.nextInt();
		
		for(int i=0;i<time;i++) {
			
			//days는 몇일간의 표본을 채집할지 정함
			int days=sc.nextInt();
			//hartal은 그 기간동안 얼마나 휴업했는지 카운트 
			int hartal=0;
			
			//표본이 되는 가게의 수를 입력받고 그 가게들의 쉬는 날짜를 저장
			int[] P=new int[sc.nextInt()];
			
			for(int j=0;j<P.length;j++) {
				P[j]=sc.nextInt();

			}
			//hoilday는 오늘이 쉬는날인지 아닌지를 검사하는 함수
			for(int j=1;j<=days;j++) {

				boolean hoilday=false;
				
				//가게의 수 만큼 for문을 돌리는데 하나의 가게라도 휴무를 할경우 break하고 har를 참으로 만들어줌
				for(int k=0;k<P.length;k++) {
					if(j%P[k]==0) {
						hoilday=true;
						break;
					}
				}
				//만약 오늘 한 가게라도 쉬는날인데 금요일(%7==6)또는 토요일(%7==0)이면 카운트를 올리지않고 아니라면 카운트를 올림
				if(hoilday) {
					if(j%7==0||j%7==6) {
					}
					else
						hartal++;
				}
			}
			System.out.println(hartal);

		}


	}

}
