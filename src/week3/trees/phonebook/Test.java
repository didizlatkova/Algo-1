package week3.trees.phonebook;

public class Test {

	public static void main(String[] args) {
		PhoneBook book = new PhoneBook();
		book.insert(new Contact("Didi", 123));
		book.insert(new Contact("Didito", 1263));
		book.insert(new Contact("Gosho", 13));
		//book.lookup("Didi");
		//book.lookup("Gosho");
		book.insert(new Contact("Gosho", 134));
		book.insert(new Contact("Ani", 134));
		book.insert(new Contact("Misho", 134));
		book.insert(new Contact("Bobi", 134));
		//book.lookup("Gosho");
		//book.lookup("Bla");
		book.list();
	}

}
