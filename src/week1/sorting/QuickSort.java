package week1.sorting;

public class QuickSort implements SortingAlgorithm {

	@Override
	public int[] sort(int[] elements) {
		int[] sorted = divideAndSort(elements, 0, elements.length - 1);
		return sorted;
	}

	public static int[] divideAndSort(int[] elements, int start, int end) {
		if(start < end){
			return new int[] {};
		}
		if (start == end) {
			return new int[] { elements[start] };
		}

		int pivot = elements[start];
		int startCounter = 0;
		int endCounter = end - start;
		int[] sorted = new int[end - start + 1];
		for (int i = start + 1; i <= end; i++) {
			if (elements[i] <= pivot) {
				sorted[startCounter] = elements[i];
				startCounter++;
			} else {
				sorted[endCounter] = elements[i];
				endCounter--;
			}
		}

		sorted[startCounter] = pivot;

		int[] leftArray = divideAndSort(sorted, start, startCounter - 1);
		int[] rightArray = divideAndSort(sorted, startCounter + 1, end);

		for (int i = 0; i < leftArray.length; i++) {
			sorted[i] = leftArray[i];
		}

		for (int i = 0; i < rightArray.length; i++) {
			sorted[i + startCounter] = rightArray[i];
		}

		return sorted;
	}

}
