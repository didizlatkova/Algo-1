package week1.sorting;

public class CountingSort implements SortingAlgorithm {

	@Override
	public int[] sort(int[] elements) {
		int maxNumber = 50;
		
		int[] counts = new int[maxNumber];
		
		for (int i = 0; i < elements.length; i++) {
			counts[elements[i]]++;
		}
		
		int index = 0;
		for (int i = 0; i < counts.length; i++) {
			for (int j = 0; j < counts[i]; j++) {
				elements[index] = i;
				index++;
			}
		}
		
		return elements;
	}
}
