package week3.trees.median;

import java.util.Scanner;

public class Median {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = Integer.parseInt(sc.nextLine());
		String[] input = sc.nextLine().split(" ");
		for (int i = 0; i < n; i++) {
			System.out.println(insert(Integer.parseInt(input[i])));
		}

		sc.close();
	}

	private static MinHeap minHeap = new MinHeap(50);
	private static MaxHeap maxHeap = new MaxHeap(50);
	private static int median;
	private static boolean medianInitialized;

	// inserts the number and returns the median
	public static int insert(int number) {
		if (minHeap.isEmpty() && maxHeap.isEmpty() && !medianInitialized) {
			medianInitialized = true;
			median = number;
			return median;
		}
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

		if (maxHeap.size() - minHeap.size() >= 2) {
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
			if (accommodated == 0) {
				throw new IndexOutOfBoundsException();
			}

			return array[1];
		}

		public void insert(int element) {
			if (accommodated == array.length) {
				throw new IndexOutOfBoundsException();
			}

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

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (int i = 1; i < array.length; i++) {
				sb.append(array[i] + ", ");
			}
			sb.append("]");

			return sb.toString();
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

		public boolean isEmpty() {
			return accommodated == 0;
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
			if (accommodated == 0) {
				throw new IndexOutOfBoundsException();
			}

			return array[1];
		}

		public void insert(int element) {
			if (accommodated == array.length) {
				throw new IndexOutOfBoundsException();
			}

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

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (int i = 1; i < array.length; i++) {
				sb.append(array[i] + ", ");
			}
			sb.append("]");

			return sb.toString();
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

		public boolean isEmpty() {
			return accommodated == 0;
		}

		public int size() {
			return accommodated;
		}

	}

}
