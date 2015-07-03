package week2.searching.roots;

import java.util.Scanner;

public class MyMath {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		sc.close();
		System.out
				.println(String.format("%.5f", MyMath.sqrt(n)));
	}

	public static double sqrt(int number) {
		return binary(number, 1, number, 0.0000001);
	}

	private static double binary(int number, double left, double right,
			double interval) {
		if (number < 0) {
			return -1;
		}

		if (number == 0) {
			return 0;
		}

		while (Math.abs(left - right) > interval) {
			double middle = left + (right - left) / 2;

			double squaredMiddleValue = middle * middle;

			if (squaredMiddleValue > number) {
				return binary(number, left, middle - interval, interval);
			} else {
				return binary(number, middle + interval, right, interval);
			}
		}

		return left + (right - left) / 2;
	}

}
