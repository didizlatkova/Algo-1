package week4.graphs.validdirectories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class ValidDirectories {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		int n = Integer.parseInt(reader.readLine());

		int graph[][] = new int[n][n];

		String[] input;
		for (int i = 0; i < n; i++) {
			input = reader.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(input[j]);
			}
		}

		ValidDirectories dirs = new ValidDirectories();
		System.out.println(dirs.isValid(graph));
	}

	public boolean isValid(int[][] graph) {
		return bfs(graph);
	}

	private boolean bfs(int[][] graph) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(0);
		boolean visited[] = new boolean[graph.length];

		while (queue.size() > 0) {
			for (int i = 0; i < graph[queue.peek()].length; i++) {
				if (graph[queue.peek()][i] > 0) {
					if (visited[i]) {
						return false;
					}
					queue.add(i);
				}
			}
			visited[queue.poll()] = true;
		}

		return true;
	}
}
