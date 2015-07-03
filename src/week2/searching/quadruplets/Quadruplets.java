package week2.searching.quadruplets;

import java.util.Arrays;
import java.util.Scanner;

public class Quadruplets {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = Integer.parseInt(sc.nextLine());
		int[] a = new int[n];
		int[] b = new int[n];
		int[] c = new int[n];
		int[] d = new int[n];

		String[] inputA = sc.nextLine().split(" ");
		String[] inputB = sc.nextLine().split(" ");
		String[] inputC = sc.nextLine().split(" ");
		String[] inputD = sc.nextLine().split(" ");
		sc.close();

		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(inputA[i]);
			b[i] = Integer.parseInt(inputB[i]);
			c[i] = Integer.parseInt(inputC[i]);
			d[i] = Integer.parseInt(inputD[i]);
		}

		System.out.println(zeroQuadrupletsCount(a, b, c, d));
	}

	public static long zeroQuadrupletsCount(int[] a, int[] b, int[] c, int[] d) {
		int[] combinations = new int[a.length * b.length];

		int index = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				combinations[index] = a[i] + b[j];
				index++;
			}
		}

		long count = 0;
		Arrays.sort(combinations);

		int found = 0;
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < d.length; j++) {
				found = binarySearch(combinations, (c[i] + d[j]) * -1, 0);
				if (found != -1) {
					count++;
					while (found + 1 < combinations.length
							&& combinations[found + 1] == combinations[found]) {
						count++;
						found++;
					}
				}
			}
		}

		return count;
	}

	private static int binarySearch(int[] array, int number, int start) {
		int firstOccurrence = -1;
		int left = start;
		int right = array.length - 1;
		int middle = 0;
		int middleValue = 0;

		while (left <= right) {
			middle = left + (right - left) / 2;
			middleValue = array[middle];

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
