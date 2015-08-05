package week8.dp.kpaths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class KPaths {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		int n = Integer.parseInt(reader.readLine());

		boolean[][] graph = new boolean[n][n];
		String[] vertex;
		int first;
		int second;
		for (int i = 0; i < n; i++) {
			vertex = reader.readLine().split(" ");
			first = Integer.parseInt(vertex[0]);
			second = Integer.parseInt(vertex[1]);
			graph[first][second] = true;
		}

		String[] lastLine = reader.readLine().split(" ");
		int start = Integer.parseInt(lastLine[0]);
		int end = Integer.parseInt(lastLine[1]);
		int pathLength = Integer.parseInt(lastLine[2]);
		visited = new boolean[n];

		dfs(graph, end, start);

		int[][] dp = new int[n][pathLength + 1];
		dp[0][0] = 1;

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[i].length; j++) {
				for (int k = 0; k < topological.size(); k++) {
					if (graph[topological.get(k)][topological.get(i)]) {
						dp[i][j] += dp[k][j - 1];
					}
				}

				if (topological.get(i) == end && j == pathLength) {
					System.out.println(dp[i][j]);
					System.exit(0);
				}
			}
		}
	}

	private static boolean[] visited;
	private static List<Integer> topological = new ArrayList<Integer>();

	private static void dfs(boolean[][] graph, int start, int end) {
		for (int i = 0; i < graph[start].length; i++) {
			if (graph[i][start] && !visited[i]) {
				if (start != end) {
					dfs(graph, i, end);
				}
			}
		}
		visited[start] = true;
		topological.add(start);
	}

}
