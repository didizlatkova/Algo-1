package week2.binarytrees.heapsort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HeapSort {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		int n = Integer.parseInt(reader.readLine());
		int[] elements = new int[n];
		String[] input = reader.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			elements[i] = Integer.parseInt(input[i]);
		}

		HeapSort heap = new HeapSort();
		int[] sorted = heap.sort(elements);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sorted.length - 1; i++) {
			sb.append(sorted[i] + " ");
		}
		sb.append(sorted[sorted.length - 1]);

		System.out.println(sb.toString());
	}

	public int[] sort(int[] elements) {
		MinHeap heap = new MinHeap(elements.length);

		for (int i = 0; i < elements.length; i++) {
			heap.insert(elements[i]);
		}

		for (int i = 0; i < elements.length; i++) {
			elements[i] = heap.getMin();
			heap.removeMin();
		}

		return elements;
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
	}
}
