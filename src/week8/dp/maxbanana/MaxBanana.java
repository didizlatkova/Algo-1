package week8.dp.maxbanana;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaxBanana {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		int n = Integer.parseInt(reader.readLine());

		int[][] bananas = new int[n][n];

		for (int i = 0; i < n; i++) {
			String[] input = reader.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				bananas[i][j] = Integer.parseInt(input[j]);
			}
		}

		for (int i = n - 1; i >= 0; i--) {
			int down = Integer.MIN_VALUE / 2;
			int left = Integer.MIN_VALUE / 2;
			for (int j = 0; j < n; j++) {
				// down
				if (i + 1 < n) {
					down = bananas[i + 1][j];
				}

				// left
				if (j - 1 >= 0) {
					left = bananas[i][j - 1];
				}

				if (i != n-1 || j != 0) {
					// check for down left cell -> it should stay the same
					bananas[i][j] += Math.max(down, left);
				}				
			}
		}

		System.out.println(bananas[0][n - 1]);
	}

}
