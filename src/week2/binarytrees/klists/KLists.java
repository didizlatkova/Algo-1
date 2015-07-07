package week2.binarytrees.klists;

import java.util.ArrayList;
import java.util.List;

public class KLists {
	
	public static void main(String[] args) {
		// 3 -> 5 -> 7 -> 9,	
		LinkedList list1 = new LinkedList();
		list1.add(3);
		list1.add(5);
		list1.add(7);
		list1.add(9);
		
		// 2 -> 4 -> 6,
		LinkedList list2 = new LinkedList();
		list2.add(2);
		list2.add(4);
		list2.add(6);
		
		// 0 -> 1 -> 8 -> 10
		LinkedList list3 = new LinkedList();
		list3.add(0);
		list3.add(1);
		list3.add(8);
		list3.add(10);
				
		List<Node> lists = new ArrayList<Node>();
		lists.add(list1.getFirstNode());
		lists.add(list2.getFirstNode());
		lists.add(list3.getFirstNode());
		
		Node result = KLists.merge(lists);
		
		System.out.println(result.value);
		while (result.nextNode != null) {
			result = result.nextNode;
			System.out.println(result.value);			
		}
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
		private int size = 0;

		public Node getFirstNode() {
			return this.first;
		}

		public void add(int value) {
			size++;
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
