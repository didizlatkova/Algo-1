package week3.trees.bst;

public class BST {

	// Checks if a binary tree is a binary search tree.
	public static boolean isBST(Node root) {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static boolean isBST(Node node, int min, int max) {
		if (node == null) {
			return true;
		}

		if (node.value < min || node.value >= max) {
			return false;
		}

		return isBST(node.left, min, node.value)
				&& isBST(node.right, node.value, max);
	}

}
