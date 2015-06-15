package week2.searching.quadruplets;

import java.util.Arrays;

public class Quadruplets {

	public static int zeroQuadrupletsCount(int[] a, int[] b, int[] c, int[] d) {
		int[] combinations = new int[a.length * b.length];

		int index = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				combinations[index] = a[i] + b[j];
				index++;
			}
		}

		int count = 0;
		index = 0;
		Arrays.sort(combinations);
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < d.length; j++) {
				int left = 0;
				int found = binarySearch(combinations,
						Math.negateExact(c[i] + d[j]), left);
				while (found != -1) {
					count++;
					found = binarySearch(combinations,
							Math.negateExact(c[i] + d[j]), found + 1);
				}
			}
		}

		return count;
	}

	private static int binarySearch(int[] array, int number, int start) {
		int firstOccurrence = -1;
		int left = start;
		int right = array.length - 1;

		while (left <= right) {
			int middle = left + (right - left) / 2;
			int middleValue = array[middle];

			if (middleValue > number) {
				right = middle - 1;
			} else if (middleValue < number) {
				left = middle + 1;
			} else {
				firstOccurrence = middle;
				right = middle - 1;
			}
		}

		return firstOccurrence;
	}
}
