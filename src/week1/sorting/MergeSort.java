package week1.sorting;

public class MergeSort implements SortingAlgorithm {

	@Override
	public int[] sort(int[] elements) {
		int[] sorted = divideAndSort(elements, 0, elements.length - 1);
		return sorted;
	}

	private static int[] divideAndSort(int[] elements, int start, int end) {
		if (start == end) {
			return new int[] { elements[start] };
		}

		int[] leftArray = divideAndSort(elements, start, (start + end) / 2);
		int[] rightArray = divideAndSort(elements, (start + end) / 2 + 1, end);

		int[] sorted = new int[rightArray.length + leftArray.length];
		int i = 0;
		int rightPointer = 0;
		int leftPointer = 0;
		while (rightPointer < rightArray.length
				&& leftPointer < leftArray.length) {
			if (rightArray[rightPointer] <= leftArray[leftPointer]) {
				sorted[i] = rightArray[rightPointer];
				rightPointer++;
			} else {
				sorted[i] = leftArray[leftPointer];
				leftPointer++;
			}
			i++;
		}

		while (rightPointer < rightArray.length) {
			sorted[i] = rightArray[rightPointer];
			i++;
			rightPointer++;
		}
		
		while (leftPointer < leftArray.length) {
			sorted[i] = leftArray[leftPointer];
			i++;
			leftPointer++;
		}

		return sorted;
	}
}
