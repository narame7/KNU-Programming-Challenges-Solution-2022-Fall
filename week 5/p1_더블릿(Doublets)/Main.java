import java.util.*;

class Main {
	static List<Integer> list;
	static Boolean[] visited;
	
	public static void getGraph(int[][] graph, String[] dict, int dictLen) {
		for (int i = 0; i < dictLen; i++) {
			int idx = 0;
			for (int j = 0; j < dictLen; j++) {
				if (i != j && dict[i].length() == dict[j].length()) {
					int diff = 0;
					int strlen = dict[i].length();
					for (int k = 0; k < strlen; k++) {
						if (dict[i].charAt(k) != dict[j].charAt(k)) diff += 1;
						if (diff >= 2) break;
					}
					if (diff == 1) {
						graph[i][idx] = j+1;
						idx++;
					}
				}
			}
		}
	}
	
	public static boolean bfs(int start, int end, int[][] graph, String[] dict, int dictLen) {
		int idx = 0;
		visited[start] = true;
		boolean hasPath = false;
		
		while (graph[start][idx] != 0) {
			if (graph[start][idx] == end + 1) {
				list.add(end);
				list.add(start);
				return true;
			}
			else {
				if (!visited[graph[start][idx]-1]) {
					hasPath = bfs(graph[start][idx]-1, end, graph, dict, dictLen);
					if (hasPath) {
						list.add(start);
						return true;
					}
				}
				idx++;
			}
		}

		return false;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		String[] dict = new String[25144];
		int dictLen = 0;
		boolean hasPath = false;
		
		do {
			dict[dictLen] = scan.nextLine();
			dictLen++;
		} while (!dict[dictLen-1].equals(""));
		
		dictLen--;
		
		int[][] graph = new int[dictLen][dictLen];
		getGraph(graph, dict, dictLen);
		
		while (scan.hasNextLine()) {
			String[] pair = scan.nextLine().split(" ");
			list = new ArrayList<Integer>();
			visited = new Boolean[dictLen];
			for (int i = 0; i < dictLen; i++)
				visited[i] = false;
			
			hasPath = bfs(Arrays.asList(dict).indexOf(pair[0]), Arrays.asList(dict).indexOf(pair[1]), graph, dict, dictLen);
			Collections.reverse(list);
			
			if (hasPath)
				for (int i = 0; i < list.size(); i++)
					System.out.println(dict[list.get(i)]);
			else
				System.out.println("No solution.");
			
			System.out.println();
		}
	}
}