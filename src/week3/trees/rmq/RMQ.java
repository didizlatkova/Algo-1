package week3.trees.rmq;

import java.util.Scanner;

public class RMQ {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String firstLine = sc.nextLine();

		String[] array = firstLine.split(" ");
		if (array.length != 2) {
			sc.close();
			throw new IllegalArgumentException("Invalid command");
		}

		int vectorLength = 0;
		int numberOfCommands = 0;

		try {
			vectorLength = Integer.parseInt(array[0]);
			numberOfCommands = Integer.parseInt(array[1]);
		} catch (NumberFormatException e) {
			sc.close();
			throw new IllegalArgumentException("Invalid command");
		}

		int[] vector = new int[vectorLength];
		String vectorNumbers = sc.nextLine();
		String[] numbersArray = vectorNumbers.split(" ");

		try {
			for (int i = 0; i < numbersArray.length; i++) {
				vector[i] = Integer.parseInt(numbersArray[i]);
			}
		} catch (NumberFormatException e) {
			sc.close();
			throw new IllegalArgumentException("Invalid command");
		}

		CommandParser parser = new CommandParser(vector);

		for (int i = 0; i < numberOfCommands; i++) {
			parser.parse(sc.nextLine());
		}

		sc.close();
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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 1; i < array.length; i++) {
			sb.append(array[i] + ", ");
		}
		sb.append("]");

		return sb.toString();
	}

	private static class CommandParser {

		private RMQ rmq;

		public CommandParser(int[] array) {
			rmq = new RMQ(array);
		}

		public void parse(String command) {
			if (command.startsWith("set")) {
				set(command);
			} else if (command.startsWith("min")) {
				min(command);
			} else {
				throw new IllegalArgumentException("Invalid command");
			}
		}

		private void set(String command) {
			String[] contactArray = command.split(" ");
			if (contactArray.length != 3) {
				throw new IllegalArgumentException("Invalid command");
			}

			int index = 0;
			int value = 0;
			try {
				index = Integer.parseInt(contactArray[1]);
				value = Integer.parseInt(contactArray[2]);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Invalid command");
			}

			if (index < 0) {
				throw new IllegalArgumentException("Invalid command");
			}

			rmq.set(index, value);
		}

		private void min(String command) {
			String[] contactArray = command.split(" ");
			if (contactArray.length != 3) {
				throw new IllegalArgumentException("Invalid command");
			}

			int startIndex = 0;
			int endIndex = 0;
			try {
				startIndex = Integer.parseInt(contactArray[1]);
				endIndex = Integer.parseInt(contactArray[2]);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Invalid command");
			}

			System.out.println(rmq.min(startIndex, endIndex));
		}

	}

}
