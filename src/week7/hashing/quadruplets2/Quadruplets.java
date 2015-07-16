package week7.hashing.quadruplets2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class Quadruplets {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		int n = Integer.parseInt(reader.readLine());
		int[] a = new int[n];
		int[] b = new int[n];
		int[] c = new int[n];
		int[] d = new int[n];

		String[] inputA = reader.readLine().split(" ");
		String[] inputB = reader.readLine().split(" ");
		String[] inputC = reader.readLine().split(" ");
		String[] inputD = reader.readLine().split(" ");

		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(inputA[i]);
			b[i] = Integer.parseInt(inputB[i]);
			c[i] = Integer.parseInt(inputC[i]);
			d[i] = Integer.parseInt(inputD[i]);
		}

		System.out.println(zeroQuadrupletsCount(a, b, c, d));
	}

	public static long zeroQuadrupletsCount(int[] a, int[] b, int[] c, int[] d) {
		Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();

		int key;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				key = a[i] + b[j];
				if (table.containsKey(key)) {
					table.replace(key, table.get(key) + 1);
				} else {
					table.put(key, 1);
				}
			}
		}

		long count = 0;

		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < d.length; j++) {
				key = c[i] + d[j];
				if (table.containsKey(key * -1)) {
					count += table.get(key * -1);
				}
			}
		}

		return count;
	}

}
