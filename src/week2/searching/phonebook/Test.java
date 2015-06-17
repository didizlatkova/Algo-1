package week2.searching.phonebook;

import java.util.Random;

public class Test {

	static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();

	private static String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			sb.append(ALPHABET.charAt(rnd.nextInt(ALPHABET.length())));
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(test());
	}

	private static boolean test() {
		Contact[] phoneBook = new Contact[50];
		int[] numbers = new int[50];

		for (int i = 0; i < phoneBook.length; i++) {
			int number = rnd.nextInt(2147483647);
			Contact contact = new Contact(randomString(10), number);
			phoneBook[i] = contact;
			numbers[i] = number;
		}

		String[] result = PhoneBook.lookupNames(phoneBook, numbers);

		for (int i = 0; i < result.length; i++) {
			if (!result[i].equals(phoneBook[i].name)) {
				return false;
			}
		}

		return true;
	}

}
