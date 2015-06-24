package week3.trees.phonebook;

public class PhoneBook {

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
			node.right =  node.right.right;
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

}
