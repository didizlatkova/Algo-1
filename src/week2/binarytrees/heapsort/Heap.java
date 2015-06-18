package week2.binarytrees.heapsort;

public class Heap implements HeapInterface {

	private int[] array;
	private int accommodated = 0;

	public Heap(int capacity) {
		array = new int[capacity + 1];
	}

	@Override
	public int getMin() {
		if (accommodated == 0) {
			throw new IndexOutOfBoundsException();
		}

		return array[1];
	}

	@Override
	public void insert(int element) {
		if (accommodated == array.length) {
			throw new IndexOutOfBoundsException();
		}

		accommodated++;
		int index = accommodated;
		array[index] = element;

		while (index / 2 > 0) {
			if (array[index] < array[index / 2]) {
				switchElements(index, index / 2);
				index /= 2;
			} else {
				break;
			}
		}
	}

	private void switchElements(int i, int j) {
		int save = array[i];
		array[i] = array[j];
		array[j] = save;
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

	@Override
	public void removeMin() {
		array[1] = array[accommodated];
		array[accommodated] = 0;
		accommodated--;

		int index = 1;
		while (index * 2 <= accommodated) {
			if (index * 2 + 1 <= accommodated) {
				if (array[index * 2] > array[index * 2 + 1]
						&& array[index] > array[index * 2 + 1]) {
					switchElements(index, index * 2 + 1);
					index = index * 2 + 1;
					continue;
				}
			}

			if (array[index] > array[index * 2]) {
				switchElements(index, index * 2);
				index *= 2;
			} else {
				break;
			}
		}
	}

	@Override
	public boolean isEmpty() {
		return accommodated == 0;
	}

}
