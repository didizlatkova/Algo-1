package week7.hashing.kintersect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class KIntersect {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		int n = Integer.parseInt(reader.readLine());

		List<List<Integer>> lists = new ArrayList<List<Integer>>();
		String[] listItems;
		for (int i = 0; i < n; i++) {
			listItems = reader.readLine().split(" ");
			List<Integer> list = new ArrayList<Integer>();
			for (int j = 1; j < listItems.length; j++) {
				list.add(Integer.parseInt(listItems[j]));
			}
			lists.add(list);
		}

		MyHashTable table = new MyHashTable(200, 10);
		for (int i = 0; i < lists.get(0).size(); i++) {
			table.insert(lists.get(0).get(i));
		}

		for (int i = 1; i < lists.size() - 1; i++) {
			for (int j = 0; j < lists.get(i).size(); j++) {
				if (!table.contains(lists.get(i).get(j))) {
					table.remove(lists.get(i).get(j));
				}
			}
		}

		for (int j = 0; j < lists.get(lists.size() - 1).size(); j++) {
			if (table.contains(lists.get(lists.size() - 1).get(j))) {
				System.out.println(lists.get(lists.size() - 1).get(j));
			}
		}

	}

	private static class MyHashTable {

		private Vector<Vector<TableElement>> table;
		private List<Integer> randomList;
		private int capacity;
		private int randomCapacity = 0;
		private final int maxListSize;

		public MyHashTable(int capacity, int maxListSize) {
			this.capacity = capacity;
			this.maxListSize = maxListSize;
			table = new Vector<Vector<TableElement>>(capacity);
			randomList = new ArrayList<Integer>();
			
			for (int i = 0; i < capacity; i++) {
				table.add(i, new Vector<TableElement>());
			}
		}

		private static class TableElement {
			public int value;
			public int randomIndex;

			public TableElement(int value, int randomIndex) {
				this.value = value;
				this.randomIndex = randomIndex;
			}
		}

		private void insert(int value) {
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

		private void remove(int value) {
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

		private boolean contains(int value) {
			int hash = hashFunction(value);
			Vector<TableElement> list = table.get(hash);
			for (TableElement element : list) {
				if (element.value == value) {
					return true;
				}
			}
			return false;
		}

		private int hashFunction(int value) {
			return value % capacity;
		}
	}
}
