package week4.graphs.phonenumbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PhoneNumbers {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		int numberOfFriends = Integer.parseInt(reader.readLine());
		String[] phoneNumbers = reader.readLine().split(" ");
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < phoneNumbers.length; i++) {
			map.put(Integer.parseInt(phoneNumbers[i]), i);
		}

		boolean graph[][] = new boolean[numberOfFriends][numberOfFriends];

		for (int i = 0; i < numberOfFriends; i++) {
			String[] phoneNumbersOfFriends = reader.readLine().split(" ");
			for (int j = 1; j <= Integer.parseInt(phoneNumbersOfFriends[0]); j++) {
				graph[i][map.get(Integer.parseInt(phoneNumbersOfFriends[j]))] = true;
			}
		}

		PhoneNumbers numbers = new PhoneNumbers();
		System.out.println(numbers.getMinCalls(graph));
	}

	public int getMinCalls(boolean graph[][]) {
		return dfs(graph);
	}

	private int dfs(boolean graph[][]) {
		boolean[] visited = new boolean[graph.length];

		Stack<Integer> stack = new Stack<Integer>();
		int countCalls = 0;
		int startingPoint = firstFalse(visited);
		while (startingPoint != -1) {
			countCalls++;
			stack.push(startingPoint);

			while (!stack.isEmpty()) {
				int current = stack.pop();
				visited[current] = true;
				for (int i = 0; i < graph[current].length; i++) {
					if (graph[current][i] && !visited[i]) {
						stack.push(i);
					}
				}
			}

			startingPoint = firstFalse(visited);
		}

		return countCalls;

	}

	private static int firstFalse(boolean[] array) {
		for (int i = 0; i < array.length; i++) {
			if (!array[i]) {
				return i;
			}
		}

		return -1;
	}

}
