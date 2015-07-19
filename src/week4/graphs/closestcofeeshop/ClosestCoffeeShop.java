package week4.graphs.closestcofeeshop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class ClosestCoffeeShop {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		int n = Integer.parseInt(reader.readLine());

		boolean graph[][] = new boolean[n][n];
		String[] input;
		for (int i = 0; i < n; i++) {
			input = reader.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				if (Integer.parseInt(input[j]) == 1) {
					graph[i][j] = true;
				}
			}
		}

		int start = Integer.parseInt(reader.readLine());
		boolean[] coffeeStores = new boolean[n];
		input = reader.readLine().split(" ");
		for (int j = 0; j < n; j++) {
			if (Integer.parseInt(input[j]) == 1) {
				coffeeStores[j] = true;
			}
		}

		ClosestCoffeeShop shop = new ClosestCoffeeShop();
		System.out.println(shop.closestCoffeeStore(graph, coffeeStores, start));
	}

	// Finds the closest coffee store to a point.
	public int closestCoffeeStore(boolean[][] graph, boolean[] isCoffeeStore,
			int startingPoint) {
		return bfs(graph, isCoffeeStore, startingPoint);
	}

	private int bfs(boolean[][] graph, boolean[] isCoffeeStore,
			int startingPoint) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(startingPoint);
		boolean visited[] = new boolean[graph.length];
		int pathLengths[] = new int[graph.length];
		int current;

		while (queue.size() > 0) {
			current = queue.poll();
			visited[current] = true;
			if (isCoffeeStore[current]) {
				return pathLengths[current];
			}
			for (int i = 0; i < graph[current].length; i++) {
				if (graph[current][i] && !visited[i]) {
					queue.add(i);
					pathLengths[i] = pathLengths[current] + 1;
				}
			}
		}

		return -1;
	}

}
