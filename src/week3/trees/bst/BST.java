package week3.trees.bst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BST {

	// Checks if a binary tree is a binary search tree.
	public static boolean isBST(Node root) {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static boolean isBST(Node node, int min, int max) {
		if (node == null) {
			return true;
		}

		if (node.value != 0 && (node.value < min || node.value >= max)) {
			return false;
		}

		return isBST(node.left, min, node.value)
				&& isBST(node.right, node.value, max);
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		int n = Integer.parseInt(reader.readLine());
		String[] input = reader.readLine().split(" ");
		int[] list = new int[n];
		for (int i = 0; i < n; i++) {
			list[i] = Integer.parseInt(input[i]);
		}

		boolean result = BST.isBST(getRoot(list));

		if (result) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	private static Node getRoot(int[] list) {
		Queue<Node> queue = new LinkedList<Node>();
		Node rootNode = new Node(list[0]);
		queue.add(rootNode);
		int pointer = 1;
		Node currentNode;

		while (pointer < list.length) {
			Node prevNode = queue.poll();
			// left
			currentNode = new Node(list[pointer]);
			prevNode.left = currentNode;
			queue.add(currentNode);

			pointer++;

			if (pointer < list.length) {
				// right
				currentNode = new Node(list[pointer]);
				prevNode.right = currentNode;
				queue.add(currentNode);
			}

			pointer++;
		}

		return rootNode;
	}

	private static class Node {

		public int value;
		public Node left;
		public Node right;

		public Node(int value) {
			this.value = value;
		}

	}

}
