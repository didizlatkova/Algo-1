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
		HashMap<String, List<Sequence>> map = new HashMap<String, List<Sequence>>();
		String nucleotides;
		String front;
		String middle;
		String back;
		for (int i = 0; i < n; i++) {
			nucleotides = reader.readLine();
			front = nucleotides.substring(0, 3);
			middle = nucleotides.substring(3, nucleotides.length() - 3);
			back = nucleotides.substring(nucleotides.length() - 3,
					nucleotides.length());
			if (!map.containsKey(front)) {
				map.put(front, new ArrayList<Sequence>());
			}
			if (!map.containsKey(back)) {
				map.put(back, new ArrayList<Sequence>());
			}

			map.get(front).add(new Sequence(middle, back));
			map.get(back).add(new Sequence(middle, front));
		}
	}

	private static String restoreSequence(HashMap<String, List<Sequence>> map) {
		HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
		
		List<String> keys = new ArrayList<String>(map.keySet());
		String restoredSequence;
		String startNode = keys.get(0);

		for (String key : keys) {
			if (map.get(key).size() == 1) {
				startNode = key;
				break;
			}
		}	
		

		return "";
	}

	private static class Sequence {
		public String value;
		public String node;

		public Sequence(String value, String node) {
			this.node = node;
			this.value = value;
		}
	}

}
