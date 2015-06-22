package week3.trees.bst;

public class BST {

	// Checks if a binary tree is a binary search tree.
	public static boolean isBST(Node root) {
		if (root.left == null && root.right == null) {
			return true;
		}

		if (root.left != null && root.value < root.left.value) {
			return false;
		}

		if (root.right != null && root.value >= root.right.value) {
			return false;
		}

		return (root.left == null || isBST(root.left))
				&& (root.right == null || isBST(root.right));
	}

}
