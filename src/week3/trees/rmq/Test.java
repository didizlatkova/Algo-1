package week3.trees.rmq;

import java.util.Scanner;

import week3.trees.rmq.CommandParser;

public class Test {

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
}
