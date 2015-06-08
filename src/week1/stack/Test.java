package week1.stack;

public class Test {

	public static void main(String[] args) {
		Stack stack  = new Stack();
		
		stack.push(0);
		stack.push(1);
		stack.push(2);
		
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}

}
