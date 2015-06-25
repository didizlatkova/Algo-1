package week3.trees.birthdayranges;

public class BirthdayRanges {

	private BinaryIndexedTree tree;

	public BirthdayRanges(int[] array) {
		this.tree = new BinaryIndexedTree(array);
	}

	// adds people who are born on a specific day
	public void add(int day, int numberOfPeople) {
		tree.update(day, numberOfPeople);
	}

	// removes people who are born on a specific day
	public void remove(int day, int numberOfPeople) {
		tree.update(day, Math.negateExact(numberOfPeople));
	}

	// returns the number of people born in a range
	public int count(int startDay, int endDay) {
		return tree.query(endDay) - tree.query(startDay);
	}

}
