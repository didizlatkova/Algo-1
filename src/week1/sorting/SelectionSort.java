package week1.sorting;

public class SelectionSort implements SortingAlgorithm {

	@Override
	public int[] sort(int[] elements) {	
		
		for (int i = 0; i < elements.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < elements.length; j++) {
				if (elements[j] < elements[minIndex]) {
					minIndex = j;
				}
			}
			// switch
			int saveFirst = elements[i];
			elements[i] = elements[minIndex];
			elements[minIndex] = saveFirst;
		}
		
		return elements;
	}

}
