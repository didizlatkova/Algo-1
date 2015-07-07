package week2.searching.birthdayranges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BirthdayRange {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		String[] input = reader.readLine().split(" ");
		int numberOfPeople = Integer.parseInt(input[0]);
		int numberOfRanges = Integer.parseInt(input[1]);
		String[] birthdaysInput = reader.readLine().split(" ");
		int[] birthdays = new int[numberOfPeople];
		for (int i = 0; i < numberOfPeople; i++) {
			birthdays[i] = Integer.parseInt(birthdaysInput[i]);
		}

		int[][] ranges = new int[numberOfRanges][2];
		String[] range = null;
		for (int i = 0; i < numberOfRanges; i++) {
			range = reader.readLine().split(" ");
			ranges[i][0] = Integer.parseInt(range[0]);
			ranges[i][1] = Integer.parseInt(range[1]);
		}

		reader.close();
		birthdaysCount(birthdays, ranges);
	}

	public static void birthdaysCount(int[] birthdays, int[][] ranges) {
		int maxNumber = 365;

		int[] counts = new int[maxNumber + 1];

		for (int i = 0; i < birthdays.length; i++) {
			counts[birthdays[i]]++;
		}

		for (int i = 1; i < counts.length; i++) {
			counts[i] += counts[i - 1];
		}

		for (int i = 0; i < ranges.length; i++) {
			System.out.println(counts[ranges[i][1]]
					- (ranges[i][0] == 0 ? 0 : counts[ranges[i][0] - 1]));
		}
	}

}
