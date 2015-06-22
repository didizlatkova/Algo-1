package week3.trees.median;

import week2.binarytrees.heapsort.*;

public class Median {

	private static MinHeap minHeap = new MinHeap(50);
	private static MaxHeap maxHeap = new MaxHeap(50);
	private static int median;
	private static boolean medianInitialized;

	// inserts the number and returns the median
	public static int insert(int number) {
		if (minHeap.isEmpty() && maxHeap.isEmpty() && !medianInitialized) {
			medianInitialized = true;
			median = number;
			return median;
		}
		if (number < median) {
			maxHeap.insert(number);
		} else {
			minHeap.insert(number);
		}

		if (minHeap.size() > maxHeap.size()) {
			maxHeap.insert(median);
			median = minHeap.getMin();
			minHeap.removeMin();
			return median;
		}

		if (maxHeap.size() - minHeap.size() >= 2) {
			minHeap.insert(median);
			median = maxHeap.getMax();
			maxHeap.removeMax();
		}

		return median;
	}
}
