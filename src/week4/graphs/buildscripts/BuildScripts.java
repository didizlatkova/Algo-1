package week4.graphs.buildscripts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class BuildScripts {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		int numberOfProjects = Integer.parseInt(reader.readLine());
		String[] projects = reader.readLine().split(" ");
		String wanted = reader.readLine();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < numberOfProjects; i++) {
			map.put(projects[i], i);
		}

		boolean graph[][] = new boolean[numberOfProjects][numberOfProjects];

		for (int i = 0; i < projects.length; i++) {
			String[] dependencies = reader.readLine().split(" ");
			for (int j = 1; j <= Integer.parseInt(dependencies[0]); j++) {
				graph[i][map.get(dependencies[j])] = true;
			}
		}

		List<Integer> order = getBuildOrder(map.get(wanted), graph);

		if (order == null) {
			System.out.print("BUILD ERROR");
		} else {
			for (int i = 0; i < order.size() - 1; i++) {
				System.out.print(projects[order.get(i)] + " ");
			}
			System.out.print(projects[order.get(order.size() - 1)]);
		}
	}

	private static List<Integer> getBuildOrder(int wanted, boolean graph[][]) {
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
