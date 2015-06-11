package week1.sorting;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		SortingAlgorithm selection = new InsertionSort();
		System.out.println(test(selection));

	}

	public static boolean test(SortingAlgorithm algorithm) {
		Random rnd = new Random();
		int[] array = new int[20];

		for (int i = 0; i < array.length; i++) {
			array[i] = rnd.nextInt(50);
		}

		int[] sorted = algorithm.sort(array);

		for (int i = 0; i < sorted.length - 1; i++) {
			if (sorted[i] > sorted[i + 1]) {
				return false;
			}
		}
		
		return true;
	}

}
