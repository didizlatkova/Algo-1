package week3.trees.minmaxheap;

import week3.trees.bst.Node;

public class Test {

	public static void main(String[] args) {
		Node node = new Node(2);
		node.left = new Node(13);
		node.left.left = new Node(5);
		node.left.right = new Node(7);
		node.right = new Node(14);
		node.right.right = new Node(9);
		node.right.right.right = new Node(10);
		
		System.out.println(MinMaxHeap.isMinMax(node));
	}

}
