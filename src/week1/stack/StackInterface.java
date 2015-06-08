package week1.stack;

public interface StackInterface {

	  // Adds value to the end of the Stack.
	  // Complexity: O(1)
	  void push(int value);

	  // Returns value from the end of the Stack and removes it.
	  // Complexity: O(n)
	  int pop();

	  // Returns value from the end of the Stack without removing it.
	  // Complexity: O(n)
	  int peek();

	  // Returns the number of elements in the Stack.
	  // Complexity: O(1)
	  int size();
	  
}
