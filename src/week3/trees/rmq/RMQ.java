package week3.trees.rmq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RMQ {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		String[] array = reader.readLine().split(" ");
		int vectorLength = Integer.parseInt(array[0]);
		int numberOfCommands = Integer.parseInt(array[1]);

		int[] vector = new int[vectorLength];
		String[] numbersArray = reader.readLine().split(" ");

		for (int i = 0; i < numbersArray.length; i++) {
			vector[i] = Integer.parseInt(numbersArray[i]);
		}

		RMQ rmq = new RMQ(vector);

		for (int i = 0; i < numberOfCommands; i++) {
			String[] commandParts = reader.readLine().split(" ");
			String command = commandParts[0];
			int firstParam = Integer.parseInt(commandParts[1]);
			int secondParam = Integer.parseInt(commandParts[2]);

			if (command.equals("set")) {
				rmq.set(firstParam, secondParam);
			} else if (command.equals("min")) {
				System.out.println(rmq.min(firstParam, secondParam));
			}
		}
	}

	private int[] array;
	private final int originalLength;

	public RMQ(int[] input) {
		int n = input.length;
		while ((n & (n - 1)) != 0) {
			n++;
		}

		originalLength = n;
		array = new int[n * 2];

		for (int i = 0; i < input.length; i++) {
			array[originalLength + i] = input[i];
		}

		for (int i = input.length; i < originalLength - input.length; i++) {
			array[originalLength + i] = Integer.MAX_VALUE;
		}

		for (int i = originalLength - 1; i > 0; i--) {
			array[i] = Math.min(array[2 * i], array[2 * i + 1]);
		}
	}

	// sets the value at index
	public void set(int index, int value) {
		array[index + originalLength] = value;
		int parentElementIndex = (index + originalLength) / 2;
		while (parentElementIndex > 0) {
			array[parentElementIndex] = Math.min(array[parentElementIndex * 2],
					array[parentElementIndex * 2 + 1]);
			parentElementIndex /= 2;
		}
	}

	// returns the minimum value in a range
	public int min(int startIndex, int endIndex) {
		startIndex += originalLength;
		int min = array[startIndex];
		endIndex += originalLength;

		while (startIndex < endIndex) {
			if (startIndex % 2 == 1) {
				// right child
				min = Math.min(min, array[startIndex]);
				startIndex++;
			} else {
				// left child
				min = Math.min(min, array[startIndex / 2]);
			}

			startIndex /= 2;

			if (endIndex % 2 == 1) {
				// right child
				min = Math.min(min, array[endIndex / 2]);
			} else {
				// left child
				min = Math.min(min, array[endIndex]);
				endIndex--;
			}

			endIndex /= 2;
		}

		return min;
	}

}
