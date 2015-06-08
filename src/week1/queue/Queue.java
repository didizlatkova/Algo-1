package week1.queue;

import week1.vector.Vector;

public class Queue implements QueueInterface {

	private Vector list;
	
	public Queue(){
		list = new Vector(4);
	}
	
	@Override
	public void push(int value) {
		list.add(value);
	}

	@Override
	public int pop() {
		int first = list.get(0);
		list.remove(0);
		return first;
	}

	@Override
	public int peek() {
		return list.get(0);
	}

	@Override
	public int size() {
		return list.size();
	}

}
