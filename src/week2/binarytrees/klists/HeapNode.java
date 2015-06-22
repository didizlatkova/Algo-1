package week2.binarytrees.klists;

import week1.linkedlist.Node;

public class HeapNode {

	private Node[] array;
	private int accommodated = 0;

	public HeapNode(int capacity) {
		array = new Node[capacity + 1];
	}

	public Node getMin() {
		if (accommodated == 0) {
			throw new IndexOutOfBoundsException();
		}

		return array[1];
	}

	public void insert(Node element) {
		if (accommodated == array.length) {
			throw new IndexOutOfBoundsException();
		}

		accommodated++;
		int index = accommodated;
		array[index] = element;
		
		while (index / 2 > 0) {
			if (array[index].value < array[index / 2].value) {
				switchElements(index, index / 2);
				index /= 2;
			} else {
				break;
			}
		}
	}

	private void switchElements(int i, int j) {
		Node save = array[i];
		array[i] = array[j];
		array[j] = save;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 1; i < array.length; i++) {
			sb.append(array[i].value + ", ");
		}
		sb.append("]");

		return sb.toString();
	}

	public void removeMin() {
		array[1] = array[accommodated];
		array[accommodated] = new Node(0);
		accommodated--;

		int index = 1;
		while (index * 2 <= accommodated) {
			if (index * 2 + 1 <= accommodated) {
				if (array[index * 2].value > array[index * 2 + 1].value
						&& array[index].value > array[index * 2 + 1].value) {
					switchElements(index, index * 2 + 1);
					index = index * 2 + 1;
					continue;
				}
			}

			if (array[index].value > array[index * 2].value) {
				switchElements(index, index * 2);
				index *= 2;
			} else {
				break;
			}
		}
	}

	public boolean isEmpty() {
		return accommodated == 0;
	}

}
