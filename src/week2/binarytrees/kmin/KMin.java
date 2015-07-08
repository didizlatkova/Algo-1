package week2.binarytrees.kmin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class KMin {

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		String[] nk = reader.readLine().split(" ");
		int n = Integer.parseInt(nk[0]);
		int k = Integer.parseInt(nk[1]);
		List<Integer> list = new ArrayList<Integer>();
		String[] input = reader.readLine().split(" ");

		for (int i = 0; i < n; i++) {
			list.add(Integer.parseInt(input[i]));
		}

		System.out.println(KMin.kthMinimum(list, k));
	}

	// Finds the k-th minimum element in an unsorted collection.
	public static int kthMinimum(List<Integer> numbers, int k) {
		MinHeap heap = new MinHeap(numbers.size());

		for (int i = 0; i < numbers.size(); i++) {
			heap.insert(numbers.get(i));
		}

		for (int i = 0; i < k - 1; i++) {
			heap.removeMin();
		}

		return heap.getMin();
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
