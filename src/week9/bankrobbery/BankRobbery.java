package week9.bankrobbery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class BankRobbery {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		String[] nm = reader.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);

		boolean[][] graph = new boolean[n + 1][n + 1];
		String[] junction;
		int first;
		int second;

		for (int i = 0; i < m; i++) {
			junction = reader.readLine().split(" ");
			first = Integer.parseInt(junction[0]);
			second = Integer.parseInt(junction[1]);
			graph[first][second] = true;
			graph[second][first] = true;
		}

		String[] bph = reader.readLine().split(" ");
		int bank = Integer.parseInt(bph[0]);
		int police = Integer.parseInt(bph[1]);
		int helicopter = Integer.parseInt(bph[2]);

		int[] policeTimes = calcPoliceTime(police, graph);
		System.out.println(binarySearch(0, maxPoliceTime, graph, helicopter,
				bank, policeTimes));
	}

	private static int maxPoliceTime;

	private static int binarySearch(int left, int right, boolean[][] graph,
			int destination, int startingPoint, int[] policeTimes) {
		int lastOk = -1;
		while (left <= right) {
			int middle = left + (right - left) / 2;

			if (bfs(graph, destination, startingPoint, policeTimes, middle)) {
				lastOk = middle;
				left = middle + 1;
			} else {
				right = middle - 1;
			}
		}

		return lastOk;
	}

	private static boolean bfs(boolean[][] graph, int destination,
			int startingPoint, int[] policeTimes, int timeWaiting) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(startingPoint);
		boolean visited[] = new boolean[graph.length];
		int pathLengths[] = new int[graph.length];
		int current;
		visited[startingPoint] = true;

		while (queue.size() > 0) {
			current = queue.poll();
			if (current == destination) {
				return true;
			}
			for (int i = 0; i < graph[current].length; i++) {
				if (graph[current][i] && !visited[i]
						&& pathLengths[current] + 1 + timeWaiting < policeTimes[i]) {
					queue.add(i);
					visited[i] = true;
					pathLengths[i] = pathLengths[current] + 1;
				}
			}
		}

		return false;
	}

	private static int[] calcPoliceTime(int startingPoint, boolean[][] graph) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(startingPoint);
		boolean visited[] = new boolean[graph.length];
		int policeTimes[] = new int[graph.length];
		int current = 0;
		visited[startingPoint] = true;

		while (queue.size() > 0) {
			current = queue.poll();
			for (int i = 1; i < graph[current].length; i++) {
				if (graph[current][i] && !visited[i]) {
					queue.add(i);
					policeTimes[i] = policeTimes[current] + 1;
					visited[i] = true;
				}
			}
		}

		maxPoliceTime = policeTimes[current];
		return policeTimes;
	}

}
