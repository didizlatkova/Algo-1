package week1.queue;

import java.util.LinkedList;

public class Queue implements QueueInterface {

	private LinkedList<Integer> list;
	
	public Queue(){
		list = new LinkedList<Integer>();
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
