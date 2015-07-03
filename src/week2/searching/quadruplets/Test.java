package week2.searching.quadruplets;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		System.out.println(test());
	}

	private static boolean test() {
		Random rnd = new Random();

		int n = 1;

		int[] a = new int[n];
		int[] b = new int[n];
		int[] c = new int[n];
		int[] d = new int[n];

		for (int i = 0; i < n; i++) {
			a[i] = -10 + rnd.nextInt(20);
			b[i] = -10 + rnd.nextInt(20);
			c[i] = -10 + rnd.nextInt(20);
			d[i] = -10 + rnd.nextInt(20);
		}

		long start = System.currentTimeMillis();
		long result = Quadruplets.zeroQuadrupletsCount(a, b, c, d);
		System.out.println(System.currentTimeMillis() - start);
		// int count = 0;
		// for (int i = 0; i < a.length; i++) {
		// for (int j = 0; j < b.length; j++) {
		// for (int l = 0; l < c.length; l++) {
		// for (int k = 0; k < d.length; k++) {
		// if (a[i] + b[j] + c[l] + d[k] == 0) {
		// count ++;
		// }
		// }
		// }
		// }
		// }

		return true;
	}

}
