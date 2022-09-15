import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int a, b;
        String line;

        while (input.hasNextLine()) {
            line = input.nextLine();
            a = Integer.parseInt(line.split(" ")[0]);
            b = Integer.parseInt(line.split(" ")[1]);
            int max_cycle = 0;
            long i_temp;
            boolean inverted = false;

            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
                inverted = true;
            }

            for (int i = a; i <= b; i++) {
                i_temp = i;
                int cycle = 1;
                while (i_temp != 1) {
                    if (i_temp % 2 == 0) {
                        i_temp = i_temp/2;
                    }
                    else {
                        i_temp = i_temp*3 + 1;
                    }
                    cycle += 1;
                }
                if (cycle > max_cycle)
                    max_cycle = cycle;
            }

            if (inverted)
                System.out.println(b + " " + a + " " + max_cycle);
            else
                System.out.println(a + " " + b + " " + max_cycle);
        }

        input.close();
    }
}