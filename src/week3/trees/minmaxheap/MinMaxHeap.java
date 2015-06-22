package week3.trees.minmaxheap;

import week3.trees.bst.Node;

public class MinMaxHeap {

	// Checks if a binary tree is a min/max heap.
	public static boolean isMinMax(Node root) {
		return isMinMax(root, "asc");
	}

	private static boolean isMinMax(Node node, String sortDirection) {
		if (node == null) {
			return true;
		}

		if (sortDirection.equals("asc")) {
			if ((node.left != null && node.value > node.left.value)
					|| (node.right != null && node.value > node.right.value)) {
				return false;
			}
			sortDirection = "desc";
		} else {
			if ((node.left != null && node.value < node.left.value)
					|| (node.right != null && node.value < node.right.value)) {
				return false;
			}
			sortDirection = "asc";
		}

		return isMinMax(node.left, sortDirection) && isMinMax(node.right, sortDirection);
	}

}
