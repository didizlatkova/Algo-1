package week1.queue;

public class Test {

	public static void main(String[] args) {
		Queue queue = new Queue();
		queue.push(2);
		queue.push(3);
		System.out.println(queue.pop());
		System.out.println(queue.peek());
		System.out.println(queue.pop());
		System.out.println(queue.size());
		
		LinkedQueue queue2 = new LinkedQueue();
		queue2.push(2);
		queue2.push(3);
		System.out.println(queue2.pop());
		System.out.println(queue2.peek());
		System.out.println(queue2.pop());
		System.out.println(queue2.size());
	}

}
