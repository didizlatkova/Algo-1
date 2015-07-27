package week8.dp.longestsubsequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class LongestSubsequence {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		int n = Integer.parseInt(reader.readLine());

		String[] input = reader.readLine().split(" ");
		int[] numbers = new int[n + 1];

		for (int i = 1; i <= input.length; i++) {
			numbers[i] = Integer.parseInt(input[i - 1]);
		}

		int max = 1;
		int[] func = new int[n + 1];
		int[] prev = new int[n + 1];
		int maxIndex = 0;
		func[0] = Integer.MIN_VALUE;
		func[1] = 1;
		prev[1] = 0;

		for (int i = 2; i < func.length; i++) {
			int currentMaxIndex = 0;
			for (int j = 1; j < i; j++) {
				if (numbers[i] > numbers[j] && func[currentMaxIndex] < func[j]) {
					currentMaxIndex = j;
					prev[i] = j;
				}
			}
			if (currentMaxIndex > 0) {
				if (func[currentMaxIndex] + 1 > max) {
					max = func[currentMaxIndex] + 1;
					maxIndex = i;
				}
				func[i] = func[currentMaxIndex] + 1;
			} else {
				func[i] = 1;
			}
		}

		System.out.println(max);
		Stack<Integer> stack = new Stack<Integer>();

		while (maxIndex != 0) {
			stack.push(maxIndex);
			maxIndex = prev[maxIndex];
		}

		while (stack.size() > 1) {
			System.out.print(numbers[stack.pop()] + " ");
		}

		System.out.print(numbers[stack.pop()]);
	}

}
