package topcoder;

import java.util.ArrayList;
import java.util.List;

public class ChessFloor {

	private static int minChanges = Integer.MAX_VALUE;

	public static void main(String[] args) {
		String[] input = { "aba", "bbb", "aba" };

		ChessFloor f = new ChessFloor();
		System.out.println(f.minimumChanges(input));
	}

	public int minimumChanges(String[] floor) {
		List<Character> sameModTwo = new ArrayList<Character>();
		List<Character> diffModTwo = new ArrayList<Character>();

		Character first = null;
		Character second = null;

		for (int i = 0; i < floor.length; i++) {
			for (int j = 0; j < floor[i].length(); j++) {
				if (i % 2 == j % 2) {
					if (!sameModTwo.contains(floor[i].charAt(j))) {
						sameModTwo.add(floor[i].charAt(j));
					}
				} else {
					if (!diffModTwo.contains(floor[i].charAt(j))) {
						diffModTwo.add(floor[i].charAt(j));
					}
				}
			}
		}

		boolean diffColors = false;
		for (int i = 0; i < sameModTwo.size(); i++) {
			for (int j = 0; j < diffModTwo.size(); j++) {
				first = sameModTwo.get(i);
				second = diffModTwo.get(j);
				if (first != second) {
					diffColors = true;
					changes(floor, first, second);
				}
			}
		}

		if (!diffColors) {
			minChanges = (floor.length * floor.length) / 2;
		}

		return minChanges;
	}

	private static void changes(String[] floor, Character first,
			Character second) {
		int changesCount = 0;

		for (int i = 0; i < floor.length; i++) {
			for (int j = 0; j < floor[i].length(); j++) {
				if (i % 2 == j % 2) {
					if (floor[i].charAt(j) != first) {
						changesCount++;
					}
				} else {
					if (floor[i].charAt(j) != second) {
						changesCount++;
					}
				}
				if (changesCount >= minChanges) {
					return;
				}
			}
		}

		minChanges = changesCount;
	}
}
