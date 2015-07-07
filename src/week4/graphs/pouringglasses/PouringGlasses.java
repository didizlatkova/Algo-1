package week4.graphs.pouringglasses;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import week1.queue.Queue;

public class PouringGlasses {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] capacitiesInput = sc.nextLine().split(" ");
		String[] waterInput = sc.nextLine().split(" ");
		int[] capacities = new int[3];
		int[] water = new int[3];
		for (int i = 0; i < 3; i++) {
			capacities[i] = Integer.parseInt(capacitiesInput[i]);
			water[i] = Integer.parseInt(waterInput[i]);
		}
		int wanted = Integer.parseInt(sc.nextLine());
		calculate(capacities, water, wanted);
		sc.close();
	}

	private static HashMap<Integer, Combination> map = new HashMap<Integer, Combination>();
	private static int combinationsCount = 0;
	private static boolean[][] graph;
	private static int pathLengths[];
	private static boolean[][][] visited;
	private static Queue queue = new Queue();
	private static String[][][] steps;
	private static Combination[][][] parents;

	private static void addCombination(int parent, int glassOne, int glassTwo,
			int glassThree, String step) {
		if (!visited[glassOne][glassTwo][glassThree]) {
			visited[glassOne][glassTwo][glassThree] = true;
			Combination newCombination = new Combination(glassOne, glassTwo,
					glassThree);
			combinationsCount++;
			map.put(combinationsCount, newCombination);
			graph[parent][combinationsCount] = true;
			pathLengths[combinationsCount] = pathLengths[parent] + 1;
			queue.push(combinationsCount);
			steps[glassOne][glassTwo][glassThree] = step;
			parents[glassOne][glassTwo][glassThree] = map.get(parent);
		}
	}

	private static void calculate(int[] capacities, int[] water, int wanted) {
		int allCombinations = 1;
		for (int i = 0; i < capacities.length; i++) {
			allCombinations *= capacities[i];
		}
		graph = new boolean[allCombinations][allCombinations];
		steps = new String[allCombinations][allCombinations][allCombinations];
		visited = new boolean[allCombinations][allCombinations][allCombinations];
		parents = new Combination[allCombinations][allCombinations][allCombinations];
		Combination startCombination = new Combination(water[0], water[1],
				water[2]);
		map.put(0, startCombination);
		pathLengths = new int[graph.length];
		queue.push(combinationsCount);

		while (queue.size() > 0) {
			int current = queue.pop();
			Combination currentCombination = map.get(current);
			if (currentCombination.glass1 == wanted
					|| currentCombination.glass2 == wanted
					|| currentCombination.glass3 == wanted) {
				System.out.println(pathLengths[current]);

				Stack<String> pouringHistory = new Stack<String>();
				while (currentCombination != null) {
					pouringHistory
							.push(steps[currentCombination.glass1][currentCombination.glass2][currentCombination.glass3]);
					currentCombination = parents[currentCombination.glass1][currentCombination.glass2][currentCombination.glass3];
				}

				while (!pouringHistory.isEmpty()) {
					System.out.println(pouringHistory.pop());
				}
				return;
			}

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
