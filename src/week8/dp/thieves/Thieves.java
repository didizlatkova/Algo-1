package week8.dp.thieves;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Thieves {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		String[] itemsCapacity = reader.readLine().split(" ");
		int items = Integer.parseInt(itemsCapacity[0]);
		int capacity = Integer.parseInt(itemsCapacity[1]);
		int[] weights = new int[items + 1];
		int[] values = new int[items + 1];
		
		for (int i = 1; i <= items; i++) {
			String[] weightValue = reader.readLine().split(" ");
			weights[i] = Integer.parseInt(weightValue[0]);
			values[i] = Integer.parseInt(weightValue[1]);
		}
		
		int[][] matrix = new int[capacity + 1][items + 1];
		for (int i = 1; i < matrix.length; i++) {
			int newWeight = Integer.MIN_VALUE;
			for (int j = 1; j < matrix.length; j++) {
				if (i - weights[j] >= 0) {
					newWeight = matrix[i - weights[j]][j-1] + values[j];
				}
				
				matrix[i][j] = Math.max(newWeight, matrix[i][j-1]);
			}
		}
		
		int max = matrix[0][items];
		for (int i = 1; i < matrix.length; i++) {
			max = Math.max(max, matrix[i][items]);
		}
		
		System.out.println(max);
	}

}
