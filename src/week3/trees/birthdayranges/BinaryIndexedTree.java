package week3.trees.birthdayranges;

public class BinaryIndexedTree {

	private int[] array;
	private final int originalLength = 512;

	public BinaryIndexedTree(int[] input) {
		array = new int[1024];

		for (int i = 0; i < input.length; i++) {
			array[originalLength + input[i]]++;
		}

		for (int i = originalLength - 1; i > 0; i--) {
			array[i] = array[2 * i] + array[2 * i + 1];
		}
	}

	public void update(int element, int amount) {
		int parentElementIndex = element + originalLength;
		if (array[parentElementIndex] + amount < 0) {
			amount = Math.negateExact(array[parentElementIndex]);
		}
		
		do {
			array[parentElementIndex] += amount;
			parentElementIndex /= 2;
		} while (parentElementIndex > 0);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 1; i < array.length; i++) {
			sb.append(array[i] + ", ");
		}
		sb.append("]");

		return sb.toString();
	}

	public int query(int range) {
		if (range == originalLength) {
			return array[1];
		}

		int sum = 0;
		int currentIndex = range + originalLength;
		while (currentIndex > 1) {
			if (currentIndex % 2 == 1) {
				// right child
				sum += array[currentIndex - 1];
			}
			currentIndex /= 2;
		}

		return sum;
	}
	
}
