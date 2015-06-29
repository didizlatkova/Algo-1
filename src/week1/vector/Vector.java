package week1.vector;

public class Vector implements VectorInterface {

	private int[] array;
	private int capacity;
	private int accommodated = 0;

	private void doubleArray() {
		int[] newArray = new int[capacity * 2];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
		capacity *= 2;
	}

	public Vector(int initialCapacity) {
		array = new int[initialCapacity];
		capacity = initialCapacity;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < accommodated; i++) {
			sb.append(array[i]);
		}
		sb.append("]");

		return sb.toString();
	}

	@Override
	public void insert(int index, int value) {
		if (index < 0 || index >= accommodated) {
			throw new IndexOutOfBoundsException();
		}

		if (accommodated == capacity) {
			doubleArray();
		}

		for (int i = accommodated; i >= index + 1; i--) {
			array[i] = array[i - 1];
		}

		array[index] = value;
		accommodated++;
	}

	@Override
	public void add(int value) {
		if (accommodated == capacity) {
			doubleArray();
		}

		array[accommodated] = value;
		accommodated++;
	}

	@Override
	public int get(int index) {
		if (index < 0 || index >= accommodated) {
			throw new IndexOutOfBoundsException();
		}

		return array[index];
	}

	@Override
	public void remove(int index) {
		if (index < 0 || index >= accommodated) {
			throw new IndexOutOfBoundsException();
		}

		for (int i = index; i < accommodated - 1; i++) {
			array[i] = array[i + 1];
		}

		accommodated--;
	}

	@Override
	public void pop() {
		array[accommodated - 1] = 0;
		accommodated--;
	}

	@Override
	public int size() {
		return accommodated;
	}

	@Override
	public int capacity() {
		return capacity;
	}

}
