package week3.trees.minmaxheap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class MinMaxHeap {

	// Checks if a binary tree is a min/max heap.
	public static boolean isMinMax(Node root) {
		return isMinMax(root, "asc");
	}

	private static class Node {

		public int value;
		public Node left;
		public Node right;

		public Node(int value) {
			this.value = value;
		}

	}

	private static boolean isMinMax(Node node, String sortDirection) {
		if (node == null) {
			return true;
		}

		if (sortDirection.equals("asc")) {
			if (node.value != 0
					&& (node.left != null && node.value > node.left.value)
					|| (node.right != null && node.value > node.right.value)) {
				return false;
			}
			sortDirection = "desc";
		} else {
			if (node.value != 0
					&& (node.left != null && node.value < node.left.value)
					|| (node.right != null && node.value < node.right.value)) {
				return false;
			}
			sortDirection = "asc";
		}

		return isMinMax(node.left, sortDirection)
				&& isMinMax(node.right, sortDirection);
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

		boolean result = isMinMax(getRoot(list));

		if (result) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

}
