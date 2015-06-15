package week2.searching.quadruplets;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		System.out.println(test());
	}

	private static boolean test() {
		Random rnd = new Random();

		int n = 2 + rnd.nextInt(10);

		int[] a = new int[n];
		int[] b = new int[n];
		int[] c = new int[n];
		int[] d = new int[n];
		
		for (int i = 0; i < n; i++) {
			a[i] = -20 + rnd.nextInt(20);
			b[i] = -20 + rnd.nextInt(20);
			c[i] = -20 + rnd.nextInt(20);
			d[i] = -20 + rnd.nextInt(20);
		}

		int result = Quadruplets.zeroQuadrupletsCount(a, b, c, d);
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				for (int l = 0; l < c.length; l++) {
					for (int k = 0; k < d.length; k++) {
						if (a[i] + b[j] + c[l] + d[k] == 0) {
							count ++;
						}
					}
				}
			}
		}		
		
		return result == count;
	}

}
