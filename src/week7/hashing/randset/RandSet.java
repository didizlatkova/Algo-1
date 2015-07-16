package week7.hashing.randset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class RandSet {

	private static Vector<Vector<TableElement>> table;
	private static List<Integer> randomList;
	private static int capacity = 200;
	private static int randomCapacity = 0;
	private static final int maxListSize = 10;

	private static class TableElement {
		public int value;
		public int randomIndex;

		public TableElement(int value, int randomIndex) {
			this.value = value;
			this.randomIndex = randomIndex;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		table = new Vector<Vector<TableElement>>(capacity);
		randomList = new ArrayList<Integer>();
		for (int i = 0; i < capacity; i++) {
			table.add(i, new Vector<TableElement>());
		}

		int n = Integer.parseInt(reader.readLine());
		String[] command;
		for (int i = 0; i < n; i++) {
			command = reader.readLine().split(" ");
			if (command[0].equals("insert")) {
				insert(Integer.parseInt(command[1]));
			} else if (command[0].equals("remove")) {
				remove(Integer.parseInt(command[1]));
			} else if (command[0].equals("contains")) {
				System.out.println(contains(Integer.parseInt(command[1])));
			} else if (command[0].equals("random")) {
				System.out.println(random());
			}

		}
	}

	private static int random() {
		return randomList.get(0);
	}

	private static void insert(int value) {
		int hash = hashFunction(value);
		Vector<TableElement> list = table.get(hash);
		for (TableElement element : list) {
			if (element.value == value) {
				return;
			}
		}
		randomList.add(value);
		list.add(new TableElement(value, randomCapacity));
		randomCapacity++;

		// check for too long list
		if (list.size() == maxListSize) {
			System.out.println("resize!");
			capacity *= 2;
			Vector<Vector<TableElement>> newTable = new Vector<Vector<TableElement>>(
					capacity);
			for (int j = 0; j < capacity; j++) {
				newTable.add(j, new Vector<TableElement>());
			}
			for (int j = 0; j < table.size(); j++) {
				for (int k = 0; k < table.get(j).size(); k++) {
					hash = hashFunction(table.get(j).get(k).value);
					newTable.get(hash).add(table.get(j).get(k));
				}
			}
			table = newTable;
		}
	}

	private static void remove(int value) {
		int hash = hashFunction(value);
		Vector<TableElement> list = table.get(hash);
		for (TableElement element : list) {
			if (element.value == value) {
				list.remove(element);
				randomList.set(element.randomIndex,
						randomList.get(randomCapacity - 1));
				randomCapacity--;
				return;
			}
		}
	}

	private static boolean contains(int value) {
		int hash = hashFunction(value);
		Vector<TableElement> list = table.get(hash);
		for (TableElement element : list) {
			if (element.value == value) {
				return true;
			}
		}
		return false;
	}

	private static int hashFunction(int value) {
		return value % capacity;
	}
}
