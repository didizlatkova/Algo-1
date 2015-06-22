package week3.trees.phonebook;

public class PhoneBook {

	private ContactNode node;

	// inserts a new contact
	public void insert(Contact contact) {
		if (node == null) {
			node = new ContactNode(contact);
			return;
		}

		ContactNode nextNode = node;
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
		ContactNode nextNode = node;
		while (true) {
			if (name.compareTo(nextNode.value.name) < 0) {
				if (nextNode.left != null) {
					nextNode = nextNode.left;
				} else {
					System.out.println("NOT FOUND!");
					break;
				}
			} else if (name.compareTo(nextNode.value.name) > 0) {
				if (nextNode.right != null) {
					nextNode = nextNode.right;
				} else {
					System.out.println("NOT FOUND!");
					break;
				}
			} else {
				System.out.println(nextNode.value.number);
				break;
			}
		}
	}

	// list all records in an alphabetical order
	public void list() {

	}

	private void traverse(ContactNode node) {
		
	}

	// remove a record for a given name
	public void remove(String name) {

	}

}
