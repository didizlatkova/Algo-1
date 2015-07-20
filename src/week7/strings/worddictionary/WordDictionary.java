package week7.strings.worddictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WordDictionary {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		int n = Integer.parseInt(reader.readLine());

		String[] input;
		for (int i = 0; i < n; i++) {
			input = reader.readLine().split(" ");
			if (input[0].equals("insert")) {
				insert(input[1]);
			} else if (input[0].equals("contains")) {
				System.out.println(contains(input[1]));
			}
		}
	}

	private static void insert(String word) {
		Node current = firstNode;
		boolean found;

		for (int i = 0; i < word.length(); i++) {
			found = false;
			for (int j = 0; j < current.children.size(); j++) {
				if (current.children.get(j).content.equals(word.charAt(i))) {
					current = current.children.get(j);
					found = true;
				}
			}
			if (!found) {
				Node newNode = new Node(word.charAt(i));
				current.children.add(newNode);
				current = newNode;
			}
		}
		current.isEndOfWord = true;
	}

	private static boolean contains(String word) {
		Node current = firstNode;
		boolean found;

		for (int i = 0; i < word.length(); i++) {
			found = false;
			for (int j = 0; j < current.children.size(); j++) {
				if (current.children.get(j).content.equals(word.charAt(i))) {
					current = current.children.get(j);
					found = true;
				}
			}
			if (!found) {
				return false;
			}
		}

		if (current.isEndOfWord) {
			return true;
		}

		return false;
	}

	private static Node firstNode = new Node(' ');

	private static class Node {

		public List<Node> children;
		public boolean isEndOfWord;
		public Character content;

		public Node(Character content) {
			this.content = content;
			this.children = new ArrayList<Node>();
		}

	}

}
