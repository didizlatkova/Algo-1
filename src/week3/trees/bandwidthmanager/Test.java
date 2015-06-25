package week3.trees.bandwidthmanager;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		while (!input.equals("end")) {
			CommandParser.parse(input);
			input = sc.nextLine();
		}

		sc.close();
	}

}
