package week3.trees.phonebook;

public class CommandParser {

	private static PhoneBook phoneBook = new PhoneBook();

	public static void parse(String command) {
		if (command.startsWith("insert")) {
			insert(command);
		} else if (command.startsWith("lookup")) {
			lookup(command);
		} else if (command.startsWith("remove")) {
			remove(command);
		} else if (command.equals("list")) {
			phoneBook.list();
		} else {
			throw new IllegalArgumentException("Invalid command");
		}
	}

	private static void insert(String command) {
		String[] contactArray = command.split(" ");
		if (contactArray.length != 3) {
			throw new IllegalArgumentException("Invalid command");
		}

		int number = 0;
		try {
			number = Integer.parseInt(contactArray[1]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid command");
		}

		String name = contactArray[2];

		Contact contact = new Contact(name, number);
		phoneBook.insert(contact);
	}

	private static void lookup(String command) {
		String[] array = command.split(" ");
		if (array.length != 2) {
			throw new IllegalArgumentException("Invalid command");
		}

		String name = array[1];
		phoneBook.lookup(name);
	}

	private static void remove(String command) {
		String[] array = command.split(" ");
		if (array.length != 2) {
			throw new IllegalArgumentException("Invalid command");
		}

		String name = array[1];
		phoneBook.remove(name);
	}

}
