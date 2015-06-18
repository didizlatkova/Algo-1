package week2.binarytrees.heapsort;

import week1.sorting.SortingAlgorithm;

public class HeapSort implements SortingAlgorithm {

	@Override
	public int[] sort(int[] elements) {
		Heap heap = new Heap(elements.length);

		for (int i = 0; i < elements.length; i++) {
			heap.insert(elements[i]);
		}

		for (int i = 0; i < elements.length; i++) {
			elements[i] = heap.getMin();
			heap.removeMin();
		}

		return elements;
	}

}
