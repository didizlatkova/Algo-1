package week1.queue;

public class Test {

	public static void main(String[] args) {
		Queue queue = new Queue();
		queue.push(2);
		queue.push(3);
		System.out.println(queue.pop());
		System.out.println(queue.peek());
		System.out.println(queue.pop());
	}

}
