package week3.trees.rmq;

public class CommandParser {

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
