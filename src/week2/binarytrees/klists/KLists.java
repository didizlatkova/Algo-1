package week2.binarytrees.klists;

import java.util.List;
import week1.linkedlist.Node;

public class KLists {

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
	
}
