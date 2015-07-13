package week6.shortestpath.lowcostflights;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LowCostFlights {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		int n = Integer.parseInt(reader.readLine());
		int[][] weight = new int[n][n];

		String[] input;
		int number;

		for (int i = 0; i < n; i++) {
			input = reader.readLine().split(" ");
			for (int j = 0; j < input.length; j++) {
				number = Integer.parseInt(input[j]);
				if (number == 0 && i != j) {
					weight[i][j] = Integer.MAX_VALUE / 2;
				} else {
					weight[i][j] = number;
				}
			}
		}

		int[][] dist = getDistances(weight);

		n = Integer.parseInt(reader.readLine());
		String[] command;
		int result;
		for (int i = 0; i < n; i++) {
			command = reader.readLine().split(" ");
			result = dist[Integer.parseInt(command[0])][Integer
					.parseInt(command[1])];
			if (result < Integer.MAX_VALUE / 2) {
				System.out.println(result);
			} else {
				System.out.println("NO WAY");
			}
		}
	}

	private static int[][] getDistances(int[][] weight) {
		for (int k = 0; k < weight.length; k++) {
			for (int u = 0; u < weight.length; u++) {
				for (int v = 0; v < weight[u].length; v++) {
					if (u != v) {
						weight[u][v] = Math.min(weight[u][v], weight[u][k]
								+ weight[k][v]);
					}
				}
			}
		}

		return weight;
	}
}
