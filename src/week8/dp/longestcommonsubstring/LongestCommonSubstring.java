package week8.dp.longestcommonsubstring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class LongestCommonSubstring {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		String stringOne = reader.readLine();
		String stringTwo = reader.readLine();

		int[][] matrix = new int[stringOne.length() + 1][stringTwo.length() + 1];

		int maxI = 0;
		int maxJ = 0;
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[i].length; j++) {
				if (stringOne.charAt(i - 1) == stringTwo.charAt(j - 1)) {
					matrix[i][j] = matrix[i - 1][j - 1] + 1;
					if (matrix[i][j] > matrix[maxI][maxJ]) {
						maxI = i;
						maxJ = j;
					}
				}
			}
		}
		
		Stack<Character> stack = new Stack<Character>();
		while(stringOne.charAt(maxI - 1) == stringTwo.charAt(maxJ - 1))
		{
			stack.push(stringOne.charAt(maxI - 1));
			maxI--;
			maxJ--;
		}
		
		while(!stack.isEmpty()){
			System.out.print(stack.pop());
		}
	}

}
