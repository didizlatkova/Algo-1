package topcoder;

import java.util.Vector;

public class ABBA {

	public String canObtain(String initial, String target) {
		Vector<Character> initialVector = new Vector<Character>();
		Vector<Character> targetVector = new Vector<Character>();

		for (int i = 0; i < target.length(); i++) {
			if (i < initial.length()) {
				initialVector.add(initial.charAt(i));
			}
			targetVector.add(target.charAt(i));
		}

		if (play(initialVector, targetVector)) {
			return "Possible";
		}

		return "Impossible";
	}

	public static boolean play(Vector<Character> initialVector,
			Vector<Character> targetVector) {
		if (initialVector.size() == targetVector.size()) {
			for (int i = 0; i < initialVector.size(); i++) {
				if (initialVector.get(i) != targetVector.get(i)) {
					return false;
				}
			}
			return true;
		}

		Vector<Character> reversed = initialVector;
		for (int i = 0; i < initialVector.size(); i++) {
			reversed.add(initialVector.size() - i - 1,
					initialVector.elementAt(i));
		}
		reversed.addElement('B');
		boolean firstMove = play(reversed, targetVector);

		initialVector.addElement('A');
		boolean secondMove = play(initialVector, targetVector);

		return firstMove || secondMove;
	}
}
