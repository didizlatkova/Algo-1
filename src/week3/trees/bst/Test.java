package week3.trees.bst;

public class Test {

	public static void main(String[] args) {
		Node node = new Node(12);
		node.left = new Node(4);
		node.left.left = new Node(2);
		node.left.right = new Node(5);
		node.right = new Node(19);
		node.right.right = new Node(20);
		node.right.right.right = new Node(21);
		
		System.out.println(BST.isBST(node));
	}

}
