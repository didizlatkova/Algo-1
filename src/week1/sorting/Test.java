package week1.sorting;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		SortingAlgorithm selection = new SelectionSort();
		System.out.println("Selection: " + test(selection));
		
		SortingAlgorithm insertion = new InsertionSort();
		System.out.println("Insertion: " + test(insertion));
		
		SortingAlgorithm counting = new CountingSort();
		System.out.println("Counting: " + test(counting));
		
		SortingAlgorithm merge = new MergeSort();
		System.out.println("Merge: " + test(merge));

	}

	public static boolean test(SortingAlgorithm algorithm) {
		Random rnd = new Random();
		
		int[] array = new int[rnd.nextInt(10) + 10];

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
