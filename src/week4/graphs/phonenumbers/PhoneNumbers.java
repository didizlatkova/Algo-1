package week4.graphs.phonenumbers;

import java.util.Stack;

public class PhoneNumbers {

	public int getMinCalls(boolean graph[][]) {
		return dfs(graph);
	}

	private int dfs(boolean graph[][]) {
		boolean[] visited = new boolean[graph.length];

		Stack<Integer> stack = new Stack<Integer>();
		int countCalls = 0;
		int startingPoint = firstFalse(visited);
		while (startingPoint != -1) {
			countCalls++;
			stack.push(startingPoint);

			while (!stack.isEmpty()) {
				int current = stack.pop();
				visited[current] = true;
				for (int i = 0; i < graph[current].length; i++) {
					if (graph[current][i] && !visited[i]) {
						stack.push(i);
					}
				}
			}

			startingPoint = firstFalse(visited);
		}

		return countCalls;

	}

	private static int firstFalse(boolean[] array) {
		for (int i = 0; i < array.length; i++) {
			if (!array[i]) {
				return i;
			}
		}

		return -1;
	}

}
