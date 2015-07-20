package week7.strings.needlehaystack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NeedleHaystack {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		String haystack = reader.readLine();
		String needle = reader.readLine();
		getIndices(haystack, needle);
	}

	private static final int BASE = 31;
	private static final int MOD = 37;

	private static void getIndices(String haystack, String needle) {
		int needleHash = hashFunction(needle, 0, needle.length());
		int currentHash = hashFunction(haystack, 0, needle.length());
		int basePower = (int) Math.pow(BASE, needle.length() - 1);

		if (currentHash == needleHash) {
			System.out.println(0);
		}

		for (int i = 1; i <= haystack.length() - needle.length(); i++) {
			currentHash = ((currentHash - ((haystack.charAt(i - 1) - 'a') * basePower))
					% MOD * BASE)
					% MOD + (haystack.charAt(i + needle.length() - 1) - 'a');
			if (currentHash == needleHash) {
				System.out.println(i);
			}
		}
	}

	private static int hashFunction(String value, int start, int end) {
		int hash = 0;

		for (int i = start; i < end; i++) {
			hash = (hash * BASE + (value.charAt(i) - 'a')) % MOD;
		}

		return hash;
	}
}
