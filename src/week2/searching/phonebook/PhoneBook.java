package week2.searching.phonebook;

import java.util.Arrays;
import java.util.Scanner;

public class PhoneBook {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] input = sc.nextLine().split(" ");
		int bookLength = Integer.parseInt(input[0]);
		int queryLength = Integer.parseInt(input[1]);
		Contact[] phoneBook = new Contact[bookLength];
		for (int i = 0; i < bookLength; i++) {
			String[] contact = sc.nextLine().split(" ");
			phoneBook[i] = new Contact(contact[1], Integer.parseInt(contact[0]));
		}

		int[] numbers = new int[queryLength];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = Integer.parseInt(sc.nextLine());
		}

		String[] names = lookupNames(phoneBook, numbers);
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}

		sc.close();
	}

	public static String[] lookupNames(Contact[] book, int[] numbers) {
		//Contact[] phoneBook = book.clone();
		Arrays.sort(book);
		String[] names = new String[numbers.length];

		for (int i = 0; i < numbers.length; i++) {
			names[i] = binarySearch(book, numbers[i]);
		}

		return names;
	}

	private static String binarySearch(Contact[] array, int number) {
		int left = 0;
		int right = array.length - 1;

		while (left <= right) {
			int middle = left + (right - left) / 2;
			int middleValue = array[middle].phone;

			if (middleValue > number) {
				right = middle - 1;
			} else if (middleValue < number) {
				left = middle + 1;
			} else {
				return array[middle].name;
			}
		}

		return "not found";
	}

	private static class Contact implements Comparable<Contact> {

		public String name;
		public int phone;

		public Contact(String name, int phone) {
			this.name = name;
			this.phone = phone;
		}

		@Override
		public int compareTo(Contact o) {
			if (this.phone == o.phone) {
				return 0;
			}

			return this.phone > o.phone ? 1 : -1;
		}

	}
}