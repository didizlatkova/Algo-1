package week5.spanningtrees.secondbest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
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
		List<Integer> allWeights = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {
			input = reader.readLine().split(" ");
			int firstNode = Integer.parseInt(input[0]);
			int secondNode = Integer.parseInt(input[1]);
			int weight = Integer.parseInt(input[2]);

			if (!adjList.containsKey(firstNode)) {
				adjList.put(firstNode, new ArrayList<QueueElement>());
			}
			adjList.get(firstNode).add(new QueueElement(secondNode, weight));

			if (!adjList.containsKey(secondNode)) {
				adjList.put(secondNode, new ArrayList<QueueElement>());
			}
			adjList.get(secondNode).add(new QueueElement(firstNode, weight));

			allWeights.add(weight);
		}

		System.out.println(secondBest(adjList, n, allWeights));
	}

	private static int secondBest(Map<Integer, List<QueueElement>> adjList,
			int n, List<Integer> allWeights) {
		PriorityQueue queue = new PriorityQueue(n);
		List<Integer> keys = new ArrayList<Integer>(adjList.keySet());
		int node = keys.get(0);
		boolean[] visited = new boolean[n];
		visited[node] = true;
		List<Integer> sums = new ArrayList<Integer>();

		do {
			int countUnvisitedNodes = 0;
			for (int i = 0; i < adjList.get(node).size(); i++) {
				if (!visited[adjList.get(node).get(i).value]) {
					queue.insert(adjList.get(node).get(i).value,
							adjList.get(node).get(i).priority);
					countUnvisitedNodes++;
				}
			}

			if (countUnvisitedNodes != 0) {
				int sumToAdd = 0;
				do {
					node = queue.getMin().value;
					sumToAdd = queue.getMin().priority;
					queue.removeMin();
				} while (visited[node] && !queue.isEmpty());
				visited[node] = true;
				sums.add(sumToAdd);
				allWeights.remove(sumToAdd);
			} else {
				queue.removeMin();
			}
		} while (!queue.isEmpty());

		int minAddedWeight = Collections.min(sums);
		int maxUnaddedWeight = 0;
		do {
			maxUnaddedWeight = Collections.max(allWeights);
		} while (sums.contains(maxUnaddedWeight)
				|| maxUnaddedWeight == minAddedWeight);

		int finalSum = 0;
		for (int i = 0; i < sums.size(); i++) {
			finalSum += sums.get(i);
		}

		return finalSum - minAddedWeight + maxUnaddedWeight;
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

		public QueueElement(int value, int priority) {
			this.value = value;
			this.priority = priority;
		}

	}

}
