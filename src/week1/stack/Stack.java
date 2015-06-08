package week1.stack;

import week1.queue.Queue;

public class Stack implements StackInterface {

	private Queue queue;
	private Queue newQueue;

	private void fillNewQueue() {
		int size = size();
		for (int i = 0; i < size - 1; i++) {
			newQueue.push(queue.pop());
		}
	}

	public Stack() {
		queue = new Queue();
		newQueue = new Queue();
	}

	@Override
	public void push(int value) {
		queue.push(value);
	}

	@Override
	public int pop() {
		fillNewQueue();

		int last = queue.pop();
		queue = newQueue;
		return last;
	}

	@Override
	public int peek() {
		fillNewQueue();

		int last = queue.peek();
		newQueue.push(queue.pop());
		queue = newQueue;
		return last;
	}

	@Override
	public int size() {
		return queue.size();
	}

}
