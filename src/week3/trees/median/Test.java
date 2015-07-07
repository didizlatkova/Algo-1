package week3.trees.median;

import java.util.Random;

public class Test {

	public static void main(String[] args) {

		int n = 1000;
		Random rnd = new Random();
		for (int i = 0; i < n; i++) {
			System.out.println(i + ": " + Median.insert(rnd.nextInt(1000)));
		}
	}

}
