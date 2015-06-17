package week2.searching.phonebook;

import java.util.Arrays;

public class PhoneBook {

	public static String[] lookupNames(Contact[] book, int[] numbers) {
		Contact[] phoneBook = book.clone();
		Arrays.sort(phoneBook);
		String[] names = new String[numbers.length];
		
		for (int i = 0; i < numbers.length; i++) {
			names[i] = binarySearch(phoneBook, numbers[i]);
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

}
