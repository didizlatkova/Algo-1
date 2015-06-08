package week1.queue;

import week1.linkedlist.LinkedList;

public class LinkedQueue implements QueueInterface {

	private LinkedList list;

	public LinkedQueue() {
		list = new LinkedList();
	}

	@Override
	public void push(int value) {
		list.add(value);
	}

	@Override
	public int pop() {
		int first = list.getFirst();
		list.removeFirst();
		return first;
	}

	@Override
	public int peek() {
		return list.getFirst();
	}

	@Override
	public int size() {
		return list.size();
	}

}
