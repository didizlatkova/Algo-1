package week2.binarytrees.klists;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class KLists {

	public static void main(String[] args) throws NumberFormatException,
			IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		int k = Integer.parseInt(reader.readLine());
		List<Node> lists = new ArrayList<Node>();

		LinkedList list;
		String[] input;
		HeapNode heap = new HeapNode(k);
		for (int i = 0; i < k; i++) {
			list = new LinkedList();
			input = reader.readLine().split(" ");
			for (int j = 0; j < input.length - 1; j++) {
				list.add(Integer.parseInt(input[j]));
			}
			heap.insert(list.getFirstNode());
			lists.add(list.getFirstNode());
		}

		List<Integer> result = KLists.merge(lists, heap);

		for (int i = 0; i < result.size() - 1; i++) {
			System.out.print(result.get(i) + " ");
		}
		System.out.print(result.get(result.size() - 1));
	}

	// Merge K sorted lists.
	public static List<Integer> merge(List<Node> lists, HeapNode heap) {
		List<Integer> sortedNodes = new ArrayList<Integer>();
		Node currentNode;

		currentNode = heap.getMin();
		heap.removeMin();
		sortedNodes.add(currentNode.value);

		while (!heap.isEmpty()) {
			if (currentNode.nextNode != null) {
				heap.insert(currentNode.nextNode);
			}
			currentNode = heap.getMin();
			heap.removeMin();
			sortedNodes.add(currentNode.value);
		}

		return sortedNodes;
	}

	private static class HeapNode {

		private Node[] array;
		private int accommodated = 0;

		public HeapNode(int capacity) {
			array = new Node[capacity + 1];
		}

		public Node getMin() {
			return array[1];
		}

		public void insert(Node element) {
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

	private static class Node {

		public int value;

		public Node nextNode;

		public Node(int value) {
			this.value = value;
		}

	}

	private static class LinkedList {

		private Node first;
		private Node last;

		public Node getFirstNode() {
			return this.first;
		}

		public void add(int value) {
			if (first == null) {
				first = new Node(value);
				last = first;
				return;
			}

			last.nextNode = new Node(value);
			last = last.nextNode;
		}
	}

}
