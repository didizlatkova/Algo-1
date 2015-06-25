package week3.trees.bandwidthmanager;

public class PriorityQueue {

	private QueueElement[] array;
	private int accommodated = 0;

	public PriorityQueue(int capacity) {
		array = new QueueElement[capacity + 1];
	}

	public String getMin() {
		if (accommodated == 0) {
			throw new IndexOutOfBoundsException();
		}

		return array[1].value;
	}

	public void insert(String element, int priority) {
		if (accommodated == array.length) {
			throw new IndexOutOfBoundsException();
		}

		accommodated++;
		int index = accommodated;
		array[index] = new QueueElement(element, priority);

		while (index / 2 > 0) {
			if (array[index].priority < array[index / 2].priority) {
				switchElements(index, index / 2);
				index /= 2;
			} else {
				break;
			}
		}
	}

	private void switchElements(int i, int j) {
		QueueElement save = array[i];
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
		array[accommodated] = null;
		accommodated--;

		int index = 1;
		while (index * 2 <= accommodated) {
			if (index * 2 + 1 <= accommodated) {
				if (array[index * 2].priority > array[index * 2 + 1].priority
						&& array[index].priority > array[index * 2 + 1].priority) {
					switchElements(index, index * 2 + 1);
					index = index * 2 + 1;
					continue;
				}
			}

			if (array[index].priority > array[index * 2].priority) {
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

	public int size() {
		return accommodated;
	}

}
