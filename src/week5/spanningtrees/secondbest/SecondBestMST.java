package week5.spanningtrees.secondbest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondBestMST {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		int n = Integer.parseInt(reader.readLine());
		Map<Integer, List<QueueElement>> adjList = new HashMap<Integer, List<QueueElement>>();
		String[] input;
		List<Integer> nodes = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {
			input = reader.readLine().split(" ");
			int firstNode = Integer.parseInt(input[0]);
			int secondNode = Integer.parseInt(input[1]);
			int weight = Integer.parseInt(input[2]);

			if (!adjList.containsKey(firstNode)) {
				adjList.put(firstNode, new ArrayList<QueueElement>());
				nodes.add(firstNode);
			}
			adjList.get(firstNode).add(new QueueElement(secondNode, weight, 0));

			if (!adjList.containsKey(secondNode)) {
				adjList.put(secondNode, new ArrayList<QueueElement>());
			}
			adjList.get(secondNode).add(new QueueElement(firstNode, weight, 0));
		}

		System.out.println(secondBest(adjList, n, nodes));
	}

	private static int secondBest(Map<Integer, List<QueueElement>> adjList,
			int n, List<Integer> nodes) {
		PriorityQueue queue = new PriorityQueue(n);
		List<Integer> keys = new ArrayList<Integer>(adjList.keySet());
		int node = keys.get(0);
		boolean[] visited = new boolean[n];
		visited[node] = true;
		int sum = 0;

		do {
			for (int i = 0; i < adjList.get(node).size(); i++) {
				if (!visited[adjList.get(node).get(i).value]) {
					queue.insert(adjList.get(node).get(i).value,
							adjList.get(node).get(i).priority, node);
				}
			}

			int comingFrom;
			int sumToAdd = 0;
			do {
				node = queue.getMin().value;
				sumToAdd = queue.getMin().priority;
				comingFrom = queue.getMin().comingFrom;
				queue.removeMin();
			} while (visited[node] && !queue.isEmpty());
			if (!visited[node]) {
				visited[node] = true;
				sum += sumToAdd;
				for (QueueElement element : adjList.get(comingFrom)) {
					if (element.value == node) {
						element.inMinSpanningTree = true;
						break;
					}
				}
				for (QueueElement element : adjList.get(node)) {
					if (element.value == comingFrom) {
						element.inMinSpanningTree = true;
						break;
					}
				}
			}
		} while (!queue.isEmpty());

		// looking for min P-y+x
		int x = 0;
		int y = 0;
		int min = Integer.MAX_VALUE;

		for (int i : keys) {
			for (int j = 0; j < adjList.get(i).size(); j++) {
				if (adjList.get(i).get(j) != null
						&& i < adjList.get(i).get(j).value
						&& !adjList.get(i).get(j).inMinSpanningTree) {
					cycle = new ArrayList<Integer>();
					visitedDFS = new boolean[n];
					x = adjList.get(i).get(j).priority;
					y = Integer.MIN_VALUE;
					found = false;
					dfs(adjList, adjList.get(i).get(j).value, i, true);
					for (int k = 0; k < cycle.size() - 1; k++) {
						for (int l = 0; l < adjList.get(cycle.get(k)).size(); l++) {
							if (adjList.get(cycle.get(k)).get(l).value == cycle
									.get(k + 1)) {
								int currentWeight = adjList.get(cycle.get(k))
										.get(l).priority;
								if (currentWeight != x && y < currentWeight) {
									y = currentWeight;
								}
								break;
							}
						}
					}

					if (x - y < min) {
						min = x - y;
					}
				}
			}
		}

		return sum + min;
	}

	private static List<Integer> cycle;
	private static boolean[] visitedDFS;
	private static boolean found;

	private static void dfs(Map<Integer, List<QueueElement>> adjList, int node,
			int startingNode, boolean firstCall) {
		if (node == startingNode) {
			cycle.add(node);
			found = true;
			return;
		}

		visitedDFS[node] = true;
		cycle.add(node);
		for (int i = 0; i < adjList.get(node).size(); i++) {
			int currentNode = adjList.get(node).get(i).value;
			if (firstCall && currentNode == startingNode) {
				continue;
			}
			if (!visitedDFS[currentNode]
					&& adjList.get(node).get(i).inMinSpanningTree) {
				dfs(adjList, currentNode, startingNode, false);
				if (!found) {
					cycle.remove((Object) currentNode);
				} else {
					break;
				}
			}
		}
	}

	private static class PriorityQueue {

		private QueueElement[] array;
		private int accommodated = 0;

		public PriorityQueue(int capacity) {
			array = new QueueElement[capacity + 1];
		}

		public QueueElement getMin() {
			if (accommodated == 0) {
				throw new IndexOutOfBoundsException();
			}

			return array[1];
		}

		public void insert(int element, int priority, int comingFrom) {
			if (accommodated == array.length) {
				throw new IndexOutOfBoundsException();
			}

			accommodated++;
			int index = accommodated;
			array[index] = new QueueElement(element, priority, comingFrom);

			while (index / 2 > 0) {
				if (array[index].priority < array[index / 2].priority) {
					switchElements(index, index / 2);
					index /= 2;
				} else {
					break;
				}
			}
		}

		private void switchElements(int i, int j) {
			QueueElement save = array[i];
			array[i] = array[j];
			array[j] = save;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (int i = 1; i < array.length; i++) {
				sb.append(array[i].value + ", ");
			}
			sb.append("]");

			return sb.toString();
		}

		public void removeMin() {
			array[1] = array[accommodated];
			array[accommodated] = null;
			accommodated--;

			int index = 1;
			while (index * 2 <= accommodated) {
				if (index * 2 + 1 <= accommodated) {
					if (array[index * 2].priority > array[index * 2 + 1].priority
							&& array[index].priority > array[index * 2 + 1].priority) {
						switchElements(index, index * 2 + 1);
						index = index * 2 + 1;
						continue;
					}
				}

				if (array[index].priority > array[index * 2].priority) {
					switchElements(index, index * 2);
					index *= 2;
				} else {
					break;
				}
			}
		}

		public boolean isEmpty() {
			return accommodated == 0;
		}

	}

	private static class QueueElement {

		public int value;
		public int priority;
		public int comingFrom;
		public boolean inMinSpanningTree;

		public QueueElement(int value, int priority, int comingFrom) {
			this.value = value;
			this.priority = priority;
			this.comingFrom = comingFrom;
			this.inMinSpanningTree = false;
		}
		
	}

}
