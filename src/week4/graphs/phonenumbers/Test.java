package week4.graphs.phonenumbers;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numberOfFriends = Integer.parseInt(sc.nextLine());
		String[] phoneNumbers = sc.nextLine().split(" ");
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < phoneNumbers.length; i++) {
			map.put(Integer.parseInt(phoneNumbers[i]), i);
		}

		boolean graph[][] = new boolean[numberOfFriends][numberOfFriends];

		for (int i = 0; i < numberOfFriends; i++) {
			String[] phoneNumbersOfFriends = sc.nextLine().split(" ");
			for (int j = 1; j <= Integer.parseInt(phoneNumbersOfFriends[0]); j++) {
				graph[i][map.get(Integer.parseInt(phoneNumbersOfFriends[j]))] = true;
			}
		}

		PhoneNumbers numbers = new PhoneNumbers();
		System.out.println(numbers.getMinCalls(graph));

		sc.close();
	}

}
