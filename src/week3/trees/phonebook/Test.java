package week3.trees.phonebook;

public class Test {

	public static void main(String[] args) {
		PhoneBook book = new PhoneBook();
		book.insert(new Contact("Gosho", 5));
		book.insert(new Contact("Didi", 3));
		book.insert(new Contact("Kiko", 7));
		book.insert(new Contact("Ani", 1));
		book.insert(new Contact("Emo", 4));
		book.insert(new Contact("Ivan", 6));
		book.insert(new Contact("Misho", 8));
		book.insert(new Contact("Bobi", 2));
		
		book.remove("Bobi");
		
		book.list();
	}

}
