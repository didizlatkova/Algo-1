package week3.trees.birthdayranges;

public class CommandParser {

	public static void parse(String command) {
		if (command.startsWith("add")) {
			add(command);
		} else if (command.startsWith("remove")) {
			remove(command);
		} else if (command.startsWith("count")) {
			count(command);
		} else {
			throw new IllegalArgumentException("Invalid command");
		}
	}

	private static void add(String command) {
		String[] contactArray = command.split(" ");
		if (contactArray.length != 3) {
			throw new IllegalArgumentException("Invalid command");
		}
		
		int day = 0;
		int numberOfPeople = 0;
		try {
			day = Integer.parseInt(contactArray[1]);
			numberOfPeople = Integer.parseInt(contactArray[2]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid command");
		}
		
		BirthdayRanges.add(day, numberOfPeople);
	}

	private static void remove(String command) {
		String[] array = command.split(" ");
		if (array.length != 2) {
			throw new IllegalArgumentException("Invalid command");
		}

		String name = array[1];
	}

	private static void count(String command) {
		String[] array = command.split(" ");
		if (array.length != 2) {
			throw new IllegalArgumentException("Invalid command");
		}

		String name = array[1];
	}

}
