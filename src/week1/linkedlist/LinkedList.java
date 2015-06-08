package week1.linkedlist;

public class LinkedList {

	private Node first;
	private int size = 0;

	public int getFirst() {
		if (first == null) {
			throw new IndexOutOfBoundsException();
		}

		return first.value;
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

	public int getLast() {
		if (first == null) {
			throw new IndexOutOfBoundsException();
		}

		Node node = first;
		while (node.nextNode != null) {
			node = node.nextNode;
		}

		return node.value;
	}

	public void removeFirst(){
		first = first.nextNode;
	}
	
	public void removeLast() {
		if (first == null) {
			throw new IndexOutOfBoundsException();
		}

		size--;
		if (first.nextNode == null) {
			first = null;
			return;
		}

		Node prevNode = first;
		Node node = first.nextNode;

		while (node.nextNode != null) {
			prevNode = node;
			node = node.nextNode;
		}

		prevNode.nextNode = null;
	}

	public int size() {
		return size;
	}
}
