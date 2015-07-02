package week4.graphs.buildscripts;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BuildScripts {

	public List<Integer> getBuildOrder(int wanted, boolean graph[][]) {
		List<Integer> order = new ArrayList<Integer>();
		boolean visited[] = new boolean[graph.length];
		boolean inPath[] = new boolean[graph.length];
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(wanted);

		while (!stack.isEmpty()) {
			int current = stack.peek();
			visited[current] = true;
			int unvisitedChildren = 0;
			for (int i = 0; i < graph[current].length; i++) {
				if (graph[current][i]) {
					if (visited[i] && !inPath[i]) {
						return null;
					}
					if (!inPath[i]) {
						unvisitedChildren++;
						stack.push(i);
					}
				}
			}
			if (!inPath[current] && unvisitedChildren == 0) {
				order.add(current);
				inPath[current] = true;
			}
			if (inPath[current]) {
				stack.pop();
			}
		}

		return order;
	}

}
