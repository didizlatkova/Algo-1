package week2.searching.birthdayranges;

public class BirthdayRange {

	public static int[] birthdaysCount(int[] birthdays, int[][] ranges) {
		int[] birthdaysCount = new int[ranges.length];
		int maxNumber = 365;

		int[] counts = new int[maxNumber + 1];

		for (int i = 0; i < birthdays.length; i++) {
			counts[birthdays[i]]++;
		}

		for (int i = 1; i < counts.length; i++) {
			counts[i] += counts[i - 1];
		}

		for (int i = 0; i < ranges.length; i++) {
			birthdaysCount[i] = counts[ranges[i][1]]
					- (ranges[i][0] == 0 ? 0 : counts[ranges[i][0] - 1]);
		}

		return birthdaysCount;
	}
	
}
