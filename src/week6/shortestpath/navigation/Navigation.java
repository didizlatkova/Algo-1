package week6.shortestpath.navigation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Navigation {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		String[] input = reader.readLine().split(" ");

		int junctions = Integer.parseInt(input[0]);
		int streets = Integer.parseInt(input[1]);
		int location = Integer.parseInt(input[2]);
		int station = Integer.parseInt(input[3]);

		int[][] weight = new int[junctions][junctions];

		for (int i = 0; i < streets; i++) {
			input = reader.readLine().split(" ");
			weight[Integer.parseInt(input[0]) - 1][Integer.parseInt(input[1]) - 1] = Integer
					.parseInt(input[2]);
			weight[Integer.parseInt(input[1]) - 1][Integer.parseInt(input[0]) - 1] = Integer
					.parseInt(input[2]);
		}

		for (int i = 0; i < junctions; i++) {
			for (int j = 0; j < junctions; j++) {
				if (weight[i][j] == 0 && i != j) {
					weight[i][j] = Integer.MAX_VALUE / 2;
				}
			}
		}

		getDistances(weight, location - 1, station - 1);
	}

	private static void getDistances(int[][] weight, int location, int station) {
		int[] dist = new int[weight.length];
		for (int i = 0; i < dist.length; i++) {
			if (i == location) {
				dist[i] = 0;
			} else {
				dist[i] = Integer.MAX_VALUE / 2;
			}
		}
		boolean[] visited = new boolean[weight.length];
		int[] prev = new int[weight.length];
		PriorityQueue queue = new PriorityQueue(100000);
		queue.insert(location, 0);

		while (!queue.isEmpty()) {
			int current = queue.getMin();
			queue.removeMin();
			if (!visited[current]) {
				visited[current] = true;
				for (int i = 0; i < weight[current].length; i++) {
					if (weight[current][i] + dist[current] < dist[i]) {
						// change dist
						dist[i] = weight[current][i] + dist[current];
						prev[i] = current;
						queue.insert(i, dist[i]);
					}
				}
			}
		}

		System.out.println(dist[station]);
		Stack<Integer> stack = new Stack<Integer>();
		int prevElement = prev[station];
		do {
			stack.push(prevElement);
			prevElement = prev[prevElement];
		} while (prevElement != location);
		stack.push(location);

		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + 1 + " ");
		}
		System.out.println(station + 1);
	}

	private static class PriorityQueue {

		private QueueElement[] array;
		private int accommodated = 0;

		public PriorityQueue(int capacity) {
			array = new QueueElement[capacity + 1];
		}

		public int getMin() {
			if (accommodated == 0) {
				throw new IndexOutOfBoundsException();
			}

			return array[1].value;
		}

		public void insert(int location, int priority) {
			if (accommodated == array.length) {
				throw new IndexOutOfBoundsException();
			}

			accommodated++;
			int index = accommodated;
			array[index] = new QueueElement(location, priority);

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
