package week3.trees.rmq;

public class RMQ {

	private int[] array;
	private final int originalLength;

	public RMQ(int[] input) {
		int n = input.length;
		while ((n & (n - 1)) != 0) {
			n++;
		}

		originalLength = n;
		array = new int[n * 2];

		for (int i = 0; i < input.length; i++) {
			array[originalLength + i] = input[i];
		}

		for (int i = originalLength - 1; i > 0; i--) {
			array[i] = Math.min(array[2 * i], array[2 * i + 1]);
		}
	}

	// sets the value at index
	public void set(int index, int value) {
		array[index + originalLength] = value;
		int parentElementIndex = (index + originalLength) / 2;
		while (parentElementIndex > 0) {
			array[parentElementIndex] = Math.min(array[parentElementIndex * 2],
					array[parentElementIndex * 2 + 1]);
			parentElementIndex /= 2;
		}
	}

	// returns the minimum value in a range
	public int min(int startIndex, int endIndex) {
		startIndex += originalLength;
		int min = array[startIndex];
		endIndex += originalLength;

		while (startIndex < endIndex) {
			if (startIndex % 2 == 1) {
				// right child
				min = Math.min(min, array[startIndex]);
				startIndex++;
			} else {
				// left child
				min = Math.min(min, array[startIndex / 2]);
			}

			startIndex /= 2;

			if (endIndex % 2 == 1) {
				// right child
				min = Math.min(min, array[endIndex / 2]);
			} else {
				// left child
				min = Math.min(min, array[endIndex]);
				endIndex--;
			}

			endIndex /= 2;
		}

		return min;
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

}
