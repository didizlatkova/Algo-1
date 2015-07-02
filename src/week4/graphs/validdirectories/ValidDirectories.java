package week4.graphs.validdirectories;

import week1.queue.Queue;

public class ValidDirectories {

	public boolean isValid(int[][] graph) {
		return bfs(graph);
	}

	private boolean bfs(int[][] graph) {
		Queue queue = new Queue();
		queue.push(0);
		boolean visited[] = new boolean[graph.length];

		while (queue.size() > 0) {
			for (int i = 0; i < graph[queue.peek()].length; i++) {
				if (graph[queue.peek()][i] > 0) {
					if (visited[i]) {
						return false;
					}
					queue.push(i);
				}
			}
			visited[queue.pop()] = true;
		}

		return true;
	}

	private void dfs() {

	}
}
