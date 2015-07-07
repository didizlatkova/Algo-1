package week2.binarytrees.klists;

import java.util.ArrayList;
import java.util.List;

import week1.linkedlist.LinkedList;
import week1.linkedlist.Node;

public class Test {

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
		
//		Node result = KLists.merge(lists);
//		
//		System.out.println(result.value);
//		while (result.nextNode != null) {
//			result = result.nextNode;
//			System.out.println(result.value);			
//		}
	}

}
