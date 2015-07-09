package week3.trees.phonebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PhoneBook {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		int n = Integer.parseInt(reader.readLine());
		String input;

		for (int i = 0; i < n; i++) {
			input = reader.readLine();
			CommandParser.parse(input);
		}
	}

	private ContactNode firstNode;

	// inserts a new contact
	public void insert(Contact contact) {
		if (firstNode == null) {
			firstNode = new ContactNode(contact);
			return;
		}

		ContactNode nextNode = firstNode;
		while (true) {
			if (contact.name.compareTo(nextNode.value.name) < 0) {
				if (nextNode.left != null) {
					nextNode = nextNode.left;
				} else {
					nextNode.left = new ContactNode(contact);
					break;
				}
			} else if (contact.name.compareTo(nextNode.value.name) > 0) {
				if (nextNode.right != null) {
					nextNode = nextNode.right;
				} else {
					nextNode.right = new ContactNode(contact);
					break;
				}
			} else {
				nextNode.value.number = contact.number;
				break;
			}
		}
	}

	// lookup a name and print its phone number
	public void lookup(String name) {
		ContactNode found = lookupNode(name);
		if (found != null) {
			System.out.println(found.value.number);
		} else {
			System.out.println("NOT FOUND!");
		}
	}

	private ContactNode lookupNode(String name) {
		if (firstNode == null) {
			return null;
		}

		ContactNode nextNode = firstNode;
		while (true) {
			if (name.compareTo(nextNode.value.name) < 0) {
				if (nextNode.left != null) {
					nextNode = nextNode.left;
				} else {
					return null;
				}
			} else if (name.compareTo(nextNode.value.name) > 0) {
				if (nextNode.right != null) {
					nextNode = nextNode.right;
				} else {
					return null;
				}
			} else {
				return nextNode;
			}
		}
	}

	// list all records in an alphabetical order
	public void list() {
		traverse(firstNode);
	}

	private void traverse(ContactNode node) {
		if (node == null) {
			return;
		}

		traverse(node.left);
		System.out.println(node.value.name + " " + node.value.number);
		traverse(node.right);
	}

	// remove a record for a given name
	public void remove(String name) {
		ContactNode nodeToRemove = lookupNode(name);
		if (nodeToRemove != null) {
			remove(nodeToRemove);
		}
	}

	private void remove(ContactNode node) {
		if (node.left == null && node.right == null) {
			this.removeFromParent(node);
		} else if (node.left != null && node.right == null) {
			node.value = node.left.value;
			node.left = node.left.left;
		} else if (node.right != null && node.left == null) {
			node.value = node.right.value;
			node.right = node.right.right;
		} else {
			ContactNode replacement = leftMostChild(node.right);
			remove(replacement);
			node.value = replacement.value;
		}
	}

	private void removeFromParent(ContactNode node) {
		ContactNode nextNode = firstNode;
		while (true) {
			if (node.value.name.compareTo(nextNode.value.name) < 0) {
				if (nextNode.left != null) {
					if (node.value.name.compareTo(nextNode.left.value.name) == 0) {
						nextNode.left = null;
						return;
					}
					nextNode = nextNode.left;
				}
			} else if (node.value.name.compareTo(nextNode.value.name) > 0) {
				if (nextNode.right != null) {
					if (node.value.name.compareTo(nextNode.right.value.name) == 0) {
						nextNode.right = null;
						return;
					}
					nextNode = nextNode.right;
				}
			}
		}
	}

	private ContactNode leftMostChild(ContactNode node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	private static class CommandParser {

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
			int number = Integer.parseInt(contactArray[1]);
			String name = contactArray[2];
			Contact contact = new Contact(name, number);
			phoneBook.insert(contact);
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

	private static class Contact {

		public String name;
		public int number;

		public Contact(String name, int number) {
			this.name = name;
			this.number = number;
		}

	}

	private static class ContactNode {

		public Contact value;
		public ContactNode left;
		public ContactNode right;

		public ContactNode(Contact value) {
			this.value = value;
		}

	}

}
