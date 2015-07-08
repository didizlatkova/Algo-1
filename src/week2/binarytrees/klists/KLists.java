package week2.binarytrees.klists;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class KLists {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		
		int k = Integer.parseInt(reader.readLine());
		List<Node> lists = new ArrayList<Node>();
		
		for (int i = 0; i < k; i++) {
			LinkedList list = new LinkedList();
			String[] input = reader.readLine().split(" ");
			for (int j = 0; j < input.length - 1; j++) {
				list.add(Integer.parseInt(input[j]));
			}
			lists.add(list.getFirstNode());
		}
		
		Node result = KLists.merge(lists);
		StringBuilder sb = new StringBuilder();
		
		sb.append(result.value + " ");
		while (result.nextNode.nextNode != null) {
			result = result.nextNode;
			sb.append(result.value + " ");			
		}
		
		sb.append(result.nextNode.value);
		System.out.println(sb.toString());
	}

	// Merge K sorted lists.
	public static Node merge(List<Node> lists) {
		Node sortedNodes;
		Node currentNode;
		
		HeapNode heap = new HeapNode(lists.size());
		for (int i = 0; i < lists.size(); i++) {
			heap.insert(lists.get(i));
		}
		currentNode = heap.getMin();
		heap.removeMin();
		sortedNodes = new Node(currentNode.value);
		Node firstNode = sortedNodes;
				
		while (currentNode.nextNode != null) {
			heap.insert(currentNode.nextNode);
			currentNode = heap.getMin();
			heap.removeMin();
			sortedNodes.nextNode = new Node(currentNode.value);
			sortedNodes = sortedNodes.nextNode;
			
			while (currentNode.nextNode == null && !heap.isEmpty()) {
				currentNode = heap.getMin();
				heap.removeMin();
				sortedNodes.nextNode = new Node(currentNode.value);
				sortedNodes = sortedNodes.nextNode;
			}
		}		
		
		return firstNode;
	}
	
	private static class HeapNode {

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

	private static class Node {

		public int value;

		public Node nextNode;

		public Node(int value) {
			this.value = value;
		}

	}
	
	private static class LinkedList {

		private Node first;

		public Node getFirstNode() {
			return this.first;
		}

		public void add(int value) {
			if (first == null) {
				first = new Node(value);
				return;
			}

			Node node = first;
			while (node.nextNode != null) {
				node = node.nextNode;
			}

			node.nextNode = new Node(value);
		}
	}
	
}
