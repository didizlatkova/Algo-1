package week2.searching.birthdayranges;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		System.out.println(test());
	}

	private static boolean test() {
		int[] birthdays = new int[100];
		int[][] ranges = new int[50][2];

		Random rnd = new Random();

		for (int i = 0; i < birthdays.length; i++) {
			birthdays[i] = rnd.nextInt(366);
		}

		for (int i = 0; i < ranges.length; i++) {
			ranges[i][0] = rnd.nextInt(366);
			ranges[i][1] = ranges[i][0] + rnd.nextInt(366 - ranges[i][0]);
		}

//		int[] result = BirthdayRange.birthdaysCount(birthdays, ranges);
//
//		for (int i = 0; i < ranges.length; i++) {
//			int count = 0;
//			for (int j = 0; j < birthdays.length; j++) {
//				if (birthdays[j] <= ranges[i][1]
//						&& birthdays[j] >= ranges[i][0]) {
//					count++;
//				}
//			}
//			if (result[i] != count) {
//				return false;
//			}
//		}
		
		return true;
	}
	
}
