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
			adjList.get(firstNode).add(new QueueElement(secondNode, weight));

			if (!adjList.containsKey(secondNode)) {
				adjList.put(secondNode, new ArrayList<QueueElement>());
			}
			adjList.get(secondNode).add(new QueueElement(firstNode, weight));
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
							adjList.get(node).get(i).priority);
				}
			}

			int saveNode = node;
			int sumToAdd = 0;
			do {
				node = queue.getMin().value;
				sumToAdd = queue.getMin().priority;
				queue.removeMin();
			} while (visited[node] && !queue.isEmpty());
			if (!visited[node]) {
				visited[node] = true;
				sum += sumToAdd;
				for (QueueElement element : adjList.get(saveNode)) {
					if (element.value == node) {
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
					dfs(adjList, adjList.get(i).get(j).value, i);
					for (int k = 1; k < cycle.size() - 1; k++) {
						int currentWeight = adjList.get(cycle.get(k)).get(
								cycle.get(k + 1)).priority;
						if (currentWeight != x && y < currentWeight) {
							y = currentWeight;
						}
					}

					if (x - y < min) {
						min = x - y;
					}
				}
			}
		}

		// for (Integer outsideNode : nodes) {
		// cycle = new ArrayList<Integer>();
		// visitedDFS = new boolean[n];
		// dfs(adjList, outsideNode, outsideNode, nodes);
		// x = adjList.get(cycle.get(0)).get(cycle.get(1)).priority;
		// y = Integer.MIN_VALUE;
		// for (int i = 1; i < cycle.size() - 1; i++) {
		// int currentWeight = adjList.get(cycle.get(i)).get(
		// cycle.get(i + 1)).priority;
		// if (currentWeight != x && y < currentWeight) {
		// y = currentWeight;
		// }
		// }
		//
		// if (x - y < min) {
		// min = x - y;
		// }
		// }

		return sum + min;
	}

	private static List<Integer> cycle;
	private static boolean[] visitedDFS;

	private static boolean dfs(Map<Integer, List<QueueElement>> adjList,
			int node, int startingNode) {
		if (node == startingNode) {
			return true;
		}

		visitedDFS[node] = true;
		cycle.add(node);
		for (int i = 0; i < adjList.get(node).size(); i++) {
			int currentNode = adjList.get(node).get(i).value;
			if (!visitedDFS[currentNode]
					&& adjList.get(node).get(i).inMinSpanningTree) {
				if (!dfs(adjList, currentNode, startingNode)) {
					cycle.remove((Object) currentNode);
				}
			}
		}

		return false;
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

		public void insert(int element, int priority) {
			if (accommodated == array.length) {
				throw new IndexOutOfBoundsException();
			}

			accommodated++;
			int index = accommodated;
			array[index] = new QueueElement(element, priority);

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
		public boolean inMinSpanningTree;

		public QueueElement(int value, int priority) {
			this.value = value;
			this.priority = priority;
			this.inMinSpanningTree = false;
		}

	}

}
