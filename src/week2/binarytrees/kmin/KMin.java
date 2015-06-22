package week2.binarytrees.kmin;

import java.util.List;

import week2.binarytrees.heapsort.Heap;

public class KMin {

	// Finds the k-th minimum element in an unsorted collection.
	public static int kthMinimum(List<Integer> numbers, int k) {
		Heap heap = new Heap(numbers.size());
		
		for (int i = 0; i < numbers.size(); i++) {
			heap.insert(numbers.get(i));
		}
		
		for (int i = 0; i < k - 1; i++) {
			heap.removeMin();
		}
		
		return heap.getMin();
	}

}
