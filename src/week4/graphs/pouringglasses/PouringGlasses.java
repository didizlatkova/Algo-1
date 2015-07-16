package week4.graphs.pouringglasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class PouringGlasses {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String[] capacitiesInput = reader.readLine().split(" ");
		String[] waterInput = reader.readLine().split(" ");
		int[] capacities = new int[3];
		int[] water = new int[3];
		for (int i = 0; i < 3; i++) {
			capacities[i] = Integer.parseInt(capacitiesInput[i]);
			water[i] = Integer.parseInt(waterInput[i]);
		}
		int wanted = Integer.parseInt(reader.readLine());
		calculate(capacities, water, wanted);
	}

	private static HashMap<Integer, Combination> map = new HashMap<Integer, Combination>();
	private static int combinationsCount = 0;
	private static HashMap<Integer, Integer> pathLengths = new HashMap<Integer, Integer>();
	private static HashMap<Combination, Boolean> visited = new HashMap<Combination, Boolean>();
	private static LinkedList<Integer> queue = new LinkedList<Integer>();
	private static HashMap<Combination, String> steps = new HashMap<Combination, String>();
	private static HashMap<Combination, Combination> parents = new HashMap<Combination, Combination>();
	private static int wanted;

	private static void addCombination(int parent, int glassOne, int glassTwo,
			int glassThree, String step) {
		Combination newCombination = new Combination(glassOne, glassTwo,
				glassThree);
		if (!visited.containsKey(newCombination)) {
			if (newCombination.glass1 == wanted
					|| newCombination.glass2 == wanted
					|| newCombination.glass3 == wanted) {
				System.out.println(pathLengths.get(parent) + 1);

				steps.put(newCombination, step);
				parents.put(newCombination, map.get(parent));

				Stack<String> pouringHistory = new Stack<String>();
				while (steps.containsKey(newCombination)) {
					pouringHistory.push(steps.get(newCombination));
					newCombination = parents.get(newCombination);
				}

				while (!pouringHistory.isEmpty()) {
					System.out.println(pouringHistory.pop());
				}
				System.exit(0);
			}
			visited.put(newCombination, true);

			combinationsCount++;
			map.put(combinationsCount, newCombination);
			pathLengths.put(combinationsCount, pathLengths.get(parent) + 1);
			queue.add(combinationsCount);
			steps.put(newCombination, step);
			parents.put(newCombination, map.get(parent));
		}
	}

	private static void calculate(int[] capacities, int[] water,
			int wantedAmount) {
		wanted = wantedAmount;
		Combination startCombination = new Combination(water[0], water[1],
				water[2]);
		map.put(0, startCombination);
		pathLengths.put(0, 0);
		queue.add(0);
		Combination currentCombination = map.get(0);
		if (currentCombination.glass1 == wanted
				|| currentCombination.glass2 == wanted
				|| currentCombination.glass3 == wanted) {
			System.out.println(pathLengths.get(0));

			Stack<String> pouringHistory = new Stack<String>();
			while (steps.containsKey(currentCombination)) {
				pouringHistory.push(steps.get(currentCombination));
				currentCombination = parents.get(currentCombination);
			}

			while (!pouringHistory.isEmpty()) {
				System.out.println(pouringHistory.pop());
			}
			return;
		}

		while (!queue.isEmpty()) {
			int current = queue.poll();
			currentCombination = map.get(current);

			int amountToPour = 0;

			// 1 to 2
			amountToPour = Math.min(capacities[1] - currentCombination.glass2,
					currentCombination.glass1);
			addCombination(current, currentCombination.glass1 - amountToPour,
					currentCombination.glass2 + amountToPour,
					currentCombination.glass3, "1 2");

			// 1 to 3
			amountToPour = Math.min(capacities[2] - currentCombination.glass3,
					currentCombination.glass1);
			addCombination(current, currentCombination.glass1 - amountToPour,
					currentCombination.glass2, currentCombination.glass3
							+ amountToPour, "1 3");

			// 2 to 1
			amountToPour = Math.min(capacities[0] - currentCombination.glass1,
					currentCombination.glass2);
			addCombination(current, currentCombination.glass1 + amountToPour,
					currentCombination.glass2 - amountToPour,
					currentCombination.glass3, "2 1");

			// 2 to 3
			amountToPour = Math.min(capacities[2] - currentCombination.glass3,
					currentCombination.glass2);
			addCombination(current, currentCombination.glass1,
					currentCombination.glass2 - amountToPour,
					currentCombination.glass3 + amountToPour, "2 3");

			// 3 to 1
			amountToPour = Math.min(capacities[0] - currentCombination.glass1,
					currentCombination.glass3);
			addCombination(current, currentCombination.glass1 + amountToPour,
					currentCombination.glass2, currentCombination.glass3
							- amountToPour, "3 1");

			// 3 to 2
			amountToPour = Math.min(capacities[1] - currentCombination.glass2,
					currentCombination.glass3);
			addCombination(current, currentCombination.glass1,
					currentCombination.glass2 + amountToPour,
					currentCombination.glass3 - amountToPour, "3 2");
		}

	}

	private static class Combination {
		public int glass1;

		public int glass2;

		public int glass3;

		public Combination(int glassOne, int glassTwo, int glassThree) {
			this.glass1 = glassOne;
			this.glass2 = glassTwo;
			this.glass3 = glassThree;
		}
	}

}
