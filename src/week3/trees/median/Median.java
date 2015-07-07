package week3.trees.median;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Median {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		int n = Integer.parseInt(reader.readLine());
		String[] input = reader.readLine().split(" ");
		reader.close();

		median = Integer.parseInt(input[0]);
		System.out.println(median);
		for (int i = 1; i < n; i++) {
			System.out.println(insert(Integer.parseInt(input[i])));
		}

	}

	private static MinHeap minHeap = new MinHeap(500000);
	private static MaxHeap maxHeap = new MaxHeap(500000);
	private static int median;

	// inserts the number and returns the median
	public static int insert(int number) {
		if (number < median) {
			maxHeap.insert(number);
		} else {
			minHeap.insert(number);
		}

		if (minHeap.size() > maxHeap.size()) {
			maxHeap.insert(median);
			median = minHeap.getMin();
			minHeap.removeMin();
			return median;
		}

		if (maxHeap.size() - minHeap.size() == 2) {
			minHeap.insert(median);
			median = maxHeap.getMax();
			maxHeap.removeMax();
		}

		return median;
	}

	private static class MaxHeap {

		private int[] array;
		private int accommodated = 0;

		public MaxHeap(int capacity) {
			array = new int[capacity + 1];
		}

		public int getMax() {
			return array[1];
		}

		public void insert(int element) {
			accommodated++;
			int index = accommodated;
			array[index] = element;

			while (index / 2 > 0) {
				if (array[index] > array[index / 2]) {
					switchElements(index, index / 2);
					index /= 2;
				} else {
					break;
				}
			}
		}

		private void switchElements(int i, int j) {
			int save = array[i];
			array[i] = array[j];
			array[j] = save;
		}

		public void removeMax() {
			array[1] = array[accommodated];
			array[accommodated] = 0;
			accommodated--;

			int index = 1;
			while (index * 2 <= accommodated) {
				if (index * 2 + 1 <= accommodated) {
					if (array[index * 2] < array[index * 2 + 1]
							&& array[index] < array[index * 2 + 1]) {
						switchElements(index, index * 2 + 1);
						index = index * 2 + 1;
						continue;
					}
				}

				if (array[index] < array[index * 2]) {
					switchElements(index, index * 2);
					index *= 2;
				} else {
					break;
				}
			}
		}

		public int size() {
			return accommodated;
		}

	}

	private static class MinHeap {

		private int[] array;
		private int accommodated = 0;

		public MinHeap(int capacity) {
			array = new int[capacity + 1];
		}

		public int getMin() {
			return array[1];
		}

		public void insert(int element) {
			accommodated++;
			int index = accommodated;
			array[index] = element;

			while (index / 2 > 0) {
				if (array[index] < array[index / 2]) {
					switchElements(index, index / 2);
					index /= 2;
				} else {
					break;
				}
			}
		}

		private void switchElements(int i, int j) {
			int save = array[i];
			array[i] = array[j];
			array[j] = save;
		}

		public void removeMin() {
			array[1] = array[accommodated];
			array[accommodated] = 0;
			accommodated--;

			int index = 1;
			while (index * 2 <= accommodated) {
				if (index * 2 + 1 <= accommodated) {
					if (array[index * 2] > array[index * 2 + 1]
							&& array[index] > array[index * 2 + 1]) {
						switchElements(index, index * 2 + 1);
						index = index * 2 + 1;
						continue;
					}
				}

				if (array[index] > array[index * 2]) {
					switchElements(index, index * 2);
					index *= 2;
				} else {
					break;
				}
			}
		}

		public int size() {
			return accommodated;
		}

	}

}
