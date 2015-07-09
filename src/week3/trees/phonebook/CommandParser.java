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
		}
	}

	private static void insert(String command) {
		String[] contactArray = command.split(" ");
		int number =  Integer.parseInt(contactArray[1]);
		String name = contactArray[2];
		Contact contact = new Contact(name, number);
		//phoneBook.insert(contact);
	}

	private static void lookup(String command) {
		String[] array = command.split(" ");
		String name = array[1];
		phoneBook.lookup(name);
	}

	private static void remove(String command) {
		String[] array = command.split(" ");
		String name = array[1];
		phoneBook.remove(name);
	}

}
