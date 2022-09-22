import java.util.Scanner;

class Main {
	public static void main(String[] args) throws Exception{
		
		Scanner input = new Scanner(System.in);
		String line;
		int n;
		int prev, next;
		int i;
		boolean jollyTorF;
		
		while (input.hasNextLine()) {
			line = input.nextLine();
			n = Integer.parseInt(line.split(" ")[0]);
			boolean[] jollyArray = new boolean[n];
			prev = Integer.parseInt(line.split(" ")[1]);
			
			for (i = 2; i <= n; i++) {
				next = Integer.parseInt(line.split(" ")[i]);
				if (Math.abs(prev - next) < n && Math.abs(prev - next) > 0)
					jollyArray[Math.abs(prev - next)] = true;
				prev = next;
			}
			
			jollyTorF = true;
			for (i = 1; i < n; i++) {
				if (!jollyArray[i]) {
					jollyTorF = false;
					break;
				}
			}
			
			if (jollyTorF)
				System.out.println("Jolly");
			else
				System.out.println("Not jolly");
		}
		
		input.close();
	}
}