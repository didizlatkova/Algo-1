package week4.graphs.closestcofeeshop;

import week1.queue.Queue;

public class ClosestCoffeeShop {

	// Finds the closest coffee store to a point.
	public int closestCoffeeStore(boolean[][] graph, boolean[] isCoffeeStore,
			int startingPoint) {
		return bfs(graph, isCoffeeStore, startingPoint);
	}

	private int bfs(boolean[][] graph, boolean[] isCoffeeStore,
			int startingPoint) {
		Queue queue = new Queue();
		queue.push(startingPoint);
		boolean visited[] = new boolean[graph.length];
		int pathLengths[] = new int[graph.length];

		while (queue.size() > 0) {
			if (isCoffeeStore[queue.peek()]) {
				return pathLengths[queue.peek()];
			}
			for (int i = 0; i < graph[queue.peek()].length; i++) {
				if (graph[queue.peek()][i] && !visited[i]) {
					queue.push(i);
					pathLengths[i] = pathLengths[queue.peek()] + 1;
				}
			}
			visited[queue.pop()] = true;
		}

		return -1;
	}

}
