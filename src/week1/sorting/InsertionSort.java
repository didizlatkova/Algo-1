package week1.sorting;

import java.util.Vector;

public class InsertionSort implements SortingAlgorithm {

	@Override
	public int[] sort(int[] elements) {
		Vector<Integer> vector = new Vector<Integer>();
		vector.add(elements[0]);

		for (int i = 1; i < elements.length; i++) {
			boolean isLess = false;
			for (int j = 0; j < vector.size(); j++) {
				if (elements[i] <= vector.get(j)) {
					vector.insertElementAt(elements[i], j);
					isLess = true;
					break;
				}
			}
			if (!isLess) {
				vector.insertElementAt(elements[i], vector.size());
			}
		}

		for (int i = 0; i < elements.length; i++) {
			elements[i] = vector.get(i);
		}

		return elements;
	}

}
