package week2.binarytrees.heapsort;

public class Test {

	public static void main(String[] args) {
		int[] elements = new int[] { 4, 13, 52, 7, 18, 3, 1, 6 };
		HeapSort heap = new HeapSort();
		int[] sorted = heap.sort(elements);
		for (int i = 0; i < sorted.length; i++) {
			System.out.print(sorted[i] + " ");
		}
	}

}
