package week5.spanningtrees.dnasequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DNASequence {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		int n = Integer.parseInt(reader.readLine());
		HashMap<Integer, Sequence> map = new HashMap<Integer, Sequence>();
		int mapCounter = 0;
		String nucleotides;
		Sequence sequence;
		List<Integer> keys;
		for (int i = 0; i < n; i++) {
			nucleotides = reader.readLine();
			sequence = new Sequence(nucleotides);
			keys = new ArrayList<Integer>(map.keySet());
			for (Integer key : keys) {
				if (sequence.front == map.get(key).front
						|| sequence.front == map.get(key).back) {
					sequence.frontConnections.add(key);
				}
				if (sequence.back == map.get(key).front
						|| sequence.back == map.get(key).back) {
					sequence.backConnections.add(key);
				}
			}
			map.put(mapCounter, sequence);
			mapCounter++;
		}
	}

	private static String restoreSequence(HashMap<Integer, Sequence> map) {
		List<Integer> keys = new ArrayList<Integer>(map.keySet());
		String restoredSequence;
		int singleConnections = 0;
		
		for (Integer key : keys) {
			if (map.get(key).frontConnections.size() == 0 && map.get(key).backConnections.size() == 0) {
				return "IMPOSSIBLE";
			}
			if (map.get(key).frontConnections.size() == 0 || map.get(key).backConnections.size() == 0) {
				singleConnections = key;
			}
		}
		
		
		
		return "";
	}

	private static class Sequence {
		public String front;
		public String back;
		public String middle;
		public List<Integer> frontConnections = new ArrayList<Integer>();
		public List<Integer> backConnections = new ArrayList<Integer>();

		public Sequence(String nucleotides) {
			front = nucleotides.substring(0, 3);
			back = nucleotides.substring(nucleotides.length() - 3,
					nucleotides.length());
			middle = nucleotides.substring(3, nucleotides.length() - 3);
		}
	}

}
