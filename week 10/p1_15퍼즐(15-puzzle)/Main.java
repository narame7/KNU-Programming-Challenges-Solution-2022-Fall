import java.util.Scanner;

class Main {
	final static int MAXMOVE=50;
	static int MAXDEPTH;
	static int[][] move = {{-1,0},{0,1},{1,0},{0,-1} };
	static char[] movechar= { 'U','R','D','L' };
	static int solved;
	static int[][] puzzle=new int[4][4];
	static int mtop; 
	static int[] movestack=new int[MAXMOVE];
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {

		int t=sc.nextInt();
		for(int i=0;i<t;i++) {
			input();
			mtop=0;
			solved=0;
			solve();
			output();
		
		}

	}


	public static void input()
	{
		int i, j;
		for (i = 0; i < 4; i++)
			for (j = 0; j < 4; j++)
				puzzle[i][j]=sc.nextInt();
	}

	public static void solve()
	{
		int i, j, k, l, value, x = 0, y=0;

		value = 0;
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				if (puzzle[i][j] == 0)
					value += i;
				if (puzzle[i][j] == 0)
				{
					x = i;
					y = j;
				}
				for (k = i; k < 4; k++) {
					if (k == i)
						l = j + 1;
					else
						l = 0;
					for (; l < 4; l++) {
						if (puzzle[k][l] != 0 && puzzle[i][j] > puzzle[k][l])
							value++;
					}
				}
			}
		}
		if (value % 2 == 0)
			return;

		for (MAXDEPTH = cost(); 1 !=solved && MAXDEPTH <= MAXMOVE; MAXDEPTH += 2)
			back(0, x, y);
	}


	public static int cost() {
		int i, j;
		int md1, md2;

		md1 = 0;
		for (i = 0; i < 4; i++)
		{
			for (j = 0; j < 4; j++) {
				if (puzzle[i][j] != 0) {

					md1 += Math.abs(i - ((puzzle[i][j] - 1) / 4));
				}
			}
		}
		md2 = 0;
		for (i = 0; i < 4; i++)
		{
			for (j = 0; j < 4; j++) {
				if (puzzle[i][j] != 0)
					md2 += Math.abs(j - ((puzzle[i][j] - 1) % 4));
			}
		}

		return md1 + md2;
	}
	public static void back(int a, int nowx, int nowy)
	{
		int i, c;
		int nextx, nexty;	

		c = cost();
		if (c == 0)
		{
			solved = 1;
			return;
		}
		if (a + c > MAXDEPTH) return;

		for (i = 0; i < 4; i++)
		{
			if (mtop > 0 && (movestack[mtop - 1] + 2) % 4 == i)
				continue;
			nextx = nowx + move[i][0];
			nexty = nowy + move[i][1];

			if (nextx < 0 || nextx >= 4 || nexty < 0 || nexty >= 4)
				continue;
			puzzle[nowx][nowy] = puzzle[nextx][nexty];
			puzzle[nextx][nexty] = 0;


			movestack[mtop++] = i;
			back(a + 1, nextx, nexty);
			if (solved==1) return;
			mtop--;

			puzzle[nextx][nexty] = puzzle[nowx][nowy];
			puzzle[nowx][nowy] = 0;
		}
	}

	public static void output()
	{
		int i;
		if (solved==1)
		{
			for (i = 0; i < mtop; i++)
				System.out.print(movechar[movestack[i]]);
		System.out.println();
		}
		else
			System.out.print("This puzzle is not solvable.\n");
			
	}




}