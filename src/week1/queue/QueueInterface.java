package week1.queue;

public interface QueueInterface {

	// Adds value to the end of the Queue.
	// Complexity: O(1)
	void push(int value);

	// Returns value from the front of the Queue and removes it.
	// Complexity: O(1)
	int pop();

	// Returns value from the front of the Queue without removing it.
	// Complexity: O(1)
	int peek();

	// Returns the number of elements in the Queue.
	// Complexity: O(1)
	int size();

}
