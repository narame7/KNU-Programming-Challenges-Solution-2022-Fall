import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        while (true) {
            int n = scan.nextInt();
            if (n == 0) break; // 0 입력시 종료

            double[] spent = new double[n]; // 각자 낸 비용을 저장할 배열
            double sum = 0;                 // 총 비용 합계

            for (int i = 0; i < n; i++) {
                spent[i] = scan.nextDouble();
                sum += 100.0 * spent[i];  // 100을 곱해서 정수로 만들어줌
            }

            // 총 비용의 평균을 구하고 나머지는 올림(ceil),
            double avg = Math.ceil(sum / n) / 100;
            double result = 0;

            // 평균보다 높은 비용을 소모한 사람들이 얼마나 돈을 더 받아야 하는지 계산
            // avg는 1센트 단위 내에서 남는 돈을 올렸기 때문에 최대값을 가지게 됨
            // 그렇기 때문에 반대로 소모한 돈(sp)에서 평균(avg)을 뺀 값은 최소가 됨
            for (double sp : spent)
                if (sp > avg) result += sp - avg;

            System.out.println("$" + String.format("%.2f", result));
        }
    }
}
