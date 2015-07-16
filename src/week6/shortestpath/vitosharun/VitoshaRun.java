package week6.shortestpath.vitosharun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class VitoshaRun {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		int n = Integer.parseInt(reader.readLine());
		String[] coords = reader.readLine().split(" ");
		int runnerX = Integer.parseInt(coords[0]);
		int runnerY = Integer.parseInt(coords[1]);
		int destX = Integer.parseInt(coords[2]);
		int destY = Integer.parseInt(coords[3]);

		String[] input;
		int[][] graph = new int[n * n][n * n];
		int current;
		int[][] distances = new int[n][n];
		for (int[] row : graph) {
			Arrays.fill(row, Integer.MAX_VALUE / 2);
		}

		for (int i = 0; i < n; i++) {
			input = reader.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				distances[i][j] = Integer.parseInt(input[j]);
				current = i * n + j;
				graph[current][current] = 0;
				// left
				if (j - 1 >= 0) {
					graph[current][i * n + j - 1] = Math.abs(distances[i][j]
							- distances[i][j - 1]) + 1;
					graph[i * n + j - 1][current] = Math.abs(distances[i][j]
							- distances[i][j - 1]) + 1;
				}

				// up left
				if (j - 1 >= 0 && i - 1 >= 0) {
					graph[current][(i - 1) * n + j - 1] = Math
							.abs(distances[i][j] - distances[i - 1][j - 1]) + 1;
					graph[(i - 1) * n + j - 1][current] = Math
							.abs(distances[i][j] - distances[i - 1][j - 1]) + 1;
				}

				// up
				if (i - 1 >= 0) {
					graph[current][(i - 1) * n + j] = Math.abs(distances[i][j]
							- distances[i - 1][j]) + 1;
					graph[(i - 1) * n + j][current] = Math.abs(distances[i][j]
							- distances[i - 1][j]) + 1;
				}

				// up right
				if (j + 1 < n && i - 1 >= 0) {
					graph[current][(i - 1) * n + j + 1] = Math
							.abs(distances[i][j] - distances[i - 1][j + 1]) + 1;
					graph[(i - 1) * n + j + 1][current] = Math
							.abs(distances[i][j] - distances[i - 1][j + 1]) + 1;
				}
			}
		}

		// for (int i = 0; i < n * n; i++) {
		// for (int j = 0; j < n * n; j++) {
		// System.out.print(graph[i][j] + " ");
		// }
		// System.out.println();
		// }

		getDistances(graph, n * runnerX + runnerY, n * destX + destY);

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

		int value;
		int n = (int) Math.sqrt(weight.length);
		while (!stack.isEmpty()) {
			value = stack.pop();
			System.out.print(value / n + ";" + value % n + " ");
		}
		System.out.println(station / n + ";" + station % n);
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
