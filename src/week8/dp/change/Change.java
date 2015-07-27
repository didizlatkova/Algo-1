package week8.dp.change;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Change {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		int n = Integer.parseInt(reader.readLine());
		int[] dp = new int[n + 1];
		dp[0] = 1;
		int[] coins = { 1, 2, 5, 10, 20, 50, 100 };

		for (int j = 0; j < coins.length; j++) {
			for (int i = 0; i < n; i++) {
				if (i + coins[j] < dp.length) {
					dp[i + coins[j]] += dp[i];
				} else {
					break;
				}
			}
		}

		System.out.println(dp[n]);
	}

}
