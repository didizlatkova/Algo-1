package week3.trees.birthdayranges;

public class CommandParser {

	private BirthdayRanges ranges;

	public CommandParser(int[] array) {
		ranges = new BirthdayRanges(array);
	}

	public void parse(String command) {
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

	private void add(String command) {
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

		if (numberOfPeople < 0) {
			throw new IllegalArgumentException("Invalid command");
		}

		ranges.add(day, numberOfPeople);
	}

	private void remove(String command) {
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

		if (numberOfPeople < 0) {
			throw new IllegalArgumentException("Invalid command");
		}

		ranges.remove(day, numberOfPeople);
	}

	private void count(String command) {
		String[] contactArray = command.split(" ");
		if (contactArray.length != 3) {
			throw new IllegalArgumentException("Invalid command");
		}

		int startDay = 0;
		int endDay = 0;
		try {
			startDay = Integer.parseInt(contactArray[1]);
			endDay = Integer.parseInt(contactArray[2]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid command");
		}

		if (startDay > endDay) {
			throw new IllegalArgumentException("Invalid command");
		}

		System.out.println(ranges.count(startDay, endDay + 1));
	}

}
