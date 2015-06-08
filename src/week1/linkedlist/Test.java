package week1.linkedlist;

public class Test {

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.removeFirst();
		System.out.println(list.getFirst());
		System.out.println(list.getLast());
		list.removeLast();
		System.out.println(list.getLast());
		System.out.println(list.size());
	}

}
